package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.OrginzationStatusEnum;
import com.upic.enums.OrginzationTypeEnum;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class OrginzationInfo extends BaseInfo {
    private String orginationName; //组织名

    private String orginationNum; //组织编号

    private OrginzationStatusEnum status; //组织状态

    private OrginzationTypeEnum type; //组织类型

    public OrginzationInfo() {
        super();
    }

    public OrginzationInfo(String orginationName, String orginationNum, OrginzationStatusEnum status, OrginzationTypeEnum type) {
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

    @Override
    public String toString() {
        return "OrginzationInfo{" +
                "orginationName='" + orginationName + '\'' +
                ", orginationNum='" + orginationNum + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
