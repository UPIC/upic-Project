package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class OrginzationOperator extends BaseEntity {
    private String orginationName; //组织名

    private String orginationNum; //组织编号

    private String jobNum; //工号

    private String username; //用户名
}
