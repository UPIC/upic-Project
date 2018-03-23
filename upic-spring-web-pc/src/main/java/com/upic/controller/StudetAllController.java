package com.upic.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.upic.common.base.enums.JugeType;
import com.upic.common.base.enums.JygeTypeEnum;
import com.upic.common.beans.utils.ChineseCharToEn;
import com.upic.common.document.excel.ExcelDocument;
import com.upic.common.fdfs.FastDFSClient;
import com.upic.condition.*;
import com.upic.dto.*;
import com.upic.dto.excel.IntegralLogInfoExcel;
import com.upic.enums.*;
import com.upic.service.*;
//import com.upic.social.user.SocialUsers;
//import com.upic.utils.UserUtils;

import com.upic.social.user.SocialUsers;
import com.upic.utils.Constans;
import com.upic.utils.UserUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.security.provider.MD5;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @Autowired
    private UserService userService;

    /**
     * 重置密码
     *
     * @param password
     * @param userNum
     * @return
     */
    @PostMapping("/changePwd")
    public String changePwd(String password, String userNum) {
        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String newPwd = bCryptPasswordEncoder.encode("123456");
            UserInfo userInfo = userService.getUserByUserNum(userNum);
            userInfo.setPassword(newPwd);
            userService.updateUser(userInfo);
            return "SUCCESS";
        } catch (Exception e) {
            LOGGER.info("changePwd :" + e.getMessage());
            return null;
        }
    }

    /**
     * 修改密码
     *
     * @param oldPwd
     * @param newPwd
     * @param userNum
     * @return
     */
    @PostMapping("/changeThePwd")
    public String changeThePwd(String oldPwd, String newPwd, String userNum) {
        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            UserInfo userInfo = userService.getUserByUserNum(userNum);
            if (!bCryptPasswordEncoder.matches(oldPwd, userInfo.getPassword())) {
                return "ERROR_OLD_PWD";
            } else {
                userInfo.setPassword(bCryptPasswordEncoder.encode(newPwd));
                userService.updateUser(userInfo);
                return "SUCCESS";
            }
        } catch (Exception e) {
            LOGGER.info("changeThePwd :" + e.getMessage());
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
    public Double getIntegeral() throws Exception {
        try {
            return integralLogService.watchIntegral(UserUtils.getUser().getUserId());
        } catch (Exception e) {
            LOGGER.info("getIntegeral :" + e.getMessage());
            return null;
        }
    }

    /**
     * 获取当前用户的积分*
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getIntegeralByUserNum")
    public Double getIntegeralByUserNum(String userNum) throws Exception {
        try {
            return integralLogService.watchIntegral(userNum);
        } catch (Exception e) {
            LOGGER.info("getIntegeral :" + e.getMessage());
            return null;
        }
    }

    /**
     * 获取当前学生素拓币*
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getGrainCoin")
    public Double getGrainCoin() throws Exception {
        try {
            return grainCoinLogService.watchGrainCoin(UserUtils.getUser().getUserId());
        } catch (Exception e) {
            LOGGER.info("getGrainCoin:" + e.getMessage());
            return null;
        }
    }

    /**
     * 获取当前学生素拓币*
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getGrainCoinByUserNum")
    public Double getGrainCoinByUserNum(String userNum) throws Exception {
        try {
            return grainCoinLogService.watchGrainCoin(userNum);
        } catch (Exception e) {
            LOGGER.info("getGrainCoin:" + e.getMessage());
            return null;
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
    public Page<BannerInfo> getBanner(@PageableDefault(size = 10) Pageable pageable, BannerCondition b) {
        try {
            return bannerService.searchBanner(b, pageable);
        } catch (Exception e) {
            LOGGER.info("getBanner:" + e.getMessage());
            return null;
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
                SocialUsers socialUsers = getUser();
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
            return null;
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
            IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo(studentNum, projectNum);
            IntegralLogInfo integralLogInfo = integralLogService.getByIntegralLogId(integralLogIdInfo);
            return integralLogInfo == null ? "error" : "success";
        } catch (Exception e) {
            LOGGER.info("isSignUpByIntegralLogId:" + e.getMessage());
            return null;
        }
    }

    /**
     * 图片上传
     *
     * @return
     */
    @PostMapping("/picUpload")
    public String picUpload(HttpServletRequest request) {
        try {
            // 转型为MultipartHttpRequest：
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // 获得文件：
            MultipartFile inputFile = multipartRequest.getFile("file");

            FastDFSClient fastDFSClient = new FastDFSClient();
            String upload = fastDFSClient.uploadFile(inputFile.getBytes(), inputFile.getOriginalFilename());

            upload = "116.62.169.117:22122/" + upload;

            return upload;
        } catch (Exception e) {
            LOGGER.info("picUpload:" + e.getMessage());
        }
        return null;
    }

    /**
     * 积分申请提交*
     *
     * @param integralLogInfo
     * @return @RequestParam("file") MultipartFile file, MultipartHttpServletRequest
     * request
     */
    @PostMapping("/postIntegralLog")
    public IntegralLogInfo postIntegralLog(IntegralLogInfo integralLogInfo, HttpServletRequest request)
            throws Exception {
        String url = null;
        try {
            url = getUrl(request, "file");
            if (url == null) {
                return null;
            }
            if (url.equals("NO_FILE")) {
                url = null;
            }
            List<String> pics = new ArrayList<>();
            pics.add(url);
            integralLogInfo.setIntegralLogPic(pics);

            integralLogInfo.setStatus(IntegralLogStatusEnum.PENDING_AUDIT);
            integralLogInfo.setType(IntegralLogTypeEnum.VOLUNTARY_APPLICATION);
            IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo();
            SocialUsers userInfo = UserUtils.getUser();
            integralLogIdInfo.setStudentNum(userInfo.getUserId());
            ChineseCharToEn cte = new ChineseCharToEn();
            if (integralLogInfo.getField1().equals("radioselect1")) {
                integralLogIdInfo.setProjectNum("VOLUNTARY_APPLICATION" + integralLogInfo.getField2());
            } else {
                integralLogIdInfo.setProjectNum("VOLUNTARY_APPLICATION"
                        + cte.getAllFirstLetter(integralLogInfo.getProjectName()).toUpperCase());
            }

            integralLogInfo.setClazz(userInfo.getClazz());
            integralLogInfo.setCollege(userInfo.getCollege());
            integralLogInfo.setCreatTime(new Date());
            integralLogInfo.setStudent(userInfo.getUserNum());
            integralLogInfo.setIntegralLogId(integralLogIdInfo);
            integralLogInfo.setCollegeOtherName(cte.getAllFirstLetter(userInfo.getCollege()).toUpperCase());
            integralLogInfo = integralLogService.saveIntegralLog(integralLogInfo);
            // 以后版本可以根据版本覆盖，例如有个标识符，为true的时候，就是要覆盖老版本，就先删除，再添加，建议在service操作，事务版本控制
            if (integralLogInfo == null) {
                throw new Exception("此项目已申请过");
            }
            return integralLogInfo;
        } catch (Exception e) {
            LOGGER.info("postIntegralLog:" + e.getMessage());
            if (integralLogInfo == null) {
                String[] split = url.split(Constans.STRONGE_URL);
                if (split != null && split.length > 1) {
                    FastDFSClient.deleteFile(split[1]);
                }
            }
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
                    .getByIntegralLogId(new IntegralLogIdInfo(getUser().getUserId(), projectNum));
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
     * 根据自身编号获取参加过的自主申报项目（可能没用）
     *
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/getIntegralLogByMySelf")
    public Page<IntegralLogInfo> getIntegralLogByMySelf(@PageableDefault(size = 10) Pageable pageable) {
        try {
            return integralLogService.getIntegralLogByMySelf(UserUtils.getUser().getUserId(), pageable);
        } catch (Exception e) {
            LOGGER.info("getIntegralLogByMySelf:" + e.getMessage());
            return null;
        }
    }

    /**
     * 学生查看自身的积分日志（可能没用）
     *
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/getAllIntegralLogByStudentNum")
    public Page<IntegralLogInfo> getAllIntegralLogByStudentNum(@PageableDefault(size = 10) Pageable pageable)
            throws Exception {
        try {
            Page<IntegralLogInfo> integralLogInfoPage = integralLogService
                    .getIntegralLogByStudentNum(UserUtils.getUser().getUserId(), pageable);
            return integralLogInfoPage;
        } catch (Exception e) {
            LOGGER.info("getAllIntegralLogByStudentNum:" + e.getMessage());
            return null;
        }
    }

    /**
     * 学生查看自身的积分日志（加Condition）
     *
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/searchIntegralLog")
    public Page<IntegralLogInfo> searchIntegralLog(@PageableDefault(size = 10) Pageable pageable,
                                                   IntegralLogCondition integralLogCondition) {
        try {
            if (integralLogCondition.getField5() != null) {
                List<Map<String, Object>> orList = new ArrayList<Map<String, Object>>();
                Map<String, Object> map = new IdentityHashMap<>();
                if (integralLogCondition.getField5().equals("PENDING_AUDIT_BEFORE/PENDING_AUDIT/PENDING_AUDIT_AGAIN/PENDING_AUDIT_FINAL")) {
                    map.put(new String("status"), new JugeType(JygeTypeEnum.EQUAL, IntegralLogStatusEnum.PENDING_AUDIT_BEFORE));
                    map.put(new String("status"), new JugeType(JygeTypeEnum.EQUAL, IntegralLogStatusEnum.PENDING_AUDIT));
                    map.put(new String("status"), new JugeType(JygeTypeEnum.EQUAL, IntegralLogStatusEnum.PENDING_AUDIT_AGAIN));
                    map.put(new String("status"), new JugeType(JygeTypeEnum.EQUAL, IntegralLogStatusEnum.PENDING_AUDIT_FINAL));
                    integralLogCondition.setField5(null);
                } else if (integralLogCondition.getField5().equals("PENDING_AUDIT_BEFORE_FAIL/PENDING_AUDIT_FAIL/PENDING_AUDIT_AGAIN_FAIL/PENDING_AUDIT_FINAL_FAIL")) {
                    map.put(new String("status"), new JugeType(JygeTypeEnum.EQUAL, IntegralLogStatusEnum.PENDING_AUDIT_BEFORE_FAIL));
                    map.put(new String("status"), new JugeType(JygeTypeEnum.EQUAL, IntegralLogStatusEnum.PENDING_AUDIT_FAIL));
                    map.put(new String("status"), new JugeType(JygeTypeEnum.EQUAL, IntegralLogStatusEnum.PENDING_AUDIT_AGAIN_FAIL));
                    map.put(new String("status"), new JugeType(JygeTypeEnum.EQUAL, IntegralLogStatusEnum.PENDING_AUDIT_FINAL_FAIL));
                    integralLogCondition.setField5(null);
                } else if (integralLogCondition.getField5().equals("SIGNED_IN/COMPLETED")) {
                    map.put(new String("status"), new JugeType(JygeTypeEnum.EQUAL, IntegralLogStatusEnum.SIGNED_IN));
                    map.put(new String("status"), new JugeType(JygeTypeEnum.EQUAL, IntegralLogStatusEnum.COMPLETED));
                    integralLogCondition.setField5(null);
                }
                orList.add(map);
                integralLogCondition.setOrList(orList);
            }
            IntegralLogIdInfo integralLogId = new IntegralLogIdInfo();
            integralLogId.setStudentNum(UserUtils.getUser().getUserId());
            integralLogCondition.setIntegralLogId(integralLogId);
            Page<IntegralLogInfo> integralLogInfoPage = integralLogService.searchIntegralLog(integralLogCondition,
                    pageable);
            return integralLogInfoPage;
        } catch (Exception e) {
            LOGGER.info("searchIntegralLog:" + e.getMessage());
            return null;
        }
    }

    /**
     * 学生查看积分日志（可能没用）
     *
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/getAllIntegralLogByUserNum")
    public Page<IntegralLogInfo> getAllIntegralLogByUserNum(String userNum,
                                                            @PageableDefault(size = 10) Pageable pageable) {
        try {
            Page<IntegralLogInfo> integralLogInfoPage = integralLogService.getAllIntegralLogByStudentNum(userNum,
                    pageable);
            return integralLogInfoPage;
        } catch (Exception e) {
            LOGGER.info("getAllIntegralLogByUserNum:" + e.getMessage());
            return null;
        }
    }

    /**
     * 获取申报中的积分（可能没用）
     *
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/getIntegralLogDeclaring")
    public Page<IntegralLogInfo> getIntegralLogDeclaring(@PageableDefault(size = 10) Pageable pageable)
            throws Exception {
        try {
            return integralLogService.getIntegralLogDeclaring(UserUtils.getUser().getUserId(), pageable);
        } catch (Exception e) {
            LOGGER.info("getIntegralLogDeclaring:" + e.getMessage());
            return null;
        }
    }

    /*****************************************
     * 导入导出
     *****************************************/

    /**
     * 积分导入
     *
     * @param baseModel
     * @return
     */
    @ApiOperation("积分导入")
    @PostMapping("/loadIntegralLogInfo")
    // public List<Object> loadIntegralLogInfo(
    // @RequestParam("inputFile")CommonsMultipartFile inputFile, String baseModel) {
    public List<Object> loadIntegralLogInfo(HttpServletRequest request, String baseModel) {
        List<Object> list = null;
        try {
            // 转型为MultipartHttpRequest：
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // 获得文件：
            MultipartFile inputFile = multipartRequest.getFile("inputFile");
            // 获得文件名：
            String filename = inputFile.getOriginalFilename();
            // 获得输入流：
            // InputStream input = file.getInputStream();
            String[] baseModels = new String[]{};
            List<String> parseArray = JSONArray.parseArray(baseModel, String.class);
            baseModels = parseArray.toArray(baseModels);
            InputStream inputStream = inputFile.getInputStream();
            if (inputStream == null) {
                throw new Exception("文件为空");
            }
            list = ExcelDocument.upload(inputStream, baseModels, IntegralLogInfoExcel.class, filename);
            // integralLogService.saveAll(list);
        } catch (Exception e) {
            LOGGER.info("loadIntegralLogInfo:" + e.getMessage());
            return null;
        }
        return list;
    }

    /**
     * 积分导出
     *
     * @return
     */
    @ApiOperation("积分导出")
    @GetMapping("exportIntegralLog")
    public void exportIntegralLog(HttpServletResponse response, IntegralLogCondition condition,
                                  List<String> baseModel) {
        try {
            List<Object> byProjectNum = integralLogService.listIntegralLog(condition);
            Workbook wk = ExcelDocument.download((String[]) baseModel.toArray(), IntegralLogInfo.class, byProjectNum);
            downLoadExcel(response, wk, "integralLog");
        } catch (Exception e) {
            LOGGER.info("exportIntegralLog:" + e.getMessage());
            try {
                response.getWriter().println("下载失败！");
            } catch (IOException e1) {
            }
        }
    }

    /**
     * 项目导入
     *
     * @param file
     * @param baseModel
     * @return
     */
    @ApiOperation("项目导入")
    @GetMapping("loadProjectInfo")
    public List<Object> loadProjectInfo(@RequestParam("file") CommonsMultipartFile file, List<String> baseModel) {
        List<Object> list = null;
        try {
            InputStream inputStream = file.getInputStream();
            if (inputStream == null) {
                throw new Exception("文件为空");
            }
            list = ExcelDocument.upload(inputStream, (String[]) baseModel.toArray(), ProjectInfo.class, file.getName());
            projectService.saveAll(list);
        } catch (Exception e) {
            LOGGER.info("loadProjectInfo:" + e.getMessage());
            return null;
        }
        return list;
    }

    /**
     * 项目导出
     *
     * @return
     */
    @ApiOperation("项目导出")
    @GetMapping("exportProject")
    public void exportProject(HttpServletResponse response, ProjectCondition condition, List<String> baseModel) {
        try {
            List<Object> byProjectNum = projectService.listProject(condition);
            Workbook wk = ExcelDocument.download((String[]) baseModel.toArray(), ProjectInfo.class, byProjectNum);
            downLoadExcel(response, wk, "project");
        } catch (Exception e) {
            LOGGER.info("exportProject:" + e.getMessage());
            try {
                response.getWriter().println("下载失败！");
            } catch (IOException e1) {
            }
        }
    }

    /**
     * 用户导入
     *
     * @param file
     * @param baseModel
     * @return
     */
    @ApiOperation("用户导入")
    @GetMapping("loadUserInfo")
    public List<Object> loadUserInfo(@RequestParam("file") CommonsMultipartFile file, List<String> baseModel) {
        List<Object> list = null;
        try {
            InputStream inputStream = file.getInputStream();
            if (inputStream == null) {
                throw new Exception("文件为空");
            }
            list = ExcelDocument.upload(inputStream, (String[]) baseModel.toArray(), UserInfo.class, file.getName());
            userService.saveAll(list);
        } catch (Exception e) {
            LOGGER.info("loadUserInfo:" + e.getMessage());
            return null;
        }
        return list;
    }

    /**
     * 用户导出
     *
     * @return
     */
    @ApiOperation("用户导出")
    @GetMapping("exportUser")
    public void exportUser(HttpServletResponse response, UserCondition condition, List<String> baseModel) {
        try {
            List<Object> byProjectNum = userService.listUser(condition);
            Workbook wk = ExcelDocument.download((String[]) baseModel.toArray(), UserInfo.class, byProjectNum);
            downLoadExcel(response, wk, "user");
        } catch (Exception e) {
            LOGGER.info("exportProject:" + e.getMessage());
            try {
                response.getWriter().println("下载失败！");
            } catch (IOException e1) {
            }
        }
    }

    /*****************************************
     * 导入导出
     *****************************************/

    /**
     * 报名
     *
     * @param projectNum
     * @return
     */
    @GetMapping("/signUp")
    @ApiOperation("报名")
    public String signUp(String projectNum) {
        try {
            IntegralLogInfo integralLogInfo = new IntegralLogInfo();
            IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo();
            integralLogIdInfo.setProjectNum(projectNum);
            SocialUsers socialUsers = getUser();

            //
            integralLogIdInfo.setStudentNum(socialUsers.getUserId());

            integralLogInfo.setIntegralLogId(integralLogIdInfo);
            integralLogInfo.setType(IntegralLogTypeEnum.SIGN_IN);
            ProjectInfo projectInfo = projectService.getProjectByNum(projectNum);
            if (projectInfo == null) {
                return null;
            }
            integralLogInfo.setIntegral(projectInfo.getIntegral());

            integralLogInfo.setStatus(IntegralLogStatusEnum.ALREADY_SIGN_UP);
            // integralLogInfo.setStudent(socialUsers.getUserNum());
            // integralLogInfo.setClazz(socialUsers.getClazz());
            // integralLogInfo.setCollege(socialUsers.getCollege());

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

    private void downLoadExcel(HttpServletResponse response, Workbook wk, String fileName) throws Exception {
        OutputStream outputStream = null;
        try {
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setHeader("Connection", "close");
            response.setHeader("Content-Type", "application/octet-stream");
            outputStream = response.getOutputStream();
            wk.write(outputStream);
            outputStream.flush();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
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

    private SocialUsers getUser() {
        return UserUtils.getUser();
    }

    public String getUrl(HttpServletRequest request, String fileName) throws IOException {
        // 转型为MultipartHttpRequest：
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获得文件：
        MultipartFile inputFile = multipartRequest.getFile(fileName);

        if (inputFile == null) {
            return "NO_FILE";
        }

        // 获得文件名：
        String filename = inputFile.getOriginalFilename();

        String uploadFile = FastDFSClient.uploadFile(inputFile.getBytes(), filename);

        if (uploadFile == null) {
            return null;
        }

        String url = Constans.STRONGE_URL + uploadFile;
        return url;
    }
}
