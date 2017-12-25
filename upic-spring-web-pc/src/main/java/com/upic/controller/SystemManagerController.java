package com.upic.controller;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.fastjson.JSONArray;
import com.upic.common.beans.utils.ChineseCharToEn;
import com.upic.condition.UserCondition;
import com.upic.dto.*;
import com.upic.dto.excel.IntegralLogInfoExcel;
import com.upic.enums.IntegralLogStatusEnum;
import com.upic.enums.UserStatusEnum;
import com.upic.enums.UserTypeEnum;
import com.upic.service.*;
//import com.upic.social.user.SocialUsers;
//import com.upic.utils.UserUtils;
import com.upic.social.user.SocialUsers;
import com.upic.utils.UserUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/systemManager")
public class SystemManagerController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(SystemManagerController.class);

    @Autowired
    private IntegralLogService integralLogService;

    @Autowired
    private ConfirmationBasisService confirmationBasisService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdviceService adviceService;

    @ApiOperation("教师获取需要审批的积分申报")
    @GetMapping("/getIntegralLogBySql")
    public Page<IntegralLogInfo> getIntegralLogBySql(Pageable pageable, String type) {
        try {
            SocialUsers s = getUser();
            List<String> statusList = s.getStatusList();
            List<String> projectCategoryList = new ArrayList<>();
            String rank = s.getRank();
            String colloge = s.getCollegeAli();

            if (type != null && type.equals("C") && rank.equals("3")) {
                projectCategoryList.add("PENDING_AUDIT_BEFORE");
            } else {
                projectCategoryList = s.getProjectCategoryList();
                for (String projectCategory : projectCategoryList) {
                    if (projectCategory.equals("PENDING_AUDIT_BEFORE")) {
                        projectCategoryList.remove(projectCategory);
                    }
                }
            }
            return integralLogService.getIntegralLogBySql(statusList, projectCategoryList, rank, colloge, pageable);
        } catch (Exception e) {
            LOGGER.info("getIntegralLogBySql:" + e.getMessage());
            return null;
        }
    }

    @ApiOperation("教师获取需要审批的项目申报")
    @GetMapping("/getProjectBySql")
    public Page<ProjectInfo> getProjectBySql(String type, Pageable pageable) {
        try {
//            UserInfo s = getUser();
            SocialUsers s = getUser();
            List<String> statusList = s.getStatusList();
            List<String> projectCategoryList = s.getProjectCategoryList();
            String rank = s.getRank();
            String colloge = s.getCollegeAli();

            /*************************************************************************/

//            List<String> statusList = new ArrayList<String>();
//            statusList.add("PENDING_AUDIT_AGAIN");
//            statusList.add("IN_AUDIT_AGAIN");
//            statusList.add("CHECKING_AGAIN");
//
//            List<String> projectCategoryList = new ArrayList<String>();
//            projectCategoryList.add("生活能力");
//            projectCategoryList.add("思想品德");
//
//            String rank = "2";
//            String colloge = s.getCollege();

            /*************************************************************************/

            Page<ProjectInfo> projectInfoPage = projectService.getProjectBySql(statusList, projectCategoryList, pageable, rank, colloge, type);
            return projectInfoPage;
        } catch (Exception e) {
            LOGGER.info("getIntegralLogBySql:" + e.getMessage());
            return null;
        }
    }

    @ApiOperation("获取系统添加项目")
    @GetMapping("/getSystemProjectByCategoryNodeId")
    public ConfirmationBasisInfo getSystemProjectByCategoryNodeId(long categoryNodeId) {
        try {
            return confirmationBasisService.getSystemProjectByCategoryNodeId(categoryNodeId);
        } catch (Exception e) {
            LOGGER.info("getSystemProjectByCategoryNodeId:" + e.getMessage());
            return null;
        }
    }

    /**
     * 根据上传人给予状态
     *
     * @param string
     * @return
     */
    @PostMapping("/changeIntegralLogInfoExcel")
    public String changeIntegralLogInfoExcel(String string, String type) {
        try {
            List<IntegralLogInfoExcel> integralLogInfoExcelList = JSONArray.parseArray(string, IntegralLogInfoExcel.class);
            List<IntegralLogInfo> integralLogInfoList = new ArrayList<IntegralLogInfo>();
            ChineseCharToEn cte = new ChineseCharToEn();
            for (IntegralLogInfoExcel integralLogInfoExcel : integralLogInfoExcelList) {
                IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo();
                integralLogIdInfo.setStudentNum(integralLogInfoExcel.getStudentNum());

                if (type.equals("radioselect1")) {
                    integralLogIdInfo.setProjectNum("VOLUNTARY_APPLICATION" + integralLogInfoExcel.getProjectNum());
                } else if (type.equals("radioselect2")) {
                    integralLogIdInfo.setProjectNum("VOLUNTARY_APPLICATION" + cte.getAllFirstLetter(integralLogInfoExcel.getProjectName()).toUpperCase());
                } else {
                    integralLogIdInfo.setProjectNum(integralLogInfoExcel.getProjectNum());
                }

                IntegralLogInfo integralLogInfo = new IntegralLogInfo();
                integralLogInfo.setIntegralLogId(integralLogIdInfo);
                integralLogInfo.setEvent(integralLogInfoExcel.getEvent());
                integralLogInfo.setIntegral(integralLogInfoExcel.getIntegral());
                integralLogInfo.setType(integralLogInfoExcel.getType());
//                if (getUser().getField1().equals("1")) {
                if (getUser().getRank().equals("1")) {
                    integralLogInfo.setStatus(IntegralLogStatusEnum.PENDING_AUDIT_FINAL);//这个什么意思
//                } else if (getUser().getField1().equals("2")) {
                } else if (getUser().getRank().equals("2")) {
                    integralLogInfo.setStatus(IntegralLogStatusEnum.PENDING_AUDIT_AGAIN);
                } else {
                    integralLogInfo.setStatus(IntegralLogStatusEnum.PENDING_AUDIT_BEFORE);
                }
                integralLogInfo.setStudent(integralLogInfoExcel.getStudent());
                integralLogInfo.setClazz(integralLogInfoExcel.getClazz());
                integralLogInfo.setCollege(integralLogInfoExcel.getCollege());
                integralLogInfo.setIntegralLogPic(integralLogInfoExcel.getIntegralLogPic());
                integralLogInfo.setVersion(integralLogInfoExcel.getVersion());
                integralLogInfo.setCreatTime(integralLogInfoExcel.getCreatTime());
                integralLogInfo.setField1(integralLogInfoExcel.getField1());
                integralLogInfo.setField2(integralLogInfoExcel.getField2());
                integralLogInfo.setField3(integralLogInfoExcel.getField3());
                integralLogInfo.setField4(integralLogInfoExcel.getField4());
                integralLogInfo.setField5(integralLogInfoExcel.getField5());
                integralLogInfo.setAddTime(integralLogInfoExcel.getAddTime());
                integralLogInfo.setProjectName(integralLogInfoExcel.getProjectName());
                integralLogInfo.setProjectCategory(integralLogInfoExcel.getProjectCategory());
                integralLogInfo.setCollegeOtherName(cte.getAllFirstLetter(integralLogInfoExcel.getCollege()).toUpperCase());
                integralLogInfoList.add(integralLogInfo);
            }
            integralLogService.addAll(integralLogInfoList);
            return "SUCCESS";
        } catch (Exception e) {
            LOGGER.info("changeIntegralLogInfoExcel:" + e.getMessage());
            return null;
        }
    }

    @GetMapping("/getAdviceByProjectNum")
    public AdviceInfo getAdviceByProjectNum(long projectId) {
        try {
            return adviceService.getAdviceByProjectId(projectId);
        } catch (Exception e) {
            LOGGER.info("getAdviceByProjectNum:" + e.getMessage());
            return null;
        }
    }

    @GetMapping("/getAllUser")
    public Page<UserInfo> getAllUser(UserCondition userCondition, Pageable pageable) {
        try {
            return userService.searchUser(userCondition, pageable);
        } catch (Exception e) {
            LOGGER.info("getAllUser:" + e.getMessage());
            return null;
        }
    }

    private SocialUsers getUser() {
        return UserUtils.getUser();
    }
}
