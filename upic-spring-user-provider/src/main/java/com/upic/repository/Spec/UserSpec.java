package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.UserCondition;
import com.upic.po.User;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class UserSpec extends CommonSimpleSpecification<User, UserCondition> {
    public UserSpec(UserCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<User> queryWraper) {
        addEqualsCondition(queryWraper, "userNum");
        addEqualsCondition(queryWraper, "username");
        addEqualsCondition(queryWraper, "college");
        addEqualsCondition(queryWraper, "major");
        addEqualsCondition(queryWraper, "clazz");
        addEqualsCondition(queryWraper, "phone");
        addEqualsCondition(queryWraper, "idCard");
        addEqualsCondition(queryWraper, "email");
        addEqualsCondition(queryWraper, "status");
        addEqualsCondition(queryWraper, "type");
        addLikeCondition(queryWraper, "nickName");
        addBetweenCondition(queryWraper, "birthday");
        addBetweenCondition(queryWraper, "earnedPoints");
        addBetweenCondition(queryWraper, "earningPoints");
    }
}
