var data3Url = "";
var pageSize = 0;
var totalPages=-1;
var pageNum=0;
var requestData={

};

$(function(){
    pageSize=$("#select-small").children('option:selected').text()
    getData(pageNum,data3Url);
    registSelect(operationType);
    registSelect(operationStatus);
})


function addHtmls(datas,pageNum) {
    totalPages=datas.totalElements;
    var data=datas.content;
    var htmls="";
    for(var i=0;i<data.length;i++){
        htmls+="<tr><td><input type='checkbox' class='checkboxes' value='1' id='"+data[i].logNum+"'/></td>";
        htmls+="<td class='center_td'>"+(parseInt(pageNum)*parseInt(pageSize)+i+1)+"</td>";
        htmls+="<td>"+data[i].createTime+"</td>";
        htmls+="<td>"+data[i].userName+"</td>";
        htmls+="<td>"+data[i].operationType+"</td>";
        htmls+="<td>"+data[i].operationStatus+"</td>";
        htmls+="<td>"+data[i].content+"</td>";
        htmls+="<td>";
        htmls+="<div class='message_div'><a href='#mymodal9' data-toggle='modal'><span onclick=commonAjax('"+data3Url+"','logNum="+data[i].logNum+"','getLogInfo','GET')>【查看详情】</span></a></div></td>";
        htmls+="</tr>";
    }
    $("#data").html(htmls);
    page(datas,data3Url,datas.size,datas.number);
}


function getLogInfo(data){
    var htmlss="";
    var statuss="";
    if (true) {};
    htmlss+="<div class='control-group'>";
    htmlss+="<label class='control-label'>创建时间</label>";
    htmlss+="<div class='controls'>";
    htmlss+="<span>"+data.+"</span>";
    htmlss+="</div>";
    htmlss+="</div>";
    htmlss+="<div class='control-group'>";
    htmlss+="<label class='control-label'>操作员姓名</label>";
    htmlss+="<div class='controls'>";
    htmlss+="<span>"+data.+"</span>";
    htmlss+="</div>";
    htmlss+="</div>";
    htmlss+="<div class='control-group'>";
    htmlss+="<label class='control-label'>操作员工号</label>";
    htmlss+="<div class='controls'>";
    htmlss+="<span>"+data.+"</span>";
    htmlss+="</div>";
    htmlss+="</div>";
    htmlss+="<div class='control-group'>";
    htmlss+="<label class='control-label'>操作状态</label>";
    htmlss+="<div class='controls'>";
    htmlss+="<span>"+statuss+"</span>";
    htmlss+="</div>";
    htmlss+="</div>";
    htmlss+="<div class='control-group'>";
    htmlss+="<label class='control-label'>操作内容</label>";
    htmlss+="<div class='controls'>";
    htmlss+="<span>"+data.content+"</span>";
    htmlss+="</div>";
    htmlss+="</div>";
    $("#getLogInfo").html(htmlss);
}


