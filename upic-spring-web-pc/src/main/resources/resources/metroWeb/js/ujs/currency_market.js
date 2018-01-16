/*
 * @Author: Marte
 * 学生素拓主页
 * @Date:   2017-09-20 13:05:18
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-09-20 17:18:29
 */

var getGrainCoinUrl = "/stu/getGrainCoin";
var getGraincoinLogPage = "/common/getGraincoinLogPage";
var getPrize = "/common/getPrize";
// var getHistoryPrize = "/common/getHistoryPrize";
var getExchangePrizeUrl = "/stu/getExchangePrize";

var myGrainCoin = 0;

$(function () {
    /**
     * 获取素拓币
     */
    getGrainCoin();

    /**
     * 获取兑换记录
     */
    ajaxs("type=PAYMENT&&myType=single", "duiHuanJiLu", getGraincoinLogPage);

    /**
     * 获取兑换物品
     */
    ajaxs("", "duiHuanWuPin", getPrize);

    /**
     * 获取历史物品
     */
    // ajaxs("", "liShiWuPin", getHistoryPrize);
})

function getGrainCoin() {
    $.ajax({
        type: "GET", // 提交方式
        url: getGrainCoinUrl,// 路径
        success: function (result) {// 返回数据根据结果进行相应的处理
            myGrainCoin = result;
            $("#grainCoin").html(result);
        }
    });
}

var types = "GET";

function ajaxs(datas, method, urls) {
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
    if (method === "duiHuanWuPin") {
        for (var i = 0; i < result.length; i++) {
            htmls += "<li>";
            htmls += "<div class='goods_item'>";
            if (result[i].prizePic == null) {
                htmls += "<img src='../../img/example.jpg' class='img-thumbnail'>";
            } else {
                htmls += "<img src='" + result[i].prizePic[0] + "' class='img-thumbnail'>";
            }
            htmls += "<h4>" + result[i].prizeName + "</h4>";
            htmls += "<p>所需积分：<span>" + result[i].score + "</span></p>";
            htmls += "<div class='button_exchange'>";
            htmls += "<button class='btn btn-warning' type='button' onclick=getExchangePrize(" + result[i].id + ',' + result[i].score + ")>兑换物品</button>";
            htmls += "</div>";
            htmls += "</div></li>";
        }
    } else {
        for (var i = 0; i < result.length; i++) {
            htmls += "<li>";
            htmls += "<div class='goods_item'>";
            htmls += "<img src='" + result[i].prizePic[0] + "' class='img-thumbnail'>";
            htmls += "<h4>" + result[i].prizeName + "</h4>";
            htmls += "<p>花费积分：<span>" + result[i].score + "</span></p>";
            htmls += "</div></li>";
        }
    }
    $("#" + method).html(htmls);
}

function getExchangePrize(prizeId, score) {
    if (myGrainCoin < score) {
        alert("兑换失败，素拓币不足！");
    } else {
        $.ajax({
            type: types, // 提交方式
            url: getExchangePrizeUrl, // 路径
            data: {
                prizeId: prizeId
            },
            beforeSend: function (XMLHttpRequest) {
            },
            success: function (result) {// 返回数据根据结果进行相应的处理
                if (result === "SUCCESS") {
                    alert("兑换成功！");

                    getGrainCoin();
                } else {
                    alert("兑换失败！");
                }
            },
            complete: function (XMLHttpRequest, textStatus) {
            },
            error: function (err) {
                alert(err.msg);
            }
        });
    }
}