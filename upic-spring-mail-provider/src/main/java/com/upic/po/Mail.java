package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.MailStatusEnum;
import com.upic.enums.MailTypeEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class Mail extends BaseEntity {
    private String mailContent; //内容

    @Enumerated(EnumType.STRING)
    private MailTypeEnum type; //类型

    private String mailer; //发件人

    private String mailNum; //发件人编号

    private String title; //标题

    private String target; //目标

    @Enumerated(EnumType.STRING)
    private MailStatusEnum status; //状态

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "mail")
    private List<MailLog> mailLogs;
}
