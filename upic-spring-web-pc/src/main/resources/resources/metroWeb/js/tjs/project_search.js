var dataUrl = "/common/getProject";
var searchKeyWordUrl = "/common/projectSearchBar";
var getProjectTypeUrl = "/common/getAllProjectCategory";
var getCollegeUrl = "/common/getCollege";
var getNowNumUrl = "/common/getSignUpNumberByProjectNum";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {};

$(function () {
    pageSize = $("#select-small").children('option:selected').text()
    commonAjax(getProjectTypeUrl, null, "addProjectType", "GET");
    commonAjax(getCollegeUrl, null, "addCollege", "GET");
    registSelect("projectCategory");
    registSelect("college");
    getData(pageNum, dataUrl);

    //模拟form导出
    $("#exportBtn").click(function () {
        var baseModels = ["projectNum", "declareUnit", "projectName", "guidanceMan", "guidanceNum", "projectCategory", "integral", "startTime", "endTime", "maximum"];
        var str = JSON.stringify(baseModels);
        var form = $("<form></form>").attr("action", exportExcelUrl).attr("method", "GET");
        form.append($("<input></input>").attr("type", "hidden").attr("name", "baseModel").attr("value", str));
        var formKeyValue = appendForm(requestData);
        for (var i = 0; i < formKeyValue.key.length; i++) {
            form.append($("<input></input>").attr("type", "hidden").attr("name", formKeyValue.key[i]).attr("value", formKeyValue.value[i]));
        }
        form.appendTo('body').submit().remove();
        form.submit();
    })
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

function addCollege(res) {
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
        htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].projectNum + "'/></td>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td>" + data[i].projectNum + "</td>";
        htmls += "<td>" + data[i].projectCategory + "</td>";
        htmls += "<td>" + data[i].projectName + "</td>";
        htmls += "<td>" + data[i].collegeOtherName + "</td>";
        htmls += "<td>" + data[i].guidanceNum + "</td>";
        htmls += "<td>" + data[i].guidanceMan + "</td>";
        htmls += "<td>" + data[i].integral + "</td>";
        htmls += "<td>";
        htmls += "<span id='" + data[i].projectNum + "1'>";
        getNowNum(data[i].projectNum, data[i].projectNum + "1");
        htmls += "</span>";
        htmls += "<span>/</span>";
        htmls += "<span>";
        htmls += data[i].maximum;
        htmls += "</span>";
        htmls += "</td>";
        htmls += "<td>" + getDate(data[i].creatTime, "yyyy-MM-dd hh:mm") + "</td>";
        htmls += "<td class='center_td'><a href='#mymodal1'";
        htmls += "data-toggle='modal'><div class='message_div' onclick=commonAjax('" + dataUrl + "','projectNum" + data[i].projectNum + "','getProjectInfo','GET')>查看详情</div></a></td></tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function getNowNum(pN, id) {
    $.ajax({
        type: "GET",
        url: getNowNumUrl,
        data: {
            projectNum: pN
        },
        success: function (result) {
            $("#" + id).html(result)
        }
    });
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

    htmlss += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data.projectNum + "'/></td>";
//    htmlss += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
    htmlss += "<td>" + data.projectNum + "</td>";
    htmlss += "<div class='span3'>代码</div>";
    htmlss += "<div class='span3'>" + data.projectNum + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目申请日期</div>";
    htmlss += "<div class='span3'>" + getDate(data.creatTime, 'yyyy-MM-dd') + "</div>";
    htmlss += "<div class='span3'>状态</div>";
    htmlss += "<div class='span3'>" + statuss + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目类别</div>";
    htmlss += "<div class='span3'>" + data.projectCategory + "</div>";
    htmlss += "<div class='span3'>所属学院</div>";
    htmlss += "<div class='span3'>" + data.declareUnit + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目名称</div>";
    htmlss += "<div class='span3'>" + data.projectName + "</div>";
    htmlss += "<div class='span3'>负责人</div>";
    htmlss += "<div class='span3'>" + data.guidanceMan + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>工作量</div>";
    htmlss += "<div class='span3'><span id='" + data.projectNum + "gzl'>";
    getGZL(data.projectNum, data.projectNum + "gzl", data.integral);
    htmlss += "</span><span>/</span><span>" + (data.maximum * data.integral) + "</span></div>";
    htmlss += "</div>";
    htmlss += "<div class='span3'>积分</div>";
    htmlss += "<div class='span3'>" + data.integral + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>开始时间</div>";
    htmlss += "<div class='span3'>" + getDate(data.startTime, 'yyyy-MM-dd') + "</div>";
    htmlss += "<div class='span3'>结束时间</div>";
    htmlss += "<div class='span3'>" + getDate(data.endTime, 'yyyy-MM-dd') + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>参与人数</div>";
    htmlss += "<div class='span3'><span id='" + data.projectNum + "2'>";
    getNowNum(data.projectNum, data.projectNum + "2");
    htmlss += "</span><span>/</span><span>" + data.maximum + "</span></div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目内容</div>";
    htmlss += "<div class='span9'>" + data.content + "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>评价标准与形式</div>";
    htmlss += "<div class='span9'>" + data.checkAssessmentCriteraAndForm + "</div>";
    htmlss += "</div>";

    $("#getProjectInfo").html(htmlss);
}

//获取工作量
function getGZL(pN, id, integral) {
    $.ajax({
        type: "GET",
        url: getNowNumUrl,
        data: {
            projectNum: pN
        },
        success: function (result) {
            $("#" + id).html(result * integral)
        }
    })
}