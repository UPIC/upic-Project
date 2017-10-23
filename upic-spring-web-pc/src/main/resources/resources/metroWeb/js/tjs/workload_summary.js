var dataUrl = "/common/getAllUser";//查询所有教师
var searchKeyWordUrl = "";//搜索条
var getTeacherNowWorkloadSummaryUrl = "/common/getTeacherNowWorkloadSummary";//查询目前工作量
var getTeacherAllWorkloadSummaryUrl = "/common/getTeacherAllWorkloadSummary";//查询总工作量
var getProjectUrl = "/common/getProject";//查询单个教师所有项目
var getDepartmentUrl = "";//部门筛选
var getYearUrl = "";//年度筛选
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {
    type: 'TEACHER'
};

$(function () {
    pageSize = $("#select-small").children('option:selected').text()
    getData(pageNum, dataUrl);
    // commonAjax(getDepartmentUrl, null, "addDepartment", "GET");
    // commonAjax(getYearUrl, null, "addYear", "GET");
    // registSelect("getDepartment");
    // registSelect("getYear");
})

function addDepartment(res) {
    var data = res.content;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>所属部门筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].departments + "</option>";
    }
    $("#addDepartment").html(htmls);
}

function addYear(res) {
    var data = res.content;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>年度筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].years + "</option>";
    }
    $("#addYear").html(htmls);
}


function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].userNum + "'/></td>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td>年度</td>";
        htmls += "<td>" + data[i].college + "</td>";
        htmls += "<td>" + data[i].userNum + "</td>";
        htmls += "<td>" + data[i].username + "</td>";
        htmls += "<td>";
        htmls += "<span id='" + data[i].userNum + "1'>";
        commonAjax(getTeacherNowWorkloadSummaryUrl, 'teacherNum=' + data[i].userNum, 'getWorkLoad1', 'GET', data[i].userNum + '1');
        htmls += "</span>";
        htmls += "<span id='" + data[i].userNum + "2'>";
        commonAjax(getTeacherAllWorkloadSummaryUrl, 'teacherNum=' + data[i].userNum, 'getWorkLoad2', 'GET', data[i].userNum + '2');
        htmls += "</span>";
        htmls += "</td>";
        htmls += "<td class='center_td'>";
        htmls += "<a href='#mymodal1' data-toggle='modal'><div class='message_div' onclick=commonAjax('" + dataUrl + "','" + requestData + "','getProjectInfo','GET')>查看详情</div></a></td>";
        htmls += "</tr>";
    }
    $("#data").html(htmls);

    page(datas, dataUrl, datas.size, datas.number);
}


function getProjectInfo(data) {
    var htmls = "";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>年度</div>";
    htmls += "<div class='span3'>2016-2017</div>";
    htmls += "<div class='span3'>所属部门</div>";
    htmls += "<div class='span3'>" + data.college + "</div>";

    htmls += "</div>";
    htmls += "div class='row-form clearfix'>";
    htmls += "<div class='span3'>工号</div>";
    htmls += "<div class='span3'>" + data.userNum + "</div>";
    htmls += "<div class='span3'>负责人</div>";
    htmls += "<div class='span3'>" + data.username + "</div>";
    htmls += "</div>";

    htmls += "<table class='table table-bordered'>";

    htmls += "<thead>";
    htmls += "<tr>";
    htmls += "<th>类别</th>";
    htmls += "<th>工作量</th>";
    htmls += "</tr>";
    htmls += "</thead>";

    htmls += "<tbody id='" + data.userNum + "'>";

    htmls += commonAjax(getProjectUrl, requestData, 'getProjectInfo2', 'GET', data.userNum);

    htmls += "</tbody>";

    htmls += "</table>";

    $("#getProjectInfo").html(htmlss);
}


function getWorkLoad1(res, id) {
    $("#" + id).html(res + '/');
}
function getWorkLoad2(res, id) {
    $("#" + id).html(res);
}

function getProjectInfo2(res, id) {
    var data = res.content;
    var htmls = "";
    var totle = "";
    for (var i = 0; i < data.length; i++) {
        htmls += "<tr>";
        htmls += "<td>" + data.projectCategory + "</td>";
        htmls += "<td>";

        htmls += "</td>";
        htmls += "</tr>";
    }
    htmls += "<tr>";
    htmls += "<td colspan='4' style='text-align:right;padding-right: 20px;'>总计工作量:<span>2</span></td>";
    htmls += "</tr>";
    //$("#")
}


