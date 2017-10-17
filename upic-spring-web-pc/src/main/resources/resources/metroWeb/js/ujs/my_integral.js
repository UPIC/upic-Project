var getIntegralLogDeclaring= "/stu/getIntegralLogDeclaring";//积分申报进度列表
var getIntegralLogByIntegralLogId = "/common/getIntegralLogByIntegralLogId";//查看申报详情
var types = "GET";

$(function() {

	ajaxs("studentNum=1522110240", "showAll", getIntegralLogDeclaring);
})

function ajaxs(datas, method, urls) {
	$.ajax({
		type: types, // 提交方式
		url: urls, // 路径
		data: datas, //

		beforeSend: function(XMLHttpRequest) {
			// progress.inc();
		},
		success: function(result) { // 返回数据根据结果进行相应的处理
			addHtmls(result, method)
		},
		complete: function(XMLHttpRequest, textStatus) {
			// progress.done(true);
		},
		error: function() {
			// progress.done(true);
		}
	});
}

function addHtmls(result, method) {
	var htmls = "";
	var htmlss = "";
	if(method === "showAll") {
		for(i=0;i<result.content.length;i++){
			var color = "";
			var statusC = "";
			htmls += "<tr>";
			htmls += "<td class='center_td'></td>";
			if(result.content[i].status === "PENDING_AUDIT") {
				color = "danger";
				statusC = "审核中";
			} else if(result.content[i].status === "HAVEPASSED") {
				color = "success";
				statusC = "通过审核";
			} else if(result.content[i].status === "FAILURE_TO_PASS_THE_AUDIT") {
				color = "danger";
				statusC = "审核失败";
			} else if(result.content[i].status === "ALREADY_SIGN_UP") {
				color = "danger";
				statusC = "";
			} else if(result.content[i].status === "SIGNED_IN") {
				color = "success";
				statusC = "";
			} else if(result.content[i].status === "COMPLETED") {
				color = "success";
				statusC = "";
			}
			htmls +="<tr>";
			htmls +="<td><input type='checkbox' class='checkboxes' value='1'/></td>";
			htmls +="<td class='center_td'>" + (i + 1) + "</td>";
			htmls +="<td>"+result.content[i].+"</td>";
			htmls +="<td>"+splitJson(result.content[i].event)+"</td>";
			htmls +="<td>"+result.content[i].projectName+"</td>";
			htmls +="<td>"+result.content[i].integral+"</td>";
		    htmls +="<td>"+result.content[i].creatTime+"</td>";
			htmls +="<td class='center_td'>"+statusC+"</td>";
			htmls +="<td class='center_td'><a href='#mymodal1'";
			htmls +="data-toggle='modal'><div class='message_div' onclick='ajaxs('"+result.content[i].integralLogId.projectNum+"','details','getIntegralLogByIntegralLogId')'>查看详情</div></a></td>";
			htmls +="</tr>";
		}
	}
	else(method === "details"){
		for(var i = 0; i < result.length; i++) {
			htmlss += "<div class='row-form clearfix'>";
			htmlss += "<div class='span3'>编号</div>";
			htmlss += "<div class='span3'>" + (i + 1) + "</div>";
			htmlss += "<div class='span3'>代码</div>";
			htmlss += "<div class='span3'>" + result[i].integralLogId.projectNum + "</div>";
			htmlss += "</div>";
			htmlss += "<div class='row-form clearfix'>";
			htmlss += "<div class='span3'>项目申请日期</div>";
			htmlss += "<div class='span3'>" + result[i].creatTime + "</div>";
			htmlss += "<div class='span3'>状态</div>";
			htmlss += "<div class='span3'>" + statusC + "</div>";
			htmlss += "</div>";
			htmlss += "<div class='row-form clearfix'>";
			htmlss += "<div class='span3'>项目类别</div>";
			htmlss += "<div class='span3'>" + splitJson(result[i].event) + "</div>";
			htmlss += "<div class='span3'>所属学院</div>";
			htmlss += "<div class='span3'>" + result[i].college + "</div>";
			htmlss += "</div>";
			htmlss += "<div class='row-form clearfix'>";
			htmlss += "<div class='span3'>项目名称</div>";
			htmlss += "<div class='span3'>" + result[i].projectName + "</div>";
			htmlss += "<div class='span3'>负责人</div>";
			htmlss += "<div class='span3'>XXX</div>";
			htmlss += "</div>";
			htmlss += "<div class='row-form clearfix'>";
			htmlss += "<div class='span3'>项目内容</div>";
			htmlss += "<div class='span9'>校级学生干部加分校级学生干部加分校级学生干部加分校级学生干部加分校级学生干部加分校级学生干部加分</div>";
			htmlss += "</div>";
			htmlss += "<div class='row-form clearfix'>";
			htmlss += "<div class='span3'>评价标准与形式</div>";
			htmlss += "<div class='span9'>校级学生干部加分校级学生干部加分校级学生干部加分校级学生干部加分校级学生干部加分校级学生干部加分</div>";
			htmlss += "</div>";
		}
	}
	$("#showAll").html(htmls);
	$("#details").html(htmlss);
}
function splitJson(json) {
	var projectCategorys = new Array();
	projectCategorys.concat(json.split(" >> "));
	return projectCategorys[0];
}
