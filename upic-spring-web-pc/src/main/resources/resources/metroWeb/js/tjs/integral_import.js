var dataUrl = "/stu/loadIntegralLogInfo";
var searchKeyWordUrl = "";
var getProjectTypeUrl = "/common/getAllProjectCategory";
var getConfirmationBasicByC = "/common/getConfirmationBasisByCategoryNodeId";
var saveUrl = "";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {};
var uploading = false;

// 项目类别
var projectCategory = "";
var projectName = "";
var allData = "";
var fatherId="";
var selectRadioIdName="";
// 上传文件
function getFile() {
	if (projectCategory == "") {
		alert("请选择项目类别");
		return;
	}

	if (projectName == "") {
		alert("请选择项目名称");
		return;
	}
	if (uploading) {
		alert("文件正在上传中，请稍候");
		return;
	}
	var baseModel = [ "studentNum", "projectNum", "integral", "student",
			"college", "clazz" ];
	var str = JSON.stringify(baseModel);

	var formData = new FormData();
	formData.append("inputFile", $("#inputFile")[0].files[0]);
	formData.append("baseModel", str);
	$.ajax({
		url : dataUrl,
		type : 'POST',
		cache : false,
		data : formData,
		processData : false,
		contentType : false,
		dataType : "json",
		beforeSend : function() {
			uploading = true;
		},
		success : function(data) {
			if (data.length > 1) {
				allData = data;
				addHtmls(data);
			} else {
				alert(data[0]);
			}
			uploading = false;
		},
		error : function(err) {
			// alert(err);
			alert("服务器异常！");
			uploading = false;
		}
	});
}

$(function() {
	pageSize = $("#select-small").children('option:selected').text()
	commonAjax(getProjectTypeUrl, null, "addProjectType", "GET");
	$("#getProjectType").change(function () {
//        var name = $(this).attr("name");
		fatherId=$(this).find("option:selected").val();
		projectCategory=$(this).find("option:selected").text();
    });
		$(":radio")
				.click(
						function() {
							var requestData=new Object();
							selectRadioIdName = $(this).attr("id");
							if (selectRadioIdName === 'radioselect1') {
								$("#inputIt").html("");
								if (fatherId == "") {
									alert("请选择项目类别");
									selectRadioIdName = "";
									return;
								}
								requestData.categoryNodeId = fatherId;
								ajaxs(requestData, "createProject",
										getConfirmationBasicByC)
							} else if (selectRadioIdName === 'radioselect2') {
								var htmls = "<div id='inputIt'><input type='text' id='projectName' placeholder='请输入项目名称'";
								htmls += "class='input-xxlarge' /> <span class='help-inline'>*</span></div>";
								$("#sample_1_length").find('label')
										.after(htmls);

							}
						});
})
/** 生成官方项目* */
function createProject(data) {
	var htmls = "<div class='selectlist'><select class='input-large m-wrap' tabindex='1' id='selectProject'>";
	htmls += "<option value='none' >请选择</option>";
	for (var i = 0; i < data.length; i++) {
		htmls += "<option value='" + data[i].projectNum + "'>"
				+ data[i].projectName + "</option>";
	}
	htmls += "</select></div>";
	// $("#writeProjectName").html(htmls);
	$("#sample_1_length").find('label').after(htmls);
	$("#selectProject").change(function () {
		var value=$(this).find("option:selected").val();
		if(value!="none"){
		projectName=$(this).find("option:selected").text();
		}
  });
}

function addProjectType(res) {
	var data = res.content;
	var htmls = "";
	htmls += "<option value='4' class='yellow'>项目类别筛选...</option>";

	for (var i = 0; i < data.length; i++) {
		htmls += "<option value='" + data[i].id + "'>" + data[i].categoryName
				+ "</option>";
	}
	$("#getProjectType").html(htmls);
	
}

