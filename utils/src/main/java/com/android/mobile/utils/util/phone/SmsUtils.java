package com.android.mobile.utils.util.phone;

import android.telephony.SmsManager;

import java.util.ArrayList;

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

}
