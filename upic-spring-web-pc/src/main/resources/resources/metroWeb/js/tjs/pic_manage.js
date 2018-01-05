/**
 * Created by zhubuqing on 2018/1/5.
 */
var dataUrl = "/systemManager/getBanner";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {
    type: "OUTSIDE"
};

$(function () {
    pageSize = $("#select-small").children('option:selected').text()
    getData(pageNum, dataUrl);
})

function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        htmls += "<tr>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td class='center_td'><input type='checkbox' class='checkboxes' value='1'/></td>";
        htmls += "<td class='center_td'>";
        htmls += "<div class='common-row'>";
        htmls += "<div class='cell-right'><span class='" + data[i].field1 + "' themeColor='#6d9eeb' id='directory'></span>";
        htmls += "</div></div></td>";
        htmls += "<td class='center_td'>" + data[i].id + "</td>";
        htmls += "<td class='img-box'>";
        htmls += "<a class='tooltip1' href='" + data[i].url + "'><img src='" + data[i].pic + "' alt=''></a>";
        htmls += "</td>";
        htmls += "<td>" + data[i].field2 + "</td>";
        htmls += "<td class='center_td'>";
        htmls += "<a href='#mymoda2' data-toggle='modal'>编辑</a><span class='space'>|</span><a href='#'>删除</a></td>";
        htmls += "</tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function getProjectInfo(result, j) {

}
