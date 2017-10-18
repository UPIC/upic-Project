/*
* @Author: Marte
* @Date:   2017-10-09 19:01:21
* @Last Modified by:   Marte
* @Last Modified time: 2017-10-12 16:28:26
*/

        var pages="";//每页几条数据
        var maxpage="";//最大页数
        var totalpage="";//总条数
        var nowpage="";//当前页

        maxpage=totalpage/pages;
        pages=$("#select-small option:selected").text();//获取每页显示几条数据


$(function(){


        ajaxGetPage(1,pages)



        function ajaxGetPage(page,pages){
                $.ajax({
                     type: POST, // 提交方式
                     url:"/common/getProjectByGuidanceNum",// 路径
                     // data: 获取第几页的参数名=page&每页几条数据参数名=pages,
                    beforeSend: function (XMLHttpRequest) {
                        // progress.inc();
                        },
                     success: function (result) {// 返回数据根据结果进行相应的处理
                     totalpage = result.total;
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
            for (var i = 0; i < result.length; i++) {
                var status="";
                  switch (result[i].status) {
                    case ("string1"):status="已保存";
                        break;
                    case ("string2"):status="未通过"
                        break;
                    case ("string3"):status="审核中"
                        break;
                    case ("string4"):status="已通过"
                        break;
                    case ("string5"):status="进行中"
                        break;
                    case ("string6"):status="已完成"
                        break;
                    default:
                }


                if (result[i].status=="已保存"&&(result[i].status=="未通过")) {
                 htmls+="<tr>";
                 htmls+="<td><input type='checkbox' class='checkboxes' value='1'/></td>";
                 htmls+="<td class='center_td'>"+项目编号+"</td>";
                 htmls+="<td>"+项目代码+"</td>";
                 htmls+="<td>"+项目类别+"</td>";
                 htmls+="<td>"+项目名称+"</td>";
                 htmls+="<td>"+2.0+"</td>";
                 htmls+="<td>"+10/40+"</td>";
                 htmls+="<td>"+1000/3000+"</td>";
                 htmls+="<td>"+2017/09/14+"</td>";
                 htmls+="<td class='center_td'>"+status+"</td>";
                 htmls+="<td class='center_td'>";
                 htmls+=" <div class='message_div'><a href='#mymodal2' data-toggle='modal'>编辑</a><span class='space'>|</span><a>提交</a></div></td>";
                 htmls+=" </tr>";
                }else{
                 htmls+="<tr>";
                 htmls+="<td><input type='checkbox' class='checkboxes' value='1'/></td>";
                 htmls+="<td class='center_td'>"+项目编号+"</td>";
                 htmls+="<td>"+项目代码+"</td>";
                 htmls+="<td>"+项目类别+"</td>";
                 htmls+="<td>"+项目名称+"</td>";
                 htmls+="<td>"+2.0+"</td>";
                 htmls+="<td>"+10/40+"</td>";
                 htmls+="<td>"+1000/3000+"</td>";
                 htmls+="<td>"+2017/09/14+"</td>";
                 htmls+="<td class='center_td'>"+status+"</td>";
                 htmls+="<td class='center_td'>";
                 htmls+=" <div class='message_div'><a href='#mymodal3' data-toggle='modal'>详情</a><span class='space'>|</span><a href='#mymodal5' data-toggle='modal'>名单</a></div></td>";
                 htmls+=" </tr>";
                }
            };


       }




        $(".dataTables_filter").find('input').attr('placeholder','空格分隔 编号、项目名称、负责人').css('width', '250px');;
        var $checkall = $("input[name='checkall']");
        var $btn = $(".btn-success");
        $btn.click(function(){
            if($checkall.is(":checked"))
            {
               alert("确定选择审核的全部内容吗");
           }
       });

        $(".group-checkable").click(function(){
        //判断当前点击的复选框处于什么状态$(this).is(":checked") 返回的是布尔类型
         if($(this).is(":checked")){
             $("input[value='1']").prop("checked",true);
         }else{
            $("input[value='1']").prop("checked",false);
        }
        });

        //页面加载项目类别筛选
        $("#sample_1_length").find('label').after("<div class='selectlist'><select name='select' style='width: 100%;' class='color-wh'> <option value='4' class='yellow' id='get'>项目类别筛选...</option> </select> </div>");

        //ajax获取项目类别
        $.ajax({
            type: "GET",
             url: 获取项目类别地址,
            success: function (result) {
                var htmls="";
                for(var i=0;i<result.content[i].length;i++){
                  htmls+="<option value='"+(i+4)+"'>"+result.content[i].类别字段+"</option>";
                }
                $("#get").append(htmls);
              }
        });

        //页面加载
        $("#sample_1_length").find('label').html("<span>每页显示</span> <select class='input-small m-wrap' tabindex='1' id='select-small'> <option value='Category 1'>5</option> <option value='Category 2'>10</option> <option value='Category 3'>15</option> <option value='Category 4'>20</option> </select><span>条记录</span>");



        $('#sample_1_info').html("当前显示<span></span>到<span>"+pages+"</span>条,共<span>"+pages+"</span>条记录");


        var begin="";//开始
        var end="";//结束
        var pagehtml="";
        if (nowpage<=6) {
            begin=1;
            end=maxpage;
        }else{
            begin=nowpage-2;
            end=nowpage+3;
            if (begin<1) {
                begin=1;
                end=6;
            };
            if (end>maxpage) {
                begin=maxpage-5;
                end=maxpage;
            };
        }

        for (var i = begin; i <=end; i++) {
            if (i==nowpage) {
                pagehtml+="<li>"+i+"</li>";
            }else{
                pagehtml+="<li><a href=javascript:ajaxGetPage("+i+","+pages+")>"+i+"</a></li>"
            }
            if (end<maxpage) {
                pagehtml+="<li>...</li>";
            }
        };
        //判断何时上一页不能点击
        var shangyiye ="";
        if(nowpage==1){
                 shangyiye="<span  style='color:#B0B0B0'>上一页</span>";//上一页不能点击
        }else{
                 shangyiye="<a href=javascript:ajaxGetPage("+(nowpage-1)+","+pages+")>上一页</a>";//上一页可以点击
        }

        //下一页
        var xiayiye ="";
        if(nowpage==maxpage){
                 xiayiye="<span  style='color:#B0B0B0'>下一页</span>";//下一页不能点击
        }else{
                 xiayiye="<a href=javascript:ajaxGetPage("+(nowpage+1)+","+pages+")>下一页</a>";//下一页可以点击
        }

        var fengyehtml="";
        fengyehtml=shangyiye+pagehtml+xiayiye;

        $('.pagination').html("<div class='pagination'> <ul id='fengyedaohang'> </ul> </div>");

        $("fengyedaohang").html(fengyehtml);

        $(".selectlist").after("<div class='selectlist selectlist_second'> <select name='select' style='width: 100%;' class='color-wh'> <option value='4' class='yellow' id='getstatus'>项目状态筛选...</option> </select> </div>");

        $.ajax({
            type: "GET",
             url: 获取项目状态地址,
            success: function (result) {
                var htmls="";
                for(var i=0;i<result.content[i].length;i++){
                  htmls+="<option value='"+(i+5)+"'>"+result.content[i].status+"</option>";
                }
                $("#getstatus").append(htmls);
              }
        });

        $(".selectlist_second").after("<button class='btn btn-small btn-success'> <i class='icon-cloud'></i>导出 </button>");

        $("#sample_1_wrapper>.row-fluid>.span6").first().css('width', '62.9%');

        $(".dataTables_filter").parent(".span6").css('width', '33.9%');


        $("textarea").html("校级学生干部加分校级学生干部加分校级学生干部加分校级学生干部加分校级学生干部加分校级学生干部加分");


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

    })