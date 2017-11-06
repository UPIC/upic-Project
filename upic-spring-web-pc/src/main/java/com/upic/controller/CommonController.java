package com.upic.controller;

import com.upic.common.document.excel.ExcelDocument;
import com.upic.condition.*;
import com.upic.dto.*;
import com.upic.enums.ImplementationProcessEnum;
import com.upic.enums.IntegralLogStatusEnum;
import com.upic.service.*;
//import com.upic.utils.UserUtils;
import com.upic.social.user.SocialUsers;
import com.upic.utils.UserUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private CategoryNodeService categoryNodeService;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private ClazzService clazzService;

    /**
     * 根据ID获取项目类别
     *
     * @param id
     * @return
     */
    @GetMapping("/getProjectCategoryById")
    @ApiOperation("根据ID获取项目类别")
    public ProjectCategoryInfo getProjectCategoryById(long id) {
        try {
            ProjectCategoryInfo projectCategoryInfo = projectCategoryService.getProjectCategoryById(id);
            System.out.println(projectCategoryInfo);
            return projectCategoryInfo;
        } catch (Exception e) {
            LOGGER.info("getProjectCategoryById:" + e.getMessage());
            return null;
        }
    }

    /**
     * 根据ID获取项目节点
     *
     * @param id
     * @return
     */
    @GetMapping("/getCategoryNodeById")
    @ApiOperation("根据ID获取项目类别")
    public CategoryNodeInfo getCategoryNodeById(long id) {
        try {
            CategoryNodeInfo categoryNodeInfo = categoryNodeService.getCategoryNodeById(id);
            System.out.println(categoryNodeInfo);
            return categoryNodeInfo;
        } catch (Exception e) {
            LOGGER.info("getCategoryNodeById:" + e.getMessage());
            return null;
        }
    }

    /**
     * 添加项目类别
     *
     * @param projectCategoryInfo
     * @return
     */
    @GetMapping("/addProjectCategory")
    @ApiOperation("添加项目类别")
    public ProjectCategoryInfo addProjectCategory(ProjectCategoryInfo projectCategoryInfo) {
        try {
            return projectCategoryService.addProjectCategory(projectCategoryInfo);
        } catch (Exception e) {
            LOGGER.info("addProjectCategory:" + e.getMessage());
            return null;
        }
    }

    /**
     * 获取所有项目类别
     *
     * @param pageable
     * @param p
     * @return
     * @throws Exception
     */
    @GetMapping("/getAllProjectCategory")
    @ApiOperation("获取所有项目类别")
    public Page<ProjectCategoryInfo> getAllProjectCategory(@PageableDefault(size = 20) Pageable pageable, ProjectCategoryCondition p) {
        try {
            return projectCategoryService.searchProjectCategory(p, pageable);
        } catch (Exception e) {
            LOGGER.info("getAllProjectCategory:" + e.getMessage());
            return null;
        }
    }

    @GetMapping("/getAllProjectImplementationProcess")
    @ApiOperation("获取项目状态")
    public List<String> getAllProjectImplementationProcess() {
        try {
            List<String> implementationProcessList = new ArrayList<String>();
            implementationProcessList.add(ImplementationProcessEnum.SAVED.getContent());
            implementationProcessList.add(ImplementationProcessEnum.IN_AUDIT.getContent());
            implementationProcessList.add(ImplementationProcessEnum.IN_AUDIT_AGAIN.getContent());
            implementationProcessList.add(ImplementationProcessEnum.IN_AUDIT_FINAL.getContent());
            implementationProcessList.add(ImplementationProcessEnum.AUDITED.getContent());
            implementationProcessList.add(ImplementationProcessEnum.ENROLLMENT.getContent());
            implementationProcessList.add(ImplementationProcessEnum.HAVE_IN_HAND.getContent());
            implementationProcessList.add(ImplementationProcessEnum.COMPLETED.getContent());
            implementationProcessList.add(ImplementationProcessEnum.CHECKING.getContent());
            implementationProcessList.add(ImplementationProcessEnum.CHECKING_AGAIN.getContent());
            implementationProcessList.add(ImplementationProcessEnum.CHECKING_FINAL.getContent());
            implementationProcessList.add(ImplementationProcessEnum.CHECKED.getContent());
            implementationProcessList.add(ImplementationProcessEnum.NOT_PASS.getContent());
            return implementationProcessList;
        } catch (Exception e) {
            LOGGER.info("getAllProjectImplementationProcess:" + e.getMessage());
            return null;
        }
    }

    /**
     * 获取所有项目类别
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getAllProjectCategoryList")
    @ApiOperation("获取所有项目类别")
    public List<ProjectCategoryInfo> getAllProjectCategoryList(ProjectCategoryCondition projectCategoryCondition) throws Exception {
        try {
            List<ProjectCategoryInfo> projectCategoryInfoList = projectCategoryService.getAllProjectCategoryList(projectCategoryCondition);
            for (ProjectCategoryInfo projectCategoryInfo : projectCategoryInfoList) {
                System.out.println(projectCategoryInfo.toString());
            }
            return projectCategoryInfoList;
        } catch (Exception e) {
            LOGGER.info("getAllProjectCategory:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 更新项目类别
     *
     * @return
     */
    @GetMapping("/updateProjectCategory")
    @ApiOperation("更新项目类别")
    public ProjectCategoryInfo updateProjectCategory(long id, String categoryName) {
        try {
            ProjectCategoryInfo projectCategoryInfo = projectCategoryService.getProjectCategoryById(id);
            projectCategoryInfo.setCategoryName(categoryName);
            projectCategoryInfo = projectCategoryService.updateProjectCategory(projectCategoryInfo);
            return projectCategoryInfo;
        } catch (Exception e) {
            LOGGER.info("updateProjectCategory:" + e.getMessage());
            return null;
        }
    }

    @GetMapping("/addCategoryNode")
    @ApiOperation("添加项目节点")
    public CategoryNodeInfo searchCategoryNode(CategoryNodeInfo categoryNodeInfo) {
        try {
            return categoryNodeService.addCategoryNode(categoryNodeInfo);
        } catch (Exception e) {
            LOGGER.info("searchCategoryNode:" + e.getMessage());
            return null;
        }
    }

    /**
     * 获取项目节点
     *
     * @param pageable
     * @param categoryNodeCondition
     * @return
     */
    @GetMapping("/searchCategoryNode")
    @ApiOperation("获取项目节点")
    public Page<CategoryNodeInfo> searchCategoryNode(@PageableDefault(size = 20) Pageable pageable, CategoryNodeCondition categoryNodeCondition) {
        try {
            return categoryNodeService.searchCategoryNode(categoryNodeCondition, pageable);
        } catch (Exception e) {
            LOGGER.info("searchCategoryNode:" + e.getMessage());
            return null;
        }
    }

    /**
     * 获取项目节点
     *
     * @param categoryNodeCondition
     * @return
     */
    @GetMapping("/searchCategoryNodeList")
    @ApiOperation("获取项目节点")
    public List<CategoryNodeInfo> searchCategoryNodeList(CategoryNodeCondition categoryNodeCondition) {
        try {
            return categoryNodeService.searchCategoryNodeList(categoryNodeCondition);
        } catch (Exception e) {
            LOGGER.info("searchCategoryNodeList:" + e.getMessage());
            return null;
        }
    }

    /**
     * 更新项目节点
     *
     * @return
     */
    @GetMapping("/updateCategoryNode")
    @ApiOperation("更新项目节点")
    public CategoryNodeInfo updateCategoryNode(long id, String nodeContent) {
        try {
            CategoryNodeInfo categoryNodeInfo = categoryNodeService.getCategoryNodeById(id);
            categoryNodeInfo.setNodeContent(nodeContent);
            categoryNodeInfo = categoryNodeService.updateCategoryNode(categoryNodeInfo);
            return categoryNodeInfo;
        } catch (Exception e) {
            LOGGER.info("updateCategoryNode:" + e.getMessage());
            return null;
        }
    }

