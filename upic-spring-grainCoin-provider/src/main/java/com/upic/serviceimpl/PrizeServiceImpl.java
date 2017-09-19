package com.upic.serviceimpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.PrizeCondition;
import com.upic.dto.PrizeInfo;
import com.upic.po.Prize;
import com.upic.repository.PrizeRepository;
import com.upic.repository.Spec.PrizeSpec;
import com.upic.service.PrizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("prizeService")
public class PrizeServiceImpl implements PrizeService {
    @Autowired
    private PrizeRepository prizeRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(PrizeServiceImpl.class);

    public PrizeInfo addPrize(PrizeInfo prizeInfo) {
        try {
            Prize prize = new Prize();
            UpicBeanUtils.copyProperties(prizeInfo, prize);
            prize = prizeRepository.save(prize);
            UpicBeanUtils.copyProperties(prize, prizeInfo);
        } catch (Exception e) {
            LOGGER.info("addPrize:奖品" + prizeInfo.toString() + "添加失败。错误信息：" + e.getMessage());
            return null;
        }
        return prizeInfo;
    }

    public PrizeInfo updatePrize(PrizeInfo prizeInfo) {

        try {
            Prize prize = prizeRepository.findOne(prizeInfo.getId());
            UpicBeanUtils.copyProperties(prizeInfo, prize);
            prize = prizeRepository.saveAndFlush(prize);
            UpicBeanUtils.copyProperties(prize, prizeInfo);
            return prizeInfo;
        } catch (Exception e) {
            LOGGER.info("updatePrize:奖品" + prizeInfo.toString() + "更新失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<PrizeInfo> searchPrizes(PrizeCondition prizeCondition, Pageable pageable) {
        Page<Prize> prizePage = null;
        try {
            prizePage = prizeRepository.findAll(new PrizeSpec(prizeCondition), pageable);
            return QueryResultConverter.convert(prizePage, pageable, new AbstractDomain2InfoConverter<Prize, PrizeInfo>() {
                @Override
                protected void doConvert(Prize domain, PrizeInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("searchPrizes:奖品列表查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public PrizeInfo getPrizeById(long prizeId) {
        try {
            Prize prize = prizeRepository.findOne(prizeId);
            if (prize == null) {
                throw new Exception();
            }
            PrizeInfo prizeInfo = new PrizeInfo();
            UpicBeanUtils.copyProperties(prize, prizeInfo);
            return prizeInfo;
        } catch (Exception e) {
            LOGGER.info("getPrizeById:单个奖品查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<PrizeInfo> getHistoryPrize(Pageable pageable) {
        Page<Prize> prizePage = null;
        try {
            prizePage = prizeRepository.getHistoryPrize(pageable);
            return QueryResultConverter.convert(prizePage, pageable, new AbstractDomain2InfoConverter<Prize, PrizeInfo>() {
                @Override
                protected void doConvert(Prize domain, PrizeInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("getHistoryPrize:单个奖品查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }
}
