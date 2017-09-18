package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.OperatorRoleCondition;
import com.upic.po.OperatorRole;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class OperatorRoleSpec extends CommonSimpleSpecification<OperatorRole, OperatorRoleCondition> {
    public OperatorRoleSpec(OperatorRoleCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<OperatorRole> queryWraper) {
        addEqualsCondition(queryWraper,"status");
        addEqualsCondition(queryWraper,"type");
    }
}
