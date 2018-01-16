var dataUrl = "/common/getProject";
var exportExcelUrl = "/common/exportProject";
var searchKeyWordUrl = "/common/projectSearchBar";
var getProjectInfoUrl = "/common/getProjectInfo";
var getProjectTypeUrl = "/common/getAllProjectCategory";
var getProjectCollegeUrl = "/common/getCollege";
var getSignUpPeopleByProjectNumUrl = "/common/getSignUpPeopleByProjectNum";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {};

$(function () {
    pageSize = $("#select-small").children('option:selected').text();
    getData(pageNum, dataUrl);
    commonAjax(getProjectTypeUrl, null, "addProjectType", "GET");
    commonAjax(getProjectCollegeUrl, null, "addProjectCollege", "GET");
    registSelect("projectCategory");
    registProjectStatusSelect("implementationProcess");
    registSelect("declareUnit");

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

function addProjectCollege(res) {
    var data = res.content;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>申报单位筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].college + "</option>";
    }
    $("#declareUnit").html(htmls);
}

function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        var status = "";
        switch (data[i].implementationProcess) {
            case ("SAVED"):
                status = "已保存";
                break;
            case ("IN_AUDIT"):
                status = "待初审";
                break;
            case ("IN_AUDIT_AGAIN"):
                status = "待复审";
                break;
            case ("IN_AUDIT_FINAL"):
                status = "待终审";
                break;
            case ("AUDITED"):
                status = "已审核";
                break;
            case ("ENROLLMENT"):
                status = "报名中";
                break;
            case ("HAVE_IN_HAND"):
                status = "进行中";
                break;
            case ("COMPLETED"):
                status = "已完成";
                break;
            case ("CHECKING"):
                status = "待初验";
                break;
            case ("CHECKING_AGAIN"):
                status = "待复验";
                break;
            case ("CHECKING_FINAL"):
                status = "待终验";
                break;
            case ("CHECKED"):
                status = "已验收";
                break;
            case ("IN_AUDIT_FAIL"):
                status = "待初审失败";
                break;
            case ("IN_AUDIT_AGAIN_FAIL"):
                status = "待复审失败";
                break;
            case ("IN_AUDIT_FINAL_FAIL"):
                status = "待终审失败";
                break;
            case ("CHECKING_FAIL"):
                status = "待初验失败";
                break;
            case ("CHECKING_AGAIN_FAIL"):
                status = "待复验失败";
                break;
            case ("CHECKING_FINAL_FAIL"):
                status = "待终验失败";
                break;
            default:
        }

        htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].projectNum + "'/></td>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td>" + data[i].projectNum + "</td>";
        htmls += "<td>" + data[i].projectCategory + "</td>";
        htmls += "<td>" + data[i].projectName + "</td>";
        htmls += "<td>" + data[i].declareUnit + "</td>";
        htmls += "<td>" + data[i].integral + "</td>";
        htmls += "<td><a href='#mymodal5' data-toggle='modal'><span onclick=commonAjax('" + getSignUpPeopleByProjectNumUrl + "','projectNum=" + data[i].projectNum + "','getPeopleInfo','GET','" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "')>" + data[i].maximum + "</span></a></td>";
        htmls += "<td>" + data[i].guidanceMan + "</td>";
        htmls += "<td class='center_td'>" + status + "</td>";
        htmls += "<td class='center_td'><a href='#mymodal1' data-toggle='modal'>";
        htmls += "<div class='message_div' onclick=commonAjax('" + getProjectInfoUrl + "','projectNum=" + data[i].projectNum + "','getProjectInfo','GET','" + (i + 1) + "')>查看详情</div></a></td></tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function getPeopleInfo(data) {
    var htmlss = "";
    htmlss += "<tr> <th>编号</th> <th>所属学院</th> <th>班级</th> <th>学号</th> <th>姓名</th> <th>状态</th> </tr>";
    for (var i = 0; i < data.length; i++) {
        htmlss += "<tr><td>" + (i + 1) + "</td><td>" + data[i].college + "</td><td>" + data[i].clazz + "</td><td>" + data[i].userNum + "</td><td>" + data[i].username + "</td><td>" + data[i].field1 + "</td></tr>";
    }
    $("#getPeopleInfo").html(htmlss);
}

