package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.MailLogTypeEnum;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class MailLog extends BaseEntity {
    private String operator; //操作人

    private String operatorNum; //操作人编号

    private String operation; //操作内容

    @Enumerated(EnumType.STRING)
    private MailLogTypeEnum type; //操作类型

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, targetEntity = Mail.class)
    @JoinColumn(unique = true, nullable = false, updatable = false)
    private Mail mail;
}
