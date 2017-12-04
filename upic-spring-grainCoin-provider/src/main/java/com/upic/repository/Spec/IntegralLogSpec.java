package com.upic.repository.Spec;

import javax.persistence.criteria.Path;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.IntegralLogCondition;
import com.upic.po.IntegralLog;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class IntegralLogSpec extends CommonSimpleSpecification<IntegralLog, IntegralLogCondition> {
    public IntegralLogSpec(IntegralLogCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<IntegralLog> queryWraper) {
        addLikeCondition(queryWraper, "event");
        addLikeCondition(queryWraper, "student");
        addEqualsCondition(queryWraper, "type");
        addEqualsCondition(queryWraper, "status");
        addEqualsCondition(queryWraper, "integral");
        addBetweenCondition(queryWraper, "creatTime");
        addEqualsCondition(queryWraper, "clazz");
        addEqualsCondition(queryWraper, "college");
        addEqualsCondition(queryWraper, "projectName");
        addEqualsCondition(queryWraper, "projectCategory");
        addEqualsCondition(queryWraper, "collegeOtherName");
        addBetweenCondition(queryWraper, "addTime");
        addOrCondition(queryWraper, "orList");
        addEqualsCondition(queryWraper, "integralLogId.studentNum");
        addEqualsCondition(queryWraper, "integralLogId.projectNum");
    }
    
    protected void addEqualsConditionToColumnSpec(QueryWraper<IntegralLog> queryWraper, String column, Object value) {
		if (needAddCondition(value)) {
			Path<?> fieldPath = getPath(queryWraper.getRoot(), column);
			queryWraper.addPredicate(queryWraper.getCb().equal(fieldPath, value));
		}
	}
}
