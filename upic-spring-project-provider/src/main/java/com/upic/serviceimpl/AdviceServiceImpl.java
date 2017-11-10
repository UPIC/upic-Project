package com.upic.serviceimpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.dto.AdviceInfo;
import com.upic.po.Advice;
import com.upic.repository.AdviceRepository;
import com.upic.service.AdviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhubuqing on 2017/11/7.
 */
@Service("adviceService")
public class AdviceServiceImpl implements AdviceService {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AdviceServiceImpl.class);

    @Autowired
    private AdviceRepository adviceRepository;

    @Override
    public AdviceInfo addAdvice(AdviceInfo adviceInfo) {
        try {
            Advice advice = new Advice();
            UpicBeanUtils.copyProperties(adviceInfo, advice);
            advice = adviceRepository.save(advice);
            UpicBeanUtils.copyProperties(advice, adviceInfo);
            return adviceInfo;
        } catch (Exception e) {
            LOGGER.info("addAdvice：" + e.getMessage());
            return null;
        }
    }

    @Override
    public AdviceInfo getAdviceByProjectId(long projectId) {
        try {
            Sort sort = new Sort(Sort.Direction.ASC, "creatTime");
            Advice advice = adviceRepository.getByProjectId(projectId, sort);
            AdviceInfo adviceInfo = new AdviceInfo();
            UpicBeanUtils.copyProperties(advice, adviceInfo);
            return adviceInfo;
        } catch (Exception e) {
            LOGGER.info("getAdviceByProjectNum：" + e.getMessage());
            return null;
        }
    }
}
