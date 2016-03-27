package com.android.mobile.utils.util;

/**
 * Created by xinming.xxm on 2016/3/27.
 */
public class ObjectUtils {
    /**
     * 比较两个对象是否相等
     * @param actual 待比较对象
     * @param expected 比较对象
     * @return if both are null or , return true; else compare two object by using Object.equals(Object)
     */
    public static boolean isEquals(Object actual, Object expected){
        return actual == expected || (null == actual ? null == expected : actual.equals(expected));
    }

    /**
     * 对象转换成字符串
     * @param str
     * @return if str is null, return ""
     */
    public static String objectToString(Object str) {
        return (null == str ? "" : (str instanceof String ? (String)str : str.toString()));
    }

    /**
     * 比较两个对象
     * 规则：
     * if v1 > v2, return 1
     * if v1 = v2, return 0
     * if v1 < v2, return -1
     * @param v1
     * @param v2
     * @param <V>
     * @return
     * if v1 is null, v2 is null, then return 0
     * if v1 is null, v2 is not null, then return -1
     * if v1 is not null, v2 is null, then return 1
     * return v1.{@link Comparable#compareTo(Object)}
     */
    public static <V> int compare(V v1, V v2) {
        return v1 == null ? (v2 == null ? 0 : -1) : (v2 == null ? 1 : ((Comparable)v1).compareTo(v2));
    }
}
