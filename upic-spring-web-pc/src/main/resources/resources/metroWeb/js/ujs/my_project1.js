var dataUrl = "/common/getProjectByGuidanceNum";
var getProjectInfoUrl = "/common/getProjectInfo";
var searchKeyWordUrl = "";
var getProjectTypeUrl = "/common/getAllProjectCategory";
var getStatusUrl = "/common/getCollege";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {};

$(function () {
    pageSize = $("#select-small").children('option:selected').text()
    getData(pageNum, dataUrl);
    commonAjax(getProjectTypeUrl, null, "addProjectType", "GET");
    commonAjax(getStatusUrl, null, "addCollegeUrl", "GET");
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
    htmls += "<option value='4' class='yellow'>项目状态筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].college + "</option>";
    }
    $("#getStatus").html(htmls);
}

function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        var status = "";

        switch (data[i].implementationProcess) {
            case ("SAVED"):
                status = "已保存";
                break;
            case ("IN_AUDIT"):
                status = "待初审";
                break;
            case ("IN_AUDIT_AGAIN"):
                status = "待复审";
                break;
            case ("IN_AUDIT_FINAL"):
                status = "待终审";
                break;
            case ("AUDITED"):
                status = "已审核";
                break;
            case ("ENROLLMENT"):
                status = "报名中";
                break;
            case ("HAVE_IN_HAND"):
                status = "进行中";
                break;
            case ("COMPLETED"):
                status = "已完成";
                break;
            case ("CHECKING"):
                status = "待初验";
                break;
            case ("CHECKING_AGAIN"):
                status = "待复验";
                break;
            case ("CHECKING_FINAL"):
                status = "待终验";
                break;
            case ("CHECKED"):
                status = "已验收";
                break;
            case ("IN_AUDIT_FAIL"):
                status = "待初审失败";
                break;
            case ("IN_AUDIT_AGAIN_FAIL"):
                status = "待复审失败";
                break;
            case ("IN_AUDIT_FINAL_FAIL"):
                status = "待终审失败";
                break;
            case ("CHECKING_FAIL"):
                status = "待初验失败";
                break;
            case ("CHECKING_AGAIN_FAIL"):
                status = "待复验失败";
                break;
            case ("CHECKING_FINAL_FAIL"):
                status = "待终验失败";
                break;
            default:
        }

        htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].projectNum + "'/></td>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td>" + data[i].id + "</td>";
        htmls += "<td>" + data[i].projectCategory + "</td>";
        htmls += "<td>" + data[i].projectName + "</td>";
        htmls += "<td>" + data[i].integral + "</td>";
        htmls += "<td>" + data[i].maximum + "</td>";
        htmls += "<td>" + (data[i].integral * data[i].maximum) + "</td>";
        htmls += "<td>" + getDate(data[i].startTime, "yyyy-MM-dd hh:mm") + "</td>";
        htmls += "<td>" + status + "</td>";
        htmls += "<td>";
        htmls += "<a href='#mymodal1' data-toggle='modal'><div class='message_div' onclick=commonAjax('" + getProjectInfoUrl + "','projectNum=" + data[i].projectNum + "','getProjectInfo','GET','" + (i + 1) + "')>查看详情</div></a></td>";
        htmls += "</tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function getProjectInfo(result, j) {
    var htmlss = "";
    var status = "";
    switch (result.implementationProcess) {
        case ("SAVED"):
            status = "已保存";
            break;
        case ("IN_AUDIT"):
            status = "待初审";
            break;
        case ("IN_AUDIT_AGAIN"):
            status = "待复审";
            break;
        case ("IN_AUDIT_FINAL"):
            status = "待终审";
            break;
        case ("AUDITED"):
            status = "已审核";
            break;
        case ("ENROLLMENT"):
            status = "报名中";
            break;
        case ("HAVE_IN_HAND"):
            status = "进行中";
            break;
        case ("COMPLETED"):
            status = "已完成";
            break;
        case ("CHECKING"):
            status = "待初验";
            break;
        case ("CHECKING_AGAIN"):
            status = "待复验";
            break;
        case ("CHECKING_FINAL"):
            status = "待终验";
            break;
        case ("CHECKED"):
            status = "已验收";
            break;
        case ("IN_AUDIT_FAIL"):
            status = "待初审失败";
            break;
        case ("IN_AUDIT_AGAIN_FAIL"):
            status = "待复审失败";
            break;
        case ("IN_AUDIT_FINAL_FAIL"):
            status = "待终审失败";
            break;
        case ("CHECKING_FAIL"):
            status = "待初验失败";
            break;
        case ("CHECKING_AGAIN_FAIL"):
            status = "待复验失败";
            break;
        case ("CHECKING_FINAL_FAIL"):
            status = "待终验失败";
            break;
        default:
    }
    htmlss += "<div class='block-fluid'>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>编号</div>";
    htmlss += "<div class='span3'>" + (parseInt(pageNum) * parseInt(pageSize) + j) + "</div>";
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





