var dataUrl = "/systemManager/getProjectBySql";
var searchKeyWordUrl = "/common/projectSearchBar";
var getProjectTypeUrl = "/common/getAllProjectCategory";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {
    implementationProcess: "IN_AUDIT"//
};

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
        var statuss = "";
        if (data[i].implementationProcess === "SAVED") {
            statuss = "已保存"
        }

        if (data[i].implementationProcess === "IN_AUDIT") {
            statuss = "审核中"
        }

        if (data[i].implementationProcess === "AUDITED") {
            statuss = "已审核"
        }

        if (data[i].implementationProcess === "NOT_PASS") {
            statuss = "未通过"
        }

        if (data[i].implementationProcess === "ENROLLMENT") {
            statuss = "报名中"
        }

        if (data[i].implementationProcess === "HAVE_IN_HAND") {
            statuss = "进行中"
        }

        if (data[i].implementationProcess === "COMPLETED") {
            statuss = "已完成"
        }

        if (data[i].implementationProcess === "CHECKED") {
            statuss = "已验收"
        }


        htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].projectNum + "'/></td>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td>" + data[i].projectNum + "</td>";
        htmls += "<td>" + data[i].projectCategory + "</td>";
        htmls += "<td>" + data[i].projectName + "</td>";
        htmls += "<td>" + data[i].integral + "</td>";
        htmls += "<td>" + data[i].maximum + "</td>";
        htmls += "<td>" + (parseFloat(data[i].integral) * parseInt(data[i].maximum)) + "</td>";
        htmls += "<td>" + getDate(data[i].startTime, "yyyy/MM/dd hh:mm") + "</td>";
        htmls += "<td class='center_td'>" + statuss + "</td>";
        htmls += "<td class='center_td'><a href='#mymodal1'";
        htmls += "data-toggle='modal'><div class='message_div' onclick=commonAjax('" + dataUrl + "','projectNum=" + data[i].projectNum + "','getProjectInfo','GET')>查看详情</div></a></td></tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}


function getProjectInfo(data) {
    var htmlss = "";
    var statuss = "";
    if (data.implementationProcess === "SAVED") {
        statuss = "已保存"
    }
    if (data.implementationProcess === "IN_AUDIT") {
        statuss = "审核中"
    }
    if (data.implementationProcess === "AUDITED") {
        statuss = "已审核"
    }
    if (data.implementationProcess === "NOT_PASS") {
        statuss = "未通过"
    }
    if (data.implementationProcess === "ENROLLMENT") {
        statuss = "报名中"
    }
    if (data.implementationProcess === "HAVE_IN_HAND") {
        statuss = "进行中"
    }
    if (data.implementationProcess === "COMPLETED") {
        statuss = "已完成"
    }
    if (data.implementationProcess === "CHECKED") {
        statuss = "已验收"
    }


    htmlss += "<div class='modal-header'>";
    htmlss += "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
    htmlss += "<h4>详情</h4>";
    htmlss += "</div>";
    htmlss += "<div class='modal-body'>";
    htmlss += "<div class='row-fluid'>";
    htmlss += "<div class='block-fluid'>";
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
    htmlss += "<div class='span3'>" + data.projectCategory + "</div>";
    htmlss += "<div class='span3'>所属学院</div>";
    htmlss += "<div class='span3'>" + data.college + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目名称</div>";
    htmlss += "<div class='span3'>" + data.projectName + "</div>";
    htmlss += "<div class='span3'>负责人</div>";
    htmlss += "<div class='span3'>XXX</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目内容</div>";
    htmlss += "<div class='span9'>" + data.content + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>评价标准与形式</div>";
    htmlss += "<div class='span9'>" + data.checkAssessmentCriteraAndForms + "</div>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='dr'>";
    htmlss += "<span></span>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='modal-footer'>";
    htmlss += "<button class='btn btn-info' data-dismiss='modal' aria-hidden='true' onclick=passOne(" + data.projectNum + ")>审核通过</button>";
    htmlss += "<button class='btn btn-danger fal' href='#myModal2' data-toggle='modal'>审核不通过</button>";
    htmlss += "<button class='btn btn-default' data-dismiss='modal' aria-hidden='true'>关闭</button>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='hide1' style='display: none;'>";
    htmlss += "<div class='modal-header'>";
    htmlss += "<h4>审核不通过原因</h4>";
    htmlss += "</div>";
    htmlss += "<div class=''>";
    htmlss += "<textarea name='1' id='textarea'>111</textarea>";
    htmlss += "</div>";
    htmlss += "<div class='modal-footer'>";
    htmlss += "<button class='btn btn-primary close-btn' onclick=notPassOne(" + data.projectNum + ")>提交</button>";
    htmlss += "<button class='btn btn-default close-btn'>关闭</button>";
    htmlss += "</div>";
    htmlss += "</div>";

    $("#getProjectInfo").html(htmlss);
}

function passOne(projectNum) {
    $.ajax({
        type: "GET",
        url: updateIntegralLogStatus,
        data: {
            "projectNum": projectNum,
            "status": "审核通过"
        },
        success: function (result) {
            alert("已发送 审核通过请求")
        }
    });
}

function notPassOne(projectNum) {
    var textarea = $("#textarea").text();
    $.ajax({
        type: "GET",
        url: updateIntegralLogStatus,
        data: {
            "projectNum": projectNum,
            "status": "审核不通过",
            "cause": textarea
        },
        success: function (result) {
            alert("已发送 审核不通过请求")
        }
    });
}

function pass() {
    var projectNumList = new Array();
    //获取选中框的projectNum放入list
    $("input[type=checkbox]:checked").each(function () {
        projectNumList.push($(this).attr("id"));
    });
    //status改为PASS
    var status = "PASS";
    //3者一起发送请求
    $.ajax({
        type: "GET",
        url: updateIntegralLogStatus,
        data: {
            "projectNumList": projectNumList,
            "status": status
        },
        success: function (result) {
            alert("已发送 审核通过请求")
        }
    });
}

function notPass() {
    var projectNumList = new Array();
    $("input[type=checkbox]:checked").each(function () {
        projectNumList.push($(this).attr("id"));
    });
    var status = "NOTPASS";
    var notPassCause = $("#notPassCause").attr("placeholder");
    $.ajax({
        type: "GET",
        url: updateIntegralLogStatus,
        data: {
            "projectNumList": projectNumList,
            "studentNumList": studentNumList,
            "status": status,
            "content": notPassCause
        },
        success: function (result) {
            alert("已发送 审核不通过请求")
        }
    });

}
