package com.upic.service;

import com.upic.condition.OperatorCondition;
import com.upic.dto.OperatorInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface OperatorService {
    /**
     * 添加管理员
     *
     * @param operatorInfo
     * @return
     */
    OperatorInfo addOperator(OperatorInfo operatorInfo);

    /**
     * 更新管理员
     *
     * @param operatorInfo
     * @return
     */
    OperatorInfo updateOperator(OperatorInfo operatorInfo);

    /**
     * 查询管理员（条件）
     *
     * @param operatorCondition
     * @param pageable
     * @return
     */
    Page<OperatorInfo> searchOperator(OperatorCondition operatorCondition, Pageable pageable);

    /**
     * 根据ID查询管理员详情
     *
     * @param operatorId
     * @return
     */
    OperatorInfo getOperatorByOperatorId(long operatorId);

    /**
     * 根据ID删除管理员
     *
     * @param operatorId
     */
    void deleteByOperatorId(long operatorId);
}
