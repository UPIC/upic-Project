package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.OperatorRoleStatusEnum;
import com.upic.enums.OperatorRoleTypeEnum;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class OperatorRole extends BaseEntity {
    private String jobNum; //操作员

    private long roleId;

    private OperatorRoleStatusEnum status; //状态

    private OperatorRoleTypeEnum type; //类型
}
