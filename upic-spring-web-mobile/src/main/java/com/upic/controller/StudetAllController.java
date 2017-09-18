package com.upic.controller;

import com.upic.condition.*;
import com.upic.dto.*;
import com.upic.enums.IntegralLogStatusEnum;
import com.upic.enums.IntegralLogTypeEnum;
import com.upic.service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/stu")
public class StudetAllController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(StudetAllController.class);

    @Autowired
    private IntegralLogService integralLogService;

    @Autowired
    private GrainCoinLogService grainCoinLogService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private BannerService bannerService;

    @Autowired
    private PrizeService prizeService;

    /**
     * 获取当前用户的积分
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getIntegeral")
    public double getIntegeral() throws Exception {
        try {
            // Authentication authentication =
            // SecurityContextHolder.getContext().getAuthentication();
            // System.out.println(authentication);
            // SocialUser user = null;
            // if (authentication != null) {
            // user = (SocialUser) authentication.getPrincipal();
            // }
            // if(user==null){
            // throw new Exception("获取用户失败");
            // }
            return integralLogService.watchIntegral("student01");
        } catch (Exception e) {
            LOGGER.info("getIntegeral :" + e.getMessage());
            throw new Exception(e.getMessage());
        }

    }

    /**
     * 获取当前学生素拓币
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getCrainCoin")
    public double getCrainCoin() throws Exception {
        try {
            // Authentication authentication =
            // SecurityContextHolder.getContext().getAuthentication();
            // System.out.println(authentication);
            // SocialUser user = null;
            // if (authentication != null) {
            // user = (SocialUser) authentication.getPrincipal();
            // }
            // if(user==null){
            // throw new Exception("获取用户失败");
            // }
            return grainCoinLogService.watchGrainCoin("student01");
        } catch (Exception e) {
            LOGGER.info("getCrainCoin:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 获取积分日志列表
     *
     * @param pageable
     * @param c
     * @return
     * @throws Exception
     */
    @GetMapping("/getIntegralLogPage")
    public Page<IntegralLogInfo> getIntegralLogPage(@PageableDefault(size = 10) Pageable pageable,
                                                    IntegralLogCondition c) throws Exception {
        try {
            return integralLogService.searchIntegralLog(c, pageable);
        } catch (Exception e) {
            LOGGER.info("getCrainCoin:" + e.getMessage());
            throw new Exception(e.getMessage());
        }

    }

    /**
     * 根据条件查询活动
     *
     * @throws Exception
     */
    @GetMapping("/getProject")
    public Page<ProjectInfo> getProject(@PageableDefault(size = 10) Pageable pageable, ProjectCondition p)
            throws Exception {
        try {
            return projectService.searchProject(p, pageable);
        } catch (Exception e) {
            LOGGER.info("getProject:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 获取banner图
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
     * 获取奖品
     *
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/getPrize")
    public Page<PrizeInfo> getPrize(@PageableDefault(size = 10) Pageable pageable, PrizeCondition p) throws Exception {
        try {
            return prizeService.searchPrizes(p, pageable);
        } catch (Exception e) {
            LOGGER.info("getPrize:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 奖品交换
     *
     * @param prizeId
     * @return
     */
    @GetMapping("/getExchangePrize")
    public PrizeInfo getExchangePrize(Long prizeId) {
        return null;
    }

    /**
     * 根据projectNum获取projectInfo
     *
     * @param projectNum
     * @return
     * @throws Exception
     */
    @GetMapping("/getProjectInfo")
    public ProjectInfo getProjectInfo(String projectNum) throws Exception {
        try {
            return projectService.getProjectByNum(projectNum);
        } catch (Exception e) {
            LOGGER.info("getProjectInfo:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 查找积分列表
     *
     * @param i
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/getPageIntegral")
    public Page<IntegralLogInfo> getPageIntegral(IntegralLogCondition i, @PageableDefault(size = 10) Pageable pageable) throws Exception {
        try {
            return integralLogService.searchIntegralLog(i, pageable);
        } catch (Exception e) {
            LOGGER.info("getProjectInfo:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 根据项目编号查询项目人数
     *
     * @param projectNum
     * @return
     * @throws Exception
     */
    @GetMapping("/getSignUpNumberByProjectNum")
    public int getSignUpNumberByProjectNum(String projectNum) throws Exception {
        try {
            return integralLogService.getSignUpNumberByProjectNum(projectNum);
        } catch (Exception e) {
            LOGGER.info("getSignUpNumberByProjectNum:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 是否已报名
     *
     * @param studentNum
     * @param projectNum
     * @return
     * @throws Exception
     */
    @GetMapping("/isSignUpByIntegralLogId")
    public String isSignUpByIntegralLogId(String studentNum, String projectNum) throws Exception {
        try {
            IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo(studentNum, projectNum);
            IntegralLogInfo integralLogInfo = integralLogService.getByIntegralLogId(integralLogIdInfo);
            return integralLogInfo == null ? "error" : "success";
        } catch (Exception e) {
            LOGGER.info("isSignUpByIntegralLogId:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 自主申报
     *
     * @param integralLogInfo
     * @param file
     * @param request
     * @return
     */
    @GetMapping("/postIntegralLog")
    public IntegralLogInfo postIntegralLog(IntegralLogInfo integralLogInfo, @RequestParam("file") MultipartFile file, MultipartHttpServletRequest request) throws Exception {
        try {
//            Iterator<String> iterator = request.getFileNames();
//            MultipartFile multipartFile = file;
//            while (iterator.hasNext()) {
//                multipartFile = request.getFile(iterator.next());
//            }

            integralLogInfo.setStatus(IntegralLogStatusEnum.PENDING_AUDIT);
            integralLogInfo.setType(IntegralLogTypeEnum.VOLUNTARY_APPLICATION);
            integralLogInfo = integralLogService.saveIntegralLog(integralLogInfo);

            return integralLogInfo;
        } catch (Exception e) {
            LOGGER.info("postIntegralLog:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 根据自身编号获取参加过的自主申报项目
     *
     * @param studentNum
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/getIntegralLogByMySelf")
    public Page<IntegralLogInfo> getIntegralLogByMySelf(String studentNum, @PageableDefault(size = 10) Pageable pageable) throws Exception {
        try {
            return integralLogService.getIntegralLogByMySelf(studentNum, pageable);
        } catch (Exception e) {
            LOGGER.info("getIntegralLogByMySelf:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
