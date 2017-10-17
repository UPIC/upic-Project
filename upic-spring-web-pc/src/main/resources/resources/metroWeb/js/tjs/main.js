/*
* @Author: Marte
* @Date:   2017-10-11 08:16:44
* @Last Modified by:   Marte
* @Last Modified time: 2017-10-12 14:29:19
*/
$(function(){


                $.ajax({
                         type: "GET",
                          url: "/common/getUserInfo",
                         success: function (result) {
                             var coin="";

                             $("#getusernsme").text(result.username+" 同学，你好！");

                             coin="<a href='#mymodal1' data-toggle='modal'> <span onclick='ajaxs('guidanceNum="+result.guidanceNum+"','getmyproject','/common/getProjectByUser')''>"+result.id+" ></span></a>"

                             $("#getcoin").html(coin);

                             var htmls="";
                             htmls="<div class='row-form clearfix'>
                                     <div class='span3'>所属学院</div>
                                     <div class='span3'>"+result.college+"</div>
                                     <div class='span3'>班级</div>
                                     <div class='span3'>"+result.clazz+"</div>
                                     </div>
                                     <div class='row-form clearfix'>
                                     <div class='span3'>姓名</div>
                                     <div class='span3'>"+result.username+"</div>
                                     <div class='span3'>学号</div>
                                     <div class='span3'>"+result.idCard+"</div>
                                     </div>";
                            $("#getinfo").html(htmls);


                           }
                     });

        function ajaxs(data,method,urls){
            $.ajax({
                     type: GET, // 提交方式
                     url:,urls,// 路径
                     data:data ,
                    beforeSend: function (XMLHttpRequest) {
                        // progress.inc();
                        },
                     success: function (result) {// 返回数据根据结果进行相应的处理
                     var datas = result.content;
                     addHtmls(datas)
                     },
                     complete: function (XMLHttpRequest, textStatus) {
                        // progress.done(true);
                    },
                      error: function () {
                    // progress.done(true);
                       }
                 });
        }



        function addHtmls(result){
            var htmls="";
            var total=0;
            htmls+="<table class='table table-bordered'>";
            htmls+="    <thead>";
            htmls+="      <tr>";
            htmls+="        <th>类别</th>";
            htmls+="        <th>积分</th>";
            htmls+="      </tr>";
            htmls+="    </thead>";
            htmls+="    <tbody>";
            for (var i = 0; i < result.length; i++) {
                htmls+="      <tr>";
                htmls+="        <td>result.projectCategory</td>";
                htmls+="        <td>"+result.integral+"</td>";
                htmls+="      </tr>";
                total=total+result.integral;//总计
            };
            htmls+="      <tr>";
            htmls+="        <td colspan='4' style='text-align:right;padding-right: 20px;'>总计:<span>"+total+"</span></td>";
            htmls+="      </tr>";
            htmls+="    </tbody> ";
            htmls+="  </table>";
            $("#getinfo").append(htmls);


        }

})
