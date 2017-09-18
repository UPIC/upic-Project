package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.CategoryNodeTypeEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class CategoryNode extends BaseEntity {
    private int longNum; //占长（也许有用）

    private int widthNum; //占宽（也许有用）

    private long fatherId; //上一级ID

    private String nodeContent; //节点内容

    @Enumerated(EnumType.STRING)
    private CategoryNodeTypeEnum type; //节点类型

    private int level; //层级

    private int isLeaf; //是否叶子节点
}
