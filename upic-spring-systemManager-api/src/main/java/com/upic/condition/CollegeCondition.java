package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;
import com.upic.enums.BannerStatusEnum;
import com.upic.enums.BannerTypeEnum;
import com.upic.enums.CollegeStatusEnum;
import com.upic.enums.CollegeTypeEnum;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class CollegeCondition extends BaseCondition {
    private String college;

    private String otherName;

    private String rank;

    private CollegeStatusEnum status;

    private CollegeTypeEnum type;

    public CollegeCondition() {
    }

    public CollegeCondition(String college, String otherName, String rank, CollegeStatusEnum status, CollegeTypeEnum type) {
        this.college = college;
        this.otherName = otherName;
        this.rank = rank;
        this.status = status;
        this.type = type;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public CollegeStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CollegeStatusEnum status) {
        this.status = status;
    }

    public CollegeTypeEnum getType() {
        return type;
    }

    public void setType(CollegeTypeEnum type) {
        this.type = type;
    }
}
