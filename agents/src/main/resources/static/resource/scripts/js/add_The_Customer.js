//判断是操作是哪个操作:修改 添加
var init = {
    isAddOrChange: function () {//判断是什么操作
        var portalId = url.getParam("portalId");
        init.commonData();
        if (portalId == null) {
            init.isAdd();
        } else {
            init.isChange(portalId);
        }
    },
    commonData: function () {
        $.ajax({
            type: "GET",
            url: "/agent/protal/getEnterpriseTypes",
            dataType: "json",
            success: function (data) {
                $("#u4_input").html("");
                for (var i = 0; i < data.length; i++) {
                    var e = data[i];
                    $("#u4_input").append("<option value='" + e.typeid + "'>" + e.typename + "</option>");
                }
            },
            error: function (data) {
                alert("网络异常");
            }
        });
        $.ajax({
            type: "GET",
            url: "/agent/protal/getPapersTypes",
            dataType: "json",
            success: function (data) {
                $("#u6_input").html("");
                for (var i = 0; i < data.length; i++) {
                    var e = data[i];
                    $("#u6_input").append("<option value='" + e.typeid + "'>" + e.typename + "</option>");
                }
            },
            error: function (data) {
                alert("网络异常");
            }
        });
    },
    getData: function () {
        var data = {
            portal: {
                portalid:$("#div_context").attr("pid"),
                corporate: $("input[name='法人代表']").val(),  	    // 法人代表
                paperstype: $("#u6_input option:selected").val(), 	    //证件类型 u6_input
                papersvalue: $("input[name='证件号码']").val(),	    //证件值
                telephone: $("input[name='公司电话']").val(),   	//公司电话
                fax: $("input[name='公司传真']").val(),   		    //传真
                country: $("input[name='国家']").val(),         //国家
                province: $("input[name='省']").val(),   	    //省
                crty: $("input[name='市']").val(),   		    //城市
                district: $("input[name='区']").val(),   	    //区县
                address: $("input[name='公司地址']").val(),   	    //公司地址
                remark: $("input[name='备注']").val()           //备注
            }, client: {
                clientid:$("#div_context").attr("cid"),
                enterprisename: $("input[name='企业姓名']").val(),   	    //企业姓名
                enterprisetype: $("#u4_input option:selected").val(),   	//企业类型
                enterpriseurl: $("input[name='企业主页']").val(),   	    //企业主页
                status: $("#u5_input option:selected").val()              //使用状态
            }, linkmans: []
        };
        var $linkmansTR = $("#div_context form table tbody tr");
        for (var i = 0; i < $linkmansTR.length; i++) {
            data.linkmans[i] = {
                linkmanid:$("table>tbody>tr:nth-child(" + (i + 1) + ")").attr("linkmanid"),
                name: $("table>tbody>tr:nth-child(" + (i + 1) + ")>td:nth-child(1)>input").val(),   		//联系人姓名
                telephone: $("table>tbody>tr:nth-child(" + (i + 1) + ")>td:nth-child(2)>input").val(),   	//电话
                fax: $("table>tbody>tr:nth-child(" + (i + 1) + ")>td:nth-child(3)>input").val(),   		    //传真
                email: $("table>tbody>tr:nth-child(" + (i + 1) + ")>td:nth-child(4)>input").val(),   		//邮箱
                duty: $("table>tbody>tr:nth-child(" + (i + 1) + ")>td:nth-child(5)>input").val()		    //职务
            };
        }
        return data;
    },
    isAdd: function () {//操作为添加时的操作

        //提交事件
        $("#u14").click(function () {
            //发送到服务器
            $.ajax({
                type: "POST",
                url: "/agent/protal/insert",
                data: {jsonStr: JSON.stringify(init.getData())},
                dataType: "json",
                success: function (data) {
                    if (data == null) {//添加失败
                        alert("添加失败");
                    } else {//添加成功
                        alert("添加成功");
                        location.href = "/agent/page/agent_Customer_Management.do";
                    }
                },
                error: function (data) {
                    alert("网络异常");
                }
            });

        });
    },
    isChange: function (portalId) {//操作为修改时的操作

        //查询出数据并赋值
        $.ajax({
            type: "POST",
            url: "/agent/protal/getPortalAllInfo",
            data: {portalid: portalId},
            dataType: "json",
            success: function (data) {
                $("input[name='法人代表']").val(data.portal.corporate);  	    // 法人代表
                $("#u6_input option[value=" + data.portal.paperstype + "]").attr("selected", "selected");	    //证件类型 u6_input
                $("input[name='证件号码']").val(data.portal.papersvalue);    //证件值
                $("input[name='公司电话']").val(data.portal.telephone); 	//公司电话
                $("input[name='公司传真']").val(data.portal.fax);		    //传真
                $("input[name='国家']").val(data.portal.country);       //国家
                $("input[name='省']").val(data.portal.province); 	    //省
                $("input[name='市']").val(data.portal.crty);	    //城市
                $("input[name='区']").val(data.portal.district);   	    //区县
                $("input[name='公司地址']").val(data.portal.address);	    //公司地址
                $("input[name='备注']").val(data.portal.remark);         //备注

                $("input[name='企业姓名']").val(data.client.enterprisename);  	    //企业姓名
                $("#u4_input option[value=" + data.client.enterprisetype + "]").attr("selected", "selected");  	//企业类型
                $("input[name='企业主页']").val(data.client.enterpriseurl);  	    //企业主页
                $("#u5_input option[value=" + data.client.status + "]").attr("selected", "selected");           //使用状态

                $("#div_context").attr("pid",data.portal.portalid).attr("cid",data.client.clientid);
                $("#div_context table tbody").html("");
                for (var i = 0; i < data.linkmans.length; i++) {
                    var e = data.linkmans[i];
                    $("#div_context table tbody").append(" <tr linkmanid='" + e.linkmanid + "'>\n" +
                        "                    <td><input name=\"linkmanName\" type=\"text\" value='" + e.name + "'></td>\n" +
                        "                    <td><input name=\"linkmanTelephone\" type=\"text\" value='" + e.telephone + "'></td>\n" +
                        "                    <td><input name=\"linkmanFax\" type=\"text\" value='" + e.fax + "'></td>\n" +
                        "                    <td><input name=\"linkmanEmail\" type=\"text\" value='" + e.email + "'></td>\n" +
                        "                    <td><input name=\"linkmanDuty\" type=\"text\" value='" + e.duty + "'></td>\n" +
                        "                    <td class='del'>删除</td>\n" +
                        "                </tr>");
                }
            },
            error: function (data) {
                alert("网络异常");
            }
        });
        //查询portalID
        //提交事件
        $("#u14").click(function () {
            //发送到服务器

            $.ajax({
                type: "POST",
                url: "/agent/protal/update",
                data: {jsonStr: JSON.stringify(init.getData()),delLinkmanIds:JSON.stringify(init.deleteLinkmanIds)},
                dataType: "json",
                success: function (data) {
                    if (data == null) {//添加失败
                        alert("更新失败");
                    } else {//添加成功
                        alert("更新成功");
                        location.href = "/agent/page/agent_Customer_Management.do";
                    }
                },
                error: function (data) {
                    alert("网络异常");
                }
            });
        });
    }
};
var url = {//有关url的方法
    getParam: function (paramName) {//获取指定参数 ParamName参数名
        var result = null;
        //&拆分字符串
        var daolenSplit = location.search.split("&");
        for (var i = 0; i < daolenSplit.length; i++) {
            //=拆分字符串
            var dengSplit = daolenSplit[i].split("=")
            if (dengSplit[0] == paramName || dengSplit[0] == "?" + paramName) {
                result = dengSplit[1];
                break;
            }
        }
        return result;
    }
};

$(function () {
    init.isAddOrChange();
    $("#button").click(function () {
        $("#div_context").toggle();
    });
    $("#u13").click(function () {
        history.back();
    });
    $(".addLinkman").click(function () {
        $("#div_context form table tbody ").append('<tr>\n' +
            '                    <td><input name="linkmanName" type="text"></td>\n' +
            '                    <td><input name="linkmanTelephone" type="text"></td>\n' +
            '                    <td><input name="linkmanFax" type="text"></td>\n' +
            '                    <td><input name="linkmanEmail" type="text"></td>\n' +
            '                    <td><input name="linkmanDuty" type="text"></td>\n' +
            '                    <td class="del">删除</td>\n' +
            '                </tr>')
    });
    $("#div_context").on("click",".del",function () {
        var a=[];
        init.deleteLinkmanIds=init.deleteLinkmanIds==null?a:init.deleteLinkmanIds;
        init.deleteLinkmanIds[init.deleteLinkmanIds.length]=$(this).parents("tr").attr("linkmanid");
        $(this).parents("tr").remove();
    } )
});