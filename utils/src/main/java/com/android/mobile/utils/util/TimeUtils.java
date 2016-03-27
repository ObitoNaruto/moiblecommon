package com.android.mobile.utils.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xinming.xxm on 2016/3/27.
 */
public class TimeUtils {
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE    = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * long类型时间格式化
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long类型时间格式化，格式化格式为默认格式
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    /**
     * 获取系统当前时间，值单位为毫秒
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }
}
