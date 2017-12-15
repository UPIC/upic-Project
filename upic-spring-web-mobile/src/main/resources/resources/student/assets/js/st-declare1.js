
/*radio*/
var myRadioValue = "";
var myRadio = "radioselect1";

/*项目描述*/
var myFinalProjectCate = "";

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}
//获取url中projectNum的值
var projectNum = getQueryString("projectNum");

$(function () {
//	//加载数据
    addOldMessage();
    $("#sui").change(function () {
        getId($("#sui option:selected").attr("id"), $("#sui option:selected").text());
    });
//  //获取项目类别
    getAllProjectCategory();
});


//获取项目类别
function getAllProjectCategory(){
	 $.ajax({
	        type: "GET", // 提交方式
	        url: "/common/getAllProjectCategory",// 路径
	        success: function (result) {// 返回数据根据结果进行相应的处理
	            addHtmls(result);
	        }
	    });
}
function addOldMessage() {
    $.ajax({
        type: "GET", // 提交方式
       // async: false,
        data: {
            projectNum: projectNum
        },
        url: "/common/getIntegralLogInfoByMySelf",// 路径
        success: function (result) {// 返回数据根据结果进行相应的处理
            $("#content").val(result.content);
            if (result.field1 === "radioselect2") {
               // gel("radioselect2").checked = true;
                $("#projectName").val(result.projectName);
            } else {
                myRadioValue = result.projectName;
            }
            myFinalProjectCate = myProjectCateSplit(result.event);
            $("#college").html(result.college);
            $("#integral").val(result.integral);
        }
    });
}

//下拉框加载
function addHtmls(result) {
    var htmls = "<option>" + myFinalProjectCate + "</option>";
    for (var i = 0; i < result.content.length; i++) {
        var level = 1;
        htmls += "<option id='" + result.content[i].id + "/" + level + "'>"
            + result.content[i].categoryName
            + "</option>";
    }
    $("#sui").html(htmls);
}

var hadCategoryName = "";

function getId(fatherId, categroyName) {
    var fatherIdLevelIsLeaf = new Array();
    fatherIdLevelIsLeaf = splitMyFatherId(fatherId);
    if (fatherIdLevelIsLeaf.length == 2) {
        fatherIdLevelIsLeaf.push(-1);
        $.ajax({
            type: "GET", // 提交方式
            data: {
                categoryNodeId: fatherIdLevelIsLeaf[0]
            },
            url: "/common/getConfirmationBasisByCategoryNodeId",// 路径
            success: function (result) {// 返回数据根据结果进行相应的处理
                var needAddHtml = "<option>" + myRadioValue + "</option>";
                for (var i = 0; i < result.length; i++) {
                    needAddHtml += "<option value='" + result[i].projectNum + "'>"
                        + result[i].projectName + "</option>";
                }
                $("#form1").html(needAddHtml);
            }
        });
    }
    if (fatherIdLevelIsLeaf[2] == 1) {
        var myCate = "<option>" + hadCategoryName + "</option>";
        $("#sui").html(myCate);
        $("#integral").val(categroyName);
    } else {
        $.ajax({
            type: "GET", // 提交方式
            url: "/common/getCategoryNode",// 路径
            data: "fatherId=" + fatherIdLevelIsLeaf[0] + "&level=" + fatherIdLevelIsLeaf[1],
            success: function (result) {// 返回数据根据结果进行相应的处理
                if (fatherIdLevelIsLeaf[2] === -1) {
                    hadCategoryName += categroyName;
                } else {
                    hadCategoryName += "/" + categroyName;
                }
                var htmlss = "<option>" + hadCategoryName + "</option>";
                for (var i = 0; i < result.content.length; i++) {
                    var level = parseInt(result.content[i].level) + 1;
                    htmlss += "<option id='" + result.content[i].id + "/" + level + "/" + result.content[i].isLeaf + "' level='" + result.content[i].level + "'>"
                        + result.content[i].nodeContent + "</option>";
                }
                $("#sui").html(htmlss);
            }
        });
    }
}

function splitMyFatherId(fatherId) {
    return fatherId.split("/");
}

function sub() {
    var Data = {};
    if (myRadio === "radioselect1") {
    	Data.projectName = $("#form1 option:selected").text();
    	Data.field1 = "radioselect1";
    	Data.field2 = $("#form1 option:selected").val();
    } else {
    	Data.projectName = $("#projectName").val();
    	Data.field1 = "radioselect2";
    }
    Data.projectCategory = $("#sui").val();
//    Data.college = $("#college").val();
    Data.content = $("#content").val();
    Data.projectNum=projectNum;
    Data.field1=$("input[name='optionsRadios']:checked").attr("id");
    $.ajax({
        url: "/stu/updateIntegralLog",
        type: 'POST', // POST
        data: Data,
        dataType: 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
            alert("已提交");
            
        }
    })
}

//分割字符串
function myProjectCateSplit(event) {
    var projectCateSplit = "";

    var projectCateSplits = new Array();
    projectCateSplits = event.split("/");
    projectCateSplits.pop();

    for (var i = 0; i < projectCateSplits.length; i++) {
        if (i == 0) {
            projectCateSplit += projectCateSplits[0];
        } else {
            projectCateSplit += "/" + projectCateSplits[i];
        }
    }

    return projectCateSplit;
}