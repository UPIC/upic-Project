package com.upic.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public enum ImplementationProcessEnum {
    SAVED("已保存", 1),
    IN_AUDIT("审核中", 2),
    AUDITED("已审核", 3),
    NOT_PASS("未通过", 4),
    ENROLLMENT("报名中", 5),
    HAVE_IN_HAND("进行中", 6),
    COMPLETED("已完成", 7),
    CHECKED("已验收", 8);

    private String content;

    private int num;

    ImplementationProcessEnum() {
    }

    ImplementationProcessEnum(String content, int num) {
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

    public static ImplementationProcessEnum getEnum(int value) {
        ImplementationProcessEnum implementationProcessEnum = null;
        ImplementationProcessEnum[] implementationProcessEnums = ImplementationProcessEnum.values();
        for (int i = 0; i < implementationProcessEnums.length; i++) {
            if (implementationProcessEnums[i].getNum() == value) {
                implementationProcessEnum = implementationProcessEnums[i];
                break;
            }
        }
        return implementationProcessEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        ImplementationProcessEnum[] implementationProcessEnums = ImplementationProcessEnum.values();
        Map<String, Map<String, Object>> stringMapHashMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < implementationProcessEnums.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(implementationProcessEnums[num].getNum()));
            map.put("num", String.valueOf(implementationProcessEnums[num].getNum()));
            map.put("content", implementationProcessEnums[num].getContent());
            stringMapHashMap.put(key, map);
        }
        return stringMapHashMap;
    }
}
