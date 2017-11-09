
$(function(){

    var projectNum = getQueryString("projectNum");
    var time = getQueryString("time");

    $("#huodongshijian").html(time);

    var changeCodeUrl="";
    var getprojectByProjectNumUrl="";
    var QRcodeStatus="";
    getQRcodeStatus(projectNum);


})





function getQRcodeStatus(projectNum){
    $.ajax({
        type: 'GET',
        url: getprojectByProjectNumUrl,
        data: {
           projectNum : projectNum,
       },
       success: function (result) {
            QRcodeStatus=result.onOff;//1开0关
            if (QRcodeStatus==1) {
                $("#QRcode").attr("class","switch-on");
            }else{
                $("#QRcode").attr("class","switch-up");
            }

        }
    });
}

function changQRcode(){
    var QRcodestatus=$$("#QRcode").attr("class");
    if (QRcodestatus=="switch-on") {//如果一开始 开（1）
        $.ajax({
        type: 'GET',
        url: changeCodeUrl,
        data: {
           onOff:0
       },
       success: function (result) {
                $("#QRcode").attr("class","switch-up");
        }
    });
    }else{//如果一开始 关（0）
        $.ajax({
        type: 'GET',
        url: changeCodeUrl,
        data: {
           onOff:1
       },
       success: function (result) {
                $("#QRcode").attr("class","switch-on");
        }
    });
    }

}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}