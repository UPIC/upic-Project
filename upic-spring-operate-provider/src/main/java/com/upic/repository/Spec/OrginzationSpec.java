package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.OrginzationCondition;
import com.upic.po.Orginzation;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class OrginzationSpec extends CommonSimpleSpecification<Orginzation, OrginzationCondition> {
    public OrginzationSpec(OrginzationCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<Orginzation> queryWraper) {
        addEqualsCondition(queryWraper, "orginationName");
        addEqualsCondition(queryWraper, "orginationNum");
        addEqualsCondition(queryWraper, "status");
        addEqualsCondition(queryWraper, "type");
    }
}
