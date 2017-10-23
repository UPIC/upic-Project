package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.CollegeStatusEnum;
import com.upic.enums.CollegeTypeEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by zhubuqing on 2017/9/5.
 */
@Data
@Entity
public class College extends BaseEntity {
    private String college;

    @Enumerated(EnumType.STRING)
    private CollegeStatusEnum status;

    @Enumerated(EnumType.STRING)
    private CollegeTypeEnum type;
}
