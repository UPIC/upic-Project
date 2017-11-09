package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.GrainCoinLogStatusEnum;
import com.upic.enums.GrainCoinLogTypeEnum;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class GrainCoinLogInfo extends BaseInfo {
    private String event; //消费事件

    private double score; //消费积分

    private GrainCoinLogTypeEnum type; //消费类型

    private GrainCoinLogStatusEnum status; //消费状态

    private String username; //消费人

    private String userNum; //用户编号

    private long prizeId;

    private String projectName;

    private String prizeName;

    private String projectNum;

    public GrainCoinLogInfo() {
    }

    public GrainCoinLogInfo(String event, double score, GrainCoinLogTypeEnum type, GrainCoinLogStatusEnum status, String username, String userNum, long prizeId, String projectName, String prizeName, String projectNum) {
        this.event = event;
        this.score = score;
        this.type = type;
        this.status = status;
        this.username = username;
        this.userNum = userNum;
        this.prizeId = prizeId;
        this.projectName = projectName;
        this.prizeName = prizeName;
        this.projectNum = projectNum;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public GrainCoinLogTypeEnum getType() {
        return type;
    }

    public void setType(GrainCoinLogTypeEnum type) {
        this.type = type;
    }

    public GrainCoinLogStatusEnum getStatus() {
        return status;
    }

    public void setStatus(GrainCoinLogStatusEnum status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(long prizeId) {
        this.prizeId = prizeId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    @Override
    public String toString() {
        return "GrainCoinLogInfo{" +
                "event='" + event + '\'' +
                ", score=" + score +
                ", type=" + type +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", userNum='" + userNum + '\'' +
                ", prizeId=" + prizeId +
                ", projectName='" + projectName + '\'' +
                ", prizeName='" + prizeName + '\'' +
                ", projectNum='" + projectNum + '\'' +
                '}';
    }
}
