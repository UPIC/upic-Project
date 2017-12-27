/*
 * @Author: Marte
 * 积分申报，活动详情的页面
 * @Date:   2017-09-20 12:19:52
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-11-10 16:17:37
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
            htmls += "<li class='clearfix'><div class='list-name'>项目名称：</div>";
            htmls += "<div class='list-det det-cen'>" + result.projectName + "</div></li>";
            htmls += "<li class='clearfix'><div class='list-name'>项目类别：</div>";
            htmls += "<div class='list-det det-cen'>";
            htmls += result.event;
            htmls += "</div>";
            htmls += "</li>";
            htmls += "<li class='li-other clearfix'>";
            htmls += "<div class='list-line'>";
            htmls += "项目详情：";
            htmls += "</div>";
            htmls += "<div class='li-text'>" + result.event + "</div>";
            htmls += "</li>";
            htmls += "<li class='li-other clearfix'>";
            htmls += "<div class='list-line'>";
            htmls += "佐证照片：";
            htmls += "</div>";
            htmls += "<img src='assets/i/b-3.jpg' alt=''>";
            htmls += "</li>";

            $("#info").html(htmls);

            if (result.status === "PENDING_AUDIT_BEFORE" || result.status === "PENDING_AUDIT" || result.status === "PENDING_AUDIT_AGAIN" || result.status === "PENDING_AUDIT_FINAL") {
                $("#info2").html("审核中");
            }

            if (result.status === "HAVEPASSED") {
                $("#info2").html("审核成功");
            }

            if (result.status === "PENDING_AUDIT_BEFORE_FAIL" || result.status === "PENDING_AUDIT_FAIL" || result.status === "PENDING_AUDIT_AGAIN_FAIL" || result.status === "PENDING_AUDIT_FINAL_FAIL") {
                $("#info2").html("审核失败");
            }
        }
    })
})

//function getSplitSmall(event) {
//    var projectCategory = new Array();
//    projectCategory = event.split("/");
//    return projectCategory[0];
//}