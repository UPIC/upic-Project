package com.upic.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public enum RoleTypeEnum {
    SUPER_ADMINISTRATOR("超级管理员", 1),
    GENERAL_ADMINISTRATOR("普通管理员", 2);

    private String content;

    private int num;

    RoleTypeEnum() {
    }

    RoleTypeEnum(String content, int num) {
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

    public static RoleTypeEnum getEnum(int value) {
        RoleTypeEnum roleTypeEnum = null;
        RoleTypeEnum[] roleTypeEnums = RoleTypeEnum.values();
        for (int i = 0; i < roleTypeEnums.length; i++) {
            if (roleTypeEnums[i].getNum() == value) {
                roleTypeEnum = roleTypeEnums[i];
                break;
            }
        }
        return roleTypeEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        RoleTypeEnum[] roleTypeEnums = RoleTypeEnum.values();
        Map<String, Map<String, Object>> stringMapHashMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < roleTypeEnums.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(roleTypeEnums[num].getNum()));
            map.put("num", String.valueOf(roleTypeEnums[num].getNum()));
            map.put("content", roleTypeEnums[num].getContent());
            stringMapHashMap.put(key, map);
        }
        return stringMapHashMap;
    }
}
