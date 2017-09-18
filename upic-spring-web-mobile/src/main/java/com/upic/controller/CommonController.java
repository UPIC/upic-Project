package com.upic.controller;

import com.upic.condition.MailCondition;
import com.upic.condition.ProjectCategoryCondition;
import com.upic.dto.MailInfo;
import com.upic.dto.ProjectCategoryInfo;
import com.upic.dto.UserInfo;
import com.upic.service.MailService;
import com.upic.service.ProjectCategoryService;
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
}
