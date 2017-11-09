/*
 * @Author: Marte
 * 教师端主页
 * @Date:   2017-09-19 10:16:05
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-11-09 10:23:06
 */

/**
 * 获取老师姓名
 */
$.ajax({
    type: "GET", // 提交方式
    url: "/common/getUserInfo",// 路径
    success: function (result) {// 返回数据根据结果进行相应的处理
        $("#tname").html(result.username);
        $("#tpic").html("<img src='" + result.pic + "'>");
    }
});