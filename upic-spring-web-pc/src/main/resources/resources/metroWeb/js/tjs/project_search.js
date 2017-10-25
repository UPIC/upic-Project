        var dataUrl = "/common/getProject";
        var searchKeyWordUrl="/common/projectSearchBar";
        var getProjectByProjectNumUrl="";
        var getProjectTypeUrl="/common/getAllProjectCategory";
        var getCollegeUrl="";
        var pageSize = 0;
        var totalPages=-1;
        var pageNum=0;
        var requestData={

        };

        $(function(){
            pageSize=$("#select-small").children('option:selected').text()
            commonAjax(getProjectTypeUrl,null,"addProjectType","GET");
            commonAjax(getCollegeUrl,null,"addCollege","GET");
            registSelect("getProjectType");
            registSelect("getCollege");
            getData(pageNum,dataUrl);
        })

        function addProjectType(res){
            var data=res.content;
            var htmls="";
            htmls+="<option value='4' class='yellow'>项目类别筛选...</option>";

            for(var i=0;i<data.length;i++){
                htmls+="<option value='"+(i+4)+"'>"+data[i].categoryName+"</option>";
            }
            $("#getProjectType").html(htmls);
        }

        function addCollege(res){
            var data=res.content;
            var htmls="";
            htmls+="<option value='4' class='yellow'>学院筛选...</option>";

            for(var i=0;i<data.length;i++){
                htmls+="<option value='"+(i+4)+"'>"+data[i].college+"</option>";
            }
            $("#getCollege").html(htmls);

        }



        function addHtmls(datas,pageNum) {
            totalPages=datas.totalElements;
            var data=datas.content;
            var htmls="";
            for(var i=0;i<data.length;i++){
                htmls+="<tr><td><input type='checkbox' class='checkboxes' value='1' id='"+data[i].projectNum+"'/></td>";
                htmls+="<td class='center_td'>"+(parseInt(pageNum)*parseInt(pageSize)+i+1)+"</td>";
                htmls+="<td>"+data[i].projectNum+"</td>";
                htmls+="<td>"+data[i].projectCategory+"</td>";
                htmls+="<td>"+data[i].projectName+"</td>";
                htmls+="<td>"+data[i].college+"</td>";
                htmls+="<td>"+data[i].guidanceNum+"</td>";
                htmls+="<td>"+data[i].guidanceMan+"</td>";
                htmls+="<td>"+data[i].integral+"</td>";
                htmls+="<td>"+data[i].maximum+"</td>";
                htmls+="<td>"+data[i].creatTime+"</td>";
                htmls+="<td class='center_td'><a href='#mymodal1'";
                htmls+="data-toggle='modal'><div class='message_div' onclick=commonAjax('"+dataUrl+"','projectNum"+data[i].projectNum+"','getProjectInfo','GET')>查看详情</div></a></td></tr>";
            }
            $("#data").html(htmls);
            page(datas,dataUrl,datas.size,datas.number);
        }

        function getProjectInfo(data){
            var htmlss="";
            var statuss="";
            if (data.implementationProcess==="SAVED") {statuss="已保存"};
            if (data.implementationProcess==="IN_AUDIT") {statuss="审核中"};
            if (data.implementationProcess==="AUDITED") {statuss="已审核"};
            if (data.implementationProcess==="NOT_PASS") {statuss="未通过"};
            if (data.implementationProcess==="ENROLLMENT") {statuss="报名中"};
            if (data.implementationProcess==="HAVE_IN_HAND") {statuss="进行中"};
            if (data.implementationProcess==="COMPLETED") {statuss="已完成"};
            if (data.implementationProcess==="CHECKED") {statuss="已验收"};

            htmls+="<tr><td><input type='checkbox' class='checkboxes' value='1' id='"+data[i].projectNum+"'/></td>";
            htmls+="<td class='center_td'>"+(parseInt(pageNum)*parseInt(pageSize)+i+1)+"</td>";
            htmls+="<td>"+data[i].projectNum+"</td>";
            htmlss+="<div class='span3'>代码</div>";
            htmlss+="<div class='span3'>"+data[i].projectNum+"</div>";
            htmlss+="</div>";
            htmlss+="<div class='row-form clearfix'>";
            htmlss+="<div class='span3'>项目申请日期</div>";
            htmlss+="<div class='span3'>"+getDate(data[i].creatTime,'yyyy-MM-dd')+"</div>";
            htmlss+="<div class='span3'>状态</div>";
            htmlss+="<div class='span3'>"+statuss+"</div>";
            htmlss+="</div>";
            htmlss+="<div class='row-form clearfix'>";
            htmlss+="<div class='span3'>项目类别</div>";
            htmlss+="<div class='span3'>"+data[i].projectCategory+"</div>";
            htmlss+="<div class='span3'>所属学院</div>";
            htmlss+="<div class='span3'>"+data[i].declareUnit+"</div>";
            htmlss+="</div>";
            htmlss+="<div class='row-form clearfix'>";
            htmlss+="<div class='span3'>项目名称</div>";
            htmlss+="<div class='span3'>"+data[i].projectName+"</div>";
            htmlss+="<div class='span3'>负责人</div>";
            htmlss+="<div class='span3'>"+data[i].guidanceMan+"</div>";
            htmlss+="</div>";
            htmlss+="<div class='row-form clearfix'>";
            htmlss+="<div class='span3'>工作量</div>";
            htmlss+="<div class='span3'>";
                  commonAjax
            htmls+="</div>";
            htmlss+="<div class='span3'>积分</div>";
            htmlss+="<div class='span3'>"+data[i].integral+"</div>";
            htmlss+="</div>";
            htmlss+="<div class='row-form clearfix'>";
            htmlss+="<div class='span3'>开始时间</div>";
            htmlss+="<div class='span3'>"+getDate(data[i].startTime,'yyyy-MM-dd')+"</div>";
            htmlss+="<div class='span3'>结束时间</div>";
            htmlss+="<div class='span3'>"+getDate(data[i].endTime,'yyyy-MM-dd')+"</div>";
            htmlss+="</div>";
            htmlss+="<div class='row-form clearfix'>";
            htmlss+="<div class='span3'>参与人数</div>";
            htmlss+="<div class='span3'>????/"+data[i].maximum+"</div>";
            htmlss+="</div>";
            htmlss+="<div class='row-form clearfix'>";
            htmlss+="<div class='span3'>项目内容</div>";
            htmlss+="<div class='span9'>"+data[i].content+"</div>";
            htmlss+="<div class='row-form clearfix'>";
            htmlss+="<div class='span3'>评价标准与形式</div>";
            htmlss+="<div class='span9'>"+data[i].checkAssessmentCriteraAndForm+"</div>";
            htmls+="</div>";


            $("#getProjectInfo").html(htmlss);
        }

        //获取工作量
        function getGZL(data,id){

        }
        //获取当前参与人数
        function getdangqiancanyurenshu (){

        }

