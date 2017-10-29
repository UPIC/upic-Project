var dataUrl = "/stu/getAllIntegralLogByStudentNum";
var getIntegralLogByIntegralLogId="/common/getIntegralLogByIntegralLogId";
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
        var statusC = "";
        var aUrl="";
        if (data[i].status === "PENDING_AUDIT") {
            statusC = "待审核";
            aUrl="#mymodal1";
        } else if (data[i].status === "HAVEPASSED") {
            statusC = "审核成功";
            aUrl="#mymodal1";
        } else if (data[i].status === "FAILURE_TO_PASS_THE_AUDIT") {
            statusC = "审核失败";
            aUrl="#mymodal2";
        } else if (data[i].status === "ALREADY_SIGN_UP") {
            statusC = "已报名";
            aUrl="#mymodal1";
        } else if (data[i].status === "SIGNED_IN") {
            statusC = "已签到";
            aUrl="#mymodal1";
        } else if (data[i].status === "COMPLETED") {
            aUrl="#mymodal1";
            statusC = "已完成";
        }

        htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].integralLogId.projectNum + "'/></td>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td>" + data[i]..integralLogId.projectNum + "</td>";
        htmls += "<td>" + splitJson(data[i].event) + "</td>";
        htmls += "<td>" + data[i].projectName + "</td>";
        htmls += "<td>" + data[i].integral + "</td>";
        htmls += "<td>" + getDate(data[i].creatTime, "yyyy-MM-dd") + "</td>";
        htmls += "<td class='center_td'>" + statusC + "</td>";
        htmls += "<td class='center_td'>";
        htmls += "<div class='message_div' onclick=commonAjax('"+getIntegralLogByIntegralLogId+"','projectNum=" + data[i].integralLogId.projectNum + "','getProjectInfo','GET')><a href='"+aUrl+"' data-toggle='modal'>查看详情</a></div></td>";
        htmls += "</tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function getProjectInfo(result) {
    var htmlss = "";
    var statusC = "";
        if (result.status === "PENDING_AUDIT") {
            statusC = "待审核";
        } else if (result.status === "HAVEPASSED") {
            statusC = "审核成功";
        } else if (result.status === "FAILURE_TO_PASS_THE_AUDIT") {
            statusC = "审核失败";
        } else if (result.status === "ALREADY_SIGN_UP") {
            statusC = "已报名";
        } else if (result.status === "SIGNED_IN") {
            statusC = "已签到";
        } else if (result.status === "COMPLETED") {
            statusC = "已完成";
        }
    if (data.field1 !="") {//审核失败
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>编号</div>";
        htmlss += "<div class='span3'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1)+ "</div>";
        htmlss += "<div class='span3'>代码</div>";
        htmlss += "<div class='span3'>" + result.integralLogId.projectNum + "</div>";
        htmlss += "</div><div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目申请日期</div>";
        htmlss += "<div class='span3'>" + getDate(result.creatTime, "yyyy-MM-dd") + "</div>";
        htmlss += "<div class='span3'>状态</div>";
        htmlss += "<div class='span3'>" + statusC + "</div>";
        htmlss += "</div> <div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目类别</div>";
        htmlss += "<div class='span3'>" + splitJson(result.event) + "</div>";
        htmlss += "<div class='span3'>所属学院</div>";
        htmlss += "<div class='span3'>" + result.college + "</div>";
        htmlss += "</div> <div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目名称</div>";
        htmlss += "<div class='span3'>" + result.projectName + "</div>";
        htmlss += "</div> <div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目内容</div>";
        htmlss += "<div class='span9'>" + result.content + "</div>";
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>作证材料</div>";
        htmlss += "<div class='span9'>";
        htmlss += "<a class='tooltip1' href='../../img/example.jpg'><img src='../../img/example.jpg'></a>";
        htmlss += "</div> </div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>审核失败原因</div>";
        htmlss += "<div class='span9'>"+result.field1+"</div></div>";
        $("#getProjectInfo2").html(htmlss);
    }else{
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>编号</div>";
        htmlss += "<div class='span3'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1)+ "</div>";
        htmlss += "<div class='span3'>代码</div>";
        htmlss += "<div class='span3'>" + result.integralLogId.projectNum + "</div>";
        htmlss += "</div><div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目申请日期</div>";
        htmlss += "<div class='span3'>" + getDate(result.creatTime, "yyyy-MM-dd") + "</div>";
        htmlss += "<div class='span3'>状态</div>";
        htmlss += "<div class='span3'>" + statusC + "</div>";
        htmlss += "</div> <div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目类别</div>";
        htmlss += "<div class='span3'>" + splitJson(result.event) + "</div>";
        htmlss += "<div class='span3'>所属学院</div>";
        htmlss += "<div class='span3'>" + result.college + "</div>";
        htmlss += "</div> <div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目名称</div>";
        htmlss += "<div class='span3'>" + result.projectName + "</div>";
        htmlss += "</div> <div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目内容</div>";
        htmlss += "<div class='span9'>" + result.content + "</div>";
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>作证材料</div>";
        htmlss += "<div class='span9'>";
        htmlss += "<a class='tooltip1' href='../../img/example.jpg'><img src='../../img/example.jpg'></a>";
        htmlss += "</div> </div>";
        $("#getProjectInfo1").html(htmlss);
    }



}





