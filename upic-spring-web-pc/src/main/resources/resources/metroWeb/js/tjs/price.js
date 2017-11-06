var dataUrl = "/common/getPrize";//status = SHELVES
var updatePrize = "/common/updatePrize";//编辑
var addPrize = "/common/addPrize";//上传
var saveUrl="";//保存
var changstatusUrl="";
var pageSize = 0;
var totalPages = -1;
var pageNum = 0;
var requestData = {
    status: "SHELVES"
};

$(function () {
    pageSize = $("#select-small").children('option:selected').text()
    getData(pageNum, dataUrl);
})

function shangchuan(){
   var Data={};
    Data.prizeName=$("#pName").val();
    Data.score=$("#pCoin").val();
    Data.content=$("#pContent").val();
    Data.prizePic=$("#pPic").val();
    $.ajax({
        type : "GET",
        url : addPrize,
        data : Data,
        success : function(result) {
          alert("已上传");
          getData(pageNum, dataUrl);
      }
  });
}

function addHtmls(datas, pageNum) {
    totalPages = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        htmls += "<tr>";
        htmls += "<td class='center_td'>" + (parseInt(pageNum) * parseInt(pageSize) + i + 1) + "</td>";
        htmls += "<td>" + data[i].prizeName + "</td>";
        htmls += " <td class='td-line'><span>" + data[i].content + "</span></td>";
        htmls += "<td class='center_td'><img src='" + data[i].prizePic + "' alt=''></td>";
        htmls += "<td>" + data[i].score + "</td>";
        htmls += "<td>" + getDate(data[i].startTime, "yyyy-MM-dd") + "</td>";
        htmls += "<td class='center_td'>";
        htmls += "<button class='btn btn-small' onclick=putaway("+data[i].prizeNum+")>上架</button>";
        htmls += "<button class='btn btn-small down' onclick=undercarriage("+data[i].prizeNum+")>下架</button>";
        htmls += "</td>";
        htmls += "<td class='center_td'>";
        htmls += "<a href='#mymodal1' data-toggle='modal'><button class='btn btn-small' onclick=commonAjax('" + dataUrl + "','" + data[i].prizeNum + "','getInfo','GET')>编辑</button></a>";
        htmls += "</td>";
        htmls += "</tr>";
    }
    $("#data").html(htmls);
    page(datas, dataUrl, datas.size, datas.number);
}

function putaway(prizeNum){
    var Data={};
    Data.prizeNum=prizeNum;
    Data.status="上架";
    $.ajax({
        type : "GET",
        url : changstatusUrl,
        data : Data,
        success : function(result) {
          alert("已上架");
          getData(pageNum, dataUrl);
      }
  });
}

function undercarriage(prizeNum){
    var Data={};
    Data.prizeNum=prizeNum;
    Data.status="下架";
    $.ajax({
        type : "GET",
        url : changstatusUrl,
        data : Data,
        success : function(result) {
          alert("已下架");
          getData(pageNum, dataUrl);
      }
  });
}

function getInfo(data){
    var htmlss="";

    htmlss+="<div class='modal-header'>";
    htmlss+="<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
    htmlss+="<h4>编辑</h4>";
    htmlss+="</div>";
    htmlss+="<div class='modal-body'>";
    htmlss+="<div class='row-fluid'>";
    htmlss+="<div class='block-fluid'>";

    htmlss+="<div class='row-form clearfix'>";
    htmlss+="<div class='span3'>上传日期</div>";
    htmlss+="<div class='span3' id='startTime'>"+getDate(data.startTime, "yyyy-MM-dd")+"</div>";
    htmlss+="</div>";
    htmlss+="<div class='row-form clearfix'>";
    htmlss+="<div class='span3'>奖品名称</div>";
    htmlss+="<div class='span3'><input type='text' id='prizeName' name='' value='"+data.prizeName+"'/></div>";
    htmlss+="<div class='span3'>所需素拓币</div>";
    htmlss+="<div class='span3''><input type='text' id='score' name='' value='"+data.score+"'/></div>";
    htmlss+="</div>";

    htmlss+="<div class='row-form clearfix>";
    htmlss+="<div class='span3'>简介</div>";
    htmlss+="<div class='span9'><textarea class='textarea1' id='content'>"+data.content+"</textarea></div>";
    htmlss+="</div>";
    htmlss+="<div class='row-form clearfix'>";
    htmlss+="<div class='span3'>图片</div>";
    htmlss+="<div class='span9'><input type='file' name='' value=''></div>";
    htmlss+="</div>";

    htmlss+="</div>";
    htmlss+="<div class='dr'><span></span></div>";
    htmlss+="</div>";
    htmlss+="</div>";
    htmlss+="<div class='modal-footer'>";
    htmlss+="<button class='btn btn-primary' data-dismiss='modal' aria-hidden='true' onclick=save()>保存</button>";
    htmlss+="<button class='btn btn-default' data-dismiss='modal' aria-hidden='true'>取消</button>";
    htmlss+="</div>";

    $("#mymodal1").html(htmlss);
}

function save(){
    var Data={};
    Data.prizeNum=$("#prizeNum").text();
    Data.prizeName=$("#prizeName").text();
    Data.score=$("#score").text();
    Data.content=$("#content").text();
    Data.startTime=$("#startTime").text();
    $.ajax({
        type : "GET",
        url : updatePrize,
        data : Data,
        success : function(result) {
          alert("已保存");
          getData(pageNum, dataUrl);
      }
  });

}