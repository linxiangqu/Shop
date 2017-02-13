package com.linxiangqu.shop.utils;

import android.util.Log;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @文件名 DateUtil.java
 * @作者 ico
 * @创建日期 2014-11-14
 * @版本 V 1.0
 */
public class DateUtil {
    public static String[] weeks = new String[]{"一", "二", "三", "四", "五", "六", "日"};

    public static int getDay(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Long time = Long.valueOf(date);
        String d = format.format(time);
        String[] day1 = d.split("-");
        int day = Integer.valueOf(day1[2]) - 1;
        return day;
    }

    /**
     * 根据传入的格式，返回字符串格式的当前时间
     *
     * @param format
     * @return String
     */
    public static String getCurrentTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间的毫秒类型
     *
     * @return Long
     */
    public static Long getCurrentTimes() {
        Timestamp time = new Timestamp(new Date().getTime());
        return time.getTime();
    }

    /**
     * 将Timestamp类型的时间，根据传入的格式，转换为字符串格式
     *
     * @param milliseconds
     * @param format
     * @return String
     */
    public static String fromTimestamp(long milliseconds, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(milliseconds);
    }

    /**
     * 获取Timestamp类型的当前时间
     *
     * @return Timestamp
     */
    public static Timestamp getCurrentTime() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * 将Timestamp类型中的毫秒提取并返回
     *
     * @param time
     * @return int
     */
    public static int getMsel(Timestamp time) {
        SimpleDateFormat sdf = new SimpleDateFormat("SSS");
        String msel = sdf.format(new Date(time.getTime()));
        return Integer.valueOf(msel);
    }

    /**
     * 将传入的Timestamp时间和当前时间进行比较，根据时差返回字符串数据
     * 本周外返回yyyy-MM-dd
     * 本周内返回星期weeks
     * 相差一天的返回昨天
     * 当天显示HH:mm
     *
     * @param time
     * @return String
     */
    public static String getHumanize(Timestamp time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm");
        int time1 = Integer.valueOf(sdf2.format(time.getTime()));
        int nowTime = Integer.valueOf(getCurrentTime("yyyyMMdd"));
        if (nowTime - time1 == 0) {
            return sdf3.format(time.getTime());
        } else if (nowTime - time1 == 1) {
            return "昨天";
        } else if (nowTime - time1 > 1
                && nowTime - time1 < getCurrentTime().getDay()) {
            return "星期" + weeks[nowTime - time1 - 1];
        } else {
            return sdf1.format(time.getTime());
        }
    }

    //格式化字符串
    public static class Format {
        /**
         * 从年一直到毫秒
         */
        public final static String YEAR2MSEL = "yyyy-MM-dd HH:mm:ss:SSS";
        /**
         * 从年到秒
         */
        public final static String YEAR2SEC = "yyyy-MM-dd HH:mm:ss";

        /**
         * 从年到日
         */
        public final static String YEAR2DAY = "yyyy-MM-dd";
        /**
         * 从分钟到秒
         */
        public final static String MM2SS = "mm:ss";
        /**
         * 从年到分钟
         */
        public final static String YEAR2MM = "yyyy-MM-dd HH:mm";

        /**
         * 从小时到秒
         */
        public final static String HH2SS = "HH:mm:ss";

        /**
         * 从小时到毫秒
         */
        public final static String HH2SSS = "HH:mm:ss:SSS";

        /**
         * 文件常用的
         */
        public final static String FILE = "yyyyMMdd_HHmmssSSS";
    }

    //获取当前日期
    public String getToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String date = sdf.format(d);
        return date;
    }

    //截取本月
    public String getCurrentMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String t = sdf.format(d);
        String m = t.substring(4, 6);
        return m;
    }

    //截取本年
    public String getCurrentYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String t = sdf.format(d);
        String y = t.substring(0, 4);
        return y;
    }


    //获取昨天的日期
    public String getDateOfYesterday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        long t = c.getTimeInMillis();
        long l = t - 24 * 3600 * 1000;
        Date d = new Date(l);
        String s = sdf.format(d);
        return s;
    }

    //获取上个月的第一天
    public String getFirstDayOfLastMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = "";
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1); //set the date to be 1
        lastDate.add(Calendar.MONTH, -1);//reduce a month to be last month
//		lastDate.add(Calendar.DATE,-1);//reduce one day to be the first day of last month

        str = sdf.format(lastDate.getTime());
        return str;
    }

    // 获取上个月的最后一天
    public String getLastDayOfLastMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = "";
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);//
        lastDate.add(Calendar.MONTH, -1);//
        lastDate.roll(Calendar.DATE, -1);//
        str = sdf.format(lastDate.getTime());
        return str;
    }

    //获取本月第一天
    public String getFirstDayOfThisMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = "";
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);//
//		lastDate.add(Calendar.MONTH,-1);//
//		lastDate.add(Calendar.DATE,-1);//

        str = sdf.format(lastDate.getTime());
        return str;
    }

    //获取本月最后一天
    public String getLastDayOfThisMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = "";
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);//
        lastDate.add(Calendar.MONTH, 1);//
        lastDate.add(Calendar.DATE, -1);//

        str = sdf.format(lastDate.getTime());
        return str;
    }

    //判断闰年
    public static boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return true;
        }
        return false;
    }
}
