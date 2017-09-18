package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.MailLogCondition;
import com.upic.po.MailLog;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class MailLogSpec extends CommonSimpleSpecification<MailLog, MailLogCondition> {
    public MailLogSpec(MailLogCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<MailLog> queryWraper) {
        addEqualsCondition(queryWraper,"operator");
        addEqualsCondition(queryWraper,"operatorNum");
        addEqualsCondition(queryWraper,"type");
        addLikeCondition(queryWraper,"operation");
    }
}
