package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.OperatorRoleStatusEnum;
import com.upic.enums.OperatorRoleTypeEnum;

import java.util.Date;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class OperatorRoleInfo extends BaseInfo {
    private String jobNum; //操作员

    private long roleId;

    private OperatorRoleStatusEnum status; //状态

    private OperatorRoleTypeEnum type; //类型

    public OperatorRoleInfo() {
        super();
    }

    public OperatorRoleInfo(String jobNum, long roleId, OperatorRoleStatusEnum status, OperatorRoleTypeEnum type) {
        this.jobNum = jobNum;
        this.roleId = roleId;
        this.status = status;
        this.type = type;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public OperatorRoleStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OperatorRoleStatusEnum status) {
        this.status = status;
    }

    public OperatorRoleTypeEnum getType() {
        return type;
    }

    public void setType(OperatorRoleTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "OperatorRoleInfo{" +
                "jobNum='" + jobNum + '\'' +
                ", roleId=" + roleId +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
