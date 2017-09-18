package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.MenuStatusEnum;
import com.upic.enums.MenuTypeEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class Menu extends BaseEntity {
    private String menu; //菜单

    private String url; //URL

    @Enumerated(EnumType.STRING)
    private MenuStatusEnum status; //状态

    @Enumerated(EnumType.STRING)
    private MenuTypeEnum type; //类型
}
