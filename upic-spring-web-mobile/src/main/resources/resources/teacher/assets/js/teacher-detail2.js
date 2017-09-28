/*
 * @Author: Marte
 * 详情页面2
 * @Date:   2017-09-19 15:12:33
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-09-23 09:59:43
 */

/**
 * 获取点击的项目名
 */

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

$(function () {
    var projectNum = getQueryString("projectNum");
    if (projectNum == null) {
        return;
    }
    getProjectTime(projectNum);
})

function getProjectTime(projectNUm) {
    $.ajax({
        url: '/teacher/getProjectByProjectNum',
        type: 'GET', // GET
        data: {
            projectNum: projectNUm
        },
        dataType: 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
        beforeSend: function (xhr) {
        },
        success: function (data) {
            addHtmls(data);
        },
        error: function (xhr, textStatus) {
        },
        complete: function () {
        }
    })
}
function addHtmls(data) {
    var status = "";
    switch (data.implementationProcess) {
        case ("IN_AUDIT"):
            status = "审核中";
            break;
        case ("AUDITED"):
            status = "已审核";
            break;
        case ("SAVED"):
            status = "已保存";
            break;
        case ("NO_PASS"):
            status = "未通过";
            break;
        case ("HAVE_IN_HAND"):
            status = "进行中";
            break;
        case ("COMPLETED"):
            status = "已完成";
            break;
        case ("ENROLLMENT"):
            status = "报名中";
            break;
    }
    var htmls = "";
    htmls += "<li><div class='list-name'>项目名称：</div><div class='list-det'>" + data.projectName + "</div></li>";
    htmls += "<li><div class='list-name'>项目类别：</div><div class='list-det'>" + data.projectCategory + "</div></li>";
    htmls += "<li><div class='list-name'>活动状态：</div><div class='list-det red'>" + status + "</div></li>";
    htmls += "<li><div class='list-name'>预获学分：</div><div class='list-det'>" + data.integral + "</div></li>";
    htmls += "<li><div class='list-name'>活动时间：</div><div class='list-det'>" + getDate(data.startTime, "MM-dd hh:mm") + "~" + getDate(data.endTime, "MM-dd hh:mm") + "</div></li>";
    htmls += "<li><div class='list-name'>报名时间：</div><div class='list-det'>" + getDate(data.signUpStartTime, "MM-dd hh:mm") + "-" + getDate(data.signUpEndTime, "MM-dd hh:mm") + "</div></li>";
    htmls += "<li class='li-other'><div class='list-line'>项目详情</div><div class='li-text'>" + data.content + "</div></li>";

    $("#content").html(htmls);

    var htmlss = "";
    htmlss += "<a href='teacher-num.html?projectNum=" + data.projectNum + "'>";
    htmlss += "<li class='one'>查看人数</li>";
    htmlss += "</a>";
    htmlss += "<li class='two'>点击显示二维码</li>";

    $("#watchNum").html(htmlss);
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