function getProjectInfo(data, j) {
    var htmlss = "";
    var status = "";
    switch (data.implementationProcess) {
        case ("SAVED"):
            status = "已保存";
            break;
        case ("IN_AUDIT"):
            status = "待初审";
            break;
        case ("IN_AUDIT_AGAIN"):
            status = "待复审";
            break;
        case ("IN_AUDIT_FINAL"):
            status = "待终审";
            break;
        case ("AUDITED"):
            status = "已审核";
            break;
        case ("ENROLLMENT"):
            status = "报名中";
            break;
        case ("HAVE_IN_HAND"):
            status = "进行中";
            break;
        case ("COMPLETED"):
            status = "已完成";
            break;
        case ("CHECKING"):
            status = "待初验";
            break;
        case ("CHECKING_AGAIN"):
            status = "待复验";
            break;
        case ("CHECKING_FINAL"):
            status = "待终验";
            break;
        case ("CHECKED"):
            status = "已验收";
            break;
        case ("IN_AUDIT_FAIL"):
            status = "待初审失败";
            break;
        case ("IN_AUDIT_AGAIN_FAIL"):
            status = "待复审失败";
            break;
        case ("IN_AUDIT_FINAL_FAIL"):
            status = "待终审失败";
            break;
        case ("CHECKING_FAIL"):
            status = "待初验失败";
            break;
        case ("CHECKING_AGAIN_FAIL"):
            status = "待复验失败";
            break;
        case ("CHECKING_FINAL_FAIL"):
            status = "待终验失败";
            break;
        default:
    }

    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>编号</div>";
    htmlss += "<div class='span3'>" + (parseInt(pageNum) * parseInt(pageSize) + j) + "</div>";
    htmlss += "<div class='span3'>代码</div>";
    htmlss += "<div class='span3'>" + data.projectNum + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目申请日期</div>";
    htmlss += "<div class='span3'>" + getDate(data.creatTime, "yyyy-MM-dd hh:mm") + "</div>";
    htmlss += "<div class='span3'>状态</div>";
    htmlss += "<div class='span3'>" + status + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目类别</div>";
    htmlss += "<div class='span3'>";
    htmlss += "<select id='" + data.projectNum + "1234'>";
    htmlss += "<option value='category " + 0 + "'>" + data.projectCategory + "</option>";
    htmlss += commonAjax(getProjectTypeUrl, '', 'getMyProjectTypeUrl', 'GET', data.projectNum);
    htmlss += "</select>";
    htmlss += "</div>";
    htmlss += "<div class='span3'>申报单位</div>";
    htmlss += "<div class='span3'><input id='myDeclareUnit1234' type='text' myVal='" + data.declareUnit + "' placeholder='" + data.declareUnit + "'></div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目名称</div>";
    htmlss += "<div class='span3'><input id='myProjectName1234' type='text' myVal='" + data.projectName + "' placeholder='" + data.projectName + "'></div>";
    htmlss += "<div class='span3'>负责人</div>";
    htmlss += "<div class='span3'><input id='myGuidance1234' type='text' myVal='" + data.guidanceMan + "' placeholder='" + data.guidanceMan + "'></div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>工作量</div>";
    htmlss += "<div class='span3'><input type='text' placeholder='" + (parseFloat(data.integral) * parseInt(data.maximum)) + "'></div>";
    htmlss += "<div class='span3'>积分</div>";
    htmlss += "<div class='span3'><input id='myIntegral1234' type='text' myVal='" + data.integral + "' placeholder='" + data.integral + "'></div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>开始时间</div>";
    htmlss += "<div class='span3'><div class='input-append date' id='datetimepicker1' data-date='2017/09/14' data-date-format='dd-mm-yyyy'>";
    htmlss += "<input id='myStartTime1234' class='span2 nowdate' size='16' type='text' value='12-02-2012' style='width:100px !important;'>";
    htmlss += "<span class='add-on'><i class='icon-th'></i></span>";
    htmlss += "</div></div>";
    htmlss += "<div class='span3'>结束时间</div>";
    htmlss += "<div class='span3'><div class='input-append date' id='datetimepicker1' data-date='2017/09/14' data-date-format='dd-mm-yyyy'>";
    htmlss += "<input id='myEndTime1234' class='span2 nowdate' size='16' type='text' value='12-02-2012' style='width:100px !important;'>";
    htmlss += "<span class='add-on'><i class='icon-th'></i></span>";
    htmlss += "</div></div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>最大参与人数</div>";
    htmlss += "<div class='span3'><input id='myMaximum1234' type='text' myVal='" + data.maximum + "' placeholder='" + data.maximum + "'></div>";
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

    var htmls = "";
    htmls += "<button class='btn btn-primary' data-dismiss='modal' aria-hidden='true' onclick=commonMyAjax('/common/updateMyProject','" + data.projectNum + "')>保存</button>";
    htmls += "<button class='btn' data-dismiss='modal' aria-hidden='true'>关闭</button>";
    $("#theUnderButton").html(htmls);
}

