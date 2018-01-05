package com.upic.controller;

import java.security.MessageDigest;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upic.common.beans.utils.ChineseCharToEn;
import com.upic.common.utils.redis.service.IRedisService;
import com.upic.condition.BannerCondition;
import com.upic.condition.IntegralLogCondition;
import com.upic.dto.BannerInfo;
import com.upic.dto.GrainCoinLogInfo;
import com.upic.dto.IntegralLogIdInfo;
import com.upic.dto.IntegralLogInfo;
import com.upic.dto.PrizeInfo;
import com.upic.dto.ProjectInfo;
import com.upic.enums.GrainCoinLogStatusEnum;
import com.upic.enums.GrainCoinLogTypeEnum;
import com.upic.enums.IntegralLogStatusEnum;
import com.upic.enums.IntegralLogTypeEnum;
import com.upic.service.BannerService;
import com.upic.service.GrainCoinLogService;
import com.upic.service.IntegralLogService;
import com.upic.service.PrizeService;
import com.upic.service.ProjectService;
import com.upic.service.UserService;
import com.upic.social.user.SocialUsers;
import com.upic.utils.UserUtils;

import io.swagger.annotations.ApiOperation;
import sun.misc.BASE64Encoder;

@RestController
@RequestMapping("/stu")
public class StudetAllController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(StudetAllController.class);

    @Autowired
    private IntegralLogService integralLogService;

    @Autowired
    private GrainCoinLogService grainCoinLogService;

    @Autowired
    private UserService userService;

    @Autowired
    private BannerService bannerService;

    @Autowired
    private PrizeService prizeService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private IRedisService redisService;

    /**
     * 报名
     *
     * @param projectNum
     * @return
     */
    @PostMapping("/signUp")
    @ApiOperation("报名")
    public String signUp(String projectNum) {
        try {
            IntegralLogInfo integralLogInfo = new IntegralLogInfo();
            IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo();
            integralLogIdInfo.setProjectNum(projectNum);
            SocialUsers socialUsers = UserUtils.getUser();

            integralLogIdInfo.setStudentNum(socialUsers.getUserId());

            integralLogInfo.setIntegralLogId(integralLogIdInfo);
            integralLogInfo.setType(IntegralLogTypeEnum.SIGN_IN);
            ProjectInfo projectInfo = projectService.getProjectByNum(projectNum);
            if (projectInfo == null) {
                return null;
            }
            integralLogInfo.setIntegral(projectInfo.getIntegral());
            integralLogInfo.setStatus(IntegralLogStatusEnum.ALREADY_SIGN_UP);

            integralLogInfo.setStudent(socialUsers.getUserNum());
            integralLogInfo.setClazz(socialUsers.getClazz());
            integralLogInfo.setCollege(socialUsers.getCollege());

            integralLogInfo.setCreatTime(new Date());
            integralLogInfo.setProjectName(projectInfo.getProjectName());
            integralLogInfo.setProjectCategory(projectInfo.getProjectCategory());
            integralLogInfo.setCollegeOtherName(projectInfo.getCollegeOtherName());
            if (integralLogService.signUp(integralLogInfo) == null) {
                return "ERROR";
            } else {
                return "SUCCESS";
            }
        } catch (Exception e) {
            LOGGER.info("signUp:" + e.getMessage());
            return null;
        }
    }

    /**
     * 获取当前用户的积分*
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getIntegeral")
    public double getIntegeral() throws Exception {
        try {
            return integralLogService.watchIntegral(UserUtils.getUser().getUserId());
        } catch (Exception e) {
            LOGGER.info("getIntegeral :" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 获取当前学生素拓币*
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getGrainCoin")
    public double getGrainCoin() throws Exception {
        try {
            return grainCoinLogService.watchGrainCoin(UserUtils.getUser().getUserId());
        } catch (Exception e) {
            LOGGER.info("getGrainCoin:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 获取banner图*
     *
     * @param pageable
     * @param b
     * @return
     * @throws Exception
     */
    @GetMapping("/getBanner")
    public Page<BannerInfo> getBanner(@PageableDefault(size = 10) Pageable pageable, BannerCondition b)
            throws Exception {
        try {
            return bannerService.searchBanner(b, pageable);
        } catch (Exception e) {
            LOGGER.info("getBanner:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 奖品交换*
     *
     * @param prizeId
     * @return
     */
    @GetMapping("/getExchangePrize")
    public String getExchangePrize(Long prizeId) {
        try {
            PrizeInfo prizeInfo = prizeService.getPrizeById(prizeId);
            // 积分不够
            double watchIntegral = integralLogService.watchIntegral(UserUtils.getUser().getUserId());
            if (watchIntegral - prizeInfo.getScore() < 0) {
                return "ERROR";
            }
            if (prizeInfo != null) {
                GrainCoinLogInfo grainCoinLogInfo = new GrainCoinLogInfo();
                grainCoinLogInfo.setEvent(UserUtils.getUser().getUserId() + "兑换" + prizeInfo.getPrizeName());
                grainCoinLogInfo.setPrizeId(prizeId);
                grainCoinLogInfo.setCreatTime(new Date());
                grainCoinLogInfo.setScore(-prizeInfo.getScore());
                grainCoinLogInfo.setType(GrainCoinLogTypeEnum.PAYMENT);
                grainCoinLogInfo.setStatus(GrainCoinLogStatusEnum.HAVEDONE);
                SocialUsers socialUsers = UserUtils.getUser();
                grainCoinLogInfo.setUsername(socialUsers.getUserNum());
                grainCoinLogInfo.setUserNum(socialUsers.getUserId());
                grainCoinLogInfo.setPrizeName(prizeInfo.getPrizeName());
                grainCoinLogService.saveGrainCoinLog(grainCoinLogInfo);
                return "SUCCESS";
            }
        } catch (Exception e) {
            LOGGER.info("getExchangePrize:" + e.getMessage());
        }
        return "ERROR";
    }

    /**
     * 查找积分列表*
     *
     * @param i
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/getPageIntegral")
    public Page<IntegralLogInfo> getPageIntegral(IntegralLogCondition i, @PageableDefault(size = 10) Pageable pageable)
            throws Exception {
        try {
            return integralLogService.searchIntegralLog(i, pageable);
        } catch (Exception e) {
            LOGGER.info("getProjectInfo:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 查找积分列表*
     *
     * @param i
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/getMyPageIntegral")
    public Page<IntegralLogInfo> getMyPageIntegral(IntegralLogCondition i,
                                                   @PageableDefault(size = 10) Pageable pageable) throws Exception {
        try {
            IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo();
            integralLogIdInfo.setStudentNum(UserUtils.getUser().getUserId());
            i.setIntegralLogId(integralLogIdInfo);
            return integralLogService.searchIntegralLog(i, pageable);
        } catch (Exception e) {
            LOGGER.info("getMyPageIntegral:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 是否已报名*（需要修改）
     *
     * @param studentNum
     * @param projectNum
     * @return
     * @throws Exception
     */
    @GetMapping("/isSignUpByIntegralLogId")
    public String isSignUpByIntegralLogId(String studentNum, String projectNum) throws Exception {
        try {

            IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo(UserUtils.getUser().getUserId(), projectNum);
            IntegralLogInfo integralLogInfo = integralLogService.getByIntegralLogId(integralLogIdInfo);
            return integralLogInfo == null ? "error" : "success";
        } catch (Exception e) {
            LOGGER.info("isSignUpByIntegralLogId:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 是否已报名*（需要修改）
     *
     * @param projectNum
     * @return
     * @throws Exception
     */
    @GetMapping("/isSignUpByProjectNum")
    public String isSignUpByProjectNum(String projectNum) throws Exception {
        try {
            IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo(UserUtils.getUser().getUserId(), projectNum);
            IntegralLogInfo integralLogInfo = integralLogService.getByIntegralLogId(integralLogIdInfo);
            return integralLogInfo == null ? "error" : "success";
        } catch (Exception e) {
            LOGGER.info("isSignUpByProjectNum:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 积分申请提交*
     *
     * @param integralLogInfo
     * @return @RequestParam("file") MultipartFile file, MultipartHttpServletRequest
     * request
     */
    @PostMapping("/postIntegralLog")
    public IntegralLogInfo postIntegralLog(IntegralLogInfo integralLogInfo) throws Exception {
        try {
            integralLogInfo.setStatus(IntegralLogStatusEnum.PENDING_AUDIT);
            integralLogInfo.setType(IntegralLogTypeEnum.VOLUNTARY_APPLICATION);
            IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo();
            // UserInfo userInfo = getUser();
            SocialUsers userInfo = UserUtils.getUser();
            integralLogIdInfo.setStudentNum(userInfo.getUserId());
            // integralLogIdInfo.setStudentNum(userInfo.getUserId());
            ChineseCharToEn cte = new ChineseCharToEn();
            if (integralLogInfo.getField1().equals("radioselect1")) {
                integralLogIdInfo.setProjectNum("VOLUNTARY_APPLICATION" + integralLogInfo.getField2());
            } else {
                integralLogIdInfo.setProjectNum("VOLUNTARY_APPLICATION"
                        + cte.getAllFirstLetter(integralLogInfo.getProjectName()).toUpperCase());
            }

            String projectCategory = splitMyProjectCategory(integralLogInfo.getEvent());
            integralLogInfo.setProjectCategory(projectCategory);
            integralLogInfo.setClazz(userInfo.getClazz());
            integralLogInfo.setCollege(userInfo.getCollege());
            integralLogInfo.setCreatTime(new Date());
            integralLogInfo.setStudent(userInfo.getUserNum());
            integralLogInfo.setIntegralLogId(integralLogIdInfo);
            integralLogInfo.setCollegeOtherName(cte.getAllFirstLetter(userInfo.getCollege()).toUpperCase());
            integralLogService.saveIntegralLog(integralLogInfo);
            return integralLogInfo;
        } catch (Exception e) {
            LOGGER.info("postIntegralLog:" + e.getMessage());
            return null;
        }
    }

    /**
     * 修改积分状态
     *
     * @return
     */
    @PostMapping("/updateIntegralLog")
    public IntegralLogInfo updateIntegralLog(IntegralLogInfo i, String projectNum) {
        try {
            IntegralLogInfo integralLogInfo = integralLogService
                    .getByIntegralLogId(new IntegralLogIdInfo(UserUtils.getUser().getUserId(), projectNum));
            // IntegralLogInfo integralLogInfo = integralLogService.getByIntegralLogId(new
            // IntegralLogIdInfo(getUser().getUserId(), projectNum));
            integralLogInfo.setProjectName(i.getProjectName());
            integralLogInfo.setProjectCategory(i.getProjectCategory());
            integralLogInfo.setContent(i.getContent());
            ChineseCharToEn cte = new ChineseCharToEn();
            if (integralLogInfo.getField1().equals("radioselect1")) {
                integralLogInfo.getIntegralLogId().setProjectNum("VOLUNTARY_APPLICATION" + i.getField2());
            } else {
                integralLogInfo.getIntegralLogId().setProjectNum(
                        "VOLUNTARY_APPLICATION" + cte.getAllFirstLetter(i.getProjectName()).toUpperCase());
            }

            if (integralLogInfo != null) {
                integralLogInfo.setStatus(failIntegralLogStatus(integralLogInfo.getStatus()));
                integralLogInfo = integralLogService.changeAllIntegralLogStatus(integralLogInfo);
                return integralLogInfo;
            }
        } catch (Exception e) {
            LOGGER.info("updateIntegralLog:" + e.getMessage());
        }
        return null;
    }

    /**
     * 修改积分状态
     *
     * @return
     */
    @PostMapping("/updateIntegralLogNew")
    public IntegralLogInfo updateIntegralLogNew(String content, String projectNum) {
        try {
            IntegralLogInfo integralLogInfo = integralLogService
                    .getByIntegralLogId(new IntegralLogIdInfo(UserUtils.getUser().getUserId(), projectNum));
            // IntegralLogInfo integralLogInfo = integralLogService.getByIntegralLogId(new
            // IntegralLogIdInfo(getUser().getUserId(), projectNum));
            integralLogInfo.setContent(content);
            if (integralLogInfo != null) {
                integralLogInfo.setStatus(failIntegralLogStatus(integralLogInfo.getStatus()));
                integralLogInfo = integralLogService.changeAllIntegralLogStatus(integralLogInfo);
                return integralLogInfo;
            }
        } catch (Exception e) {
            LOGGER.info("updateIntegralLog:" + e.getMessage());
        }
        return null;
    }

    private String splitMyProjectCategory(String event) {
        String[] projectCategoryList = event.split("/");
        return projectCategoryList[0];
    }

    /**
     * 根据自身编号获取参加过的自主申报项目（可能没用）
     *
     * @param studentNum
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/getIntegralLogByMySelf")
    public Page<IntegralLogInfo> getIntegralLogByMySelf(String studentNum,
                                                        @PageableDefault(size = 10) Pageable pageable) throws Exception {
        try {
            return integralLogService.getIntegralLogByMySelf(studentNum, pageable);
        } catch (Exception e) {
            LOGGER.info("getIntegralLogByMySelf:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 学生查看自身的积分日志（可能没用）
     *
     * @param studentNum
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/getAllIntegralLogByStudentNum")
    public Page<IntegralLogInfo> getAllIntegralLogByStudentNum(String studentNum,
                                                               @PageableDefault(size = 10) Pageable pageable) throws Exception {
        try {
            return integralLogService.getAllIntegralLogByStudentNum(studentNum, pageable);
        } catch (Exception e) {
            LOGGER.info("getAllIntegralLogByStudentNum:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 获取申报中的积分（可能没用）
     *
     * @param studentNum
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/getIntegralLogDeclaring")
    public Page<IntegralLogInfo> getIntegralLogDeclaring(String studentNum,
                                                         @PageableDefault(size = 10) Pageable pageable) throws Exception {
        try {
            return integralLogService.getIntegralLogDeclaring(studentNum, pageable);
        } catch (Exception e) {
            LOGGER.info("getIntegralLogDeclaring:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/qrCodeConsumption")
    public String qrCodeConsumption(IntegralLogInfo i, String accessToken, String projectNum) {
        try {
            SocialUsers socialUsers = UserUtils.getUser();
            IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo(socialUsers.getUserId(), projectNum);
            i.setIntegralLogId(integralLogIdInfo);
            return integralLogService.qrCodeConsumption(i, accessToken);
        } catch (Exception e) {
            LOGGER.info("qrCodeConsumption:" + e.getMessage());
        }
        return null;
    }

    /**
     * 1001 超时 1002服务器异常
     *
     * @param response
     * @param projectNum
     * @param nowTime
     */
    @GetMapping("/qrCodeConsumption")
    public void qrCodeConsume(HttpServletResponse response, String projectNum, String nowTime) {
        String msg = "";
        try {
            String qrNum = redisService.get("QR" + projectNum);
            if (qrNum == null) {
                msg = "1001";
                throw new Exception(msg);
            }
            String token = projectNum + nowTime + "QR";
            // 验签
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            BASE64Encoder base64Encoder = new BASE64Encoder();
            String accessToken = base64Encoder.encode(messageDigest.digest(token.getBytes("utf-8")));
            if (!qrNum.equals(accessToken)) {
                msg = "1002";
                throw new Exception(msg);
            }
            // 获取项目
            ProjectInfo projectByNum = projectService.getProjectByNum(projectNum);
            // 属于当场消费的活动
            if (projectByNum.getSignUpStartTime() == null) {
                ChineseCharToEn c = new ChineseCharToEn();
                SocialUsers user = UserUtils.getUser();
                IntegralLogInfo i = new IntegralLogInfo();
                i.setCreatTime(new Date());
                i.setClazz(user.getClazz());
                // i.setContent(content);
                i.setCollege(user.getCollege());
                i.setCollegeOtherName(c.getAllFirstLetter(user.getCollege()).toUpperCase());
                i.setEvent(projectByNum.getProjectName() + "扫码参加");
                IntegralLogIdInfo id = new IntegralLogIdInfo();
                id.setProjectNum(projectNum);
                id.setStudentNum(user.getUserId());
                i.setIntegralLogId(id);
                i.setIntegral(projectByNum.getIntegral());
                i.setProjectCategory(projectByNum.getProjectCategory());
                i.setProjectName(projectByNum.getProjectName());
                i.setStatus(IntegralLogStatusEnum.SIGNED_IN);
                i.setStudent(user.getUserNum());
                i.setType(IntegralLogTypeEnum.SIGN_IN);
                integralLogService.saveIntegralLog(i);
                response.sendRedirect("/index.html?msg=" + msg);
                return;
            }
            // 获取用户
            SocialUsers user = UserUtils.getUser();
            // 更改报名积分状态
            msg = integralLogService.changeIntegralLogToSignedIn(projectNum, user.getUserId());
            // 二维码扫完后跳转地址 并传递msg
            response.sendRedirect("/index.html?msg=" + msg);
        } catch (Exception e) {
            LOGGER.info("qrCodeConsumption:" + e.getMessage());
        }
    }

    /**
     * 修改未通过积分状态
     *
     * @param status
     * @return
     */
    private IntegralLogStatusEnum failIntegralLogStatus(IntegralLogStatusEnum status) {
        if (status == IntegralLogStatusEnum.PENDING_AUDIT_BEFORE) {
            return IntegralLogStatusEnum.PENDING_AUDIT_BEFORE_FAIL;
        } else if (status == IntegralLogStatusEnum.PENDING_AUDIT) {
            return IntegralLogStatusEnum.PENDING_AUDIT_FAIL;
        } else if (status == IntegralLogStatusEnum.PENDING_AUDIT_AGAIN) {
            return IntegralLogStatusEnum.PENDING_AUDIT_AGAIN_FAIL;
        } else if (status == IntegralLogStatusEnum.PENDING_AUDIT_FINAL) {
            return IntegralLogStatusEnum.PENDING_AUDIT_FINAL_FAIL;
        } else if (status == IntegralLogStatusEnum.PENDING_AUDIT_BEFORE_FAIL) {
            return IntegralLogStatusEnum.PENDING_AUDIT_BEFORE;
        } else if (status == IntegralLogStatusEnum.PENDING_AUDIT_FAIL) {
            return IntegralLogStatusEnum.PENDING_AUDIT;
        } else if (status == IntegralLogStatusEnum.PENDING_AUDIT_AGAIN_FAIL) {
            return IntegralLogStatusEnum.PENDING_AUDIT_AGAIN;
        } else {
            return IntegralLogStatusEnum.PENDING_AUDIT_FINAL;
        }
    }
}
