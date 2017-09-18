package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.CategoryNodeCondition;
import com.upic.po.CategoryNode;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class CategoryNodeSpec extends CommonSimpleSpecification<CategoryNode, CategoryNodeCondition> {
    public CategoryNodeSpec(CategoryNodeCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<CategoryNode> queryWraper) {
        addEqualsCondition(queryWraper,"fatherId");
        addEqualsCondition(queryWraper,"type");
        addEqualsCondition(queryWraper,"level");
        addLikeCondition(queryWraper,"nodeContent");
        addEqualsCondition(queryWraper, "isLeaf");
    }
}
