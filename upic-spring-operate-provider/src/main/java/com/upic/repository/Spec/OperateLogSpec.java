package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.OperateLogCondition;
import com.upic.po.OperateLog;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class OperateLogSpec extends CommonSimpleSpecification<OperateLog, OperateLogCondition> {
    public OperateLogSpec(OperateLogCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<OperateLog> queryWraper) {
        addEqualsCondition(queryWraper, "operator");
        addEqualsCondition(queryWraper, "operatorNum");
        addEqualsCondition(queryWraper, "ipAddress");
        addEqualsCondition(queryWraper, "type");
        addEqualsCondition(queryWraper, "status");
        addLikeCondition(queryWraper, "content");
    }
}
