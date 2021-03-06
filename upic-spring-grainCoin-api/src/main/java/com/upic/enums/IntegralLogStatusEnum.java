package com.upic.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhubuqing on 2017/8/5.
 */
public enum IntegralLogStatusEnum {
    SAVE("保存", 1),
    PENDING_AUDIT_BEFORE("待初审", 2),
    PENDING_AUDIT_BEFORE_FAIL("待初审失败", 3),
    PENDING_AUDIT("待学院审", 4),
    PENDING_AUDIT_FAIL("待学院审失败", 5),
    PENDING_AUDIT_AGAIN("待部门审", 6),
    PENDING_AUDIT_AGAIN_FAIL("待部门审失败", 7),
    PENDING_AUDIT_FINAL("待团委审", 8),
    PENDING_AUDIT_FINAL_FAIL("待团委审失败", 9),
    HAVEPASSED("审核成功", 10),

    ALREADY_SIGN_UP("已报名", 11),
    SIGNED_IN("已签到", 12),
    COMPLETED("已完成", 13);

    private String content;

    private int num;

    IntegralLogStatusEnum() {
    }

    IntegralLogStatusEnum(String content, int num) {
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

    public static IntegralLogStatusEnum getEnum(int value) {
        IntegralLogStatusEnum integralLogStatusEnum = null;
        IntegralLogStatusEnum[] integralLogStatusEnums = IntegralLogStatusEnum.values();
        for (int i = 0; i < integralLogStatusEnums.length; i++) {
            if (integralLogStatusEnums[i].getNum() == value) {
                integralLogStatusEnum = integralLogStatusEnums[i];
                break;
            }
        }
        return integralLogStatusEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        IntegralLogStatusEnum[] integralLogStatusEnums = IntegralLogStatusEnum.values();
        Map<String, Map<String, Object>> stringMapHashMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < integralLogStatusEnums.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(integralLogStatusEnums[num].getNum()));
            map.put("num", String.valueOf(integralLogStatusEnums[num].getNum()));
            map.put("content", integralLogStatusEnums[num].getContent());
            stringMapHashMap.put(key, map);
        }
        return stringMapHashMap;
    }
}
