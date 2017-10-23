var dataUrl = "/common/getPrize";//status = SHELVES
var updatePrize = "/common/updatePrize";//编辑
var addPrize = "/common/addPrize";//上传
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {
    status: "SHELVES"
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
        htmls += "<td>" + data[i].prizeName + "</td>";
        htmls += " <td class='td-line'><span>" + data[i].content + "</span></td>";
        htmls += "<td class='center_td'><img src='" + data[i].prizePic + "' alt=''></td>";
        htmls += "<td>" + data[i].score + "</td>";
        htmls += "<td>" + getDate(data[i].startTime, "yyyy-MM-dd") + "</td>";
        htmls += "<td>";
        htmls += "<button class='btn btn-small' onClick=>上架</button>";
        htmls += "<button class='btn btn-small down' onClick=>下架</button>";
        htmls += "</td>";
        htmls += "<td class='center_td'>";
        htmls += "<a href='#mymodal1' data-toggle='modal'><button class='btn btn-small'>编辑</button></a>";
        htmls += "</td>";
        htmls += "</tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function up() {//上传按钮

}

function changeStatus() {//上架，下架方法

}

function changeInfo() {//编辑方法

}