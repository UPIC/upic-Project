package com.upic.service;

import com.upic.condition.OrginzationCondition;
import com.upic.dto.OrginzationInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface OrginzationService {
    /**
     * 添加角色
     *
     * @return
     */
    OrginzationInfo addOrginzation(OrginzationInfo orginationInfo);

    /**
     * 更新角色内容
     *
     * @return
     */
    OrginzationInfo updateOrginzation(OrginzationInfo orginationInfo);

    /**
     * 查询角色（条件）
     *
     * @param pageable
     * @return
     */
    Page<OrginzationInfo> searchOrginzation(OrginzationCondition orginationCondition, Pageable pageable);

    /**
     * 根据ID查询角色
     *
     * @return
     */
    OrginzationInfo getOrginzationById(long orginationId);

    /**
     * 删除角色
     */
    void deleteOrginzation(long orginationId);
}
