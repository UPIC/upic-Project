package com.upic.service;

import com.upic.condition.ProjectCategoryCondition;
import com.upic.dto.ProjectCategoryInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface ProjectCategoryService {
    /**
     * 添加项目类别
     *
     * @param projectCategoryInfo
     * @return
     */
    ProjectCategoryInfo addProjectCategory(ProjectCategoryInfo projectCategoryInfo);

    /**
     * 修改项目类别
     *
     * @param projectCategoryInfo
     * @return
     */
    ProjectCategoryInfo updateProjectCategory(ProjectCategoryInfo projectCategoryInfo);

    /**
     * 查找项目类别（条件）
     *
     * @return
     */
    Page<ProjectCategoryInfo> searchProjectCategory(ProjectCategoryCondition projectCategoryCondition, Pageable pageable);

    /**
     * 根据ID查找项目类别
     *
     * @param projectCategoryId
     * @return
     */
    ProjectCategoryInfo getProjectCategoryById(long projectCategoryId);

    /**
     * 根据ID删除项目类别
     *
     * @param projectCategoryId
     * @return
     */
    String deleteProjectCategory(long projectCategoryId);

    /**
     * 获取所有projectCategory
     *
     * @param projectCategoryCondition
     * @return
     */
    List<ProjectCategoryInfo> getAllProjectCategoryList(ProjectCategoryCondition projectCategoryCondition);
}
