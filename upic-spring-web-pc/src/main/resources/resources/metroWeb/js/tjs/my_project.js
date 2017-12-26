/*
 * @Author: Marte
 * @Date:   2017-10-09 19:01:21
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-11-06 10:23:46
 */
var dataUrl = "/common/getProjectByGuidanceNum";
var searchKeyWordUrl = "/common/myProjectSearchBar";
var getProjectTypeUrl = "/common/getAllProjectCategory";
var getPeopleByProjectNumUrl = "/common/getSignUpNumberByProjectNum";
var getSignUpPeopleByProjectNumUrl = "/common/getSignUpPeopleByProjectNum";
var getqrCodeUrl = "/teacher/qrCodeGenerate";
var getProjectStatusUrl = "/common/getAllProjectImplementationProcess";
var exportExcelUrl = "/common/exportProjectByGuidanceNum";
var saveUrl = "/common/updateMyProject";
var changeProjectStatusUrl = "/common/changeProjectStatusUrl";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {};
var qrcode = "";
var timeId = "";

$(function () {
    qrcode = new QRCode(document.getElementById("qrcode"), {
        width: 96,//设置宽高
        height: 96
    });
    pageSize = $("#select-small").children('option:selected').text()
    getData(pageNum, dataUrl);
    commonAjax(getProjectTypeUrl, null, "addProjectType", "GET");
    commonAjax(getProjectStatusUrl, null, "addProjectStatus", "GET");
    registSelect("projectCategory");
    registSelect("implementationProcess");

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

function addProjectStatus(res) {
    var data = res;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>项目状态筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i] + "</option>";
    }
    $("#implementationProcess").html(htmls);

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
        if (data[i].implementationProcess === "SAVED" || data[i].implementationProcess === "IN_AUDIT" || data[i].implementationProcess === "IN_AUDIT_AGAIN" || data[i].implementationProcess === "IN_AUDIT_FINAL" || data[i].implementationProcess === "IN_AUDIT_FAIL" || data[i].implementationProcess === "IN_AUDIT_AGAIN_FAIL" || data[i].implementationProcess === "IN_AUDIT_FINAL_FAIL" || data[i].implementationProcess === "CHECKING_FAIL" || data[i].implementationProcess === "CHECKING_AGAIN_FAIL" || data[i].implementationProcess === "CHECKING_FINAL_FAIL") {
            htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].projectNum + "'/></td>";
            htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
            htmls += "<td>" + data[i].projectNum + "</td>";
            htmls += "<td>" + data[i].projectCategory + "</td>";
            htmls += "<td>" + data[i].projectName + "</td>";
            htmls += "<td>" + data[i].integral + "</td>";
            htmls += "<td id='peopleNum" + data[i].projectNum + "'>" + "10/" + data[i].maximum + "</td>";
            htmls += "<td id='workSummary" + data[i].projectNum + "'>" + "1000/" + (parseFloat(data[i].integral) * parseInt(data[i].maximum)) + "</td>";
            htmls += "<td>" + getDate(data[i].startTime, "yyyy/MM/dd hh:mm") + "</td>";
            htmls += "<td class='center_td'>" + status + "</td>";
            htmls += "<td class='center_td'>";
            htmls += " <div class='message_div'><a href='#mymodal2' data-toggle='modal'><span onclick=commonAjax('" + dataUrl + "','projectNum=" + data[i].projectNum + "','getProjectInfo2','GET','" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "')>编辑</span></a><span class='space'>|</span><a><span onclick=commonAjax('" + changeProjectStatusUrl + "','projectNum=" + data[i].projectNum + "','subOne','GET')>提交</span></a></div></td>";
            htmls += " </tr>";
        } else {
            htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].projectNum + "'/></td>";
            htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
            htmls += "<td>" + data[i].projectNum + "</td>";
            htmls += "<td>" + data[i].projectCategory + "</td>";
            htmls += "<td>" + data[i].projectName + "</td>";
            htmls += "<td>" + data[i].integral + "</td>";
            htmls += "<td id='peopleNum" + data[i].projectNum + "'>" + "10/" + data[i].maximum + "</td>";
            htmls += "<td id='workSummary" + data[i].projectNum + "'>" + "1000/" + (parseFloat(data[i].integral) * parseInt(data[i].maximum)) + "</td>";
            htmls += "<td>" + getDate(data[i].startTime, "yyyy/MM/dd hh:mm") + "</td>";
            htmls += "<td class='center_td'>" + status + "</td>";
            htmls += "<td class='center_td'>";
            if (data[i].implementationProcess === "ENROLLMENT") {
                htmls += " <div class='message_div'><a href='#mymodal3' data-toggle='modal'><span onclick=commonAjax('" + dataUrl + "','projectNum=" + data[i].projectNum + "','getProjectInfo','GET','" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "')>详情</span></a><span class='space'>|</span><a href='#mymodal5' data-toggle='modal'><span onclick=commonAjax('" + getSignUpPeopleByProjectNumUrl + "','projectNum=" + data[i].projectNum + "','getPeopleInfo','GET','" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "')>名单</span></a><span class='space'>|</span><a href='#mymodal1' data-toggle='modal'><span onclick=commonMyAjax('" + getqrCodeUrl + "','projectNum=" + data[i].projectNum + "&freshTime=30000','getQrCode','GET',30000)>点击查看二维码</span></a></div></td>";
            } else {
                htmls += " <div class='message_div'><a href='#mymodal3' data-toggle='modal'><span onclick=commonAjax('" + dataUrl + "','projectNum=" + data[i].projectNum + "','getProjectInfo','GET','" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "')>详情</span></a><span class='space'>|</span><a href='#mymodal5' data-toggle='modal'><span onclick=commonAjax('" + getSignUpPeopleByProjectNumUrl + "','projectNum=" + data[i].projectNum + "','getPeopleInfo','GET','" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "')>名单</span></a></div></td>";
            }
            htmls += " </tr>";
        }

        var myData = {
            integral: data[i].integral,
            maximum: data[i].maximum,
            projectNum: data[i].projectNum
        }

        commonAjax(getPeopleByProjectNumUrl, "projectNum=" + data[i].projectNum, "getPeopleByProjectNum", "GET", myData);
    }

    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number, requestData);
}

