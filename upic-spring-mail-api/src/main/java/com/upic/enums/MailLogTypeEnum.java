package com.upic.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public enum MailLogTypeEnum {
    SCHOOL("学校", 1),
    COLLEGE("学院", 2);

    private String content;

    private int num;

    MailLogTypeEnum() {
    }

    MailLogTypeEnum(String content, int num) {
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

    public static MailLogTypeEnum getEnum(int value) {
        MailLogTypeEnum mailLogTypeEnum = null;
        MailLogTypeEnum[] mailLogTypeEnums = MailLogTypeEnum.values();
        for (int i = 0; i < mailLogTypeEnums.length; i++) {
            if (mailLogTypeEnums[i].getNum() == value) {
                mailLogTypeEnum = mailLogTypeEnums[i];
                break;
            }
        }
        return mailLogTypeEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        MailLogTypeEnum[] mailLogTypeEnums = MailLogTypeEnum.values();
        Map<String, Map<String, Object>> stringMapHashMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < mailLogTypeEnums.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(mailLogTypeEnums[num].getNum()));
            map.put("num", String.valueOf(mailLogTypeEnums[num].getNum()));
            map.put("content", mailLogTypeEnums[num].getContent());
            stringMapHashMap.put(key, map);
        }
        return stringMapHashMap;
    }
}
