/**
 * 获取积分
 */
$.ajax({
	type : "GET", // 提交方式
	url : "/stu/getIntegeral",// 路径
	success : function(result) {// 返回数据根据结果进行相应的处理
			$("#integeral").html(result);
	}
});

/**
 * 获取素拓币
 */
$.ajax({
	type : "GET", // 提交方式
	url : "/stu/getCrainCoin",// 路径
	success : function(result) {// 返回数据根据结果进行相应的处理
			$("#getCrainCoin").html(result);
	}
});