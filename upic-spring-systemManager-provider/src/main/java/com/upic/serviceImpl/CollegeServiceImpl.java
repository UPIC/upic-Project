package com.upic.serviceImpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.CollegeCondition;
import com.upic.dto.CollegeInfo;
import com.upic.po.College;
import com.upic.repository.CollegeRepository;
import com.upic.repository.Spec.CollegeSpec;
import com.upic.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.upic.serviceImpl.BannerServiceImpl.LOGGER;

/**
 * Created by zhubuqing on 2017/9/10.
 */
@Service("collegeService")
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    private CollegeRepository collegeRepository;

    @Override
    public CollegeInfo addCollege(CollegeInfo collegeInfo) {
        try {
            College college = new College();
            UpicBeanUtils.copyProperties(collegeInfo, college);
            college = collegeRepository.save(college);
            UpicBeanUtils.copyProperties(college, collegeInfo);
            return collegeInfo;
        } catch (Exception e) {
            LOGGER.info("addCollege错误信息：" + e.getMessage());
            return null;
        }
    }

    @Override
    public CollegeInfo updateCollege(CollegeInfo collegeInfo) {
        try {
            College college = new College();
            UpicBeanUtils.copyProperties(collegeInfo, college);
            college = collegeRepository.saveAndFlush(college);
            UpicBeanUtils.copyProperties(college, collegeInfo);
            return collegeInfo;
        } catch (Exception e) {
            LOGGER.info("updateCollege：" + e.getMessage());
            return null;
        }
    }

    @Override
    public Page<CollegeInfo> searchCollege(CollegeCondition collegeCondition, Pageable pageable) {
        Page<College> collegePage = null;
        try {
            collegePage = collegeRepository.findAll(new CollegeSpec(collegeCondition), pageable);
            return QueryResultConverter.convert(collegePage, pageable, new AbstractDomain2InfoConverter<College, CollegeInfo>() {
                @Override
                protected void doConvert(College domain, CollegeInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("searchCollege：" + e.getMessage());
            return null;
        }
    }

    @Override
    public CollegeInfo getById(long collegeId) {
        try {
            CollegeInfo collegeInfo = new CollegeInfo();
            College college = collegeRepository.getOne(collegeId);
            UpicBeanUtils.copyProperties(college, collegeInfo);
            return collegeInfo;
        } catch (Exception e) {
            LOGGER.info("getById：" + e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteCollege(long collegeId) {
        try {
            collegeRepository.delete(collegeId);
        } catch (Exception e) {
            LOGGER.info("deleteCollege：" + e.getMessage());
        }
    }
}
