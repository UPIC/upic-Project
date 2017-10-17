/*
* @Author: Marte
* @Date:   2017-10-12 08:38:20
* @Last Modified by:   Marte
* @Last Modified time: 2017-10-13 10:21:46
*/

var getIntegralLogWithOutPass="/common/getIntegralLogWithOutPass";//获取积分列表（未通过审核人员）
var getAllProjectCategory="/common/getAllProjectCategory";//获取项目类别
var integralLogSearchBar="/common/integralLogSearchBar";//搜索条(String status=PENDING_AUDIT, String keyword)
var updateIntegralLogStatus="/common/updateIntegralLogStatus";//修改积分状态(List<String> studentNumList, List<String> projectNumList, IntegralLogStatusEnum status)

$(function () {
    /*
     加载数据
     */
    ajaxs("status=PENDING_AUDIT", "showAll", getIntegralLogWithOutPass)

})

function ajaxs(datas, method, urls) {
    $.ajax({
        type: GET, // 提交方式
        url: urls,// 路径
        data: datas,//

        beforeSend: function (XMLHttpRequest) {
// progress.inc();
        },
        success: function (result) {// 返回数据根据结果进行相应的处理
            addHtmls(result,method)
        },
        complete: function (XMLHttpRequest, textStatus) {
// progress.done(true);
        },
        error: function () {
// progress.done(true);
        }
    });
}

function addHtmls(result,method) {

    var htmls="";
    var htmlss="";



    if (method==="showAll") {
        for (var i=0;i<=result.content.length;i++) {
                                <tr>
                                <td><input type="checkbox" class="checkboxes" value="1" /></td>
                                    <td class="center_td">01</td>
                                    <td>信息工程学院</td>
                                    <td>1422110120</td>
                                    <td>14微社交</td>
                                    <td>XXX</td>
                                    <td>校级学生干部加分</td>
                                    <td>项目名称</td>
                                    <td>
                                    <a href="#mymodal1" data-toggle="modal"><div class="message_div">查看详情</div></a></td>
                                </tr>
                                <tr>

            htmls+="<tr>";
            htmls+="<td><input type='checkbox' class='checkboxes' value='1' /></td>";
            htmls+="<td class='center_td'>"+(i+1)+"</td>";
            htmls+="<td>"+result.content[i].college+"</td>";
            htmls+="<td>"+result.content[i].studentId+"</td>";
            htmls+="<td>"+result.content[i].className+"</td>";
            htmls+="<td>"+result.content[i].studentName+"</td>";
            htmls+="<td>"+项目类别+"</td>";
            htmls+="<td>"+result.content[i].projectName+"</td>";
            htmls+="<td>";
            htmls+="<a href='#mymodal1' data-toggle='modal'><div class='message_div' onclick='ajaxs('status=PENDING_AUDIT','details','getIntegralLogWithOutPass')>查看详情</div></a></td>";
            htmls+="</tr>";
        };
    $("#apply").html(htmls);
    }else{
         for (var i=0;i<=result.content.length;i++) {

            htmlss+="    <div class='row-form clearfix'>";
            htmlss+="     <div class='span3'>申请日期</div>";
            htmlss+="       <div class='span3'>"+result.content[i].startTime+"</div>";
            htmlss+="       <div class='span3'>姓名</div>";
            htmlss+="       <div class='span3'>"+result.content[i].userName+"</div>";
            htmlss+="  </div>";

            htmlss+=" <div class='row-form clearfix'>";
            htmlss+="   <div class='span3'>所属学院</div>";
            htmlss+="   <div class='span3'>"+result.content[i].college+"</div>";
            htmlss+="   <div class='span3'>班级</div>";
            htmlss+="   <div class='span3'>"+result.content[i].clazz+"</div>";
            htmlss+="  </div>";
            htmlss+="   <div class='row-form clearfix'>";
            htmlss+="     <div class='span3'>类别</div>";
            htmlss+="     <div class='span3'>"+项目类别+"</div>";
            htmlss+="     <div class='span3'>名称</div>";
            htmlss+="     <div class='span3'>"+reslut.content[i].projectName+"</div>";
            htmlss+="   </div>";
            htmlss+="    <div class='row-form clearfix'>";
            htmlss+="     <div class='span3'>详情</div>";
            htmlss+="    <div class='span9'>"+result.content[i].content+"</div>";
            htmlss+="  </div>";
            htmlss+=" <div class='row-form clearfix'>";
            htmlss+="    <div class='span3'>作证材料</div>";
            htmlss+="<div class='span9'><a class='tooltip1' href='"+result.content[i].picUrl+"'><img src='"+result.content[i].picUrl+"' ></a></div>";
            htmlss+="  </div>";
            htmlss+=" </div>";
         }
         $("#info").html(htmlss);
    }

}



