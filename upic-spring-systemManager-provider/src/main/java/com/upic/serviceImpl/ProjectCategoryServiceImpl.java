package com.upic.serviceImpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.ProjectCategoryCondition;
import com.upic.dto.ProjectCategoryInfo;
import com.upic.po.ProjectCategory;
import com.upic.repository.ProjectCategoryRepository;
import com.upic.repository.Spec.ProjectCategorySpec;
import com.upic.service.ProjectCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by zhubuqing on 2017/9/11.
 */
@Service("projectCategoryService")
public class ProjectCategoryServiceImpl implements ProjectCategoryService {
    @Autowired
    private ProjectCategoryRepository projectCategoryRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(ProjectCategoryServiceImpl.class);

    public ProjectCategoryInfo addProjectCategory(ProjectCategoryInfo projectCategoryInfo) {
        try {
            ProjectCategory projectCategory = new ProjectCategory();
            UpicBeanUtils.copyProperties(projectCategoryInfo, projectCategory);
            projectCategory = projectCategoryRepository.save(projectCategory);
            UpicBeanUtils.copyProperties(projectCategory, projectCategoryInfo);
            return projectCategoryInfo;
        } catch (Exception e) {
            LOGGER.info("addProjectCategory:项目类别" + projectCategoryInfo.toString() + "添加失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public ProjectCategoryInfo updateProjectCategory(ProjectCategoryInfo projectCategoryInfo) {
        try {
            ProjectCategory projectCategory = new ProjectCategory();
            UpicBeanUtils.copyProperties(projectCategoryInfo, projectCategory);
            projectCategory = projectCategoryRepository.saveAndFlush(projectCategory);
            UpicBeanUtils.copyProperties(projectCategory, projectCategoryInfo);
            return projectCategoryInfo;
        } catch (Exception e) {
            LOGGER.info("updateProjectCategory:项目类别" + projectCategoryInfo.toString() + "更新失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<ProjectCategoryInfo> searchProjectCategory(ProjectCategoryCondition projectCategoryCondition, Pageable pageable) {
        Page<ProjectCategory> projectCategoryPage = null;
        try {
            projectCategoryPage = projectCategoryRepository.findAll(new ProjectCategorySpec(projectCategoryCondition), pageable);
            return QueryResultConverter.convert(projectCategoryPage, pageable, new AbstractDomain2InfoConverter<ProjectCategory, ProjectCategoryInfo>() {
                protected void doConvert(ProjectCategory domain, ProjectCategoryInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("searchProjectCategory:项目类别列表查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public ProjectCategoryInfo getProjectCategoryById(long projectCategoryId) {
        try {
            ProjectCategory projectCategory = projectCategoryRepository.findOne(projectCategoryId);
            ProjectCategoryInfo projectCategoryInfo = new ProjectCategoryInfo();
            UpicBeanUtils.copyProperties(projectCategory, projectCategoryInfo);
            return projectCategoryInfo;
        } catch (Exception e) {
            LOGGER.info("getProjectCategoryById:项目类别查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }
}
