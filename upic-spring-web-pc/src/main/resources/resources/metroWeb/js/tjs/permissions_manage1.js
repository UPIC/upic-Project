var data1Url = "/operator/searchOperator";
var saveOperatorInfoUrl = "/operator/updateTheOperator";
var updateOperatorRoleUrl = "/operator/updateOperatorRole";
var saveUserStatusUrl = "/operator/changeOperatorStatus";
var pageSize1 = 0;
var pageNum1 = 0;
var requestData1 = {};

$(function () {
    pageSize1 = $("#select-small3").children('option:selected').text();
    getData1(pageNum1, data1Url);
    registSelect("type");
    registSelect("status");
    $("#addRoleRank").change(function () {
        var rank = $(this).children('option:selected').attr("roleRank");
        $.ajax({
            type: "GET",
            url: "/common/getAllColleges",
            data: {
                rank: rank
            },
            beforeSend: function (XMLHttpRequest) {
            },
            success: function (result) {
                if (result != null && result != "") {
                    var html = "<option value='category 0'>请选择</option>";
                    for (var i = 0; i < result.content.length; i++) {
                        html += "<option value='category " + (i + 1) + "' aliasName='" + result.content[i].otherName + "'>" + result.content[i].college + "</option>";
                    }
                    $("#addRoleCollege").html(html);
                }
            },
            complete: function (XMLHttpRequest, textStatus) {
            },
            error: function () {
            }
        });
    })
})

