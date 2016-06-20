package com.android.mobile.utils.util;

import java.util.Locale;

/**
 * Created by xinming.xxm on 2016/3/27.
 */
public class StringUtils {
    /**
     * �ַ����п�
     * @param str
     * @return if string is null or its size is 0, return true, else return false
     */
    public static boolean isEmpty(CharSequence str) {
        return (str == null || str.length() == 0);
    }

    /**
     * �ַ���ת����
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * byte[]����ת��Ϊ16���Ƶ��ַ���
     *
     * @param data Ҫת�����ֽ�����
     * @return ת����Ľ��
     */
    public static final String byteArrayToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.getDefault());
    }

    /**
     * 16���Ʊ�ʾ���ַ���ת��Ϊ�ֽ�����
     *
     * @param s 16���Ʊ�ʾ���ַ���
     * @return byte[] �ֽ�����
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] d = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // ��λһ�飬��ʾһ���ֽ�,��������ʾ��16�����ַ�������ԭ��һ�������ֽ�
            d[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return d;
    }

    /**
     * ���������ַ��������и����Ĺؼ��ֱ��
     *
     * @param sourceString �������ַ���
     * @param keyword      �����Ĺؼ���
     * @return ���ص��Ǵ�Html��ǩ���ַ�������ʹ��ʱҪͨ��Html.fromHtml()ת��ΪSpanned�����ٴ��ݸ�TextView����
     */
    public static String keywordMadeRed(String sourceString, String keyword) {
        String result = "";
        if (sourceString != null && !"".equals(sourceString.trim())) {
            if (keyword != null && !"".equals(keyword.trim())) {
                result = sourceString.replaceAll(keyword,
                        "<font color=\"red\">" + keyword + "</font>");
            } else {
                result = sourceString;
            }
        }
        return result;
    }

    /**
     * Ϊ�������ַ������HTML��ɫ��ǣ���ʹ��Html.fromHtml()��ʽ��ʾ��TextView ��ʱ���佫�Ǻ�ɫ��
     *
     * @param string �������ַ���
     * @return
     */
    public static String addHtmlRedFlag(String string) {
        return "<font color=\"red\">" + string + "</font>";
    }
}
