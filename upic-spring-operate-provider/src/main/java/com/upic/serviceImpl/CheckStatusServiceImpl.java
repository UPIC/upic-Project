package com.upic.serviceImpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.CheckStatusCondition;
import com.upic.condition.RoleCondition;
import com.upic.dto.CheckStatusInfo;
import com.upic.dto.RoleInfo;
import com.upic.po.CheckStatus;
import com.upic.po.Role;
import com.upic.repository.CheckStatusRepository;
import com.upic.repository.RoleRepository;
import com.upic.repository.Spec.CheckStatusSpec;
import com.upic.repository.Spec.RoleSpec;
import com.upic.service.CheckStatusService;
import com.upic.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by zhubuqing on 2017/9/11.
 */
@Service("checkStatusService")
public class CheckStatusServiceImpl implements CheckStatusService {
    @Autowired
    private CheckStatusRepository checkStatusRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(CheckStatusServiceImpl.class);

    @Override
    public CheckStatusInfo addCheckStatus(CheckStatusInfo checkStatusInfo) {
        try {
            CheckStatus checkStatus = new CheckStatus();
            UpicBeanUtils.copyProperties(checkStatusInfo, checkStatus);
            checkStatus = checkStatusRepository.save(checkStatus);
            UpicBeanUtils.copyProperties(checkStatus, checkStatusInfo);
            return checkStatusInfo;
        } catch (Exception e) {
            LOGGER.info("addCheckStatus。错误信息：" + e.getMessage());
            return null;
        }
    }

    @Override
    public CheckStatusInfo updateCheckStatus(CheckStatusInfo checkStatusInfo) {
        try {
            CheckStatus checkStatus = new CheckStatus();
            UpicBeanUtils.copyProperties(checkStatusInfo, checkStatus);
            checkStatus = checkStatusRepository.saveAndFlush(checkStatus);
            UpicBeanUtils.copyProperties(checkStatus, checkStatusInfo);
            return checkStatusInfo;
        } catch (Exception e) {
            LOGGER.info("updateCheckStatus。错误信息：" + e.getMessage());
            return null;
        }
    }

    @Override
    public Page<CheckStatusInfo> searchCheckStatus(CheckStatusCondition checkStatusCondition, Pageable pageable) {
        Page<CheckStatus> checkStatusPage = null;
        try {
            checkStatusPage = checkStatusRepository.findAll(new CheckStatusSpec(checkStatusCondition), pageable);
            return QueryResultConverter.convert(checkStatusPage, pageable, new AbstractDomain2InfoConverter<CheckStatus, CheckStatusInfo>() {
                @Override
                protected void doConvert(CheckStatus domain, CheckStatusInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("searchCheckStatus。错误信息：" + e.getMessage());
            return null;
        }
    }

    @Override
    public CheckStatusInfo getCheckStatusById(long checkStatusId) {
        try {
            CheckStatus checkStatus = checkStatusRepository.findOne(checkStatusId);
            CheckStatusInfo checkStatusInfo = new CheckStatusInfo();
            UpicBeanUtils.copyProperties(checkStatus, checkStatusInfo);
            return checkStatusInfo;
        } catch (Exception e) {
            LOGGER.info("getCheckStatusById。错误信息：" + e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteCheckStatus(long checkStatusId) {
        try {
            checkStatusRepository.delete(checkStatusId);
        } catch (Exception e) {
            LOGGER.info("deleteCheckStatus。错误信息：" + e.getMessage());
        }
    }
}
