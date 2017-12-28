/*
 * @Author: Marte
 * 活动详情
 * @Date:   2017-09-20 12:24:27
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-11-10 13:11:31
 */

 var isFull = false;
 var isApply = false;
 var txtDis = "";
 var maxNum = 0;
 var nowNum = -1;
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
    maxNum = data.maximum;
    var htmls = "";
    var htmlss = "";
    htmls += "<ul><li><div class='list-name'>项目名称：</div>";
    htmls += "<div class='list-det'>" + data.projectName + "</div></li>";
    htmls += "<li><div class='list-name'>项目类别：</div>";
    htmls += "<div class='list-det'>" + data.projectCategory
    + "</div></li><li>";
    htmls += "<div class='list-name'>活动状态：</div>";
    htmls += "<div class='list-det red' id='statusNum'>未报名</div></li>";
    htmls += "<li><div class='list-name'>预获学分：</div>";
    htmls += "<div class='list-det'>" + data.integral + "</div></li>";
    htmls += "<li><div class='list-name'>人数：</div>";
    htmls += "<div class='list-det' id='nowNum'>" + getPeopleNum(data.projectNum) + "/" + data.maximum
    + "</div></li>";
    htmls += "<li><div class='list-name'>活动时间：</div>";
    htmls += "<div class='list-det'>" + getDate(data.startTime, "MM-dd hh:mm") + "~" + getDate(data.startTime, "MM-dd hh:mm") + "</div></li>";
    htmls += "<li><div class='list-name'>报名时间：</div>";
    htmls += "<div class='list-det'>" + getDate(data.signUpStartTime, "MM-dd hh:mm") + "-" + getDate(data.signUpEndTime, "MM-dd hh:mm") + "</div></li>";
    htmls += "<li class='li-other'><div class='list-line'>项目详情：</div>";
    htmls += "<div class='li-text'>" + data.content + "</div></li></ul>";

    htmlss+="<a href='javascript:;' onClick=apply('"+data.projectNum+"')>";
    htmlss+="<div class='container nav-bot' id='txtD'>";
    htmlss+="确认报名";
    htmlss+="</div>";
    htmlss+="</a>";

    $("#content").html(htmls);
    $("#quedingbaoming").html(htmlss);

}
function getDate(date, rule) {
    var date = new Date(date);
    var dateStr = date.pattern(rule);
    return dateStr;
}
/**
 * 判断是否报名
 * 
 * @returns
 */
 function jugeApply(projectNUm) {
    $.ajax({
        url: '/stu/isSignUpByIntegralLogId',
        type: 'GET', // GET
        data: {
            projectNum: projectNUm,
// studentNum: '1522110240'
        },
        beforeSend: function (xhr) {
        },
        success: function (data) {
            // 报名了
            if (data == "success") {
                // 按钮变黑
                txtDis += "已报名";
                $(".navbar").addClass("nav-bot-black");
                $(".navbar").removeClass("nav-bot");
                $("#txtD").html(txtDis);
                isApply = false;
                $("#statusNum").html("已报名");
                return;
            }else{
            	isApply=true;
            }
            $("#statusNum").html("未报名");
        },
        error: function (xhr, textStatus) {
        },
        complete: function () {
        }
    })
}
/**
 * 获取人数
 * 
 * @param projectNUm
 * @returns
 */
 function getPeopleNum(projectNUm) {
    $.ajax({
        url: '/common/getSignUpNumberByProjectNum',
        type: 'GET', // GET
        data: {
            projectNum: projectNUm,
            studentNum: '1522110240'

        },
// dataType : 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
beforeSend: function (xhr) {
},
success: function (data) {
    var num = Number(data);
    if (num === 'NaN') {
        $("#nowNum").html("获取失败，请重新刷新页面！");
    }
    $("#nowNum").html(num + "/" + maxNum);
    nowNum = num;
            // 查询是否报名
            jugeApply(projectNUm);
            // 满人了
            if (num >= maxNum) {
                // 按钮变黑
                txtDis += "人数已满/";
                $(".navbar").addClass("nav-bot-black");
                $(".navbar").removeClass("nav-bot");
                $("#txtD").html(txtDis);
                return;
            }
        },
        error: function (xhr, textStatus) {
        },
        complete: function () {
        }
    })
}
/**
 * 报名
 * 
 * @returns
 */
 function apply(projectNUm) {
    if (nowNum == -1) {
        return;
    }
    if (nowNum >= maxNum) {
        return;
    }
    if (isApply) {
       $.ajax({
        url: '/stu/signUp',
        type: 'POST', // POST
        data: {
        	projectNum: projectNUm,
        },
        success: function (data) {
// alert("已发送报名请求")
        	if(data==="SUCCESS"){
        		alert("报名成功");
        		isApply=false;
        		 $(".navbar").addClass("nav-bot-black");
                 $(".navbar").removeClass("nav-bot");
                 $("#txtD").html("已报名");
        	}else{
        		alert("请求失败");
        	}
        }
    })
   }else{
	   alert("你已报名成功");
   }
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