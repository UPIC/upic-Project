package com.upic.repository.spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.AdviceCondition;
import com.upic.po.Advice;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class AdviceSpec extends CommonSimpleSpecification<Advice, AdviceCondition> {
    public AdviceSpec(AdviceCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<Advice> queryWraper) {
        addLikeCondition(queryWraper, "advice");
        addEqualsCondition(queryWraper, "operator");
        addEqualsCondition(queryWraper, "operatorNum");
        addEqualsCondition(queryWraper, "statusOperation");
        addEqualsCondition(queryWraper, "type");
    }
}
