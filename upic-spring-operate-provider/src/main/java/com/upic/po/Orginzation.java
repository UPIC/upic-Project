package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.OrginzationStatusEnum;
import com.upic.enums.OrginzationTypeEnum;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class Orginzation extends BaseEntity {
    private String orginationName; //组织名

    private String orginationNum; //组织编号

    @Enumerated(EnumType.STRING)
    private OrginzationStatusEnum status; //组织状态

    @Enumerated(EnumType.STRING)
    private OrginzationTypeEnum type; //组织类型
}
