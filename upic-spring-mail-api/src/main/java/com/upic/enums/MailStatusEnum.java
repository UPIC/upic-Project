package com.upic.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public enum MailStatusEnum {
    ALREADY_ISSUED("已发出", 1),
    DRAFTS("草稿箱", 2),
    IN_REVERSION("修改中", 3);

    private String content;

    private int num;

    MailStatusEnum() {
    }

    MailStatusEnum(String content, int num) {
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

    public static MailStatusEnum getEnum(int value) {
        MailStatusEnum mailStatusEnum = null;
        MailStatusEnum[] mailStatusEnums = MailStatusEnum.values();
        for (int i = 0; i < mailStatusEnums.length; i++) {
            if (mailStatusEnums[i].getNum() == value) {
                mailStatusEnum = mailStatusEnums[i];
                break;
            }
        }
        return mailStatusEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        MailStatusEnum[] mailStatusEnums = MailStatusEnum.values();
        Map<String, Map<String, Object>> stringMapHashMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < mailStatusEnums.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(mailStatusEnums[num].getNum()));
            map.put("num", String.valueOf(mailStatusEnums[num].getNum()));
            map.put("content", mailStatusEnums[num].getContent());
            stringMapHashMap.put(key, map);
        }
        return stringMapHashMap;
    }
}
