/**
 * 获取积分
 */
var getIntegeralUrl="http://088c1452.ngrok.io/stu/getIntegeral";
var getGrainCoinUrl="http://088c1452.ngrok.io/stu/getGrainCoin";
$.ajax({
	type : "GET", // 提交方式
	url : getIntegeralUrl,// 路径
	success : function(result) {// 返回数据根据结果进行相应的处理
			$("#"+integeral).html(result);
	}
});

/**
 * 获取素拓币
 */
$.ajax({
	type : "GET", // 提交方式
	url : getGrainCoinUrl,// 路径
	success : function(result) {// 返回数据根据结果进行相应的处理
			$("#"+getGrainCoin).html(result);
	}
});