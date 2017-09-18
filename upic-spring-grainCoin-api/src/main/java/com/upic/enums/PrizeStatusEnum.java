package com.upic.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public enum PrizeStatusEnum {
    SHELVES("已上架", 1),
    ALREADY_LAID("已下架", 2);

    private String content;

    private int num;

    PrizeStatusEnum() {
    }

    PrizeStatusEnum(String content, int num) {
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

    public static PrizeStatusEnum getEnum(int value) {
        PrizeStatusEnum prizeStatusEnum = null;
        PrizeStatusEnum[] prizeStatusEnums = PrizeStatusEnum.values();
        for (int i = 0; i < prizeStatusEnums.length; i++) {
            if (prizeStatusEnums[i].getNum() == value) {
                prizeStatusEnum = prizeStatusEnums[i];
                break;
            }
        }
        return prizeStatusEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        PrizeStatusEnum[] prizeStatusEnums = PrizeStatusEnum.values();
        Map<String, Map<String, Object>> stringMapHashMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < prizeStatusEnums.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(prizeStatusEnums[num].getNum()));
            map.put("num", String.valueOf(prizeStatusEnums[num].getNum()));
            map.put("content", prizeStatusEnums[num].getContent());
            stringMapHashMap.put(key, map);
        }
        return stringMapHashMap;
    }
}
