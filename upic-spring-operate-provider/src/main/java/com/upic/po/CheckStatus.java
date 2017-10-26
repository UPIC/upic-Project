package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class CheckStatus extends BaseEntity {
    private int num;

    private String type;

    private String enumName;

    private String name;
}
