

function page(data3, data3Url, pageSize3, pageNum3) {
    var totalData = "当前显示<span>" + (parseInt(pageNum3) * parseInt(pageSize3) + 1)
        + "</span>到<span>"
        + (parseInt(pageNum3) * parseInt(pageSize3) + data3.content.length)
        + "</span>条,共<span>" + data3.totalElements + "</span>条记录";
    $('#sample_1_1_info').html(totalData);
    loadPage(data3Url, data3.totalPages, pageSize3, pageNum3);
}

// 分页
function loadPage(data3Url, totalPages3, pageSize3, pageNum3) {
    // var pageNum=requestData.page;
    var htmls = "<div class='pagination'><ul>";
    if ((parseInt(pageNum3) + 1) == 1) {
        htmls += "<li><a href='#'>上一页</a></li>";
    } else {
        htmls += "<li><a href='javasist:;' onClick='getData3(" + (pageNum3 - 1)
            + ",\"" + data3Url + "\"," + pageSize3 + ")'>上一页</a></li>";
    }
    for (var i = parseInt(pageNum3 + 1); i <= totalPages3; i++) {
        if ((totalPages3 - parseInt(pageNum3 + 1)) < 6) {
            if (i == parseInt(pageNum3 + 1)) {
                htmls += "<li><a href='#'>" + parseInt(pageNum3 + 1)
                    + "</a></li>";
            } else {
                htmls += "<li><a href='javasist:;' onClick='getData3(" + (i - 1)
                    + ",\"" + data3Url + "\"," + pageSize3 + ")'>" + i
                    + "</a></li>";
            }
        } else {
            if (i < (parseInt(pageNum3 + 1) + 2)) {
                htmls += "<li><a href='javasist:;' onClick='getData3(" + (i - 1)
                    + ",\"" + data3Url + "\"," + pageSize3 + ")'>" + i
                    + "</a></li>";
            } else if (i > (totalPages3 - 2)) {getData3(pageNum2, data2Url);
                htmls += "<li><a href='javasist:;' onClick='getData3(" + (i - 1)
                    + ",\"" + data3Url + "\"," + pageSize3 + ")'>" + i
                    + "</a></li>";
            } else if (i == (parseInt(pageNum3 + 1) + 3)) {
                htmls += "<li><a href='#'>...</a></li>";
            }
        }
    }
    if (totalPages3 == 1 || (parseInt(pageNum3) + 1) == totalPages3) {
        htmls += "<li><a href='#'>下一页</a></li>";
    } else {
        htmls += "<li><a href='javasist:;' onClick='getData3(" + (pageNum3 + 1)
            + ",\"" + data3Url + "\"," + pageSize3 + ")'>下一页</a></li>";
    }
    $("#widget_tab1").find('.pagination').html(htmls);
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

var oldVal = "";
$(function () {
    // pageSize监听
    $("#select-small1").change(function () {
        pageSize3 = parseInt($(this).children('option:selected').text());// 这就是selected的值
        getData3(0, data3Url);
    });
    // input监听事件
    $('#search').bind('input propertychange', function () {
        if ($.trim($(this).val()) == oldVal) {
            getData3(0, data3Url);
            return;
        }
        oldVal = $.trim($(this).val());
        if ($(this).val().length > 0) {
            requestData3.keyword = oldVal;
            getData3(0, searchKeyWordUrl);
        }
    });
})

// 下拉框注册监听
function registSelect(id) {
    $("#" + id).change(function () {
        var name = $(this).attr("name");
        var value = $(this).children('option:selected').text();
        eval('(' + "requestData." + name + "=\"" + value + '\")');
        getData3(0, data3Url);
    });
}

// 通用ajax请求 万能类
function commonAjax(url, requestData, methodName, requestType, sendData) {
    $.ajax({
        type: requestType,
        url: url,
        data: requestData,
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (result) {
            if (result != "" && result != null) {
                if (sendData == null && sendData == "") {
                    var str = JSON.stringify(result);
                    eval('(' + methodName + '(' + str + '))');
                } else {
                    var str = JSON.stringify(result);
                    var str1 = JSON.stringify(sendData);
                    eval('(' + methodName + '(' + str + ',' + str1 + '))');
                }
            } else if (result == 0) {
                var str1 = JSON.stringify(sendData);
                eval('(' + methodName + '(' + 0 + ',' + str1 + '))');
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}