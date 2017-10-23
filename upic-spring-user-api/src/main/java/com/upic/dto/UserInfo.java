package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.UserStatusEnum;
import com.upic.enums.UserTypeEnum;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class UserInfo extends BaseInfo {
    private String userNum;

    private String username;

    private String password;

    private String college;

    private String major;

    private String clazz;

    private String phone;

    private String idCard;

    private String email;

    private String pic;

    private UserStatusEnum status;

    private String nickName;

    private UserTypeEnum type;

    private double earnedPoints;

    private double earningPoints;

    public UserInfo() {
        super();
    }

    public UserInfo(String userNum, String username, String password, String college, String major, String clazz, String phone, String idCard, String email, String pic, UserStatusEnum status, String nickName, UserTypeEnum type, double earnedPoints, double earningPoints) {
        this.userNum = userNum;
        this.username = username;
        this.password = password;
        this.college = college;
        this.major = major;
        this.clazz = clazz;
        this.phone = phone;
        this.idCard = idCard;
        this.email = email;
        this.pic = pic;
        this.status = status;
        this.nickName = nickName;
        this.type = type;
        this.earnedPoints = earnedPoints;
        this.earningPoints = earningPoints;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
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

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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

    public UserStatusEnum getStatus() {
        return status;
    }

    public void setStatus(UserStatusEnum status) {
        this.status = status;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public UserTypeEnum getType() {
        return type;
    }

    public void setType(UserTypeEnum type) {
        this.type = type;
    }

    public double getEarnedPoints() {
        return earnedPoints;
    }

    public void setEarnedPoints(double earnedPoints) {
        this.earnedPoints = earnedPoints;
    }

    public double getEarningPoints() {
        return earningPoints;
    }

    public void setEarningPoints(double earningPoints) {
        this.earningPoints = earningPoints;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userNum='" + userNum + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", college='" + college + '\'' +
                ", major='" + major + '\'' +
                ", clazz='" + clazz + '\'' +
                ", phone='" + phone + '\'' +
                ", idCard='" + idCard + '\'' +
                ", email='" + email + '\'' +
                ", pic='" + pic + '\'' +
                ", status=" + status +
                ", nickName='" + nickName + '\'' +
                ", type=" + type +
                ", earnedPoints=" + earnedPoints +
                ", earningPoints=" + earningPoints +
                '}';
    }
}
