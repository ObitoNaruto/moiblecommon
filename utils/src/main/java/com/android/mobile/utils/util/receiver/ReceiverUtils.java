package com.android.mobile.utils.util.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.TextUtils;

/**
 * Created by xinming.xxm on 2016/5/4.
 */
public class ReceiverUtils extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        //拦截短信
        if(TextUtils.equals(intent.getAction(), "android.provider.Telephony.SMS_RECEIVED")){
            System.out.println("短信收到了");

            Object[] pdus = (Object[]) intent.getExtras().get("pdus");
            for(Object pdu : pdus)
            {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                String sender = smsMessage.getOriginatingAddress();
                String body = smsMessage.getMessageBody();
                System.out.println("sender=" + sender);
                System.out.println("body=" + body);

                if("5554".equals(sender))
                {
                    //短信拦截
                    abortBroadcast();
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(sender, null, "去死吧", null, null);
                }
            }
        }
    }
}
