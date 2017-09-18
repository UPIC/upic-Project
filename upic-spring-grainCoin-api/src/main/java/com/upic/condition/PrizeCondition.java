package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;
import com.upic.enums.PrizeStatusEnum;

import java.util.Date;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class PrizeCondition extends BaseCondition {
    private String prizeName; //奖品名

    private Double score; //消费分数

    private String title; //标题

    private String content; //奖品内容

    private PrizeStatusEnum status; //奖品状态

    private Date startTime; //开始时间

    private Date startTimeTo; //开始时间

    private Date endTime; //结束时间

    private Date endTimeTo; //结束时间

    public PrizeCondition() {
        super();
    }

    public PrizeCondition(String prizeName, Double score, String title, String content, PrizeStatusEnum status, Date startTime, Date startTimeTo, Date endTime, Date endTimeTo) {
        super();
        this.prizeName = prizeName;
        this.score = score;
        this.title = title;
        this.content = content;
        this.status = status;
        this.startTime = startTime;
        this.startTimeTo = startTimeTo;
        this.endTime = endTime;
        this.endTimeTo = endTimeTo;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PrizeStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PrizeStatusEnum status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTimeTo() {
        return startTimeTo;
    }

    public void setStartTimeTo(Date startTimeTo) {
        this.startTimeTo = startTimeTo;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTimeTo() {
        return endTimeTo;
    }

    public void setEndTimeTo(Date endTimeTo) {
        this.endTimeTo = endTimeTo;
    }
}
