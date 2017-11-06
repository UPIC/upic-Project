var dataUrl = "/common/getProject";
var searchKeyWordUrl = "/common/projectSearchBar";
var getProjectInfo = "/common/getProjectInfo";
var getProjectTypeUrl = "/common/getAllProjectCategory";
var getProjectStatusUrl = "";
var getProjectCollegeUrl = "/common/getCollege";
var getProjectCollege = "";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {};

$(function () {
    pageSize = $("#select-small").children('option:selected').text()
    getData(pageNum, dataUrl);
    commonAjax(getProjectTypeUrl, null, "addProjectType", "GET");
    // commonAjax(getProjectStatusUrl,null,"addProjectStatus","GET");
    commonAjax(getProjectCollegeUrl,null,"addProjectCollege","GET");
    registSelect("getProjectType")
    registSelect("getProjectStatus")
    registSelect("getProjectCollege")
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

function addProjectCollege(res) {
    var data = res.content;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>学院筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].college + "</option>";
    }
    $("#getProjectCollege").html(htmls);
}
function addProjectStatus(res) {
    var data = res.content;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>项目状态筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].college + "</option>";
    }
    $("#getCollege").html(htmls);
}

function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        var statuss = "";
        if (data[i].implementationProcess === "SAVED") {
            statuss = "已保存"
        }
        if (data[i].implementationProcess === "IN_AUDIT") {
            statuss = "审核中"
        }
        if (data[i].implementationProcess === "AUDITED") {
            statuss = "已审核"
        }
        if (data[i].implementationProcess === "NOT_PASS") {
            statuss = "未通过"
        }
        if (data[i].implementationProcess === "ENROLLMENT") {
            statuss = "报名中"
        }
        if (data[i].implementationProcess === "HAVE_IN_HAND") {
            statuss = "进行中"
        }
        if (data[i].implementationProcess === "COMPLETED") {
            statuss = "已完成"
        }
        if (data[i].implementationProcess === "CHECKED") {
            statuss = "已验收"
        }

        htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].projectNum + "'/></td>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td>" + data[i].projectNum + "</td>";
        htmls += "<td>" + data[i].projectCategory + "</td>";
        htmls += "<td>" + data[i].projectName + "</td>";
        htmls += "<td>" + data[i].declareUnit + "</td>";
        htmls += "<td>" + data[i].integral + "</td>";
        htmls += "<td>" + data[i].maximum + "</td>";
        htmls += "<td>" + data[i].guidanceMan + "</td>";
        // htmls+="<td>"+getDate(data[i].startTime,"yyyy/MM/dd hh:mm")+"</td>";
        htmls += "<td class='center_td'>" + statuss + "</td>";
        htmls += "<td class='center_td'><a href='#mymodal1'";
        htmls += "data-toggle='modal'><div class='message_div' onclick=commonAjax('" + getProjectInfo + "','projectNum=" + data[i].projectNum + "','getProjectInfo','GET')>查看详情</div></a></td></tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function getProjectInfo(data) {
    var htmlss = "";
    var statuss = "";
    if (data[i].implementationProcess === "SAVED") {
        statuss = "已保存"
    }
    ;
    if (data.implementationProcess === "IN_AUDIT") {
        statuss = "审核中"
    }
    ;
    if (data.implementationProcess === "AUDITED") {
        statuss = "已审核"
    }
    ;
    if (data.implementationProcess === "NOT_PASS") {
        statuss = "未通过"
    }
    ;
    if (data.implementationProcess === "ENROLLMENT") {
        statuss = "报名中"
    }
    ;
    if (data.implementationProcess === "HAVE_IN_HAND") {
        statuss = "进行中"
    }
    ;
    if (data.implementationProcess === "COMPLETED") {
        statuss = "已完成"
    }
    ;
    if (data.implementationProcess === "CHECKED") {
        statuss = "已验收"
    }
    ;

    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>编号</div>";
    htmlss += "<div class='span3'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</div>";
    htmlss += "<div class='span3'>代码</div>";
    htmlss += "<div class='span3'>" + data.projectNum + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目申请日期</div>";
    htmlss += "<div class='span3'>" + data.creatTime + "</div>";
    htmlss += "<div class='span3'>状态</div>";
    htmlss += "<div class='span3'>" + statuss + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目类别</div>";
    htmlss += "<div class='span3' id='" + data.projectNum + "'>";
    htmlss += commonAjax(getProjectTypeUrl, '', 'getProjectTypeUrl', 'GET', data.projectNum);
    htmlss += "</div>";
    htmlss += "<div class='span3'>所属学院</div>";
    htmlss += "<div class='span3'><input type='text' placeholder='" + data.college + "'></div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目名称</div>";
    htmlss += "<div class='span3'><input type='text' placeholder='" + data.projectName + "'></div>";
    htmlss += "<div class='span3'>负责人</div>";
    htmlss += "<div class='span3'><input type='text' placeholder='" + data.guidanceMan + "'></div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>工作量</div>";
    htmlss += "<div class='span3'><input type='text' placeholder='" + (parseFloat(data.integral) * parseInt(data.maximum)) + "'></div>";
    htmlss += "<div class='span3'>积分</div>";
    htmlss += "<div class='span3'><input type='text' placeholder='" + data.integral + "'></div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>开始时间</div>";
    htmlss += "<div class='span3'><div class='input-append date' id='datetimepicker1' data-date='2017/09/14' data-date-format='dd-mm-yyyy'>";
    htmlss += "<input class='span2 nowdate' size='16' type='text' value='12-02-2012' style='width:100px !important;'>";
    htmlss += "<span class='add-on'><i class='icon-th'></i></span>";
    htmlss += "</div></div>";
    htmlss += "<div class='span3'>结束时间</div>";
    htmlss += "<div class='span3'><div class='input-append date' id='datetimepicker1' data-date='2017/09/14' data-date-format='dd-mm-yyyy'>";
    htmlss += "<input class='span2 nowdate' size='16' type='text' value='12-02-2012' style='width:100px !important;'>";
    htmlss += "<span class='add-on'><i class='icon-th'></i></span>";
    htmlss += "</div></div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>最大参与人数</div>";
    htmlss += "<div class='span3'><input type='text' placeholder='" + data.maximum + "'></div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>项目内容</div>";
    htmlss += "<div class='span9'>" + data.content + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>评价标准与形式</div>";
    htmlss += "<div class='span9'>" + data.checkAssessmentCriteraAndForm + "</div>";
    htmlss += "</div>";

    $("#getProjectInfo").html(htmlss);
    page(datas, dataUrl, datas.size, datas.number);
}


