var dataUrl = "/common/getProject";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var getProjectTypeUrl = "/common/getAllProjectCategory";
var getProjectStatusUrl = "/common/getProjectStatus";

var requestData = {};

$(function () {
    pageSize = $("#select-small").children('option:selected').text();
    commonAjax(getProjectTypeUrl, null, "addProjectType", "GET");
    commonAjax(getProjectStatusUrl, null, "addProjectStatus", "GET");
    registSelect("projectCategory");
    registSelect("implementationProcess");
    getData(pageNum, dataUrl);
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

function addProjectStatus(res) {
    var data = res;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>项目状态筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + getProjectImplementationProcesses(data[i]) + "</option>";
    }
    $("#implementationProcess").html(htmls);
}

function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    var status = "";
    var turnOnOrTurnOff = "";
    var tOrF = 0;
    for (var i = 0; i < data.length; i++) {
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
        if (data[i].onOff == 0) {
            turnOnOrTurnOff = "switch-off";
            tOrF = 0
        }
        if (data[i].onOff == 1) {
            turnOnOrTurnOff = "switch-on";
            tOrF = 1
        }

        htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].projectNum + "'/></td>";
        htmls += "<td class='center_td'>";
        htmls += "<div class='common-row'>";
        htmls += "<div class='cell-right'><span class='" + turnOnOrTurnOff + "' themeColor='#6d9eeb' id='' onclick=changeStatus('" + data[i].projectNum + "','" + tOrF + "')></span></div>";
        htmls += "</div></td>";
        htmls += "<td class='center_td'>" + data[i].projectNum + "</td>";
        htmls += "<td>" + data[i].projectCategory + "</td>";
        htmls += "<td>" + data[i].projectName + "</td>";
        htmls += "<td>" + data[i].guidanceMan + "</td>";
        htmls += "<td>" + getDate(data[i].startTime, 'yyyy-MM-dd') + "</td>";
        htmls += "<td>" + getDate(data[i].endTime, 'yyyy-MM-dd') + "</td>";
        htmls += "<td>" + status + "</td>";
        htmls += "<td class='center_td'>";
        htmls += "<a href='#mymodal1' data-toggle='modal'><div class='message_div'><span onclick=commonAjax('" + dataUrl + "','projectNum=" + data[i].projectNum + "','getInfo','GET')>查看详情</span></div></a></td>";
        htmls += "</tr>";
    }

    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function getInfo(datas) {
    var data = datas.content[0];
    var htmlss = "";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目类别</div>";
    htmlss += "<div class='span3'>" + data.projectCategory + "</div>";
    htmlss += "<div class='span3'>项目名称</div>";
    htmlss += "<div class='span3'>" + data.projectName + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>起始时间</div>";
    htmlss += "<div class='span3'>" + getDate(data.startTime, 'yyyy-MM-dd') + "</div>";
    htmlss += "<div class='span3'>结束时间</div>";
    htmlss += "<div class='span3'>" + getDate(data.endTime, 'yyyy-MM-dd') + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>申报单位</div>";
    htmlss += "<div class='span3'>" + data.declareUnit + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>二维码</div>";
    htmlss += "<div class='span9'>";
    if (data.implementationProcess != "ENROLLMENT") {
        htmlss += "<img src='../../img/fuck.jpeg' alt=''>";
    } else {

    }
    htmlss += "</div>";
    htmlss += "</div>";

    $("#mymodal1").html(htmlss);
}

function changeStatus(pN, juge) {
    if (juge == 0) {
        juge = 1;
        $("#" + pN).removeClass('switch-off');
        $("#" + pN).addClass('switch-on');
    } else {
        juge = 0;
        $("#" + pN).removeClass('switch-on');
        $("#" + pN).addClass('switch-off');
    }

    var projectInfoJson = {
        projectNum: pN,
        onOff: juge
    }

    var projectInfo = "{ ";
    for (var item in projectInfoJson) {
        projectInfo += "'" + item + "':'" + projectInfoJson[item] + "',";
    }
    projectInfo += " }";

    $.ajax({
        type: "POST",
        url: "/common/updateProjectOnOff",
        data: {
            projectInfo: projectInfo
        },
        success: function (result) {
            alert(result);
            getData(pageNum, dataUrl);
        }
    });
}

function getProjectImplementationProcesses(implementationProcess) {
    return (implementationProcess.split("=")[2]).split("}")[0];
}