package com.upic.controller;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.upic.dto.ConfirmationBasisInfo;
import com.upic.dto.IntegralLogInfo;
import com.upic.dto.ProjectInfo;
import com.upic.dto.UserInfo;
import com.upic.service.ConfirmationBasisService;
import com.upic.service.IntegralLogService;
import com.upic.service.ProjectService;
import com.upic.service.UserService;
//import com.upic.utils.UserUtils;
import com.upic.social.user.SocialUsers;
import com.upic.utils.UserUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
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

    @ApiOperation("教师获取需要审批的积分申报")
    @GetMapping("/getIntegralLogBySql")
    public Page<IntegralLogInfo> getIntegralLogBySql(Pageable pageable) {
        try {
            SocialUsers s = getUser();
            List<String> statusList = s.getStatusList();
            List<String> projectCategoryList = s.getProjectCategoryList();
            String rank = s.getRank();
            String colloge = s.getCollegeAli();
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
            SocialUsers s = getUser();
            List<String> statusList = s.getStatusList();
            List<String> projectCategoryList = s.getProjectCategoryList();
            String rank = s.getRank();
            String colloge = s.getCollegeAli();
            Page<ProjectInfo> projectInfoPage = projectService.getProjectBySql(statusList, projectCategoryList, pageable, rank, colloge, type);
            return projectInfoPage;
        } catch (Exception e) {
            LOGGER.info("getIntegralLogBySql:" + e.getMessage());
            return null;
        }
    }

    @ApiOperation("获取系统添加项目")
    @GetMapping("getSystemProjectByCategoryNodeId")
    public ConfirmationBasisInfo getSystemProjectByCategoryNodeId(long categoryNodeId) {
        try {
            return confirmationBasisService.getSystemProjectByCategoryNodeId(categoryNodeId);
        } catch (Exception e) {
            LOGGER.info("getSystemProjectByCategoryNodeId:" + e.getMessage());
            return null;
        }
    }

    private SocialUsers getUser() {
        return UserUtils.getUser();
    }
}
