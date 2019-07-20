$(function () {
    bindRole();
    bindUserInfo(page);
    findButton();
})

/**
 *绑定角色信息
 */
function bindRole(){
    $.ajax({
        type:"GET",
        url:"/agent/role/bindrole",
        dataType:"json",
        async:false,
        success:function (data) {
            $("#selectedByUser").append("<option value=0>--请选择--</option>")
            $.each(data,function (i,e) {
                $("#selectedByUser").append("<option value='"+e.roleid+"'>"+e.rolename+"</option>")
            })
        },
        error:function (data) {
            alert("网络延迟");
        }
    })
}
var page=0;
/**
 * 绑定用户信息
 */
function bindUserInfo(page){
    var username=$("#selectedByUserName").val();
    var roleId=$("#selectedByUser option:selected").val();
    var enable=$("#selectedByIsOpen").val();
    alert(username+"===="+roleId+"====="+enable);
    $.ajax({
        type: "GET",
        url: "/agent/user/userInfo.json",
        data:{username:username,roleId:roleId,enable:enable,pageSum:page},
        dataType: "json",
        success:function (data) {
            $("table tr:gt(0)").remove();
            $.each(data.content,function (i,e) {
                var enableType=null;
                if(e.enable==0){
                    enableType="是";
                }else{
                    enableType="否"
                }
                $("table").append("<tr>" +
                                        "<td>"+e.username+"</td>" +
                                        "<td>"+e.name+"</td>" +
                                        "<td>"+e.role.rolename+"</td><td>"+e.lastlogintime+"</td>" +
                                        "<td>"+enableType+"</td>" +
                                        "<td>" +
                                        "<ul id='"+e.userid+"'>" +
                                        "<li class='up'>修改</li>" +
                                        "<li class='del'>删除</li>" +
                                        "<li class='yu'>预付款</li>" +
                                        "<li class='log'>Log日志</li>" +
                                        "</ul>" +
                                        "</td>" +
                                "</tr>")
            })
        },
        error:function (data) {
            alert("网络异常");
        }

    })
}

/**
 *
 */
function  findButton() {
    page=0;
    $("#selectedSubmit").click(function () {
        bindUserInfo(page);
    })
}