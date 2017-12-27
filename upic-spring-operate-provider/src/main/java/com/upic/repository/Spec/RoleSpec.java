package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.RoleCondition;
import com.upic.po.Role;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class RoleSpec extends CommonSimpleSpecification<Role, RoleCondition> {
    public RoleSpec(RoleCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<Role> queryWraper) {
        addEqualsCondition(queryWraper,"roleName");
        addEqualsCondition(queryWraper,"status");
        addEqualsCondition(queryWraper,"type");
        addEqualsCondition(queryWraper,"content");
    }
}
