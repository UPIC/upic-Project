package com.upic.service;

import com.upic.condition.PrizeCondition;
import com.upic.dto.PrizeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public interface PrizeService {
    /**
     * 添加奖品
     *
     * @param prizeInfo
     * @return
     */
    PrizeInfo addPrize(PrizeInfo prizeInfo);

    /**
     * 修改奖品
     *
     * @param prizeInfo
     * @return
     */
    PrizeInfo updatePrize(PrizeInfo prizeInfo);

    /**
     * 查询奖品（条件）
     *
     * @param prizeCondition
     * @param pageable
     * @return
     */
    Page<PrizeInfo> searchPrizes(PrizeCondition prizeCondition, Pageable pageable);

    /**
     * 查询单个奖品
     *
     * @param prizeId
     * @return
     */
    PrizeInfo getPrizeById(long prizeId);

    /**
     * 获取历史物品
     *
     * @return
     */
    Page<PrizeInfo> getHistoryPrize(Pageable pageable);
}
