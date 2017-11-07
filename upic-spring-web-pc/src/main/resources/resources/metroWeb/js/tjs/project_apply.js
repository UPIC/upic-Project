var getAllProjectCategoryUrl="/common/getAllProjectCategory";
var addProjectUrl="/common/addProject";
var getAllCollegeUrl="";

var inputs = $("input");
function cancel(){
    inputs.each(function(){
        $(this).val("");
    });
}

function saveProject(){
    var Data={};

    Data.creatTime=$("#creatTime").text();
    Data.projectCategory=$("#getAllProjectCategory option:selected") .val();
    Data.declareUnit=$("#declareUnit option:selected") .val();
    Data.projectName=$("#projectName").val();
    Data.guidanceMan=$("#guidanceMan").val();

    Data.integral=$("#integral").val();
    Data.maximum=$("#maximum").val();

    Data.startTime=$("#startTime").val();
    Data.endTime=$("#endTime").val();

    Data.signUpStartTime=$("#signUpStartTime").val();
    Data.signUpEndTime=$("#signUpEndTime").val();


    Data.content=$("#content").val();
    Data.implementationProcess=$("#implementationProcess").val();
    Data.checkAssessmentCriteraAndForm=$("#checkAssessmentCriteraAndForm").text();
    $.ajax({
        type : "POST",
        url : addProjectUrl,
        data : Data,
        beforeSend : function(XMLHttpRequest) {
        },
        success : function(result) {
            alert("已保存");
        },
        complete : function(XMLHttpRequest, textStatus) {
        },
        error : function() {
        }
    });
}

$(function(){

    $.ajax({
        type : "GET",
        url : getAllProjectCategoryUrl,
        success : function(result) {
            var data=res.content;
            var htmls="";
            htmls+="<option value='category 1'>--请选择项目类别--</option>";

            for(var i=0;i<data.length;i++){
                htmls+="<option value='category 1'>"+data[i].categoryName+"</option>";
            }
        }
    });
    $("#getAllProjectCategory").html();


    $.ajax({
        type : "GET",
        url : getAllCollegeUrl,
        success : function(result) {
            var data=res.content;
            var htmls="";
            for(var i=0;i<data.length;i++){
                htmls+="<option value='category 1'>"+data[i].collegeName+"</option>";
            }
        }
    });
    $("#getAllCollege").html();



    $('#integral').bind('input propertychange', function () {
        var integral=$("#integral").val();
        var maximum=$("#maximum").val();
        if (integral==NaN&&maximum==NaN) {
            $("#gzl").html("");
        }else{
            $("#gzl").html(integral*maximum);
        }
    }

    $('#maximum').bind('input propertychange', function () {
        var integral=$("#integral").val();
        var maximum=$("#maximum").val();
        alert(integral);
        if (integral==NaN&&maximum==NaN) {
            $("#gzl").html("");
        }else{
            $("#gzl").html(integral*maximum);
        }

    }
})



