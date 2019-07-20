$(function () {
    init();
    $(".div div span ").click(function () {
        $(this).css("background","ghostwhite");
        $(this).siblings().css("background","");
    });
    $("#div_insert img").click(function () {
        $("#insert").show();
    });
    $(".esc").click(function () {
        $("input[type=text]").val("");
        $("#insert").hide();
        $("#update").hide();
    });
   /* $(".up").click(function () {
        var name=$(this).attr("name");
        var id=$(this).attr("id");
        $("#update input[name=upname]").val(name);
        $(".upSub").attr("id",id);
        $("#update").show();
    });*/
    /**
     * 点击服务类型保存按钮
     */
    $(".insertSub").click(function () {
        var  typeName=$("input[name=insertType]").val();
        var  typeValue=$("input[name=insertValue]").val();
        var  status=$("#insertSelect").val();
        if(typeName!="" ) {
            $.ajax({
                type: "GET",
                url: "/agent/serve/financeType.do",
                data: {typeName: typeName,status:status,typeValue:typeValue},
                dataType: "json",
                success: function (data) {
                    if (data == true) {
                        alert("添加成功!");
                        $("#insert").hide();
                        init();
                    } else {
                        alert("网络延迟,路线繁忙请稍后再试!");
                    }
                },
                error: function (data) {
                    alert("网络发生异常!");
                }
            })
        }else{
            alert("类型名不可以为空!");
        }
    });
    /**
     * 点击修改按钮
     */
    $("#div_table table").on("click",".up",function(){
        var name=$(this).attr("name");
        var id=$(this).attr("id");
        var value=$(this).parent().prev().prev().html();
        var status=$(this).parent().prev().html();
        if(status=="启用"){
            $("#upSelect option[value=0]").attr("selected","true");
        }else{
            $("#upSelect option[value=1]").attr("selected","true");
        }

        $("#update input[name=upType]").val(name);
        $("#update input[name=upValue]").val(value);
        $("#update input[name=id]").val(id);
        $("#update").show();
    });
    $(".upSub").click(function () {
        var  typeName=$("input[name=upType]").val();
        var  status=$("#upSelect").val();
        var   typeValue=$("input[name=upValue]").val();
        var  typeId=$("input[name=id]").val();
        if(typeName!="" ) {
            $.ajax({
                type: "GET",
                url: "/agent/serve/financeType.up",
                data: {typeName: typeName, status: status,typeValue:typeValue,typeId:typeId},
                dataType: "json",
                success: function (data) {
                    if (data == true) {
                        alert("修改成功!");
                        $("#update").hide();
                        init();
                    } else {
                        alert("网络延迟,路线繁忙请稍后再试!");
                    }
                },
                error: function (data) {
                    alert("网络发生异常!");
                }
            })
        }else{
            alert("类型名不可以为空!");
        }
    });
})
function init(){
    /*服务类型*/
    $.ajax({
        type:"GET",
        url:"/agent/serve/financeType.json",
        data:{typeParentId:26},
        dataType:"json",
        success:function(data){

            //数据
            $("#div_table table tr:gt(0)").html("");
            $(data).each(function (i,e){
                var stats;
                if(e.status==0){
                    stats="启用";
                }else{
                    stats="禁用";
                }
                $("#div_table table").append("<tr>\n" +
                    "<td>"+(i+1)+"</td>\n" +
                    "<td>"+e.typename+"</td>\n" +
                    "<td>"+e.typevalue+"</td>"+
                    "<td>"+stats+"</td>\n" +
                    "<td> <a href='javascript:void(0)' class='up' name="+e.typename+" id='"+e.typeid+"' >修改</a></td></tr>");
            });
            $("#div_table .div").html("");
            /*  //页数操作
              if(data.totalPages>1){
                  var pages=data.totalPages;
                  $("#div_table .div ").append("<div><span  names='0'>首页</span></div>");
                  for (var i=0;i<pages;i++){
                      $("#div_table .div div").append("<span  names='"+i+"'>"+(i+1)+"</span>");
                  }
                  $("#div_table .div div").append("<span   names='"+(pages-1)+"'>尾页</span>");
              }*/
        },
        error:function(data){
            alert("网络异常");
        }
    });
}