package com.upic.repository.Spec;

import com.upic.common.support.spec.CommonSimpleSpecification;
import com.upic.common.support.spec.QueryWraper;
import com.upic.condition.ConfirmationBasisCondition;
import com.upic.po.ConfirmationBasis;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public class ConfirmationBasisSpec extends CommonSimpleSpecification<ConfirmationBasis,ConfirmationBasisCondition> {
    public ConfirmationBasisSpec(ConfirmationBasisCondition condition) {
        super(condition);
    }

    protected void addCondition(QueryWraper<ConfirmationBasis> queryWraper) {
        addLikeCondition(queryWraper,"content");
    }
}
