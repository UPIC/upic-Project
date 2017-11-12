var dataUrl = "/common/getProject";
var exportExcelUrl = "/common/exportProject";
var searchKeyWordUrl = "/common/projectSearchBar";
var getProjectInfo = "/common/getProjectInfo";
var getProjectTypeUrl = "/common/getAllProjectCategory";
var getProjectStatusUrl = "";
var getProjectCollegeUrl = "/common/getCollege";
var getProjectCollege = "";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {};

$(function () {
    pageSize = $("#select-small").children('option:selected').text();
    getData(pageNum, dataUrl);
    commonAjax(getProjectTypeUrl, null, "addProjectType", "GET");
    commonAjax(getProjectCollegeUrl, null, "addProjectCollege", "GET");
    registSelect("getProjectType");
    registSelect("getProjectStatus");
    registSelect("getProjectCollege");

    $("#exportBtn").click(function () {
        var baseModels = ["projectNum", "declareUnit", "projectName", "guidanceMan", "guidanceNum", "projectCategory", "integral", "startTime", "endTime", "maximum"];
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
    $("#getProjectType").html(htmls);
}

function addProjectCollege(res) {
    var data = res.content;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>学院筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].college + "</option>";
    }
    $("#getProjectCollege").html(htmls);
}
function addProjectStatus(res) {
    var data = res.content;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>项目状态筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].college + "</option>";
    }
    $("#getCollege").html(htmls);
}

function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        var statuss = "";
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
        htmls += "<td>" + data[i].projectNum + "</td>";
        htmls += "<td>" + data[i].projectCategory + "</td>";
        htmls += "<td>" + data[i].projectName + "</td>";
        htmls += "<td>" + data[i].declareUnit + "</td>";
        htmls += "<td>" + data[i].integral + "</td>";
        htmls += "<td>" + data[i].maximum + "</td>";
        htmls += "<td>" + data[i].guidanceMan + "</td>";
        // htmls+="<td>"+getDate(data[i].startTime,"yyyy/MM/dd hh:mm")+"</td>";
        htmls += "<td class='center_td'>" + statuss + "</td>";
        htmls += "<td class='center_td'><a href='#mymodal1'";
        htmls += "data-toggle='modal'><div class='message_div' onclick=commonAjax('" + getProjectInfo + "','projectNum=" + data[i].projectNum + "','getProjectInfo','GET')>查看详情</div></a></td></tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function getProjectInfo(data) {
    var htmlss = "";
    var statuss = "";
    switch (data.implementationProcess) {
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

    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>编号</div>";
    htmlss += "<div class='span3'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</div>";
    htmlss += "<div class='span3'>代码</div>";
    htmlss += "<div class='span3'>" + data.projectNum + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目申请日期</div>";
    htmlss += "<div class='span3'>" + data.creatTime + "</div>";
    htmlss += "<div class='span3'>状态</div>";
    htmlss += "<div class='span3'>" + statuss + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目类别</div>";
    htmlss += "<div class='span3' id='" + data.projectNum + "'>";
    htmlss += commonAjax(getProjectTypeUrl, '', 'getProjectTypeUrl', 'GET', data.projectNum);
    htmlss += "</div>";
    htmlss += "<div class='span3'>所属学院</div>";
    htmlss += "<div class='span3'><input type='text' placeholder='" + data.college + "'></div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目名称</div>";
    htmlss += "<div class='span3'><input type='text' placeholder='" + data.projectName + "'></div>";
    htmlss += "<div class='span3'>负责人</div>";
    htmlss += "<div class='span3'><input type='text' placeholder='" + data.guidanceMan + "'></div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>工作量</div>";
    htmlss += "<div class='span3'><input type='text' placeholder='" + (parseFloat(data.integral) * parseInt(data.maximum)) + "'></div>";
    htmlss += "<div class='span3'>积分</div>";
    htmlss += "<div class='span3'><input type='text' placeholder='" + data.integral + "'></div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>开始时间</div>";
    htmlss += "<div class='span3'><div class='input-append date' id='datetimepicker1' data-date='2017/09/14' data-date-format='dd-mm-yyyy'>";
    htmlss += "<input class='span2 nowdate' size='16' type='text' value='12-02-2012' style='width:100px !important;'>";
    htmlss += "<span class='add-on'><i class='icon-th'></i></span>";
    htmlss += "</div></div>";
    htmlss += "<div class='span3'>结束时间</div>";
    htmlss += "<div class='span3'><div class='input-append date' id='datetimepicker1' data-date='2017/09/14' data-date-format='dd-mm-yyyy'>";
    htmlss += "<input class='span2 nowdate' size='16' type='text' value='12-02-2012' style='width:100px !important;'>";
    htmlss += "<span class='add-on'><i class='icon-th'></i></span>";
    htmlss += "</div></div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>最大参与人数</div>";
    htmlss += "<div class='span3'><input type='text' placeholder='" + data.maximum + "'></div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目内容</div>";
    htmlss += "<div class='span9'>" + data.content + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>评价标准与形式</div>";
    htmlss += "<div class='span9'>" + data.checkAssessmentCriteraAndForm + "</div>";
    htmlss += "</div>";

    $("#getProjectInfo").html(htmlss);
    page(datas, dataUrl, datas.size, datas.number);
}


function getProjectTypeUrl(res, id) {
    var htmls = "";
    var data = res.content;
    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='category " + (i + 1) + "'>" + data.category + "</option>";
    }
    $("#id").html(htmls);

}