/*
 * @Author: Marte
 * 积分申报
 * @Date:   2017-09-24 10:17:37
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-11-06 19:58:00
 */

/*
 获取项目类别，级别
 */

 var getAllProjectCategory = "/common/getAllProjectCategoryList";
 var getCategoryNodeByFatherId="/common/searchCategoryNodeList";
 var submitUrl="";
 var types = "GET";

 $(function () {
    ajaxs("", "getProjectCategory", getAllProjectCategory);
    $("#projectCategory").change( function() { getNextCategory(); });
})

 function ajaxs(datas, method, urls) {
    $.ajax({
        type: types, // 提交方式
        url: urls,// 路径
        data: datas,
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (result) {// 返回数据根据结果进行相应的处理
            method(result)
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

function getProjectCategory(res) {
    var data = res;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='"+data[i].id+"' name='"+i+"'>" + data[i].categoryName + "</option>";
    }
    $("#projectCategory").html(htmls);
}


function getNextCategory(){
    var id=$("#projectCategory").find("option:selected").val();
    ajaxs('level=1&id='+id, "getNextProjectCategory", getCategoryNodeByFatherId);
}

function getNextCategory2(){
    var id=$("#projectCategory").find("option:selected").val();
    var isLeaf=$("#projectCategory option:selected").attr("name");
    var nodeContent=$("#projectCategory option:selected").attr("data");
    if (isLeaf==1) {
        $("#integral").attr("placeholder",nodeContent)
    }else{
        ajaxs('id='+id, "getNextProjectCategory", getCategoryNodeByFatherId);
    }
}

function getNextProjectCategory(data){
    var htmls = "";
    htmls+="<select class='input-medium m-wrap' tabindex='1' id='"+data[0].id+"'>";
    for (var i = 0; i < data.length; i++) {
        htmls += "<option value='"+data[i].id+"' name='"+data[i].isLeaf+"' data='"+data[i].nodeContent+"'>" + data[i].categoryName + "</option>";
    }
    htmls += "</select>";
    $("#getNext").html(htmls);
    $("#"+data[0].id).change( function() { getNextCategory2(); });

}

function submit(){
    var Data={

    };
    var str="";
    $("select option:selected").each(function(){
        str+=$(this).val()+"/";
    });
    str=str.slice(0,-1);
    Data.projectName=$("#projectName").val();
    Data.projectCategory=$("#projectCategory option:selected").val();
    Data.event=str;
    Data.integral=$("#integral").attr('placeholder');
    // Data.file=$("#file-0a")
     $.ajax({
        type: 'GET',
        url: submitUrl,
        data: Data,
        success: function (result) {
           alert("已提交")
        }
    });
}

function reset(){
    $("#projectName").val("");
    $("#projectCategory  option[name='0'] ").attr("selected",true);
    $("#getNext").html("");
    $("#integral").attr('placeholder','');
}

