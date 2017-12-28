/*
 * @Author: Marte
 * 积分申报
 * @Date:   2017-09-24 10:17:37
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-11-06 19:58:00
 */

/*
 获取项目类别，级别
 */

var getAllProjectCategory = "/common/getAllProjectCategoryList";
var getCategoryNodeByFatherId = "/common/searchCategoryNodeList";
var submitUrl = "/stu/postIntegralLog";
var getConfirmationBasicByC = "/common/getConfirmationBasisByCategoryNodeId";
var types = "GET";
var selectIdNum = 0;
var selectRadioIdName = "";
// event对象
var event = new Object();
var fatherId = "";
var requestData = new Object();
$(function () {
    ajaxs("", "selectCreate", getAllProjectCategory);
    $(":radio")
        .click(
            function () {
                selectRadioIdName = $(this).attr("id");
                if (selectRadioIdName === 'radioselect1') {
                    $("#writeProjectName").html("");
                    if (fatherId == "") {
                        alert("请选择项目类别");
                        selectRadioIdName = "";
                        return;
                    }
                    requestData.categoryNodeId = fatherId;
                    ajaxs(requestData, "createProject",
                        getConfirmationBasicByC)
                } else if (selectRadioIdName === 'radioselect2') {
                    var htmls = "<input type='text' id='projectName' placeholder='请输入项目名称' class='input-xxlarge' /> <span class='help-inline'>*</span>";
                    $("#writeProjectName").html(htmls);
                }
            });
    $("#submit").click(function () {
        submit();
    })
})

function submit(url) {
    var formData = new FormData();
    var data = new Object();
    if (parseInt($("#integral").attr('value')) == 0) {
        alert("请选择项目类别");
        return;
    }
    if (selectRadioIdName == "") {
        alert("请填写项目名称");
        return;
    }
    if (selectRadioIdName == "radioselect1") {
        var val = $("#selectProject option:selected").attr("value");
        if (val === "none") {
            alert("请选择项目名");
            return;
        }
        data.projectName = $("#selectProject").find("option:selected").text();
        // 项目编号
        formData.append("field2", val);
        formData.append("field1", "radioselect1");
    } else if (selectRadioIdName == "radioselect2") {
        var val = $("#projectName").val();
        if (val == "") {
            alert("请填写项目名称");
            return;
        }
        formData.append("projectName", val);
        formData.append("field1", "radioselect2");
    }

    formData.append("file", $("#file-0a")[0].files[0]);
    formData.append("projectCategory", $("#1").find("option:selected").text());
    formData.append("event", getEvent());
    formData.append("integral", parseInt($("#integral").attr('value')));
    formData.append("url", url);
    $.ajax({
        type: 'POST',
        url: submitUrl,
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
            alert("已提交")
        },
        error: function (err) {
            alert(err.msg);
        }
    });
}

/** 生成官方项目* */
function createProject(data) {
    var htmls = "<select class='input-large m-wrap' tabindex='1' id='selectProject'>";
    htmls += "<option value='none' >请选择</option>";
    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + data[i].projectNum + "'>"
            + data[i].projectName + "</option>";
    }
    htmls += "</select>";
    $("#writeProjectName").html(htmls);
}

/** select 生成器* */
function selectCreate(data) {
    selectIdNum++;
    var htmls = "<select class='input-large m-wrap' tabindex='1' id='"
        + selectIdNum + "'>";
    htmls += "<option value='none' >请选择</option>";
    for (var i = 0; i < data.length; i++) {

        htmls += "<option value='";
        if (selectIdNum == 1) {
            htmls += "father" + data[i].id;
        } else {
            htmls += data[i].id;
        }
        htmls += "'isLeaf='" + data[i].isLeaf + "'data='" + data[i].nodeContent
            + "'>";
        if (selectIdNum == 1) {
            htmls += data[i].categoryName;
        } else {
            htmls += data[i].nodeContent;
        }
        htmls += "</option>";
    }
    htmls += "</select>";
    if (selectIdNum == 1) {
        $("#selectAll").html(htmls);
    } else {
        $("#selectAll").append(htmls);
    }
    // 注册监听
    $("#" + selectIdNum).change(function () {
        var selectId = parseInt($(this).attr("id"));
        if (selectIdNum == 1) {
            getNextCategory($(this));
        } else {
            if (selectId < selectIdNum) {
                removeSelect((selectId + 1));
                selectIdNum = selectId;
                $("#integral").attr("value", 0);
            }
            if (selectId == 1) {
                $("#writeProjectName").html("");
                getNextCategory($(this));
            } else {
                getNextCategory2($(this));
            }
        }
    });
}

// 获取下一个节点
function getNextCategory(obj) {
    // var id = $("#projectCategory").find("option:selected").val();
    var id = obj.find("option:selected").val();
    fatherId = id.split("father")[1];
    requestData.level = 1;
    requestData.fatherId = fatherId;
    ajaxs(requestData, "selectCreate", getCategoryNodeByFatherId);
}

function getNextCategory2(obj) {
    var id = obj.find("option:selected").val();
    var isLeaf = $("#" + selectIdNum + " option:selected").attr("isLeaf");
    var nodeContent = $("#" + selectIdNum + " option:selected").attr("data");
    if (isLeaf == 1) {
        $("#integral").attr("value", nodeContent)
    } else {
        requestData = new Object();
        requestData.fatherId = id;
        ajaxs(requestData, "selectCreate", getCategoryNodeByFatherId);
    }
}

// 移除select
function removeSelect(startId) {
    for (var i = startId; i <= selectIdNum; i++) {
        $("#" + i).remove();
    }
}

// 重置
function reset() {
    $("#projectName").val("");
    $("#projectCategory  option[name='0'] ").attr("selected", true);
    $("#getNext").html("");
    $("#integral").attr('placeholder', '');
}

// ajax
function ajaxs(datas, method, urls) {
    if (jQuery.type(datas.fatherId) === 'undefined' && datas != "") {
        return;
    }
    $.ajax({
        type: types, // 提交方式
        url: urls,// 路径
        data: datas,
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (result) {// 返回数据根据结果进行相应的处理
            var str = JSON.stringify(result);
            eval('(' + method + '(' + str + '))');
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

function getEvent() {
    var str = "";
    for (var i = 1; i <= selectIdNum; i++) {
        if (i != 1) {
            str += "/";
        }
        str += $("#" + i + " option:selected").text();
    }
    return str;
}
