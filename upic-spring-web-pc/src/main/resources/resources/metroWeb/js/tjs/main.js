/*
 * @Author: Marte
 * @Date:   2017-10-11 08:16:44
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-10-12 14:29:19
 */
var projectCategory = "";

var results = "";

$(function () {
    getAllProjectCategory();

    $.ajax({
        type: "GET",
        url: "/common/getUserInfo",
        success: function (result) {
            if (result.type === "TEACHER") {
                $("#getUsername").text(result.username + " 老师，你好！");
                $("#getIntegralLogDetails").css("display", "none");
                $("#getGrainCoinDetails").hide();
            } else {
                $("#getUsername").text(result.username + " 同学，你好！");
            }

            results = result;
        }
    });

    $.ajax({
        type: "GET",
        url: "/stu/getIntegeral",
        success: function (result) {
            $("#putIntegralLogInIt").html(result);
        }
    });

    $.ajax({
        type: "GET",
        url: "/stu/getGrainCoin",
        success: function (result) {
            $("#suTuoBi").html("当前素拓币为：" + result);
        }
    });
})

function getIntegralLogDetails() {
    $.ajax({
        type: "GET", // 提交方式
        url: "/common/getIntegralLogPageByUserNum",// 路径
        data: "studentNum=" + results.userNum,
        beforeSend: function (XMLHttpRequest) {
            // progress.inc();
        },
        success: function (result) {// 返回数据根据结果进行相应的处理
            var datas = result.content;
            addHtmls(datas);
        },
        complete: function (XMLHttpRequest, textStatus) {
            // progress.done(true);
        },
        error: function () {
            // progress.done(true);
        }
    });
}

function addHtmls(result) {
    var integralLogResult = result;
    var htmls = "";
    htmls = "<div class='row-form clearfix'><div class= 'span3' > 所属学院 </div> <div class = 'span3' > "
        + results.college
        + " </div> <div class = 'span3' > 班级 </div> <div class = 'span3' >"
        + results.clazz
        + " </div> </div> <div class ='row-form clearfix'> <div class = 'span3' > " +
        "姓名 </div> <div class = 'span3' > "
        + results.username + " </div> <div class = 'span3' > " +
        "学号 </div> <div class = 'span3' > "
        + results.userNum + " </div> </div> ";
    var total = 0;

    htmls += "<table class='table table-bordered'>";
    htmls += "<thead>";
    htmls += "<tr>";
    htmls += "<th>类别</th>";
    htmls += "<th>积分</th>";
    htmls += "<th>类别</th>";
    htmls += "<th>积分</th>";
    htmls += "</tr>";
    htmls += "</thead>";
    htmls += "<tbody>";

    for (var i = 0; i < projectCategory.length; i++) {
        var projectCategoryTotal = 0;

        for (var j = 0; j < integralLogResult.length; j++) {
            if (integralLogResult[j].projectCategory === projectCategory[i].categoryName && (integralLogResult[j].status === "HAVEPASSED" || integralLogResult[j].status === "COMPLETED")) {
                projectCategoryTotal += integralLogResult[j].integral;
            }
        }

        if (i % 2 == 0) {
            if (projectCategoryTotal < 0.5 && (projectCategory[i].categoryName === "社会实践" || projectCategory[i].categoryName === "志愿服务" || projectCategory[i].categoryName === "讲座论坛" || projectCategory[i].categoryName === "生活能力")) {
                htmls += "<tr>";
                htmls += "<td>" + projectCategory[i].categoryName + "</td>";
                htmls += "<td>" + projectCategoryTotal + "<img src='../../img/warn.png' alt='' width='24px' height='24px'></td>";
            } else {
                htmls += "<tr>";
                htmls += "<td>" + projectCategory[i].categoryName + "</td>";
                htmls += "<td>" + projectCategoryTotal + "</td>";
            }
        } else {
            if (projectCategoryTotal < 0.5 && (projectCategory[i].categoryName === "社会实践" || projectCategory[i].categoryName === "志愿服务" || projectCategory[i].categoryName === "讲座论坛" || projectCategory[i].categoryName === "生活能力")) {
                htmls += "<td>" + projectCategory[i].categoryName + "</td>";
                htmls += "<td>" + projectCategoryTotal + "<img src='../../img/warn.png' alt='' width='24px' height='24px'></td>";
                htmls += "</tr>";
            } else {
                htmls += "<td>" + projectCategory[i].categoryName + "</td>";
                htmls += "<td>" + projectCategoryTotal + "</td>";
                htmls += "</tr>";
            }
        }

        total += projectCategoryTotal;
    }

    if (projectCategory.length % 2 == 1) {
        htmls += "<td></td>";
        htmls += "<td></td>";
        htmls += "</tr>";
    }

    htmls += "<tr>";
    htmls += "<td colspan='4' style='text-align:right;padding-right: 20px;'>总计:<span>" + total + "</span>";
    htmls += "</td>";
    htmls += "</tr>";
    htmls += "</tbody></table>";
    htmls += "<div class='warn'>";
    htmls += "<img src='../../img/warn.png' alt='' width='24px' height='24px'>“社会实践”、“志愿服务”、“讲座论坛”、“生活能力”课程积分至少达到0.5分";
    htmls += "</div>";

    $("#getInfo").html(htmls);
}

function getAllProjectCategory() {
    $.ajax({
        type: "GET",
        url: "/common/getAllProjectCategory",
        success: function (result) {
            projectCategory = result.content;
        }
    });
}