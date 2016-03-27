package com.android.mobile.utils.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by xinming.xxm on 2016/3/27.
 */
public class DisplayUtils {
    /**
     * ��pxֵת����Ϊdip����dpֵ����֤�ߴ��С����
     * @param context
     * @param pxValue
     * @return
     */
    public static float pxTodip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxValue /scale + 0.5f);
    }

    /**
     * ��dip����dpֵת����pxֵ����֤�ߴ��С����
     * @param context
     * @param dipVlaue
     * @return
     */
    public static float dipToPx(Context context, float dipVlaue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dipVlaue * scale + 0.5f);
    }

    /**
     * ��dip����dpֵת����pxֵ,���ط���
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
     * ��pxֵת����spֵ
     * @param context
     * @param pxVlaue
     * @return
     */
    public static float pxToSp(Context context, float pxVlaue){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (pxVlaue / fontScale + 0.5f);
    }

    /**
     * ��spֵת��Ϊpxֵ
     * @param context
     * @param spValue
     * @return
     */
    public static float spToPx(Context context, float spValue){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (spValue * fontScale + 0.5f);
    }

    /**
     * ��spֵת��Ϊpxֵ ,���ط���
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
}
