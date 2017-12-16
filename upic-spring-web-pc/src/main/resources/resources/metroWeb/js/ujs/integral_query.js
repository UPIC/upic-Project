var dataUrl = "/stu/getAllIntegralLogByStudentNum";
var getIntegralLogByIntegralLogId = "/common/getIntegralLogByIntegralLogId";
var searchKeyWordUrl = "/common/integralLogSearchBar";
var getProjectTypeUrl = "/common/getAllProjectCategory";
var getStatusUrl = "/common/getCollege";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {};

$(function () {
    $("#main-content").css({
        minHeight: $(window).height() - 100 + "px"
    });

    pageSize = $("#select-small").children('option:selected').text()
    getData(pageNum, dataUrl);
    commonAjax(getProjectTypeUrl, null, "addProjectType", "GET");
    commonAjax(getStatusUrl, null, "addStatus", "GET");
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

function addStatus(res) {
    var data = res.content;
    var htmls = "";
    var htmlss = "";
    htmls += "<option value='4' class='yellow'>状态筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].college + "</option>";
    }
    $("#college").html(htmls);

    for (var i = 0; i < data.length; i++) {
        htmlss += "<option value='" + (i + 4) + "'>" + data[i].college + "</option>";
    }
    $("selectCollege").append(htmlss);
}

function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        var statusC = "";
        var aUrl = "";
        if (data[i].status === "SAVE") {
            statusC = "保存";
        } else if (data[i].status === "PENDING_AUDIT_BEFORE") {
            statusC = "待初审";
        } else if (data[i].status === "PENDING_AUDIT_BEFORE_FAIL") {
            statusC = "待初审失败";
        } else if (data[i].status === "PENDING_AUDIT") {
            statusC = "待学院审";
        } else if (data[i].status === "PENDING_AUDIT_FAIL") {
            statusC = "待学院审失败";
        } else if (data[i].status === "PENDING_AUDIT_AGAIN") {
            statusC = "待部门审";
        } else if (data[i].status === "PENDING_AUDIT_AGAIN_FAIL") {
            statusC = "待部门审失败";
        } else if (data[i].status === "PENDING_AUDIT_FINAL") {
            statusC = "待团委审";
        } else if (data[i].status === "PENDING_AUDIT_FINAL_FAIL") {
            statusC = "待团委审失败";
        } else if (data[i].status === "HAVEPASSED") {
            statusC = "审核成功";
        } else if (data[i].status === "ALREADY_SIGN_UP") {
            statusC = "已报名";
        } else if (data[i].status === "SIGNED_IN") {
            statusC = "已签到";
        } else if (data[i].status === "COMPLETED") {
            statusC = "已完成";
        }

        htmls += "<tr>";
        // htmls += "<td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].integralLogId.projectNum + "'/></td>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td class='center_td'>" + statusC + "</td>";
        // htmls += "<td>" + data[i].integralLogId.projectNum + "</td>";
        htmls += "<td>" + getDate(data[i].creatTime, "yyyy-MM-dd") + "</td>";
        htmls += "<td>" + data[i].projectCategory + "</td>";
        htmls += "<td>" + data[i].projectName + "</td>";
        htmls += "<td>" + data[i].integral + "</td>";

        htmls += "<td class='center_td'>";
        if (data[i].status === "SAVE" || data[i].status === "PENDING_AUDIT_BEFORE_FAIL" || data[i].status === "PENDING_AUDIT_FAIL" || data[i].status === "PENDING_AUDIT_AGAIN_FAIL" || data[i].status === "PENDING_AUDIT_FINAL_FAIL") {//审核失败
            htmls += "<a href='#mymodal2' data-toggle='modal'>";
        } else {
            htmls += "<a href='#mymodal1' data-toggle='modal'>";
        }
        htmls += "<div class='message_div' onclick=commonAjax('" + getIntegralLogByIntegralLogId + "','projectNum=" + data[i].integralLogId.projectNum + "','getProjectInfo','GET','" + i + 1 + "')>查看详情</div>";
        htmls += "</a>";
        htmls += "</tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function getProjectInfo(result, j) {
    var htmlss = "";
    var statusC = "";
    if (result.status === "SAVE") {
        statusC = "保存";
    } else if (result.status === "PENDING_AUDIT_BEFORE") {
        statusC = "待初审";
    } else if (result.status === "PENDING_AUDIT_BEFORE_FAIL") {
        statusC = "待初审失败";
    } else if (result.status === "PENDING_AUDIT") {
        statusC = "待学院审";
    } else if (result.status === "PENDING_AUDIT_FAIL") {
        statusC = "待学院审失败";
    } else if (result.status === "PENDING_AUDIT_AGAIN") {
        statusC = "待部门审";
    } else if (result.status === "PENDING_AUDIT_AGAIN_FAIL") {
        statusC = "待部门审失败";
    } else if (result.status === "PENDING_AUDIT_FINAL") {
        statusC = "待团委审";
    } else if (result.status === "PENDING_AUDIT_FINAL_FAIL") {
        statusC = "待团委审失败";
    } else if (result.status === "HAVEPASSED") {
        statusC = "审核成功";
    } else if (result.status === "ALREADY_SIGN_UP") {
        statusC = "已报名";
    } else if (result.status === "SIGNED_IN") {
        statusC = "已签到";
    } else if (result.status === "COMPLETED") {
        statusC = "已完成";
    }
    htmlss += "<div class='modal-header'>";
    htmlss += "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
    htmlss += "<h4 id='mymodallabel1'>详情</h4>";
    htmlss += "</div>";
    htmlss += "<div class='modal-body'>";
    htmlss += "<div class='row-fluid'>";
    htmlss += "<div class='block-fluid'>";

    if (result.status === "SAVE" || result.status === "PENDING_AUDIT_BEFORE_FAIL" || result.status === "PENDING_AUDIT_FAIL" || result.status === "PENDING_AUDIT_AGAIN_FAIL" || result.status === "PENDING_AUDIT_FINAL_FAIL") {//审核失败
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>编号</div>";
        htmlss += "<div class='span3'>" + (parseInt(pageNum) * parseInt(pageSize) + j) + "</div>";
        htmlss += "<div class='span3'>代码</div>";
        htmlss += "<div id='myProjectNum' class='span3'>" + result.integralLogId.projectNum + "</div>";
        htmlss += "</div><div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目申请日期</div>";
        htmlss += "<div class='span3'>" + getDate(result.creatTime, "yyyy-MM-dd") + "</div>";
        htmlss += "<div class='span3'>状态</div>";
        htmlss += "<div class='span3'>" + statusC + "</div>";
        htmlss += "</div><div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目类别</div>";
        htmlss += "<div class='span3'>" + result.projectCategory + "</div>";
        htmlss += "<div class='span3'>所属学院</div>";
        // htmlss += "<div class='span3'><input type='text' name='' value='" + result.college + "' disabled='disabled'></div>";
        htmlss += "<div class='span3'><div class='controls'>";
        htmlss += "<select class='input-medium m-wrap' tabindex='1' id='selectCollege'>";
        htmlss += "<option value='4' class='yellow'>" + result.college + "</option>";
        htmlss += "</select></div></div>";
        htmlss += "</div> <div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目名称</div>";
        htmlss += "<div class='span3'><input id='newProjectName' type='text' name='' value='" + result.projectName + "' disabled='disabled'></div>";
        htmlss += "</div> <div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目内容</div>";
        htmlss += "<div class='span9'><input id='newContent' type='text' name='' value='" + result.content + "' disabled='disabled'></div>";
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>作证材料</div>";
        htmlss += "<div class='span9'>";
        htmlss += "<a class='tooltip1' href='../../img/example.jpg'><img src='../../img/example.jpg'></a>";
        htmlss += "</div> </div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>审核失败原因</div>";
        htmlss += "<div class='span9'>" + result.field1 + "</div></div>";
        htmlss += "</div>";
        htmlss += "<div class='dr'><span></span></div>";
        htmlss += "</div>";
        htmlss += "</div>";
        htmlss += "<div class='modal-footer'>";
        htmlss += "<button class='btn btn-primary' data-dismiss='modal' aria-hidden='true' onclick=changeMyIntegralLogStatus('/common/updateIntegralLog')>提交</button>";
        htmlss += "<button class='btn btn-default' data-dismiss='modal' aria-hidden='true'>取消</button>";
        htmlss += "</div>";
        $("#mymodal2").html(htmlss);
    } else {
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>编号</div>";
        htmlss += "<div class='span3'>" + (parseInt(pageNum) * parseInt(pageSize) + j) + "</div>";
        htmlss += "<div class='span3'>代码</div>";
        htmlss += "<div class='span3'>" + result.integralLogId.projectNum + "</div>";
        htmlss += "</div><div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目申请日期</div>";
        htmlss += "<div class='span3'>" + getDate(result.creatTime, "yyyy-MM-dd") + "</div>";
        htmlss += "<div class='span3'>状态</div>";
        htmlss += "<div class='span3'>" + statusC + "</div>";
        htmlss += "</div> <div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目类别</div>";
        htmlss += "<div class='span3'>" + result.projectCategory + "</div>";
        htmlss += "<div class='span3'>所属学院</div>";
        htmlss += "<div class='span3'>" + result.college + "</div>";
        htmlss += "</div> <div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目名称</div>";
        htmlss += "<div class='span3'>" + result.projectName + "</div>";
        htmlss += "</div> <div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目内容</div>";
        htmlss += "<div class='span9'>" + result.event + "</div>";
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>作证材料</div>";
        htmlss += "<div class='span9'>";
        htmlss += "<a class='tooltip1' href='../../img/example.jpg'><img src='../../img/example.jpg'></a>";
        htmlss += "</div> </div>";
        htmlss += "</div><div class='dr'><span></span></div></div></div><div class='modal-footer'>";
        htmlss += "<button class='btn btn-primary' data-dismiss='modal' aria-hidden='true'>确定</button>";
        htmlss += "<button class='btn btn-default' data-dismiss='modal' aria-hidden='true'>取消</button>";
        htmlss += "</div>";
        $("#mymodal1").html(htmlss);
    }
}

function changeMyIntegralLogStatus(url) {
    var Data = {};
    Data.projectName = $("#newProjectName").val();
    Data.content = $("#newContent").val();
    Data.projectNum = $("#myProjectNum").html();
    $.ajax({
        url: url,
        type: 'POST', // POST
        data: Data,
        dataType: 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
            alert("提交成功");
            getData(pageNum, dataUrl);
        }
    })
}