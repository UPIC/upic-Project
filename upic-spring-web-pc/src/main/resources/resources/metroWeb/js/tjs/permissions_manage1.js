var data1Url = "";
var chakanUrl="";
var xiugaiUrl="";
var shezhimimaUrl="";
var dongjieUrl="";
var saveUserInfoUrl="";
var saveUserStatusUrl="";
var pageSize = 0;
var totalPages=-1;
var pageNum=0;
var requestData={

};

$(function(){
    pageSize=$("#select-small").children('option:selected').text()
    getData(pageNum,data1Url);
    registSelect(userType);
    registSelect(userStatus);
})


function addHtmls(datas,pageNum) {
    totalPages=datas.totalElements;
    var data=datas.content;
    var htmls="";
    for(var i=0;i<data.length;i++){
        htmls+="<tr><td><input type='checkbox' class='checkboxes' value='1' id='"+data[i].userNum+"'/></td>";
        htmls+="<td class='center_td'>"+(parseInt(pageNum)*parseInt(pageSize)+i+1)+"</td>";
        htmls+="<td>"+data[i].userNum+"</td>";
        htmls+="<td>"+data[i].userName+"</td>";
        htmls+="<td>"+data[i].userStatus+"</td>";
        htmls+="<td>"+data[i].userType+"</td>";
        htmls+="<td>";
        htmls+="<div class='message_di'>";
        htmls+="<a href='#mymodal2' data-toggle='modal'><span onclick=commonAjax('"+chakanUrl+"','userNum="+data[i].userNum+"','chakan','GET')>【查看】</span></a>";
        htmls+="<span class='space'>|</span>";
        htmls+="<a href='#mymodal3' data-toggle='modal'><span onclick=commonAjax('"+chakanUrl+"','userNum="+data[i].userNum+"','xiugai','GET')>【修改】</span></a>";
        htmls+="<span class='space'>|</span>";
        htmls+="<a href='#mymodal' data-toggle='modal'><span onclick=shezhimima("+data[i].userNum+","+data[i].userName+")>【重置密码】</span></a>";
        htmls+="<span class='space'>|</span>";
        htmls+="<a href='#mymodal5' data-toggle='modal'><sapn onclick=dongjie("+data[i].userNum+","+data[i].userName+")>【冻结】</sapn></a>";
        htmls+="</div></td>";
        htmls+="</tr>";
    }
    $("#data").html(htmls);
    page(datas,data1Url,datas.size,datas.number);
}


function chakan(data){
    var htmlss="";
    var statuss="";
    if (true) {statuss=""};
    htmlss+="<div class='control-group'>";
    htmlss+="<label class='control-label'>操作员姓名：</label>";
    htmlss+="<div class='controls'>";
    htmlss+="<input type='text' class='input-medium' placeholder='"+data.userName+"' disabled />";
    htmlss+="</div>";
    htmlss+="</div>";
    htmlss+="<div class='control-group'>";
    htmlss+="<label class='control-label'>操作员工号</label>";
    htmlss+="<div class='controls'>";
    htmlss+="<input type='text' class='input-medium' placeholder='"+data.userNum+"' disabled/>";
    htmlss+="</div>";
    htmlss+="</div>";
    htmlss+="<div class='control-group'>";
    htmlss+="<label class='control-label'>创建时间</label>";
    htmlss+="<div class='controls'>";
    htmlss+="<span>"+getDate(data.createTime)+"</span>";
    htmlss+="</div>";
    htmlss+="</div>";
    htmlss+="<div class='control-group'>";
    htmlss+="<label class='control-label'>状态</label>";
    htmlss+="<div class='controls'>";
    htmlss+="<span>"+statuss+"</span>";
    htmlss+="</div>";
    htmlss+="</div>";
    htmlss+="<div class='control-group'>":
    htmlss+="<label class='control-label'>角色</label>";
    htmlss+="<div class='controls'>";
    htmlss+="<span>"+data.userType+"</span>";
    htmlss+="</div>";
    htmlss+="</div>";

    $("#getUserInfo").html(htmlss);
}

function xiugai(data){
    var htmlss="";
    var statuss="";
    if (true) {statuss=""};
    htmlss+="<div class='modal-header'>";
    htmlss+="<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
    htmlss+="<h4>修改信息</h4>";
    htmlss+="</div>";
    htmlss+="<div class='modal-body'>";
    htmlss+="<form action='#'' class='form-horizontal'>";
    htmlss+="<div class='control-group'>";
    htmlss+="<label class='control-label'>操作员姓名：</label>";
    htmlss+="<div class='controls'>";
    htmlss+="<input type='text' id='"+data.userNum+"name' class='input-medium' value='"+data.userName+"'  />";
    htmlss+="</div>";
    htmlss+="</div>";
    htmlss+="<div class='control-group'>";
    htmlss+="<label class='control-label'>操作员工号</label>";
    htmlss+="<div class='controls'>";
    htmlss+="<input type='text' id='"+data.userNum+"num' class='input-medium' value='"+data.userNum+"' />";
    htmlss+="</div>";
    htmlss+="</div>";
    htmlss+="<div class='control-group'>";
    htmlss+="<label class='control-label'>创建时间</label>";
    htmlss+="<div class='controls'>";
    htmlss+="<span id='"+data.userNum+"time'>"+getDate(data.createTime)+"</span>";
    htmlss+="</div>";
    htmlss+="</div>";
    htmlss+="<div class='control-group'>";
    htmlss+="<label class='control-label'>状态</label>";
    htmlss+="<div class='controls'>";
    htmlss+="<span id='"+data.userNum+"status'>"+statuss+"</span>";
    htmlss+="</div>";
    htmlss+="</div>";
    htmlss+="<div class='control-group'>":
    htmlss+="<label class='control-label'>角色</label>";
    htmlss+="<div class='controls'>";
    htmlss+="<span id='"+data.userNum+"type'>"+data.userType+"</span>";
    htmlss+="</div>";
    htmlss+="</div>";
    htmlss+="<fieldset class='fild1'>";
    htmlss+="<legend style='font-size: 12px;'>选择角色</legend>";
    htmlss+="<div class='controls controls_change'>";
    htmlss+="<label class='checkbox'>";
    htmlss+="<input type='checkbox' value=''/>  学生";
    htmlss+="</label>";
    htmlss+="<label class='checkbox'>";
    htmlss+="<input type='checkbox' value=''/> 教师";
    htmlss+="</label>";
    htmlss+="<label class='checkbox'>";
    htmlss+="<input type='checkbox' value=''/> 管理员";
    htmlss+="</label>";
    htmlss+="<label class='checkbox'>";
    htmlss+="<input type='checkbox' value=''/> 超级管理员";
    htmlss+="</label>";
    htmlss+="</div>";
    htmlss+="</fieldset>";
    htmlss+="</form>";
    htmlss+="</div>";
    htmlss+="<div class='modal-footer'>";
    htmlss+="<button class='btn btn-primary' data-dismiss='modal' onclick=saveUserInfo("+data.userNum+")>保存</button>";
    htmlss+="<button class='btn' data-dismiss='modal' aria-hidden='true'>关闭</button>";
    htmlss+="/div>";
    $("#changeUserInfo").html(htmlss);
    if (data.userType==="STUDENT") {};


}

