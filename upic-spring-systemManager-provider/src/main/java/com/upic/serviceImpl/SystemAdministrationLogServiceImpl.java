package com.upic.serviceImpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.SystemAdministrationLogCondition;
import com.upic.dto.SystemAdministrationLogInfo;
import com.upic.po.SystemAdministrationLog;
import com.upic.repository.Spec.SystemAdministrationLogSpec;
import com.upic.repository.SystemAdministrationLogRepository;
import com.upic.service.SystemAdministrationLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by zhubuqing on 2017/9/11.
 */
@Service("systemAdministrationLogService")
public class SystemAdministrationLogServiceImpl implements SystemAdministrationLogService {
    @Autowired
    private SystemAdministrationLogRepository systemAdministrationLogRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(SystemAdministrationLogServiceImpl.class);

    public SystemAdministrationLogInfo addSystemAdministrationLog(SystemAdministrationLogInfo systemAdministrationLogInfo) {
        try {
            SystemAdministrationLog systemAdministrationLog = new SystemAdministrationLog();
            UpicBeanUtils.copyProperties(systemAdministrationLogInfo, systemAdministrationLog);
            systemAdministrationLog = systemAdministrationLogRepository.save(systemAdministrationLog);
            UpicBeanUtils.copyProperties(systemAdministrationLog, systemAdministrationLogInfo);
            return systemAdministrationLogInfo;
        } catch (Exception e) {
            LOGGER.info("addSystemAdministrationLog:日志" + systemAdministrationLogInfo.toString() + "添加失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public SystemAdministrationLogInfo updateSystemAdministrationLog(SystemAdministrationLogInfo systemAdministrationLogInfo) {
        try {
            SystemAdministrationLog systemAdministrationLog = new SystemAdministrationLog();
            UpicBeanUtils.copyProperties(systemAdministrationLogInfo, systemAdministrationLog);
            systemAdministrationLog = systemAdministrationLogRepository.saveAndFlush(systemAdministrationLog);
            UpicBeanUtils.copyProperties(systemAdministrationLog, systemAdministrationLogInfo);
            return systemAdministrationLogInfo;
        } catch (Exception e) {
            LOGGER.info("updateSystemAdministrationLog:日志" + systemAdministrationLogInfo.toString() + "更新失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<SystemAdministrationLogInfo> searchSystemAdministrationLog(SystemAdministrationLogCondition systemAdministrationLogCondition, Pageable pageable) {
        Page<SystemAdministrationLog> systemAdministrationLogPage = null;
        try {
            systemAdministrationLogPage = systemAdministrationLogRepository.findAll(new SystemAdministrationLogSpec(systemAdministrationLogCondition), pageable);
            return QueryResultConverter.convert(systemAdministrationLogPage, pageable, new AbstractDomain2InfoConverter<SystemAdministrationLog, SystemAdministrationLogInfo>() {
                protected void doConvert(SystemAdministrationLog domain, SystemAdministrationLogInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("searchSystemAdministrationLog:日志列表查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public SystemAdministrationLogInfo getBySystemAdministrationLogId(long systemAdministrationLogId) {
        try {
            SystemAdministrationLog systemAdministrationLog = systemAdministrationLogRepository.findOne(systemAdministrationLogId);
            SystemAdministrationLogInfo systemAdministrationLogInfo = new SystemAdministrationLogInfo();
            UpicBeanUtils.copyProperties(systemAdministrationLog, systemAdministrationLogInfo);
            return systemAdministrationLogInfo;
        } catch (Exception e) {
            LOGGER.info("getBySystemAdministrationLogId:日志查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }
}
