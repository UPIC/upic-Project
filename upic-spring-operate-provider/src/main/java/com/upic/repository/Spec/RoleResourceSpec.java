package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.RoleResourceCondition;
import com.upic.po.RoleResource;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class RoleResourceSpec extends CommonSimpleSpecification<RoleResource, RoleResourceCondition> {
    public RoleResourceSpec(RoleResourceCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<RoleResource> queryWraper) {
        addEqualsCondition(queryWraper,"roleId");
        addEqualsCondition(queryWraper,"resourceNum");
        addEqualsCondition(queryWraper,"status");
        addEqualsCondition(queryWraper,"type");
    }
}
