package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.ImplementationProcessEnum;
import com.upic.enums.ProjectAddWayEnum;
import com.upic.enums.ProjectTypeEnum;
import com.upic.enums.RankEnum;

import java.util.Date;
import java.util.List;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class ProjectInfo extends BaseInfo {
    private String projectNum; //项目编号

    private String declareUnit; //申报单位

    private String projectName; //项目名称

    private String guidanceMan; //指导人

    private String guidanceNum; //指导人编号

    private String projectCategory; //项x目类别

    private double integral; //单个积分

    private Date startTime; //活动开始时间

    private Date endTime; //活动结束时间

    private Date signUpStartTime; //报名开始时间

    private Date signUpEndTime; //报名结束时间

    private int maximum; //最大参与人数

    private String content; //项目主要内容

    private ImplementationProcessEnum implementationProcess; //项目实施进程

    private String projectImplementationProcess;

    private String checkAssessmentCriteraAndForm; //考核评价标准与形式

    private ProjectAddWayEnum projectAddWay; //项目添加方式

    private RankEnum rankEnum; //等级

    private String unit; //单位

    private int onOff; //二维码开关

    private String refreshTime; //刷新时间

    private ProjectTypeEnum type;

    private long projectCategoryId;

    private String collegeOtherName;

    public ProjectInfo() {
        super();
    }

    public ProjectInfo(String projectNum, String declareUnit, String projectName, String guidanceMan, String guidanceNum, String projectCategory, double integral, Date startTime, Date endTime, Date signUpStartTime, Date signUpEndTime, int maximum, String content, ImplementationProcessEnum implementationProcess, String checkAssessmentCriteraAndForm, ProjectAddWayEnum projectAddWay, RankEnum rankEnum, String unit, int onOff, String refreshTime, ProjectTypeEnum type, long projectCategoryId, String collegeOtherName) {
        this.projectNum = projectNum;
        this.declareUnit = declareUnit;
        this.projectName = projectName;
        this.guidanceMan = guidanceMan;
        this.guidanceNum = guidanceNum;
        this.projectCategory = projectCategory;
        this.integral = integral;
        this.startTime = startTime;
        this.endTime = endTime;
        this.signUpStartTime = signUpStartTime;
        this.signUpEndTime = signUpEndTime;
        this.maximum = maximum;
        this.content = content;
        this.implementationProcess = implementationProcess;
        this.checkAssessmentCriteraAndForm = checkAssessmentCriteraAndForm;
        this.projectAddWay = projectAddWay;
        this.rankEnum = rankEnum;
        this.unit = unit;
        this.onOff = onOff;
        this.refreshTime = refreshTime;
        this.type = type;
        this.projectCategoryId = projectCategoryId;
        this.collegeOtherName = collegeOtherName;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getDeclareUnit() {
        return declareUnit;
    }

    public void setDeclareUnit(String declareUnit) {
        this.declareUnit = declareUnit;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getGuidanceMan() {
        return guidanceMan;
    }

    public void setGuidanceMan(String guidanceMan) {
        this.guidanceMan = guidanceMan;
    }

    public String getGuidanceNum() {
        return guidanceNum;
    }

    public void setGuidanceNum(String guidanceNum) {
        this.guidanceNum = guidanceNum;
    }

    public String getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory;
    }

    public double getIntegral() {
        return integral;
    }

    public void setIntegral(double integral) {
        this.integral = integral;
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

    public Date getSignUpStartTime() {
        return signUpStartTime;
    }

    public void setSignUpStartTime(Date signUpStartTime) {
        this.signUpStartTime = signUpStartTime;
    }

    public Date getSignUpEndTime() {
        return signUpEndTime;
    }

    public void setSignUpEndTime(Date signUpEndTime) {
        this.signUpEndTime = signUpEndTime;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ImplementationProcessEnum getImplementationProcess() {
        return implementationProcess;
    }

    public void setImplementationProcess(ImplementationProcessEnum implementationProcess) {
        this.implementationProcess = implementationProcess;
    }

    public String getCheckAssessmentCriteraAndForm() {
        return checkAssessmentCriteraAndForm;
    }

    public void setCheckAssessmentCriteraAndForm(String checkAssessmentCriteraAndForm) {
        this.checkAssessmentCriteraAndForm = checkAssessmentCriteraAndForm;
    }

    public ProjectAddWayEnum getProjectAddWay() {
        return projectAddWay;
    }

    public void setProjectAddWay(ProjectAddWayEnum projectAddWay) {
        this.projectAddWay = projectAddWay;
    }

    public RankEnum getRankEnum() {
        return rankEnum;
    }

    public void setRankEnum(RankEnum rankEnum) {
        this.rankEnum = rankEnum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getOnOff() {
        return onOff;
    }

    public void setOnOff(int onOff) {
        this.onOff = onOff;
    }

    public String getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(String refreshTime) {
        this.refreshTime = refreshTime;
    }

    public ProjectTypeEnum getType() {
        return type;
    }

    public void setType(ProjectTypeEnum type) {
        this.type = type;
    }

    public long getProjectCategoryId() {
        return projectCategoryId;
    }

    public void setProjectCategoryId(long projectCategoryId) {
        this.projectCategoryId = projectCategoryId;
    }

    public String getCollegeOtherName() {
        return collegeOtherName;
    }

    public void setCollegeOtherName(String collegeOtherName) {
        this.collegeOtherName = collegeOtherName;
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "projectNum='" + projectNum + '\'' +
                ", declareUnit='" + declareUnit + '\'' +
                ", projectName='" + projectName + '\'' +
                ", guidanceMan='" + guidanceMan + '\'' +
                ", guidanceNum='" + guidanceNum + '\'' +
                ", projectCategory='" + projectCategory + '\'' +
                ", integral=" + integral +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", signUpStartTime=" + signUpStartTime +
                ", signUpEndTime=" + signUpEndTime +
                ", maximum=" + maximum +
                ", content='" + content + '\'' +
                ", implementationProcess=" + implementationProcess +
                ", checkAssessmentCriteraAndForm='" + checkAssessmentCriteraAndForm + '\'' +
                ", projectAddWay=" + projectAddWay +
                ", rankEnum=" + rankEnum +
                ", unit='" + unit + '\'' +
                ", onOff=" + onOff +
                ", refreshTime='" + refreshTime + '\'' +
                ", type=" + type +
                ", projectCategoryId=" + projectCategoryId +
                ", collegeOtherName='" + collegeOtherName + '\'' +
                '}';
    }
}
