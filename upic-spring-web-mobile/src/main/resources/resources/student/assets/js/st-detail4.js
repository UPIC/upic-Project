/*
 * @Author: Marte
 * @Date:   2017-10-07 12:28:15
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-11-10 15:20:24
 */

/*
 获取前一页面发送的项目ID
 */
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
    var htmlss = "";
    htmls+="<li class='clearfix'>";
    htmls+="<div class='list-name'>";
    htmls+="项目名称：";
    htmls+="</div>";
    htmls+="<div class='list-det det-cen'>";
    htmls+=data.projectName;
    htmls+="</div>";
    htmls+="</li>";
    htmls+="<li class='clearfix'>";
    htmls+="<div class='list-name'>";
    htmls+="项目类别：";
    htmls+="</div>";
    htmls+=data.projectCategory;
    htmls+="</li>";
    htmls+="<li class='li-other clearfix'>";
    htmls+="<div class='list-line'>";
    htmls+="项目详情：";
    htmls+="</div>";
    htmls+="<div class='li-text'>";
    htmls+=data.content;
    htmls+="</div>";
    htmls+="</li>";
    htmls+="<li class='li-other clearfix'>";
    htmls+="<div class='list-line'>";
    htmls+="佐证照片：";
    htmls+="</div>";
    htmls+="<img src='assets/i/b-3.jpg' alt=''>";
    htmls+="</li>";
    htmls+="<li class='clearfix'>";
    htmls+="<div class='list-name'>";
    htmls+="失败原因：";
    htmls+="</div>";
    htmls+="<div class='list-det'>";
    htmls+=data.field1;
    htmls+="</div>";
    htmls+="</li>";

    htmlss+="<a href='st-declare1.html?projectNum="+data.projectNum+"' id='submit'>";
    htmlss+="<div class='container1 nav-bot'>修改</div>";
    htmlss+="</a>";

    $("#content").html(htmls);
    $("#xiugai").html(htmlss;)

}