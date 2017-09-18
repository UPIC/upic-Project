package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.OperateLogStatusEnum;
import com.upic.enums.OperateLogTypeEnum;
import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by zhubuqing on 2017/9/5.
 */
@Data
@Entity
public class OperateLog extends BaseEntity {
    private String operator; //操作员

    private String operatorNum; //工号

    private String ipAddress; //IP

    private String content; //操作内容

    private OperateLogTypeEnum type;

    private OperateLogStatusEnum status;
}
