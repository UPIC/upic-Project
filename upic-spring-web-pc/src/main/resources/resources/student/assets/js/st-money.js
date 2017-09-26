/*
* @Author: Marte
* 我的素拓币
* @Date:   2017-09-20 13:05:18
* @Last Modified by:   Marte
* @Last Modified time: 2017-09-20 18:56:31
*/

var getStudentInfoUrl="/common/getUserInfo";
var getStudentCoinUrl="/stu/getGrainCoin";

 /*
  获取头像,姓名
   */
   $.ajax({
    type : "GET", // 提交方式
    url : getStudentInfoUrl,// 路径
    success : function(result) {// 返回数据根据结果进行相应的处理
        $("#stinfo").html( "<img src='"+result.pic+"'><span>"+result.username+"</span>");
    }
});
/*
  获取积分
  */
  $.ajax({
    type : "GET", // 提交方式
    url : getStudentCoinUrl,// 路径
    success : function(result) {// 返回数据根据结果进行相应的处理
        $("#stcoin").html(result);
    }
});