//ajax获取页面内容
function getData1(pageNum1, data1Url, pageSize1) {
    requestData1.size = parseInt($("#select-small3").children('option:selected')
        .text());
    requestData1.page = pageNum1;
    $.ajax({
        type: "GET",
        url: data1Url,
        data: requestData1,
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (result) {
            if (result != "" && result != null) {
                addHtmls1(result, pageNum1, requestData1);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}


function getCaoZuoYuan() {
    pageSize1 = $("#select-small3").children('option:selected').text();
    getData1(pageNum1, data1Url);
    registSelect("userType");
    registSelect("userStatus");
}

function addHtmls1(datas) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].jobNum + "'/></td>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum1) * parseInt(pageSize1) + i + 1) + "</td>";
        htmls += "<td>" + data[i].jobNum + "</td>";
        htmls += "<td>" + data[i].username + "</td>";
        if (data[i].status === "FROZE") {
            htmls += "<td>冻结</td>";
        } else {
            htmls += "<td>正常</td>";
        }
        if (data[i].type === 0) {
            htmls += "<td>学生</td>";
        } else {
            htmls += "<td>教师</td>";
        }
        // htmls += "<td>" + data[i].type + "</td>";
        htmls += "<td>";
        htmls += "<div class='message_di'>";
        htmls += "<a href='#mymodal2' data-toggle='modal'><span onclick=commonAjax('" + data1Url + "','jobNum=" + data[i].jobNum + "','chakan','GET')>【查看】</span></a>";
        htmls += "<span class='space'>|</span>";
        htmls += "<a href='#mymodal3' data-toggle='modal'><span onclick=commonAjax('" + data1Url + "','jobNum=" + data[i].jobNum + "','xiugai','GET','" + data[i].rank + "')>【修改】</span></a>";
        htmls += "<span class='space'>|</span>";
        // htmls += "<a href='#mymodal' data-toggle='modal'><span onclick=shezhimima(" + data[i].jobNum + "," + data[i].username + ")>【重置密码】</span></a>";
        // htmls += "<span class='space'>|</span>";
        if (data[i].status === "NORMAL_CONDITION") {
            htmls += "<a href='#mymodal5' data-toggle='modal'><sapn onclick=dongjie('" + data[i].jobNum + "','" + data[i].username + "','1')>【冻结】</sapn></a>";
        } else {
            htmls += "<a href='#mymodal5' data-toggle='modal'><sapn onclick=dongjie('" + data[i].jobNum + "','" + data[i].username + "','2')>【恢复】</sapn></a>";
        }
        htmls += "</div></td>";
        htmls += "</tr>";
    }
    $("#data1").html(htmls);
    page1(datas, data1Url, datas.size, datas.number);
}

function chakan(datas) {
    var data = datas.content[0];
    var htmlss = "";
    var statuss = "";
    if (data.status === "NORMAL_CONDITION") {
        statuss = "正常";
    } else {
        statuss = "已冻结";
    }
    htmlss += "<div class='control-group'>";
    htmlss += "<label class='control-label'>操作员姓名：</label>";
    htmlss += "<div class='controls'>";
    htmlss += "<input type='text' class='input-medium' placeholder='" + data.username + "' disabled />";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='control-group'>";
    htmlss += "<label class='control-label'>操作员工号</label>";
    htmlss += "<div class='controls'>";
    htmlss += "<input type='text' class='input-medium' placeholder='" + data.jobNum + "' disabled/>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='control-group'>";
    htmlss += "<label class='control-label'>创建时间</label>";
    htmlss += "<div class='controls'>";
    htmlss += "<span>" + getDate(data.creatTime, "yyyy-MM-dd") + "</span>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='control-group'>";
    htmlss += "<label class='control-label'>状态</label>";
    htmlss += "<div class='controls'>";
    htmlss += "<span id='" + data.jobNum + "status'>" + statuss + "</span>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='control-group'>";
    htmlss += "<label class='control-label'>角色</label>";
    htmlss += "<div class='controls'>";
    htmlss += "<span id='theRole'></span>";
    htmlss += "</div>";
    htmlss += "</div>";

    $("#getUserInfo").html(htmlss);

    getTheRole(data.jobNum, "theRole");
}

function xiugai(datas, rank) {
    var data = datas.content[0];
    var htmlss = "";
    var statuss = "";
    if (data.status === "NORMAL_CONDITION") {
        statuss = "正常";
    } else {
        statuss = "已冻结";
    }
    htmlss += "<div class='modal-header'>";
    htmlss += "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
    htmlss += "<h4>修改信息</h4>";
    htmlss += "</div>";
    htmlss += "<div class='modal-body'>";
    htmlss += "<form action='#' class='form-horizontal'>";
    htmlss += "<div class='control-group'>";
    htmlss += "<label class='control-label'>操作员姓名：</label>";
    htmlss += "<div class='controls'>";
    htmlss += "<span id='" + data.jobNum + "name'>" + data.username + "</span>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='control-group'>";
    htmlss += "<label class='control-label'>操作员工号</label>";
    htmlss += "<div class='controls'>";
    htmlss += "<span id='" + data.jobNum + "num'>" + data.jobNum + "</span>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='control-group'>";
    htmlss += "<label class='control-label'>创建时间</label>";
    htmlss += "<div class='controls'>";
    htmlss += "<span id='" + data.jobNum + "time'>" + getDate(data.creatTime, "yyyy-MM-dd") + "</span>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='control-group'>";
    htmlss += "<label class='control-label'>状态</label>";
    htmlss += "<div class='controls'>";
    htmlss += "<select id='theOperatorStatus' style='width: 100%;' class='color-wh'>";
    htmlss += "<option value='4' class='yellow'>" + statuss + "</option>";
    htmlss += "<option value='4' myStatus='NORMAL_CONDITION' class='yellow'>正常</option>";
    htmlss += "<option value='4' myStatus='FROZE' class='yellow'>冻结</option>";
    htmlss += "</select>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='control-group'>";
    htmlss += "<label class='control-label'>角色</label>";
    htmlss += "<div class='controls'>";
    htmlss += "<span id='" + data.jobNum + "type'></span>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<fieldset class='fild1'>";
    htmlss += "<legend style='font-size: 12px;'>选择角色</legend>";
    htmlss += "<div id='getAllRoles' class='controls controls_change'>";
    htmlss += "</div>";
    htmlss += "</fieldset>";
    htmlss += "</form>";
    htmlss += "</div>";
    htmlss += "<div class='modal-footer'>";
    htmlss += "<button class='btn btn-primary' data-dismiss='modal' onclick=saveUserInfo(" + data.jobNum + ")>保存</button>";
    htmlss += "<button class='btn' data-dismiss='modal' aria-hidden='true'>关闭</button>";
    htmlss += "</div>";
    $("#mymodal3").html(htmlss);

    getTheRole(data.jobNum, data.jobNum + "type", "need", rank);
}

function saveUserInfo(id) {
    var Data = {};
    Data.status = $("#theOperatorStatus  option:selected").attr("myStatus");
    Data.jobNum = $("#" + id + "num").html();

    var operatorInfo = "{ ";
    for (var item in Data) {
        operatorInfo += "'" + item + "':'" + Data[item] + "',";
    }
    operatorInfo += " }";

    $.ajax({
        type: "POST",
        url: saveOperatorInfoUrl,
        data: {
            operatorInfo: operatorInfo
        },
        success: function (result) {
            alert("已保存");
            updateOperatorRole(Data.jobNum);
        }
    });
}

function updateOperatorRole(jobNum) {
    var roleIdLists = new Array();
    $("input[type=checkbox]:checked").each(function () {
        roleIdLists.push($(this).attr("id"));
    });

    var roleIdList = new Array();

    for (var i = 0; i < roleIdLists.length; i++) {
        roleIdList.push(splitRoleId(roleIdLists[i]));
    }

    var str = JSON.stringify(roleIdList);

    $.ajax({
        type: "GET",
        url: updateOperatorRoleUrl,
        data: {
            roleIdList: str,
            jobNum: jobNum
        },
        success: function (result) {
            getData1(pageNum1, data1Url);
        }
    });
}

function dongjie(jobNum, username, num) {
    var htmls = "";
    htmls += "<div class='modal-header'>";
    htmls += "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
    htmls += "<h4>确认提示</h4>";
    htmls += "</div>";
    htmls += "<div class='modal-body'>";
    htmls += "<form action='#'' class='form-horizontal'>";
    if (num == 1) {
        htmls += "<p>冻结<span>" + username + "</span>?</p>";
    } else {
        htmls += "<p>恢复<span>" + username + "</span>?</p>";
    }
    htmls += "</form>";
    htmls += "</div>";
    htmls += "<div class='modal-footer'>";
    htmls += "<button class='btn btn-primary' data-dismiss='modal' onclick=changeUserStatus('" + jobNum + "')>确定</button>";
    htmls += "<button class='btn' data-dismiss='modal' aria-hidden='true'>取消</button>";
    htmls += "</div>";
    $("#mymodal5").html(htmls);
}

function changeUserStatus(usernum) {
    $.ajax({
        type: "GET",
        url: saveUserStatusUrl,
        data: {
            jobNum: usernum
        },
        success: function (result) {
            alert("操作成功");
            getData1(pageNum1, data1Url);
        }
    });
}

function getTheRole(jobNum, method, needToCompare, rank) {
    $.ajax({
        type: "GET",
        url: "/operator/getRoleByJobNum",
        data: {
            jobNum: jobNum
        },
        success: function (result) {
            if (needToCompare === "need") {
                getAllRoles(result, rank);
            }
            var addHtml = "";
            for (var i = 0; i < result.length; i++) {
                if (i == 0) {
                    addHtml += result[i].roleName;
                } else {
                    addHtml += "、" + result[i].roleName;
                }
            }
            $("#" + method).html(addHtml);
        }
    });
}

function getAllRoles(hadRoles, rank) {
    $.ajax({
        type: "GET",
        url: "/operator/getMyRoles",
        data: {
            rank: rank
        },
        success: function (result) {
            var addHtml = "";
            for (var i = 0; i < result.length; i++) {
                addHtml += "<label class='checkbox'>";
                addHtml += "<input id='checkbox" + result[i].id + "' type='checkbox' value=''/>" + result[i].roleName;
                addHtml += "</label>";
            }

            $("#getAllRoles").html(addHtml);

            for (var i = 0; i < result.length; i++) {
                for (var j = 0; j < hadRoles.length; j++) {
                    if (result[i].id == hadRoles[j].id) {
                        $("#checkbox" + result[i].id).attr("checked", true);
                        break;
                    }
                }
            }
        }
    });
}

function splitRoleId(roleId) {
    var roleIds = new Array();
    roleIds = roleId.split("checkbox");
    return roleIds[1];
}

function addOperators() {
    var data = {
        username: $("#addOperatorName").val(),
        jobNum: $("#addOperatorNum").val(),
        password: $("#addOperatorPassword").val(),
        status: $("#addOperatorStatus  option:selected").attr("operatorStatus"),
        rank: parseInt($("#addOperatorRank  option:selected").attr("operatorRank"))
    }
    $.ajax({
        type: "POST",
        url: "/operator/addOperator",
        data: data,
        success: function (result) {
            if (result === "SUCCESS") {
                alert("添加成功！");
                getData1(pageNum1, data1Url);
            } else if (result === "ERROR") {
                alert("请先添加用户！");
                getData1(pageNum1, data1Url);
            }
        }
    });
}