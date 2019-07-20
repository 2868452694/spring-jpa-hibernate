$(function () {
    $.ajax({
        type:"GET",
        url:"/agent/appAddress/appAddress.do",
        data:{id:1},
        dataType:"json",
        success:function (data) {
            $("input[type=text]").val("");
            $("input[name=fuwu]").val(data.configname);
            $("input[name=num]").val(data.configvalue);
        },
        error:function (data) {
            alert("网络异常");
        }
    });
    $("input[type=button]").click(function () {
        $.ajax({
            type:"GET",
            url:"/agent/appAddress/appAddress.up",
            data:{appid:1,configname:$("input[name=fuwu]").val(),configvalue:$("input[name=num]").val()},
            dataType:"json",
            success:function (data) {
                if(data==true){
                    alert("保存成功!");
                }else{
                    alert("网络出现延迟请稍后在试!");
                }
            },
            error:function (data) {
                alert("网络异常");
            }
        });
    });
})