package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.SystemAdministrationLogTypeEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class SystemAdministrationLog extends BaseEntity {
    private String operation; //操作内容

    private String operatorName; //操作人

    private String operatorNum; //操作人编号

    @Enumerated(EnumType.STRING)
    private SystemAdministrationLogTypeEnum type; //操作类型
}
