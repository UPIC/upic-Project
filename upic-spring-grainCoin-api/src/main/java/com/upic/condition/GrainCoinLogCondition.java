package com.upic.condition;

import java.util.List;
import java.util.Map;

import com.upic.common.base.condition.BaseCondition;
import com.upic.enums.GrainCoinLogStatusEnum;
import com.upic.enums.GrainCoinLogTypeEnum;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class GrainCoinLogCondition extends BaseCondition {
    private String event; //消费事件

    private Double score; //消费积分

    private GrainCoinLogTypeEnum type; //消费类型

    private GrainCoinLogStatusEnum status; //消费状态

    private String username; //消费人

    private String userNum; //用户编号

    private String projectName;

    private String prizeName;

    private String projectNum;
    
    private List<Map<String,Object>> orList;
    public GrainCoinLogCondition() {
        super();
    }

    public GrainCoinLogCondition(String event, Double score, GrainCoinLogTypeEnum type, GrainCoinLogStatusEnum status, String username, String userNum, String projectName, String prizeName, String projectNum) {
        this.event = event;
        this.score = score;
        this.type = type;
        this.status = status;
        this.username = username;
        this.userNum = userNum;
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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
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
    
    public List<Map<String, Object>> getOrList() {
		return orList;
	}

	public void setOrList(List<Map<String, Object>> orList) {
		this.orList = orList;
	}
}
