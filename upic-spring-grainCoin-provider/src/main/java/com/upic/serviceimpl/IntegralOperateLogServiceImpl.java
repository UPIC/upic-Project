package com.upic.serviceimpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.IntegralOperateLogCondition;
import com.upic.dto.IntegralOperateLogInfo;
import com.upic.po.IntegralOperateLog;
import com.upic.repository.IntegralOperateLogRepository;
import com.upic.repository.Spec.IntegralOperateLogSpec;
import com.upic.service.IntegralOperateLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by zhubuqing on 2017/9/11.
 */
@Service("integralOperateLogService")
public class IntegralOperateLogServiceImpl implements IntegralOperateLogService {
    @Autowired
    private IntegralOperateLogRepository integralOperateLogRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(IntegralOperateLogServiceImpl.class);

    public IntegralOperateLogInfo addIntegralOperateLog(IntegralOperateLogInfo integralOperateLogInfo) {
        try {
            IntegralOperateLog integralOperateLog = new IntegralOperateLog();
            UpicBeanUtils.copyProperties(integralOperateLogInfo, integralOperateLog);
            integralOperateLog = integralOperateLogRepository.save(integralOperateLog);
            UpicBeanUtils.copyProperties(integralOperateLog, integralOperateLogInfo);
            return integralOperateLogInfo;
        } catch (Exception e) {
            LOGGER.info("addIntegralOperateLog:积分操作记录" + integralOperateLogInfo.toString() + "添加失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<IntegralOperateLogInfo> searchIntegralOperateLog(IntegralOperateLogCondition integralOperateLogCondition, Pageable pageable) {
        Page<IntegralOperateLog> integralOperateLogPage = null;
        try {
            integralOperateLogPage = integralOperateLogRepository.findAll(new IntegralOperateLogSpec(integralOperateLogCondition), pageable);
            return QueryResultConverter.convert(integralOperateLogPage, pageable, new AbstractDomain2InfoConverter<IntegralOperateLog, IntegralOperateLogInfo>() {
                protected void doConvert(IntegralOperateLog domain, IntegralOperateLogInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("searchIntegralOperateLog:积分操作记录列表查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public IntegralOperateLogInfo getByIntegralOperateLogId(long integralOperateLogId) {
        try {
            IntegralOperateLog integralOperateLog = integralOperateLogRepository.findOne(integralOperateLogId);
            IntegralOperateLogInfo integralOperateLogInfo = new IntegralOperateLogInfo();
            UpicBeanUtils.copyProperties(integralOperateLog, integralOperateLogInfo);
            return integralOperateLogInfo;
        } catch (Exception e) {
            LOGGER.info("getByIntegralOperateLogId:单个积分操作记录查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }
}
