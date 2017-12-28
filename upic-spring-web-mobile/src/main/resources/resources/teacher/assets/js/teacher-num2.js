/*
 * @Author: Marte
 * 查看人数
 * @Date:   2017-09-19 15:13:27
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-11-09 14:57:37
 */
 function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

var page = 0;
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
        url: urls+"?"+datas,// 路径
//        data: datas,
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
    var htmlss = "";

    if (method == "getNum") {
        for (var i = 0; i < result.length; i++) {
        	if(result[i].status==="ALREADY_SIGN_UP"){
        		status="已报名";
        	}
        	else{
        		var status = "已签到";
        	}
            htmls += "<tr><td>" + (i + 1) + "</td>";
            htmls += "<td>" + result[i].integralLogId.studentNum + "</td>";
            htmls += "<td>" + result[i].student + "</td>";
            htmls += "<td>" + status + "</td>";
        }

        htmlss+="<div class='top-left'>总人数:"+result.length;
        htmlss+="</div>";

        $("#getNum2").html(htmlss);
        $("#" + method).html(htmls);

    }

}