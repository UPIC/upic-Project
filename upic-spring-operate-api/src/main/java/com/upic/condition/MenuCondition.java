package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;
import com.upic.enums.MenuStatusEnum;
import com.upic.enums.MenuTypeEnum;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class MenuCondition extends BaseCondition {
    private String menu; //菜单

    private String url; //URL

    private MenuStatusEnum status; //状态

    private MenuTypeEnum type; //类型

    public MenuCondition() {
        super();
    }

    public MenuCondition(String menu, String url, MenuStatusEnum status, MenuTypeEnum type) {
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
}
