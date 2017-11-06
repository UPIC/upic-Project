var getAllProjectCategory = "/common/getAllProjectCategory";//获取项目类别
var submitUrl="";
var types = "GET";

$(function () {
    ajaxs("", "getProject", getAllProjectCategory);
})

function ajaxs(datas, method, urls) {
    // if (pageCount == page || pageCount == 0) {
    //     return;
    // }
    $.ajax({
        type: types, // 提交方式
        url: urls,// 路径
        data: datas,
        beforeSend: function (XMLHttpRequest) {
// progress.inc();
        },
        success: function (result) {// 返回数据根据结果进行相应的处理
            var datas = result.content;
            addHtmls(datas, method)
        },
        complete: function (XMLHttpRequest, textStatus) {
// progress.done(true);
        },
        error: function () {
// progress.done(true);
        }
    });
}

function addHtmls(result, method) {
    var htmls = "";
    if (method == "getProject") {
        for (var i = 0; i < result.length; i++) {
            htmls += "<option value='category "+(i+1)+"'>"+result[i].categoryName+"</option>";
        }
        $("#getProject").html(htmls);
    }
}
//提交
function submit(){
    var Data={};
    Data.shengbaoTime=$("#shengbaoTime").val();
    Data.gzl=$("#gzl").val();
    Data.shengbaodanwei=$("#shengbaodanwei").val();
    Data.zhidaoren=$("#zhidaoren").val();
    Data.getProject=$("#getProject option:selected").text();
    Data.projectName=$("#projectName").val();
    Data.integral=$("#integral").val();
    Data.maximum=$("#maximum").val();
    Data.startTime=$("#startTime").val();
    Data.endTime=$("#endTime").val();
    Data.content=$("#content").val();
    Data.xiangmujincheng=$("#xiangmujincheng").val();
    Data.checkAssessmentCriteraAndForm=$("#checkAssessmentCriteraAndForm").val();

    $.ajax({
        type: 'GET',
        url: submitUrl,
        data: Data,
        success: function (result) {
           alert("已提交")
        }
    });

}

 var newDate = new Date();
 var str = "" + newDate.getFullYear() + "-";
   str += (newDate.getMonth()+1) + "-";
   str += newDate.getDate();
  var t= newDate.toJSON();

    $('#datetimepicker,#datetimepicker1,#datetimepicker2').datetimepicker({
    format: 'yyyy-mm-dd',
    minView: 'month',
    language:  'zh-CN',
    todayBtn:  1,
    autoclose: 1,
    startDate:new Date(t),
    todayHighlight: 1,
    todayBtn: true,
});
    $(".nowdate").val(str);


function reset(){
    $("#gzl").val("");
    $("#shengbaodanwei").val("");
    $("#zhidaoren").val("");
    $("#getProject option:selected").text();
    $("#projectName").val("");
    $("#integral").val("");
    $("#maximum").val("");
    $("#content").val("");
    $("#xiangmujincheng").val("");
    $("#checkAssessmentCriteraAndForm").val("");
    $(".nowdate").val(str);
    $("#getProject  option[value='category 1'] ").attr("selected",true);
}
