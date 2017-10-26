package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class RoleCheckStatus extends BaseEntity {
    private String roleName; //角色名

    private String enumName;

    private String checkStatusName;

    private int num;

    private long roleId;
}
