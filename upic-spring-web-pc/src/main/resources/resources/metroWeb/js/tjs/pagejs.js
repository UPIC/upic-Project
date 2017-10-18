function getData(pageNum, dataUrl, pageSize) {
	requestData.size = parseInt($("#select-small").children('option:selected')
			.text());
	requestData.page = pageNum;
	$.ajax({
		type : "GET",
		url : dataUrl,
		data : requestData,
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(result) {
			if (result != "" && result != null) {
				addHtmls(result, pageNum, requestData)
			}
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
		}
	});

}
function page(data, dataUrl, pageSize, pageNum) {
	var totalData = "当前显示<span>" + (parseInt(pageNum) * parseInt(pageSize) + 1)
			+ "</span>到<span>"
			+ (parseInt(pageNum) * parseInt(pageSize) + data.content.length)
			+ "</span>条,共<span>" + data.totalElements + "</span>条记录";
	$('#sample_1_info').html(totalData);
	loadPage(dataUrl, data.totalPages, pageSize, pageNum);
}
function loadPage(dataUrl, totalPages, pageSize, pageNum) {
	// var pageNum=requestData.page;
	var htmls = "<div class='pagination'><ul>";
	if ((parseInt(pageNum) + 1) == 1) {
		htmls += "<li><a href='#'>上一页</a></li>";
	} else {
		htmls += "<li><a href='javasist:;' onClick='getData(" + (pageNum - 1)
				+ ",\"" + dataUrl + "\"," + pageSize + ")'>上一页</a></li>";
	}
	for (var i = parseInt(pageNum + 1); i <= totalPages; i++) {
		if ((totalPages - parseInt(pageNum + 1)) < 6) {
			if (i == parseInt(pageNum + 1)) {
				htmls += "<li><a href='#'>" + parseInt(pageNum + 1)
						+ "</a></li>";
			} else {
				htmls += "<li><a href='javasist:;' onClick='getData(" + (i - 1)
						+ ",\"" + dataUrl + "\"," + pageSize + ")'>" + i
						+ "</a></li>";
			}
		} else {
			if (i < (parseInt(pageNum + 1) + 2)) {
				htmls += "<li><a href='javasist:;' onClick='getData(" + (i - 1)
						+ ",\"" + dataUrl + "\"," + pageSize + ")'>" + i
						+ "</a></li>";
			} else if (i > (totalPages - 2)) {
				htmls += "<li><a href='javasist:;' onClick='getData(" + (i - 1)
						+ ",\"" + dataUrl + "\"," + pageSize + ")'>" + i
						+ "</a></li>";
			} else if (i == (parseInt(pageNum + 1) + 3)) {
				htmls += "<li><a href='#'>...</a></li>";
			}
		}
	}
	if (totalPages == 1 || (parseInt(pageNum) + 1) == totalPages) {
		htmls += "<li><a href='#'>下一页</a></li>";
	} else {
		htmls += "<li><a href='javasist:;' onClick='getData(" + (pageNum + 1)
				+ ",\"" + dataUrl + "\"," + pageSize + ")'>下一页</a></li>";
	}
	$('.pagination').html(htmls);
}

$(function(){
	$("#select-small").change(function() {
		pageSize = parseInt($(this).children('option:selected').text());// 这就是selected的值
		getData(0,dataUrl);
	});
})

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
