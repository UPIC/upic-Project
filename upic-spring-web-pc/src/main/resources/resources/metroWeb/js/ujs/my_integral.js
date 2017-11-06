var getAllIntegralLogByStudentNum = "/stu/getAllIntegralLogByStudentNum";
var getProjectCategoryUrl = "/common/getAllProjectCategory";
var getIntegralLogByIntegralLogId = "/common/getIntegralLogByIntegralLogId";
var getProjectTypeUrl = "/common/getAllProjectCategory";
var getStatusUrl = "/common/getCollege";
var searchKeyWordUrl = "";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {};

$(function () {
    pageSize = $("#select-small").children('option:selected').text()
    getData(pageNum, dataUrl);
    commonAjax(getProjectTypeUrl, null, "addProjectType", "GET");
    commonAjax(getStatusUrl, null, "addStatus", "GET");
    registSelect("projectCategory");
    registSelect("getStatus");
})

function addProjectType(res) {
    var data = res.content;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>项目类别筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].categoryName + "</option>";
    }
    $("#projectCategory").html(htmls);
}

function addStatus(res) {
    var data = res.content;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>学院筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].status + "</option>";
    }
    $("#getStatus").html(htmls);
}

function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
            var color = "";
            var statusC = "";
            htmls += "<tr>";
            htmls += "<td class='center_td'>" + i + 1 + "</td>";
            if (data[i].status === "PENDING_AUDIT") {
                color = "danger";
                statusC = "待审核";
            } else if (data[i].status === "HAVEPASSED") {
                color = "success";
                statusC = "已通过";
            } else if (data[i].status === "FAILURE_TO_PASS_THE_AUDIT") {
                color = "danger";
                statusC = "未通过";
            } else if (data[i].status === "ALREADY_SIGN_UP") {
                color = "danger";
                statusC = "已报名";
            } else if (data[i].status === "SIGNED_IN") {
                color = "success";
                statusC = "已签到";
            } else if (data[i].status === "COMPLETED") {
                color = "success";
                statusC = "已完成";
            }
            htmls += "<tr>";
            htmls += "<td class='center_td'>" + i + 1 + "</td>";
            htmls += "<td class='center_td'><button class='btn btn-mini btn-" + color + "'>" + statusC + "</button></td>";
            htmls += "<td>" + splitJson(data[i].event) + "</td>";
            htmls += "<td>" + data[i].projectName + "</td>";
            htmls += "<td>" + data[i].college + "</td>";
            htmls += "<td>" + data[i].integral + "</td>";
            htmls += "<td>";
            htmls += "<a href='#mymodal1' data-toggle='modal'><div class='message_div' onclick=commonAjax('"+getIntegralLogByIntegralLogId+"','projectNum=" + data[i].integralLogId.projectNum + "','getProjectInfo','GET')>查看详情</div></a></td>";
            htmls += "</tr>";
        }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function getProjectInfo(result) {
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

        $("#getProjectInfo1").html(htmlss);
    }
}

function splitJson(json) {
    var projectCategorys = new Array();
    projectCategorys = json.split("/");
    return projectCategorys[0];
}