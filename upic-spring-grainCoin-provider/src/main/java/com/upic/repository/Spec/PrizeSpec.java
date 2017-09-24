package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.PrizeCondition;
import com.upic.po.Prize;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class PrizeSpec extends CommonSimpleSpecification<Prize, PrizeCondition> {
    public PrizeSpec(PrizeCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<Prize> queryWraper) {
        addLikeCondition(queryWraper, "prizeName");
        addBetweenCondition(queryWraper, "score");
        addEqualsCondition(queryWraper, "status");
        addLikeCondition(queryWraper, "title");
        addLikeCondition(queryWraper, "content");
        addBetweenCondition(queryWraper, "startTime");
        addBetweenCondition(queryWraper, "endTime");
    }
}
