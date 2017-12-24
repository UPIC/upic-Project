package com.upic.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public enum OperatorStatusEnum {
    NORMAL_CONDITION("正常", 1),
    FROZE("已冻结", 2);

    private String content;

    private int num;

    OperatorStatusEnum() {
    }

    OperatorStatusEnum(String content, int num) {
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

    public static OperatorStatusEnum getEnum(int value) {
        OperatorStatusEnum operatorStatusEnum = null;
        OperatorStatusEnum[] operatorStatusEnums = OperatorStatusEnum.values();
        for (int i = 0; i < operatorStatusEnums.length; i++) {
            if (operatorStatusEnums[i].getNum() == value) {
                operatorStatusEnum = operatorStatusEnums[i];
                break;
            }
        }
        return operatorStatusEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        OperatorStatusEnum[] operatorStatusEnums = OperatorStatusEnum.values();
        Map<String, Map<String, Object>> stringMapHashMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < operatorStatusEnums.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(operatorStatusEnums[num].getNum()));
            map.put("num", String.valueOf(operatorStatusEnums[num].getNum()));
            map.put("content", operatorStatusEnums[num].getContent());
            stringMapHashMap.put(key, map);
        }
        return stringMapHashMap;
    }
}
