var dataUrl = "/common/getAllUser";
var exportExcelUrl = "/common/exportUser";
var searchKeyWordUrl = "/common/userSearchBar";
var getIntegeralUrl = "/stu/getIntegeralByUserNum";
var getGrainCoinUrl = "/stu/getGrainCoinByUserNum";
var getAllIntegralLogByStudentNum = "/stu/getAllIntegralLogByUserNum";
var getProjectCollegeUrl = "/common/getCollege";
var getProjectclazzUrl = "/common/getClazz";
var getUserByUserNum = "/common/getUserByUserNum";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {
    type: 'STUDENT'
};

$(function () {
    pageSize = $("#select-small").children('option:selected').text()
    commonAjax(getProjectCollegeUrl, null, "addProjectCollege", "GET");
    commonAjax(getProjectclazzUrl, null, "addProjectclazz", "GET");
    // commonAjax(getIntegeralUrl, null, "addIntegeral", "GET");
    registSelect("getProjectCollege");
    registSelect("getProjectclazz");
    // registSelect("getIntegeral");
    getData(pageNum, dataUrl);

    $("#exportBtn").click(function () {
        var baseModels = ["college", "clazz", "userNum", "username", "earnedPoints", "earningPoints"];
        var str = JSON.stringify(baseModels);
        var form = $("<form></form>").attr("action", exportExcelUrl).attr("method", "GET");
        form.append($("<input></input>").attr("type", "hidden").attr("name", "baseModel").attr("value", str));
        var formKeyValue = appendForm(requestData);
        for (var i = 0; i < formKeyValue.key.length; i++) {
            form.append($("<input></input>").attr("type", "hidden").attr("name", formKeyValue.key[i]).attr("value", formKeyValue.value[i]));
        }
        form.appendTo('body').submit().remove();
        form.submit();
    })
})

function addProjectclazz(res) {
    var data = res.content;
    var htmls = "";
    htmls += "<option value='4' class='yellow'>班级筛选...</option>";

    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='" + (i + 4) + "'>" + data[i].clazz + "</option>";
    }
    $("#getProjectclazz").html(htmls);
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
// function getIntegeral(res) {
//     var data = res.content;
//     var htmls = "";
//     htmls += "<option value='4' class='yellow'>积分筛选...</option>";
//
//     for (var i = 0; i < data.length; i++) {
//         htmls += "<option value='" + (i + 4) + "'>" + data[i].integeral + "</option>";
//     }
//     $("#getIntegeral").html(htmls);
// }

function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='" + data[i].studentNum + "'/></td>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td>" + data[i].college + "</td>";
        htmls += "<td>" + data[i].clazz + "</td>";
        htmls += "<td>" + data[i].userNum + "</td>";
        htmls += "<td>" + data[i].username + "</td>";
        htmls += "<td id='" + data[i].userNum + "jf'>" + getIntegeralAjax(data[i].userNum, (data[i].userNum + 'jf')) + "</td>"; // commonAjax(getIntegeralUrl, requestData, 'getIntegeral', 'GET', data[i].studentNum + 'jf')
        htmls += "<td id='" + data[i].userNum + "stf'>" + getGrainCoinAjax(data[i].userNum, (data[i].userNum + 'stf')) + "</td>"; // commonAjax(getGrainCoinUrl, requestData, 'getGrainCoin', 'GET', data[i].studentNum + 'stf')
        htmls += "<td class='center_td'><a href='#mymodal1'";
        htmls += "data-toggle='modal'><div class='message_div' onclick=commonAjax('" + getUserByUserNum + "','userNum=" + data[i].userNum + "','getProjectInfo','GET')>查看详情</div></a></td></tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function getProjectInfo(data) {
    var htmlss = "";

    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>所属学院</div>";
    htmlss += "<div class='span3'>" + data.college + "</div>";
    htmlss += "<div class='span3'>班级</div>";
    htmlss += "<div class='span3'>" + data.clazz + "</div>";
    htmlss += "</div>";
    htmlss += "<div class='row-form clearfix'>";
    htmlss += "<div class='span3'>姓名</div>";
    htmlss += "<div class='span3'>" + data.username + "</div>";
    htmlss += "<div class='span3'>学号</div>";
    htmlss += "<div class='span3'>" + data.userNum + "</div>";
    htmlss += "</div>";

    htmlss += "<table class='table table-bordered'>";
    htmlss += "<thead><tr><th>类别</th><th>积分</th></tr></thead>";
    htmlss += "<tbody id='" + data.userNum + "'>";
    commonAjax(getAllIntegralLogByStudentNum, 'userNum=' + data.userNum, 'getAllIntegralLogByUserNum', 'GET', data.userNum);
    //commonAjax(getUserByUserNum ,'userNum=" + data[i].userNum + "','getProjectInfo','GET')
    htmlss += "</tbody>";
    htmlss += "</table>";

    $("#getProjectInfo").html(htmlss);
}

function getAllIntegralLogByUserNum(data, id) {
    var htmls = "";
    var totalIntegral = 0;
    for (var i = 0; i < data.content.length; i++) {
        htmls += "<tr><td>" + data.content[i].projectCategory + "</td><td>" + data.content[i].integral + "</td></tr>";
        totalIntegral += data.content[i].integral;
    }
    htmls += "<tr><td colspan='4' style='text-align:right;padding-right: 20px;'>总计:<span>" + totalIntegral + "</span></td></tr>";
    $("#" + id).html(htmls);
}

function getIntegeralAjax(userNum, id) {
    var a = 0;
    $.ajax({
        type: 'GET',
        url: getIntegeralUrl,
        data: {
            userNum: userNum
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (result) {
            a = result;
            $("#" + id).html(a);
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

function getGrainCoinAjax(userNum, id) {
    var a = 0;
    $.ajax({
        type: 'GET',
        url: getGrainCoinUrl,
        data: {
            userNum: userNum
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (result) {
            a = result;
            $("#" + id).html(a);
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}