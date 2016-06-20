package com.android.mobile.utils.util.polling;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;

/**
 * Created by xinming.xxm on 2016/6/20.
 */
public class PollingUtils {

    /**
     * �ж��Ƿ������ѯ����
     *
     * @param context ������
     * @param cls     Class
     * @return
     */
    public static boolean isPollingServiceExist(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
                intent, PendingIntent.FLAG_NO_CREATE);
        return pendingIntent != null;
    }

    /**
     * ������ѯ����
     *
     * @param context  ������
     * @param interval ʱ��������λΪ��
     * @param cls      Class
     */
    public static void startPollingService(Context context, int interval,
                                           Class<?> cls) {
        startPollingService(context, interval, cls, null);
    }

    /**
     * ������ѯ����
     *
     * @param context  ������
     * @param interval ʱ��������λΪ��
     * @param cls      Class
     * @param action   Action
     */
    public static void startPollingService(Context context, int interval,
                                           Class<?> cls, String action) {
        AlarmManager manager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, cls);
        if (!TextUtils.isEmpty(action)) {
            intent.setAction(action);
        }
        PendingIntent pendingIntent = PendingIntent.getService(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long triggerAtTime = SystemClock.elapsedRealtime();
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime,
                interval * 1000, pendingIntent);
    }

    /**
     * ֹͣ��ѯ����
     *
     * @param context ������
     * @param cls     Class
     */
    public static void stopPollingService(Context context, Class<?> cls) {
        stopPollingService(context, cls, null);
    }

    /**
     * ֹͣ��ѯ����
     *
     * @param context ������
     * @param cls     Class
     * @param action  Action
     */
    public static void stopPollingService(Context context, Class<?> cls,
                                          String action) {
        AlarmManager manager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, cls);
        if (!TextUtils.isEmpty(action)) {
            intent.setAction(action);
        }
        PendingIntent pendingIntent = PendingIntent.getService(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        manager.cancel(pendingIntent);
    }
}
