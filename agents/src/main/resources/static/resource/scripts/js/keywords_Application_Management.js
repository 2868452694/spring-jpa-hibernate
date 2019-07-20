var PageData = {
    pageIndex: 0,
    pageSum: 0,
    kwname: function () {
        return $("#kwname").val()
    },
    getByPageAndKwnameData: function (pageIndex, kwname) {
        //获取指定的
        $.ajax({
            type: "GET",
            url: "/agent/keyword/selKeywordByPageAndName",
            data: {kwname: kwname, pageSum: pageIndex},
            dataType: "json",
            success: function (data) {
                //根据返回数据进行修改元素
                //绑定数据
                //清空table
                $("#div_table table").html('<tr><td>序号</td><td>关键词</td><td>客户名称</td><td>代理商</td><td>申请年限</td><td>到期日期</td><td>申请到期状态</td><td>审核状态</td><td>使用状态</td><td>app开通状态</td><td colspan="2">操作</td> </tr>');
                //循环绑定数据
                for(var i =0 ;i<data.keywordPage.content.length;i++){
                    var e=data.keywordPage.content[i];
                    var ec=data.clients[i];
                    $("#div_table table").append('<tr kwid="' + e.kwid + '"><td>' + e.kwid + '</td><td>' + e.kwname + '</td><td>' + ec.enterprisename + '</td><td>' + e.users.name + '</td><td>' + e.applylimit + '</td><td>' + dateTo(e.lasttime) + '</td><td>' + (e.status == 0 ? '未到期' : "已过期") + '</td><td>' + (e.auditstatus == 0 ? "核审中" : e.auditstatus == 1 ? "已通过" : "未通过") + '</td><td>' + (e.usestatus == 0 ? "已使用" : "未使用") + '</td><td>' + (e.appStatus == 2 ? "已开通" : "未开通") + '</td><td class="caozuo" colspan="2"><div class="kai" kwid="' + e.kwid + '"> 开通APP</div><div class="xu" kwid="' + e.kwid + '">续费</div></td> </tr>');

                }
                //绑定页数
                //清空页数
                $("#div_table .div div").html("");
                PageData.pageSum = data.keywordPage.totalPages - 1;
                PageData.pageIndex = data.keywordPage.number;
                $("#div_table .div div").append("<span pageIndex='0'>首页</span>");
                for (var i = data.keywordPage.number; (i > 0 && i > data.keywordPage.number - 5); i--) {
                    $("#div_table .div div").append("<span pageIndex='" + i + "'>" + (1 + i) + "页</span>");
                }
                for (var i = data.keywordPage.number; (i < data.keywordPage.totalPages && i < data.keywordPage.number + 5); i++) {
                    $("#div_table .div div").append("<span pageIndex='" + i + "'>" + (1 + i) + "页</span>");
                }
                $("#div_table .div div").append("<span pageIndex='" + data.keywordPage.totalPages + "'>末页</span>");


            },
            error: function (data) {
                alert("网络异常");
            }
        });
        //更新当前数据
    },
    getByPageData: function (pageIndex) {
        return PageData.getByPageAndKwnameData(pageIndex, PageData.kwname());
    }
};

