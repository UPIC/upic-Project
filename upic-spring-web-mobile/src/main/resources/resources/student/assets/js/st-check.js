/*
 * @Author: Marte
 * 积分申报，活动详情的页面
 * @Date:   2017-09-20 12:19:52
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-09-21 14:57:35
 */

/**
 * 1.项目名
 * 2.类别 X
 * 3.详情
 * 4.照片
 */
var getIntegralLogInfoByMySelf = "/common/getIntegralLogInfoByMySelf";

/*
 获取上一页面传递过来的projectNum
 */
var projectNum = getQueryString("projectNum");

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

$(function () {
    var htmls = "";
    /*
     获取活动详情
     */
    $.ajax({
        type: "GET", // 提交方式
        url: getIntegralLogInfoByMySelf,// 路径
        data: "projectNum=" + projectNum,
        success: function (result) {// 返回数据根据结果进行相应的处理

            htmls += "<li><div class='list-name'>项目名称：</div>";
            htmls += "<div class='list-det'>" + result.projectName + "</div></li>";
            htmls += "<li><div class='list-name'>项目类别：</div>";
            htmls += "<div class='list-det'>" + "文体活动" + "</div></li>";
            htmls += "<li class='li-other'><div class='list-line'>项目详情：</div>";
            htmls += "<div class='li-text'>" + result.event + "</div></li>";
            htmls += "<li class='li-other'><div class='list-line'>佐证照片：</div><img src='assets/i/b-3.jpg'></li>";

            $("#info").html(htmls);

            if (result.status === "PENDING_AUDIT") {
                $("#info2").html("审核中...");
            }

            if (result.status === "HAVEPASSED") {
                $("#info2").html("审核成功...");
            }

            if (result.status === "FAILURE_TO_PASS_THE_AUDIT") {
                $("#info2").html("审核失败...");
            }
        }
    })
})

