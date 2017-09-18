package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.OperatorStatusEnum;

import java.util.List;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class OperatorInfo extends BaseInfo {
    private String jobNum; //工号

    private String username; //用户名

    private String password; //密码

    private String email; //邮箱

    private String pic; //头像

    private OperatorStatusEnum status; //状态

    private String phone; //手机号

    private String idcard; //身份证

    private int type; //

    private List<OperatorRoleInfo> operatorRoles;

    public OperatorInfo() {
        super();
    }

    public OperatorInfo(String jobNum, String username, String password, String email, String pic, OperatorStatusEnum status, String phone, String idcard, int type, List<OperatorRoleInfo> operatorRoles) {
        super();
        this.jobNum = jobNum;
        this.username = username;
        this.password = password;
        this.email = email;
        this.pic = pic;
        this.status = status;
        this.phone = phone;
        this.idcard = idcard;
        this.type = type;
        this.operatorRoles = operatorRoles;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public OperatorStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OperatorStatusEnum status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<OperatorRoleInfo> getOperatorRoles() {
        return operatorRoles;
    }

    public void setOperatorRoles(List<OperatorRoleInfo> operatorRoles) {
        this.operatorRoles = operatorRoles;
    }

    @Override
    public String toString() {
        return "OperatorInfo{" +
                "jobNum='" + jobNum + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", pic='" + pic + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", idcard='" + idcard + '\'' +
                ", type=" + type +
                '}';
    }
}
