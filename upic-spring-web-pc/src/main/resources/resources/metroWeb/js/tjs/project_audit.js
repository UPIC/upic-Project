var dataUrl = "/systemManager/getProjectBySql";
var getProjectInfoUrl = "/common/getProjectInfo";
var searchKeyWordUrl = "/common/projectSearchBar";
var getProjectCategoryUrl = "/common/getAllProjectCategory";
var getProjectStatusUrl = "/common/getAllProjectImplementationProcess";
var changeStatusUrl = "/common/changeAllProjectStatus";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {
    type: "S"
};

$(function () {
    pageSize = $("#select-small").children('option:selected').text()
    getData(pageNum, dataUrl);
    commonAjax(getProjectCategoryUrl, null, "addProjectCategory", "GET");
    commonAjax(getProjectStatusUrl, null, "addProjectStatus", "GET");
    registSelect("projectNum");
    registSelect("ProjectStatus");
})

function addProjectCategory(res) {
    var data = res.content;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>项目类别筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].categoryName + "</option>";
    }
    $("#projectCategory").html(htmls);
}

function addProjectStatus(res) {
    var data = res;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>项目状态筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i] + "</option>";
    }
    $("#projectStatus").html(htmls);
}

function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        var statuss = "";
        if (data[i].implementationProcess === "IN_AUDIT") {
            statuss = "待初审";
        } else if (data[i].implementationProcess === "IN_AUDIT_AGAIN") {
            statuss = "待复审";
        } else if (data[i].implementationProcess === "IN_AUDIT_FINAL") {
            statuss = "待终审";
        }

        htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].projectNum + "'/></td>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td>" + data[i].projectNum + "</td>";
        htmls += "<td>" + data[i].projectCategory + "</td>";
        htmls += "<td>" + data[i].projectName + "</td>";
        htmls += "<td>" + data[i].integral + "</td>";
        htmls += "<td>" + data[i].maximum + "</td>";
        htmls += "<td>" + (parseFloat(data[i].integral) * parseInt(data[i].maximum)) + "</td>";
        htmls += "<td>" + getDate(data[i].creatTime, "yyyy/MM/dd hh:mm") + "</td>";
        htmls += "<td class='center_td'>" + statuss + "</td>";
        htmls += "<td class='center_td'><a href='#mymodal1'";
        htmls += "data-toggle='modal'><div class='message_div' onclick=commonAjax('" + getProjectInfoUrl + "','projectNum=" + data[i].projectNum + "','getProjectInfo','GET','" + (i + 1) + "')>查看详情</div></a></td></tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function getProjectInfo(data, j) {
    var htmlss = "";
    var statuss = "";
    if (data.implementationProcess === "IN_AUDIT") {
        statuss = "待初审";
    } else if (data.implementationProcess === "IN_AUDIT_AGAIN") {
        statuss = "待复审";
    } else if (data.implementationProcess === "IN_AUDIT_FINAL") {
        statuss = "待终审";
    }

    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>编号</div>";
    htmlss += "<div class='span3'>" + (parseInt(pageNum) * parseInt(pageSize) + j) + "</div>";
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
    htmlss += "<div class='span3'>申报单位</div>";
    htmlss += "<div class='span3'>" + data.declareUnit + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目名称</div>";
    htmlss += "<div class='span3'>" + data.projectName + "</div>";
    htmlss += "<div class='span3'>负责人</div>";
    htmlss += "<div class='span3'>" + data.guidanceMan + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目内容</div>";
    htmlss += "<div class='span9'>" + data.content + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>评价标准与形式</div>";
    htmlss += "<div class='span9'>" + data.checkAssessmentCriteraAndForm + "</div>";
    htmlss += "</div>";

    $("#getProjectInfo").html(htmlss);
}

function pass() {
    var projectNumList = new Array();
    //获取选中框的projectNum放入list
    $("input[type=checkbox]:checked").each(function () {
        projectNumList.push($(this).attr("id"));
    });
    var str = JSON.stringify(projectNumList);
    //status改为PASS
    var status = "PASS";
    //3者一起发送请求
    $.ajax({
        type: "GET",
        url: changeStatusUrl,
        data: {
            projectNumList: str,
            status: status
        },
        success: function (result) {
            if (result === "SUCCESS") {
                alert("已发送审核通过请求。")
            }
            getData(pageNum, dataUrl);
        }
    });

}

function notPass() {
    var projectNumList = new Array();
    //获取选中框的projectNum放入list
    $("input[type=checkbox]:checked").each(function () {
        projectNumList.push($(this).attr("id"));
    });

    var failReason = $("#notPassCause").val();

    var str = JSON.stringify(projectNumList);

    var status = "NOT_PASS";

    $.ajax({
        type: "GET",
        url: changeStatusUrl,
        data: {
            projectNumList: str,
            status: status,
            failReason: failReason
        },
        success: function (result) {
            if (result === "SUCCESS") {
                alert("已发送审核未通过请求。");
            }
            getData(pageNum, dataUrl);
        }
    });
}
