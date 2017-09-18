package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;
import com.upic.enums.SystemAdministrationLogTypeEnum;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class SystemAdministrationLogCondition extends BaseCondition {
    private String operation; //操作内容

    private String operatorName; //操作人

    private String operatorNum; //操作人编号

    private SystemAdministrationLogTypeEnum type; //操作类型

    public SystemAdministrationLogCondition() {
        super();
    }

    public SystemAdministrationLogCondition(String operation, String operatorName, String operatorNum, SystemAdministrationLogTypeEnum type) {
        super();
        this.operation = operation;
        this.operatorName = operatorName;
        this.operatorNum = operatorNum;
        this.type = type;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorNum() {
        return operatorNum;
    }

    public void setOperatorNum(String operatorNum) {
        this.operatorNum = operatorNum;
    }

    public SystemAdministrationLogTypeEnum getType() {
        return type;
    }

    public void setType(SystemAdministrationLogTypeEnum type) {
        this.type = type;
    }
}
