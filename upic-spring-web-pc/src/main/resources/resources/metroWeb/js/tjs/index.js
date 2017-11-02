var getResourceBySelfUrl = "/operator/getResourceBySelf";

$(function() {
	commonAjax(getResourceBySelfUrl, "", "getFirstResourceBySelf", "GET");
})

function getFirstResourceBySelf(datas) {
	var htmls = "";

	for (var i = 0; i < datas.length; i++) {
		if (datas[i].level <= 1) {
			htmls += "<li class='sub-menu ";
			if (i == 0) {
				htmls += "active";
			}
			htmls+="'>";
			htmls += "<a href='javascript:;'>";
			htmls += "<i class='icon-dashboard'></i>";
			htmls += "<span>" + datas[i].resourceName + "</span>";
			htmls += "<span class='arrow'></span>";
			htmls += "</a>";
			htmls += "<ul class='sub'";
			if (i == 0) {
				htmls += "'style='display: none;'";
			}
			htmls+=">";
			for (var j = 0; j < datas.length; j++) {
				if (datas[j].fatherId === datas[i].id) {
					htmls += "<li><a class='' href='" + datas[j].url
							+ "' target='myFrameName'>" + datas[j].resourceName
							+ "</a></li>";
				}
			}
			htmls += "</ul></li>";
		}
	}
	$("#getResourceBySelfId").html(htmls);
	$('.sidebar-menu>li').click(function(e) {
		$('.sidebar-menu>li').removeClass('active');
		$(this).addClass('active');
	});
	// function treeMenu(a){
	// this.tree=a||[];
	// this.groups={};
	// };
	// treeMenu.prototype={
	// init:function(pid){
	// this.group();
	// return this.getDom(this.groups[pid]);
	// },
	// group:function(){
	// for(var i=0;i<this.tree.length;i++){
	// if(this.groups[this.tree[i].pId]){
	// this.groups[this.tree[i].pId].push(this.tree[i]);
	// }else{
	// this.groups[this.tree[i].pId]=[];
	// this.groups[this.tree[i].pId].push(this.tree[i]);
	// }
	// }
	// },
	// getDom:function(a){
	// if(!a){return ''}
	// var html='\n<ul >\n';
	// for(var i=0;i<a.length;i++){
	// html+='<li><a href="#">'+a[i].name+'</a>';
	// html+=this.getDom(this.groups[a[i].id]);
	// html+='</li>\n';
	// };
	// html+='</ul>\n';
	// return html;
	// }
	// };
	// var html=new treeMenu(datas).init(0);
	// alert(html);
}
