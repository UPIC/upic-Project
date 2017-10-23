var getAllIntegralLogByStudentNum = "/stu/getAllIntegralLogByStudentNum";//积分申报进度列表
var types = "GET";

$(function () {
    ajaxs("studentNum=1522110240", "showAll", getAllIntegralLogByStudentNum);
})

function ajaxs(datas, method, urls, j) {
    $.ajax({
        type: types, // 提交方式
        url: urls, // 路径
        data: datas, //

        beforeSend: function (XMLHttpRequest) {
            // progress.inc();
        },
        success: function (result) { // 返回数据根据结果进行相应的处理
            addHtmls(result, method, j)
        },
        complete: function (XMLHttpRequest, textStatus) {
            // progress.done(true);
        },
        error: function () {
            // progress.done(true);
        }
    });
}

function addHtmls(result, method, j) {
    var htmls = "";
    var htmlss = "";
    if (method === "showAll") {
        for (i = 0; i < result.content.length; i++) {
            var color = "";
            var statusC = "";
            if (result.content[i].status === "PENDING_AUDIT") {
                color = "danger";
                statusC = "待审核";
            } else if (result.content[i].status === "HAVEPASSED") {
                color = "success";
                statusC = "审核成功";
            } else if (result.content[i].status === "FAILURE_TO_PASS_THE_AUDIT") {
                color = "danger";
                statusC = "审核失败";
            } else if (result.content[i].status === "ALREADY_SIGN_UP") {
                color = "danger";
                statusC = "已报名";
            } else if (result.content[i].status === "SIGNED_IN") {
                color = "success";
                statusC = "已签到";
            } else if (result.content[i].status === "COMPLETED") {
                color = "success";
                statusC = "已完成";
            }
            htmls += "<tr>";
            htmls += "<td><input type='checkbox' class='checkboxes' value='1'/></td>";
            htmls += "<td class='center_td'>" + (i + 1) + "</td>";
            htmls += "<td>" + result.content[i].integralLogId.projectNum + "</td>";
            htmls += "<td>" + splitJson(result.content[i].event) + "</td>";
            htmls += "<td>" + result.content[i].projectName + "</td>";
            htmls += "<td>" + result.content[i].integral + "</td>";
            htmls += "<td>" + getDate(result.content[i].creatTime, "yyyy-MM-dd") + "</td>";
            htmls += "<td class='center_td'>" + statusC + "</td>";
            htmls += "<td class='center_td'>";
            htmls += "<div class='message_div' onclick=ajaxs('projectNum=" + result.content[i].integralLogId.projectNum + "','details','/common/getIntegralLogByIntegralLogId','" + (i + 1) + "')><a href='#mymodal1' data-toggle='modal'>查看详情</a></div></td>";
            htmls += "</tr>";
        }
        $("#showAll").html(htmls);
    } else if (method === "details") {
        var statusC = "";
        if (result.status === "PENDING_AUDIT") {
            statusC = "待审核";
        } else if (result.status === "HAVEPASSED") {
            statusC = "审核成功";
        } else if (result.status === "FAILURE_TO_PASS_THE_AUDIT") {
            statusC = "审核失败";
        } else if (result.status === "ALREADY_SIGN_UP") {
            statusC = "已报名";
        } else if (result.status === "SIGNED_IN") {
            statusC = "已签到";
        } else if (result.status === "COMPLETED") {
            statusC = "已完成";
        }

        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>编号</div>";
        htmlss += "<div class='span3'>" + j + "</div>";
        htmlss += "<div class='span3'>代码</div>";
        htmlss += "<div class='span3'>" + result.integralLogId.projectNum + "</div>";
        htmlss += "</div><div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目申请日期</div>";
        htmlss += "<div class='span3'>" + getDate(result.creatTime, "yyyy-MM-dd") + "</div>";
        htmlss += "<div class='span3'>状态</div>";
        htmlss += "<div class='span3'>" + statusC + "</div>";
        htmlss += "</div> <div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目类别</div>";
        htmlss += "<div class='span3'>" + splitJson(result.event) + "</div>";
        htmlss += "<div class='span3'>所属学院</div>";
        htmlss += "<div class='span3'>" + result.college + "</div>";
        htmlss += "</div> <div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目名称</div>";
        htmlss += "<div class='span3'>" + result.projectName + "</div>";
        htmlss += "</div> <div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目内容</div>";
        htmlss += "<div class='span9'>" + result.content + "</div>";
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>作证材料</div>";
        htmlss += "<div class='span9'>";
        htmlss += "<a class='tooltip1' href='../../img/example.jpg'><img src='../../img/example.jpg'></a>";
        htmlss += "</div> </div>";
        $("#details").html(htmlss);
    }
}

function splitJson(json) {
    var projectCategorys = new Array();
    projectCategorys = json.split("/");
    return projectCategorys[0];
}

function getDate(date, rule) {
    var date = new Date(date);
    var dateStr = date.pattern(rule);
    return dateStr;
}

Date.prototype.pattern = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, // 月份
        "d+": this.getDate(), // 日
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时
        "H+": this.getHours(), // 小时
        "m+": this.getMinutes(), // 分
        "s+": this.getSeconds(), // 秒
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
        "S": this.getMilliseconds()
        // 毫秒
    };
    var week = {
        "0": "/u65e5",
        "1": "/u4e00",
        "2": "/u4e8c",
        "3": "/u4e09",
        "4": "/u56db",
        "5": "/u4e94",
        "6": "/u516d"
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    }
    if (/(E+)/.test(fmt)) {
        fmt = fmt
            .replace(
                RegExp.$1,
                ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f"
                        : "/u5468")
                    : "")
                + week[this.getDay() + ""]);
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
                : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}