$(function () {
    alert("sss");
    bindLogInfo(page);
    startPage();
})
var page=0;
function bindLogInfo(page){
    $.ajax({
        type:"GET",
        url:"/agent/log/findLog.json",
        data:{pageSum:page},
        dataType:"json",
        success:function (data) {
            $("#div_table table tr:gt(0)").html("");
            $.each(data.content,function(i,e){
                $("table").append("<tr>" +
                                    "<td>"+(i+1)+"</td>" +
                                    "<td>"+e.users.username+"</td>" +
                                    "<td>"+e.loginfo+"</td>" +
                                    "<td>"+e.logtime+"</td></tr>");
            });
            $("#div_table .div").html("");
            //页数操作
            if(data.totalPages>1){
                var pages=data.totalPages;
                var pagecount=10;
                $("#div_table .div ").append("<div><span  names='0'>首页</span></div>");
                for (var i=0;i<pages;i++){
                    if(page>0){
                        if(page>4) {
                            if(i==0) {
                                var numpage = page - 4;
                                for (var j = numpage; j < page; j++) {
                                    $("#div_table .div div").append("<span  names='" + j + "'>" + (j + 1) + "</span>");
                                    i = j;
                                    pagecount--;
                                }
                            }else{
                                $("#div_table .div div").append("<span  names='" + i+ "'>" + (i+ 1) + "</span>");
                                pagecount--;
                            }
                        }else{
                            if(i==0) {
                                for (var j = 0; j < page; j++) {
                                    $("#div_table .div div").append("<span  names='" + j + "'>" + (j + 1) + "</span>");
                                    i = j;
                                    pagecount--;
                                }
                            }else{
                                $("#div_table .div div").append("<span  names='" + i + "'>" + (i + 1) + "</span>");
                                pagecount--;
                            }
                        }
                    }else{
                        $("#div_table .div div").append("<span  names='" + i + "'>" + (i + 1) + "</span>");
                        pagecount--;
                    }
                    if(pagecount==0){
                        break;
                    }
                }
                $("#div_table .div div").append("<span   names='"+(pages-1)+"'>尾页</span>");
            }
        },
        error:function (data) {
            alert("网络延迟");
        }
    })
}

/**
 * 点击页码
 */
function startPage(){
    $("#div_table .div").on("click","div span",function () {
        page=$(this).attr("names");
        alert(page);
        bindLogInfo(page);
    })
};
