package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class ProjectCategory extends BaseEntity {
    private String categoryName; //项目类别名

    private String subordinateSector; //所属部门

    private String subordinateSectorOtherName; //部门别名
}
