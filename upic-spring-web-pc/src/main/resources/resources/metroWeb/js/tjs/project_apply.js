var getAllProjectCategoryUrl = "/common/getAllProjectCategory";
var addProjectUrl = "/common/addProject";
var getAllCollegeUrl = "/common/getCollege";

var inputs = $("input");
function cancel() {
    inputs.each(function () {
        $(this).val("");
    });
}

function saveProject() {
    var Data = {};

    Data.creatTime = $("#creatTime").val();
    Data.projectCategory = $("#getAllProjectCategory option:selected").text();
    Data.projectCategoryId = $("#getAllProjectCategory option:selected").attr("id");

    Data.declareUnit = $("#declareUnit option:selected").text();
    Data.collegeOtherName = $("#declareUnit option:selected").attr("id");

    Data.projectName = $("#projectName").val();
    Data.guidanceMan = $("#guidanceMan").val();

    Data.integral = $("#integral").val();
    Data.maximum = $("#maximum").val();

    Data.startTime = $("#startTime").val();
    Data.endTime = $("#endTime").val();

    Data.signUpStartTime = $("#signUpStartTime").val();
    Data.signUpEndTime = $("#signUpEndTime").val();

    Data.content = $("#content").val();
    Data.projectImplementationProcess = $("#projectImplementationProcess").val();
    Data.checkAssessmentCriteraAndForm = $("#checkAssessmentCriteraAndForm").val();

    var str = JSON.stringify(Data);

    $.ajax({
        type: "POST",
        url: addProjectUrl,
        data: {
            projectInfo: str
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (result) {
            alert("已保存");
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

$(function () {
    $.ajax({
        type: "GET",
        url: getAllProjectCategoryUrl,
        success: function (result) {
            var data = result.content;
            var htmls = "";
            htmls += "<option value='category 1'>--请选择项目类别--</option>";

            for (var i = 0; i < data.length; i++) {
                htmls += "<option value='category 1' id='" + data[i].id + "'>" + data[i].categoryName + "</option>";
            }
            $("#getAllProjectCategory").html(htmls);
        }
    });

    $.ajax({
        type: "GET",
        url: getAllCollegeUrl,
        success: function (result) {
            var data = result.content;
            var htmls = "";
            for (var i = 0; i < data.length; i++) {
                htmls += "<option value='category 1' id='" + data[i].otherName + "'>" + data[i].college + "</option>";
            }
            $("#declareUnit").html(htmls);
        }
    });

    $('#integral').bind('input propertychange', function () {
        var integral = $("#integral").val();
        var maximum = $("#maximum").val();
        if (integral == 'NaN' && maximum == 'NaN') {
            $("#gzl").html("");
        } else {
            $("#gzl").html(integral * maximum);
        }
    })

    $('#maximum').bind('input propertychange', function () {
        var integral = $("#integral").val();
        var maximum = $("#maximum").val();
        if (integral == 'NaN' && maximum == 'NaN') {
            $("#gzl").html("");
        } else {
            $("#gzl").html(integral * maximum);
        }
    })
})



