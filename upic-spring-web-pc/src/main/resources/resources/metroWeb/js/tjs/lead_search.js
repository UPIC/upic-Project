var dataUrl = "/common/getAllUser";
var getCollegeUrl = "/common/getCollege";
var getClazzUrl = "/common/getClazz";
var searchKeyWordUrl = "/common/projectSearchBar";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {};

$(function () {
    pageSize = $("#select-small").children('option:selected').text()
    getData(pageNum, dataUrl);
    commonAjax(getCollegeUrl, null, "addCollege", "GET");
    commonAjax(getClazzUrl, null, "addClazz", "GET");
    registSelect("college");
    registSelect("clazz");
})

function addCollege(res) {
    var data = res.content;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>学院筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].college + "</option>";
    }
    $("#college").html(htmls);
}

function addClazz(res) {
    var data = res.content;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>班级筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].clazz + "</option>";
    }
    $("#clazz").html(htmls);
}

function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        htmls += "<tr>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td>" + data[i].college + "</td>";
        htmls += "<td>" + data[i].userNum + "</td>";
        htmls += "<td>" + data[i].username + "</td>";
        htmls += "<td>" + data[i].clazz + "</td>";
        htmls += "</tr>";
    }

    $("#data").html(htmls);
    page(datas, dataUrl, requestData.size, requestData.page);
}
