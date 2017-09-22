package com.upic.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhubuqing on 2017/8/5.
 */
public enum IntegralLogTypeEnum {
    SIGN_IN("报名导入", 1),
    VOLUNTARY_APPLICATION("自主申请", 2);

    private String content;

    private int num;

    IntegralLogTypeEnum() {
    }

    IntegralLogTypeEnum(String content, int num) {
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

    public static IntegralLogTypeEnum getEnum(int value) {
        IntegralLogTypeEnum integralLogTypeEnum = null;
        IntegralLogTypeEnum[] integralLogTypeEnums = IntegralLogTypeEnum.values();
        for (int i = 0; i < integralLogTypeEnums.length; i++) {
            if (integralLogTypeEnums[i].getNum() == value) {
                integralLogTypeEnum = integralLogTypeEnums[i];
                break;
            }
        }
        return integralLogTypeEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        IntegralLogTypeEnum[] integralLogTypeEnums = IntegralLogTypeEnum.values();
        Map<String, Map<String, Object>> stringMapHashMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < integralLogTypeEnums.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(integralLogTypeEnums[num].getNum()));
            map.put("num", String.valueOf(integralLogTypeEnums[num].getNum()));
            map.put("content", integralLogTypeEnums[num].getContent());
            stringMapHashMap.put(key, map);
        }
        return stringMapHashMap;
    }
}