function commonMyAjax(url, data, method, requestType, refreshTime) {
     timeId = window.setInterval("qrAjax('"+url+"','"+data+"','"+method+"','"+requestType+"')", parseInt(refreshTime));
//	commonAjax(url, data, method, requestType);
//	commonAjax(url+"?"+data, "", method, requestType);
	
}

function  qrAjax(url, data, method, requestType, refreshTime){
	$.ajax({
        type: requestType,
        url: url+"?"+data,
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (result) {
        	getQrCode(result);
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function (err) {
        	alert(err.msg);
        }
    });
}
function closeTheInterval() {
    clearInterval(timeId);
}

function getPeopleByProjectNum(data, myData) {
    $("#peopleNum" + myData.projectNum).html(data + "/" + myData.maximum);
    $("#workSummary" + myData.projectNum).html((parseInt(data) * parseFloat(myData.integral)) + "/" + (parseInt(myData.maximum) * parseFloat(myData.integral)));
    $("#detailPeopleNum" + myData.projectNum).html(data + "/" + myData.maximum);
    $("#detailWorkSummary" + myData.projectNum).html((parseInt(data) * parseFloat(myData.integral)) + "/" + (parseInt(myData.maximum) * parseFloat(myData.integral)));
}

function getProjectInfo(datas, sendData) {
    var data = datas.content[0];
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
    var htmls = "";
    htmls += "<div class='modal-header'>";
    htmls += "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
    htmls += "<h4>详情</h4>";
    htmls += "</div>";
    htmls += "<div class='modal-body'>";
    htmls += "<div class='row-fluid'>";
    htmls += "<div class='block-fluid'>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>编号</div>"
    htmls += "<div class='span3'>" + sendData + "</div>";
    htmls += "<div class='span3'>代码</div>";
    htmls += "<div class='span3'>" + data.projectNum + "</div>";
    htmls += "</div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>项目申请日期</div>";
    htmls += "<div class='span3'>" + getDate(data.creatTime, "yyyy/MM/dd hh:mm") + "</div>";
    htmls += "<div class='span3'>状态</div><div class='span3'>" + status + "</div></div>";
    htmls += "<div class='row-form clearfix'><div class='span3'>项目类别</div><div class='span3'>" + data.projectCategory + "</div><div class='span3'>申报单位</div><div class='span3'>" + data.declareUnit + "</div></div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>项目名称</div>";
    htmls += "<div class='span3'>" + data.projectName + "</div>";
    htmls += "<div class='span3'>负责人</div>";
    htmls += "<div class='span3'>" + data.guidanceMan + "</div>";
    htmls += "</div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>工作量</div>";
    htmls += "<div class='span3' id='detailWorkSummary" + data.projectNum + "'></div>"; //" + (parseFloat(data.integral) * parseInt(data.maximum)) + "
    htmls += "<div class='span3'>积分</div>";
    htmls += "<div class='span3'>" + data.integral + "</div>";
    htmls += "</div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>开始时间</div>";
    htmls += "<div class='span3'>" + getDate(data.startTime, "yyyy-MM-dd hh:mm") + "</div>";
    htmls += "<div class='span3'>结束时间</div>";
    htmls += "<div class='span3'>" + getDate(data.endTime, "yyyy-MM-dd hh:mm") + "</div>";
    htmls += "</div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>参与人数</div>";
    htmls += "<div class='span3' id='detailPeopleNum" + data.projectNum + "'></div>"; //" + data.maximum + "
    htmls += "</div><div class='row-form clearfix'><div class='span3'>项目内容</div><div class='span9'>" + data.content + "</div></div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>评价标准与形式</div>";
    htmls += "<div class='span9'>" + data.checkAssessmentCriteraAndForm + "</div></div>";
    htmls += "</div><div class='dr'><span></span></div></div></div><div class='modal-footer'>";
    htmls += "<button class='btn' data-dismiss='modal' aria-hidden='true'>关闭</button></div>";
    $("#mymodal3").html(htmls);

    var myData = {
        integral: data.integral,
        maximum: data.maximum,
        projectNum: data.projectNum
    }

    commonAjax(getPeopleByProjectNumUrl, "projectNum=" + data.projectNum, "getPeopleByProjectNum", "GET", myData);
}

function getProjectInfo2(datas, sendData) {
    var data = datas.content[0];
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
    var htmls = "";
    htmls += "<div class='modal-header'>";
    htmls += "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
    htmls += "<h4>详情</h4>";
    htmls += "</div>";
    htmls += "<div class='modal-body'>";
    htmls += "<div class='row-fluid'>";
    htmls += "<div class='block-fluid'>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>编号</div>";
    htmls += "<div class='span3'>" + sendData + "</div>";
    htmls += "<div class='span3'>代码</div>";
    htmls += "<div class='span3' id='projectNum'>" + data.projectNum + "</div>";
    htmls += "</div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>项目申请日期</div>";
    htmls += "<div class='span3' id='creatTime' name='" + data.creatTime + "'>" + getDate(data.creatTime, "yyyy/MM/dd hh:mm") + "</div>";
    htmls += "<div class='span3'>状态</div>";
    htmls += "<div class='span3' id='status'>" + status + "</div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>项目类别</div>";
    htmls += "<div class='span3'>";

    // htmls += data.projectCategory;
    htmls += "<select class='input-large m-wrap' tabindex='1' id='getProjectType'>";
    htmls += "</select>";
    htmls += "</div>";
    htmls += "<div class='span3'>申报单位</div>";
    htmls += "<div class='span3'><input type='text' id='college' value='" + data.declareUnit + "'></div>";
    htmls += "</div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>项目名称</div>";
    htmls += "<div class='span3'><input type='text' id='projectName' value='" + data.projectName + "'></div>";
    htmls += "<div class='span3'>负责人</div>";
    htmls += "<div class='span3'><input type='text' id='guidanceMan' value='" + data.guidanceMan + "'></div>";
    htmls += "</div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>工作量</div>";
    htmls += "<div class='span3'><input readonly type='text' id='gzl' value='" + (parseFloat(data.integral) * parseInt(data.maximum)) + "'></div>";
    htmls += "<div class='span3'>积分</div>";
    htmls += "<div class='span3'><input type='text' id='integral' value='" + data.integral + "'></div>";
    htmls += "</div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>开始时间</div>";
    htmls += "<div class='span3'><div class='input-append date' id='datetimepicker1' data-date='2017/09/14' data-date-format='dd-mm-yyyy'>";
    htmls += "<input class='span2 nowdate' id='startTime' name='" + data.startTime + "' size='16' type='text' value='" + getDate(data.startTime, "yyyy-MM-dd hh:mm") + "' style='width:100px !important;' />";
    htmls += "<span class='add-on'><i class='icon-th'></i></span>";
    htmls += "</div></div>";
    htmls += "<div class='span3'>结束时间</div>";
    htmls += "<div class='span3'><div class='input-append date' id='datetimepicker1' data-date='2017/09/14' data-date-format='dd-mm-yyyy'>";
    htmls += "<input class='span2 nowdate' size='16' id='endTime' name='" + data.endTime + "' type='text' value='" + getDate(data.endTime, "yyyy-MM-dd hh:mm") + "' style='width:100px !important;' />";
    htmls += "<span class='add-on'><i class='icon-th'></i></span>";
    htmls += "</div></div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>总人数</div>";
    htmls += "<div class='span3'><input type='text' id='maximum' value='" + data.maximum + "'></div>";
    htmls += "</div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>项目内容</div>";
    htmls += "<div class='span9'>";
    htmls += "<textarea id='content'>" + data.content + "</textarea></div>";
    htmls += "</div>";
    htmls += "<div class='row-form clearfix'>";
    htmls += "<div class='span3'>评价标准与形式</div>";
    htmls += "<div class='span9'><textarea id='checkAssessmentCriteraAndForm'>" + data.checkAssessmentCriteraAndForm + "</textarea></div>";
    htmls += "</div>";
    if (data.implementationProcess === "IN_AUDIT_FAIL" || data.implementationProcess === "IN_AUDIT_AGAIN_FAIL" || data.implementationProcess === "IN_AUDIT_FINAL_FAIL" || data.implementationProcess === "CHECKING_FAIL" || data.implementationProcess === "CHECKING_AGAIN_FAIL" || data.implementationProcess === "CHECKING_FINAL_FAIL") {
        htmls += "<div class='row-form clearfix'>";
        htmls += "<div class='span3'>审核记录</div>";
        htmls += "<div class='span3' id='checkLog" + data.id + "'></div>";
        htmls += "</div>";
        htmls += "<div class='row-form clearfix'>";
        htmls += "<div class='span3'>原因</div>";
        htmls += "<div class='span9' id='checkReason" + data.id + "'></div>";
        htmls += "</div>";

        // 获取审核记录与原因
        commonAjax()
    }
    htmls += "</div>";
    htmls += "<div class='dr'><span></span></div>";
    htmls += "</div>";
    htmls += "</div>";
    htmls += "<div class='modal-footer'>";
    htmls += "<button class='btn btn-primary' data-dismiss='modal' aria-hidden='true' onclick=save()>保存</button>";
    htmls += "<button class='btn' data-dismiss='modal' aria-hidden='true'>关闭</button>";
    htmls += "</div>";
    $("#mymodal2").html(htmls);

    commonAjax(getProjectTypeUrl, '', 'getProjectType', 'GET', data.projectCategory);
}

function getPeopleInfo(data) {
    var htmlss = "";
    htmlss += "<tr> <th>编号</th> <th>所属学院</th> <th>班级</th> <th>学号</th> <th>姓名</th> <th>状态</th> </tr>";
    for (var i = 0; i < data.length; i++) {
        htmlss += "<tr><td>" + (i + 1) + "</td><td>" + data[i].college + "</td><td>" + data[i].clazz + "</td><td>" + data[i].userNum + "</td><td>" + data[i].username + "</td><td>" + data[i].field1 + "</td></tr>";
    }
    $("#getPeopleInfo").html(htmlss);
}

function getProjectType(res, projectCategory) {
    var htmls = "";
    var data = res.content;
    htmls += "<option value='category" + 1 + "'>" + projectCategory + "</option>";
    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='category" + (i + 1) + "'>" + data[i].categoryName + "</option>";
    }
    $("#getProjectType").html(htmls);

}

