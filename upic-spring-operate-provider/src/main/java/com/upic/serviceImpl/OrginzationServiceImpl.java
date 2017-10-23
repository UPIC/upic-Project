package com.upic.serviceImpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.OrginzationCondition;
import com.upic.condition.RoleCondition;
import com.upic.dto.OrginzationInfo;
import com.upic.dto.RoleInfo;
import com.upic.po.Orginzation;
import com.upic.po.Role;
import com.upic.repository.OrginzationRepository;
import com.upic.repository.RoleRepository;
import com.upic.repository.Spec.OrginzationSpec;
import com.upic.repository.Spec.RoleSpec;
import com.upic.service.OrginzationService;
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
@Service("orginzationService")
public class OrginzationServiceImpl implements OrginzationService {
    @Autowired
    private OrginzationRepository orginzationRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(OrginzationServiceImpl.class);

    @Override
    public OrginzationInfo addOrginzation(OrginzationInfo orginationInfo) {
        try {
            Orginzation orginzation = new Orginzation();
            UpicBeanUtils.copyProperties(orginationInfo, orginzation);
            orginzation = orginzationRepository.save(orginzation);
            UpicBeanUtils.copyProperties(orginzation, orginationInfo);
            return orginationInfo;
        } catch (Exception e) {
            LOGGER.info("addOrgination：" + e.getMessage());
            return null;
        }
    }

    @Override
    public OrginzationInfo updateOrginzation(OrginzationInfo orginationInfo) {
        try {
            Orginzation orginzation = new Orginzation();
            UpicBeanUtils.copyProperties(orginationInfo, orginzation);
            orginzation = orginzationRepository.saveAndFlush(orginzation);
            UpicBeanUtils.copyProperties(orginzation, orginationInfo);
            return orginationInfo;
        } catch (Exception e) {
            LOGGER.info("updateOrgination：" + e.getMessage());
            return null;
        }
    }

    public Page<OrginzationInfo> searchOrginzation(OrginzationCondition orginzationCondition, Pageable pageable) {
        Page<Orginzation> orginzationPage = null;
        try {
            orginzationPage = orginzationRepository.findAll(new OrginzationSpec(orginzationCondition), pageable);
            return QueryResultConverter.convert(orginzationPage, pageable, new AbstractDomain2InfoConverter<Orginzation, OrginzationInfo>() {
                protected void doConvert(Orginzation domain, OrginzationInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("searchOrginzation：" + e.getMessage());
            return null;
        }
    }

    @Override
    public OrginzationInfo getOrginzationById(long orginationId) {
        try {
            OrginzationInfo orginzationInfo = new OrginzationInfo();
            Orginzation orginzation = orginzationRepository.getOne(orginationId);
            UpicBeanUtils.copyProperties(orginzation, orginzationInfo);
            return orginzationInfo;
        } catch (Exception e) {
            LOGGER.info("getOrginzationById：" + e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteOrginzation(long orginationId) {
        try {
            orginzationRepository.delete(orginationId);
        } catch (Exception e) {
            LOGGER.info("deleteOrginzation：" + e.getMessage());
        }
    }
}
