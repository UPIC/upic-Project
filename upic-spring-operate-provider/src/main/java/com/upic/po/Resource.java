package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.ResourceStatusEnum;
import com.upic.enums.ResourceTypeEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class Resource extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String resourceNum; //菜单编号

    private String resourceName; //菜单名称

    private String url; //URL

    @Enumerated(EnumType.STRING)
    private ResourceStatusEnum status; //状态

    @Enumerated(EnumType.STRING)
    private ResourceTypeEnum type; //类型

    private int level; //层级

    private long fatherId; //上级ID

    private int isLeaf; //是否叶子

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "resource")
    private List<RoleResource> roleResources;
}
