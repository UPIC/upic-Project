package com.upic.repository.Spec;

import com.upic.common.base.condition.BaseCondition;
import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.po.Clazz;
import com.upic.po.College;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class ClazzSpec extends CommonSimpleSpecification<Clazz, BaseCondition> {
    public ClazzSpec(BaseCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<Clazz> queryWraper) {
        addEqualsCondition(queryWraper, "college");
        addEqualsCondition(queryWraper, "major");
        addEqualsCondition(queryWraper, "clazz");
        addEqualsCondition(queryWraper, "status");
        addEqualsCondition(queryWraper, "type");
    }
}
