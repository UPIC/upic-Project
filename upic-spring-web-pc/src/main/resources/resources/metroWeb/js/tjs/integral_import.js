var dataUrl = "";
var searchKeyWordUrl = "";
var getProjectTypeUrl = "";
var getProjectStatusUrl = "";
var saveUrl = "";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {};

//上传文件
function getFile() {
    //ajax方法上传文件到后台
    var files = $('input[name="inputFile"]').prop('files');//获取到文件列表
    $.ajax({
        type: "POST",
        url: daoruUrl,
        data: files,
        success: function (result) {
            alert("已导入")
        }
    });
}


$(function () {
    pageSize = $("#select-small").children('option:selected').text()
    commonAjax(getProjectTypeUrl, null, "addProjectType", "GET");
    commonAjax(getProjectStatusUrl, null, "addProjectStatus", "GET");
    registSelect("getProjectType");
    registSelect("getProjectStatus");
    getData(pageNum, dataUrl);
})

function addProjectType(res) {
    var data = res.content;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>项目类别筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].categoryName + "</option>";
    }
    $("#getProjectType").html(htmls);
}


function addProjectType(res) {
    var data = res.content;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>项目状态筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].status + "</option>";
    }
    $("#getProjectStatus").html(htmls);
}


function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {

        htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].projectNum + "'/></td>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td name='projectNum' id='" + data[i].projectNum + data[i] + userNum + "a'>" + data[i].projectNum + "</td>";
        htmls += "<td name='projectCategory' id='" + data[i].projectNum + data[i] + userNum + "b'>" + data[i].projectCategory + "</td>";
        htmls += "<td name='projectName' id='" + data[i].projectNum + data[i] + userNum + "c'>" + data[i].projectName + "</td>";
        htmls += "<td name='college' id='" + data[i].projectNum + data[i] + userNum + "d'>" + data[i].college + "</td>";
        htmls += "<td name='clazz' id='" + data[i].projectNum + data[i] + userNum + "e'>" + data[i].clazz + "</td>";
        htmls += "<td name='userNum' id='" + data[i].projectNum + data[i] + userNum + "f'>" + data[i].userNum + "</td>";
        htmls += "<td name='username' id='" + data[i].projectNum + data[i] + userNum + "g'>" + data[i].username + "</td>";
        htmls += "<td name='integral' id='" + data[i].projectNum + data[i] + userNum + "h'>" + data[i].integral + "</td>";
        htmls += "<td class='center_td'><div class='message_div'><a href='#mymodal3' data-toggle='modal'>";
        htmls += "<span onclick=commonAjax('" + dataUrl + "','projectNum=" + data[i].projectNum + "','bianji','GET')>编辑</span>";
        htmls += "</a><span class='space'>|</span><a>";
        htmls += "<span onclick=sub(" + data[i].projectNum + data[i] + userNum + ")>提交</span>";
        htmls += "</a></div><td></tr>";
    }

    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function bianji(data) {
    var htmlss = "";
    htmlss += "<div class='modal-header'>";
    htmlss += "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
    htmlss += "<h4 id='mymodallabel1'>详情</h4>";
    htmlss += "</div>";
    htmlss += "<div class='modal-body'>";
    htmlss += "<div class='row-fluid'>";
    htmlss += "<div class='block-fluid'>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>提交时间</div>";
    htmlss += "<div class='span6' id='creatTime'>" + getDate(data.creatTime, 'yyyy-mm-dd') + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目类别</div>";
    htmlss += "<div class='span3' id='projectCategory'><input type='text' name='' id='projectCategory' value='" + data.projectCategory + "'></div>";
    htmlss += "<div class='span3'>项目名称</div>";
    htmlss += "<div class='span3' id='projectName'><input type='text' name='' id='projectName' value='" + data.projectName + "'></div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>所属学院</div>";
    htmlss += "<div class='span3'>";
    htmlss += "<input type='text' name='' id='college' value='" + data.college + "'>";
    htmlss += "</div>";
    htmlss += "<div class='span3'>班级</div>";
    htmlss += "<div class='span3'><input type='text' name='' id='clazz' value='" + data.clazz + "'></div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>积分</div>";
    htmlss += "<div class='span3'><input type='text' name='' id='integral' value='" + data.integral + "'></div>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='dr'><span></span></div>";
    htmlss += "</div>";
    htmlss += "</div>";
    htmlss += "<div class='modal-footer'>";
    htmlss += "<button class='btn btn-primary' data-dismiss='modal' aria-hidden='true' onclick=save()>保存</button>";
    htmlss += "<button class='btn' data-dismiss='modal' aria-hidden='true'>关闭</button>";
    htmlss += "</div>";
    $("#mymodal3").html(htmlss);
}

function save() {
    var Data = {};
    Data.creatTime = $("#creatTime").text();
    Data.projectCategory = $("#projectCategory").val();
    Data.projectName = $("projectName").val();
    Data.college = $("#college").val();
    Data.clazz = $("#clazz").val();
    Data.integral = $("#integral").val();
    Data.status = "";
    $.ajax({
        type: "GET",
        url: saveUrl,
        data: Data,
        success: function (result) {
            alert("已保存")
        }
    });
}

function sub(id) {
    var Data = {};
    Data.projectNum = $("#" + id + "a").text();
    Data.projectCategory = $("#" + id + "b").text();
    Data.projectName = $("#" + id + "c").text();
    Data.college = $("#" + id + "d").text();
    Data.clazz = $("#" + id + "e").text();
    Data.userNum = $("#" + id + "f").text();
    Data.username = $("#" + id + "g").text();
    Data.integral = $("#" + id + "h").text();
    Data.status = "";
    $.ajax({
        type: "GET",
        url: saveUrl,
        data: Data,
        success: function (result) {
            alert("已提交")
        }
    });

}

function subAll(){
    var DataList = new Array();
    var Data={};
    //获取选中框的projectNum放入list
    $("input[type=checkbox]:checked").each(function(){
        Data.projectNum=($(this).find(".projectNum").val());
         Data.projectCategory=($(this).find(".projectCategory").val());
          Data.projectName=($(this).find(".projectName").val());
           Data.college=($(this).find(".college").val());
            Data.clazz=($(this).find(".clazz").val());
             Data.userNum=($(this).find(".userNum").val());
              Data.username=($(this).find(".username").val());
               Data.integral=($(this).find(".integral").val());
                Data.status="";
                DataList.push(Data);
    });
    $.ajax({
        type: "GET",
        url: saveUrl,
        data: DataList,
        success: function (result) {
            alert("已提交")
        }
    });



}