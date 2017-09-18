package com.upic.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public enum RoleStatusEnum {
    NORMAL_CONDITION("正常状态", 1),
    FROZE("已冻结", 2);

    private String content;

    private int num;

    RoleStatusEnum() {
    }

    RoleStatusEnum(String content, int num) {
        this.content = content;
        this.num = num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public static RoleStatusEnum getEnum(int value) {
        RoleStatusEnum roleStatusEnum = null;
        RoleStatusEnum[] roleStatusEnums = RoleStatusEnum.values();
        for (int i = 0; i < roleStatusEnums.length; i++) {
            if (roleStatusEnums[i].getNum() == value) {
                roleStatusEnum = roleStatusEnums[i];
                break;
            }
        }
        return roleStatusEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        RoleStatusEnum[] roleStatusEnums = RoleStatusEnum.values();
        Map<String, Map<String, Object>> stringMapHashMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < roleStatusEnums.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(roleStatusEnums[num].getNum()));
            map.put("num", String.valueOf(roleStatusEnums[num].getNum()));
            map.put("content", roleStatusEnums[num].getContent());
            stringMapHashMap.put(key, map);
        }
        return stringMapHashMap;
    }
}
