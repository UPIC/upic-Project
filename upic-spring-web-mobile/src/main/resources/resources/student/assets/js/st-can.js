/*
 * @Author: Marte
 * 我能兑换
 * @Date:   2017-09-20 12:19:52
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-11-10 13:51:26
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
 function getCoin(){
    $.ajax({
    type: "GET", // 提交方式
    url: getCoin,// 路径
    async: false,
    success: function (result) {// 返回数据根据结果进行相应的处理
        coin = result;
    }
});
}

$(function () {
    ajaxs("score=0&scoreTo=" + coin + "&status=SHELVES", "getPrizeInfo", getinfo);
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
            htmls += "<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#myModal'>";
            htmls += "兑换";
            htmls += "</button>";
            htmls += "<div class='modal fade' id='myModa' tabindex='-1' role='dialog' aria-labelledby='myModalLabel'>";
            htmls += "<div class='modal-dialog' role='document'>";
            htmls += "<div class='modal-content'>";
            htmls += "<div class='modal-header'>";
            htmls += "<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>";
            htmls += "<h4 class='modal-title' id='myModalLabe'>提示</h4>";
            htmls += "</div>";
            htmls += "<div class='modal-body'>";
            htmls += "兑换成功";
            htmls += "</div>";
            htmls += " <a href='st-main.html'>";
            htmls += "<div class='modal-footer'>";
            htmls += "<button type='button' class='btn btn-default' data-dismiss='modal' onclick='duihuan(" + result[i].id + ")'>确定</button>";
            htmls += "</div>";
            htmls += " </a>";
            htmls += "</div>";
            htmls += "</div>";
            htmls += "</div>";
            htmls += "</div>";
            htmls += "<div class='right-apply'>总计: <span>" + result[i].score + "</span> 分</div></div></li>";
        }
    }

    if (page == 1) {
        $("#" + method).html(htmls);
    } else {
        $("#" + method).append(htmls);
    }
}

function duihuan(prizeId){
    $.ajax({
        type: types, // 提交方式
        url: '/stu/getExchangePrize',// 路径
        data: {
            id:prizeId
        },
        success: function (result) {// 返回数据根据结果进行相应的处理
            alert("已兑换");
            getCoin();
        }
    });
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
                         getCoin();
                         ajaxs("score=0&scoreTo=" + coin + "&status=SHELVES", "getPrizeInfo", getinfo);

                     }
                 });