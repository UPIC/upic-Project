package com.upic.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public enum GrainCoinLogStatusEnum {
    HAVEDONE("已完成", 100);

    private String content;

    private int num;

    GrainCoinLogStatusEnum() {
    }

    GrainCoinLogStatusEnum(String content, int num) {
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

    public static GrainCoinLogStatusEnum getEnum(int value) {
        GrainCoinLogStatusEnum grainCoinLogStatusEnum = null;
        GrainCoinLogStatusEnum[] grainCoinLogStatusEnums = GrainCoinLogStatusEnum.values();
        for (int i = 0; i < grainCoinLogStatusEnums.length; i++) {
            if (grainCoinLogStatusEnums[i].getNum() == value) {
                grainCoinLogStatusEnum = grainCoinLogStatusEnums[i];
                break;
            }
        }
        return grainCoinLogStatusEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        GrainCoinLogStatusEnum[] grainCoinLogStatusEnums = GrainCoinLogStatusEnum.values();
        Map<String, Map<String, Object>> stringMapHashMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < grainCoinLogStatusEnums.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(grainCoinLogStatusEnums[num].getNum()));
            map.put("num", String.valueOf(grainCoinLogStatusEnums[num].getNum()));
            map.put("content", grainCoinLogStatusEnums[num].getContent());
            stringMapHashMap.put(key, map);
        }
        return stringMapHashMap;
    }
}
