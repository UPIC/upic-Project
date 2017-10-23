package com.upic.repository.spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.ProjectCondition;
import com.upic.po.Project;


public class ProjectSpec extends CommonSimpleSpecification<Project, ProjectCondition> {

    public ProjectSpec(ProjectCondition condition) {
        super(condition);
    }

    @Override
    protected void addCondition(QueryWraper<Project> queryWraper) {
        addLikeCondition(queryWraper, "projectNum");
        addLikeCondition(queryWraper, "projectName");
        addBetweenCondition(queryWraper, "integral");
        addBetweenCondition(queryWraper, "startTime");
        addBetweenCondition(queryWraper, "endTime");
        addBetweenCondition(queryWraper, "signUpStartTime");
        addBetweenCondition(queryWraper, "signUpEndTime");
        addEqualsCondition(queryWraper, "declareUnit");
        addEqualsCondition(queryWraper, "guidanceMan");
        addEqualsCondition(queryWraper, "maximum");
        addEqualsCondition(queryWraper, "implementationProcess");
        addEqualsCondition(queryWraper, "checkAssessmentCriteraAndForm");
        addEqualsCondition(queryWraper, "projectAddWay");
        addEqualsCondition(queryWraper, "unit");
        addEqualsCondition(queryWraper, "onOff");
        addEqualsCondition(queryWraper, "refreshTime");
        addEqualsCondition(queryWraper, "type");
        addEqualsCondition(queryWraper, "projectCategoryId");
    }
}
