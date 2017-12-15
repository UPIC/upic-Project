/*
 * @Author: Marte
 * 积分申报
 * @Date:   2017-09-20 12:22:12
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-11-10 14:51:57
 */

/**
 * 1.如果传过来的状态码 审核中对应id="aa" ....
 */
var page = 0;
var pageCount = -1;
var getStudentInfoUrl = "/common/getUserInfo";
var getInreviewIntegralLogPage = "/common/getInreviewIntegralLogPage";
var getSuccessIntegralLogPage = "/common/getSuccessIntegralLogPage";
var getDefeatedIntegralLogPage = "/common/getDefeatedIntegralLogPage";
var postIntegralLog = "/stu/postIntegralLog";
var types = "GET";

var pageRequest = {};

var obj = {
    event: "",
    projectName: "",
    content: "",
    integral: ""
};

var activitiNow = "";

var requestUrl = "";

/**
 * 滚动条
 * @type {number}
 */
var totalheight = 0;// 定义一个总的高度变量
$(window)
    .scroll(
        function () {
            totalheight = parseFloat($(window).height())
                + parseFloat($(window).scrollTop());// 浏览器的高度加上滚动条的高度
            if ($(document).height() <= (totalheight + 6)) // 当文档的高度小于或者等于总的高度的时候，开始动态加载数据
            {
                if (pageCount > page) {
                    page++;
                    pageRequest.page = page;
                    ajaxs(pageRequest, activitiNow, requestUrl);
                }
            }
        });

$(function () {
    /**
     * 获取用户学院
     */
    $.ajax({
        type: "GET", // 提交方式
        async: false,
        url: getStudentInfoUrl,// 路径
        success: function (result) {// 返回数据根据结果进行相应的处理
            $("#college").html(result.college);
        }
    });

    /**
     * 点击提交事件
     */
    $("#submit").click(function () {
        obj.event = hadCategoryName;
        obj.content = $("#content").text();
        obj.integral = $("#integral").val();
        if (myRadio === "radioselect1") {
            obj.projectName = $("#form1 option:selected").text();
            obj.field1 = "radioselect1";
            obj.field2 = $("#form1 option:selected").val();
        } else {
            obj.projectName = $("#projectName").val();
            obj.field1 = "radioselect2";
        }

        $.ajax({
            type: "POST",
            url: postIntegralLog,
            data: obj,
            success: function (message) {
                if (message != null) {
                    alert("请求已提交！");
                    window.location.href = "st-check.html?projectNum=" + message.integralLogId.projectNum;
                }
            },
            error: function (message) {

            }
        });
    });

    /*
     获取点击申报表
     */
    $(".aa").click(function () {
        /**
         * 获取用户学院
         */
        $.ajax({
            type: "GET", // 提交方式
            async: false,
            url: getStudentInfoUrl,// 路径
            success: function (result) {// 返回数据根据结果进行相应的处理
                $("#college").html(result.college);
            }
        });
    })

    /**
     * 获取点击已申报，加载审核中数据。
     */
    $(".bb").click(function () { // 和点击审核中是一样的
        juge($(this));
        pageRequest.page = 0;
        pageRequest.size = 10;
        requestUrl = getInreviewIntegralLogPage;
        ajaxs(pageRequest, "inreview", requestUrl);
        activitiNow = "inreview";
    })

    /**
     * 获取点击审核中
     */
    $(".cc").click(function () {
        juge($(this));
        pageRequest.page = 0;
        pageRequest.size = 10;
        requestUrl = getInreviewIntegralLogPage;
        ajaxs(pageRequest, "inreview", requestUrl);
        activitiNow = "inreview";
    })

    /**
     * 获取点击审核成功
     */
    $(".dd").click(function () {
        juge($(this));
        pageRequest.page = 0;
        pageRequest.size = 10;
        requestUrl = getSuccessIntegralLogPage;
        ajaxs(pageRequest, "success", requestUrl);
        activitiNow = "success";
    })

    /**
     * 获取点击审核失败
     */
    $(".ee").click(function () {
        juge($(this));
        pageRequest.page = 0;
        pageRequest.size = 10;
        requestUrl = getDefeatedIntegralLogPage;
        ajaxs(pageRequest, "defeated", requestUrl);
        activitiNow = "defeated";
    })
})

function juge(obj) {
    if (obj.hasClass("active")) {
        return;
    }
    page = 0;
    pageCount = -1;
}

function ajaxs(datas, method, urls) {
    if (pageCount == page || pageCount == 0) {
        return;
    }
    $.ajax({
        type: types, // 提交方式
        url: urls, // 路径
        data: datas,
        async: false,

        beforeSend: function (XMLHttpRequest) {
// progress.inc();
        },
        success: function (result) { // 返回数据根据结果进行相应的处理
            pageCount = result.totalPages;
            var datas = result.content;
            addHtmlss(datas, method)
        },
        complete: function (XMLHttpRequest, textStatus) {
// progress.done(true);
        },
        error: function () {
// progress.done(true);
        }
    });
}

function addHtmlss(result, method) {
    var htmls = "";
    var url = "st-check.html";
    for (var i = 0; i < result.length; i++) {
        if (result[i].status === "PENDING_AUDIT_BEFORE_FAIL" || result[i].status === "PENDING_AUDIT_FAIL" || result[i].status === "PENDING_AUDIT_AGAIN_FAIL" || result[i].status === "PENDING_AUDIT_FINAL_FAIL") {
            url = "st-detail4.html";
        }
        htmls += "<div class='act-div'><div class='tab-left'><img src='" + result[i].integralLogPic + "'></div>";
        htmls += "<div class='tab-right'><div class='tab-title'><div class='right-name'>" + subMyStr(result[i].projectName) + "</div></div>";
        htmls += "<div class='tab-text'><span>" + subSmallStr(result[i].event) + "</span></div>";
        htmls += "<a href='" + url + "?projectNum=" + result[i].integralLogId.projectNum + "'><div class='tab-search'>" + "查看详情" + " ></div></a></div></div>";
    }
    if (method === "inreview") {
        if (page == 0) {
            $("#aa").html(htmls);
        } else {
            $("#aa").append(htmls);
        }
    } else if (method === "success") {
        if (page == 0) {
            $("#bb").html(htmls);
        } else {
            $("#bb").append(htmls);
        }
    } else {
        if (page == 0) {
            $("#cc").html(htmls);
        } else {
            $("#cc").append(htmls);
        }
    }
}

function subMyStr(str) {
    if (str.length > 8) {
        str = str.substring(0, 8);
        str += "...";
    }
    return str;
}

function subSmallStr(str) {
    if (str.length > 27) {
        str = str.substring(0, 27);
        str += "...";
    }
    return str;
}