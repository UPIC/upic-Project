/*
 * @Author: Marte
 * 查看人数
 * @Date:   2017-09-19 15:13:27
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-09-23 09:56:05
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

var page = 1;
var pageCount = -1;
var getNumUrl = "/teacher/getUserListByProjectNum";
var types = "GET";
$(function () {
    /**
     * 获取人数信息
     */
    ajaxs("projectNum=" + getQueryString("projectNum"), "getNum", getNumUrl)

})
function ajaxs(datas, method, urls) {
    if (pageCount == page || pageCount == 0) {
        return;
    }
    $.ajax({
        type: types, // 提交方式
        url: urls,// 路径
        data: datas,
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
    if (method == "getNum") {
        for (var i = 0; i < result.length; i++) {
            var status = "未签到";
            if (result[i].status === "SIGNED_IN" || result[i].status === "COMPLETED") {
                status = "已签到"
            }

            var property = "";
            if (status === "已签到") {
                property = "disabled='false'"
            }
            ;
            htmls += " <tr><td>" + (i + 1) + "</td>";
            htmls += "<td>" + result[i].integralLogId.studentNum + "</td>";
            htmls += "<td>" + result[i].student + "</td>";
            htmls += "<td>" + status + "</td>";
            htmls += "<td><input type='checkbox' id='inlineCheckbox2' value='option2' " + property + " ></td></tr>";
        }
        if (page == 1) {
            $("#" + method).html(htmls);
        } else {
            $("#" + method).append(htmls);
        }
    }

}