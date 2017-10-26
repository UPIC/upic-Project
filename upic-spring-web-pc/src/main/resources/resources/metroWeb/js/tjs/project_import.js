 var dataUrl = "";
 var searchKeyWordUrl="";
 var getProjectTypeUrl="";
 var saveUrl="";
 var getAllProjectCategoryUrl="/common/getAllProjectCategory";
 var pageSize = 0;
 var totalPages=-1;
 var pageNum=0;
 var requestData={

 };

//上传文件
function getFile(file){
    //ajax方法上传文件到后台
}



$(function(){
    pageSize=$("#select-small").children('option:selected').text()
    commonAjax(getProjectTypeUrl,null,"addProjectType","GET");
    registSelect("getProjectType");
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


function addHtmls(datas,pageNum) {
    totalPages=datas.totalElements;
    var data=datas.content;
    var htmls="";
    for(var i=0;i<data.length;i++){

      htmls+="<tr><td><input type='checkbox' class='checkboxes' value='1' id='"+data[i].projectNum+"'/></td>";
      htmls+="<td class='center_td'>"+(parseInt(pageNum)*parseInt(pageSize)+i+1)+"</td>";
      htmls+="<td id='"+data[i].projectNum+"a'>"+data[i].projectNum+"</td>";
      htmls+="<td id='"+data[i].projectNum+"b'>"+data[i].projectCategory+"</td>";
      htmls+="<td id='"+data[i].projectNum+"c'>"+data[i].projectName+"</td>";
      htmls+="<td id='"+data[i].projectNum+"d'>"+data[i].integral+"</td>";
      htmls+="<td id='"+data[i].projectNum+"e'>"+data[i].number+"</td>";
      htmls+="<td id='"+data[i].projectNum+"f'>"+data[i].number*data[i].integral+"</td>";
      htmls+="<td id='"+data[i].projectNum+"g'>"+data[i].creatTime+"</td>";
      htmls+="<td class='center_td'><div class='message_div'><a href='#mymodal2' data-toggle='modal'>";
      htmls+="<span onclick=commonAjax('"+dataUrl+"','projectNum="+data[i].projectNum+"','bianji','GET')>编辑</span>";
      htmls+="</a><span class='space'>|</span><a>";
      htmls+="<span onclick=sub("+data[i].projectNum+")>提交</span>";
      htmls+="</a></div><td></tr>";
  }

  $("#data").html(htmls);
  page(datas,dataUrl,datas.size,datas.number);
}

function bianji(data){
 var htmlss="";

 htmlss+="<div class='modal-header'>";
 htmlss+="<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
 htmlss+="<h4>详情</h4>";
 htmlss+="</div>";
 htmlss+="<div class='modal-body'>";
 htmlss+="<div class='row-fluid'>";
 htmlss+="<div class='block-fluid'>";
 htmlss+="<div class='row-form clearfix'>";
 htmlss+="<div class='span3'>编号</div>";
 htmlss+="<div class='span3'>"+(parseInt(pageNum)*parseInt(pageSize)+i+1)+"</div>";
 htmlss+="<div class='span3'>代码</div>";
 htmlss+="<div class='span3' id='projectNum'>"+data.projectNum+"</div>";
 htmlss+="</div>";
 htmlss+="<div class='row-form clearfix'>";
 htmlss+="<div class='span3'>项目申请日期</div>";
 htmlss+="<div class='span3' id='creatTime'>"+getDate(data.creatTime,'yyyy-mm-dd')+"</div>";
 htmlss+="<div class='span3'>状态</div>";
 htmlss+="<div class='span3' id='status'>"+data.status+"</div>";
 htmlss+="</div>";
 htmlss+="<div class='row-form clearfix'>";
 htmlss+="<div class='span3'>项目类别</div>";
 htmlss+="<div class='span3'>";
 htmlss+="<select class='input-large m-wrap' tabindex='1' id='getAllProjectCategory'>";
 htmlss+="</select></div>";
 htmlss+="<div class='span3'>所属学院</div>";
 htmlss+="<div class='span3'><input type='text' id='college' value='"+data.college+"'></div>";
 htmlss+="</div>";
 htmlss+="<div class='row-form clearfix'>";
 htmlss+="<div class='span3'>项目名称</div>";
 htmlss+="<div class='span3'><input type='text' id='projectName' value='"+data.projectName+"'></div>";
 htmlss+="<div class='span3'>负责人</div>";
 htmlss+="<div class='span3'><input type='text' id='guidanceMan' value='"+data.guidanceMan+"'></div>";
 htmlss+="</div>";
 htmlss+="<div class='row-form clearfix'>";
 htmlss+="<div class='span3'>工作量</div>";
 htmlss+="<div class='span3'><input type='text' id='gzl' value='"+data.gzl+"'></div>";
 htmlss+="<div class='span3'>积分</div>";
 htmlss+="<div class='span3'><input type='text' id='grainCoin' value='"+data.grainCoin+"'></div>";
 htmlss+="</div>";
 htmlss+="<div class='row-form clearfix'>";
 htmlss+="<div class='span3'>开始时间</div>";
 htmlss+="<div class='span3'><div class='input-append date' id='datetimepicker1' data-date='2017/09/14' data-date-format='dd-mm-yyyy'>";
 htmlss+="<input class='span2 nowdate' size='16' type='text' id='startTime' value='12-02-2012' style='width:100px !important;'>";
 htmlss+="<span class='add-on'><i class='icon-th'></i></span>";
 htmlss+="</div></div>";
 htmlss+="<div class='span3'>结束时间</div>";
 htmlss+="<div class='span3'><div class='input-append date' id='datetimepicker2' data-date='2017/09/14' data-date-format='dd-mm-yyyy'>";
 htmlss+="<input class='span2 nowdate' size='16' type='text' id='endTime' value='12-02-2012' style='width:100px !important;'>";
 htmlss+="<span class='add-on'><i class='icon-th'></i></span>";
 htmlss+="</div></div>";
 htmlss+="</div>";
 htmlss+="<div class='row-form clearfix'>";
 htmlss+="<div class='span3'>最大参与人数</div>";
 htmlss+="<div class='span3'><input type='text' id='maximum' value='"+data.maximum+"'></div>";
 htmlss+="</div>";
 htmlss+="<div class='row-form clearfix'>";
 htmlss+="<div class='span3'>项目内容</div>";
 htmlss+="<div class='span9'>";
 htmlss+="<textarea name='' id='content' placeholder='"+data.content+"'></textarea></div>";
 htmlss+="</div>";
 htmlss+="<div class='row-form clearfix'>";
 htmlss+="<div class='span3'>评价标准与形式</div>";
 htmlss+="<div class='span9'><textarea name='' id='checkAssessmentCriteraAndForm' placeholder='"+data.checkAssessmentCriteraAndForm+"'></textarea></div>";
 htmlss+="</div>";
 htmlss+="</div>";
 htmlss+="<div class='dr'><span></span></div>";
 htmlss+="</div>";
 htmlss+="</div>";
 htmlss+="<div class='modal-footer'>";
 htmlss+="<button class='btn btn-primary' data-dismiss='modal' aria-hidden='true' onclick=save()>保存</button>";
 htmlss+="<button class='btn' data-dismiss='modal' aria-hidden='true'>关闭</button>";
 htmlss+="</div>";

 $("#mymodal2").html(htmlss);
 $.ajax({
    type : "GET",
    url : getAllProjectCategoryUrl,
    success : function(result) {
        var data=res.content;
        var htmls="";
        for(var i=0;i<data.length;i++){
            htmls+="<option value='category 1'>"+data[i].categoryName+"</option>";
        }
        $("#getAllProjectCategory").html(htmls);
    }
});
}

function save(){
  var Data={};

  Data.projectName=$("projectName").val();
  Data.creatTime=$("#creatTime").text();
  Data.projectstatus=$("#status").val();
  Data.projectCategory=$('#getAllProjectCategory option:selected') .val();
  Data.college=$("#college").val();
  Data.projectName=$("#projectName").val();
  Data.guidanceMan=$("#guidanceMan").val();
  Data.gzl=$("#gzl").val();
  Data.grainCoin=$("#grainCoin").val();
  Data.startTime=$("#startTime").val();
  Data.endTime=$("#endTime").val();
  Data.maximum=$("#maximum").val();
  Data.content=$("#content").val();
  Data.checkAssessmentCriteraAndForm=$("#checkAssessmentCriteraAndForm").text();

  Data.status="";//保存状态码
  $.ajax({
    type : "GET",
    url : saveUrl,
    data : Data,
    success : function(result) {
      alert("已保存")
  }
});
}

function sub(id){
   var Data={};
   Data.projectNum=$("#"+id+"a").text();
   Data.projectCategory=$("#"+id+"b").text();
   Data.projectName=$("#"+id+"c").text();
   Data.integral=$("#"+id+"d").text();
   Data.number=$("#"+id+"e").text();
   Data.creatTime=$("#"+id+"g").text();

   Data.status="";//提交的状态码
   $.ajax({
    type : "GET",
    url : saveUrl,
    data : Data,
    success : function(result) {
      alert("已提交")
  }
});

}