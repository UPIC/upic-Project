package com.upic.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public enum GrainCoinLogTypeEnum {
    INCOME("收入", 100), PAYMENT("支出", 101);
    private String content;

    private int num;

    GrainCoinLogTypeEnum() {
    }

    GrainCoinLogTypeEnum(String content, int num) {
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

    public static GrainCoinLogTypeEnum getEnum(int value) {
        GrainCoinLogTypeEnum grainCoinLogTypeEnum = null;
        GrainCoinLogTypeEnum[] grainCoinLogTypeEnums = GrainCoinLogTypeEnum.values();
        for (int i = 0; i < grainCoinLogTypeEnums.length; i++) {
            if (grainCoinLogTypeEnums[i].getNum() == value) {
                grainCoinLogTypeEnum = grainCoinLogTypeEnums[i];
                break;
            }
        }
        return grainCoinLogTypeEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        GrainCoinLogTypeEnum[] grainCoinLogTypeEnums = GrainCoinLogTypeEnum.values();
        Map<String, Map<String, Object>> stringMapHashMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < grainCoinLogTypeEnums.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(grainCoinLogTypeEnums[num].getNum()));
            map.put("num", String.valueOf(grainCoinLogTypeEnums[num].getNum()));
            map.put("content", grainCoinLogTypeEnums[num].getContent());
            stringMapHashMap.put(key, map);
        }
        return stringMapHashMap;
    }
}
