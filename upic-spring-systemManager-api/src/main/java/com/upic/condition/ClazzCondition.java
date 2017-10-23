package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;
import com.upic.enums.BannerStatusEnum;
import com.upic.enums.BannerTypeEnum;
import com.upic.enums.ClazzStatusEnum;
import com.upic.enums.ClazzTypeEnum;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class ClazzCondition extends BaseCondition {
    private String college;

    private String major;

    private String clazz;

    private ClazzStatusEnum status;

    private ClazzTypeEnum type;

    public ClazzCondition() {
        super();
    }

    public ClazzCondition(String college, String major, String clazz, ClazzStatusEnum status, ClazzTypeEnum type) {
        this.college = college;
        this.major = major;
        this.clazz = clazz;
        this.status = status;
        this.type = type;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public ClazzStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ClazzStatusEnum status) {
        this.status = status;
    }

    public ClazzTypeEnum getType() {
        return type;
    }

    public void setType(ClazzTypeEnum type) {
        this.type = type;
    }
}
