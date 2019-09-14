package com.zqx.java.springboot.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * 日期相关工具类
 * @JDK1.8 or later
 */
public class DateUtils {

    private DateUtils(){}

    public static final String FORMAT_yyyy = "yyyy"; // 年
    public static final String FORMAT_yyyy_MM = "yyyy-MM"; // 年-月
    public static final String FORMAT_yyyy_MM_dd = "yyyy-MM-dd"; // 年-月-日
    public static final String FORMAT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss"; // 年-月-日 时:分:秒
    public static final String FORMAT_yyyy_MM_dd_HH = "yyyy-MM-dd HH"; // 年-月-日 时 24制
    public static final String FORMAT_yyyy_MM_dd_hh = "yyyy-MM-dd hh"; // 年-月-日 时 12制
    public static final String FORMAT_yyyy_MM_dd_000000 = "yyyy-MM-dd 00:00:00";
    public static final String FORMAT_yyyy_MM_01_000000 = "yyyy-MM-01 00:00:00";
    public static final String FORMAT_yyyy_01_01_000000 = "yyyy-01-01 00:00:00";


    /**********************************JDK1.8及以后的时间操作*******************************************/


    /**
     * 将日期类型转为字符串
     * @param date Date日期
     * @param pattern 格式化形式
     * @return String日期字符串
     */
    public static String dateToStr(Date date, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String dateStr = localDateTime.format(formatter);
        return dateStr;
    }

    /**
     * 字符串转换成日期
     * @param source  源日期字符串
     * @param pattern 待格式化形式
     * @param zoneId  时区
     * @return java.util.Date类型日期
     */
    public static Date strToDate(String source, String pattern, ZoneId zoneId) {
        LocalDateTime localDateTime = LocalDateTime.parse(source, DateTimeFormatter.ofPattern(pattern));
        if(Objects.isNull(zoneId)) {
            zoneId = ZoneId.systemDefault();
        }
        Instant instant = localDateTime.atZone(zoneId).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * 将java.util.Date转换为java.time.LocalDateTime
     * @param date Date类型日期
     * @param zoneId 时区ID
     * @return
     */
    private static LocalDateTime transferTo(Date date, ZoneId zoneId){
        if(Objects.isNull(zoneId)) {
            zoneId = ZoneId.systemDefault();
        }
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), zoneId);
        return localDateTime;
    }

    public static void main(String[] args) {
        System.out.println();
    }

}
