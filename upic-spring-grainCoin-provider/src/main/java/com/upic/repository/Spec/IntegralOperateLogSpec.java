package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.IntegralOperateLogCondition;
import com.upic.po.IntegralOperateLog;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class IntegralOperateLogSpec extends CommonSimpleSpecification<IntegralOperateLog, IntegralOperateLogCondition> {
    public IntegralOperateLogSpec(IntegralOperateLogCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<IntegralOperateLog> queryWraper) {
        addLikeCondition(queryWraper,"event");
        addEqualsCondition(queryWraper,"operator");
        addEqualsCondition(queryWraper,"operatorNum");
        addEqualsCondition(queryWraper,"integralLogId");
    }
}
