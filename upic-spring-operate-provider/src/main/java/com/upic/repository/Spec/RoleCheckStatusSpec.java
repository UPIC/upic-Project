package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.CheckStatusCondition;
import com.upic.condition.RoleCheckStatusCondition;
import com.upic.po.CheckStatus;
import com.upic.po.RoleCheckStatus;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class RoleCheckStatusSpec extends CommonSimpleSpecification<RoleCheckStatus, RoleCheckStatusCondition> {
    public RoleCheckStatusSpec(RoleCheckStatusCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<RoleCheckStatus> queryWraper) {
        addEqualsCondition(queryWraper, "roleName");
        addEqualsCondition(queryWraper, "num");
        addEqualsCondition(queryWraper, "enumName");
        addEqualsCondition(queryWraper, "roleId");
        addEqualsCondition(queryWraper, "checkStatusName");
    }
}
