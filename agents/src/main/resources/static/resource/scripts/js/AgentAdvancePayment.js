$(function () {
    bindFinanceType();
    InitadvancePay();
    FindadvancePay();
})

/**
 * 绑定财务类型
 */
function bindFinanceType(){
    $.ajax({
        type:"GET",
        url:"/agent/finance/financeType.json",
        data:{typeParentId:27},
        dataType:"json",
        async:false,
        success:function (data) {
            $("#u2_input").append(" <option value='0'>请选择</option>");
            $.each(data,function (i,e) {
                $("#u2_input").append("<option value="+e.typeid+">"+e.typename+"</option>")
            })
        },
        error:function (data) {
            alert("网络异常");
        }
    })
}

var page=0;

/**
 * 窗体加载时显示财务信息
 * @constructor
 */
function InitadvancePay(){
    var finatype=$("#u2_input option:selected").val();
    alert(finatype);
    var time1=$("#time1").val();
    alert(time1);
    var time2=$("#time2").val();
    $.ajax({
        type:"GET",
        url:"/agent/user/UsersDetail",
        data:{finatype:finatype,createTime1:time1,createTime2:time2,pageSum:page},
        dataType: "json",
        success:function (data) {
            $("#tr").siblings().remove();
            $.each(data.content,function (i,e) {
                $("table").append("<tr>" +
                                    "<td>"+i+1+"</td>" +
                                    "<td>"+e.finatype+"</td>" +
                                    "<td>"+e.operationmoney+"</td>" +
                                    "<td>"+e.balance+"</td>" +
                                    "<td>"+e.description+"</td>" +
                                    "<td>"+e.createtime+"</td></tr>")
            })
        },
        error:function (data) {
            alert("网络异常");
        }
    })
}

/**
 * 点击查询绑定预付款信息
 */
function FindadvancePay(){
    $("#img").click(function () {
        alert("Sss");
        InitadvancePay();
    })
}
