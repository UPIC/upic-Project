package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

/**
 * 审批状态表
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class CheckStatus extends BaseEntity {
    private int num; // 编号

    private String type; // 类型

    private String enumName; // 英文名

    private String name; // 中文名
}
