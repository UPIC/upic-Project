package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class OrginzationProjectCategory extends BaseEntity {
    private String orginationName; //组织名

    private String orginationNum; //组织编号

    private long projectCategoryId; //项目类别ID

    private String projectCategory; //项目类别
}
