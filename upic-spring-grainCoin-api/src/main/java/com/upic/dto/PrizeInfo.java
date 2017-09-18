package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.PrizeStatusEnum;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class PrizeInfo extends BaseInfo {
    private String prizeName; //奖品名

    private double score; //消费分数

    private List<String> prizePic; //图片

    private String title; //标题

    private String content; //奖品内容

    private PrizeStatusEnum status; //奖品状态

    private String remark; //备注

    private GrainCoinLogInfo grainCoinLog; //素拓币消费日志

    private Date startTime; //开始时间

    private Date endTime; //结束时间

    public PrizeInfo() {
    }

    public PrizeInfo(String prizeName, double score, List<String> prizePic, String title, String content, PrizeStatusEnum status, String remark, GrainCoinLogInfo grainCoinLog, Date startTime, Date endTime) {
        this.prizeName = prizeName;
        this.score = score;
        this.prizePic = prizePic;
        this.title = title;
        this.content = content;
        this.status = status;
        this.remark = remark;
        this.grainCoinLog = grainCoinLog;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<String> getPrizePic() {
        return prizePic;
    }

    public void setPrizePic(List<String> prizePic) {
        this.prizePic = prizePic;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public GrainCoinLogInfo getGrainCoinLog() {
        return grainCoinLog;
    }

    public void setGrainCoinLog(GrainCoinLogInfo grainCoinLog) {
        this.grainCoinLog = grainCoinLog;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "PrizeInfo{" +
                "prizeName='" + prizeName + '\'' +
                ", score=" + score +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", grainCoinLog=" + grainCoinLog +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
