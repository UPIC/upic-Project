package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.ProjectCategoryCondition;
import com.upic.po.ProjectCategory;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class ProjectCategorySpec extends CommonSimpleSpecification<ProjectCategory, ProjectCategoryCondition> {
    public ProjectCategorySpec(ProjectCategoryCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<ProjectCategory> queryWraper) {
        addEqualsCondition(queryWraper,"categoryName");
        addEqualsCondition(queryWraper,"subordinateSector");
    }
}
