package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.AdviceStatusOperationEnum;
import com.upic.enums.AdviceTypeEnum;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhubuqing on 2017/9/4.
 */
@Data
@Entity
public class Advice extends BaseEntity {
    private String advice; //建议

    private String operator; //操作人

    private String operatorNum; //操作人编号

    @Enumerated(EnumType.STRING)
    private AdviceStatusOperationEnum statusOperation; //操作结果

    @Enumerated(EnumType.STRING)
    private AdviceTypeEnum type; //类型

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, targetEntity = Project.class)
    @JoinColumn(unique = true, nullable = false, updatable = false)
    private Project project;
}
