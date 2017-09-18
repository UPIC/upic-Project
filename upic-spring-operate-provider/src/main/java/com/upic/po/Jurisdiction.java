package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class Jurisdiction extends BaseEntity {
    private String jurisdictionName; //权限名称

    private long resourceId; //菜单ID

    private String remark; //备注

    private String url; //URL

    private String status; //状态

    private String type; // 类型

    @OneToMany(mappedBy = "jurisdiction")
    private List<RoleJurisdiction> roleJurisdictions;
}
