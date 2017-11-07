package com.upic.po;

import com.upic.enums.IntegralLogStatusEnum;
import com.upic.enums.IntegralLogTypeEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by zhubuqing on 2017/8/5.
 */
@Data
@Entity
@Embeddable
public class IntegralLog implements Serializable {
    @EmbeddedId
    private IntegralLogId integralLogId;

    private String content; //项目详情

    private String event; //事件 json

    private double integral; //分数

    @Enumerated(EnumType.STRING)
    private IntegralLogTypeEnum type; //类型

    @Enumerated(EnumType.STRING)
    private IntegralLogStatusEnum status; //状态

    private String student; //学生姓名

    private String clazz; //班级

    private String college; //学院

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> integralLogPic; //图片

    @Version
    private int version;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creatTime;

    private String apartment;

    private String projectCategory;

    private String field1;

    private String field2;

    private String field3;

    private String field4;

    private String field5;

    @Temporal(TemporalType.TIMESTAMP)
    private Date addTime;

    private String projectName;

    private String collegeOtherName;
}

