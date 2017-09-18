package com.upic.repository.Spec;

import com.upic.common.base.condition.BaseCondition;
import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.po.Banner;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class BannerSpec extends CommonSimpleSpecification<Banner, BaseCondition> {
    public BannerSpec(BaseCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<Banner> queryWraper) {
        addEqualsCondition(queryWraper,"status");
        addEqualsCondition(queryWraper,"type");
    }
}
