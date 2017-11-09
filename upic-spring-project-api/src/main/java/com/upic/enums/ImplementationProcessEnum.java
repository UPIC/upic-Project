package com.upic.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public enum ImplementationProcessEnum {
    SAVED("已保存", 1),
    IN_AUDIT("待初审", 2),
    IN_AUDIT_FAIL("待初审失败", 3),
    IN_AUDIT_AGAIN("待复审", 4),
    IN_AUDIT_AGAIN_FAIL("待复审失败", 5),
    IN_AUDIT_FINAL("待终审", 6),
    IN_AUDIT_FINAL_FAIL("待终审失败", 7),
    AUDITED("已审核", 8),
    ENROLLMENT("报名中", 9),
    HAVE_IN_HAND("进行中", 10),
    COMPLETED("已完成", 11),
    CHECKING("待初验", 12),
    CHECKING_FAIL("待初验失败", 13),
    CHECKING_AGAIN("待复验", 14),
    CHECKING_AGAIN_FAIL("待复验失败", 15),
    CHECKING_FINAL("待终验", 16),
    CHECKING_FINAL_FAIL("待终验失败", 17),
    CHECKED("已验收", 18);

    private String content;

    // AA

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
