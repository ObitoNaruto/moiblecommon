package com.android.mobile.utils.util.phone;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xinming.xxm on 2016/3/27.
 */
public class TelephoneUtils {
    /**
     * IMSI�ǹ����ƶ��û�ʶ����ļ��(International Mobile Subscriber Identity)
     * IMSI����15λ����ṹ���£�
     * MCC+MNC+MIN
     * MCC��Mobile Country Code���ƶ������룬��3λ���й�Ϊ460;
     * MNC:Mobile NetworkCode���ƶ������룬��2λ
     * ���й����ƶ��Ĵ���Ϊ��00��02����ͨ�Ĵ���Ϊ01�����ŵĴ���Ϊ03
     * ���������ǣ�Ҳ��Android�ֻ���APN�����ļ��еĴ��룩��
     * �й��ƶ���46000 46002
     * �й���ͨ��46001
     * �й����ţ�46003
     * ������һ�����͵�IMSI����Ϊ460030912121001
     */
    public static String getIMSI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String IMSI = telephonyManager.getSubscriberId();
        return IMSI;
    }

    /**
     * IMEI��International Mobile Equipment Identity �������ƶ��豸��ʶ���ļ��
     * IMEI��15λ������ɵġ����Ӵ��š�������ÿ̨�ֻ�һһ��Ӧ�����Ҹ�����ȫ����Ψһ��
     * �����Ϊ��
     * 1. ǰ6λ��(TAC)�ǡ��ͺź�׼���롱��һ��������
     * 2. ���ŵ�2λ��(FAC)�ǡ����װ��š���һ��������
     * 3. ֮���6λ��(SNR)�ǡ����š���һ���������˳���
     * 4. ���1λ��(SP)ͨ���ǡ�0�壬Ϊ�����룬Ŀǰ�ݱ���
     */
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String IMEI = telephonyManager.getDeviceId();
        return IMEI;
    }

    public static String printTelephoneInfo(Context context) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);
        StringBuilder sb = new StringBuilder();
        sb.append("_______ �ֻ���Ϣ  ").append(time).append(" ______________");
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String IMSI = tm.getSubscriberId();
        //IMSIǰ����λ460�ǹ��Һ��룬��ε���λ����Ӫ�̴��ţ�00��02���й��ƶ���01����ͨ��03�ǵ��š�
        String providerName = null;
        if (IMSI != null) {
            if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
                providerName = "�й��ƶ�";
            } else if (IMSI.startsWith("46001")) {
                providerName = "�й���ͨ";
            } else if (IMSI.startsWith("46003")) {
                providerName = "�й�����";
            }
        }
        sb.append(providerName).append("  �ֻ��ţ�").append(tm.getLine1Number()).append(" IMSI�ǣ�").append(IMSI);
        sb.append("\nDeviceID(IMEI)       :").append(tm.getDeviceId());
        sb.append("\nDeviceSoftwareVersion:").append(tm.getDeviceSoftwareVersion());
        sb.append("\ngetLine1Number       :").append(tm.getLine1Number());
        sb.append("\nNetworkCountryIso    :").append(tm.getNetworkCountryIso());
        sb.append("\nNetworkOperator      :").append(tm.getNetworkOperator());
        sb.append("\nNetworkOperatorName  :").append(tm.getNetworkOperatorName());
        sb.append("\nNetworkType          :").append(tm.getNetworkType());
        sb.append("\nPhoneType            :").append(tm.getPhoneType());
        sb.append("\nSimCountryIso        :").append(tm.getSimCountryIso());
        sb.append("\nSimOperator          :").append(tm.getSimOperator());
        sb.append("\nSimOperatorName      :").append(tm.getSimOperatorName());
        sb.append("\nSimSerialNumber      :").append(tm.getSimSerialNumber());
        sb.append("\ngetSimState          :").append(tm.getSimState());
        sb.append("\nSubscriberId         :").append(tm.getSubscriberId());
        sb.append("\nVoiceMailNumber      :").append(tm.getVoiceMailNumber());

        return sb.toString();
    }

    /**
     * �绰������
     * @param number
     * need permission:<uses-permission android:name="android.permission.CALL_PHONE"/>
     */
    public static void callPhone(Context context, String number){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        context.startActivity(intent);

    }
}
