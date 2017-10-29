package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.RoleResourceStatusEnum;
import com.upic.enums.RoleResourceTypeEnum;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class RoleResource extends BaseEntity {
    private long roleId; //角色

    private String resourceNum; //菜单编号

    private RoleResourceStatusEnum status; //状态

    private RoleResourceTypeEnum type; //类型
}
