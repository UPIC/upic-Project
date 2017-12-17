package com.upic.controller;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.upic.dto.IntegralLogInfo;
import com.upic.dto.ProjectInfo;
import com.upic.dto.UserInfo;
import com.upic.enums.UserStatusEnum;
import com.upic.enums.UserTypeEnum;
import com.upic.service.IntegralLogService;
import com.upic.service.ProjectService;
//import com.upic.social.user.SocialUsers;
//import com.upic.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherAllController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(TeacherAllController.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private IntegralLogService integralLogService;

    /**
     * 根据项目编号获取项目详情
     *
     * @param projectNum
     * @return
     * @throws Exception
     */
    @GetMapping("/getProjectByProjectNum")
    public ProjectInfo getProjectByProjectNum(String projectNum) {
        try {
            return projectService.getProjectByNum(projectNum);
        } catch (Exception e) {
            LOGGER.info("getProjectByProjectNum:" + e.getMessage());
            return null;
        }
    }

    /**
     * 根据项目编号查找相关用户
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getUserListByProjectNum")
    public Page<IntegralLogInfo> getUserListByProjectNum(@PageableDefault(size = 10) Pageable pageable, String projectNum) throws Exception {
        try {
            Page<IntegralLogInfo> integralLogInfoPage = integralLogService.getUserListByProjectNum(projectNum, pageable);
            System.out.println(integralLogInfoPage.getContent().toString());
            return integralLogInfoPage;
        } catch (Exception e) {
            LOGGER.info("getUserListByProjectNum:" + e.getMessage());
            throw new Exception("getUserListByProjectNum:" + e.getMessage());
        }
    }

    /**
     * 二维码生成
     *
     * @param projectNum
     * @param freshTime
     * @return
     */
    @GetMapping("/qrCodeGenerate")
    public String qrCodeGenerate(String projectNum, long freshTime) {
        try {
            String accessToken = projectService.qrCodeGenerate(projectNum, freshTime);
            return accessToken;
        } catch (Exception e) {
            LOGGER.info("qrCodeGenerate:" + e.getMessage());
        }
        return null;
    }

    private UserInfo getUser() {
        return new UserInfo("1522110240", "章威男", "", "信息工程学院", "计算机科学与技术", "15微社交1班", "13250950317", "1", "zhang_wei_nan@qq.com", "", UserStatusEnum.NORMAL_CONDITION, "山鸡", UserTypeEnum.TEACHER, 0, 0);
    }
}
