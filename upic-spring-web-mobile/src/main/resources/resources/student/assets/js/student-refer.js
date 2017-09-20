/**
 * 获取积分
 */
 var page = 1;
 var pageCount=-1;
 var getAllurl = "https://088c1452.ngrok.io/stu/getProject";
 var getPerson="https://088c1452.ngrok.io/stu/getPageIntegral";
 var types = "GET";
 $(function(){
 	ajaxs("","home",getAllurl)
	/**
 * 获取当前可见的活动
 *
 * @returns
 */
 $(".aa").click(function() {
 	juge($(this));
 	ajaxs("","home",getAllurl)

 })
 $(".bb").click(function() {
 	juge($(this));
 	ajaxs("","profile",getPerson)

 })

 $(".cc").click(function() {
 	juge($(this));
 	ajaxs("","messages",getPerson)

 })
})

 function juge(obj) {
 	if (obj.hasClass("active")) {
 		return;
 	}
 	page=1;
 	pageCount=-1;
 }

 function ajaxs(datas,method,urls){
 	if(pageCount==page || pageCount==0){
 		return;
 	}
 	$.ajax({
		type : types, // 提交方式
		url : urls,// 路径
		data:{
			datas
		},
		beforeSend : function(XMLHttpRequest) {
// progress.inc();
},
		success : function(result) {// 返回数据根据结果进行相应的处理
			pageCount=result.total;
			var datas=result.content;
			addHtmls(datas,method)
		},
		complete : function(XMLHttpRequest, textStatus) {
// progress.done(true);
},
error : function() {
// progress.done(true);
}
});
 }

 function addHtmls(result,method){
 	var htmls="";
 	if(method=="home"){
 		for(var i=0;i<result.length;i++){
 			htmls+="<div class='act-div'><div class='tab-left'><img src='assets/i/b-3.jpg' alt=''></div><div class='tab-right'>";
 			htmls+="<div class='tab-title'><div class='right-name'>";
 			htmls+=result[i].projectName;
 			htmls+="</div>";
 			htmls+="<a href='st-detail.html?projectNum="+result[i].projectNum+"'><div class='right-apply'>报名</div> </a>";
 			htmls+="</div><div class='tab-text'>";
 			htmls+="<span>活动时间："+getDate(result[i].startTime,"yy-MM-dd hh:mm")+"-"+getDate(result[i].endTime,"yy-MM-dd hh:mm")+"</span><br> <span>报名截止时间："+getDate(result[i].signUpEndTime,"yy-MM-dd hh:mm")+"</span><br>";
 			htmls+="<span>预获积分："+result[i].integral+"</span></div>";
 			htmls+="<a href='st-detail.html?projectNum="+result[i].projectNum+"'><div class='tab-search'>查看详情 ></div> </a></div></div>";
 		}
 	}
 	else{
 		for(var i=0;i<result.length;i++){
 			htmls+="<div class='act-div'><div class='tab-left'><img src='assets/i/b-3.jpg' alt=''></div><div class='tab-right'>";
 			htmls+="<div class='tab-title'><div class='right-name'>";
 			htmls+=result[i].projectName;
 			htmls+="</div>";
 			htmls+="</div><div class='tab-text'>";
 			htmls+="<span id='"+result[i].integralLogId.projectNum+"'>活动时间：</span><br> ";
 			htmls+="<span>预获积分："+result[i].integral+"</span><span class='red'>" ;
 			if(method=="profile"){
 				htmls+="报名成功";
 			}else{
 				htmls+="已完成";
 			}
 			htmls+=	"</span></div>";
 			htmls+="<a href='st-detail.html?projectNum="+result[i].integralLogId.projectNum+"'><div class='tab-search'>查看详情 ></div> </a></div></div>";
 		}
 	}
 	if(page==1){
 		$("#"+method).html(htmls);
 	}else{
 		$("#"+method).append(htmls);
 	}
 }

 function getDate(date,rule){
 	var date = new Date(date);
 	var dateStr = date.pattern(rule);
 	return dateStr;
 }
/**
 * 只满足获取项目时间
 *
 * @returns
 */
 function getProjectTime(projectNUm){
 	$.ajax({
 		url:'/stu/getProjectInfo',
	    type:'GET', // GET
	    data:{
	    	projectNum:projectNUm
	    },
	    dataType:'json',    // 返回的数据格式：json/xml/html/script/jsonp/text
	    beforeSend:function(xhr){
	    },
	    success:function(data){
	    	var str= getDate(data.startTime,"yy.MM.dd hh:mm")+"-"+getDate(data.startTime,"yy.MM.dd hh:mm");
	    	$("#"+projectNUm).html("活动时间："+str);
	    },
	    error:function(xhr,textStatus){
	    },
	    complete:function(){
	    }
	})
 }

 Date.prototype.pattern = function(fmt) {
 	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
};
var week = {
	"0" : "/u65e5",
	"1" : "/u4e00",
	"2" : "/u4e8c",
	"3" : "/u4e09",
	"4" : "/u56db",
	"5" : "/u4e94",
	"6" : "/u516d"
};
if (/(y+)/.test(fmt)) {
	fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
		.substr(4 - RegExp.$1.length));
}
if (/(E+)/.test(fmt)) {
	fmt = fmt
	.replace(
		RegExp.$1,
		((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f"
			: "/u5468")
		: "")
		+ week[this.getDay() + ""]);
}
for ( var k in o) {
	if (new RegExp("(" + k + ")").test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
			: (("00" + o[k]).substr(("" + o[k]).length)));
	}
}
return fmt;
}