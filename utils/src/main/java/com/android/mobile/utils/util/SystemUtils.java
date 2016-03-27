package com.android.mobile.utils.util;

/**
 * Created by xinming.xxm on 2016/3/27.
 */
public class SystemUtils {
    /**
     * 获取推荐的默认线程池size
     * @param max
     * @return
     */
    public static int getDefaultThreadPoolSize(int max) {
        int availableProcessors = 2 * Runtime.getRuntime().availableProcessors() + 1;
        return availableProcessors > max ? max : availableProcessors;
    }
}
