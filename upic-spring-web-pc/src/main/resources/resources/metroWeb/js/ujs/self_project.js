
var getAllProjectCategory = "/common/getAllProjectCategory";//获取项目类别
var types = "GET";

$(function () {

    ajaxs("", "getProject", getAllProjectCategory);

})


function ajaxs(datas, method, urls) {
    // if (pageCount == page || pageCount == 0) {
    //     return;
    // }
    $.ajax({
        type: types, // 提交方式
        url: urls,// 路径
        data: datas,
        beforeSend: function (XMLHttpRequest) {
// progress.inc();
        },
        success: function (result) {// 返回数据根据结果进行相应的处理
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
    if (method == "getProject") {
        for (var i = 0; i < result.length; i++) {

            htmls += "<option value='category "+(i+1)+"'>"+result[i].categoryName+"</option>";

        }
        $("#getProject").html(htmls);
    }
}
//提交和点击按钮  点击事件 方法还没写
