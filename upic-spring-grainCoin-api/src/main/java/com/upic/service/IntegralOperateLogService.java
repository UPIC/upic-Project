package com.upic.service;

import com.upic.condition.IntegralOperateLogCondition;
import com.upic.dto.IntegralOperateLogInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zhubuqing on 2017/9/11.
 */
public interface IntegralOperateLogService {
    /**
     * 添加积分操作日志
     *
     * @param integralOperateLogInfo
     * @return
     */
    IntegralOperateLogInfo addIntegralOperateLog(IntegralOperateLogInfo integralOperateLogInfo);

    /**
     * 查询积分操作日志列表（条件）
     *
     * @param integralOperateLogCondition
     * @param pageable
     * @return
     */
    Page<IntegralOperateLogInfo> searchIntegralOperateLog(IntegralOperateLogCondition integralOperateLogCondition, Pageable pageable);

    /**
     * 查询单个积分操作日志
     *
     * @param integralOperateLogId
     * @return
     */
    IntegralOperateLogInfo getByIntegralOperateLogId(long integralOperateLogId);
}
