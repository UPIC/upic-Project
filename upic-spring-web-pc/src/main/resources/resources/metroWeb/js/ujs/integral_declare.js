/*
 * @Author: Marte
 * 积分申报
 * @Date:   2017-09-24 10:17:37
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-10-11 22:33:40
 */

/*
 获取项目类别，级别
 */

var getAllProjectCategory = "/common/getAllProjectCategory";
var getStuPostIntegralLog = "/stu/postIntegralLog";
var types = "GET";

$(function () {
    ajaxs("", "getProject", getAllProjectCategory);
})

function ajaxs(datas, method, urls) {
    $.ajax({
        type: types, // 提交方式
        url: urls,// 路径
        data: datas,
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (result) {// 返回数据根据结果进行相应的处理
            addHtmls(result, method)
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

function addHtmls(result, method) {
    var html1 = "";
    var html2 = "";
    var html3 = "";
    var html4 = "";

    if (method == "getProject") {
        for (var i = 0; i < result.content.length; i++) {
            html1 += "<option value='category " + (i + 1) + "'>" + result.content[i].categoryName + "</option>";
        }

        $("#getone").html(html1);

        for (var i = 0; i < result.content.length; i++) {
            html2 += "<option value='category " + (i + 1) + "'>" + result.content[i].getr1 + "</option>";
        }

        $("#gettwo").html(html2);

        for (var i = 0; i < result.content.length; i++) {
            html3 += "<option value='category " + (i + 1) + "'>" + result.content[i].getr2 + "</option>";
        }

        $("#getthree").html(html3);
    }

    if (method == "getIntegralLog") {
        html4 = "<input class='medium' readonly type='text' placeholder='" + result.integral + "' disabled /><span class='help-inline'>*</span>";
    }

    $("#getfour").html(html4);
}

function postIntegral() {
    ajaxs("", "getIntegralLog", getStuPostIntegralLog);
}