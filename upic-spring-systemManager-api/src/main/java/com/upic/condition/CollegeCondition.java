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

    private CollegeStatusEnum status;

    private CollegeTypeEnum type;

    public CollegeCondition() {
        super();
    }

    public CollegeCondition(String college, CollegeStatusEnum status, CollegeTypeEnum type) {
        this.college = college;
        this.status = status;
        this.type = type;
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
}
