//加载所有项目类别
//增加项目类别
//修改项目类别
var getAllProjectCategoryUrl = "/common/getAllProjectCategory";
var addProjectCategoryUrl = "/common/addProjectCategory";

$(function () {
    getDate();

    $(".btn-primary").click(function () {
        var trElement = document.getElementById("data").childNodes;
        var tdElement = document.getElementById("tr" + trElement.length).childNodes;
        var tdNum = tdElement.length;
        if (tdNum == 1) {
            var htmlTd = "";
            htmlTd += "<td id='td" + trElement.length * 2 + "'>";
            htmlTd += "<input id='input" + trElement.length * 2 + "' type='text'  class='text' value='NewProjectCategory' />";
            htmlTd += "<button class='btn btn-small' onclick=saveNewProjectCategory('input" + trElement.length * 2 + "')>保存</button>";
            htmlTd += "</td>";
            $("#tr" + trElement.length).append(htmlTd);
        } else {
            var htmlTr = "";
            htmlTr += "<tr id='tr" + (trElement.length + 1) + "'>";
            htmlTr += "<td id='td" + (trElement.length * 2 + 1) + "'>";
            htmlTr += "<input id='input" + (trElement.length * 2 + 1) + "' type='text'  class='text' value='NewProjectCategory' />";
            htmlTr += "<button class='btn btn-small' onclick=saveNewProjectCategory('input" + (trElement.length * 2 + 1) + "')>保存</button>";
            htmlTr += "</td>";
            htmlTr += "</tr>";
            $("#data").append(htmlTr);
        }
    });
});

function getDate() {
    $.ajax({
        type: "GET",
        url: getAllProjectCategoryUrl,
        success: function (result) {
            var res = result.content;
            addhtml(res);
        }
    });
}

function addhtml(res) {
    var htmls = "";
    for (var i = 0; i < res.length; i++) {
        htmls += "<tr id='tr" + (i / 2 + 1) + "'>";
        htmls += "<td id='td" + (i + 1) + "'>";
        htmls += "<span>" + res[i].categoryName + "</span>";
        htmls += "</td>";
        if (i + 1 >= res.length) {
            break;
        }
        htmls += "<td id='td" + (i + 2) + "'>";
        htmls += "<span>" + res[i + 1].categoryName + "</span>";
        htmls += "</td>";
        htmls += "</tr>";
        i++;
    }
    $("#data").html(htmls);
}

function saveNewProjectCategory(id) {
    var categoryName = $("#" + id).val();

    $.ajax({
        type: "GET",
        url: addProjectCategoryUrl,
        data: {
            categoryName: categoryName
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (result) {
            if (result === "SUCCESS") {
                alert("编辑成功！")
                getDate();
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}
