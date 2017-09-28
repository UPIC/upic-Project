/*
 * @Author: Marte
 * 兑换记录
 * @Date:   2017-09-20 12:16:07
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-09-20 22:04:53
 */
var page = 1;
var pageCount = -1;
var getGraincoinLogPage = "/common/getGraincoinLogPage";
var types = "GET";

$(function () {
    /**
     * 获取兑换记录
     */
    ajaxs("type=PAYMENT", "home", getGraincoinLogPage)
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
            htmls += "<li><div class='tab-left'><img src='assets/i/b-3.jpg'></div>";
            htmls += "<div class='tab-right'><div class='right-name'>" + result[i].prizeName + "</div>";
            htmls += "<div class='right-apply other'>素拓币: <span>" + result[i].score + "</span> 分</div></div></li>";
        }
    }
    if (page == 1) {
        $("#" + method).html(htmls);
    } else {
        $("#" + method).append(htmls);
    }
}