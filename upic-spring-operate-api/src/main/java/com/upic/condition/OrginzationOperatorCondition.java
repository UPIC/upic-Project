package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class OrginzationOperatorCondition extends BaseCondition {
    private String orginationName; //组织名

    private String orginationNum; //组织编号

    private String jobNum; //工号

    private String username; //用户名

    public OrginzationOperatorCondition() {
        super();
    }

    public OrginzationOperatorCondition(String orginationName, String orginationNum, String jobNum, String username) {
        this.orginationName = orginationName;
        this.orginationNum = orginationNum;
        this.jobNum = jobNum;
        this.username = username;
    }

    public String getOrginationName() {
        return orginationName;
    }

    public void setOrginationName(String orginationName) {
        this.orginationName = orginationName;
    }

    public String getOrginationNum() {
        return orginationNum;
    }

    public void setOrginationNum(String orginationNum) {
        this.orginationNum = orginationNum;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
