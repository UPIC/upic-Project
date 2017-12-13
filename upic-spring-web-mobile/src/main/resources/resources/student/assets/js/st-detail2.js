/*
 * @Author: Marte
 * 积分申报，活动详情的页面
 * @Date:   2017-09-20 12:19:52
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-11-10 16:38:42
 */

/**
 * 1.项目名
 * 2.类别 X
 * 3.详情
 * 4.照片
 */
var getProjectInfoByProjectNum = "/stu/getMyPageIntegral";

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
        url: getProjectInfoByProjectNum,// 路径
        data: "projectNum=" + projectNum,
        success: function (result) {// 返回数据根据结果进行相应的处理
            htmls += "<li>";
            htmls += "<div class='list-name'>";
            htmls += "项目名称：";
            htmls += "</div>";
            htmls += "<div class='list-det'>";
            htmls += result.content[0].projectName;
            htmls += "</div>";
            htmls += "</li>";
            htmls += "<li>";
            htmls += "<div class='list-name'>";
            htmls += "项目类别：";
            htmls += "</div>";
            htmls += "<div class='list-det'>";
            htmls += result.content[0].projectCategory;
            htmls += "</div>";
            htmls += "</li>";
            htmls += "<li>";
            htmls += "<div class='list-name'>";
            htmls += "活动状态：";
            htmls += "</div>";
            htmls += "<div class='list-det red'>";
            if (result.content[0].status == "ALREADY_SIGN_UP") {
                htmls += "已报名"
            }

            if (result.content[0].status == "HAVEPASSED" || result.content[0].status == "COMPLETED") {
                htmls += "已完成"
            }

            htmls += "</div>";
            htmls += "</li>";
            htmls += "<li>";
            htmls += "<div class='list-name'>";
            htmls += "预获学分：";
            htmls += "</div>";
            htmls += "<div class='list-det'>";
            htmls += result.content[0].integral;
            htmls += "</div>";
            htmls += "</li>";
            if (result.content[0].status == "ALREADY_SIGN_UP") {
                htmls += "<li>";
                htmls += "<div class='list-name'>";
                htmls += "报名时间：";
                htmls += "</div>";
                htmls += "<div class='list-det'>";
                htmls += getDate(result.content[0].creatTime, "MM-dd hh:mm");
                htmls += "</div>";
                htmls += "</li>";
            }

            if (result.content[0].status == "HAVEPASSED" || result.content[0].status == "COMPLETED") {
                htmls += "<li>";
                htmls += "<div class='list-name'>";
                htmls += "完成时间：";
                htmls += "</div>";
                htmls += "<div class='list-det'>";
                htmls += getDate(result.content[0].creatTime, "yyyy-MM-dd hh:mm");
                htmls += "</div>";
                htmls += "</li>";
            }

            htmls += "<li class='li-other'>";
            htmls += "<div class='list-line'>";
            htmls += "项目详情：";
            htmls += "</div>";
            htmls += "<div class='li-text'>";
            htmls += result.content[0].event;
            htmls += "</div>";
            htmls += "</li>";

            $("#info").html(htmls);
        }
    })
})

function getDate(date, rule) {
    var date = new Date(date);
    var dateStr = date.pattern(rule);
    return dateStr;
}

Date.prototype.pattern = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, // 月份
        "d+": this.getDate(), // 日
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时
        "H+": this.getHours(), // 小时
        "m+": this.getMinutes(), // 分
        "s+": this.getSeconds(), // 秒
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
        "S": this.getMilliseconds()
        // 毫秒
    };
    var week = {
        "0": "/u65e5",
        "1": "/u4e00",
        "2": "/u4e8c",
        "3": "/u4e09",
        "4": "/u56db",
        "5": "/u4e94",
        "6": "/u516d"
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    }
    if (/(E+)/.test(fmt)) {
        fmt = fmt
            .replace(
                RegExp.$1,
                ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f"
                        : "/u5468")
                    : "")
                + week[this.getDay() + ""]);
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
                : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}