package com.upic.service;

import com.upic.condition.GrainCoinLogCondition;
import com.upic.dto.GrainCoinLogInfo;
import com.upic.dto.IntegralOperateLogInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public interface GrainCoinLogService {
    /**
     * 查看素拓分
     *
     * @param userNum
     * @return
     */
    double watchGrainCoin(String userNum);

    /**
     * 查看已消费的奖品
     *
     * @param grainCoinLogCondition
     * @param pageable
     * @return
     */
    Page<GrainCoinLogInfo> searchPrizeByCondition(GrainCoinLogCondition grainCoinLogCondition, Pageable pageable);

    /**
     * 奖品兑换
     *
     * @param prizeId
     * @param grainCoinLogInfo
     * @return
     */
    GrainCoinLogInfo exchangePrize(long prizeId, GrainCoinLogInfo grainCoinLogInfo);
}
