/*
 * @Author: Marte
 * @Date:   2017-10-07 12:28:15
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-11-10 15:20:24
 */

var getIntegralLogInfoByMySelf = "/common/getIntegralLogInfoByMySelf";

/*
 * 获取前一页面发送的项目ID
 */
var projectNum = getQueryString("projectNum");
$(function() {

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
		url : getIntegralLogInfoByMySelf,
		type : 'GET', // GET
		data : {
			projectNum : projectNum
		},
		dataType : 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
		beforeSend : function(xhr) {
		},
		success : function(data) {
			addHtmls(data);
		},
		error : function(xhr, textStatus) {
		},
		complete : function() {
		}
	})
}
var obj = new Object();
function sunUpdate() {
	obj.content = $("#contents").val();
	obj.projectNum = projectNum;

	if (obj.contet === "") {
		alert("请填写项目详情");
		return;
	}
	if (obj.projectNum === "") {
		alert("数据异常，请重新刷新页面");
	}
	$.ajax({
		url : "/stu/updateIntegralLogNew",
		type : 'POST', // GET
		data : obj,
		dataType : 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
		beforeSend : function(xhr) {
		},
		success : function(data) {
			if (data != null) {
				alert("请求已提交！");
				window.location.href = "st-check.html?projectNum="
						+ data.integralLogId.projectNum;
			}
		},
		error : function(xhr, textStatus) {
		},
		complete : function() {
		}
	})
}

function addHtmls(data) {
	var htmls = "";
	var htmlss = "";
	htmls += "<li class='clearfix'>";
	htmls += "<div class='list-name'>";
	htmls += "项目名称：";
	htmls += "</div>";
	htmls += "<div class='list-det det-cen'>";
	htmls += data.projectName;
	htmls += "</div>";
	htmls += "</li>";
	htmls += "<li class='clearfix'>";
	htmls += "<div class='list-name'>";
	htmls += "项目积分：";
	htmls += "</div>";
	htmls += "<div class='list-det det-cen'>";
	htmls += data.integral;
	htmls += "</div>";
	htmls += "</li>";
	htmls += "<li class='clearfix'>";
	htmls += "<div class='list-name'>";
	htmls += "项目简介：";
	htmls += "</div>";
	htmls += "<div class='list-det det-cen'>";
	htmls += data.event;
	htmls += "</div>";
	htmls += "</li>";
	htmls += "<li class='clearfix'>";
	htmls += "<div class='list-name'>";
	htmls += "项目类别：";
	htmls += "</div>";
	htmls += data.projectCategory;
	htmls += "</li>";
	htmls += '<li class="clearfix"><div>项目详情:</div> <textarea id="contents" class="form-control" rows="3">'
			+ data.content + '</textarea></li>';
	htmls += "<li class='li-other clearfix'>";
	htmls += "<div class='list-line'>";
	htmls += "佐证照片：";
	htmls += "</div>";
	htmls += "<img src='assets/i/b-3.jpg' alt=''>";
	htmls += "</li>";

	htmlss += "<a href='#' id='submit'>";
	htmlss += "<div class='container1 nav-bot'>确认修改</div>";
	htmlss += "</a>";

	$("#content").html(htmls);
	$("#xiugai").html(htmlss);
	$("#submit").click(function() {
		sunUpdate();
	})
}