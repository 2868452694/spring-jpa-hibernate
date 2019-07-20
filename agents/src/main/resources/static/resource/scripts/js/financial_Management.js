//用户名集合
//$("#div_ul")
//集合中的项
//$("#div_ul ul li")
//用户名搜索框
// $("input[name=user]")
//操作备注
//$("textarea")
//操作资金
//$("#u3_input")
var financial_Management = {
    //查询用户名集合
    getUserByusername: function (username) {
        $.ajax({
            url: "/agent/user/getuserByusername",
            type: "post",
            data: {username: username},
            dataType: "json",
            success: function (data) {
                $("#div_ul ul").html("");//清空
                for (var i = 0; i < data.length; i++) {
                    var e = data[i];
                    $("#div_ul ul").append("<li userid='" + e.userid + "'>" + e.username + "</li>");
                }
            },
            error: function () {
                alert("网络异常");
            }

        });
    },
    //操作类型集合
    getAllType: function () {
        $.ajax({
            url: "/agent/finance/financeType.json",
            type: "post",
            data: {typeParentId: 27},
            dataType: "json",
            success: function (data) {
                $("#u2_input").html("");
                for (var i = 0; i < data.length; i++) {
                    var e = data[i];
                    $("#u2_input").append("<option value='" + e.typeid + "'>" + e.typename + "</option>");
                }
            },
            error: function () {
                alert("网络异常");
            }

        });
    },
    //保存
    create: function (userid, typeid, remarks, money) {
        $.ajax({
            url: "/agent/finance/inserFinancial",
            type: "post",
            data: {userid: userid, typeid: typeid, remarks: remarks, money: money},
            dataType: "text",
            success: function (data) {
                alert(data);
            },
            error: function () {
                alert("网络异常");
            }

        });
    }
};
$(function () {
    financial_Management.getAllType();
    $("input[name=user]").change(function () {
        financial_Management.getUserByusername($(this).val());
        $("#div_ul").show();
    });
    $("#div_ul ul").on("click", "li", function () {
        $("input[name=user]").val($(this).html());
        $("input[name=user]").attr("userid",$(this).attr("userid"));
        $("#div_ul").hide();
    });
    // $("input[name=user]").val($("#div_ul ul").on("click","li",function () {
    //     $("input[name=user]").val($(this).html());
    //     $("#div_ul").hide();
    // }).find("li").eq(0).html()).focus(function () {
    //     $("#div_ul").show();
    // }).change(function () {
    //     $("#div_ul").hide();
    // });
    //保存
    $("#img").click(function () {
        if($("input[name=user]").attr("userid")==null||$("input[name=user]").attr("userid")==""){
            alert("请选择用户");
            return ;
        }
        var userid=$("input[name=user]").attr("userid"),typeid=$("#u2_input option:selected").val(),remarks=$("textarea").val(),money=$("#u3_input").val();
        financial_Management.create(userid, typeid, remarks, money);
    });
});
