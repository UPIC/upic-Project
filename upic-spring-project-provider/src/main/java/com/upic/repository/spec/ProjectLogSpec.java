package com.upic.repository.spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.ProjectLogCondition;
import com.upic.po.ProjectLog;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class ProjectLogSpec extends CommonSimpleSpecification<ProjectLog, ProjectLogCondition> {
    public ProjectLogSpec(ProjectLogCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<ProjectLog> queryWraper) {
        addLikeCondition(queryWraper,"operation");
        addEqualsCondition(queryWraper,"operatorName");
        addEqualsCondition(queryWraper,"projectId");
        addEqualsCondition(queryWraper,"operatorNum");
    }
}
