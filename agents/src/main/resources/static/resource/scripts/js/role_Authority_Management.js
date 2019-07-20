var roleJuisdictionManage = {
    Data: null,//查询出的数据
    getAllRole: function () {//查询出所有的角色和与之关联的权限
        $.ajax({
            url: "/agent/role/selAllRole",
            type: "get",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data != null) {
                    roleJuisdictionManage.Data=data;
                    $("#div_left").html("");
                    for(var i=0;i<data.length;i++){
                        var e=data[i];
                        $("#div_left").append('<div roid='+e.roleid+' index='+i+'>\n' +
                            '            <a href="#">+'+e.rolename+'</a>\n' +
                            '        </div>')
                    }
                }
            },
            error: function () {
                alert("网络异常");
            }
        });
    },
    getAllJuisdiction: function () {//查询出所有的权限
        $.ajax({
            url: "/agent/role/faj",
            type: "get",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data != null) {
                    $("tbody").html("");
                    for(var i=0;i<data.length;i++){
                        var e=data[i];
                        $("tbody").append(' <tr jdid='+e.jdid+'>\n' +
                            '                <td>\n' +
                            '                    <input type="checkbox"/>\n' +
                            '                </td>\n' +
                            '                <td>'+e.jdname+'</td>\n' +
                            '                <td>'+e.jdurl+'</td>\n' +
                            '                <td>'+dateTo(e.createtime)+'</td>\n' +
                            '                <td '+e.status+'>'+(e.status==0?"启用":"停用")+'</td>\n' +
                            '            </tr>')
                    }
                }
            },
            error: function () {
                alert("网络异常");
            }
        });
    },
    roleJuisdictionUpdate: function (data) {//修改角色的权限
        $.ajax({
            url: "/agent/role/juisdictionUpdate",
            type: "get",
            data:{data:JSON.stringify(data)},
            dataType: "json",
            success: function (data) {
              //更新页面
                for(var i=0;i<data.length;i++) {
                    var e = roleJuisdictionManage.Data[i];
                    if(e.roleid==data.roleid){
                        roleJuisdictionManage.Data[i]=data;
                        roleJuisdictionManage.showByRoleAJuisdiction(i);
                        return;
                    }
                }
            },
            error: function () {
                alert("网络异常");
            }
        });
    },
    showByRoleAJuisdiction: function (index) {//展示指定角色的权限
        $("tbody tr td input").attr("checked",false);
        $("table").attr("roleid",roleJuisdictionManage.Data[index].roleid);
        var jus=roleJuisdictionManage.Data[index].jurisdictions;
        for(var i=0;i<jus.length;i++){
            var e=jus[i];
            $("tbody tr[jdid="+e.jdid+"] td input").attr("checked",true);
        }
        checked.monitorIsSelectAll();
    }
};

var checked={
    monitorIsSelectAll:function(){//监测是否全选
        //复选框选中的个数不等于所有复选框的个数就取消全选
        var allCheckbox=$("tbody input[type=checkbox]");
        var checkedCheckbox=$("tbody input:checked");
        if(allCheckbox.length==checkedCheckbox.length){
            $("thead input").attr("checked",true);
        }else{
            $("thead input").attr("checked",false);
        }
    },
    selectAll:function(){
        var allCheckbox=$("tbody input[type=checkbox]");
        var checkedCheckbox=$("tbody input:checked");
        if(allCheckbox.length==checkedCheckbox.length){
            $("table input").attr("checked",false);
        }else{
            $("table input").attr("checked",true);
        }
    }
};
$(function () {
    roleJuisdictionManage.getAllJuisdiction();
    roleJuisdictionManage.getAllRole();
    roleJuisdictionManage.showByRoleAJuisdiction(0);

    $("#div_left").on("click","div",function(){
        $("#update+button").attr("index",$(this).attr("index"));
        roleJuisdictionManage.showByRoleAJuisdiction($(this).attr("index"));
    });
    $("tbody ").on("change","input[type=checkbox]",checked.monitorIsSelectAll);
    $("thead").on("change","input[type=checkbox]",checked.selectAll);
    $("#update+button").click(function () {
        roleJuisdictionManage.showByRoleAJuisdiction($(this).attr("index"));
    });
    $("#update").click(function () {
        var data={roleid:$("table").attr("roleid")};
        data.jdids=[];
        var selInput=$("tbody input:checked");
        for(var i=0;i<selInput.length;i++){
            data.jdids[i]=selInput.eq(i).parents("tr").attr("jdid");
        }
        roleJuisdictionManage.roleJuisdictionUpdate(data);
    });
});
