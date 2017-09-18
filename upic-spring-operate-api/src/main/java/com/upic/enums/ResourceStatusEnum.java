package com.upic.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public enum ResourceStatusEnum {
    NORMAL_CONDITION("正常状态", 1),
    FROZE("已冻结", 2);

    private String content;

    private int num;

    ResourceStatusEnum() {
    }

    ResourceStatusEnum(String content, int num) {
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

    public static ResourceStatusEnum getEnum(int value) {
        ResourceStatusEnum resourceStatusEnum = null;
        ResourceStatusEnum[] resourceStatusEnums = ResourceStatusEnum.values();
        for (int i = 0; i < resourceStatusEnums.length; i++) {
            if (resourceStatusEnums[i].getNum() == value) {
                resourceStatusEnum = resourceStatusEnums[i];
                break;
            }
        }
        return resourceStatusEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        ResourceStatusEnum[] resourceStatusEnums = ResourceStatusEnum.values();
        Map<String, Map<String, Object>> stringMapHashMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < resourceStatusEnums.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(resourceStatusEnums[num].getNum()));
            map.put("num", String.valueOf(resourceStatusEnums[num].getNum()));
            map.put("content", resourceStatusEnums[num].getContent());
            stringMapHashMap.put(key, map);
        }
        return stringMapHashMap;
    }
}
