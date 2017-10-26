package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.MailStatusEnum;
import com.upic.enums.MailTypeEnum;

import java.util.Date;
import java.util.List;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class MailInfo extends BaseInfo {
    private String mailContent; //内容

    private MailTypeEnum type; //类型

    private String mailer; //发件人

    private String mailNum; //发件人编号

    private String title; //标题

    private String target; //目标

    private MailStatusEnum status; //状态

    private List<MailLogInfo> mailLogs;

    public MailInfo() {
        super();
    }

    public MailInfo(Long id, Date creatTime, String field1, String field2, String field3, String field4, String field5, String mailContent, MailTypeEnum type, String mailer, String mailNum, String title, String target, MailStatusEnum status, List<MailLogInfo> mailLogs) {
        this.mailContent = mailContent;
        this.type = type;
        this.mailer = mailer;
        this.mailNum = mailNum;
        this.title = title;
        this.target = target;
        this.status = status;
        this.mailLogs = mailLogs;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public MailTypeEnum getType() {
        return type;
    }

    public void setType(MailTypeEnum type) {
        this.type = type;
    }

    public String getMailer() {
        return mailer;
    }

    public void setMailer(String mailer) {
        this.mailer = mailer;
    }

    public String getMailNum() {
        return mailNum;
    }

    public void setMailNum(String mailNum) {
        this.mailNum = mailNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public MailStatusEnum getStatus() {
        return status;
    }

    public void setStatus(MailStatusEnum status) {
        this.status = status;
    }

    public List<MailLogInfo> getMailLogs() {
        return mailLogs;
    }

    public void setMailLogs(List<MailLogInfo> mailLogs) {
        this.mailLogs = mailLogs;
    }

    @Override
    public String toString() {
        return "MailInfo{" +
                "mailContent='" + mailContent + '\'' +
                ", type=" + type +
                ", mailer='" + mailer + '\'' +
                ", mailNum='" + mailNum + '\'' +
                ", title='" + title + '\'' +
                ", target='" + target + '\'' +
                ", status=" + status +
                '}';
    }
}
