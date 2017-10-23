package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.ImplementationProcessEnum;
import com.upic.enums.ProjectAddWayEnum;
import com.upic.enums.ProjectTypeEnum;
import com.upic.enums.RankEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class Project extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String projectNum; //项目编号

    private String declareUnit; //申报单位

    private String projectName; //项目名称

    private String guidanceMan; //指导人

    private String guidanceNum; //指导人编号

    private String projectCategory; //项x目类别

    @Enumerated(EnumType.STRING)
    private RankEnum rankEnum; //等级（学校级别、学院级别等）

    private String unit; //单位

    private double integral; //单个积分

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime; //开始时间

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime; //结束时间

    @Temporal(TemporalType.TIMESTAMP)
    private Date signUpStartTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date signUpEndTime;

    private int maximum; //最大参与人数

    private int onOff; //二维码开关

    private String refreshTime; //刷新时间

    private String content; //项目主要内容

    @Enumerated(EnumType.STRING)
    private ImplementationProcessEnum implementationProcess; //项目实施进程

    @Enumerated(EnumType.STRING)
    private ProjectTypeEnum type;

    private long projectCategoryId;

    private String checkAssessmentCriteraAndForm; //考核评价标准与形式

    @Enumerated(EnumType.STRING)
    private ProjectAddWayEnum projectAddWay; //项目添加方式

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "project")
    private List<ProjectLog> projectLogs;
}
