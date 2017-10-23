package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;
import com.upic.enums.OrginzationStatusEnum;
import com.upic.enums.OrginzationTypeEnum;
import com.upic.enums.RoleStatusEnum;
import com.upic.enums.RoleTypeEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class OrginzationCondition extends BaseCondition {
    private String orginationName; //组织名

    private String orginationNum; //组织编号

    private OrginzationStatusEnum status; //组织状态

    private OrginzationTypeEnum type; //组织类型

    public OrginzationCondition() {
        super();
    }

    public OrginzationCondition(String orginationName, String orginationNum, OrginzationStatusEnum status, OrginzationTypeEnum type) {
        this.orginationName = orginationName;
        this.orginationNum = orginationNum;
        this.status = status;
        this.type = type;
    }

    public String getOrginationName() {
        return orginationName;
    }

    public void setOrginationName(String orginationName) {
        this.orginationName = orginationName;
    }

    public String getOrginationNum() {
        return orginationNum;
    }

    public void setOrginationNum(String orginationNum) {
        this.orginationNum = orginationNum;
    }

    public OrginzationStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrginzationStatusEnum status) {
        this.status = status;
    }

    public OrginzationTypeEnum getType() {
        return type;
    }

    public void setType(OrginzationTypeEnum type) {
        this.type = type;
    }
}
