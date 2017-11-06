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
        htmls += "<a href='#mymodal1' data-toggle='modal'><div class='message_div' onclick=commonAjax('" + dataUrl + "','userNum=" + data[i].userNum + "','getProjectInfo','GET')>查看详情</div></a></td>";
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
    htmls += "<div class='span3'>" + data.content[0].college + "</div>";

    htmls += "</div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>工号</div>";
    htmls += "<div class='span3'>" + data.content[0].userNum + "</div>";
    htmls += "<div class='span3'>负责人</div>";
    htmls += "<div class='span3'>" + data.content[0].username + "</div>";
    htmls += "</div>";

    htmls += "<table class='table table-bordered'>";

    htmls += "<thead>";
    htmls += "<tr>";
    htmls += "<th>类别</th>";
    htmls += "<th>工作量</th>";
    htmls += "</tr>";
    htmls += "</thead>";

    htmls += "<tbody id='" + data.content[0].userNum + "A'>";

    commonAjax(getProjectUrl, 'guidanceNum=' + data.content[0].userNum, 'getProjectInfo2', 'GET', data.content[0].userNum + 'A');

    htmls += "</tbody>";

    htmls += "</table>";

    $("#getProjectInfo").html(htmls);
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
    for (var i = 0; i < data.length; i++) {
        htmls += "<tr>";
        htmls += "<td>" + data[i].projectCategory + "</td>";
        htmls += "<td id='" + data[i].integral + "_" + data[i].projectNum + "'>";
        commonAjax('/common/getSignUpNumberByProjectNum', 'projectNum=' + data[i].projectNum, 'getProjectGZL', 'GET', data[i].integral + "_" + data[i].projectNum);
        htmls += "</td>";
        htmls += "</tr>";
    }
    htmls += "<tr>";
    if (data.length == 0) {
        htmls += "<td colspan='4' style='text-align:right;padding-right: 20px;'>总计工作量:<span>" + 0 + "</span></td>";
    } else {
        htmls += "<td colspan='4' style='text-align:right;padding-right: 20px;'>总计工作量:<span id='" + data[0].guidanceNum + "ProjectSpan'>";
        commonAjax(getTeacherNowWorkloadSummaryUrl, 'teacherNum=' + data[0].guidanceNum, 'getProjectSpan', 'GET', data[0].guidanceNum + 'ProjectSpan');
        htmls += "</span></td>";
    }
    htmls += "</tr>";
    $("#" + id).html(htmls);
}

function getProjectGZL(res, id) {
    var totle = res * splitJson(id);
    $("#" + id).html(totle);
}

function getProjectSpan(res, id) {
    $("#" + id).html(res);
}

function splitJson(json) {
    var projectCategorys = new Array();
    projectCategorys = json.split("_");
    return projectCategorys[0];
}

/****************导出****************************/
document.getElementById("exportBtn").onclick = function(){
            var table = document.getElementById("sample_1").innerHTML;//获取table模板
            exporExcel("工作量汇总",table);
        }
        /**
         * @params: FileName:导出Excel的文件名称，excel:需要导出的table
         * 如果没有table列表，只有json数据的话，将json数据拼接成table字符串模板即可
         * **/
         function exporExcel(FileName,excel){
            var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";
            excelFile += '<meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">';
            excelFile += '<meta http-equiv="content-type" content="application/vnd.ms-excel';
            excelFile += '; charset=UTF-8">';
            excelFile += "<head>";
            excelFile += "<!--[if gte mso 9]>";
            excelFile += "<xml>";
            excelFile += "<x:ExcelWorkbook>";
            excelFile += "<x:ExcelWorksheets>";
            excelFile += "<x:ExcelWorksheet>";
            excelFile += "<x:Name>";
            excelFile += "{worksheet}";
            excelFile += "</x:Name>";
            excelFile += "<x:WorksheetOptions>";
            excelFile += "<x:DisplayGridlines/>";
            excelFile += "</x:WorksheetOptions>";
            excelFile += "</x:ExcelWorksheet>";
            excelFile += "</x:ExcelWorksheets>";
            excelFile += "</x:ExcelWorkbook>";
            excelFile += "</xml>";
            excelFile += "<![endif]-->";
            excelFile += "</head>";
            excelFile += "<body>";
            excelFile += excel;
            excelFile += "</body>";
            excelFile += "</html>";


            var uri = 'data:application/vnd.ms-excel;charset=utf-8,' + encodeURIComponent(excelFile);

            var link = document.createElement("a");
            link.href = uri;

            link.style = "visibility:hidden";
                link.download = FileName ;  //格式默认为.xls

                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);
            }