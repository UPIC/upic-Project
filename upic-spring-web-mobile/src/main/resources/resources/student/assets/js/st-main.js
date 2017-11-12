/*
 * @Author: Marte
 * 学生素拓主页
 * @Date:   2017-09-20 13:05:18
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-09-20 17:18:29
 */

/**
 * 获取积分
 */
var getIntegeralUrl = "/stu/getIntegeral";
var getGrainCoinUrl = "/stu/getGrainCoin";
$.ajax({
    type: "GET", // 提交方式
    url: getIntegeralUrl,// 路径
    success: function (result) {// 返回数据根据结果进行相应的处理
        $("#integeral").html(result);
    }
});

/**
 * 获取素拓币
 */
$.ajax({
    type: "GET", // 提交方式
    url: getGrainCoinUrl,// 路径
    success: function (result) {// 返回数据根据结果进行相应的处理
        $("#getGrainCoin").html(result);
    }
});