var dataUrl = "/common/getProjectByGuidanceNum";
var getProjectInfo="/common/getProjectInfo";
var searchKeyWordUrl = "";
var getProjectTypeUrl = "/common/getAllProjectCategory";
var getStatusUrl = "/common/getCollege";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {

};

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

function addCollegeUrl(res) {
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
        var status = "";

        if (data[i].implementationProcess === "SAVED") {
            status = "已保存"
        } else if (data[i].implementationProcess === "IN_AUDIT") {
            status = "审核中"
        } else if (data[i].implementationProcess === "NOT_PASS") {
            status = "未通过"
        } else if (data[i].implementationProcess === "AUDITED") {
            status = "已审核"
        } else if (data[i].implementationProcess === "HAVE_IN_HAND") {
            status = "进行中"
        } else {
            status = "已完成"
        }

        htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].integralLogId.projectNum + "'/></td>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td>" + data[i].id + "</td>";
        htmls += "<td>" + data[i].projectCategory + "</td>";
        htmls += "<td>" + data[i].projectName + "</td>";
        htmls += "<td>" + data[i].integral + "</td>";
        htmls += "<td>" + data[i].maximum + "</td>";
        htmls += "<td>" + (data[i].integral * data[i].maximum) + "</td>";
        htmls += "<td>" + data[i].creatTime + "</td>";
        htmls += "<td>" + status + "</td>";
        htmls += "<td>";
        htmls += "<a href='#mymodal1' data-toggle='modal'><div class='message_div' onclick=commonAjax('"+getProjectInfo+"','projectNum=" + data[i].integralLogId.projectNum + "','getProjectInfo','GET')>查看详情</div></a></td>";
        htmls += "</tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function getProjectInfo(result) {
    var htmlss = "";
    var status = "";

        if (result.implementationProcess === "SAVED") {
            status = "已保存"
        } else if (result.implementationProcess === "IN_AUDIT") {
            status = "审核中"
        } else if (result.implementationProcess === "NOT_PASS") {
            status = "未通过"
        } else if (result.implementationProcess === "AUDITED") {
            status = "已审核"
        } else if (result.implementationProcess === "HAVE_IN_HAND") {
            status = "进行中"
        } else {
            status = "已完成"
        }
        htmlss += "<div class='block-fluid'>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>编号</div>";
        htmlss += "<div class='span3'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</div>";
        htmlss += "<div class='span3'>代码</div>";
        htmlss += "<div class='span3'>" + result.id + "</div>";
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目申请日期</div>";
        htmlss += "<div class='span3'>" + result.creatTime + "</div>";
        htmlss += "<div class='span3'>状态</div>";
        htmlss += "<div class='span3'>" + status + "</div>";
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目类别</div>";
        htmlss += "<div class='span3'>" + result.projectCategory + "</div>";
        htmlss += "<div class='span3'>申报单位</div>";
        htmlss += "<div class='span3'>" + result.declareUnit + "</div>";
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目名称</div>";
        htmlss += "<div class='span3'>" + result.projectName + "</div>";
        htmlss += "<div class='span3'>负责人</div>";
        htmlss += "<div class='span3'>" + result.guidanceMan + "</div>";
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目内容</div>";
        htmlss += "<div class='span9'>" + result.content + "</div>";
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>评价标准与形式</div>";
        htmlss += "<div class='span9'>" + result.checkAssessmentCriteraAndForm + "</div>";
        htmlss += "</div>";
        htmlss += "</div>";
        $("#getProjectInfo").html(htmlss);
}





