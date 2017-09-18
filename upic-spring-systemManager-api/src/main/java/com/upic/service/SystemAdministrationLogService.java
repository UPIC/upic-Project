package com.upic.service;

import com.upic.condition.SystemAdministrationLogCondition;
import com.upic.dto.SystemAdministrationLogInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zhubuqing on 2017/9/11.
 */
public interface SystemAdministrationLogService {
    /**
     * 添加日志
     *
     * @param systemAdministrationLogInfo
     * @return
     */
    SystemAdministrationLogInfo addSystemAdministrationLog(SystemAdministrationLogInfo systemAdministrationLogInfo);

    /**
     * 更新日志
     *
     * @param systemAdministrationLogInfo
     * @return
     */
    SystemAdministrationLogInfo updateSystemAdministrationLog(SystemAdministrationLogInfo systemAdministrationLogInfo);

    /**
     * 查询日志（条件）
     *
     * @param systemAdministrationLogCondition
     * @param pageable
     * @return
     */
    Page<SystemAdministrationLogInfo> searchSystemAdministrationLog(SystemAdministrationLogCondition systemAdministrationLogCondition, Pageable pageable);

    /**
     * 获取单个日志
     *
     * @param systemAdministrationLogId
     * @return
     */
    SystemAdministrationLogInfo getBySystemAdministrationLogId(long systemAdministrationLogId);
}
