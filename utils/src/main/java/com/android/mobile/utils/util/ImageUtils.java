package com.android.mobile.utils.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by xinming.xxm on 2016/3/27.
 */
public class ImageUtils {
    /**
     * Bitmap 转换成字节数组
     * @param b
     * @return
     */
    public static byte[] bitmapToByte(Bitmap b) {
        if (b == null) {
            return null;
        }

        ByteArrayOutputStream o = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, o);
        return o.toByteArray();
    }

    /**
     * 字节数组转换成Bitmap
     * @param b
     * @return
     */
    public static Bitmap byteToBitmap(byte[] b) {
        return (b == null || b.length == 0) ? null : BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * Drawable转换成Bitmap
     * @param d
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable d) {
        return d == null ? null : ((BitmapDrawable)d).getBitmap();
    }

    /**
     * Bitmap专程成Drawable
     * @param res
     * @param b
     * @return
     */
    public static Drawable bitmapToDrawable(Resources res, Bitmap b) {
        return b == null ? null : new BitmapDrawable(res, b);
    }

    /**
     * Drawable转换成字节数组
     * 原理：先将Drawable专程成Bitmap，然后将Bitmap专程字节数组
     * @param d
     * @return
     */
    public static byte[] drawableToByte(Drawable d) {
        return bitmapToByte(drawableToBitmap(d));
    }

    /**
     * 字节数组转换成rawable
     * 原理：先将字节数组转换成Bitmap，然后将Bitmap转换成Drawable
     * @param res
     * @param b
     * @return
     */
    public static Drawable byteToDrawable(Resources res, byte[] b) {
        return bitmapToDrawable(res, byteToBitmap(b));
    }

    public static Bitmap getBitmapFromStream(InputStream is){
        Bitmap b = BitmapFactory.decodeStream(is);
        return b;
    }

    public static Drawable getDrawableFromStream(InputStream is){
        Drawable d = Drawable.createFromStream(is, "src");
        return d;
    }

    /**
     * scale image
     * @param org
     * @param scaleWidth
     * @param scaleHeight
     * @return
     */
    public static Bitmap scaleImage(Bitmap org, float scaleWidth, float scaleHeight) {
        if (org == null) {
            return null;
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(org, 0, 0, org.getWidth(), org.getHeight(), matrix, true);
    }
}
