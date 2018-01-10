var dataUrl = "/systemManager/getAllUser";
var addUserUrl = "/common/addUser";//添加用户
var searchKeyWordUrl = "/common/userSearchBar";//搜索条
var updateUser = "/common/updateUser"//更新用户
var daoruUrl = "";//导入用户
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {};
var batchAddUser = "/user/batchAddStudent";
var getAllCollegeUrl = "/common/getCollege";
var getProjectClazzUrl = "/common/getClazz";

var uploading = true;
function getFile() {
    if (!uploading) {
        return;
    }
    uploading = false;
    var baseModel = ["userNum", "username", "college", "major",
        "clazz", "type"];
    var str = JSON.stringify(baseModel);

    var formData = new FormData();
    formData.append("inputFile", $("#inputFile")[0].files[0]);
    formData.append("baseModel", str);
    $.ajax({
        url: batchAddUser,
        type: 'POST',
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        dataType: "json",
        beforeSend: function () {
            uploading = false;
        },
        success: function (data) {
            if (data === "SUCCESS") {
                alert("上传成功");
            } else {
                alert("上传失败，请重试！");
            }
            uploading = true;
        },
        error: function (err) {
            // alert(err);
            alert("服务器异常！");
            uploading = true;
        }
    });
}
function addOne() {
    var Data = {};
    Data.college = $("#college1 option:selected").text();
    Data.clazz = $("#clazz1 option:selected").text();
    Data.userNum = $("#userNum1").val();
    Data.username = $("#username1").val();

    Data.status = ""; // 保存状态码
    $.ajax({
        type: "POST",
        url: addUserUrl,
        data: Data,
        success: function (result) {
            if (result === "SUCCESS") {
                alert("已新建用户");
                getData(pageNum, dataUrl);
            } else if (result === "HAVE") {
                alert("用户已存在");
                getData(pageNum, dataUrl);
            }
        }
    });
}

function clearStr() {
    $("#userNum1").val("");
    $("#username1").val("");
}

$(function () {
    pageSize = $("#select-small").children('option:selected').text();
    getData(pageNum, dataUrl);
})


function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        htmls += "<tr>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td id='" + data[i].userNum + "a'>" + data[i].college + "</td>";
        htmls += "<td id='" + data[i].userNum + "b'>" + data[i].clazz + "</td>";
        htmls += "<td id='" + data[i].userNum + "c'>" + data[i].userNum + "</td>";
        htmls += "<td id='" + data[i].userNum + "d'>" + data[i].username + "</td>";
        htmls += "<td class='center_td'><div class='message_div'><a href='#mymodal1' data-toggle='modal'>";
        htmls += "<span onclick=commonAjax('" + dataUrl + "','userNum=" + data[i].userNum + "','bianji','GET')>编辑</span>";
        htmls += "</a><span class='space'>|</span><a>";
        htmls += "<span onclick=sub(" + data[i].userNum + ")>保存</span>";
        htmls += "</a></div></tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function bianji(datas) {
    var data = datas.content;
    var htmlss = "";
    htmlss += "<div class='modal-header'>";
    htmlss += "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
    htmlss += "<h4>详情</h4>";
    htmlss += "</div>";
    htmlss += "<div class='modal-body'>";
    htmlss += "<div class='row-fluid'>";
    htmlss += "<div class='block-fluid'>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>所属学院</div>";
    htmlss += "<div class='span3'><input type='text' name='' id='college' value='" + data[0].college + "' ></div>";
    htmlss += "<div class='span3'>班级</div>";
    htmlss += "<div class='span3'><input type='text' name='' id='clazz' value='" + data[0].clazz + "'></div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>学号</div>";
    htmlss += "<div class='span3'><input type='text' name='' id='userNum' value='" + data[0].userNum + "'></div>";
    htmlss += "<div class='span3'>姓名</div>";
    htmlss += "<div class='span3'><input type='text' name='' id='userName' value='" + data[0].username + "'></div>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='dr'><span></span></div>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='modal-footer'>";
    htmlss += "<button class='btn btn-primary' data-dismiss='modal' aria-hidden='true' onclick=save()>保存</button>";
    htmlss += "<button class='btn btn-default' data-dismiss='modal' aria-hidden='true'>关闭</button>";
    htmlss += "</div>";

    $("#mymodal1").html(htmlss);
}

function save() {
    var Data = {};
    Data.college = $("#college").val();
    Data.clazz = $("#clazz").val();
    Data.userNum = $("#userNum").val();
    Data.username = $("#userName").val();

    $.ajax({
        type: "POST",
        url: updateUser,
        data: Data,
        success: function (result) {
            alert("已保存");
            getData(pageNum, dataUrl);
        }
    });
}

function sub(id) {
    var Data = {};
    Data.college = $("#" + id + "a").text();
    Data.clazz = $("#" + id + "b").text();
    Data.userNum = $("#" + id + "c").text();
    Data.username = $("#" + id + "d").text();

    Data.status = "";//提交的状态码
    $.ajax({
        type: "POST",
        url: saveUrl,
        data: Data,
        success: function () {
            alert("已提交")
        }
    });
}

function getAllCollege() {
    registSelect1("college1");
    $.ajax({
        type: "GET",
        data: {},
        url: getAllCollegeUrl,
        success: function (result) {
            var datas = result.content;
            var htmls = "";
            htmls += "<option value='" + (i + 4) + "'>请选择</option>";
            for (var i = 0; i < datas.length; i++) {
                htmls += "<option value='" + (i + 4) + "'>" + datas[i].college + "</option>";
            }
            $("#college1").html(htmls);
        }
    });
}

function addProjectClazz(res) {
    var data = res.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].clazz + "</option>";
    }
    $("#clazz1").html(htmls);
}

// 下拉框注册监听
function registSelect1(id) {
    $("#" + id).change(function () {
        var name = $(this).attr("name");
        var value = $(this).children('option:selected').text();
        commonAjax(getProjectClazzUrl, "college=" + value, "addProjectClazz", "GET");
    });
}