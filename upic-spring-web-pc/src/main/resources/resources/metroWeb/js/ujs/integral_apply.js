/*
 * @Author: Marte
 * @Date:   2017-10-11 12:12:10
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-10-11 23:44:01
 */
var getProjectWithoutSignUp = "/common/getProjectWithoutSignUp";//获取活动列表
var getProjectInfo = "/common/getProjectInfo";//查看活动详情
//少一个活动报名

$(function () {
    /*
     加载数据
     */
    ajaxs("", "showAll", getProjectWithoutSignUp);

})

function ajaxs(datas, method, urls) {
    $.ajax({
        type: "GET", // 提交方式
        url: urls,// 路径
        data: datas,//

        beforeSend: function (XMLHttpRequest) {
// progress.inc();
        },
        success: function (result) {// 返回数据根据结果进行相应的处理
            addHtmls(result, method)
        },
        complete: function (XMLHttpRequest, textStatus) {
// progress.done(true);
        },
        error: function () {
// progress.done(true);
        }
    });
}

var a = "1";

function addHtmls(result, method) {

    var htmls = "";
    var htmlss = "";
    if (method === "showAll") {
        for (var i = 0; i < result.content.length; i++) {
            htmls += "<tr>";
            htmls += "<td class='center_td'>" + (i + 1) + "</td>";
            htmls += "<td>" + result.content[i].projectCategory + "</td>";
            htmls += "<td>" + result.content[i].projectName + "</td>";
            htmls += "<td>" + result.content[i].declareUnit + "</td>";
            htmls += "<td>" + result.content[i].integral + "</td>";
            htmls += "<td>" + getDate(result.content[i].signUpEndTime, "yyyy-MM-dd") + "</td>"; //
            htmls += "<td>";
            htmls += "<div class='message_div' onclick=xiangQing('projectNum=" + result.content[i].projectNum + "','" + a + "','" + getProjectInfo + "')><a href='#mymodal1' data-toggle='modal' >报名</a></div></td>";
            htmls += "</tr>";
        }
        $("#apply").html(htmls);
    } else {
        htmlss += "<div class='modal-header'>";
        htmlss += "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
        htmlss += "<h4 id='mymodallabel1'>详情</h4>";
        htmlss += "</div>";

        htmlss += "<div class='modal-body'>";
        htmlss += "<div class='row-fluid'>";
        htmlss += "<div class='block-fluid'>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>编号</div>";
        htmlss += "<div class='span3'>" + result.projectNum + "</div>";
        htmlss += "<div class='span3'>报名截止时间</div>";
        htmlss += "<div class='span3'>" + getDate(result.signUpEndTime, "yyyy-MM-dd") + "</div>"; //
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目开始时间</div>";
        htmlss += "<div class='span3'>" + getDate(result.startTime, "yyyy-MM-dd") + "</div>";
        htmlss += "<div class='span3'>项目结束时间</div>";
        htmlss += "<div class='span3'>" + getDate(result.endTime, "yyyy-MM-dd") + "</div>";
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目类别</div>";
        htmlss += "<div class='span3'>" + result.projectCategory + "</div>";
        htmlss += "<div class='span3'>所属单位</div>";
        htmlss += "<div class='span3'>" + result.declareUnit + "</div>";
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目名称</div>";
        htmlss += "<div class='span3'>" + result.projectName + "</div>";
        htmlss += "<div class='span3'>负责人</div>";
        htmlss += "<div class='span3'>" + result.guidanceMan + "</div>";
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>项目内容</div>";
        htmlss += "<div class='span9'>" + result.content + "</div>";
        htmlss += "</div>";
        htmlss += "<div class='row-form clearfix'>";
        htmlss += "<div class='span3'>评价标准与形式</div>";
        htmlss += "<div class='span9'>" + result.checkAssessmentCriteraAndForm + "</div>";
        htmlss += "</div>";
        htmlss += "</div>";
        htmlss += "<div class='dr'><span></span></div>";
        htmlss += "</div>";
        htmlss += "</div>";

        htmlss += "<div class='modal-footer'>";
        htmlss += "<button class='btn btn-primary' data-dismiss='modal' aria-hidden='true' onclick=''>报名</button>";
        htmlss += "<button class='btn btn-default' data-dismiss='modal' aria-hidden='true'>关闭</button>";
        htmlss += "</div>";

        $("#mymodal1").html(htmlss);
    }
}

function xiangQing(projectNum, method, url) {
    ajaxs(projectNum, method, url);
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