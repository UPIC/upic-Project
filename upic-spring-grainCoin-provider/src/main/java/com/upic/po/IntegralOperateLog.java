package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhubuqing on 2017/9/4.
 */
@Data
@Entity
public class IntegralOperateLog extends BaseEntity {
    private String event; //操作内容

    private String finishCheckEnumName;

    private String finishCheck;

    private String operator; //操作人

    private String operatorNum; //操作人编号

//    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, targetEntity = IntegralLog.class)
//    @JoinColumn(unique = true, nullable = false, updatable = false, referencedColumnName = "integralLogId")
    private String integralLogId; //积分日志
}
