package cn.agent.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * 将制定添加指定年限后返回
     * @param date 指定时间
     * @param year 添加的年 负数可减去年数
     * @return 添加后的时间
     */
    public static Date addYear(Date date, Long year){
        c.setTime( date );
        int inyear= Math.toIntExact( year );
        c.add( Calendar.YEAR, inyear);
        return c.getTime();
    }
    private static Calendar c = Calendar.getInstance();
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 将字符串时间转换成java.util.Date类型 按照  yyyy-MM-dd HH:mm:ss
     * @param date
     * @return java.util.Date类型的时间
     * @throws ParseException
     */
    public static Date strToDate(String date) throws ParseException {
        return format.parse( date );
    }
}
