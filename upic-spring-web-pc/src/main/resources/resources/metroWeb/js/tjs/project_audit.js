var dataUrl = "/common/getProject";
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
    htmlss += "<div class='span9'></div>";
    htmlss += "</div>";

    $("#getProjectInfo").html(htmlss);
}


