package com.android.mobile.utils.util;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.format.Formatter;

/**
 * Created by xinming.xxm on 2016/3/27.
 */
public class MemoryUtils {
    private static final String MEM_INFO_PATH = "/proc/meminfo";

    /**
     * Get memory info of device.
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static ActivityManager.MemoryInfo getMemoryInfo(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        return mi;
    }

    /**
     * Print Memory info.
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static ActivityManager.MemoryInfo printMemoryInfo(Context context) {
        ActivityManager.MemoryInfo mi = getMemoryInfo(context);
            StringBuilder sb = new StringBuilder();
            sb.append("_______  Memory :   ");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                sb.append("\ntotalMem        :").append(mi.totalMem);
            }
            sb.append("\navailMem        :").append(mi.availMem);
            sb.append("\nlowMemory       :").append(mi.lowMemory);
            sb.append("\nthreshold       :").append(mi.threshold);
        return mi;
    }

    /**
     * Get available memory info.
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static String getAvailMemory(Context context) {// ��ȡandroid��ǰ�����ڴ��С
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        // mi.availMem; ��ǰϵͳ�Ŀ����ڴ�
        return Formatter.formatFileSize(context, mi.availMem);// ����ȡ���ڴ��С���
    }
}
