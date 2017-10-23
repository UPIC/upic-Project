package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.BannerStatusEnum;
import com.upic.enums.BannerTypeEnum;
import com.upic.enums.ClazzStatusEnum;
import com.upic.enums.ClazzTypeEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by zhubuqing on 2017/9/5.
 */
@Data
@Entity
public class Clazz extends BaseEntity {
    private String college;

    private String major;

    private String clazz;

    @Enumerated(EnumType.STRING)
    private ClazzStatusEnum status;

    @Enumerated(EnumType.STRING)
    private ClazzTypeEnum type;
}
