package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class ProjectCategoryInfo extends BaseInfo {
    private String categoryName; //项目类别名

    private String subordinateSector; //所属部门

    public ProjectCategoryInfo() {
        super();
    }

    public ProjectCategoryInfo(String categoryName, String subordinateSector) {
        super();
        this.categoryName = categoryName;
        this.subordinateSector = subordinateSector;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubordinateSector() {
        return subordinateSector;
    }

    public void setSubordinateSector(String subordinateSector) {
        this.subordinateSector = subordinateSector;
    }

    @Override
    public String toString() {
        return "ProjectCategoryInfo{" +
                "categoryName='" + categoryName + '\'' +
                ", subordinateSector='" + subordinateSector + '\'' +
                '}';
    }
}
