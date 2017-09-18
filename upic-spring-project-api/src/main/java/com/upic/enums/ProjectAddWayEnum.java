package com.upic.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public enum ProjectAddWayEnum {
    AUTO_ADD("自动添加", 1),
    MANUAL_ADDITION("手动添加", 2);

    private String content;

    private int num;

    ProjectAddWayEnum() {
    }

    ProjectAddWayEnum(String content, int num) {
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

    public static ProjectAddWayEnum getEnum(int value) {
        ProjectAddWayEnum projectAddWayEnum = null;
        ProjectAddWayEnum[] projectAddWayEnums = ProjectAddWayEnum.values();
        for (int i = 0; i < projectAddWayEnums.length; i++) {
            if (projectAddWayEnums[i].getNum() == value) {
                projectAddWayEnum = projectAddWayEnums[i];
                break;
            }
        }
        return projectAddWayEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        ProjectAddWayEnum[] projectAddWayEnums = ProjectAddWayEnum.values();
        Map<String, Map<String, Object>> stringMapHashMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < projectAddWayEnums.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(projectAddWayEnums[num].getNum()));
            map.put("num", String.valueOf(projectAddWayEnums[num].getNum()));
            map.put("content", projectAddWayEnums[num].getContent());
            stringMapHashMap.put(key, map);
        }
        return stringMapHashMap;
    }
}
