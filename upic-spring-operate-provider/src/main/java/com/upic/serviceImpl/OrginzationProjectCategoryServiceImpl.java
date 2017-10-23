package com.upic.serviceImpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.OrginzationProjectCategoryCondition;
import com.upic.dto.OrginzationProjectCategoryInfo;
import com.upic.po.OrginzationProjectCategory;
import com.upic.repository.OrginzationProjectCategoryRepository;
import com.upic.repository.Spec.OrginzationProjectCategorySpec;
import com.upic.service.OrginzationProjectCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by zhubuqing on 2017/9/11.
 */
@Service("orginzationProjectCategoryService")
public class OrginzationProjectCategoryServiceImpl implements OrginzationProjectCategoryService {
    @Autowired
    private OrginzationProjectCategoryRepository orginzationProjectCategoryRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(OrginzationProjectCategoryServiceImpl.class);

    @Override
    public OrginzationProjectCategoryInfo addOrginzationProjectCategory(OrginzationProjectCategoryInfo orginzationProjectCategoryInfo) {
        try {
            OrginzationProjectCategory orginzationProjectCategory = new OrginzationProjectCategory();
            UpicBeanUtils.copyProperties(orginzationProjectCategoryInfo, orginzationProjectCategory);
            orginzationProjectCategory = orginzationProjectCategoryRepository.save(orginzationProjectCategory);
            UpicBeanUtils.copyProperties(orginzationProjectCategory, orginzationProjectCategoryInfo);
            return orginzationProjectCategoryInfo;
        } catch (Exception e) {
            LOGGER.info("addOrginzationProjectCategory：" + e.getMessage());
            return null;
        }
    }

    @Override
    public OrginzationProjectCategoryInfo updateOrginzationProjectCategory(OrginzationProjectCategoryInfo orginzationProjectCategoryInfo) {
        try {
            OrginzationProjectCategory orginzationProjectCategory = new OrginzationProjectCategory();
            UpicBeanUtils.copyProperties(orginzationProjectCategoryInfo, orginzationProjectCategory);
            orginzationProjectCategory = orginzationProjectCategoryRepository.saveAndFlush(orginzationProjectCategory);
            UpicBeanUtils.copyProperties(orginzationProjectCategory, orginzationProjectCategoryInfo);
            return orginzationProjectCategoryInfo;
        } catch (Exception e) {
            LOGGER.info("updateOrginzationProjectCategory：" + e.getMessage());
            return null;
        }
    }

    @Override
    public Page<OrginzationProjectCategoryInfo> searchOrginzationProjectCategory(OrginzationProjectCategoryCondition orginzationProjectCategoryCondition, Pageable pageable) {
        Page<OrginzationProjectCategory> orginzationProjectCategoryPage = null;
        try {
            orginzationProjectCategoryPage = orginzationProjectCategoryRepository.findAll(new OrginzationProjectCategorySpec(orginzationProjectCategoryCondition), pageable);
            return QueryResultConverter.convert(orginzationProjectCategoryPage, pageable, new AbstractDomain2InfoConverter<OrginzationProjectCategory, OrginzationProjectCategoryInfo>() {
                @Override
                protected void doConvert(OrginzationProjectCategory domain, OrginzationProjectCategoryInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("searchOrginzationProjectCategory：" + e.getMessage());
            return null;
        }
    }

    @Override
    public OrginzationProjectCategoryInfo getOrginzationProjectCategoryById(long orginzationProjectCategoryId) {
        try {
            OrginzationProjectCategoryInfo orginzationProjectCategoryInfo = new OrginzationProjectCategoryInfo();
            OrginzationProjectCategory orginzationProjectCategory = orginzationProjectCategoryRepository.getOne(orginzationProjectCategoryId);
            UpicBeanUtils.copyProperties(orginzationProjectCategory, orginzationProjectCategoryInfo);
            return orginzationProjectCategoryInfo;
        } catch (Exception e) {
            LOGGER.info("getOrginzationProjectCategoryById：" + e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteOrginzationProjectCategory(long orginzationProjectCategoryId) {
        try {
            orginzationProjectCategoryRepository.delete(orginzationProjectCategoryId);
        } catch (Exception e) {
            LOGGER.info("deleteOrginzationProjectCategory：" + e.getMessage());
        }
    }
}
