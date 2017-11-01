/*
 * @Author: Marte
 * 我的积分
 * @Date:   2017-09-24 10:17:37
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-10-11 22:55:01
 */

var getAllIntegralLogByStudentNum = "/stu/getAllIntegralLogByStudentNum";
var getProjectCategoryUrl = "/common/getAllProjectCategory";
var getIntegralLogByIntegralLogId = "/common/getIntegralLogByIntegralLogId";
var types = "GET";

$(function () {
    /*
     加载数据
     */
    ajaxs("studentNum=1522110240", "showAll", getAllIntegralLogByStudentNum)
})


function ajaxs(datas, method, urls, j) {
    $.ajax({
        type: types, // 提交方式
        url: urls,// 路径
        data: datas,//

        beforeSend: function (XMLHttpRequest) {
// progress.inc();
        },
        success: function (result) {// 返回数据根据结果进行相应的处理
            addHtmls(result, method, j)
        },
        complete: function (XMLHttpRequest, textStatus) {
// progress.done(true);
        },
        error: function () {
// progress.done(true);
        }
    });
}


function addHtmls(result, method, j) {
    var htmls = "";
    var htmlss = "";
    if (method === "showAll") {
        for (var i = 0; i < result.content.length; i++) {
            var color = "";
            var statusC = "";
            htmls += "<tr>";
            htmls += "<td class='center_td'>" + i + 1 + "</td>";
            if (result.content[i].status === "PENDING_AUDIT") {
                color = "danger";
                statusC = "待审核";
            } else if (result.content[i].status === "HAVEPASSED") {
                color = "success";
                statusC = "已通过";
            } else if (result.content[i].status === "FAILURE_TO_PASS_THE_AUDIT") {
                color = "danger";
                statusC = "未通过";
            } else if (result.content[i].status === "ALREADY_SIGN_UP") {
                color = "danger";
                statusC = "已报名";
            } else if (result.content[i].status === "SIGNED_IN") {
                color = "success";
                statusC = "已签到";
            } else if (result.content[i].status === "COMPLETED") {
                color = "success";
                statusC = "已完成";
            }
            htmls += "<td class='center_td'><button class='btn btn-mini btn-" + color + "'>" + statusC + "</button></td>";
            htmls += "<td>" + splitJson(result.content[i].event) + "</td>";
            htmls += "<td>" + result.content[i].projectName + "</td>";
            htmls += "<td>" + result.content[i].college + "</td>";
            htmls += "<td>" + result.content[i].integral + "</td>";
            htmls += "<td>";
            htmls += "<a href='#mymodal1' data-toggle='modal'><div class='message_div' onclick=ajaxs('projectNum=" + result.content[i].integralLogId.projectNum + "','details','/common/getIntegralLogByIntegralLogId','" + (i + 1) + "')>查看详情</div></a></td>";
            htmls += "</tr>";
        }
        $("#showAll").html(htmls);
    } else if (method === "details") {
        var statusC = "";
        if (result.status === "PENDING_AUDIT") {
            statusC = "待审核";
        } else if (result.status === "HAVEPASSED") {
            statusC = "已通过";
        } else if (result.status === "FAILURE_TO_PASS_THE_AUDIT") {
            statusC = "未通过";
        } else if (result.status === "ALREADY_SIGN_UP") {
            statusC = "已报名";
        } else if (result.status === "SIGNED_IN") {
            statusC = "已签到";
        } else if (result.status === "COMPLETED") {
            statusC = "已完成";
        }

        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>编号</div>";
        htmlss += "<div class='span3'>" + j + "</div>";
        htmlss += "<div class='span3'>代码</div>";
        htmlss += "<div class='span3'>" + result.integralLogId.projectNum + "</div>";
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目申请日期</div>";
        htmlss += "<div class='span3'>" + result.creatTime + "</div>";
        htmlss += "<div class='span3'>状态</div>";
        htmlss += "<div class='span3'>" + statusC + "</div>";
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目类别</div>";
        htmlss += "<div class='span3'>" + splitJson(result.event) + "</div>";
        htmlss += "<div class='span3'>所属学院</div>";
        htmlss += "<div class='span3'>" + result.college + "</div>";
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目名称</div>";
        htmlss += "<div class='span3'>" + result.projectName + "</div>";
        htmlss += "<div class='span3'>负责人</div>";
        htmlss += "<div class='span3' id='guidanceMan" + result.integralLogId.projectNum + "'>" + "1" + "</div>";
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目内容</div>";
        htmlss += "<div class='span9' id='content" + result.integralLogId.projectNum + "'>" + "1" + "</div>";
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>评价标准与形式</div>";
        htmlss += "<div class='span9' id='checkAssessmentCriteraAndForm" + result.integralLogId.projectNum + "'>" + "1" + "</div>";
        htmlss += "</div>";

        $("#details").html(htmlss);

        projectAjaxs("projectNum=" + result.integralLogId.projectNum, "/common/getProjectInfo", result);
    }
}

function projectAjaxs(datas, url, project) {
    $.ajax({
        type: types, // 提交方式
        url: url,// 路径
        data: datas,//

        beforeSend: function (XMLHttpRequest) {
        },
        success: function (result) {// 返回数据根据结果进行相应的处理
            if (project.type === 'SIGN_IN') {
                $("#guidanceMan" + project.integralLogId.projectNum).html(result.guidanceMan);
                $("#content" + project.integralLogId.projectNum).html(result.content);
                $("#checkAssessmentCriteraAndForm" + project.integralLogId.projectNum).html(result.checkAssessmentCriteraAndForm);
            } else {
                $("#guidanceMan" + project.integralLogId.projectNum).html("自主申报");
                $("#content" + project.integralLogId.projectNum).html("自主申报");
                $("#checkAssessmentCriteraAndForm" + project.integralLogId.projectNum).html("自主申报");
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

function splitJson(json) {
    var projectCategorys = new Array();
    projectCategorys = json.split("/");
    return projectCategorys[0];
}