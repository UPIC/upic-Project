var getAllProjectCategoryUrl="/common/getAllProjectCategory";
var addProjectUrl="/common/addProject";


var inputs = $("input");
function cancel(){
    inputs.each(function(){
    $(this).val("");
 });
}

function saveProject(){
   $.ajax({
    type : "POST",
    url : addProjectUrl,
    data : $("#commentform").serialize(),
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
    $("#getAllProjectCategory").html()
})