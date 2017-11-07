package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;
import com.upic.enums.AdviceStatusOperationEnum;
import com.upic.enums.AdviceTypeEnum;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class AdviceCondition extends BaseCondition {
    private String advice; //建议

    private String operator; //操作人

    private String operatorNum; //操作人编号

    private AdviceStatusOperationEnum statusOperation; //操作结果

    private AdviceTypeEnum type; //类型

    private Long projectId;

    public AdviceCondition() {
        super();
    }

    public AdviceCondition(String advice, String operator, String operatorNum, AdviceStatusOperationEnum statusOperation, AdviceTypeEnum type, Long projectId) {
        this.advice = advice;
        this.operator = operator;
        this.operatorNum = operatorNum;
        this.statusOperation = statusOperation;
        this.type = type;
        this.projectId = projectId;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
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

    public AdviceStatusOperationEnum getStatusOperation() {
        return statusOperation;
    }

    public void setStatusOperation(AdviceStatusOperationEnum statusOperation) {
        this.statusOperation = statusOperation;
    }

    public AdviceTypeEnum getType() {
        return type;
    }

    public void setType(AdviceTypeEnum type) {
        this.type = type;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