var AppOpen = {
    AppOpendata: null,
    RenewAppData: null,
    getAppData: function (keywordId) {
        $.ajax({
            type: "GET",
            url: "/agent/keyword/getOpenAppPageData",
            data: {keywordId: keywordId},
            dataType: "json",
            success: function (data) {
                AppOpen.AppOpendata = data;
                //根据返回的参数做出信息修改
                $("#insert div:first+div+div span:first").html(data.keyword.kwname);//关键字名称
                //企业名称
                $("#insert div:first+div+div span:last").html(data.client.enterprisename);
                //修改价格
                $("#insert div:first+div+div+div span:first").html(data.types.typevalue * 3);
                //绑定事件
                $("#insert input[value='保存']").click(null).click(function () {
                    var username = $("#insert div:first+div input:first").val();
                    if (username == null || username == "") {
                        alert("请输入用户名");
                        return;
                    }
                    var password = $("#insert div:first+div input:last").val();
                    if (password == null || password == "") {
                        alert("请输入密码");
                        return;
                    }
                    AppOpen.AppOpen(username, password, AppOpen.AppOpendata.keyword.kwid);
                });
            },
            error: function (data) {
                alert("网络异常");
            }
        });
    },
    AppOpen: function (username, password, keywordId) {
        $.ajax({
            type: "POST",
            url: "/agent/keyword/openApp",
            data: {keywordId: keywordId, username: username, password: password},
            dataType: "json",
            success: function (data) {
                alert(data.error.message);
                if (data.error.message == "开通成功") {
                    $("#insert").hide()
                }
                //数据更新
                var e = data.keyword;
                var ec= data.client;
                $("#div_table table tr[kwid='" + data.keyword.kwid + "']").html("").append('<td>' + e.kwid + '</td><td>' + e.kwname + '</td><td>' + ec.enterprisename + '</td><td>' + e.users.name + '</td><td>' + e.applylimit + '</td><td>' + dateTo(e.lasttime) + '</td><td>' + (e.status == 0 ? '未到期' : "已过期") + '</td><td>' + (e.auditstatus == 0 ? "核审中" : e.auditstatus == 1 ? "已通过" : "未通过") + '</td><td>' + (e.usestatus == 0 ? "已使用" : "未使用") + '</td><td>' + (e.appStatus == 2 ? "已开通" : "未开通") + '</td><td class="caozuo" colspan="2"><div class="kai" kwid="' + e.kwid + '"> 开通APP</div><div class="xu" kwid="' + e.kwid + '">续费</div></td>' );
            },
            error: function (data) {
                alert("网络异常");
            }
        });
        AppOpen.AppOpendata = null;
    },
    getRenewAppData: function (keywordId) {
        //获取数据并绑定到续费面板上
        $.ajax({
            type: "POST",
            url: "/agent/keyword/getRenewAppData",
            data: {keywordId: keywordId},
            dataType: "json",
            success: function (data) {
                AppOpen.RenewAppData = data;
                $("#update").attr("kwid",data.keyword.kwid);
                $("#update>div:first>strong>strong").html(data.keyword.kwname);
                $("#update>div:first>strong>span").html("[当前账户余额:" + data.user.balance + "]");
                $("#update>div:nth-child(2)>div:first>input").val(data.client.enterprisename);
                $("#update>div:nth-child(2)>div:nth-child(2)>input").val(data.keyword.kwname);
                $("#update>div:nth-child(2)>div:nth-child(3)>select").html("").change(function () {
                    $("input[name=upname]:last").val(parseInt($("#update>div:nth-child(2)>div:nth-child(3)>select option:selected").val()) * parseInt($("#update>div:nth-child(2)>div:nth-child(4)>select option:selected").val()));
                });
                for (var i = 0; i < data.typess.length; i++) {
                    $("#update>div:nth-child(2)>div:nth-child(3)>select").append("<option value='" + data.typess[i].typevalue + "' typeid='" + data.typess[i].typeid + "'>" + data.typess[i].typename + "</option>");
                }
                $("#update>div:nth-child(2)>div:nth-child(4)>select").html("").change(function () {
                    $("input[name=upname]:last").val(parseInt($("#update>div:nth-child(2)>div:nth-child(3)>select option:selected").val()) * parseInt($("#update>div:nth-child(2)>div:nth-child(4)>select option:selected").val()));
                });
                for (var i = 0; i < data.servicetime.stvalue; i++) {
                    $("#update>div:nth-child(2)>div:nth-child(4)>select").append("<option value='" + (i + 1) + "'>" + (i + 1) + "年</option>");
                }
                //添加选择事件
                $("input[name=upname]:last").val(parseInt($("#update>div:nth-child(2)>div:nth-child(3)>select option:selected").val()) * parseInt($("#update>div:nth-child(2)>div:nth-child(4)>select option:selected").val()));
                $("#update .upSub").click(null).click(function () {
                    AppOpen.renewApp($("#update>div:nth-child(2)>div:nth-child(3)>select option:selected").attr("typeid"),$("#update").attr("kwid"),$("#update>div:nth-child(2)>div:nth-child(4)>select option:selected").val());
                });

            },
            error: function (data) {
                alert("网络异常");
            }
        });
    },
    renewApp: function (typesId, keywordId, openYear) {//类型id, 关键字标识, 续费年数
        $.ajax({
            type: "POST",
            url: "/agent/keyword/renewApp",
            data: {keywordId: keywordId,typesId:typesId,openYear:openYear},
            dataType: "json",
            success: function (data) {
                alert(data.error.message);
                if (data.error.message == "续费成功") {
                    $("#update").hide()
                }
                var e = data.keyword;
                var ec= data.client;
                $("#div_table table tr[kwid='" + data.keyword.kwid + "']").html("").append('<td>' + e.kwid + '</td><td>' + e.kwname + '</td><td>' + ec.enterprisename + '</td><td>' + e.users.name + '</td><td>' + e.applylimit + '</td><td>' + dateTo(e.lasttime) + '</td><td>' + (e.status == 0 ? '未到期' : "已过期") + '</td><td>' + (e.auditstatus == 0 ? "核审中" : e.auditstatus == 1 ? "已通过" : "未通过") + '</td><td>' + (e.usestatus == 0 ? "已使用" : "未使用") + '</td><td>' + (e.appStatus == 2 ? "已开通" : "未开通") + '</td><td class="caozuo" colspan="2"><div class="kai" kwid="' + e.kwid + '"> 开通APP</div><div class="xu" kwid="' + e.kwid + '">续费</div></td>' );

            },
            error: function (data) {
                alert("网络异常");
            }
        });
    }
};
$(function () {
    PageData.getByPageData(0);
    $(".div>div").on("click", "span[pageIndex]", function () {
        PageData.getByPageData($(this).attr("pageIndex"));
        $(this).css("background", "ghostwhite");
        $(this).siblings().css("background", "");
    });
    $("#div_table").on("click", ".kai", function () {
        if ($(this).parent().prev().html() == "已开通") {//当前关键字开通时则立即退出该方法
            return;
        }
        AppOpen.getAppData($(this).attr("kwid"));
        $("#update").hide();
        $("#insert").show();
    }).on("click", ".xu", function () {
        if ($(this).parent().prev().html() == "未开通") {//当前关键字开通时则立即退出该方法
            return;
        }
        AppOpen.getRenewAppData($(this).attr("kwid"));
        $("#insert").hide();
        $("#update").show();
    });
    $("#cha").click(function () {
        PageData.getByPageData(0);
    });
    $("#div_insert").on("click", ".esc", function () {
        $("#div_insert input[type=text]").val("");
        AppOpen.data = null;
        $("#insert").hide();
        $("#update").hide();
    });
});
