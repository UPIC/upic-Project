package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.SystemAdministrationLogCondition;
import com.upic.po.SystemAdministrationLog;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class SystemAdministrationLogSpec extends CommonSimpleSpecification<SystemAdministrationLog, SystemAdministrationLogCondition> {
    public SystemAdministrationLogSpec(SystemAdministrationLogCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<SystemAdministrationLog> queryWraper) {
        addLikeCondition(queryWraper,"operation");
        addEqualsCondition(queryWraper,"operatorName");
        addEqualsCondition(queryWraper,"operatorNum");
        addEqualsCondition(queryWraper,"type");
    }
}
