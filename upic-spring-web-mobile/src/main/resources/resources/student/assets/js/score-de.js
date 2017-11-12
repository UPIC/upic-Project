/*
 * @Author: Marte
 * 积分明细
 * @Date:   2017-09-20 12:18:20
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-11-10 10:37:31
 */

/**
 * 1.获取姓名
 * 2.获取当前积分总数
 * 3.积分明细(只加不减)
 */
var page = 1;
var pageCount = -1;
var types = "GET";
var getStudentInfoUrl = "/common/getUserInfo";
var getStudentScoreUrl = "/stu/getIntegeral";
var getIntegralLogPageUrl = "/common/getIntegralLogPage";


$(function () {

    /*
     获取头像,姓名
     */
    $.ajax({
        type: "GET", // 提交方式
        url: getStudentInfoUrl,// 路径
        success: function (result) {// 返回数据根据结果进行相应的处理
            $("#stinfo").html("<img src='" + result.pic + "'><span>" + result.username + "</span>");
        }
    });
    /*
     获取积分
     */
    $.ajax({
        type: "GET", // 提交方式
        url: getStudentScoreUrl,// 路径
        success: function (result) {// 返回数据根据结果进行相应的处理
            $("#stscore").html(result);
        }
    });

    /*
     获取积分明细
     */

    ajaxs("size=20", "home", getIntegralLogPageUrl)

})


function ajaxs(datas, method, urls) {
    if (pageCount == page || pageCount == 0) {
        return;
    }
    $.ajax({
        type: types, // 提交方式
        url: urls,// 路径
        data: datas,//

        beforeSend: function (XMLHttpRequest) {
// progress.inc();
        },
        success: function (result) {// 返回数据根据结果进行相应的处理
            pageCount = result.total;
            var datas = result.content;
            addHtmls(datas, method)
        },
        complete: function (XMLHttpRequest, textStatus) {
// progress.done(true);
        },
        error: function () {
// progress.done(true);
        }
    });
}

function addHtmls(result, method) {
    var htmls = "";
    if (method == "home") {
        for (var i = 0; i < result.length; i++) {
            var sta = "";
            var color = "#B0B0B0";
            if (result[i].status === "PENDING_AUDIT") {
                sta = "待审核";
            }
            if (result[i].status === "HAVEPASSED") {
                color = "#FFCC33";
                sta = "已通过";
            }
            if (result[i].status === "FAILURE_TO_PASS_THE_AUDIT") {
                sta = "未通过审核";
            }
            if (result[i].status === "ALREADY_SIGN_UP") {
                sta = "已报名";
            }
            if (result[i].status === "COMPLETED") {
                color = "#FFCC33";
                sta = "已完成";
            }

            htmls += "<li><div class='de-money'>" + subMyStr(result[i].projectName);
            htmls += "<div class='time' style='color: #B0B0B0'>" + sta;
            htmls += "</div></div><div class='de-num' style='color: " + color + "'>+";
            htmls += result[i].integral + "</div></li>";
        }
    }
    if (page == 1) {
        $("#" + method).html(htmls);
    } else {
        $("#" + method).append(htmls);
    }
}

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

function subMyStr(str) {
    if (str.length > 8) {
        str = str.substring(0, 8);
        str += "...";
    }
    return str;
}
 /** 滚动条* */
    var totalheight = 0;// 定义一个总的高度变量
    $(window)
            .scroll(
                    function() {
                        totalheight = parseFloat($(window).height())
                                + parseFloat($(window).scrollTop());// 浏览器的高度加上滚动条的高度
                        if ($(document).height() <= totalheight) // 当文档的高度小于或者等于总的高度的时候，开始动态加载数据
                        {
                           page++;
                           ajaxs('size=10&page='+page,"home", getIntegralLogPageUrl);

                        }
                    });