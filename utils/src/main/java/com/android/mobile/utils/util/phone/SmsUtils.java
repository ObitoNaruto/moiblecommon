package com.android.mobile.utils.util.phone;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.SmsManager;

import com.android.mobile.utils.util.io.xml.SmsInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinming.xxm on 2016/4/27.
 */
public class SmsUtils {
    /**
     * 发送短信
     * @param number 手机号码
     * @param content 短信内容
     * need permission:<uses-permission android:name="android.permission.SEND_SMS"/>
     */
    public static void sendTextMessage(String number, String content){
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> contents = smsManager.divideMessage(content);
        for(String str : contents){
            smsManager.sendTextMessage(number, null, str, null, null);
        }
    }

    /**读取系统短信
     * @param context
     * need permission: <uses-permission android:name="android.permission.READ_SMS"/>
     */
    public static void readSystemSms(Context context){
        Uri uri =  Uri.parse("content://sms/");
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, null);
        List<SmsInfo> smsInfos = new ArrayList<>();
        while(cursor.moveToNext())
        {
            String address = cursor.getString(0);
            long date = cursor.getLong(1);
            int type = cursor.getInt(2);
            String body = cursor.getString(3);

            SmsInfo smsInfo = new SmsInfo(date, type, body, address);
            smsInfos.add(smsInfo);
        }
        cursor.close();
    }

    /**
     * 生成一条短信
     * @param context
     * need permission:<uses-permission android:name="android.permission.WRITE_SMS"/>
     */
    public static void insertSystemSms(Context context){
        ContentResolver resolver = context.getContentResolver();
        Uri uri = Uri.parse("content://sms");
        ContentValues values = new ContentValues();
        values.put("address", "15833227282");
        values.put("type", 1);
        values.put("date", System.currentTimeMillis());
        values.put("body", "牛逼！测试一下汉字短信乱码不？");
        resolver.insert(uri, values);
    }

}
