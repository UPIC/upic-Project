package com.upic.common.utils.excel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhubuqing on 2018/2/5.
 */
public class Correspondence {
    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("projectNum", "项目编号");
        map.put("declareUnit", "申报单位");
        map.put("projectName", "项目名称");
        map.put("guidanceMan", "指导教师");
        map.put("guidanceNum", "申报教师编号");
        map.put("projectCategory", "项目类别");
        map.put("integral", "项目积分");
        map.put("startTime", "开始时间");
        map.put("endTime", "结束时间");
        map.put("maximum", "最大人数");
        map.put("college", "所属学院");
        map.put("clazz", "所属班级");
        map.put("userNum", "用户编号");
        map.put("username", "用户姓名");
        map.put("earnedPoints", "已获分数");
        map.put("earningPoints", "预获分数");
        map.put("prizeName", "奖品名称");
        map.put("score", "花费分数");
        map.put("admin", "admin");
    }

    public static String getTranslationWords(String englishWord) {
        return map.get(englishWord);
    }
}
