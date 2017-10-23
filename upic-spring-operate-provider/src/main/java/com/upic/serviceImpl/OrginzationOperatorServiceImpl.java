package com.upic.serviceImpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.OrginzationCondition;
import com.upic.condition.OrginzationOperatorCondition;
import com.upic.dto.OrginzationInfo;
import com.upic.dto.OrginzationOperatorInfo;
import com.upic.po.Orginzation;
import com.upic.po.OrginzationOperator;
import com.upic.repository.OrginzationOperatorRepository;
import com.upic.repository.OrginzationRepository;
import com.upic.repository.Spec.OrginzationOperatorSpec;
import com.upic.repository.Spec.OrginzationSpec;
import com.upic.service.OrginzationOperatorService;
import com.upic.service.OrginzationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by zhubuqing on 2017/9/11.
 */
@Service("orginzationOperatorService")
public class OrginzationOperatorServiceImpl implements OrginzationOperatorService {
    @Autowired
    private OrginzationOperatorRepository orginzationOperatorRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(OrginzationOperatorServiceImpl.class);

    @Override
    public OrginzationOperatorInfo addOrginzationOperator(OrginzationOperatorInfo orginationOperatorInfo) {
        try {
            OrginzationOperator orginzationOperator = new OrginzationOperator();
            UpicBeanUtils.copyProperties(orginationOperatorInfo, orginzationOperator);
            orginzationOperator = orginzationOperatorRepository.save(orginzationOperator);
            UpicBeanUtils.copyProperties(orginzationOperator, orginationOperatorInfo);
            return orginationOperatorInfo;
        } catch (Exception e) {
            LOGGER.info("addOrginzationOperator：" + e.getMessage());
            return null;
        }
    }

    @Override
    public OrginzationOperatorInfo updateOrginzationOperator(OrginzationOperatorInfo orginationOperatorInfo) {
        try {
            OrginzationOperator orginzationOperator = new OrginzationOperator();
            UpicBeanUtils.copyProperties(orginationOperatorInfo, orginzationOperator);
            orginzationOperator = orginzationOperatorRepository.saveAndFlush(orginzationOperator);
            UpicBeanUtils.copyProperties(orginzationOperator, orginationOperatorInfo);
            return orginationOperatorInfo;
        } catch (Exception e) {
            LOGGER.info("addOrginzationOperator：" + e.getMessage());
            return null;
        }
    }

    @Override
    public Page<OrginzationOperatorInfo> searchOrginzation(OrginzationOperatorCondition orginationOperatorCondition, Pageable pageable) {
        Page<OrginzationOperator> orginzationOperatorPage = null;
        try {
            orginzationOperatorPage = orginzationOperatorRepository.findAll(new OrginzationOperatorSpec(orginationOperatorCondition), pageable);
            return QueryResultConverter.convert(orginzationOperatorPage, pageable, new AbstractDomain2InfoConverter<OrginzationOperator, OrginzationOperatorInfo>() {
                @Override
                protected void doConvert(OrginzationOperator domain, OrginzationOperatorInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("searchOrginzation：" + e.getMessage());
            return null;
        }
    }

    @Override
    public OrginzationOperatorInfo getOrginzationOperatorById(long orginationOperatorId) {
        try {
            OrginzationOperatorInfo o = new OrginzationOperatorInfo();
            OrginzationOperator orginzationOperator = orginzationOperatorRepository.getOne(orginationOperatorId);
            UpicBeanUtils.copyProperties(orginzationOperator, o);
            return o;
        } catch (Exception e) {
            LOGGER.info("getOrginzationOperatorById：" + e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteOrginzationOperator(long orginationOperatorId) {
        try {
            orginzationOperatorRepository.delete(orginationOperatorId);
        } catch (Exception e) {
            LOGGER.info("deleteOrginzationOperator：" + e.getMessage());
        }
    }
}
