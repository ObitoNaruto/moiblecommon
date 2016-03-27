package com.android.mobile.utils.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

/**
 * Created by xinming.xxm on 2016/3/27.
 */
public class DisplayUtils {
    /**
     * 将px值转换成为dip或者dp值，保证尺寸大小不变
     * @param context
     * @param pxValue
     * @return
     */
    public static float pxTodip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxValue /scale + 0.5f);
    }

    /**
     * 将dip或者dp值转换成px值，保证尺寸大小不变
     * @param context
     * @param dipVlaue
     * @return
     */
    public static float dipToPx(Context context, float dipVlaue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dipVlaue * scale + 0.5f);
    }

    /**
     * 将dip或者dp值转换成px值,重载方法
     * @param context
     * @param dipVlaue
     * @return
     */
    public static float dipToPx2(Context context, float dipVlaue){
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dipVlaue,
                context.getResources().getDisplayMetrics());
    }

    /**
     * 将px值转换成sp值
     * @param context
     * @param pxVlaue
     * @return
     */
    public static float pxToSp(Context context, float pxVlaue){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (pxVlaue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值
     * @param context
     * @param spValue
     * @return
     */
    public static float spToPx(Context context, float spValue){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (spValue * fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值 ,重载方法
     * @param context
     * @param spValue
     * @return
     */
    public static float spToPx2(Context context, float spValue){
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                spValue,
                context.getResources().getDisplayMetrics() );
    }

    /**
     * 获取 显示信息
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm;
    }

    /**
     * 打印 显示信息
     */
    public static DisplayMetrics printDisplayInfo(Context context) {
        DisplayMetrics dm = getDisplayMetrics(context);
            StringBuilder sb = new StringBuilder();
            sb.append("_______  显示信息:  ");
            sb.append("\ndensity         :").append(dm.density);
            sb.append("\ndensityDpi      :").append(dm.densityDpi);
            sb.append("\nheightPixels    :").append(dm.heightPixels);
            sb.append("\nwidthPixels     :").append(dm.widthPixels);
            sb.append("\nscaledDensity   :").append(dm.scaledDensity);
            sb.append("\nxdpi            :").append(dm.xdpi);
            sb.append("\nydpi            :").append(dm.ydpi);
        return dm;
    }
}
