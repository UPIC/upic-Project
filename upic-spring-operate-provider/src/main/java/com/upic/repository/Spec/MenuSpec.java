package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.MenuCondition;
import com.upic.po.Menu;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class MenuSpec extends CommonSimpleSpecification<Menu, MenuCondition> {
    public MenuSpec(MenuCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<Menu> queryWraper) {
        addEqualsCondition(queryWraper,"menu");
        addEqualsCondition(queryWraper,"url");
        addEqualsCondition(queryWraper,"status");
        addEqualsCondition(queryWraper,"type");
    }
}
