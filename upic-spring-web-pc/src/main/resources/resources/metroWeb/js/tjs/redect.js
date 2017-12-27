//加载所有项目类别
//增加项目类别
//修改项目类别
var getAllProjectCategoryUrl = "/common/getAllProjectCategory";
var addProjectCategoryUrl = "/common/addProjectCategory";
var updateProjectCategoryUrl = "/common/updateProjectCategory";

$(function () {

    getDate();
    tabel_click();

    $(".btn-primary").click(function () {

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

function addProjectCategory(cn) {
    $.ajax({
        type: "GET",
        url: addProjectCategoryUrl,
        data: {
            "categoryName": cn
        },
        success: function (result) {
            alert(result)
        }
    });
}

function addhtml(res) {
    var htmls = "";
    for (var i = 0; i < res.length; i++) {
        htmls += "<tr>";
        htmls += "<td>";
        htmls += "<span>" + res[i].categoryName + "</span>";
        htmls += "<button id='" + res[i].id + "'' type='submit' class='btn btn-small'>编辑</button>";
        htmls += "</td>";
        htmls += "<td>";
        htmls += "<span>" + res[i + 1].categoryName + "</span>";
        htmls += "<button id='" + res[i + 1].id + "'' type='submit'  class='btn btn-small'>编辑</button>";
        htmls += "</td>";
        htmls += "</tr>";
        i++;
    }
}

function tabel_click() {
    $("td>button").click(function () {
        var xg = $(this).html();
        var id = $(this).attr("id");
        if (xg == '编辑') {
            var old = $(this).parent('td').find('span').html();//获取编辑后的值
            $(this).parent('td').find('span').html(old);
            $(this).html('保存');
        } else if (xg == '保存') {
            updateProjectCategory(id, old);
            $(this).html('编辑');
            getDate();
        }
        ;
    })
}

function updateProjectCategory(categoryid, old) {
    $.ajax({
        type: "GET",
        url: updateProjectCategoryUrl,
        data: {
            "id": categoryid,
            "categoryName": old
        },
        success: function (result) {
            alert("已修改");
        }
    });
}