package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.IntegralLogCondition;
import com.upic.po.IntegralLog;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class IntegralLogSpec extends CommonSimpleSpecification<IntegralLog, IntegralLogCondition> {
    public IntegralLogSpec(IntegralLogCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<IntegralLog> queryWraper) {
        addLikeCondition(queryWraper, "event");
        addLikeCondition(queryWraper, "student");
        addEqualsCondition(queryWraper, "type");
        addEqualsCondition(queryWraper, "status");
        addEqualsCondition(queryWraper, "integral");
        addBetweenCondition(queryWraper, "creatTime");
        addEqualsCondition(queryWraper, "clazz");
        addEqualsCondition(queryWraper, "college");
    }
}
