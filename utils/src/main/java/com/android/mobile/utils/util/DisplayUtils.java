package com.android.mobile.utils.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.inputmethod.InputMethodManager;

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

    /**
     * 获取dialog宽度
     *
     * @param activity Activity
     * @return Dialog宽度
     */
    public static int getDialogW(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = activity.getResources().getDisplayMetrics();
        int w = dm.widthPixels - 100;
        // int w = aty.getWindowManager().getDefaultDisplay().getWidth() - 100;
        return w;
    }

    /**
     * 获取屏幕宽度
     *
     * @param activity Activity
     * @return 屏幕宽度
     */
    public static int getScreenW(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = activity.getResources().getDisplayMetrics();
        int w = dm.widthPixels;
        // int w = aty.getWindowManager().getDefaultDisplay().getWidth();
        return w;
    }

    /**
     * 获取屏幕高度
     *
     * @param activity Activity
     * @return 屏幕高度
     */
    public static int getScreenH(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = activity.getResources().getDisplayMetrics();
        int h = dm.heightPixels;
        // int h = aty.getWindowManager().getDefaultDisplay().getHeight();
        return h;
    }

    /**
     * Toggle keyboard If the keyboard is visible,then hidden it,if it's
     * invisible,then show it
     *
     * @param context 上下文
     */
    public static void toggleKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
