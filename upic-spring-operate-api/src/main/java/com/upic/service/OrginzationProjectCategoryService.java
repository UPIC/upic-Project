package com.upic.service;

import com.upic.condition.OrginzationCondition;
import com.upic.condition.OrginzationProjectCategoryCondition;
import com.upic.dto.OrginzationInfo;
import com.upic.dto.OrginzationProjectCategoryInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface OrginzationProjectCategoryService {
    /**
     * 添加角色
     *
     * @return
     */
    OrginzationProjectCategoryInfo addOrginzationProjectCategory(OrginzationProjectCategoryInfo orginzationProjectCategoryInfo);

    /**
     * 更新角色内容
     *
     * @return
     */
    OrginzationProjectCategoryInfo updateOrginzationProjectCategory(OrginzationProjectCategoryInfo orginzationProjectCategoryInfo);

    /**
     * 查询角色（条件）
     *
     * @param pageable
     * @return
     */
    Page<OrginzationProjectCategoryInfo> searchOrginzationProjectCategory(OrginzationProjectCategoryCondition orginzationProjectCategoryCondition, Pageable pageable);

    /**
     * 根据ID查询角色
     *
     * @return
     */
    OrginzationProjectCategoryInfo getOrginzationProjectCategoryById(long orginzationProjectCategoryId);

    /**
     * 删除角色
     */
    void deleteOrginzationProjectCategory(long orginzationProjectCategoryId);
}
