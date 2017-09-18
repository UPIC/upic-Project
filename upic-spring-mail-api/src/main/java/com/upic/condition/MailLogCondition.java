package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;
import com.upic.enums.MailLogTypeEnum;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class MailLogCondition extends BaseCondition {
    private String operator; //操作人

    private String operatorNum; //操作人编号

    private String operation; //操作内容

    private MailLogTypeEnum type; //操作类型

    public MailLogCondition() {
        super();
    }

    public MailLogCondition(String operator, String operatorNum, String operation, MailLogTypeEnum type) {
        super();
        this.operator = operator;
        this.operatorNum = operatorNum;
        this.operation = operation;
        this.type = type;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorNum() {
        return operatorNum;
    }

    public void setOperatorNum(String operatorNum) {
        this.operatorNum = operatorNum;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public MailLogTypeEnum getType() {
        return type;
    }

    public void setType(MailLogTypeEnum type) {
        this.type = type;
    }
}
