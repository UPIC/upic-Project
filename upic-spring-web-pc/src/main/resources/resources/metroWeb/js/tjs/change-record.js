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

/****************导出****************************/
// document.getElementById("exportBtn").onclick = function () {
//     var table = document.getElementById("sample_1").innerHTML;//获取table模板
//     exporExcel("奖品兑换记录", table);
// }
// /**
//  * @params: FileName:导出Excel的文件名称，excel:需要导出的table
//  * 如果没有table列表，只有json数据的话，将json数据拼接成table字符串模板即可
//  * **/
// function exporExcel(FileName, excel) {
//     var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";
//     excelFile += '<meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">';
//     excelFile += '<meta http-equiv="content-type" content="application/vnd.ms-excel';
//     excelFile += '; charset=UTF-8">';
//     excelFile += "<head>";
//     excelFile += "<!--[if gte mso 9]>";
//     excelFile += "<xml>";
//     excelFile += "<x:ExcelWorkbook>";
//     excelFile += "<x:ExcelWorksheets>";
//     excelFile += "<x:ExcelWorksheet>";
//     excelFile += "<x:Name>";
//     excelFile += "{worksheet}";
//     excelFile += "</x:Name>";
//     excelFile += "<x:WorksheetOptions>";
//     excelFile += "<x:DisplayGridlines/>";
//     excelFile += "</x:WorksheetOptions>";
//     excelFile += "</x:ExcelWorksheet>";
//     excelFile += "</x:ExcelWorksheets>";
//     excelFile += "</x:ExcelWorkbook>";
//     excelFile += "</xml>";
//     excelFile += "<![endif]-->";
//     excelFile += "</head>";
//     excelFile += "<body>";
//     excelFile += excel;
//     excelFile += "</body>";
//     excelFile += "</html>";
//
//     var uri = 'data:application/vnd.ms-excel;charset=utf-8,' + encodeURIComponent(excelFile);
//
//     var link = document.createElement("a");
//     link.href = uri;
//
//     link.style = "visibility:hidden";
//     link.download = FileName;  //格式默认为.xls
//
//     document.body.appendChild(link);
//     link.click();
//     document.body.removeChild(link);
// }