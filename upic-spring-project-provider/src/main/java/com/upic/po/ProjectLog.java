package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class ProjectLog extends BaseEntity {
    private long projectId;

    private String operation; //操作内容

    private String operatorName; //操作人

    private String operatorNum; //操作人编号
}
