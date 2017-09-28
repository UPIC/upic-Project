/*
 * @Author: Marte
 * 我能兑换
 * @Date:   2017-09-20 12:19:52
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-09-21 18:10:28
 */

/**
 * 获取商品详情
 */

var getCoin = "/stu/getGrainCoin";
var getinfo = "/common/getPrize";
var page = 1;
var pageCount = -1;
var types = "GET";
var coin = "";

/*
 获取积分
 */
$.ajax({
    type: "GET", // 提交方式
    url: getCoin,// 路径
    async: false,
    success: function (result) {// 返回数据根据结果进行相应的处理
        coin = result;
    }
});

$(function () {
    ajaxs("score=0" + "&scoreTo=" + coin + "&status=SHELVES", "getPrizeInfo", getinfo);
});

function ajaxs(datas, method, urls) {
    if (pageCount == page || pageCount == 0) {
        return;
    }
    $.ajax({
        type: types, // 提交方式
        url: urls,// 路径
        async: false,
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
    if (method == "getPrizeInfo") {
        for (var i = 0; i < result.length; i++) {
            htmls += "<li><div class='tab-left'><img src='" + result[i].prizePic + "'></div>";
            htmls += "<div class='tab-right'><div class='right-name'>" + result[i].prizeName;
            htmls += "<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#myModa2'>兑换</button>";

            htmls += "<div class='modal fade' id='myModa2' tabindex='-1' role='dialog' aria-labelledby='myModalLabe2'>";
            htmls += "<div class='modal-dialog' role='document'><div class='modal-content'>";
            htmls += "<div class='modal-header'>";
            htmls += "<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>";
            htmls += "<h4 class='modal-title' id='myModalLabe2'>提示</h4>";
            htmls += "</div><div class='modal-body'>兑换失败</div>";
            htmls += "<div class='modal-footer'>";
            htmls += "<button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button></div></div></div></div></div>";
            htmls += "<div class='right-apply'>总计: <span>" + result[i].score + "</span> 分</div></div></li>";
        }
    }

    if (page == 1) {
        $("#" + method).html(htmls);
    } else {
        $("#" + method).append(htmls);
    }
}


