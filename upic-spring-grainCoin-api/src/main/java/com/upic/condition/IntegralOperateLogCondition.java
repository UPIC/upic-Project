package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class IntegralOperateLogCondition extends BaseCondition {
    private String event; //操作内容

    private String operator; //操作人

    private String operatorNum; //操作人编号

    private String integralLogId; //积分日志

    private String finishCheckEnumName;

    private String finishCheck;

    public IntegralOperateLogCondition() {
        super();
    }

    public IntegralOperateLogCondition(String event, String operator, String operatorNum, String integralLogId, String finishCheckEnumName, String finishCheck) {
        this.event = event;
        this.operator = operator;
        this.operatorNum = operatorNum;
        this.integralLogId = integralLogId;
        this.finishCheckEnumName = finishCheckEnumName;
        this.finishCheck = finishCheck;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
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

    public String getIntegralLogId() {
        return integralLogId;
    }

    public void setIntegralLogId(String integralLogId) {
        this.integralLogId = integralLogId;
    }

    public String getFinishCheckEnumName() {
        return finishCheckEnumName;
    }

    public void setFinishCheckEnumName(String finishCheckEnumName) {
        this.finishCheckEnumName = finishCheckEnumName;
    }

    public String getFinishCheck() {
        return finishCheck;
    }

    public void setFinishCheck(String finishCheck) {
        this.finishCheck = finishCheck;
    }
}
