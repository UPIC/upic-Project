package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.BannerStatusEnum;
import com.upic.enums.BannerTypeEnum;
import com.upic.enums.CollegeStatusEnum;
import com.upic.enums.CollegeTypeEnum;

/**
 * Created by zhubuqing on 2017/9/5.
 */
public class CollegeInfo extends BaseInfo {
    private String college;

    private CollegeStatusEnum status;

    private CollegeTypeEnum type;

    private String otherName;

    private String rank;

    public CollegeInfo() {
    }

    public CollegeInfo(String college, CollegeStatusEnum status, CollegeTypeEnum type, String otherName, String rank) {
        this.college = college;
        this.status = status;
        this.type = type;
        this.otherName = otherName;
        this.rank = rank;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
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

    @Override
    public String toString() {
        return "CollegeInfo{" +
                "college='" + college + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", otherName='" + otherName + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }
}