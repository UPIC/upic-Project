package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.AdviceStatusOperationEnum;
import com.upic.enums.AdviceTypeEnum;

/**
 * Created by zhubuqing on 2017/9/4.
 */
public class AdviceInfo extends BaseInfo {
    private String advice; //建议

    private String operator; //操作人

    private String operatorNum; //操作人编号

    private AdviceStatusOperationEnum statusOperation; //操作结果

    private AdviceTypeEnum type; //类型

    private long projectId;

    public AdviceInfo() {
        super();
    }

    public AdviceInfo(String advice, String operator, String operatorNum, AdviceStatusOperationEnum statusOperation, AdviceTypeEnum type, long projectId) {
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

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "AdviceInfo{" +
                "advice='" + advice + '\'' +
                ", operator='" + operator + '\'' +
                ", operatorNum='" + operatorNum + '\'' +
                ", statusOperation=" + statusOperation +
                ", type=" + type +
                ", projectId=" + projectId +
                '}';
    }
}
