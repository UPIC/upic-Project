package com.upic.serviceimpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.MailLogCondition;
import com.upic.dto.MailLogInfo;
import com.upic.po.MailLog;
import com.upic.repository.MailLogRepository;
import com.upic.repository.Spec.MailLogSpec;
import com.upic.service.MailLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by zhubuqing on 2017/9/12.
 */
@Service("mailLogService")
public class MailLogServiceImpl implements MailLogService {
    @Autowired
    private MailLogRepository mailLogRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(MailLogServiceImpl.class);

    public MailLogInfo addMailLog(MailLogInfo mailLogInfo) {
        try {
            MailLog mailLog = new MailLog();
            UpicBeanUtils.copyProperties(mailLogInfo, mailLog);
            mailLog = mailLogRepository.save(mailLog);
            UpicBeanUtils.copyProperties(mailLog, mailLogInfo);
            return mailLogInfo;
        } catch (Exception e) {
            LOGGER.info("addMailLog:添加信件日志" + mailLogInfo.toString() + "失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public MailLogInfo updateMailLog(MailLogInfo mailLogInfo) {
        try {
            MailLog mailLog = new MailLog();
            UpicBeanUtils.copyProperties(mailLogInfo, mailLog);
            mailLog = mailLogRepository.saveAndFlush(mailLog);
            UpicBeanUtils.copyProperties(mailLog, mailLogInfo);
            return mailLogInfo;
        } catch (Exception e) {
            LOGGER.info("updateMailLog:更新信件日志" + mailLogInfo.toString() + "失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<MailLogInfo> searchMailLog(MailLogCondition mailLogCondition, Pageable pageable) {
        Page<MailLog> mailLogPage = null;
        try {
            mailLogPage = mailLogRepository.findAll(new MailLogSpec(mailLogCondition), pageable);
            return QueryResultConverter.convert(mailLogPage, pageable, new AbstractDomain2InfoConverter<MailLog, MailLogInfo>() {
                protected void doConvert(MailLog domain, MailLogInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("searchMailLog:查询信件日志列表失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public MailLogInfo getByMailLogId(long mailLogId) {
        try {
            MailLog mailLog = mailLogRepository.findOne(mailLogId);
            MailLogInfo mailLogInfo = new MailLogInfo();
            UpicBeanUtils.copyProperties(mailLog, mailLogInfo);
            return mailLogInfo;
        } catch (Exception e) {
            LOGGER.info("getByMailLogId:查询信件日志失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public void deleteMailLog(long mailLogId) {
        try {
            mailLogRepository.delete(mailLogId);
        } catch (Exception e) {
            LOGGER.info("deleteMailLog:删除信件日志失败。错误信息：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
