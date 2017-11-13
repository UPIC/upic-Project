package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;
import com.upic.enums.ImplementationProcessEnum;
import com.upic.enums.ProjectAddWayEnum;
import com.upic.enums.ProjectTypeEnum;
import com.upic.enums.RankEnum;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class ProjectCondition extends BaseCondition {
    private String projectNum; //项目编号

    private String declareUnit; //申报单位

    private String projectName; //项目名称

    private String guidanceMan; //指导人

    private String guidanceNum; //指导人编号

    private String projectCategory; //项x目类别

    private Double integral; //单个积分

    private Double integralTo; //单个积分

    private Date startTime; //开始时间

    private Date startTimeTo; //开始时间

    private Date endTime; //结束时间

    private Date endTimeTo; //结束时间

    private Date signUpStartTime;

    private Date signUpStartTimeTo;

    private Date signUpEndTime;

    private Date signUpEndTimeTo;

    private Integer maximum; //最大参与人数

    private Integer maximumTo; //最大参与人数

    private String content; //项目主要内容

    private ImplementationProcessEnum implementationProcess; //项目实施进程

    private String checkAssessmentCriteraAndForm; //考核评价标准与形式

    private ProjectAddWayEnum projectAddWay; //项目添加方式

    private RankEnum rankEnum; //级别

    private String unit;

    private Integer onOff; //二维码开关

    private String refreshTime; //刷新时间

    private ProjectTypeEnum type;

    private Long projectCategoryId;

    private String collegeOtherName;

    private String projectImplementationProcess;

    private List<Map<String,Object>> orList;
    public ProjectCondition() {
        super();
    }

    public ProjectCondition(String projectNum, String declareUnit, String projectName, String guidanceMan, String guidanceNum, String projectCategory, Double integral, Double integralTo, Date startTime, Date startTimeTo, Date endTime, Date endTimeTo, Date signUpStartTime, Date signUpStartTimeTo, Date signUpEndTime, Date signUpEndTimeTo, Integer maximum, Integer maximumTo, String content, ImplementationProcessEnum implementationProcess, String checkAssessmentCriteraAndForm, ProjectAddWayEnum projectAddWay, RankEnum rankEnum, String unit, Integer onOff, String refreshTime, ProjectTypeEnum type, Long projectCategoryId, String collegeOtherName, String projectImplementationProcess) {
        this.projectNum = projectNum;
        this.declareUnit = declareUnit;
        this.projectName = projectName;
        this.guidanceMan = guidanceMan;
        this.guidanceNum = guidanceNum;
        this.projectCategory = projectCategory;
        this.integral = integral;
        this.integralTo = integralTo;
        this.startTime = startTime;
        this.startTimeTo = startTimeTo;
        this.endTime = endTime;
        this.endTimeTo = endTimeTo;
        this.signUpStartTime = signUpStartTime;
        this.signUpStartTimeTo = signUpStartTimeTo;
        this.signUpEndTime = signUpEndTime;
        this.signUpEndTimeTo = signUpEndTimeTo;
        this.maximum = maximum;
        this.maximumTo = maximumTo;
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
        this.projectImplementationProcess = projectImplementationProcess;
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

    public Double getIntegral() {
        return integral;
    }

    public void setIntegral(Double integral) {
        this.integral = integral;
    }

    public Double getIntegralTo() {
        return integralTo;
    }

    public void setIntegralTo(Double integralTo) {
        this.integralTo = integralTo;
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

    public Date getSignUpStartTime() {
        return signUpStartTime;
    }

    public void setSignUpStartTime(Date signUpStartTime) {
        this.signUpStartTime = signUpStartTime;
    }

    public Date getSignUpStartTimeTo() {
        return signUpStartTimeTo;
    }

    public void setSignUpStartTimeTo(Date signUpStartTimeTo) {
        this.signUpStartTimeTo = signUpStartTimeTo;
    }

    public Date getSignUpEndTime() {
        return signUpEndTime;
    }

    public void setSignUpEndTime(Date signUpEndTime) {
        this.signUpEndTime = signUpEndTime;
    }

    public Date getSignUpEndTimeTo() {
        return signUpEndTimeTo;
    }

    public void setSignUpEndTimeTo(Date signUpEndTimeTo) {
        this.signUpEndTimeTo = signUpEndTimeTo;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    public Integer getMaximumTo() {
        return maximumTo;
    }

    public void setMaximumTo(Integer maximumTo) {
        this.maximumTo = maximumTo;
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

    public Integer getOnOff() {
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

    public Long getProjectCategoryId() {
        return projectCategoryId;
    }

    public void setProjectCategoryId(long projectCategoryId) {
        this.projectCategoryId = projectCategoryId;
    }

    public void setOnOff(Integer onOff) {
        this.onOff = onOff;
    }

    public void setProjectCategoryId(Long projectCategoryId) {
        this.projectCategoryId = projectCategoryId;
    }

    public String getCollegeOtherName() {
        return collegeOtherName;
    }

    public void setCollegeOtherName(String collegeOtherName) {
        this.collegeOtherName = collegeOtherName;
    }

    public String getProjectImplementationProcess() {
        return projectImplementationProcess;
    }

    public void setProjectImplementationProcess(String projectImplementationProcess) {
        this.projectImplementationProcess = projectImplementationProcess;
    }

	public List<Map<String, Object>> getOrList() {
		return orList;
	}

	public void setOrList(List<Map<String, Object>> orList) {
		this.orList = orList;
	}
    
}
