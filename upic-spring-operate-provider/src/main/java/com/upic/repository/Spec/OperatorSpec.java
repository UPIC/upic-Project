package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.OperatorCondition;
import com.upic.po.Operator;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class OperatorSpec extends CommonSimpleSpecification<Operator, OperatorCondition> {
    public OperatorSpec(OperatorCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<Operator> queryWraper) {
        addEqualsCondition(queryWraper,"jobNum");
        addEqualsCondition(queryWraper,"username");
        addEqualsCondition(queryWraper,"email");
        addEqualsCondition(queryWraper,"status");
        addEqualsCondition(queryWraper,"phone");
        addEqualsCondition(queryWraper,"idcard");
        addEqualsCondition(queryWraper,"type");
    }
}
