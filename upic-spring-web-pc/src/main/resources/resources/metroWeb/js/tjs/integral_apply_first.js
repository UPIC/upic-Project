var dataUrl = "/systemManager/getIntegralLogBySql";//获取积分列表
var getAllProjectCategory = "/common/getAllProjectCategory";//获取项目类别
var searchKeyWordUrl = "/common/integralLogSearchBar";//搜索条(String status=PENDING_AUDIT, String keyword)
var updateIntegralLogStatus = "/common/changeAllIntegralLogStatus";
//修改积分状态(List<String> studentNumList, List<String> projectNumList, IntegralLogStatusEnum status)
var getProjectTypeUrl = "/common/getAllProjectCategory";
var getCollegeUrl = "/common/getCollege";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {
    type: "C"
};

$(function () {
    pageSize = $("#select-small").children('option:selected').text()
    getData(pageNum, dataUrl);
    commonAjax(getProjectTypeUrl, null, "addProjectType", "GET");
    commonAjax(getCollegeUrl, null, "addCollegeUrl", "GET");
    registSelect("projectCategory");
    registSelect("college");
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
    $("#college").html(htmls);
}

function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].integralLogId.projectNum + "' name='" + data[i].integralLogId.studentNum + "'/></td>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td>" + data[i].college + "</td>";
        htmls += "<td id='studentNum'>" + data[i].integralLogId.studentNum + "</td>";
        htmls += "<td>" + data[i].clazz + "</td>";
        htmls += "<td>" + data[i].student + "</td>";
        htmls += "<td>" + data[i].projectCategory + "</td>";
        htmls += "<td>" + data[i].projectName + "</td>";
        htmls += "<td style='display:none' id='status'>" + data[i].status + "</td>";
        htmls += "<td>";
        htmls += "<div class='message_div' onclick=commonAjax('/common/getIntegralLogByProjectNumStudentNum','projectNum=" + data[i].integralLogId.projectNum + "&studentNum=" + data[i].integralLogId.studentNum + "','getProjectInfo','GET')><a href='#mymodal1' data-toggle='modal'>查看详情</a></div></td>";
        htmls += "</tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function getProjectInfo(data) {
    var htmlss = "";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>申请日期</div>";
    htmlss += "<div class='span3'>" + getDate(data.creatTime, "yyyy-MM-dd") + "</div>";
    htmlss += "<div class='span3'>姓名</div>";
    htmlss += "<div class='span3'>" + data.student + "</div>";
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
    htmlss += "<div class='span9'>" + data.event + "</div>";
    htmlss += "<div class='span3'>积分</div>";
    htmlss += "<div class='span9'>" + data.integral + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>作证材料</div>";
    htmlss += "<div class='span9'><a class='tooltip1' href='" + data.picUrl + "'><img src='" + data.pic + "' ></a></div>";
    htmlss += "</div>";
    htmlss += "</div>";

    $("#getProjectInfo").html(htmlss);
}

function pass() {
    var projectNumList = new Array();
    var studentNumList = new Array();
    //获取选中框的projectNum放入list
    $("input[type=checkbox]:checked").each(function () {
        projectNumList.push($(this).attr("id"));
    });
    //获取studentNum放入list2
    $("input[type=checkbox]:checked").each(function () {
        studentNumList.push($(this).attr("name"));
    });

    var projectStr = JSON.stringify(projectNumList);

    var studentStr = JSON.stringify(studentNumList);

    //status改为PASS
    var status = "PASS";
    //3者一起发送请求
    $.ajax({
        type: "GET",
        url: updateIntegralLogStatus,
        data: {
            projectNumList: projectStr,
            studentNumList: studentStr,
            status: status
        },
        success: function (result) {
            if (result === "SUCCESS") {
                alert("已发送 审核通过请求");
            }
            getData(pageNum, dataUrl);
        }
    });
}

function notPass() {
    var projectNumList = new Array();
    var studentNumList = new Array();
    //获取选中框的projectNum放入list
    $("input[type=checkbox]:checked").each(function () {
        projectNumList.push($(this).attr("id"));
    });
    //获取studentNum放入list2
    $("input[type=checkbox]:checked").each(function () {
        studentNumList.push($(this).attr("name"));
    });

    var projectStr = JSON.stringify(projectNumList);

    var studentStr = JSON.stringify(studentNumList);

    //status改为PASS
    var status = "NOTPASS";

    var notPassCause = $("#notPassCause").attr("placeholder");
    $.ajax({
        type: "GET",
        url: updateIntegralLogStatus,
        data: {
            projectNumList: projectStr,
            studentNumList: studentStr,
            status: status,
            failReason: notPassCause
        },
        success: function (result) {
            if (result === "SUCCESS") {
                alert("已发送 审核不通过请求")
            }
            getData(pageNum, dataUrl);
        }
    });


}





