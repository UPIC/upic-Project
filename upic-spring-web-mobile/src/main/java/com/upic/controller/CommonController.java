package com.upic.controller;

import com.upic.condition.MailCondition;
import com.upic.condition.ProjectCategoryCondition;
import com.upic.condition.ProjectCondition;
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

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/getUserInfo")
    public UserInfo getUserInfo() {
        try { // 1.获取认证信息 2.根据用户信息查询

        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 根据id获取相应的站内信
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
     * 获取所有项目类别
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
     * 获取相应的站内信
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
     * 用户查看站内信
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

    @GetMapping("/getHistoryPrize")
    public Page<PrizeInfo> getHistoryPrize(@PageableDefault(size = 10) Pageable pageable) throws Exception {
        try {
            return prizeService.getHistoryPrize(pageable);
        } catch (Exception e) {
            LOGGER.info("getHistoryPrize:" + e.getMessage());
            throw new Exception("getHistoryPrize" + e.getMessage());
        }
    }
}
