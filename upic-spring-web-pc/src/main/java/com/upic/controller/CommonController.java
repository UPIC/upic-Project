package com.upic.controller;

import com.upic.condition.*;
import com.upic.dto.*;
import com.upic.enums.IntegralLogStatusEnum;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

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
    private UserService userService;

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
            Page<PrizeInfo> prizeInfoPage = prizeService.getHistoryPrize(pageable);
            return prizeInfoPage;
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
            integralLogIdInfo.setStudentNum(getUser().getUserNum());
            return integralLogService.getByIntegralLogId(integralLogIdInfo);
        } catch (Exception e) {
            LOGGER.info("getProjectWithoutSignUp:" + e.getMessage());
            throw new Exception("getProjectWithoutSignUp" + e.getMessage());
        }
    }

    /******************************************20171009******************************************/

    /**
     * 积分搜索条
     *
     * @param pageable
     * @param status
     * @param keyword
     * @return
     * @throws Exception
     */
    @GetMapping("/integralLogSearchBar")
    public Page<IntegralLogInfo> integralLogSearchBar(@PageableDefault(size = 10) Pageable pageable, IntegralLogStatusEnum status, String keyword) throws Exception {
        try {
            return integralLogService.integralLogSearchBar(status, keyword, pageable);
        } catch (Exception e) {
            LOGGER.info("integralLogSearchBar:" + e.getMessage());
            throw new Exception("integralLogSearchBar" + e.getMessage());
        }
    }

    /**
     * 修改积分状态
     *
     * @param integralLogIdInfos
     * @param status
     * @return
     */
    @GetMapping("/updateIntegralLogStatus")
    public String updateIntegralLogStatus(List<IntegralLogIdInfo> integralLogIdInfos, IntegralLogStatusEnum status) throws Exception {
        try {
            return integralLogService.updateIntegralLogStatus(integralLogIdInfos, status);
        } catch (Exception e) {
            LOGGER.info("updateIntegralLogStatus:" + e.getMessage());
            throw new Exception("updateIntegralLogStatus" + e.getMessage());
        }
    }

    /**
     * 我的项目搜索条
     *
     * @param pageable
     * @param keyword
     * @return
     * @throws Exception
     */
    @GetMapping("/myProjectSearchBar")
    public Page<ProjectInfo> myProjectSearchBar(@PageableDefault(size = 10) Pageable pageable, String keyword) throws Exception {
        try {
            return projectService.projectSearchBar(getUser().getUserNum(), keyword, pageable);
        } catch (Exception e) {
            LOGGER.info("myProjectSearchBar:" + e.getMessage());
            throw new Exception("myProjectSearchBar" + e.getMessage());
        }
    }

    /**
     * 更新项目
     *
     * @param projectInfo
     * @return
     * @throws Exception
     */
    @GetMapping("/updateProject")
    public ProjectInfo updateProject(ProjectInfo projectInfo) throws Exception {
        try {
            return projectService.updateProject(projectInfo);
        } catch (Exception e) {
            LOGGER.info("updateProject:" + e.getMessage());
            throw new Exception("updateProject" + e.getMessage());
        }
    }

    /**
     * 项目搜索条
     *
     * @param pageable
     * @param keyword
     * @return
     * @throws Exception
     */
    @GetMapping("/projectSearchBar")
    public Page<ProjectInfo> projectSearchBar(@PageableDefault(size = 10) Pageable pageable, String keyword) throws Exception {
        try {
            return projectService.projectSearchBar(keyword, pageable);
        } catch (Exception e) {
            LOGGER.info("projectSearchBar:" + e.getMessage());
            throw new Exception("projectSearchBar" + e.getMessage());
        }
    }

    /**
     * 获取所有用户
     *
     * @param pageable
     * @param userCondition
     * @return
     * @throws Exception
     */
    @GetMapping("/getAllUser")
    public Page<UserInfo> getAllUser(@PageableDefault(size = 10) Pageable pageable, UserCondition userCondition) throws Exception {
        try {
            return userService.searchUser(userCondition, pageable);
        } catch (Exception e) {
            LOGGER.info("getAllUser:" + e.getMessage());
            throw new Exception("getAllUser" + e.getMessage());
        }
    }

    /**
     * 教师查询目前汇总工作量
     *
     * @param teacherNum
     * @return
     */
    @GetMapping("/getTeacherNowWorkloadSummary")
    public Double getTeacherNowWorkloadSummary(String teacherNum) throws Exception {
        try {
            List<String> projectNumList = projectService.getByGuidanceNum(teacherNum); // 获取到的是项目编号的List
            List<IntegralLogInfo> integralLogInfoAllList = new ArrayList<IntegralLogInfo>();
            projectNumList.stream().parallel().forEach((i)->{
            	List<IntegralLogInfo> integralLogInfoList = integralLogService.getByProjectNum(i); // 获取积分列表
                integralLogInfoAllList.addAll(integralLogInfoList);
            });
            //用流实现
            return  integralLogInfoAllList.stream().parallel().reduce(IntegralLogInfo::doSum).get().getIntegral();
        } catch (Exception e) {
            LOGGER.info("getTeacherNowWorkloadSummary:" + e.getMessage());
//            throw new Exception("getTeacherNowWorkloadSummary" + e.getMessage());
            return null;
        }
    }

    /**
     * 教师查询汇总工作量
     *
     * @param teacherNum
     * @return
     */
    @GetMapping("/getTeacherAllWorkloadSummary")
    public double getTeacherAllWorkloadSummary(String teacherNum) throws Exception {
        try {
            return projectService.getTeacherAllWorkloadSummary(teacherNum);
        } catch (Exception e) {
            LOGGER.info("getTeacherAllWorkloadSummary:" + e.getMessage());
            throw new Exception("getTeacherAllWorkloadSummary" + e.getMessage());
        }
    }

    /**
     * 更新奖品
     *
     * @param prizeInfo
     * @return
     * @throws Exception
     */
    @GetMapping("/updatePrize")
    public PrizeInfo updatePrize(PrizeInfo prizeInfo) throws Exception {
        try {
            return prizeService.updatePrize(prizeInfo);
        } catch (Exception e) {
            LOGGER.info("updatePrize:" + e.getMessage());
            throw new Exception("updatePrize" + e.getMessage());
        }
    }

    /**
     * 获取所有奖品交易记录
     *
     * @param pageable
     * @param grainCoinLogCondition
     * @return
     * @throws Exception
     */
    @GetMapping("/getGrainCoinLog")
    public Page<GrainCoinLogInfo> getGrainCoinLog(@PageableDefault(size = 10) Pageable pageable, GrainCoinLogCondition grainCoinLogCondition) throws Exception {
        try {
            return grainCoinLogService.searchPrizeByCondition(grainCoinLogCondition, pageable);
        } catch (Exception e) {
            LOGGER.info("getGrainCoinLog:" + e.getMessage());
            throw new Exception("getGrainCoinLog" + e.getMessage());
        }
    }

    /**
     * 更新用户
     *
     * @param userInfo
     * @return
     * @throws Exception
     */
    @GetMapping("/updateUser")
    public UserInfo updateUser(UserInfo userInfo) throws Exception {
        try {
            return userService.updateUser(userInfo);
        } catch (Exception e) {
            LOGGER.info("updateUser:" + e.getMessage());
            throw new Exception("updateUser" + e.getMessage());
        }
    }

    /**
     * 添加用户
     *
     * @param userInfo
     * @return
     * @throws Exception
     */
    @GetMapping("/addUser")
    public UserInfo addUser(UserInfo userInfo) throws Exception {
        try {
            return userService.addUser(userInfo);
        } catch (Exception e) {
            LOGGER.info("addUser:" + e.getMessage());
            throw new Exception("addUser" + e.getMessage());
        }
    }

    /**
     * 添加奖品
     *
     * @param prizeInfo
     * @return
     * @throws Exception
     */
    @GetMapping("/addPrize")
    public PrizeInfo addPrize(PrizeInfo prizeInfo) throws Exception {
        try {
            return prizeService.addPrize(prizeInfo);
        } catch (Exception e) {
            LOGGER.info("addPrize:" + e.getMessage());
            throw new Exception("addPrize" + e.getMessage());
        }
    }

    private UserInfo getUser() {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //SocialUser so=(SocialUser) authentication.getPrincipal();
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("山鸡");
        userInfo.setPic("assets/i/shanji.jpg");
        return userInfo;
    }
}
