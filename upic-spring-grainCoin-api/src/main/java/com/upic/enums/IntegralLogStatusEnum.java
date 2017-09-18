package com.upic.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhubuqing on 2017/8/5.
 */
public enum IntegralLogStatusEnum {
    PENDING_AUDIT("待审核", 1),
    HAVEPASSED("已通过", 2),
    FAILURE_TO_PASS_THE_AUDIT("未通过审核", 3),

    ALREADY_SIGN_UP("已报名", 4),
    COMPLETED("已完成", 5);

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
