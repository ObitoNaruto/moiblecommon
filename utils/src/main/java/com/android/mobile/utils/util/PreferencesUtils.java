package com.android.mobile.utils.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xinming.xxm on 2016/3/27.
 */
public class PreferencesUtils {
    public static String PREFERENCE_NAME = "ObitoMobileCommon";

    /**
     * 存储字符串数据到SharedPreferences中去
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * 获取存储在SharedPreferences中的字符串(有默认值)
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    /**
     * 获取存储在SharedPreferences中的字符串(无默认值)
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    /**
     * 存储int数据到SharedPreferences中去
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }

    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }

    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, defaultValue);
    }

    public static long getLong(Context context, String key) {
        return getLong(context, key, -1);
    }

    public static boolean putFloat(Context context, String key, float value) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    public static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getFloat(key, defaultValue);
    }

    public static float getFloat(Context context, String key) {
        return getFloat(context, key, -1);
    }

    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }
}
