var dataUrl = "/stu/searchIntegralLog";
var getIntegralLogByIntegralLogId = "/common/getIntegralLogByIntegralLogId";
var getProjectTypeUrl = "/common/getAllProjectCategory";
// var getStatusUrl = "/common/getCollege";
var searchKeyWordUrl = "/common/integralLogSearchBarWithoutStatus";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var exportExcelUrl = "/common/exportIntegralLogByGuidanceNum";
var requestData = {};

$(function () {
    pageSize = $("#select-small").children('option:selected').text();
    getData(pageNum, dataUrl);
    commonAjax(getProjectTypeUrl, null, "addProjectType", "GET");
    // commonAjax(getStatusUrl, "rank=3", "addStatus", "GET");
    registSelect("projectCategory");
    // registSelect("college");

    $("#exportBtn").click(function () {
        var baseModels = ["projectNum", "studentNum", "event", "integral", "projectName", "student", "projectCategory", "college", "clazz", "status"];
        var str = JSON.stringify(baseModels);
        var form = $("<form></form>").attr("action", exportExcelUrl).attr("method", "GET");
        form.append($("<input></input>").attr("type", "hidden").attr("name", "baseModel").attr("value", str));
        var formKeyValue = appendForm(requestData);
        for (var i = 0; i < formKeyValue.key.length; i++) {
            form.append($("<input></input>").attr("type", "hidden").attr("name", formKeyValue.key[i]).attr("value", formKeyValue.value[i]));
        }
        form.appendTo('body').submit().remove();
        form.submit();
    })
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

// function addStatus(res) {
//     var data = res.content;
//     var htmls = "";
//     htmls += "<option value='4' class='yellow'>学院筛选...</option>";
//
//     for (var i = 0; i < data.length; i++) {
//         htmls += "<option value='" + (i + 4) + "'>" + data[i].college + "</option>";
//     }
//     $("#college").html(htmls);
// }

function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        var color = "";
        var statusC = "";
        if (data[i].status === "SAVE") {
            color = "danger";
            statusC = "保存";
        } else if (data[i].status === "PENDING_AUDIT_BEFORE") {
            color = "danger";
            statusC = "待初审";
        } else if (data[i].status === "PENDING_AUDIT_BEFORE_FAIL") {
            color = "danger";
            statusC = "待初审失败";
        } else if (data[i].status === "PENDING_AUDIT") {
            color = "danger";
            statusC = "待学院审";
        } else if (data[i].status === "PENDING_AUDIT_FAIL") {
            color = "danger";
            statusC = "待学院审失败";
        } else if (data[i].status === "PENDING_AUDIT_AGAIN") {
            color = "danger";
            statusC = "待部门审";
        } else if (data[i].status === "PENDING_AUDIT_AGAIN_FAIL") {
            color = "danger";
            statusC = "待部门审失败";
        } else if (data[i].status === "PENDING_AUDIT_FINAL") {
            color = "danger";
            statusC = "待团委审";
        } else if (data[i].status === "PENDING_AUDIT_FINAL_FAIL") {
            color = "danger";
            statusC = "待团委审失败";
        } else if (data[i].status === "HAVEPASSED") {
            color = "success";
            statusC = "审核成功";
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
        htmls += "<a href='#mymodal1' data-toggle='modal'><div class='message_div' onclick=commonAjax('" + getIntegralLogByIntegralLogId + "','projectNum=" + data[i].integralLogId.projectNum + "','getProjectInfo','GET','" + (i + 1) + "')>查看详情</div></a></td>";
        htmls += "</tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function getProjectInfo(result, j) {
    var statusC = "";
    var htmlss = "";

    if (result.status === "SAVE") {
        statusC = "保存";
    } else if (result.status === "PENDING_AUDIT_BEFORE") {
        statusC = "待初审";
    } else if (result.status === "PENDING_AUDIT_BEFORE_FAIL") {
        statusC = "待初审失败";
    } else if (result.status === "PENDING_AUDIT") {
        statusC = "待学院审";
    } else if (result.status === "PENDING_AUDIT_FAIL") {
        statusC = "待学院审失败";
    } else if (result.status === "PENDING_AUDIT_AGAIN") {
        statusC = "待部门审";
    } else if (result.status === "PENDING_AUDIT_AGAIN_FAIL") {
        statusC = "待部门审失败";
    } else if (result.status === "PENDING_AUDIT_FINAL") {
        statusC = "待团委审";
    } else if (result.status === "PENDING_AUDIT_FINAL_FAIL") {
        statusC = "待团委审失败";
    } else if (result.status === "HAVEPASSED") {
        statusC = "审核成功";
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
    htmlss += "<div class='span3'>" + getDate(result.creatTime, "yyyy-MM-dd hh:mm") + "</div>";
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

function splitJson(json) {
    if (json == null || json === "") {
        return "";
    }
    var projectCategorys = new Array();
    projectCategorys = json.split("/");
    return projectCategorys[0];
}