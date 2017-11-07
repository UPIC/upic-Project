 var dataUrl="";
 var changeCodeUrl="";
 var pageSize = 0;
 var totalPages=-1;
 var pageNum=0;
 var requestData={

 };

 $(function(){
    pageSize=$("#select-small").children('option:selected').text();
    commonAjax(getProjectTypeUrl,null,"addProjectType","GET");
    commonAjax(getProjectStatusUrl,null,"addProjectStatus","GET");
    registSelect("getProjectType");
    registSelect("getProjectStatus");
    getData(pageNum,dataUrl);
})

 function addProjectType(res){
    var data=res.content;
    var htmls="";
    htmls+="<option value='4' class='yellow'>项目类别筛选...</option>";

    for(var i=0;i<data.length;i++){
      htmls+="<option value='"+(i+4)+"'>"+data[i].categoryName+"</option>";
  }
  $("#getProjectType").html(htmls);
}

function addProjectStatus(res){
    var data=res.content;
    var htmls="";
    htmls+="<option value='4' class='yellow'>项目状态筛选...</option>";

    for(var i=0;i<data.length;i++){
      htmls+="<option value='"+(i+4)+"'>"+data[i].status+"</option>";
  }
  $("#getProjectStatus").html(htmls);
}

function addHtmls(datas,pageNum) {
    totalPages=datas.totalElements;
    var data=datas.content;
    var htmls="";
    var statuss="";
    var turnOnOrTurnOff="";
    var tOrF=0;
    for(var i=0;i<data.length;i++){
        if (data[i].implementationProcess==="SAVED") {statuss="已保存"};
        if (data[i].implementationProcess==="IN_AUDIT") {statuss="审核中"};
        if (data[i].implementationProcess==="AUDITED") {statuss="已审核"};
        if (data[i].implementationProcess==="NOT_PASS") {statuss="未通过"};
        if (data[i].implementationProcess==="ENROLLMENT") {statuss="报名中"};
        if (data[i].implementationProcess==="HAVE_IN_HAND") {statuss="进行中"};
        if (data[i].implementationProcess==="COMPLETED") {statuss="已完成"};
        if (data[i].implementationProcess==="CHECKED") {statuss="已验收"};

        if (data[i].onOff=0) {turnOnOrTurnOff="switch-off";tOrF=0};
        if (data[i].onOff=1) {turnOnOrTurnOff="switch-on";tOrF=1};

        htmls+="<tr><td><input type='checkbox' class='checkboxes' value='1' id='"+data[i].projectNum+"'/></td>";
        htmls+="<td class='center_td'>";
        htmls+="<div class='common-row'>";
        htmls+="<div class='cell-right'><span class='"+turnOnOrTurnOff+"' themeColor='#6d9eeb' id='"+data[i].projectNum+"' onclick=changeStatus("+data[i].projectNum+","+tOrF+")'></span></div>";
        htmls+="</div>";
        htmls+="</td>";
        htmls+="<td class='center_td'>"+data[i].projectNum+"</td>";
        htmls+="<td>"+data[i].projectCategory+"</td>";
        htmls+="<td>"+data[i].projectName+"</td>";
        htmls+="<td>"+data[i].guidanceMan+"</td>";
        htmls+="<td>"+getDate(data[i].startTime,'yyyy-mm-dd')+"</td>";
        htmls+="<td>"+getDate(data[i].endTime,'yyyy-mm-dd')+"</td>";
        htmls+="<td>"+statuss+"</td>";
        htmls+="<td class='center_td'>";
        htmls+="<a href='#mymodal1' data-toggle='modal'><div class='message_div'><span onclick=commonAjax('"+dataUrl+"','projectNum="+data[i].projectNum+"','getInfo','GET')>查看详情</span></div></a></td>";
        htmls+="</tr>";
    }

    $("#data").html(htmls);
    page(datas,dataUrl,datas.size,datas.number);
}

function getInfo(data){
   var htmlss="";
   htmlss+="<div class='row-form clearfix'>";
   htmlss+="<div class='span3'>项目类别</div>";
   htmlss+="<div class='span3'>"+data.projectCategory+"</div>";
   htmlss+="<div class='span3'>项目名称</div>";
   htmlss+="<div class='span3'>"+data.projectName+"</div>";
   htmlss+="</div>";
   htmlss+="<div class='row-form clearfix'>";
   htmlss+="<div class='span3'>起始时间</div>";
   htmlss+="<div class='span3'>"+getDate(data.startTime,'yyyy-mm-dd')+"</div>";
   htmlss+="<div class='span3'>结束时间</div>";
   htmlss+="<div class='span3'>"+getDate(data.endTime,'yyyy-mm-dd')+"</div>";
   htmlss+="</div>";
   htmlss+="<div class='row-form clearfix'>";
   htmlss+="<div class='span3'>所属学院</div>";
   htmlss+="<div class='span3'>"+data.college+"</div>";
   htmlss+="</div>";
   htmlss+="<div class='row-form clearfix'>";
   htmlss+="<div class='span3'>二维码</div>";
   htmlss+="<div class='span9'><img src='"+data.pic+"' alt=''></div>";
   htmlss+="</div>";

   $("#mymodal1").html(htmlss);
}

function changeStatus(pN,juge){
    if (juge=0) {
        juge=1;
        $("#"+pN).removeClass('switch-off');
        $("#"+pN).addClass('switch-on');
    };
    if (juge=1) {
        juge=0;
        $("#"+pN).removeClass('switch-on');
        $("#"+pN).addClass('switch-off');
    };
     $.ajax({
        type: 'GET',
        url: changeCodeUrl,
        data: {
           "projectNum" : pN,
           "onOff" :  juge
        },
        success: function (result) {
            alert(result);
        }
    });
}



