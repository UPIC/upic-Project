/**
 * 素拓币商城
 * 
 * @authors Marte (iqianduan@126.com)
 * @date 2017-09-18 13:53:25
 * @version $Id$
 */

var page = 1;
var pageCount = -1;
var getAllUrl = "/common/getPrize";// 商品信息地址
var getPicUrl = "/stu/getBanner";// banner图地址
var getCoinUrl = "/stu/getGrainCoin";// 获取素拓币地址
var types = "GET";
$(function() {
	/**
	 * 获取轮播图地址
	 */
	ajaxs("", "getPic", getPicUrl)
	/*
	 * h获取当前用户积分
	 */
	getCoin();

	/**
	 * 获取所有商品
	 */
	ajaxs("status=SHELVES", "getAll", getAllUrl)
})

function getCoin() {
	$.ajax({
		type : "GET", // 提交方式
		url : getCoinUrl,// 路径
		success : function(result) {// 返回数据根据结果进行相应的处理
			$("#getCoin").html("当前素拓币：" + result);
		}
	});
}
function ajaxs(datas, method, urls) {
	if (pageCount == page || pageCount == 0) {
		return;
	}
	$.ajax({
		type : types, // 提交方式
		url : urls,// 路径
		data : datas,
		beforeSend : function(XMLHttpRequest) {
			// progress.inc();
		},
		success : function(result) {// 返回数据根据结果进行相应的处理
			pageCount = result.total;
			var datas = result.content;
			addHtmls(datas, method)
		},
		complete : function(XMLHttpRequest, textStatus) {
			// progress.done(true);
		},
		error : function() {
			// progress.done(true);
		}
	});
}

function addHtmls(result, method) {
	var htmls = "";
	if (method == "getAll") {
		for (var i = 0; i < result.length; i++) {
			htmls += "<li>";
			htmls += "<div class='tab-left'>";
			htmls += "<img src='" + result[i].prizePic + "'>";
			htmls += "</div>";
			htmls += "<div class='tab-right'>";
			htmls += "<div class='right-name'>";
			htmls += subMyStr(result[i].prizeName);

			htmls += "<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#myModal'>";
			htmls += "兑换";
			htmls += "</button>";

			htmls += "<div class='modal fade' id='myModa' tabindex='-1' role='dialog' aria-labelledby='myModalLabel'>";
			htmls += "<div class='modal-dialog' role='document'>";
			htmls += "<div class='modal-content'>";
			htmls += "<div class='modal-header'>";
			htmls += "<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>";
			htmls += "<h4 class='modal-title' id='myModalLabe'>提示</h4>";
			htmls += "</div>";
			htmls += "<div class='modal-body'>";
			htmls += "兑换成功";
			htmls += "</div>";
			htmls += " <a href='st-main.html'>";
			htmls += "<div class='modal-footer'>";
			htmls += "<button type='button' class='btn btn-default' data-dismiss='modal' onclick='duihuan("
					+ result[i].id + ")'>确定</button>";
			htmls += "</div>";
			htmls += " </a>";
			htmls += "</div>";
			htmls += "</div>";
			htmls += "</div>";
			htmls += " </div>";
			htmls += "<div class='right-apply'>";
			htmls += "总计: <span>" + result[i].score + "</span> 分";
			htmls += "</div>";
			htmls += "</div>";
			htmls += "</li>";
		}
		if (page == 1) {
			$("#" + method).html(htmls);
		} else {
			$("#" + method).append(htmls);
		}
	}

	if (method == "getPic") {
		for (var i = 0; i < result.length; i++) {
			if (i === 0) {
				htmls += "<div class='item active'>";
			} else {
				htmls += "<div class='item'>";
			}
			htmls += "<img src='" + result[i].url + "'";
			htmls += "</div>";
		}
		$("#" + method).html(htmls);
	}
}

function subMyStr(str) {
	if (str.length > 6) {
		str = str.substring(0, 6);
		str += "...";
	}
	return str;
}

function duihuan(prizeId) {
	$.ajax({
		type : types, // 提交方式
		url : '/stu/getExchangePrize',// 路径
		data : {
			id : prizeId
		},
		success : function(result) {// 返回数据根据结果进行相应的处理
			alert("已兑换");
			getCoin();
		}
	});
}

/** 滚动条* */
var totalheight = 0;// 定义一个总的高度变量
$(window).scroll(
		function() {
			totalheight = parseFloat($(window).height())
					+ parseFloat($(window).scrollTop());// 浏览器的高度加上滚动条的高度
			if ($(document).height() <= totalheight) // 当文档的高度小于或者等于总的高度的时候，开始动态加载数据
			{
				page++;
				ajaxs('status=SHELVES&size=10&page=' + page, "getAll",
						getAllurl);

			}
		});