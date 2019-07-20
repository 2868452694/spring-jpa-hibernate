$(function () {
    $.ajax({
        type:"GET",
        url:"/agent/appAddress/serviceTime.do",
        data:{id:1},
        dataType:"json",
        success:function(data){
            $("inputp[type=text]").val("");
            $("input[name=fuwu]").val(data.stname);
            $("input[name=num]").val(data.stvalue);
        },
        error:function (data) {
            alert("网络异常");
        }
    });
    $("input[type=button]").click(function () {
        $.ajax({
            type:"GET",
            url:"/agent/appAddress/serviceTime.up",
            data:{stid:1,stname:$("input[name=fuwu]").val(),stvalue:$("input[name=num]").val()},
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