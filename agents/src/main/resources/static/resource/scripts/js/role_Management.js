var roleCURD = {
    //创建 名称,是否启用
    create: function (rolename, status) {
        $.ajax({
            url: "/agent/role/create",
            type: "post",
            data: {rolename: rolename, status: status},
            dataType: "json",
            success: function (data) {
                //弹出提示信息
                alert(data.message);
                //成功后关闭添加框
                //失败后清空添加框
                if (data.exeCode == 0) {
                    $("#insert").hide();
                    $("#insert input[type=text]").val("");
                    $("#insert select option:first").attr("selected", true);
                } else {
                    $("#insert input").val("");
                    $("#insert select option:first").attr("selected", true);
                }
            },
            error: function () {
                alert("网络异常")
            }
        });
    },
    update: function (roleid, rolename, status) {
        $.ajax({
            url: "/agent/role/update",
            type: "post",
            data: {roleid: roleid, rolename: rolename, status: status},
            dataType: "json",
            success: function (data) {
                //弹出提示信息
                alert(data.message);
                //成功后关闭添加框
                //失败后清空添加框
                if (data.exeCode == 0) {
                    $("#update").hide();
                    $("#insert input[type=text]").val("");
                    $("#insert select option:first").attr("selected", true);
                    //实时更新页面信息
                    var e=data.updateResult;
                    $("#div_table table tbody tr[roleid="+data.updateResult.roleid+"]").html("").append(  "            <td>" + e.rolename + "</td>\n" +
                        "            <td>" + dateTo(e.createtime) + "</td>\n" +
                        "            <td status=" + e.status + ">" + (e.status == 0 ? "启用" : "不启用") + "</td>\n" +
                        "            <td class=\"update\">修改</td>\n" +
                        "            <td class=\"delete\">删除</td>\n");

                }
            },
            error: function () {
                alert("网络异常")
            }
        });
    },
    read: {
        readOne: function (roleid) {
            $.ajax({
                url: "/agent/role/readOne",
                type: "post",
                data: {roleid: roleid},
                dataType: "json",
                success: function (data) {
                    if (data.exeCode == 0) {
                        //更改修改框的数据
                        $("#update").attr("roleid",data.data.roleid);
                        $("#update .rolename").val(data.data.rolename);
                        $("#update .status option[value="+data.data.status+"]").attr("selected",true);
                    }
                },
                error: function () {
                    alert("网络异常")
                }
            });
        },
        readByNumPage: function (indexPage) {
            $.ajax({
                url: "/agent/role/readPage",
                type: "post",
                data: {indexPage: indexPage},
                dataType: "json",
                success: function (data) {
                    $("#div_table table tbody").html("");
                    //更新数据
                    for (var i = 0; i < data.data.content.length; i++) {
                        var e = data.data.content[i];
                        $("#div_table table tbody").append(" <tr roleid=" + e.roleid + ">\n" +
                            "            <td>" + e.rolename + "</td>\n" +
                            "            <td>" + dateTo(e.createtime) + "</td>\n" +
                            "            <td status=" + e.status + ">" + (e.status == 0 ? "启用" : "不启用") + "</td>\n" +
                            "            <td class=\"update\">修改</td>\n" +
                            "            <td class=\"delete\">删除</td>\n" +
                            "        </tr>")
                    }

                    //绑定页数
                    //清空span
                    $("#div_table .div div").html("");
                    //添加首页和尾页
                    $("#div_table .div div").append("<span pageIndex='0'>首页</span>");
                    //显示前五页
                    for(var i=data.data.number-1;i>=data.data.number-5;i--){
                        if(i<0)break;
                        $("#div_table .div div").append("<span pageIndex='"+i+"'>"+(i+1)+"页</span>");
                    }
                    $("#div_table .div div").append("<span pageIndex='"+data.data.number+"'>"+(data.data.number+1)+"页</span>");//当前页
                    //显示后五页
                    for(var i=data.data.number+1;i<data.data.number+5;i++){
                        if(i>=data.data.totalPages)break;
                        $("#div_table .div div").append("<span pageIndex='"+i+"'>"+(i+1)+"页</span>");
                    }
                    $("#div_table .div div").append("<span pageIndex='"+(data.data.totalPages-1)+"'>尾页</span>");
                },
                error: function () {
                    alert("网络异常")
                }
            });
        }
    },
    delete: function (roleid) {
        $.ajax({
            url: "/agent/role/delById",
            type: "post",
            data: {roleid: roleid},
            dataType: "json",
            success: function (data) {
                alert(data.message);
                if (data.exeCode == 0) {
                    $("tr[roleid='" + date.roleid + "']").remove();
                }
            },
            error: function () {
                alert("网络异常")
            }
        });
    }

};

$(function () {
    roleCURD.read.readByNumPage(0);//加载第0页
    $(".div div span ").click(function () {
        $(this).css("background", "ghostwhite");
        $(this).siblings().css("background", "");
    });
    //显示添加框框
    $("#div_insert img").click(function () {
        $("#insert").show();
    });
    $("#insert .addSub").click(function () {
        roleCURD.create($("#insert .rolename").val(), $("#insert .status option:selected").val());
    });
    $("#update .upSub").click(function () {
        roleCURD.update($(this).parents("#update").attr("roleid"), $("#update .rolename").val(), $("#update .status option:selected").val());
    });
    $("table ").on("click", ".update", function () {
        roleCURD.read.readOne($(this).parents("tr").attr("roleid"));
        $("#update").show();
    }).on("click", ".delete", function () {
        if (confirm("确定删除本条记录?")) {
            roleCURD.delete($(this).parents("tr").attr("roleid"));
        }
    });
    //退出
    $(".esc").click(function () {
        $("input[type=text]").val("");
        $("#insert").hide();
        $("#update").hide();
    });
    $(".div ").on("click","span",function () {
        roleCURD.read.readByNumPage($(this).attr("pageIndex"));
    });
});