//    @GetMapping("/deleteProjectCategory") // 待定
//    @ApiOperation("删除项目类别")
//    public String deleteProjectCategory(long projectCategoryId) {
//        try {
//            projectCategoryService.deleteProjectCategory(projectCategoryId);
//            List<CategoryNodeInfo> categoryNodeInfoList = categoryNodeService.getCategoryNodeByFatherId(projectCategoryId);
//            if (categoryNodeInfoList != null) {
//
//            }
//            return "SUCCESS";
//        } catch (Exception e) {
//            LOGGER.info("updateCategoryNode:" + e.getMessage());
//            return "ERROR";
//        }
//    }

    /**
     * 根据条件查询活动*
     *
     * @throws Exception
     */
    @GetMapping("/getProject")
    @ApiOperation("根据条件查询活动")
    public Page<ProjectInfo> getProject(@PageableDefault(size = 10) Pageable pageable, ProjectCondition p)
            throws Exception {
        try {
            System.out.println("!@#$!@#$!@#$!@#$!@#$!@#$!@#$!@#$!@#$!@#$!@#$!@#$!@#$!@#$!@#$!@#$!@#$!@#$!@#$!@#$" + p.getGuidanceNum());
            Page<ProjectInfo> projectInfoPage = projectService.searchProject(p, pageable);
            System.out.println(projectInfoPage.getContent().toString());
            return projectInfoPage;
        } catch (Exception e) {
            LOGGER.info("getProject:" + e.getMessage());
            return null;
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
    @ApiOperation("根据条件查询奖品")
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
    @ApiOperation("根据条件获取站内信")
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
    @ApiOperation("根据ID获取相应的站内信")
    public MailInfo getMailInfo(@ApiParam("站内信ID") Long id) throws Exception {
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
    @ApiOperation("根据项目编号查询项目人数")
    public int getSignUpNumberByProjectNum(@ApiParam("项目编号") String projectNum) throws Exception {
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
    @ApiOperation("根据项目编号查询活动详情")
    public ProjectInfo getProjectInfo(@ApiParam("项目编号") String projectNum) throws Exception {
        try {
            ProjectInfo projectInfo = projectService.getProjectByNum(projectNum);
            System.out.println(projectInfo);
            return projectInfo;
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
    @ApiOperation("获取积分明细")
    public Page<IntegralLogInfo> getIntegralLogPage(@PageableDefault(size = 10) Pageable pageable,
                                                    IntegralLogCondition c) throws Exception {
        try {
            Page<IntegralLogInfo> integralLogInfoPage = integralLogService.searchIntegralLog(c, pageable);
            System.out.println("******************************************************************************");
            System.out.println(integralLogInfoPage.getContent().toString());
            System.out.println("******************************************************************************");
            return integralLogInfoPage;
        } catch (Exception e) {
            LOGGER.info("getIntegralLogPage:" + e.getMessage());
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
    @ApiOperation("获取素拓币明细")
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
    @ApiOperation("用户查看站内信（可能没用）")
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
    @ApiOperation("根据用户获取项目列表")
    public Page<ProjectInfo> getProjectByUser(@PageableDefault(size = 10) Pageable pageable, ProjectCondition projectCondition) {
        try {
            return projectService.searchProject(projectCondition, pageable);
        } catch (Exception e) {
            LOGGER.info("getProjectByUser:" + e.getMessage());
            return null;
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
    @ApiOperation("项目申报")
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
    @ApiOperation("获取单个奖品")
    public PrizeInfo getPrizeById(@ApiParam("奖品ID") long prizeId) throws Exception {
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
    @ApiOperation("获取历史奖品")
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
    @ApiOperation("查询我未报名、并且在报名期间内的活动（学生移动端全部活动查询）")
    public Page<ProjectInfo> getProjectWithoutSignUp(@PageableDefault(size = 10) Pageable pageable) throws Exception {
        try {
            Page<ProjectInfo> projectWithoutSignUp = projectService.getProjectWithoutSignUp(new Date(), pageable);
            return projectWithoutSignUp;
        } catch (Exception exception) {
            LOGGER.info("getProjectWithoutSignUp:" + exception.getMessage());
            throw new Exception("getProjectWithoutSignUp" + exception.getMessage());
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
    @ApiOperation("根据项目编号查找已报名的活动")
    public IntegralLogInfo getIntegralLogInfoByMySelf(@ApiParam("项目编号") String projectNum) throws Exception {
        try {
            IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo();
            integralLogIdInfo.setProjectNum(projectNum);
            integralLogIdInfo.setStudentNum(getUser().getUserId());
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
    @ApiOperation("积分搜索条")
    public Page<IntegralLogInfo> integralLogSearchBar(@PageableDefault(size = 10) Pageable pageable, @ApiParam("积分状态") IntegralLogStatusEnum status, @ApiParam("关键词") String keyword) throws Exception {
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
     * @param status
     * @return
     */
    @GetMapping("/updateIntegralLogStatus")
    @ApiOperation("修改积分状态")
    public String updateIntegralLogStatus(@ApiParam("学生编号）") List<String> studentNumList, @ApiParam("项目编号") List<String> projectNumList, @ApiParam("积分状态") IntegralLogStatusEnum status) throws Exception {
        try {
            List<IntegralLogIdInfo> integralLogIdInfoList = new ArrayList<IntegralLogIdInfo>();
            for (int i = 0; i < studentNumList.size(); i++) {
                integralLogIdInfoList.get(i).setProjectNum(projectNumList.get(i));
                integralLogIdInfoList.get(i).setStudentNum(studentNumList.get(i));
            }
            return integralLogService.updateIntegralLogStatus(integralLogIdInfoList, status);
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
    @ApiOperation("我的项目搜索条")
    public Page<ProjectInfo> myProjectSearchBar(@PageableDefault(size = 10) Pageable pageable, @ApiParam("关键词") String keyword) throws Exception {
        try {
            return projectService.projectSearchBar(getUser().getUserId(), keyword, pageable);
        } catch (Exception e) {
            LOGGER.info("myProjectSearchBar:" + e.getMessage());
            throw new Exception("myProjectSearchBar" + e.getMessage());
        }
    }

    /**
     * 项目导出
     *
     * @return
     */
    @ApiOperation("项目导出")
    @GetMapping("exportProjectSearchBar")
    public void exportProjectSearchBar(HttpServletResponse response, String keyword, List<String> baseModel) {
        try {
            List<Object> byProjectNum = projectService.exportProjectSearchBar(getUser().getUserId(), keyword);
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
     * 更新项目
     *
     * @param projectInfo
     * @return
     * @throws Exception
     */
    @GetMapping("/updateProject")
    @ApiOperation("更新项目")
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
    @ApiOperation("项目搜索条")
    public Page<ProjectInfo> projectSearchBar(@PageableDefault(size = 10) Pageable pageable, @ApiParam("关键词") String keyword) throws Exception {
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
    @ApiOperation("获取所有用户")
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
    @ApiOperation("教师查询目前汇总工作量")
    public Double getTeacherNowWorkloadSummary(@ApiParam("教师编号") String teacherNum) throws Exception {
        try {
            List<String> projectNumList = projectService.getByGuidanceNum(teacherNum); // 获取到的是项目编号的List
            List<IntegralLogInfo> integralLogInfoAllList = new ArrayList<IntegralLogInfo>();
            projectNumList.stream().parallel().forEach((i) -> {
                List<IntegralLogInfo> integralLogInfoList = integralLogService.getByProjectNum(i); // 获取积分列表
                integralLogInfoAllList.addAll(integralLogInfoList);
            });
            //用流实现
            return integralLogInfoAllList.stream().parallel().reduce(IntegralLogInfo::doSum).get().getIntegral();
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
    @ApiOperation("教师查询汇总工作量")
    public double getTeacherAllWorkloadSummary(@ApiParam("教师编号") String teacherNum) {
        try {
            return projectService.getTeacherAllWorkloadSummary(teacherNum);
        } catch (Exception e) {
            LOGGER.info("getTeacherAllWorkloadSummary:" + e.getMessage());
//            throw new Exception("getTeacherAllWorkloadSummary" + e.getMessage());
            return 0;
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
    @ApiOperation("更新奖品")
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
    @ApiOperation("获取所有奖品交易记录")
    public Page<GrainCoinLogInfo> getGrainCoinLog(@PageableDefault(size = 10) Pageable pageable, GrainCoinLogCondition grainCoinLogCondition) throws Exception {
        try {
            return grainCoinLogService.searchPrizeByCondition(grainCoinLogCondition, pageable);
        } catch (Exception e) {
            LOGGER.info("getGrainCoinLog:" + e.getMessage());
            throw new Exception("getGrainCoinLog" + e.getMessage());
        }
    }

    /**
     * 积分导出
     *
     * @return
     */
    @ApiOperation("积分导出")
    @GetMapping("exportGrainCoinLog")
    public void exportGrainCoinLog(HttpServletResponse response, GrainCoinLogCondition condition, List<String> baseModel) {
        try {
            List<Object> byProjectNum = grainCoinLogService.exportGrainCoinLog(condition);
            Workbook wk = ExcelDocument.download((String[]) baseModel.toArray(), IntegralLogInfo.class, byProjectNum);
            downLoadExcel(response, wk, "integralLog");
        } catch (Exception e) {
            LOGGER.info("exportGrainCoinLog:" + e.getMessage());
            try {
                response.getWriter().println("下载失败！");
            } catch (IOException e1) {
            }
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
    @ApiOperation("更新用户")
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
    @ApiOperation("添加用户")
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
    @ApiOperation("添加奖品")
    public PrizeInfo addPrize(PrizeInfo prizeInfo) throws Exception {
        try {
            return prizeService.addPrize(prizeInfo);
        } catch (Exception e) {
            LOGGER.info("addPrize:" + e.getMessage());
            throw new Exception("addPrize" + e.getMessage());
        }
    }

    /**
     * 根据积分ID获取积分详情
     *
     * @param projectNum
     * @return
     * @throws Exception
     */
    @GetMapping("/getIntegralLogByIntegralLogId")
    @ApiOperation("根据积分ID获取积分详情")
    public IntegralLogInfo getIntegralLogByIntegralLogId(String projectNum) throws Exception {
        try {
            IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo();
            integralLogIdInfo.setStudentNum(getUser().getUserId());
            integralLogIdInfo.setProjectNum(projectNum);
            return integralLogService.getByIntegralLogId(integralLogIdInfo);
        } catch (Exception e) {
            LOGGER.info("getIntegralLogByIntegralLogId:" + e.getMessage());
            throw new Exception("getIntegralLogByIntegralLogId" + e.getMessage());
        }
    }

    /**
     * 根据单个积分状态获取积分
     *
     * @param pageable
     * @param status
     * @return
     * @throws Exception
     */
    @GetMapping("/getIntegralLogWithOutPass")
    @ApiOperation("根据单个积分状态获取积分")
    public Page<IntegralLogInfo> getIntegralLogWithOutPass(@PageableDefault(size = 10) Pageable pageable, IntegralLogStatusEnum status) throws Exception {
        try {
            return integralLogService.getIntegralLogWithOutPass(status, pageable);
        } catch (Exception e) {
            LOGGER.info("getIntegralLogWithOutPass:" + e.getMessage());
            throw new Exception("getIntegralLogWithOutPass" + e.getMessage());
        }
    }

    /**
     * 根据项目编号与用户编号获取积分
     *
     * @return
     */
    @GetMapping("/getIntegralLogByProjectNumStudentNum")
    @ApiOperation("获取所有积分")
    public IntegralLogInfo getIntegralLogByProjectNumStudentNum(String projectNum, String studentNum) {
        try {
            IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo();
            integralLogIdInfo.setStudentNum(studentNum);
            integralLogIdInfo.setProjectNum(projectNum);
            IntegralLogInfo integralLogInfo = integralLogService.getByIntegralLogId(integralLogIdInfo);
            return integralLogInfo;
        } catch (Exception e) {
            LOGGER.info("getIntegralLogWithOutPass:" + e.getMessage());
            return null;
        }
    }

    /**
     * 获取所有积分
     *
     * @param integralLogCondition
     * @param pageable
     * @return
     */
    @GetMapping("/getAllIntegralLog")
    @ApiOperation("获取所有积分")
    public Page<IntegralLogInfo> getAllIntegralLog(IntegralLogCondition integralLogCondition, @PageableDefault(size = 10) Pageable pageable) {
        try {
            Page<IntegralLogInfo> integralLogInfoPage = integralLogService.searchIntegralLog(integralLogCondition, pageable);
            System.out.println("!#@$!@#$!@#$!@#$!@#$!@#$!@#$!@#$!@#$!@#$!@#$" + integralLogInfoPage.getContent().size());
            return integralLogInfoPage;
        } catch (Exception e) {
            LOGGER.info("getIntegralLogWithOutPass:" + e.getMessage());
            return null;
        }
    }

    /**
     * 获取用户的积分
     *
     * @return
     * @throws Exception
     */
    @ApiOperation("获取用户的积分")
    @GetMapping("/getIntegeral")
    public double getIntegeral(String studentNum) throws Exception {
        try {
            return integralLogService.watchIntegral(studentNum);
        } catch (Exception e) {
            LOGGER.info("getIntegeral :" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 获取学生素拓币
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getGrainCoin")
    public double getGrainCoin(String studentNum) throws Exception {
        try {
            return grainCoinLogService.watchGrainCoin(studentNum);
        } catch (Exception e) {
            LOGGER.info("getGrainCoin:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 用户搜索条
     *
     * @param pageable
     * @param keyword
     * @return
     * @throws Exception
     */
    @GetMapping("/userSearchBar")
    public Page<UserInfo> userSearchBar(@PageableDefault(size = 10) Pageable pageable, String keyword) throws Exception {
        try {
            return userService.userSearchBar(keyword, pageable);
        } catch (Exception e) {
            LOGGER.info("userSearchBar:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /*******************************************20171018*******************************************/

    /**
     * 查找我的项目
     *
     * @param pageable
     * @return
     */
    @GetMapping("/getProjectByGuidanceNum")
    public Page<ProjectInfo> getProjectByGuidanceNum(@PageableDefault(size = 10) Pageable pageable) {
        try {
            Page<ProjectInfo> projectInfoPage = projectService.getProjectByGuidanceNum(getUser().getUserId(), pageable);
            System.out.println(projectInfoPage.toString());
            return projectInfoPage;
        } catch (Exception e) {
            LOGGER.info("getProjectByGuidanceNum:" + e.getMessage());
            return null;
        }
    }

    /**
     * 我的项目导出
     *
     * @return
     */
    @ApiOperation("我的项目导出")
    @GetMapping("exportProjectByGuidanceNum")
    public void exportProjectByGuidanceNum(HttpServletResponse response, String guidanceNum, List<String> baseModel) {
        try {
            List<Object> byProjectNum = projectService.exportProjectByGuidanceNum(guidanceNum);
            Workbook wk = ExcelDocument.download((String[]) baseModel.toArray(), ProjectInfo.class, byProjectNum);
            downLoadExcel(response, wk, "project");
        } catch (Exception e) {
            LOGGER.info("exportProjectByGuidanceNum:" + e.getMessage());
            try {
                response.getWriter().println("下载失败！");
            } catch (IOException e1) {
            }
        }
    }

    /**
     * 查询project
     *
     * @param projectCondition
     * @param pageable
     * @return
     */
    @GetMapping("/searchProject")
    @ApiOperation("查找项目")
    public Page<ProjectInfo> searchProject(ProjectCondition projectCondition, @PageableDefault(size = 10) Pageable pageable) {
        try {
            Page<ProjectInfo> projectInfoPage = projectService.searchProject(projectCondition, pageable);
            return projectInfoPage;
        } catch (Exception e) {
            LOGGER.info("searchProject:" + e.getMessage());
            return null;
        }
    }

    /**
     * 获取学院
     *
     * @param collegeCondition
     * @param pageable
     * @return
     */
    @GetMapping("/getCollege")
    @ApiOperation("获取学院")
    public Page<CollegeInfo> getCollege(CollegeCondition collegeCondition, @PageableDefault(size = 20) Pageable pageable) {
        try {
            Page<CollegeInfo> collegeInfoPage = collegeService.searchCollege(collegeCondition, pageable);
            return collegeInfoPage;
        } catch (Exception e) {
            LOGGER.info("getCollege:" + e.getMessage());
            return null;
        }
    }

    @GetMapping("/getClazz")
    @ApiOperation("获取班级")
    public Page<ClazzInfo> getClazz(ClazzCondition clazzCondition, @PageableDefault(size = 10) Pageable pageable) {
        try {
            Page<ClazzInfo> clazzInfoPage = clazzService.searchClazz(clazzCondition, pageable);
            return clazzInfoPage;
        } catch (Exception e) {
            LOGGER.info("getClazz:" + e.getMessage());
            return null;
        }
    }

    /**
     * 获取项目状态列表
     *
     * @return
     */
    @GetMapping("/getProjectStatus")
    @ApiOperation("获取项目状态列表")
    public List<String> getProjectStatus() {
        try {
            Map map = ImplementationProcessEnum.toMap();
            List<String> projectStatusList = new ArrayList<>();
            for (int i = 0; i < map.size(); i++) {
                projectStatusList.add((String) map.get(i));
            }
            return getProjectStatus();
        } catch (Exception e) {
            LOGGER.info("getProjectStatus:" + e.getMessage());
            return null;
        }
    }

    @GetMapping("/getUserByUserNum")
    @ApiOperation("根据用户编号查询用户")
    public UserInfo getUserByUserNum(String userNum) {
        try {
            UserInfo userInfo = userService.getUserByUserNum(userNum);
            return userInfo;
        } catch (Exception e) {
            LOGGER.info("getUserByUserNum:" + e.getMessage());
            return null;
        }
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/getUserInfo")
    public UserInfo getUserInfo() {
        UserInfo userInfo = userService.getUserByUserNum(getUser().getUserId());
        return userInfo;
    }

    private SocialUsers getUser() {
        return UserUtils.getUser();
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
}
