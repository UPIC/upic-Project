package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;
import com.upic.enums.BannerStatusEnum;
import com.upic.enums.BannerTypeEnum;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class BannerCondition extends BaseCondition {
    private BannerStatusEnum status;

    private BannerTypeEnum type;

    public BannerCondition() {
        super();
    }

    public BannerCondition(BannerStatusEnum status, BannerTypeEnum type) {
        super();
        this.status = status;
        this.type = type;
    }

    public BannerStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BannerStatusEnum status) {
        this.status = status;
    }

    public BannerTypeEnum getType() {
        return type;
    }

    public void setType(BannerTypeEnum type) {
        this.type = type;
    }
}