function getMyProjectTypeUrl(res, id) {
    var htmls = "";
    var data = res.content;
    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='category " + (i + 1) + "'>" + data[i].categoryName + "</option>";
    }
    $("#" + id + "1234").append(htmls);
}

function commonMyAjax(url, projectNum) {
    var projectCategory = $("#" + projectNum + "1234").find("option:selected").text();
    var declareUnit = getNowData("myDeclareUnit1234");
    var projectName = getNowData("myProjectName1234");
    var guidance = getNowData("myGuidance1234");
    var integral = getNowData("myIntegral1234");
    var startTime = $("#myStartTime1234").val();
    var endTime = $("#myEndTime1234").val();
    var maximum = getNowData("myMaximum1234");

    var projectInfoJson = {
        projectNum: projectNum,
        projectCategory: projectCategory,
        declareUnit: declareUnit,
        projectName: projectName,
        guidance: guidance,
        integral: integral,
        startTime: startTime,
        endTime: endTime,
        maximum: maximum
    }

    var projectInfo = "{ ";
    for (var item in projectInfoJson) {
        projectInfo += "'" + item + "':'" + projectInfoJson[item] + "',";
    }
    projectInfo += " }";

    $.ajax({
        type: "POST",
        url: url,
        data: {
            projectInfo: projectInfo
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (result) {
            alert("修改成功");
            getData(pageNum, dataUrl);
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

function getNowData(id) {
    return $("#" + id).val() == "" ? $("#" + id).attr("myVal") : $("#" + id).val();
}

// 下拉框注册监听
function registProjectStatusSelect(id) {
    $("#" + id).change(function () {
        var name = $(this).attr("name");
        var value = $(this).children('option:selected').text();
        var implementationProcess = null;
        delete requestData.implementationProcess;
        delete requestData.field5;
        switch (value) {
            case "已保存":
                implementationProcess = "SAVED";
                break;
            case "待审核":
                implementationProcess = "IN_AUDIT/IN_AUDIT_AGAIN/IN_AUDIT_FINAL";
                name = "field5";
                break;
            case "进行中":
                implementationProcess = "AUDITED/ENROLLMENT/HAVE_IN_HAND";
                name = "field5";
                break;
            case "待验收":
                implementationProcess = "CHECKING/CHECKING_AGAIN/CHECKING_FINAL";
                name = "field5";
                break;
            case "已完成":
                implementationProcess = "COMPLETED/CHECKED";
                name = "field5";
                break;
            case "驳回待调整":
                implementationProcess = "IN_AUDIT_FAIL/IN_AUDIT_AGAIN_FAIL/IN_AUDIT_FINAL_FAIL/CHECKING_FAIL/CHECKING_AGAIN_FAIL/CHECKING_FINAL_FAIL";
                name = "field5";
                break;
        }
        eval('(' + "requestData." + name + "=\"" + implementationProcess + '\")');
        getData(0, dataUrl);
    });
}