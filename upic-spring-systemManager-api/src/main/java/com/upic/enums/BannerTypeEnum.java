package com.upic.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhubuqing on 2017/9/5.
 */
public enum BannerTypeEnum {
    OUTSIDE("外部Banner", 1),
    INSIDE("内部Banner", 2);

    private String content;

    private int num;

    BannerTypeEnum() {
    }

    BannerTypeEnum(String content, int num) {
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

    public static BannerTypeEnum getEnum(int value) {
        BannerTypeEnum bannerStatusEnum = null;
        BannerTypeEnum[] bannerTypeEnums = BannerTypeEnum.values();
        for (int i = 0; i < bannerTypeEnums.length; i++) {
            if (bannerTypeEnums[i].getNum() == value) {
                bannerStatusEnum = bannerTypeEnums[i];
                break;
            }
        }
        return bannerStatusEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        BannerTypeEnum[] bannerTypeEnums = BannerTypeEnum.values();
        Map<String, Map<String, Object>> stringMapHashMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < bannerTypeEnums.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(bannerTypeEnums[num].getNum()));
            map.put("num", String.valueOf(bannerTypeEnums[num].getNum()));
            map.put("content", bannerTypeEnums[num].getContent());
            stringMapHashMap.put(key, map);
        }
        return stringMapHashMap;
    }
}
