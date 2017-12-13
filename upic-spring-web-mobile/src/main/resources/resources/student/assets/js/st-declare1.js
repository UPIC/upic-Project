function gel(id) {
    return document.getElementById(id);
}

function myProjectCateSplit(event) {
    var projectCateSplit = "";

    var projectCateSplits = new Array();
    projectCateSplits = event.split("/");
    projectCateSplits.pop();

    for (var i = 0; i < projectCateSplits.length; i++) {
        if (i = 0) {
            projectCateSplit += projectCateSplits[0];
        } else {
            projectCateSplit += "/" + projectCateSplits[i];
        }
    }

    return projectCateSplit;
}

var myRadioValue = "";

var myFinalProjectCate = "";

var projectNum = getQueryString("projectNum");

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

$(function () {
    addOldMessage();
    $("#projectCategorySelect").click(function () {
        $("#integral").val("");
        hadCategoryName = "";
        $.ajax({
            type: "GET", // 提交方式
            url: "/common/getAllProjectCategory",// 路径
            success: function (result) {// 返回数据根据结果进行相应的处理
                addHtmls(result);
            }
        });
    });

    $("#sui").change(function () {
        getId($("#sui option:selected").attr("id"), $("#sui option:selected").text());
    });

    $.ajax({
        type: "GET", // 提交方式
        url: "/common/getAllProjectCategory",// 路径
        success: function (result) {// 返回数据根据结果进行相应的处理
            addHtmls(result);
        }
    });
});

var myRadio = "radioselect1";

function addOldMessage() {
    $.ajax({
        type: "GET", // 提交方式
        async: false,
        data: {
            projectNum: projectNum
        },
        url: "/common/getIntegralLogInfoByMySelf",// 路径
        success: function (result) {// 返回数据根据结果进行相应的处理
            $("#content").html(result.content);
            if (result.field1 === "radioselect2") {
                gel("radioselect2").checked = true;
                $("#projectName").val(result.projectName);
            } else {
                myRadioValue = result.projectName;
            }
            myFinalProjectCate = myProjectCateSplit(result.event);
            $("#integral").val(result.integral);
        }
    });
}

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
    Data.projectName = $("#projectName").val();
    Data.projectCategory = $("#sui").val();
    Data.college = $("#college").val();
    Data.content = $("#content").val();
    $.ajax({
        url: subUrl,
        type: 'GET', // GET
        data: Data,
        dataType: 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
            alert("已提交");
        }
    })
}