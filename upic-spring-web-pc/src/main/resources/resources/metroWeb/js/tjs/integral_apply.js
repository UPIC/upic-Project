/*
 * @Author: Marte
 * @Date:   2017-10-12 08:38:20
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-10-19 15:50:21
 */

var dataUrl = "/common/getAllIntegralLog";//获取积分列表
var getAllProjectCategory = "/common/getAllProjectCategory";//获取项目类别
var searchKeyWordUrl = "/common/integralLogSearchBar";//搜索条(String status=PENDING_AUDIT, String keyword)
var updateIntegralLogStatus = "/common/updateIntegralLogStatus";//修改积分状态(List<String> studentNumList, List<String> projectNumList, IntegralLogStatusEnum status)
var getProjectTypeUrl = "/common/getAllProjectCategory";
var getCollegeUrl = "/common/getCollege";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {
    status: 'PENDING_AUDIT'
};

$(function () {
    pageSize = $("#select-small").children('option:selected').text()
    getData(pageNum, dataUrl);
    commonAjax(getProjectTypeUrl, null, "addProjectType", "GET");
    commonAjax(getCollegeUrl, null, "addCollegeUrl", "GET");
    registSelect("projectCategory");
    registSelect("getCollege");
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
        htmls += "<option value='" + (i + 4) + "'>" + data[i].college + "</option>";
    }
    $("#getCollege").html(htmls);
}

function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        requestData = {
            status: 'PENDING_AUDIT',
            projectNum: data[i].integralLogId.projectNum,
            studentNum: data[i].integralLogId.studentNum
        };
        htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].integralLogId.projectNum + "'/></td>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td>" + data[i].college + "</td>";
        htmls += "<td>" + data[i].integralLogId.studentNum + "</td>";
        htmls += "<td>" + data[i].clazz + "</td>";
        htmls += "<td>" + data[i].student + "</td>";
        htmls += "<td>" + data[i].projectCategory + "</td>";
        htmls += "<td>" + data[i].projectName + "</td>";
        htmls += "<td>";
        // onclick事件引号问题
        htmls += "<div class='message_div' onclick=commonAjax(" + dataUrl + "," + requestData + ",'getProjectInfo','GET')><a href='#mymodal1' data-toggle='modal'>查看详情</a></div></td>";
        htmls += "</tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function getProjectInfo(data) {
    var htmlss = "";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>申请日期</div>";
    htmlss += "<div class='span3'>" + data.startTime + "</div>";
    htmlss += "<div class='span3'>姓名</div>";
    htmlss += "<div class='span3'>" + data.userName + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>所属学院</div>";
    htmlss += "<div class='span3'>" + data.college + "</div>";
    htmlss += "<div class='span3'>班级</div>";
    htmlss += "<div class='span3'>" + data.clazz + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>类别</div>";
    htmlss += "<div class='span3'>" + data.projectCategory + "</div>";
    htmlss += "<div class='span3'>名称</div>";
    htmlss += "<div class='span3'>" + data.projectName + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>详情</div>";
    htmlss += "<div class='span9'>" + data.content + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>作证材料</div>";
    htmlss += "<div class='span9'><a class='tooltip1' href='" + data.picUrl + "'><img src='" + data.picUrl + "' ></a></div>";
    htmlss += "</div>";
    htmlss += "</div>";

    $("#getProjectInfo").html(htmlss);
}





