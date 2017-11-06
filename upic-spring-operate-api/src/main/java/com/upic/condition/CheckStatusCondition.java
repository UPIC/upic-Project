package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class CheckStatusCondition extends BaseCondition {
    private Integer num;

    private String type;

    private String enumName;

    private String name;

    public CheckStatusCondition() {
    }

    public CheckStatusCondition(Integer num, String type, String enumName, String name) {
        this.num = num;
        this.type = type;
        this.enumName = enumName;
        this.name = name;
    }

    public Integer getNum() {
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
}
