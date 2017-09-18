package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.RoleJurisdictionCondition;
import com.upic.po.RoleJurisdiction;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class RoleJurisdictionSpec extends CommonSimpleSpecification<RoleJurisdiction, RoleJurisdictionCondition> {
    public RoleJurisdictionSpec(RoleJurisdictionCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<RoleJurisdiction> queryWraper) {
        addEqualsCondition(queryWraper,"status");
        addEqualsCondition(queryWraper,"type");
    }
}
