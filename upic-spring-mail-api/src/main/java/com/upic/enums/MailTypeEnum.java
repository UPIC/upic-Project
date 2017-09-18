package com.upic.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public enum MailTypeEnum {
    SCHOOL("学校", 1),
    COLLEGE("学院", 2);

    private String content;

    private int num;

    MailTypeEnum() {
    }

    MailTypeEnum(String content, int num) {
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

    public static MailTypeEnum getEnum(int value) {
        MailTypeEnum mailTypeEnum = null;
        MailTypeEnum[] mailTypeEnums = MailTypeEnum.values();
        for (int i = 0; i < mailTypeEnums.length; i++) {
            if (mailTypeEnums[i].getNum() == value) {
                mailTypeEnum = mailTypeEnums[i];
                break;
            }
        }
        return mailTypeEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        MailTypeEnum[] mailTypeEnums = MailTypeEnum.values();
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
