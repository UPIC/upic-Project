/*
 * @Author: Marte
 * @Date:   2017-10-09 19:01:21
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-10-20 09:20:00
 */
var dataUrl = "/common/getProjectByGuidanceNum";
var searchKeyWordUrl = "/common/myProjectSearchBar";
var getProjectTypeUrl = "/common/getAllProjectCategory";
var getPeopleByProjectNumUrl = "";
var getProjectStatusUrl = "";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {};

$(function () {
    pageSize = $("#select-small").children('option:selected').text()
    getData(pageNum, dataUrl);
    commonAjax(getProjectTypeUrl, null, "addProjectType", "GET");
    // commonAjax(getProjectStatusUrl, null, "addProjectStatus", "GET");
    registSelect("projectCategory");
    registSelect("ProjectStatus");
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
    var data = res.content;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>项目状态筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].status + "</option>";
    }
    $("#ProjectStatus").html(htmls);

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
            case ("NOT_PASS"):
                status = "未通过"
                break;
            case ("IN_AUDIT"):
                status = "审核中"
                break;
            case ("AUDITED"):
                status = "已审核"
                break;
            case ("HAVE_IN_HAND"):
                status = "进行中"
                break;
            case ("COMPLETED"):
                status = "已完成"
                break;
            default:
        }
        if (data[i].implementationProcess === "SAVED" || data[i].implementationProcess === "NOT_PASS") {
            htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].projectNum + "'/></td>";
            htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
            htmls += "<td>" + data[i].projectNum + "</td>";
            htmls += "<td>" + data[i].projectCategory + "</td>";
            htmls += "<td>" + data[i].projectName + "</td>";
            htmls += "<td>" + data[i].integral + "</td>";
            htmls += "<td>" + "10/" + data[i].maximum + "</td>";
            htmls += "<td>" + "1000/" + (parseFloat(data[i].integral) * parseInt(data[i].maximum)) + "</td>";
            htmls += "<td>" + getDate(data[i].startTime, "yyyy/MM/dd hh:mm") + "</td>";
            htmls += "<td class='center_td'>" + status + "</td>";
            htmls += "<td class='center_td'>";
            htmls += " <div class='message_div'><a href='#mymodal2' data-toggle='modal'>编辑</a><span class='space'>|</span><a>提交</a></div></td>";
            htmls += " </tr>";
        } else {
            htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].projectNum + "'/></td>";
            htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
            htmls += "<td>" + data[i].projectNum + "</td>";
            htmls += "<td>" + data[i].projectCategory + "</td>";
            htmls += "<td>" + data[i].projectName + "</td>";
            htmls += "<td>" + data[i].integral + "</td>";
            htmls += "<td>" + "10/" + data[i].maximum + "</td>";
            htmls += "<td>" + "1000/" + (parseFloat(data[i].integral) * parseInt(data[i].maximum)) + "</td>";
            htmls += "<td>" + getDate(data[i].startTime, "yyyy/MM/dd hh:mm") + "</td>";
            htmls += "<td class='center_td'>" + status + "</td>";
            htmls += "<td class='center_td'>";
            htmls += " <div class='message_div'><a href='#mymodal3' data-toggle='modal'><span onclick=commonAjax('" + dataUrl + "','" + data[i].projectNum + "','getProjectInfo','GET')>详情</span></a><span class='space'>|</span><a href='#mymodal5' data-toggle='modal'><span onclick=commonAjax('" + getPeopleByProjectNumUrl + "','" + data[i].projectNum + "','getPeopleInfo','GET')>名单</span></a></div></td>";
            htmls += " </tr>";
        }
    }

    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number, requestData);

}
function getProjectInfo(data) {
    var status = "";
    switch (data[i].implementationProcess) {
        case ("SAVED"):
            status = "已保存";
            break;
        case ("NOT_PASS"):
            status = "未通过"
            break;
        case ("IN_AUDIT"):
            status = "审核中"
            break;
        case ("AUDITED"):
            status = "已审核"
            break;
        case ("HAVE_IN_HAND"):
            status = "进行中"
            break;
        case ("COMPLETED"):
            status = "已完成"
            break;
        default:
    }
    var htmls = "";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>编号</div>";
    htmls += "<div class='span3'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</div>";
    htmls += "<div class='span3'>代码</div>";
    htmls += "<div class='span3'>" + data[i].projectNum + "</div>";
    htmls += "</div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>项目申请日期</div>";
    htmls += "<div class='span3'>" + getDate(data[i].startTime, "yyyy/MM/dd hh:mm") + "</div>";
    htmls += "<div class='span3'>状态</div>";
    htmls += "<div class='span3'>" + status + "</div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>项目类别</div>";
    htmls += "<div class='span3'>";

    htmls += data.projectCategory;
    htmls += "</div>";
    htmls += "<div class='span3'>所属学院</div>";
    htmls += "<div class='span3'>" + data.college + "</div>";
    htmls += "</div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>项目名称</div>";
    htmls += "<div class='span3'>" + data.projectName + "</div>";
    htmls += "<div class='span3'>负责人</div>";
    htmls += "<div class='span3'>" + data.guidanceMan + "</div>";
    htmls += "</div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>工作量</div>";
    htmls += "<div class='span3'>" + (parseFloat(data[i].integral) * parseInt(data[i].maximum)) + "</div>";
    htmls += "<div class='span3'>积分</div>";
    htmls += "<div class='span3'>" + data.integral + "</div>";
    htmls += "</div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>开始时间</div>";
    htmls += "<div class='span3'>" + getDate(data[i].startTime, "yyyy/MM/dd hh:mm") + "</div>";
    htmls += "<div class='span3'>结束时间</div>";
    htmls += "<div class='span3'>" + getDate(data[i].endTime, "yyyy/MM/dd hh:mm") + "</div>";
    htmls += "</div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>参与人数</div>";
    htmls += "<div class='span3'>20/" + data[i].maximum + "</div>";
    htmls += "</div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>项目内容</div>";
    htmls += "<div class='span9'>";
    htmls += data.content;
    htmls += "</div>";

    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>评价标准与形式</div>";
    htmls += "<div class='span9'></div>";
    htmls += "</div>";
    $("#getProjectInfo").html(htmls);
}

function getPeopleInfo(data) {
    var res = data.content;
    var htmlss = "";
    htmlss += "<tr> <th>编号</th> <th>所属学院</th> <th>班级</th> <th>学号</th> <th>姓名</th> </tr>";
    for (var i = 0; i < data.length; i++) {
        htmlss += "<tr><td>" + (i + 1) + "</td><td>" + data[i].college + "</td><td>" + data[i].clazz + "</td><td>" + data[i].studentNum + "</td><td>" + data[i].studentName + "</td></tr>";
    }
    $("#getPeopleInfo").html(htmlss);
}