function addHtmls(data) {
	var htmls = "";
	for (var i = 0; i < data.length; i++) {
		htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='"
				+ data[i].projectNum + "'/></td>";
		htmls += "<td class='center_td'>" + data.length + "</td>";
		htmls += "<td name='projectNum' id='" + data[i].projectNum
				+ data[i].studentNum + "a'>" + data[i].projectNum + "</td>";
		htmls += "<td name='projectCategory' id='" + data[i].projectNum
				+ data[i].studentNum + "b'>" + data[i].projectCategory
				+ "</td>";
		htmls += "<td name='projectName' id='" + data[i].projectNum
				+ data[i].studentNum + "c'>" + data[i].projectName + "</td>";
		htmls += "<td name='college' id='" + data[i].projectNum
				+ data[i].studentNum + "d'>" + data[i].college + "</td>";
		htmls += "<td name='clazz' id='" + data[i].projectNum
				+ data[i].studentNum + "e'>" + data[i].clazz + "</td>";
		htmls += "<td name='studentNum' id='" + data[i].projectNum
				+ data[i].studentNum + "f'>" + data[i].studentNum + "</td>";
		htmls += "<td name='student' id='" + data[i].projectNum
				+ data[i].studentNum + "g'>" + data[i].student + "</td>";
		htmls += "<td name='integral' id='" + data[i].projectNum
				+ data[i].studentNum + "h'>" + data[i].integral + "</td>";
		htmls += "<td class='center_td'><div class='message_div'><a href='#mymodal3' data-toggle='modal'>";
		htmls += "<span onclick=bianji('" + i + "')>编辑</span>";
		htmls += "</a></div><td></tr>";
	}

	$("#data").html(htmls);
}

function bianji(index) {
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
	htmlss += "<div class='span6' id='creatTime'>"
			+ getDate(new Date(), 'yyyy-mm-dd') + "</div>";
	htmlss += "</div>";
	htmlss += "<div class='row-form clearfix'>";
	htmlss += "<div class='span3'>项目类别</div>";
	htmlss += "<div class='span3' id='projectCategory'><input type='text' name='' id='projectCategory' value='"
			+ allData[index].projectCategory + "'></div>";
	htmlss += "<div class='span3'>项目名称</div>";
	htmlss += "<div class='span3' id='projectName'><input type='text' name='' id='projectName' value='"
			+ allData[index].projectName + "'></div>";
	htmlss += "</div>";
	htmlss += "<div class='row-form clearfix'>";
	htmlss += "<div class='span3'>所属学院</div>";
	htmlss += "<div class='span3'>";
	htmlss += "<input type='text' name='' id='college' value='"
			+ allData[index].college + "'>";
	htmlss += "</div>";
	htmlss += "<div class='span3'>班级</div>";
	htmlss += "<div class='span3'><input type='text' name='' id='clazz' value='"
			+ allData[index].clazz + "'></div>";
	htmlss += "</div>";
	htmlss += "<div class='row-form clearfix'>";
	htmlss += "<div class='span3'>积分</div>";
	htmlss += "<div class='span3'><input type='text' name='' id='integral' value='"
			+ allData[index].integral + "'></div>";
	htmlss += "</div>";
	htmlss += "</div>";
	htmlss += "<div class='dr'><span></span></div>";
	htmlss += "</div>";
	htmlss += "</div>";
	htmlss += "<div class='modal-footer'>";
	htmlss += "<button class='btn btn-primary' data-dismiss='modal' aria-hidden='true' onclick=save('"
			+ index + "')>保存</button>";
	htmlss += "<button class='btn' data-dismiss='modal' aria-hidden='true'>关闭</button>";
	htmlss += "</div>";
	$("#mymodal3").html(htmlss);
}

function save(index) {
	// var Data = {};
	allData[index].creatTime = $("#creatTime").text();
	allData[index].projectCategory = $("#projectCategory").val();
	allData[index].projectName = $("projectName").val();
	allData[index].college = $("#college").val();
	allData[index].clazz = $("#clazz").val();
	allData[index].integral = $("#integral").val();
	addHtmls(allData);
}
// ajax
function ajaxs(datas, method, urls) {
//	if (jQuery.type(datas.fatherId) === 'undefined' && datas != "") {
//		return;
//	}
	$.ajax({
		type : "GET", // 提交方式
		url : urls,// 路径
		data : datas,
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(result) {// 返回数据根据结果进行相应的处理
			var str = JSON.stringify(result);
			eval('(' + method + '(' + str + '))');
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
		}
	});
}