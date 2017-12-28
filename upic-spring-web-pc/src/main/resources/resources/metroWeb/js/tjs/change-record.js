/*
 * @Author: Marte
 * @Date:   2017-10-12 08:36:44
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-11-06 12:04:55
 */

var dataUrl = "/common/getGrainCoinLog";//type = PAYMENT  获取所有奖品兑换记录
var userSearchBar = "/common/userSearchBar"//String keyword  搜索条
var types = "GET";
var exportExcelUrl = "/common/exportGrainCoinLog";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {
    type: "PAYMENT"
}

$(function () {
    pageSize = $("#select-small").children('option:selected').text()
    getData(pageNum, dataUrl);

    $("#exportBtn").click(function () {
        var baseModels = ["prizeName", "username", "userNum", "score"];
        var str = JSON.stringify(baseModels);
        var form = $("<form></form>").attr("action", exportExcelUrl).attr("method", "GET");
        form.append($("<input></input>").attr("type", "hidden").attr("name", "baseModel").attr("value", str));
        form.append($("<input></input>").attr("type", "hidden").attr("name", "type").attr("value", "PAYMENT"));
        var formKeyValue = appendForm(requestData);
        for (var i = 0; i < formKeyValue.key.length; i++) {
            form.append($("<input></input>").attr("type", "hidden").attr("name", formKeyValue.key[i]).attr("value", formKeyValue.value[i]));
        }
        form.appendTo('body').submit().remove();
        form.submit();
    })
})


function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        htmls += "<tr>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td><img src='" + data[i].pic + "' alt=''>" + data[i].prizeName + "</td>";
        htmls += "<td>" + data[i].username + "</td>";
        htmls += "<td>" + data[i].userNum + "</td>";
        htmls += "<td>" + data[i].score + "</td>";
        htmls += "<td>" + getDate(data[i].creatTime, 'yyyy-MM-dd') + "</td>";
        htmls += "</tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}
