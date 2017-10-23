package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.OrginzationStatusEnum;
import com.upic.enums.OrginzationTypeEnum;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class OrginzationProjectCategoryInfo extends BaseInfo {
    private String orginationName; //组织名

    private String orginationNum; //组织编号

    private long projectCategoryId; //项目类别ID

    private String projectCategory; //项目类别

    public OrginzationProjectCategoryInfo() {
        super();
    }

    public OrginzationProjectCategoryInfo(String orginationName, String orginationNum, long projectCategoryId, String projectCategory) {
        this.orginationName = orginationName;
        this.orginationNum = orginationNum;
        this.projectCategoryId = projectCategoryId;
        this.projectCategory = projectCategory;
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

    public long getProjectCategoryId() {
        return projectCategoryId;
    }

    public void setProjectCategoryId(long projectCategoryId) {
        this.projectCategoryId = projectCategoryId;
    }

    public String getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory;
    }

    @Override
    public String toString() {
        return "OrginzationProjectCategoryInfo{" +
                "orginationName='" + orginationName + '\'' +
                ", orginationNum='" + orginationNum + '\'' +
                ", projectCategoryId=" + projectCategoryId +
                ", projectCategory='" + projectCategory + '\'' +
                '}';
    }
}
