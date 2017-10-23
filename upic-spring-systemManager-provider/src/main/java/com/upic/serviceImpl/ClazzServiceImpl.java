package com.upic.serviceImpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.ClazzCondition;
import com.upic.condition.CollegeCondition;
import com.upic.dto.ClazzInfo;
import com.upic.dto.CollegeInfo;
import com.upic.po.Clazz;
import com.upic.po.College;
import com.upic.repository.ClazzRepository;
import com.upic.repository.CollegeRepository;
import com.upic.repository.Spec.ClazzSpec;
import com.upic.repository.Spec.CollegeSpec;
import com.upic.service.ClazzService;
import com.upic.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.upic.serviceImpl.BannerServiceImpl.LOGGER;

/**
 * Created by zhubuqing on 2017/9/10.
 */
@Service("clazzService")
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzRepository clazzRepository;

    @Override
    public ClazzInfo addClazz(ClazzInfo clazzInfo) {
        try {
            Clazz clazz = new Clazz();
            UpicBeanUtils.copyProperties(clazzInfo, clazz);
            clazz = clazzRepository.save(clazz);
            UpicBeanUtils.copyProperties(clazz, clazzInfo);
            return clazzInfo;
        } catch (Exception e) {
            LOGGER.info("addClazz：" + e.getMessage());
            return null;
        }
    }

    @Override
    public ClazzInfo updateClazz(ClazzInfo clazzInfo) {
        try {
            Clazz clazz = new Clazz();
            UpicBeanUtils.copyProperties(clazzInfo, clazz);
            clazz = clazzRepository.saveAndFlush(clazz);
            UpicBeanUtils.copyProperties(clazz, clazzInfo);
            return clazzInfo;
        } catch (Exception e) {
            LOGGER.info("updateClazz：" + e.getMessage());
            return null;
        }
    }

    @Override
    public Page<ClazzInfo> searchClazz(ClazzCondition clazzCondition, Pageable pageable) {
        Page<Clazz> clazzPage = null;
        try {
            clazzPage = clazzRepository.findAll(new ClazzSpec(clazzCondition), pageable);
            return QueryResultConverter.convert(clazzPage, pageable, new AbstractDomain2InfoConverter<Clazz, ClazzInfo>() {
                @Override
                protected void doConvert(Clazz domain, ClazzInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("searchClazz：" + e.getMessage());
            return null;
        }
    }

    @Override
    public ClazzInfo getById(long clazzId) {
        try {
            ClazzInfo c = new ClazzInfo();
            Clazz clazz = clazzRepository.findOne(clazzId);
            UpicBeanUtils.copyProperties(clazz, c);
            return c;
        } catch (Exception e) {
            LOGGER.info("getById：" + e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteClazz(long clazzId) {
        try {
            clazzRepository.delete(clazzId);
        } catch (Exception e) {
            LOGGER.info("deleteClazz：" + e.getMessage());
        }
    }
}
