package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.OrginzationOperatorCondition;
import com.upic.po.OrginzationOperator;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class OrginzationOperatorSpec extends CommonSimpleSpecification<OrginzationOperator, OrginzationOperatorCondition> {
    public OrginzationOperatorSpec(OrginzationOperatorCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<OrginzationOperator> queryWraper) {
        addEqualsCondition(queryWraper, "orginationName");
        addEqualsCondition(queryWraper, "orginationNum");
        addEqualsCondition(queryWraper, "jobNum");
        addEqualsCondition(queryWraper, "username");
    }
}
