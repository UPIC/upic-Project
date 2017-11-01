var data3Url = "";
var pageSize3 = 0;
var totalPages3 = -1;
var pageNum3 = 0;
var requestData3 = {};

function getCaoZuoRiZhi() {
    pageSize3 = $("#select-small").children('option:selected').text()
    getData3(pageNum3, data3Url);
    registSelect("operationType");
    registSelect("operationStatus");
}

//ajax获取页面内容
function getData3(pageNum3, data3Url, pageSize3) {
    requestData3.size = parseInt($("#select-small1").children('option:selected')
        .text());
    requestData3.page = pageNum3;
    $.ajax({
        type: "GET",
        url: data3Url,
        data: requestData3,
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (result) {
            if (result != "" && result != null) {
                addHtmls2(result, pageNum3, requestData3);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

function addHtmls3(datas) {
    totalPage3 = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].logNum + "'/></td>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum3) * parseInt(pageSize3) + i + 1) + "</td>";
        htmls += "<td>" + data[i].createTime + "</td>";
        htmls += "<td>" + data[i].userName + "</td>";
        htmls += "<td>" + data[i].operationType + "</td>";
        htmls += "<td>" + data[i].operationStatus + "</td>";
        htmls += "<td>" + data[i].content + "</td>";
        htmls += "<td>";
        htmls += "<div class='message_div'><a href='#mymodal9' data-toggle='modal'><span onclick=commonAjax('" + data3Url + "','logNum=" + data[i].logNum + "','getLogInfo','GET')>【查看详情】</span></a></div></td>";
        htmls += "</tr>";
    }
    $("#data3").html(htmls);
    page(datas, data3Url, datas.size, datas.number);
}

function getLogInfo(data) {
    var htmlss = "";
    var statuss = "";
    if (true) {
    }

    htmlss += "<div class='control-group'>";
    htmlss += "<label class='control-label'>创建时间</label>";
    htmlss += "<div class='controls'>";
    htmlss += "<span>" + data.creatTime + "</span>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='control-group'>";
    htmlss += "<label class='control-label'>操作员姓名</label>";
    htmlss += "<div class='controls'>";
    htmlss += "<span>" + data.operator + "</span>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='control-group'>";
    htmlss += "<label class='control-label'>操作员工号</label>";
    htmlss += "<div class='controls'>";
    htmlss += "<span>" + data.operatorNum + "</span>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='control-group'>";
    htmlss += "<label class='control-label'>操作状态</label>";
    htmlss += "<div class='controls'>";
    htmlss += "<span>" + statuss + "</span>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='control-group'>";
    htmlss += "<label class='control-label'>操作内容</label>";
    htmlss += "<div class='controls'>";
    htmlss += "<span>" + data.content + "</span>";
    htmlss += "</div>";
    htmlss += "</div>";
    $("#getLogInfo").html(htmlss);
}
