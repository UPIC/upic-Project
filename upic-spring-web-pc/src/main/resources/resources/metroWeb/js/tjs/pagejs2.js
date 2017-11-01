

function page2(data2, data2Url, pageSize2, pageNum2) {
    var totalData = "当前显示<span>" + (parseInt(pageNum2) * parseInt(pageSize2) + 1)
        + "</span>到<span>"
        + (parseInt(pageNum2) * parseInt(pageSize2) + data2.content.length)
        + "</span>条,共<span>" + data2.totalElements + "</span>条记录";
    $('#sample_1_2_info').html(totalData);
    loadPage2(data2Url, data2.totalPages, pageSize2, pageNum2);
}

// 分页
function loadPage2(data2Url, totalPages2, pageSize2, pageNum2) {
    // var pageNum=requestData.page;
    var htmls = "<div class='pagination'><ul>";
    if ((parseInt(pageNum2) + 1) == 1) {
        htmls += "<li><a href='#'>上一页</a></li>";
    } else {
        htmls += "<li><a href='javasist:;' onClick='getData2(" + (pageNum2 - 1)
            + ",\"" + data2Url + "\"," + pageSize2 + ")'>上一页</a></li>";
    }
    for (var i = parseInt(pageNum2 + 1); i <= totalPages2; i++) {
        if ((totalPages2 - parseInt(pageNum2 + 1)) < 6) {
            if (i == parseInt(pageNum2 + 1)) {
                htmls += "<li><a href='#'>" + parseInt(pageNum2 + 1)
                    + "</a></li>";
            } else {
                htmls += "<li><a href='javasist:;' onClick='getData2(" + (i - 1)
                    + ",\"" + data2Url + "\"," + pageSize2 + ")'>" + i
                    + "</a></li>";
            }
        } else {
            if (i < (parseInt(pageNum2 + 1) + 2)) {
                htmls += "<li><a href='javasist:;' onClick='getData2(" + (i - 1)
                    + ",\"" + data2Url + "\"," + pageSize2 + ")'>" + i
                    + "</a></li>";
            } else if (i > (totalPages2 - 2)) {getData2(pageNum2, data2Url);
                htmls += "<li><a href='javasist:;' onClick='getData2(" + (i - 1)
                    + ",\"" + data2Url + "\"," + pageSize2 + ")'>" + i
                    + "</a></li>";
            } else if (i == (parseInt(pageNum2 + 1) + 3)) {
                htmls += "<li><a href='#'>...</a></li>";
            }
        }
    }
    if (totalPages2 == 1 || (parseInt(pageNum2) + 1) == totalPages2) {
        htmls += "<li><a href='#'>下一页</a></li>";
    } else {
        htmls += "<li><a href='javasist:;' onClick='getData2(" + (pageNum2 + 1)
            + ",\"" + data2Url + "\"," + pageSize2 + ")'>下一页</a></li>";
    }
    $("#widget_tab2").find('.pagination').html(htmls);
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
    $("#select-small2").change(function () {
        pageSize2 = parseInt($(this).children('option:selected').text());// 这就是selected的值
        getData2(0, data2Url);
    });
    // input监听事件
    $('#search').bind('input propertychange', function () {
        if ($.trim($(this).val()) == oldVal) {
            getData2(0, data2Url);
            return;
        }
        oldVal = $.trim($(this).val());
        if ($(this).val().length > 0) {
            requestData1.keyword = oldVal;
            getData2(0, searchKeyWordUrl);
        }
    });
})

// 下拉框注册监听
function registSelect(id) {
    $("#" + id).change(function () {
        var name = $(this).attr("name");
        var value = $(this).children('option:selected').text();
        eval('(' + "requestData." + name + "=\"" + value + '\")');
        getData2(0, data2Url);
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