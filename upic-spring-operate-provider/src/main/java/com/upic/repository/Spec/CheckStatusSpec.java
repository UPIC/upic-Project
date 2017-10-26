package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.CheckStatusCondition;
import com.upic.condition.RoleCondition;
import com.upic.po.CheckStatus;
import com.upic.po.Role;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class CheckStatusSpec extends CommonSimpleSpecification<CheckStatus, CheckStatusCondition> {
    public CheckStatusSpec(CheckStatusCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<CheckStatus> queryWraper) {
        addEqualsCondition(queryWraper, "num");
        addEqualsCondition(queryWraper, "enumName");
        addEqualsCondition(queryWraper, "type");
        addEqualsCondition(queryWraper, "name");
    }
}
