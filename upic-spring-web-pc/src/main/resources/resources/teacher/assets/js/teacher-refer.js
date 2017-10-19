/*
 * @Author: Marte
 * 活动查询
 * @Date:   2017-09-19 10:26:52
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-09-23 09:48:45
 */

var page = 1;
var pageCount = -1;
var getAllurl = "/common/getProjectByGuidanceNum";//获取用户项目列表
var getNewNum = "/common/getSignUpNumberByProjectNum";//项目报名人数
var types = "GET";


$(function () {
    ajaxs("guidanceNum=101045", "getAll", getAllurl)
    /**
     * 获取创建的活动详情
     *
     * @returns
     */
})


function ajaxs(datas, method, urls) {
    if (pageCount == page || pageCount == 0) {
        return;
    }
    $.ajax({
        type: types, // 提交方式
        url: urls,// 路径
        data: datas,
        async: false,
        beforeSend: function (XMLHttpRequest) {
// progress.inc();
        },
        success: function (result) {// 返回数据根据结果进行相应的处理
            pageCount = result.totalPages;
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
    if (method == "getAll") {
        for (var i = 0; i < result.length; i++) {
            var num = getPeopleNum(result[i].projectNum);
            result.field1 = num;
        }

        for (var i = 0; i < result.length; i++) {
            var status = "";
            var url = "";
            var className = "";
            if (i == 0) {
                className = "act-div top"
            } else {
                className = "act-div"
            }

            switch (result[i].implementationProcess) {
                case ("IN_AUDIT"):
                    status = "审核中";
                    url = "teacher-detail.html?projectNum=" + result[i].projectNum;
                    break;
                case ("AUDITED"):
                    status = "已审核";
                    url = "teacher-detail.html?projectNum=" + result[i].projectNum;
                    break;
                case ("SAVED"):
                    status = "已保存";
                    url = "teacher-detail.html?projectNum=" + result[i].projectNum;
                    break;
                case ("NO_PASS"):
                    status = "未通过";
                    url = "teacher-detail.html?projectNum=" + result[i].projectNum;
                    break;
                case ("HAVE_IN_HAND"):
                    status = "进行中";
                    url = "teacher-detail2.html?projectNum=" + result[i].projectNum;
                    break;
                case ("COMPLETED"):
                    status = "已完成";
                    url = "teacher-detail.html?projectNum=" + result[i].projectNum;
                    break;
                case ("ENROLLMENT"):
                    status = "报名中";
                    url = "teacher-detail.html?projectNum=" + result[i].projectNum;
                    break;
            }

            htmls += "<div class='" + className + "'>";
            htmls += "<div class='tab-left'>";
            htmls += "<img src='assets/i/b-3.jpg'>";
            htmls += "</div>";
            htmls += "<div class='tab-right'>";
            htmls += "<div class='tab-title'>";
            htmls += "<div class='right-name'>";
            htmls += subMyStr(result[i].projectName);
            htmls += "</div>";
            htmls += " <div class='right-apply'>";
            htmls += "<span class='big'>" + status + "</span>";
            var signUpNum = parseInt(result[i].field1);
            if (isNaN(signUpNum)) {
                signUpNum = 0;
            }
            htmls += "<span class='small'></span><span  id='nowNum'>" + signUpNum + "</span>" + "<span>/" + result[i].maximum + "</span>";
            htmls += "</div>";
            htmls += "</div>";
            htmls += "<div class='tab-text'>";
            htmls += "<span>活动时间：" + getDate(result[i].startTime, "yy-MM-dd") + "-" + getDate(result[i].endTime, "yy-MM-dd") + "</span><br>";
            htmls += "<span>报名截止时间：" + getDate(result[i].signUpEndTime, "yy-MM-dd hh:mm") + "</span><br>";
            htmls += "<span>预获学分：" + result[i].integral + "</span>";
            htmls += "</div>";
            htmls += "<a href='" + url + "'><div class='tab-search'>";
            htmls += " 查看详情 >";
            htmls += "</div>";
            htmls += "</a>";
            htmls += "</div>";
            htmls += "</div>";
        }
    }
    if (page == 1) {
        $("#" + method).html(htmls);
    } else {
        $("#" + method).append(htmls);
    }
}

/**
 * 获取人数
 *
 * @param projectNum
 * @returns
 */
function getPeopleNum(projectNum) {
    $.ajax({
        url: getNewNum,
        type: 'GET', // GET
        async: false,
        data: {
            projectNum: projectNum
        },
// dataType : 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
        beforeSend: function (xhr) {
        },
        success: function (data) {
            var num = Number(data);
            if (num === 'NaN') {
                $("#nowNum").html("获取失败，请重新刷新页面！");
            }
            return num;
        },
        error: function (xhr, textStatus) {
        },
        complete: function () {
        }
    })
}


function getDate(date, rule) {
    var date = new Date(date);
    var dateStr = date.pattern(rule);
    return dateStr;
}
/**
 * 只满足获取项目时间
 *
 * @returns
 */
function getProjectTime(projectNUm) {
    $.ajax({
        url: '/stu/getProjectInfo',
        type: 'GET', // GET
        data: {
            projectNum: projectNUm
        },
        dataType: 'json',    // 返回的数据格式：json/xml/html/script/jsonp/text
        beforeSend: function (xhr) {
        },
        success: function (data) {
            var str = getDate(data.startTime, "yy.MM.dd hh:mm") + "-" + getDate(data.startTime, "yy.MM.dd hh:mm");
            $("#" + projectNUm).html("活动时间：" + str);
        },
        error: function (xhr, textStatus) {
        },
        complete: function () {
        }
    })
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