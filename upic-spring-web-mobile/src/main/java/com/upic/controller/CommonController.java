package com.upic.controller;

import com.upic.condition.*;
import com.upic.dto.*;
import com.upic.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by zhubuqing on 2017/9/18.
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private MailService mailService;

    @Autowired
    private ProjectCategoryService projectCategoryService;

    @Autowired
    private IntegralLogService integralLogService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PrizeService prizeService;

    @Autowired
    private GrainCoinLogService grainCoinLogService;

    @Autowired
    private CategoryNodeService categoryNodeService;

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/getUserInfo")
    public UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("山鸡");
        userInfo.setPic("assets/i/shanji.jpg");
        try { // 1.获取认证信息 2.根据用户信息查询
        } catch (Exception e) {
        }
        return userInfo;
    }

    /*********************************************项目类别*********************************************/

    /**
     * 获取所有项目类别*
     */
    @GetMapping("/getAllProjectCategory")
    public Page<ProjectCategoryInfo> getAllProjectCategory(@PageableDefault(size = 20) Pageable pageable, ProjectCategoryCondition p) throws Exception {
        try {
            return projectCategoryService.searchProjectCategory(p, pageable);
        } catch (Exception e) {
            LOGGER.info("getAllProjectCategory:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/getCategoryNode")
    public Page<CategoryNodeInfo> getCategoryNode(@PageableDefault(size = 20) Pageable pageable, CategoryNodeCondition categoryNodeCondition) throws Exception {
        try {
            return categoryNodeService.searchCategoryNode(categoryNodeCondition, pageable);
        } catch (Exception e) {
            LOGGER.info("getCategoryNode:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /*********************************************项目类别*********************************************/

    /**
     * 根据条件查询活动*
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
     * 获取奖品*
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
     * 获取相应的站内信*
     *
     * @param pageable
     * @param m
     * @return
     * @throws Exception
     */
    @GetMapping("/getMail")
    public Page<MailInfo> getMail(@PageableDefault(size = 10) Pageable pageable, MailCondition m) throws Exception {
        try {
            return mailService.searchMail(m, pageable);
        } catch (Exception e) {
            LOGGER.info("getMail:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 根据id获取相应的站内信*
     *
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/getMailInfo")
    public MailInfo getMailInfo(Long id) throws Exception {
        try {
            return mailService.getMailById(id);
        } catch (Exception e) {
            LOGGER.info("getMailInfo:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 根据项目编号查询项目人数*
     *
     * @param projectNum
     * @return
     * @throws Exception
     */
    @GetMapping("/getSignUpNumberByProjectNum")
    public int getSignUpNumberByProjectNum(String projectNum) throws Exception {
        try {
            int a = integralLogService.getSignUpNumberByProjectNum(projectNum);
            System.out.println(a + "*****************************************************************************************************************************");
            return a;
        } catch (Exception e) {
            LOGGER.info("getSignUpNumberByProjectNum:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 根据活动编号查看活动详情*
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
     * 获取积分明细*
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
            Page<IntegralLogInfo> integralLogInfoPage = integralLogService.searchIntegralLog(c, pageable);
            System.out.println("******************************************************************************");
            System.out.println(integralLogInfoPage.getContent().toString());
            System.out.println("******************************************************************************");
            return integralLogInfoPage;
        } catch (Exception e) {
            LOGGER.info("getProjectInfo:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 获取素拓币明细*
     *
     * @param pageable
     * @param grainCoinLogCondition
     * @return
     * @throws Exception
     */
    @GetMapping("/getGraincoinLogPage")
    public Page<GrainCoinLogInfo> getGraincoinLogPage(@PageableDefault(size = 10) Pageable pageable, GrainCoinLogCondition grainCoinLogCondition) throws Exception {
        try {
            return grainCoinLogService.searchPrizeByCondition(grainCoinLogCondition, pageable);
        } catch (Exception e) {
            LOGGER.info("getGraincoinLogPage:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 用户查看站内信（可能没用）
     *
     * @param pageable
     * @param userInfo
     * @return
     * @throws Exception
     */
    @GetMapping("/getMyMail")
    public Page<MailInfo> getMyMail(@PageableDefault(size = 10) Pageable pageable, UserInfo userInfo) throws Exception {
        try {
            return mailService.getMyMail(userInfo.getCollege(), userInfo.getMajor(), userInfo.getClazz(), userInfo.getUserNum(), pageable);
        } catch (Exception e) {
            LOGGER.info("getMyMail:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /****************************************************************************************************/

    /**
     * 根据用户获取项目列表
     *
     * @param pageable
     * @param projectCondition
     * @return
     * @throws Exception
     */
    @GetMapping("/getProjectByUser")
    public Page<ProjectInfo> getProjectByUser(@PageableDefault(size = 10) Pageable pageable, ProjectCondition projectCondition) throws Exception {
        try {
            return projectService.searchProject(projectCondition, pageable);
        } catch (Exception e) {
            LOGGER.info("getProjectByUser:" + e.getMessage());
            throw new Exception("getProjectByUser" + e.getMessage());
        }
    }

    /**
     * 项目申报
     *
     * @param projectInfo
     * @return
     * @throws Exception
     */
    @GetMapping("/addProject")
    public ProjectInfo addProject(ProjectInfo projectInfo) throws Exception {
        try {
            return projectService.addProject(projectInfo);
        } catch (Exception e) {
            LOGGER.info("addProject:" + e.getMessage());
            throw new Exception("addProject" + e.getMessage());
        }
    }

    /**
     * 获取单个奖品
     *
     * @param prizeId
     * @return
     * @throws Exception
     */
    @GetMapping("/getPrizeById")
    public PrizeInfo getPrizeById(long prizeId) throws Exception {
        try {
            return prizeService.getPrizeById(prizeId);
        } catch (Exception e) {
            LOGGER.info("getPrizeById:" + e.getMessage());
            throw new Exception("getPrizeById" + e.getMessage());
        }
    }

    /**
     * 获取历史奖品
     *
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/getHistoryPrize")
    public Page<PrizeInfo> getHistoryPrize(@PageableDefault(size = 10) Pageable pageable) throws Exception {
        try {
            return prizeService.getHistoryPrize(pageable);
        } catch (Exception e) {
            LOGGER.info("getHistoryPrize:" + e.getMessage());
            throw new Exception("getHistoryPrize" + e.getMessage());
        }
    }

    /****************************************************************************************************/

    /**
     * 查询我未报名、并且在报名期间内的活动（学生移动端全部活动查询）
     *
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/getProjectWithoutSignUp")
    public Page<ProjectInfo> getProjectWithoutSignUp(@PageableDefault(size = 10) Pageable pageable) throws Exception {
        try {
            Page<ProjectInfo> projectInfoPage = projectService.getProjectWithoutSignUp(new Date(), pageable);
            return projectInfoPage;
        } catch (Exception e) {
            LOGGER.info("getProjectWithoutSignUp:" + e.getMessage());
            throw new Exception("getProjectWithoutSignUp" + e.getMessage());
        }
    }

    /**
     * 根据项目编号查找已报名的活动*
     *
     * @param projectNum
     * @return
     * @throws Exception
     */
    @GetMapping("/getIntegralLogInfoByMySelf")
    public IntegralLogInfo getIntegralLogInfoByMySelf(String projectNum) throws Exception {
        try {
            IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo();
            integralLogIdInfo.setProjectNum(projectNum);
            integralLogIdInfo.setStudentNum("1522110240");
            return integralLogService.getByIntegralLogId(integralLogIdInfo);
        } catch (Exception e) {
            LOGGER.info("getProjectWithoutSignUp:" + e.getMessage());
            throw new Exception("getProjectWithoutSignUp" + e.getMessage());
        }
    }
}
