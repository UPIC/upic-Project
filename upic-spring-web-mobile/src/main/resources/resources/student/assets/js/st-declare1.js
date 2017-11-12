$(function () {
    var projectNum = getQueryString("projectNum");
    if (projectNum == null) {
        return;
    }
    getProjectTime(projectNum);
})

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

function getProjectTime(projectNum) {
    $.ajax({
        url: '/common/getProjectInfo',
        type: 'GET', // GET
        data: {
            projectNum: projectNum
        },
        dataType: 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
        beforeSend: function (xhr) {
        },
        success: function (data) {
            addHtmls(data);
        },
        error: function (xhr, textStatus) {
        },
        complete: function () {
        }
    })
}

function addHtmls(data) {
    var htmls = "";

    htmls+="<li>";
    htmls+="<div class='list-left'>项目名称：</div>";
    htmls+="<div class='list-right'>";
    htmls+="<input id='projectName' type='text' name='' value='"+data.projectName+"''>";
    htmls+="</div>";
    htmls+="</li>";
    htmls+="<li>";
    htmls+="<div class='list-left'>项目类别：</div>";
    htmls+="<div class='list-right'>";
    htmls+="<input type='text' name='' id='sui' value='"+data.projectCategory+"'>";
    htmls+="<div id='div1' class='div1 qq'>";
    htmls+="<ul class='clearfix' id='one'>";
    htmls+="</ul>";
    htmls+="</div>";
    htmls+="</div>";
    htmls+="<div class='clear'></div>";
    htmls+="</li>";
    htmls+="<li>";
    htmls+="<div class='list-left'>所属学院：</div>";
    htmls+="<div class='list-right agray' id='college'>"+data.college+"</div>";
    htmls+="</li>";
    htmls+="<li class='list-other'>";
    htmls+="<div>项目详情:</div> <textarea id='content' class='form-control' rows='3'>"+data.content+"</textarea>";
    htmls+="</li>";
    htmls+="<li class='list-other'>";
    htmls+="<div class='de-text'>上传佐证:</div> <img src='assets/i/b-2.png' alt=''>";
    htmls+="</li>";

    $("#content").html(htmls);

}

function sub(){
    var Data={};
    Data.projectName=$("#projectName").val();
    Data.projectCategory=$("#sui").val();
    Data.college=$("#college").val();
    Data.content=$("#content").val();
    $.ajax({
        url: subUrl,
        type: 'GET', // GET
        data:Data,
        dataType: 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
           alert("已提交");
        }
    })
}