package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.MenuStatusEnum;
import com.upic.enums.MenuTypeEnum;

import java.util.Date;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class MenuInfo extends BaseInfo {
    private String menu; //菜单

    private String url; //URL

    private MenuStatusEnum status; //状态

    private MenuTypeEnum type; //类型

    public MenuInfo() {
        super();
    }

    public MenuInfo(Long id, Date creatTime, String field1, String field2, String field3, String field4, String field5, String menu, String url, MenuStatusEnum status, MenuTypeEnum type) {
        super();
        this.menu = menu;
        this.url = url;
        this.status = status;
        this.type = type;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MenuStatusEnum getStatus() {
        return status;
    }

    public void setStatus(MenuStatusEnum status) {
        this.status = status;
    }

    public MenuTypeEnum getType() {
        return type;
    }

    public void setType(MenuTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MenuInfo{" +
                "menu='" + menu + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
