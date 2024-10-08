package com.paradise.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private static final String TIME_FORMAT = "HH:mm:ss";

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter DATE_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 格式化日期
     *
     * @param date
     * @return yyyy-MM-dd
     */
    public static String getFormatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(date);
    }

    /**
     * 格式化时间
     *
     * @param date
     * @return HH:mm:ss
     */
    public static String getFormatTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
        return format.format(date);
    }

    /**
     * 格式化日期及时间
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getFormatDateTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        return format.format(date);
    }

    /**
     * 格式化日期
     *
     * @param localDate
     * @return
     */
    public static String getFormatDate(LocalDate localDate) {
        return localDate.format(DATE_FORMATTER);
    }

    /**
     * 格式化日期
     *
     * @param localDate
     * @return
     */
    public static String getFormatMonth(LocalDate localDate) {
        return localDate.format(DATE_MONTH_FORMATTER);
    }


    /**
     * 格式化时间
     *
     * @param localDateTime
     * @return
     */
    public static String getFormatTime(LocalDateTime localDateTime) {
        return localDateTime.format(TIME_FORMATTER);
    }

    /**
     * 格式化日期及时间
     *
     * @param localDateTime
     * @return
     */
    public static String getFormatDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

    /**
     * 日期时间字符串转LocalDateTime
     *
     * @param dateStr
     * @return
     */
    public static LocalDateTime dateStrToLocalDateTime(String dateStr) {
        return LocalDateTime.parse(dateStr, DATE_TIME_FORMATTER);
    }

    /**
     * 日期字符串转LocalDate
     *
     * @param dateStr
     * @return
     */
    public static LocalDate dateStrToLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    /**
     * 日期字符串 转 Date
     *
     * @param dateStr
     * @return
     */
    public static Date dateStrToDate(String dateStr) {
        return Date.from(LocalDate.parse(dateStr, DATE_FORMATTER).atStartOfDay(ZoneId.systemDefault()).toInstant());

    }

    /**
     * 日期时间字符串 转 Date
     *
     * @param dateStr
     * @return
     */
    public static Date dateTimeStrToDate(String dateStr) {
        return Date.from(LocalDateTime.parse(dateStr, DATE_TIME_FORMATTER).atZone(ZoneId.systemDefault()).toInstant());

    }

    /**
     * LocalDate 转 Date
     *
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime 转 Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime 转 yyyymm
     *
     * @param localDateTime
     * @return
     */
    public static String localYearMonth(LocalDateTime localDateTime) {
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return dateToLocalDate(date).format(DATE_MONTH_FORMATTER);
    }

    /**
     * LocalDateTime 转 yyyymmdd
     *
     * @param localDateTime
     * @return
     */
    public static String localYearMonthDay(LocalDateTime localDateTime) {
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return dateToLocalDate(date).format(DATE_FORMATTER);
    }



    /**
     * Date 转 LocalDate
     *
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date 转 LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 计算两个日期时间差
     *
     * @param start yyyy-MM-dd HH:mm:ss
     * @param end   yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long dateDifference(String start, String end) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        try {
            Date startTime = format.parse(start);
            Date endTime = format.parse(end);
            return endTime.getTime() - startTime.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 计算两个日期之间的天数差
     *
     * @param start 2018-03-01 12:00:00
     * @param end   2018-03-12 12:00:00
     * @return
     */
    public static long calculationDays(String start, String end) {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date fromDate = simpleFormat.parse(start);
            Date toDate = simpleFormat.parse(end);
            long from = fromDate.getTime();
            long to = toDate.getTime();
            long days = (int) ((to - from) / (1000 * 60 * 60 * 24));
            return days;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 计算小时差
     *
     * @param start
     * @param end
     * @return
     */
    public static long calculationHours(String start, String end) {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date fromDate = simpleFormat.parse(start);
            Date toDate = simpleFormat.parse(end);
            long from = fromDate.getTime();
            long to = toDate.getTime();
            long hours = (int) ((to - from) / (1000 * 60 * 60));
            return hours;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 计算分钟差
     *
     * @param start
     * @param end
     * @return
     */
    public static long calculationMinutes(String start, String end) {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date fromDate = simpleFormat.parse(start);
            Date toDate = simpleFormat.parse(end);
            long from = fromDate.getTime();
            long to = toDate.getTime();
            long minutes = (int) ((to - from) / (1000 * 60));
            return minutes;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * 计算两个日期之间的秒数差
     *
     * @param start
     * @param end
     * @return
     */
    public static long calculationSecond(String start, String end) {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date fromDate = simpleFormat.parse(start);
            Date toDate = simpleFormat.parse(end);
            long from = fromDate.getTime();
            long to = toDate.getTime();
            long seconds = (int) ((to - from) / 1000);
            return seconds;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
     *
     * @param time
     * @param number
     * @param field
     * @return
     */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }


    /**
     * 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
     *
     * @param time
     * @param number
     * @param field
     * @return
     */
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field) {
        return time.minus(number, field);
    }


    /**
     * 根据field不同加减不同值
     *
     * @param date
     * @param field  Calendar.YEAR
     * @param number 1000/-1000
     */
    public static Date calculationDate(Date date, int field, int number) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(field, number);
        return calendar.getTime();
    }


    /**
     * 比较第一个日期是否大于第二个日期
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return true-大于;false-不大于
     */
    public boolean localDateIsBefore(LocalDate firstDate, LocalDate secondDate) {
        return firstDate.isBefore(secondDate);
    }

    /**
     * 获取当天剩下秒数
     *
     * @return
     * @throws Exception
     */
    public static long getTimeSeconds() throws Exception {
        //获取当天还剩下多少秒
        Date sDate = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = df.format(sDate) + " 23:59:59";
        Date eDate = sdf.parse(format);
        long eTime = eDate.getTime();
        long sTime = sDate.getTime();
        long time = (eTime - sTime) / 1000;
        return time;
    }

    public static String getDateYyyyMmDd(String date){
        String[] a = date.split("-");
        String b = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i].length()==1) {
                b = b+"-0"+a[i];
            }else{
                b = b+"-"+a[i];
            }
        }
        return b;
    }

}
