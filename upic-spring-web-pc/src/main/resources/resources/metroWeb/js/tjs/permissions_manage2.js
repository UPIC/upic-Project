var data2Url = "";
var changeRoleInfoUrl = "";
var deleteRoleUrl = "";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {};

$(function () {
    pageSize = $("#select-small").children('option:selected').text();
    getData(pageNum, data2Url);
})

function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].userNum + "'/></td>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td>" + data[i].username + "</td>";
        htmls += "<td>" + data[i].usertype + "</td>";
        htmls += "<td>" + data[i].content + "</td>";
        htmls += "<td>" + getDate(data[i].createTime) + "</td>";
        htmls += "<td>";
        htmls += "<div class='message_div'><a href='#mymodal10' data-toggle='modal'><span onclick=>【分配权限】</span></a>";
        htmls += "<span class='space'>|</span>";
        htmls += "<a href='#mymodal7' data-toggle='modal'><span onclick=change(" + data[i].userNum + "," + data[i].username + "," + data[i].type + ")>【修改】</span></a>";
        htmls += "<span class='space'>|</span>";
        htmls += "<a href='#mymodal8' data-toggle='modal'><span onclick=deleteA(" + data[i].userNum + "," + data[i].username + ")>【删除】</span></a>";
        htmls += "</div></td>";
        htmls += "</tr>";
    }
    $("#data").html(htmls);
    page(datas, data2Url, datas.size, datas.number);
}

function change(num, name, type) {
    var htmls = "";
    htmls += "<div class='modal-header'>";
    htmls += "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
    htmls += "<h4>修改角色</h4>";
    htmls += "</div>";
    htmls += "<div class='modal-body'>";
    htmls += "<form action='#' class='form-horizontal'>";
    htmls += "<div class='control-group'>";
    htmls += "<label class='control-label'>角色名称：</label>";
    htmls += "<div class='controls'>";
    htmls += "<input type='text' id='" + num + "name' class='input-medium' value='" + name + "'/>";
    htmls += "</div>";
    htmls += "</div>";
    htmls += "<div class='control-group'>";
    htmls += "<label class='control-label'>角色类型：</label>";
    htmls += "<div class='controls'>";
    htmls += "<input type='text' id='" + num + "type' class='input-medium' value='" + type + "' />";
    htmls += "</div>";
    htmls += "</div>";
    htmls += "<div class='control-group'>";
    htmls += "<label class='control-label'>角色描述：</label>";
    htmls += "<div class='controls'>";
    htmls += "<textarea id='" + num + "content'></textarea>";
    htmls += "</div>";
    htmls += "</div>";
    htmls += "</form>";
    htmls += "</div>";
    htmls += "<div class='modal-footer'>";
    htmls += "<button class='btn btn-primary' data-dismiss='modal' onclick=changeRoleInfo(" + num + ")>确定</button>";
    htmls += "<button class='btn' data-dismiss='modal' aria-hidden='true'>取消</button>";
    htmls += "</div>";
    $("#changeRole").html(htmls);
}

function changeRoleInfo(num) {
    var Data = {};
    Data.userNum = num;
    Data.userType = $("#" + num + "type").val();
    Data.content = $("#" + num + "content").text();

    $.ajax({
        type: "GET",
        url: changeRoleInfoUrl,
        data: Data,
        success: function (result) {
            alert("修改成功")
        }
    });
}

function deleteA(num, name) {
    var htmls = "";
    htmls += "<div class='modal-header'>";
    htmls += "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
    htmls += "<h4>确认提示</h4>";
    htmls += "</div>";
    htmls += "<div class='modal-body'>";
    htmls += "<form action='#' class='form-horizontal'>";
    htmls += "<p>删除角色<span>【" + name + "】</span>?</p>";
    htmls += "</form>";
    htmls += "</div>";
    htmls += "<div class='modal-footer'>";
    htmls += "<button class='btn btn-primary' data-dismiss='modal' onclick=deleteRole(" + num + ")>确定</button>";
    htmls += "<button class='btn' data-dismiss='modal' aria-hidden='true'>取消</button>";
    htmls += "</div>";
    $("#deleteRole").html(htmls);
}

function deleteRole(num) {
    var Data = {};
    Data.userNum = num;

    $.ajax({
        type: "GET",
        url: deleteRoleUrl,
        data: Data,
        success: function (result) {
            alert("删除成功")
        }
    });
}