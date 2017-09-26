/*
 * @Author: Marte
 * 积分申报
 * @Date:   2017-09-24 10:17:37
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-09-25 18:53:26
 */

/*
 获取项目类别，级别
 */

var getAllProjectCategory = "/common/getAllProjectCategory";
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
        async: false,
        beforeSend: function (XMLHttpRequest) {
// progress.inc();
        },
        success: function (result) {// 返回数据根据结果进行相应的处理
            pageCount = result.total;
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
            htmls += "               <div class='control-group'>";
            htmls += "                 <label class='control-label'>选择项目类别:</label>";
            htmls += "                       <div class='controls'>";
            htmls += "                          <select class='input-large m-wrap' tabindex='1'>";
            htmls += "                               <option value='category 1'>创新实践</option>";
            htmls += "                               <option value='category 2'>创业实践</option>";
            htmls += "                               <option value='category 3'>社会实践</option>";
            htmls += "                               <option value='category 4'>文体活动</option>";
            htmls += "                               <option value='category 5'>志愿服务</option>";
            htmls += "                               <option value='category 6'>讲座论坛</option>";
            htmls += "                              <option value='category 7'>社团工作</option>";
            htmls += "                              <option value='category 8'>国（境）内外交流</option>";
            htmls += "                              <option value='category 9'>生活能力</option>";
            htmls += "                              <option value='category 10'>思想品德</option>";
            htmls += "                              <option value='category 11'>非学校组织的素质拓展课程</option>";
            htmls += "                          </select>";
            htmls += "                      </div>";
            htmls += "                  </div>";

            htmls += "                 <div class='control-group'>";
            htmls += "                     <label class='control-label'>选择项目级别:</label>";
            htmls += "                     <div class='controls'>";
            htmls += "                         <select class='input-medium m-wrap' tabindex='1'>";
            htmls += "                             <option value='category 1'>市级</option>";
            htmls += "                             <option value='category 2'>校级</option>";
            htmls += "                             <option value='category 3'>院级</option>";
            htmls += "                         </select>";
            htmls += "                         <select class='input-medium m-wrap' tabindex='2'>";
            htmls += "                             <option value='category 1'>一等奖</option>";
            htmls += "                              <option value='category 2'>二等奖</option>";
            htmls += "                             <option value='category 3'>三等奖</option>";
            htmls += "                          </select>";
            htmls += "                      </div>";
            htmls += "                  </div>";
        }

        $("#" + method).append(htmls);
    }

}

