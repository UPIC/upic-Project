package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class ConfirmationBasisCondition extends BaseCondition {
    private String content; //内容

    public ConfirmationBasisCondition() {
        super();
    }

    public ConfirmationBasisCondition(String content) {
        super();
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
