var getResourceBySelfUrl = "/operator/getResourceBySelf";
var getUserUrl = "/common/getUserInfo";

$(function () {
    commonAjax(getResourceBySelfUrl, "", "getFirstResourceBySelf", "GET");
    commonAjax(getUserUrl, "", "getUserInfo", "GET");
})

function getFirstResourceBySelf(datas) {
    var htmls = "";

    for (var i = 0; i < datas.length; i++) {
        if (datas[i].level <= 1) {
            htmls += "<li class='sub-menu ";
            if (i == 0) {
                htmls += "active";
            }
            htmls += "'>";
            var num = checkNum(i, datas);
            if (num == 0) {
                htmls += "<a href='" + datas[i].url + "'target='myFrameName'>";
                htmls += "<i class='icon-book'></i>";
                htmls += "<span>" + datas[i].resourceName + "</span>";
                htmls += "</a>";
                htmls += "</li>";
                continue;
            }
            htmls += "<a href='javascript:;'>";
            htmls += "<i class='icon-book'></i>";
            htmls += "<span>" + datas[i].resourceName + "</span>";
            htmls += "<span class='arrow'></span>";
            htmls += "</a>";
            htmls += "<ul class='sub'";
            if (i == 0) {
                htmls += "'style='display: none;'";
            }
            htmls += ">";
            for (var j = 0; j < datas.length; j++) {
                if (datas[j].fatherId === datas[i].id) {
                    htmls += "<li><a class='' href='" + datas[j].url
                        + "' target='myFrameName'>" + datas[j].resourceName
                        + "</a></li>";
                }
            }
            htmls += "</ul></li>";
        }
    }
    $("#getResourceBySelfId").html(htmls);
    $('.sidebar-menu>li').click(function (e) {
        $('.sidebar-menu>li').removeClass('active');
        $(this).addClass('active');
    });

    function checkNum(i, datas) {
        var num = 0;
        for (var j = 0; j < datas.length; j++) {
            if (datas[j].fatherId === datas[i].id) {
                num++;
            }
        }
        return num;
    }
}

function getUserInfo(datas) {
    $("#username").html(datas.username);
}