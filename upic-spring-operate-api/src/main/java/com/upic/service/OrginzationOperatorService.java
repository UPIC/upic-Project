package com.upic.service;

import com.upic.condition.OrginzationCondition;
import com.upic.condition.OrginzationOperatorCondition;
import com.upic.dto.OrginzationInfo;
import com.upic.dto.OrginzationOperatorInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface OrginzationOperatorService {
    /**
     * 添加角色
     *
     * @return
     */
    OrginzationOperatorInfo addOrginzationOperator(OrginzationOperatorInfo orginationOperatorInfo);

    /**
     * 更新角色内容
     *
     * @return
     */
    OrginzationOperatorInfo updateOrginzationOperator(OrginzationOperatorInfo orginationOperatorInfo);

    /**
     * 查询角色（条件）
     *
     * @param pageable
     * @return
     */
    Page<OrginzationOperatorInfo> searchOrginzation(OrginzationOperatorCondition orginationOperatorCondition, Pageable pageable);

    /**
     * 根据ID查询角色
     *
     * @return
     */
    OrginzationOperatorInfo getOrginzationOperatorById(long orginationOperatorId);

    /**
     * 删除角色
     */
    void deleteOrginzationOperator(long orginationOperatorId);
}
