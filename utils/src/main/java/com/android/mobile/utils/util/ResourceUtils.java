package com.android.mobile.utils.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by xinming.xxm on 2016/3/27.
 */
public class ResourceUtils {

    /**
     * 从assets中读取文件
     * @param context
     * @param fileName
     * @return
     */
    public static String geFileFromAssets(Context context, String fileName) {
        if (context == null || StringUtils.isEmpty(fileName)) {
            return null;
        }

        StringBuilder s = new StringBuilder("");
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *从raw中读取数据文件
     * it can be used to open drawable, sound, and raw resources; it will fail on string and color
     * resources
     * @param context
     * @param resId
     * @return
     */
    public static String geFileFromRaw(Context context, int resId) {
        if (context == null) {
            return null;
        }

        StringBuilder s = new StringBuilder();
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
