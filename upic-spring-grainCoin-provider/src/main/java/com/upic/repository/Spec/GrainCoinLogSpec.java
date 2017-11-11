package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.GrainCoinLogCondition;
import com.upic.po.GrainCoinLog;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class GrainCoinLogSpec extends CommonSimpleSpecification<GrainCoinLog, GrainCoinLogCondition> {
    public GrainCoinLogSpec(GrainCoinLogCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<GrainCoinLog> queryWraper) {
        addLikeCondition(queryWraper, "event");
        addLikeCondition(queryWraper, "projectName");
        addLikeCondition(queryWraper, "prizeName");
        addEqualsCondition(queryWraper, "score");
        addEqualsCondition(queryWraper, "type");
        addEqualsCondition(queryWraper, "status");
        addLikeCondition(queryWraper, "username");
        addEqualsCondition(queryWraper, "userNum");
        addOrCondition(queryWraper, "orList");
    }
}
