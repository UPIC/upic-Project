package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.BannerStatusEnum;
import com.upic.enums.BannerTypeEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by zhubuqing on 2017/9/5.
 */
@Data
@Entity
public class Banner extends BaseEntity {
    private String pic; //图片

    private String url; //URL

    private int num; //顺序

    @Enumerated(EnumType.STRING)
    private BannerStatusEnum status;

    @Enumerated(EnumType.STRING)
    private BannerTypeEnum type;
}
