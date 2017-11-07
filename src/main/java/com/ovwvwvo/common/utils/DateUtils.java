package com.ovwvwvo.common.utils;

import android.content.res.Resources;

import com.ovwvwvo.common.AppWrapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static String getStringToday(String dateFormat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(currentTime);
    }

    public static String dateToString(Date date, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }

    /**
     * 将字符串型日期转换成日期
     */
    public static Date stringToDate(String dateStr, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 两个时间点的间隔时长（分钟）(靠前的时间必须在前，靠后的必须在后)
     *
     * @return 两个时间点的间隔时长（分钟）
     */
    public static long compareMin(Date before, Date after) {
        if (before == null || after == null) {
            return 0l;
        }
        long dif = 0;
        if (after.getTime() >= before.getTime()) {
            dif = after.getTime() - before.getTime();
        } else if (after.getTime() < before.getTime()) {
            dif = after.getTime() + 86400000 - before.getTime();
        }
        dif = Math.abs(dif);
        return dif / 60000;
    }

    /**
     * 获取指定时间间隔分钟后的时间
     *
     * @param date 指定的时间
     * @param min  间隔分钟数
     * @return 间隔分钟数后的时间
     */
    public static Date addMinutes(Date date, int min) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, min);
        return calendar.getTime();
    }

    /**
     * 时间戳转换为日期
     */
    public static String String2Date(String currentTimeMillis) {

        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(Long
            .parseLong(currentTimeMillis)));
    }

    /**
     * 时间戳转换为时间
     */
    public static String String2Time(String currentTimeMillis) {

        return new SimpleDateFormat("HH:mm").format(new Date(Long
            .parseLong(currentTimeMillis)));
    }

    public static String formatDate(Date date, String format) {
        String result = null;
        Resources resources = AppWrapper.getInstance().getAppContext().getResources();
        Locale locale = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            locale = resources.getConfiguration().getLocales().get(0);
        } else
            locale = resources.getConfiguration().locale;

        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format, locale);
            try {
                result = dateFormat.format(date);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return result;
    }
}
