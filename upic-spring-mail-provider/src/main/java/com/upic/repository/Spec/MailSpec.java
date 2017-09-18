package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.MailCondition;
import com.upic.po.Mail;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class MailSpec extends CommonSimpleSpecification<Mail, MailCondition> {
    public MailSpec(MailCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<Mail> queryWraper) {
        addLikeCondition(queryWraper,"mailContent");
        addLikeCondition(queryWraper,"title");
        addLikeCondition(queryWraper,"target");
        addEqualsCondition(queryWraper,"type");
        addEqualsCondition(queryWraper,"mailer");
        addEqualsCondition(queryWraper,"mailNum");
        addEqualsCondition(queryWraper,"status");
    }
}
