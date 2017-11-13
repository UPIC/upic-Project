var dataUrl = "/common/getProjectWithoutSignUp";
var getProjectInfo = "/common/getProjectInfo";
var searchKeyWordUrl = "/common/integralLogSearchBar";
var getProjectTypeUrl = "/common/getAllProjectCategory";
var getStatusUrl = "/common/getCollege";
var baomingUrl = "";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {};

$(function () {
    pageSize = $("#select-small").children('option:selected').text();
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
    htmls += "<option value='4' class='yellow'>状态筛选...</option>";

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
        htmls += "<tr>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td>" + data[i].projectCategory + "</td>";
        htmls += "<td>" + data[i].projectName + "</td>";
        htmls += "<td>" + data[i].declareUnit + "</td>";
        htmls += "<td>" + data[i].integral + "</td>";
        htmls += "<td>" + getDate(data[i].signUpEndTime, "yyyy-MM-dd") + "</td>";
        htmls += "<td>";
        htmls += "<div class='message_div' onclick=commonAjax('" + getProjectInfo + "','projectNum=" + data[i].projectNum + "','getProjectInfoA','GET')><a href='#mymodal1' data-toggle='modal' >报名</a></div></td>";
        htmls += "</tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function getProjectInfoA(result) {
    var htmlss = "";
    htmlss += "<div class='modal-header'>";
    htmlss += "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
    htmlss += "<h4>详情</h4>";
    htmlss += "</div>";
    htmlss += "<div class='modal-body'>";
    htmlss += "<div class='row-fluid'>";
    htmlss += "<div class='block-fluid'>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>编号</div>";
    htmlss += "<div class='span3'>" + result.projectNum + "</div>";
    htmlss += "<div class='span3'>报名截止时间</div>";
    htmlss += "<div class='span3'>" + getDate(result.signUpEndTime, "yyyy-MM-dd") + "</div>"; //
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目开始时间</div>";
    htmlss += "<div class='span3'>" + getDate(result.startTime, "yyyy-MM-dd") + "</div>";
    htmlss += "<div class='span3'>项目结束时间</div>";
    htmlss += "<div class='span3'>" + getDate(result.endTime, "yyyy-MM-dd") + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目类别</div>";
    htmlss += "<div class='span3'>" + result.projectCategory + "</div>";
    htmlss += "<div class='span3'>所属单位</div>";
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
    htmlss += "<div class='dr'><span></span></div>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='modal-footer'>";
    htmlss += "<button class='btn btn-primary' data-dismiss='modal' aria-hidden='true' onclick=apply(" + result.projectNum + ")>报名</button>";
    htmlss += "<button class='btn btn-default' data-dismiss='modal' aria-hidden='true'>关闭</button>";
    htmlss += "</div>"
    $("#mymodal1").html(htmlss);

}

function apply(pN) {//报名按钮
    $.ajax({
        type: 'GET',
        url: baomingUrl,
        data: {
            projectNum: pN
        },
        success: function (result) {
            alert("已报名")
        }
    });

}



