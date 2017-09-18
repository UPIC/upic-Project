package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhubuqing on 2017/9/4.
 */
@Data
@Entity
public class ConfirmationBasis extends BaseEntity {
    private String content; //内容

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, targetEntity = CategoryNode.class)
    @JoinColumn(unique = true, nullable = false, updatable = false)
    private CategoryNode categoryNode; //项目类节点
}
