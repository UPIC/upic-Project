package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;
import com.upic.enums.UserStatusEnum;
import com.upic.enums.UserTypeEnum;

import java.util.Date;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class UserCondition extends BaseCondition {
    private String userNum;

    private String username;

    private String college;

    private String major;

    private String clazz;

    private String phone;

    private String idCard;

    private String email;

    private UserStatusEnum status;

    private String nickName;

    private UserTypeEnum type;

    private Date birthday;

    private Date birthdayTo;

    private Double earnedPoints;

    private Double earnedPointsTo;

    private Double earningPoints;

    private Double earningPointsTo;

    public UserCondition() {
        super();
    }

    public UserCondition(String userNum, String username, String college, String major, String clazz, String phone, String idCard, String email, UserStatusEnum status, String nickName, UserTypeEnum type, Date birthday, Date birthdayTo, Double earnedPoints, Double earnedPointsTo, Double earningPoints, Double earningPointsTo) {
        this.userNum = userNum;
        this.username = username;
        this.college = college;
        this.major = major;
        this.clazz = clazz;
        this.phone = phone;
        this.idCard = idCard;
        this.email = email;
        this.status = status;
        this.nickName = nickName;
        this.type = type;
        this.birthday = birthday;
        this.birthdayTo = birthdayTo;
        this.earnedPoints = earnedPoints;
        this.earnedPointsTo = earnedPointsTo;
        this.earningPoints = earningPoints;
        this.earningPointsTo = earningPointsTo;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthdayTo() {
        return birthdayTo;
    }

    public void setBirthdayTo(Date birthdayTo) {
        this.birthdayTo = birthdayTo;
    }

    public Double getEarnedPoints() {
        return earnedPoints;
    }

    public void setEarnedPoints(double earnedPoints) {
        this.earnedPoints = earnedPoints;
    }

    public Double getEarnedPointsTo() {
        return earnedPointsTo;
    }

    public void setEarnedPointsTo(double earnedPointsTo) {
        this.earnedPointsTo = earnedPointsTo;
    }

    public Double getEarningPoints() {
        return earningPoints;
    }

    public void setEarningPoints(double earningPoints) {
        this.earningPoints = earningPoints;
    }

    public Double getEarningPointsTo() {
        return earningPointsTo;
    }

    public void setEarningPointsTo(double earningPointsTo) {
        this.earningPointsTo = earningPointsTo;
    }
}
