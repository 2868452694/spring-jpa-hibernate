$(function () {
    bindName();
    exit();
    upPwd();
})
var password;
/**
 * 绑定用户名
 */
function bindName(){
    $.ajax({
        type:"GET",
        url:"/agent/user/userInfo",
        dataType:"json",
        success:function(data){
            $("#names").html(data.name);
            password=data.password;
        },
        error:function(data){
            alert("网络延迟");
        }
    });
}

/**
 * 退出
 */
function exit(){
    $("#exit").click(function () {
        $.ajax({
            type: "GET",
            url: "/agent/user/logout.json",
            dataType: "json",
            success:function (data) {
                if(data=="true"){
                    location.href="/agent/user/login";
                }
            },
            error:function (data) {
                alert("网络异常")
            }
        })
    })
}

/**
 * 修改密码
 */
function upPwd(){
    $("#save").click(function () {
        var oldpassword=$("#oldpassword").val();
        if(oldpassword!=password){
            alert("与原密码不一致");
        }else if($("#newpassword").val()!=$("#comnepwd").val()){
            alert("两次密码不一致");
        }else{
            $.ajax({
                type:"GET",
                url:"/agent/user/updatePwd.json",
                data:{pwd:$("#newpassword").val()},
                dataType:"json",
                success:function (data) {
                    if(data=="true"){
                        alert("修改成功，下次登录生效");
                        $("#newpassword").val("");
                        $("#oldpassword").val("");
                        $("#comnepwd").val("");
                        $("#div_from").hide();
                    }else{
                        alert("修改失败");
                    }
                },
                error:function (data) {
                    alert("网络异常");
                }
            })
        }
    })
}

