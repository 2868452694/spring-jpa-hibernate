/**
 * 时间戳格式化函数
 */
function dateTo(timestamp){
    var date = new Date(timestamp);
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds();
    var datetimeresult=Y+M+D+h+m+s;
    return datetimeresult;
}
