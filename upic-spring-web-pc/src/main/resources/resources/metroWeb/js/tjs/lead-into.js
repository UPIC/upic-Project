var dataUrl="";
var addUserUrl="/common/addUser";//添加用户
var searchKeyWordUrl="/common/userSearchBar";//搜索条
var updateUser="/common/updateUser"//更新用户
var daoruUrl="";//导入用户
var pageSize = 0;
var totalPages=-1;
var pageNum=0;

function getFile(){//获取EXCEL
  //ajax方法上传文件到后台
    var files = $('input[name="inputFile"]').prop('files');//获取到文件列表
    $.ajax({
        type: "POST",
        url: daoruUrl,
        data: files,
        success: function (result) {
            alert("已导入")
        }
    });

}

function addOne(){
  var Data={};
  Data.college=$("#college1").val();
  Data.clazz=$("#clazz1").text();
  Data.userNum=$("#userNum1").val();
  Data.userName=$("#userName1").val();

  Data.status="";//保存状态码
  $.ajax({
    type : "POST",
    url : addUserUrl,
    data : Data,
    success : function(result) {
      alert("已新建用户")
  }
});
}

function clearStr(){
  $("#college1").val("");
  $("#clazz1").text("");
  $("#userNum1").val("");
  $("#userName1").val("");
}

$(function(){
    pageSize=$("#select-small").children('option:selected').text();
    getData(pageNum,dataUrl);
})


function addHtmls(datas,pageNum) {
    totalPages=datas.totalElements;
    var data=datas.content;
    var htmls="";
    for(var i=0;i<data.length;i++){
        htmls+="<tr>";
        htmls+="<td class='center_td'>"+(parseInt(pageNum)*parseInt(pageSize)+i+1)+"</td>";
        htmls+="<td id='"+data[i].userNum+"a'>"+data[i].college+"</td>";
        htmls+="<td id='"+data[i].userNum+"b'>"+data[i].clazz+"</td>";
        htmls+="<td id='"+data[i].userNum+"c'>"+data[i].userNum+"</td>";
        htmls+="<td id='"+data[i].userNum+"d'>"+data[i].username+"</td>";

        htmls+="<td class='center_td'><div class='message_div'><a href='#mymodal1' data-toggle='modal'>";
        htmls+="<span onclick=commonAjax('"+dataUrl+"','userNum="+data[i].userNum+"','bianji','GET')>编辑</span>";
        htmls+="</a><span class='space'>|</span><a>";
        htmls+="<span onclick=sub("+data[i].userNum+")>提交</span>";
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
 htmlss+="<div class='span3'>所属学院</div>";
 htmlss+="<div class='span3'><input type='text' name='' id='college' value='"+data.college+"' ></div>";
 htmlss+="<div class='span3'>班级</div>";
 htmlss+="<div class='span3'><input type='text' name='' id='clazz' value='"+data.clazz+"'></div>";
 htmlss+="</div>";
 htmlss+="<div class='row-form clearfix'>";
 htmlss+="<div class='span3'>学号</div>";
 htmlss+="<div class='span3'><input type='text' name='' id='userNum' value='"+data.userNum+"'></div>";
 htmlss+="<div class='span3'>姓名</div>";
 htmlss+="<div class='span3'><input type='text' name='' id='userName' value='"+data.userName+"'></div>";
 htmlss+="</div>";
 htmlss+="</div>";
 htmlss+="<div class='dr'><span></span></div>";
 htmlss+="</div>";
 htmlss+="</div>";
 htmlss+="div class='modal-footer'>";
 htmlss+="<button class='btn btn-primary' data-dismiss='modal' aria-hidden='true' onclick=save()>保存</button>";
 htmlss+="<button class='btn btn-default' data-dismiss='modal' aria-hidden='true'>关闭</button>";
 htmlss+="div>";

$("#mymodal1").html(htmlss);

}

function save(){
  var Data={};

  Data.college=$("#college").val();
  Data.clazz=$("#clazz").text();
  Data.userNum=$("#userNum").val();
  Data.userName=$("#userName").val();

  Data.status="";//保存状态码
  $.ajax({
    type : "POST",
    url : updateUser,
    data : Data,
    success : function(result) {
      alert("已保存")
  }
});
}

function sub(id){
 var Data={};
 Data.college=$("#"+id+"a").text();
 Data.clazz=$("#"+id+"b").text();
 Data.userNum=$("#"+id+"c").text();
 Data.username=$("#"+id+"d").text();

 Data.status="";//提交的状态码
   $.ajax({
    type : "POST",
    url : saveUrl,
    data : Data,
    success : function() {
      alert("已提交")
  }
});

}