package com.upic.repository.Spec;

import com.upic.common.base.condition.BaseCondition;
import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.po.Banner;
import com.upic.po.College;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class CollegeSpec extends CommonSimpleSpecification<College, BaseCondition> {
    public CollegeSpec(BaseCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<College> queryWraper) {
        addEqualsCondition(queryWraper,"college");
        addEqualsCondition(queryWraper,"status");
        addEqualsCondition(queryWraper,"type");
    }
}