function save() {
    var projectInfo = {};
    projectInfo.projectNum = $("#projectNum").text();
    projectInfo.creatTime = $("#creatTime").attr("name");
    projectInfo.projectCategory = $("#getProjectType option:selected").text();
    projectInfo.declareUnit = $("#college").val();
    projectInfo.projectName = $("#projectName").val();
    projectInfo.guidanceMan = $("#guidanceMan").val();
    projectInfo.integral = $("#integral").val();
    projectInfo.startTime = $("#startTime").attr("name");
    projectInfo.endTime = $("#endTime").attr("name");
    projectInfo.maximum = $("#maximum").val();
    projectInfo.content = $("#content").val();
    projectInfo.checkAssessmentCriteraAndForm = $("#checkAssessmentCriteraAndForm").val();

    var str = JSON.stringify(projectInfo);

    var obj = new Object();

    obj.projectInfo = str;

    commonAjax(saveUrl, obj, "subOne", "POST");
}

function subOne(data) {
    if (data === "SUCCESS") {
        alert("保存成功");
        getData(0, dataUrl);
    }
}

function changeEnglishChinese(value) {
    var changeValue = "";
    switch (value) {
        case ("已保存"):
            changeValue = "SAVED";
            break;
        case ("待初审"):
            changeValue = "IN_AUDIT";
            break;
        case ("待复审"):
            changeValue = "IN_AUDIT_AGAIN";
            break;
        case ("待终审"):
            changeValue = "IN_AUDIT_FINAL";
            break;
        case ("已审核"):
            changeValue = "AUDITED";
            break;
        case ("报名中"):
            changeValue = "ENROLLMENT";
            break;
        case ("进行中"):
            changeValue = "HAVE_IN_HAND";
            break;
        case ("已完成"):
            changeValue = "COMPLETED";
            break;
        case ("待初验"):
            changeValue = "CHECKING";
            break;
        case ("待复验"):
            changeValue = "CHECKING_AGAIN";
            break;
        case ("待终验"):
            changeValue = "CHECKING_FINAL";
            break;
        case ("已验收"):
            changeValue = "CHECKED";
            break;
        case ("待初审失败"):
            changeValue = "IN_AUDIT_FAIL";
            break;
        case ("待复审失败"):
            changeValue = "IN_AUDIT_AGAIN_FAIL";
            break;
        case ("待终审失败"):
            changeValue = "IN_AUDIT_FINAL_FAIL";
            break;
        case ("待初验失败"):
            changeValue = "CHECKING_FAIL";
            break;
        case ("待复验失败"):
            changeValue = "CHECKING_AGAIN_FAIL";
            break;
        case ("待终验失败"):
            changeValue = "CHECKING_FINAL_FAIL";
            break;
        default:
    }
    return changeValue;
}

function getQrCode(data) {
    qrcode.makeCode("");
    qrcode.makeCode(data);
}