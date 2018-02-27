var dataUrl = "/common/getPrize"; // status = SHELVES
var addPrize = "/common/addPrize"; // 上传
var changstatusUrl = "/common/changePrizeStatus";
var deletePrizeUrl = "/common/deletePrize";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {};

$(function () {
    pageSize = $("#select-small").children('option:selected').text()
    getData(pageNum, dataUrl);
})

function shangChuan() {
    var formData = new FormData();
    formData.append("prizeName", $("#pName").val());
    formData.append("score", $("#pCoin").val());
    formData.append("content", $("#pContent").val());
    formData.append("file", $("#pPic")[0].files[0]);
    $.ajax({
        type: 'POST',
        url: addPrize,
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
            alert("已上传");
            getData(pageNum, dataUrl);
        },
        error: function (err) {
            alert("上传失败！");
        }
    });
}

function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        var status = "";

        if (data[i].status === "SHELVES") {
            status = "已上架";
        } else {
            status = "已下架";
        }

        htmls += "<tr>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td>" + data[i].prizeName + "</td>";
        htmls += "<td class='td-line'><span>" + data[i].content + "</span></td>";
        htmls += "<td>" + status + "</td>";
        htmls += "<td>" + data[i].score + "</td>";
        htmls += "<td>" + getDate(data[i].creatTime, "yyyy-MM-dd") + "</td>";
        htmls += "<td class='center_td'>";
        htmls += "<button class='btn btn-small' onclick=putAway('" + data[i].id + "')>上架</button>";
        htmls += "<button class='btn btn-small down' onclick=underCarriage('" + data[i].id + "')>下架</button>";
        htmls += "</td>";
        htmls += "<td class='center_td'>";
        htmls += "<a data-toggle='modal'><button class='btn btn-small' onclick=deletePrize('" + data[i].id + "')>删除</button></a>";
        htmls += "</td>";
        htmls += "</tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function deletePrize(id) {
    $.ajax({
        type: "GET",
        url: deletePrizeUrl,
        data: {
            id: id
        },
        success: function (result) {
            if (result === "SUCCESS") {
                alert("删除成功！");
                getData(pageNum, dataUrl);
            } else {
                alert("删除失败！");
            }
        }
    });
}

function putAway(prizeNum) {
    $.ajax({
        type: "GET",
        url: changstatusUrl,
        data: {
            prizeId: prizeNum,
            status: "上架"
        },
        success: function (result) {
            if (result === "SUCCESS") {
                alert("已上架");
                getData(pageNum, dataUrl);
            } else {
                alert("上架失败");
            }
        }
    });
}

function underCarriage(prizeNum) {
    $.ajax({
        type: "GET",
        url: changstatusUrl,
        data: {
            prizeId: prizeNum,
            status: "下架"
        },
        success: function (result) {
            if (result === "SUCCESS") {
                alert("已下架");
                getData(pageNum, dataUrl);
            } else {
                alert("下架失败");
            }
        }
    });
}