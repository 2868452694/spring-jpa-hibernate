$(function () {
    login();
})

/**
 * 登录
 */
function login(){
    $("#su").click(function () {
        var username=$("input[name='username']").val();
        var password=$("input[name='password']").val();
        $.ajax({
            type:"GET",
            url:"doLogin.json",
            data:{username:username,password:password},
            dataType:"text",
            success:function(data){
               if(data=="success"){
                    location.href="/agent/page/AgentManage";
               }else if(data=="error"){
                    $("#message").html("密码不正确")
               }else if(data=="failed"){
                   $("#message").html("用户名不正确")
               }
            },
            error:function(data){
                alert("网络异常");
            }
        });
    })
}