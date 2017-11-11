package com.upic.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhubuqing on 2017/9/13.
 */
public enum RankEnum {
    SCHOOL("学校", 1),APARTMENT("部门",2), COLLEGE("学院", 3), MAJOR("专业", 4), CLAZZ("班级", 5);
    private String content;

    private int num;

    RankEnum() {
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

    RankEnum(String content, int num) {
        this.content = content;
        this.num = num;
    }

    public static RankEnum getEnum(int value) {
        RankEnum mailTypeEnum = null;
        RankEnum[] mailTypeEnums = RankEnum.values();
        for (int i = 0; i < mailTypeEnums.length; i++) {
            if (mailTypeEnums[i].getNum() == value) {
                mailTypeEnum = mailTypeEnums[i];
                break;
            }
        }
        return mailTypeEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        RankEnum[] mailTypeEnums = RankEnum.values();
        Map<String, Map<String, Object>> stringMapHashMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < mailTypeEnums.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(mailTypeEnums[num].getNum()));
            map.put("num", String.valueOf(mailTypeEnums[num].getNum()));
            map.put("content", mailTypeEnums[num].getContent());
            stringMapHashMap.put(key, map);
        }
        return stringMapHashMap;
    }
}
