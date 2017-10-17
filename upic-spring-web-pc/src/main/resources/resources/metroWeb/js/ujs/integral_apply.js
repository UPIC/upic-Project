/*
 * @Author: Marte
 * @Date:   2017-10-11 12:12:10
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-10-11 23:44:01
 */
var getProjectWithoutSignUp = "/common/getProjectWithoutSignUp";//获取活动列表
var getProjectInfo = "/common/getProjectInfo";//查看活动详情
//少一个活动报名

$(function () {
    /*
     加载数据
     */
    ajaxs("", "showAll", getProjectWithoutSignUp);

})

function ajaxs(datas, method, urls) {
    $.ajax({
        type: "GET", // 提交方式
        url: urls,// 路径
        data: datas,//

        beforeSend: function (XMLHttpRequest) {
// progress.inc();
        },
        success: function (result) {// 返回数据根据结果进行相应的处理
            addHtmls(result, method)
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
    var htmlss = "";
    if (method === "showAll") {
        for (var i = 0; i < result.content.length; i++) {
            htmls += "<tr>";
            htmls += "<td class='center_td'>" + (i + 1) + "</td>";
            htmls += "<td>" + result.content[i].projectCategory + "</td>";
            htmls += "<td>" + result.content[i].projectName + "</td>";
            htmls += "<td>" + result.content[i].declareUnit + "</td>";
            htmls += "<td>" + result.content[i].integral + "</td>";
            htmls += "<td>" + reslut.content[i].signUpEndTimesignUpEndTimesignUpEndTimesignUpEndTime + "</td>";
            htmls += "<td>";
            htmls += "<a href='#mymodal1' data-toggle='modal'><div class='message_div' onclick='ajaxs('" + result.content[i].integralLogId.projectNum + "','details','getIntegralLogByIntegralLogId')'>报名</div></a></td>";
            htmls += "</tr>";
        }
        $("#apply").html(htmls);
    } else {
        for (var i = 0; i < result.length; i++) {
            htmlss += "<div class='modal-header'>";
            htmlss += "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
            htmlss += "<h4 id='mymodallabel1'>详情</h4>";
            htmlss += "</div>";

            htmlss += "<div class='modal-body'>";
            htmlss += "<div class='row-fluid'>";
            htmlss += "<div class='block-fluid'>";
            htmlss += "     <div class='row-form clearfix'>";
            htmlss += "       <div class='span3'>编号</div>";
            htmlss += "       <div class='span3'>" + (i + 1) + "</div>";
            htmlss += "       <div class='span3'>报名截止时间</div>";
            htmlss += "    <div class='span3'>" + reslut[i].signUpEndTime + "</div>";
            htmlss += "  </div>";
            htmlss += " <div class='row-form clearfix'>";
            htmlss += "   <div class='span3'>项目开始时间</div>";
            htmlss += "   <div class='span3'>" + reslut[i].startTime + "</div>";
            htmlss += "   <div class='span3'>项目结束时间</div>";
            htmlss += "   <div class='span3'>" + reslut[i].endTime + "</div>";
            htmlss += "   </div>";
            htmlss += "   <div class='row-form clearfix'>";
            htmlss += "     <div class='span3'>项目类别</div>";
            htmlss += "     <div class='span3'>" + splitJson(result[i].event) + "</div>";
            htmlss += "     <div class='span3'>所属单位</div>";
            htmlss += "     <div class='span3'>" + reslut[i].college + "</div>";
            htmlss += "   </div>";
            htmlss += "    <div class='row-form clearfix'>";
            htmlss += "     <div class='span3'>项目名称</div>";
            htmlss += "    <div class='span3'>" + reslut[i].projectName + "</div>";
            htmlss += "    <div class='span3'>负责人</div>";
            htmlss += "    <div class='span3'>123</div>";
            htmlss += "  </div>";
            htmlss += " <div class='row-form clearfix'>";
            htmlss += "    <div class='span3'>项目内容</div>";
            htmlss += "    <div class='span9'>123</div>";
            htmlss += "  </div>";
            htmlss += "  <div class='row-form clearfix'>";
            htmlss += "    <div class='span3'>评价标准与形式</div>";
            htmlss += "    <div class='span9'>123</div>";
            htmlss += "  </div>";
            htmlss += " </div>";
            htmlss += "  <div class='dr'><span></span></div>";
            htmlss += " </div>";
            htmlss += "</div>";

            htmlss += " <div class='modal-footer'>";
            htmlss += "   <button class='btn btn-primary' data-dismiss='modal' aria-hidden='true' onclick=''>报名</button>";
            htmlss += "   <button class='btn btn-default' data-dismiss='modal' aria-hidden='true'>关闭</button>";
            htmlss += " </div>";
        }
        $("#mymodal1").html(htmlss);
    }
}