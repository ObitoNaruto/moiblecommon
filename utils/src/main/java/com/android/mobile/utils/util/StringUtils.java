package com.android.mobile.utils.util;

/**
 * Created by xinming.xxm on 2016/3/27.
 */
public class StringUtils {
    /**
     * ×Ö·û´®ÅÐ¿Õ
     * @param str
     * @return if string is null or its size is 0, return true, else return false
     */
    public static boolean isEmpty(CharSequence str) {
        return (str == null || str.length() == 0);
    }
}