function getProjectTypeUrl(res, id) {
    var htmls = "";
    var data = res.content;
    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='category " + (i + 1) + "'>" + data.category + "</option>";
    }
    $("#id").html(htmls);

}
/****************导出****************************/
document.getElementById("exportBtn").onclick = function(){
            var table = document.getElementById("sample_1").innerHTML;//获取table模板
            exporExcel("导出Excel表格",table);
        }
        /**
         * @params: FileName:导出Excel的文件名称，excel:需要导出的table
         * 如果没有table列表，只有json数据的话，将json数据拼接成table字符串模板即可
         * **/
         function exporExcel(FileName,excel){
            var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";
            excelFile += '<meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">';
            excelFile += '<meta http-equiv="content-type" content="application/vnd.ms-excel';
            excelFile += '; charset=UTF-8">';
            excelFile += "<head>";
            excelFile += "<!--[if gte mso 9]>";
            excelFile += "<xml>";
            excelFile += "<x:ExcelWorkbook>";
            excelFile += "<x:ExcelWorksheets>";
            excelFile += "<x:ExcelWorksheet>";
            excelFile += "<x:Name>";
            excelFile += "{worksheet}";
            excelFile += "</x:Name>";
            excelFile += "<x:WorksheetOptions>";
            excelFile += "<x:DisplayGridlines/>";
            excelFile += "</x:WorksheetOptions>";
            excelFile += "</x:ExcelWorksheet>";
            excelFile += "</x:ExcelWorksheets>";
            excelFile += "</x:ExcelWorkbook>";
            excelFile += "</xml>";
            excelFile += "<![endif]-->";
            excelFile += "</head>";
            excelFile += "<body>";
            excelFile += excel;
            excelFile += "</body>";
            excelFile += "</html>";


            var uri = 'data:application/vnd.ms-excel;charset=utf-8,' + encodeURIComponent(excelFile);

            var link = document.createElement("a");
            link.href = uri;

            link.style = "visibility:hidden";
                link.download = FileName ;  //格式默认为.xls

                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);
            }