$(function () {
    bindName();
})
/**
 * 绑定用户信息
 */
function bindName(){
    $.ajax({
        type:"GET",
        url:"/agent/user/userInfo",
        dataType:"json",
        success:function(data){
            $("#name").html("您好，"+data.name);
            $("#time").html(data.lastlogintime);
            $("#money").html("￥"+data.balance);
        },
        error:function(data){
            alert("网络延迟");
        }
    });
}