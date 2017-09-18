package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class ProjectLog extends BaseEntity {
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, targetEntity = Project.class)
    @JoinColumn(unique = true, nullable = false, updatable = false)
    private Project project; //项目

    private String operation; //操作内容

    private String operatorName; //操作人

    private String operatorNum; //操作人编号
}
