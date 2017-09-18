package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.ResourceCondition;
import com.upic.po.Resource;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class ResourceSpec extends CommonSimpleSpecification<Resource, ResourceCondition> {
    public ResourceSpec(ResourceCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<Resource> queryWraper) {
        addEqualsCondition(queryWraper,"resourceNum");
        addEqualsCondition(queryWraper,"resourceName");
        addEqualsCondition(queryWraper,"url");
        addEqualsCondition(queryWraper,"status");
        addEqualsCondition(queryWraper,"type");
        addEqualsCondition(queryWraper,"level");
        addEqualsCondition(queryWraper,"fatherId");
        addEqualsCondition(queryWraper,"isLeaf");
    }
}
