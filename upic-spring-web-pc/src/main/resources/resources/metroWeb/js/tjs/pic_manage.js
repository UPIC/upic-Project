/**
 * Created by zhubuqing on 2018/1/5.
 */
var dataUrl = "/systemManager/getBanner";
var getBannerByIdUrl = "/systemManager/getBannerById";
var submitUrl = "/systemManager/uploadBanner";
var updateBannerUrl = "/systemManager/updateBanner";
var deleteBannerUrl = "/systemManager/deleteBanner";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {
    type: "OUTSIDE"
};

$(function () {
    pageSize = $("#select-small").children('option:selected').text()
    getData(pageNum, dataUrl);
})

function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        htmls += "<tr>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td class='center_td'><input type='checkbox' class='checkboxes' value='1'/></td>";
        htmls += "<td class='center_td'>";
        htmls += "<div class='common-row'>";
        htmls += "<div class='cell-right'><span name='status' class='" + data[i].field1 + "' themeColor='#6d9eeb' id=''></span>";
        htmls += "</div></div></td>";
        htmls += "<td class='center_td'>" + data[i].id + "</td>";
        htmls += "<td class='img-box'>";
        htmls += "<a class='tooltip1' href='" + data[i].url + "'><img src='" + data[i].pic + "' alt=''></a>";
        htmls += "</td>";
        htmls += "<td>" + data[i].field2 + "</td>";
        htmls += "<td class='center_td'>";
        htmls += "<a href='#mymoda2' data-toggle='modal'><div onclick=commonAjax('" + getBannerByIdUrl + "?id=" + data[i].id + "','','getBannerBrief','GET')>编辑</div></a><span class='space'>|</span><a href='#'><div onclick=commonAjax('" + deleteBannerUrl + "?id=" + data[i].id + "','','deleteBanner','GET')>删除</div></a></td>";
        htmls += "</tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function uploadPic() {
    var formData = new FormData();
    formData.append("url", $("#uploadPicUrl").val());
    formData.append("field2", $("#uploadPicContent").val());
    formData.append("file", $("#uploadPicFile")[0].files[0]);
    $.ajax({
        type: 'POST',
        url: submitUrl,
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
            if (result === "SUCCESS") {
                alert("已提交");
                getData(pageNum, dataUrl);
            } else {
                alert("提交失败");
                getData(pageNum, dataUrl);
            }
        },
        error: function (err) {
            alert(err.msg);
        }
    });
}

function getBannerBrief(res) {
    if (res != null) {
        var bannerBrief = res;
        var html = "";
        html += "<div class='modal-header'>";
        html += "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
        html += "<h4 id='mymodallabel11'>编辑</h4></div>";
        html += "<div class='row-fluid'>";
        html += "<div class='block-fluid'>";
        html += "<div class='row-form clearfix'>";
        html += "<div class='span3'>图片链接</div>";
        html += "<div class='span6'><input id='" + bannerBrief.id + "updateBannerTheUrl' type='text' name='' value='" + bannerBrief.url + "'></div>";
        html += "</div>";
        html += "<div class='row-form clearfix'>";
        html += "<div class='span3'>图片详情</div>";
        html += "<div class='span6'><input id='" + bannerBrief.field2 + "updateBannerField2' type='text' name='' value='" + bannerBrief.field2 + "'></div>";
        html += "</div>";
        html += "<div class='row-form clearfix'>";
        html += "<div class='span3'>选择图片</div>";
        html += "<div class='span3'><input id='" + bannerBrief.id + "updateBannerPic' type='file'/></div>";
        html += "</div></div><div class='dr'><span></span></div>";
        html += "</div>";
        html += "<div class='modal-footer'>";
        html += "<button class='btn btn-primary' data-dismiss='modal' aria-hidden='true' onclick='updateBanner('" + bannerBrief.id + "')'>上传</button>";
        html += "<button class='btn' data-dismiss='modal' aria-hidden='true'>取消</button>";
        html += "</div>";
        $("#mymoda2").html(html);
    }
}

function updateBanner(bannerId) {
    var formData = new FormData();
    formData.append("id", bannerId);
    formData.append("url", $("#" + bannerId + "updateBannerTheUrl").val());
    formData.append("field2", $("#" + bannerId + "updateBannerField2").val());
    formData.append("file", $("#" + bannerId + "updateBannerPic")[0].files[0]);
    $.ajax({
        type: 'POST',
        url: updateBannerUrl,
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
            if (result === "SUCCESS") {
                alert("编辑成功");
                getData(pageNum, dataUrl);
            } else {
                alert("编辑失败");
                getData(pageNum, dataUrl);
            }
        },
        error: function (err) {
            alert(err.msg);
        }
    });
}

function deleteBanner(res) {
    if (res == "SUCCESS") {
        alert("删除成功");
        getData(pageNum, dataUrl);
    } else {
        alert("删除失败");
        getData(pageNum, dataUrl);
    }
}