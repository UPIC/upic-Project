package com.upic.service;

import com.upic.condition.CategoryNodeCondition;
import com.upic.dto.CategoryNodeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface CategoryNodeService {
    /**
     * 添加节点
     *
     * @param categoryNodeInfo
     * @return
     */
    CategoryNodeInfo addCategoryNode(CategoryNodeInfo categoryNodeInfo);

    /**
     * 修改节点
     *
     * @param categoryNodeInfo
     * @return
     */
    CategoryNodeInfo updateCategoryNode(CategoryNodeInfo categoryNodeInfo);

    /**
     * 查询节点（条件）
     *
     * @param categoryNodeCondition
     * @return
     */
    Page<CategoryNodeInfo> searchCategoryNode(CategoryNodeCondition categoryNodeCondition, Pageable pageable);

    /**
     * 根据ID，获得单个节点
     *
     * @param categoryNodeId
     * @return
     */
    CategoryNodeInfo getCategoryNodeById(long categoryNodeId);

    /**
     * 根据fatherId查询项目节点列表
     *
     * @param fatherId
     * @return
     */
    List<CategoryNodeInfo> getCategoryNodeByFatherId(long fatherId);

    /**
     * 获取项目节点
     *
     * @param categoryNodeCondition
     * @return
     */
    List<CategoryNodeInfo> searchCategoryNodeList(CategoryNodeCondition categoryNodeCondition);

    /**
     * 删除项目节点
     *
     * @param id
     * @return
     */
    String deleteCategoryNode(long id);
}
