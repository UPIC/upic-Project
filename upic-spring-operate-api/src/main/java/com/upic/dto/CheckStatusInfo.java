package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class CheckStatusInfo extends BaseInfo {
    private int num;

    private String type;

    private String enumName;

    private String name;

    public CheckStatusInfo() {
    }

    public CheckStatusInfo(int num, String type, String enumName, String name) {
        this.num = num;
        this.type = type;
        this.enumName = enumName;
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CheckStatusInfo{" +
                "num=" + num +
                ", type='" + type + '\'' +
                ", enumName='" + enumName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