function saveUserInfo(id){
    var Data={

    };
    Data.userName=$("#"+id+"name").text();
    Data.userNum=$("#"+id+"num").text();
    Data.createTime=$("#"+id+"time").text();
    Data.status=$("#"+id+"status").text();
    Data.userType=$("#"+id+"type").text();

    $.ajax({
        type : "GET",
        url : saveUserInfoUrl,
        data : Data,
        success : function(result) {
          alert("已保存")
      }
  });

}

function shezhimima(usernum,username){
    var html="";
    htmlss+="<div class='modal-header'>";
    htmlss+="<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
    htmls+="<h4>重置密码</h4>";
    htmls+="</div>";
    htmlss+="<div class='modal-body'>";
    htmlss+="<form action='#'' class='form-horizontal'>";
    htmlss+="<div class='control-group'>";
    htmlss+="<label class='control-label'>操作员姓名：</label>";
    htmlss+="<div class='controls'>";
    htmlss+="<input type='text' class='input-medium' value='"+username+"' disabled />";
    htmlss+="</div>";
    htmls+="</div>";
    htmlss+="<div class='control-group'>";
    htmlss+="<label class='control-label'>操作员工号</label>";
    htmlss+="<div class='controls'>";
    htmlss+="<input type='text' class='input-medium' value='"+usernum+"' />";
    htmlss+="</div>";
    htmlss+="</div>";
    htmls+="<div class='control-group'>";
    htmls+="<label class='control-label'>新密码</label>";
    htmls+="<div class='controls'>";
    htmls+="<input type='password' class='input-medium' id='"+usernum+"apassword'/>";
    htmls+="</div>";
    htmls+="</div>";
    htmls+="<div class='control-group'>";
    htmls+="<label class='control-label'>确认新密码</label>";
    htmls+="<div class='controls'>";
    htmls+="<input type='password' class='input-medium' id='"+usernum+"bpassword'/>";
    htmls+="</div>";
    htmls+="</div>";
    htmls+="</form>";
    htmls+="</div>";
    htmls+="<div class='modal-footer'>";
    htmls+="<button class='btn btn-primary' data-dismiss='modal' onclick=savePassWord("+usernum+")>保存</button>":
    htmls+="<button class='btn' data-dismiss='modal' aria-hidden='true'>关闭</button>";
    htmls+="</div>";
    $("#changepassword").html(htmls);
    var aPW=$("#"+usernum+"apassword").val();
    var bPW=$("#"+usernum+"bpassword").val();
    if (aPW!=bPW) {
        alert("两次输入的密码不一致,请重新输入");
        $("#"+usernum+"apassword").attr("value","");
        $("#"+usernum+"bpassword").attr("value","");
    };

}

function savePassWord(usernum){
    var bPW=$("#"+usernum+"bpassword").val();
    var Data={};
    Data.userNum=usernum;
    Data.password=bPW;
    $.ajax({
        type : "GET",
        url : savePassWordUrl,
        data : Data,
        success : function(result) {
          alert("已保存")
      }
  });
}

function dongjie(usernum,username){
    var htmls="";
    htmls+="<div class='modal-header'>";
    htmls+="<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
    htmls+="<h4>确认提示</h4>";
    htmls+="</div>";
    htmls+="<div class='modal-body'>";
    htmls+="<form action='#'' class='form-horizontal'>";
    htmls+="<p>冻结<span>"+username+"</span>?</p>";
    htmls+="</form>";
    htmls+="</div>";
    htmls+="<div class='modal-footer'>";
    htmls+="<button class='btn btn-primary' data-dismiss='modal' onclick=changeUserStatus("+usernum+")>确定</button>";
    htmls+="<button class='btn' data-dismiss='modal' aria-hidden='true'>取消</button>";
    htmls+="</div>";
    $("#changeUserStatus").html(htmls);
}

function changeUserStatus(usernum){
    var Data={};
    Data.userNum=usernum;
    Data.status="DONGJIE";
    $.ajax({
        type : "GET",
        url : saveUserStatusUrl,
        data : Data,
        success : function(result) {
          alert("已冻结")
      }
  });
}