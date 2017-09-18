package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.JurisdictionCondition;
import com.upic.po.Jurisdiction;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class JurisdictionSpec extends CommonSimpleSpecification<Jurisdiction, JurisdictionCondition> {
    public JurisdictionSpec(JurisdictionCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<Jurisdiction> queryWraper) {
        addLikeCondition(queryWraper, "jurisdictionName");
        addLikeCondition(queryWraper, "remark");
        addEqualsCondition(queryWraper, "resourceId");
        addEqualsCondition(queryWraper, "url");
        addEqualsCondition(queryWraper, "status");
        addEqualsCondition(queryWraper, "type");
    }
}
