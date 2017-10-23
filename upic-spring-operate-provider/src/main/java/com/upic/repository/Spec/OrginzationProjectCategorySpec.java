package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.OrginzationProjectCategoryCondition;
import com.upic.po.OrginzationProjectCategory;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class OrginzationProjectCategorySpec extends CommonSimpleSpecification<OrginzationProjectCategory, OrginzationProjectCategoryCondition> {
    public OrginzationProjectCategorySpec(OrginzationProjectCategoryCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<OrginzationProjectCategory> queryWraper) {
        addEqualsCondition(queryWraper, "orginationName");
        addEqualsCondition(queryWraper, "orginationNum");
        addEqualsCondition(queryWraper, "projectCategoryId");
        addEqualsCondition(queryWraper, "projectCategory");
    }
}
