/*
 * @Author: Marte
 * @Date:   2017-10-12 08:40:02
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-11-03 10:12:08
 */

var dataUrl = "/systemManager/getProjectBySql";
var searchKeyWordUrl = "/common/projectSearchBar";
var getProjectNumUrl = "";
var getProjectStatusUrl = "";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {};

$(function () {
    pageSize = $("#select-small").children('option:selected').text()
    getData(pageNum, dataUrl);
    // commonAjax(getProjectNumUrl,null,"addProjectNum","GET");
    // commonAjax(getProjectStatusUrl,null,"addProjectStatus","GET");
    registSelect("projectNum");
    registSelect("ProjectStatus");
})
function addProjectNum(res) {
    var data = res.content;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>项目编号筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].projectNum + "</option>";
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
    $("#getCollege").html(htmls);
}


function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        var statuss = "";
        if (data[i].implementationProcess === "IN_AUDIT" || data[i].implementationProcess === "IN_AUDIT_AGAIN" || data[i].implementationProcess === "IN_AUDIT_FINAL") {
            if (data[i].implementationProcess === "CHECKING") {
                statuss = "待初验";
            } else if (data[i].implementationProcess === "CHECKING_AGAIN") {
                statuss = "待复验";
            } else if (data[i].implementationProcess === "CHECKING_FINAL") {
                statuss = "待终验";
            }

            htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].projectNum + "'/></td>";
            htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
            htmls += "<td>" + data[i].projectNum + "</td>";
            htmls += "<td>" + data[i].projectCategory + "</td>";
            htmls += "<td>" + data[i].projectName + "</td>";
            htmls += "<td>" + data[i].integral + "</td>";
            htmls += "<td>" + data[i].maximum + "</td>";
            htmls += "<td>" + data[i].guidanceMan + "</td>";
            htmls += "<td>" + (parseFloat(data[i].integral) * parseInt(data[i].maximum)) + "</td>";
            htmls += "<td>" + getDate(data[i].startTime, "yyyy/MM/dd hh:mm") + "</td>";
            htmls += "<td>" + getDate(data[i].endTime, "yyyy/MM/dd hh:mm") + "</td>";
            htmls += "<td class='center_td'>" + statuss + "</td>";
            htmls += "<td class='center_td'><a href='#mymodal1'";
            htmls += "data-toggle='modal'><div class='message_div' onclick=commonAjax('" + dataUrl + "','projectNum=" + data[i].projectNum + "','getProjectInfo','GET')>查看详情</div></a></td></tr>";
        }
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}


function getProjectInfo(data) {
    var htmlss = "";
    var statuss = "";
    if (data[i].implementationProcess === "COMPLETED") {
        statuss = "未验收"
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
    htmlss += "<div class='span3'>" + data.guidanceMan + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>" + data.content + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>评价标准与形式</div>";
    htmlss += "<div class='span9'></div>";
    htmlss += "</div>";

    $("#getProjectInfo").html(htmlss);
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
        url: changeStatusUrl,
        data: {
            "projectNumList": projectNumList,
            "status": status
        },
        success: function (result) {
            alert("已发送 审核通过请求")
        }
    });
    getData(pageNum, dataUrl);
}

function notPass() {
    var projectNumList = new Array();
    //获取选中框的projectNum放入list
    $("input[type=checkbox]:checked").each(function () {
        projectNumList.push($(this).attr("id"));
    });
    var status = "NOTPASS";

    $.ajax({
        type: "GET",
        url: changeStatusUrl,
        data: {
            "projectNumList": projectNumList,
            "status": status,
        },
        success: function (result) {
            alert("已发送 审核不通过请求")
        }
    });
    getData(pageNum, dataUrl);

}
