package com.android.mobile.utils.util.location;

import android.text.TextUtils;
import android.util.Log;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created by xinming.xxm on 2016/6/20.
 */
public class GoogleLocationUtils {

    /**
     * ���ݵ�ַ��ȡ��Ӧ�ľ�γ��
     *
     * @param address ��ַ��Ϣ
     * @return ��γ������
     */
    public static double[] getLocationInfo(String address) {
        if (TextUtils.isEmpty(address)) {
            return null;
        }
        // ����һ��HttpClient��������ָ����ַ��������
        HttpClient client = new DefaultHttpClient();
        // ��ָ����ַ����GET����
        HttpGet httpGet = new HttpGet("http://maps.google."
                + "com/maps/api/geocode/json?address=" + address
                + "ka&sensor=false");
        StringBuilder sb = new StringBuilder();
        try {
            // ��ȡ����������Ӧ
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            // ��ȡ��������Ӧ��������
            InputStream stream = entity.getContent();
            int b;
            // ѭ����ȡ��������Ӧ
            while ((b = stream.read()) != -1) {
                sb.append((char) b);
            }
            // �����������ص��ַ���ת��ΪJSONObject����
            JSONObject jsonObject = new JSONObject(sb.toString());
            // ��JSONObject������ȡ������λ�õ�location����
            JSONObject location = jsonObject.getJSONArray("results")
                    .getJSONObject(0).getJSONObject("geometry")
                    .getJSONObject("location");
            // ��ȡ������Ϣ
            double longitude = location.getDouble("lng");
            // ��ȡγ����Ϣ
            double latitude = location.getDouble("lat");
            // �����ȡ�γ����Ϣ���double[]����
            return new double[]{longitude, latitude};
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ���ݾ�γ�Ȼ�ȡ��Ӧ�ĵ�ַ
     *
     * @param longitude ����
     * @param latitude  γ��
     * @param lang      ���� ���λ����Ĭ��en
     * @return ��ַ��Ϣ
     * @throws Exception
     */
    public static String getAddress(double longitude, double latitude,
                                    String lang) throws Exception {
        if (DEBUG) {
            LogUtils.d(TAG, "location : (" + longitude + "," + latitude + ")");
        }
        if (lang == null) {
            lang = "en";
        }
        // �趨����ĳ�ʱʱ��
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
        HttpConnectionParams.setSoTimeout(params, 10 * 1000);
        // ����һ��HttpClient��������ָ����ַ��������
        HttpClient client = new DefaultHttpClient(params);
        // ��ָ����ַ����GET����
        HttpGet httpGet = new HttpGet("https://maps.googleapis.com/maps/api/"
                + "geocode/json?latlng=" + latitude + "," + longitude
                + "&sensor=false&language=" + lang);
        StringBuilder sb = new StringBuilder();
        // ִ������
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        // ��ȡ��������Ӧ���ַ���
        InputStream stream = entity.getContent();
        int b;
        while ((b = stream.read()) != -1) {
            sb.append((char) b);
        }
        // �ѷ�������Ӧ���ַ���ת��ΪJSONObject
        JSONObject jsonObj = new JSONObject(sb.toString());
        Log.d("ConvertUtil", "getAddress:" + sb.toString());
        // ��������Ӧ����еĵ�ַ����
        JSONObject addressObject = jsonObj.getJSONArray("results")
                .getJSONObject(0);
        String address = decodeLocationName(addressObject);
        return address;
    }

    /**
     * ����Google API ���������Һͳ�������
     * https://developers.google.com/maps/documentation/geocoding
     *
     * @param jsonObject ��ַJson����
     * @return ���ع��Һͳ���
     */
    public static String decodeLocationName(JSONObject jsonObject) {
        JSONArray jsonArray;
        String country = "", city = "";
        String TYPE_COUNTRY = "country";
        String TYPE_LOCALITY = "locality";
        String TYPE_POLITICAL = "political";
        boolean hasCountry = false;
        try {
            jsonArray = jsonObject.getJSONArray("address_components");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                JSONArray types = jo.getJSONArray("types");
                boolean hasLocality = false, hasPolicical = false;

                for (int j = 0; j < types.length(); j++) {
                    String type = types.getString(j);
                    if (type.equals(TYPE_COUNTRY) && !hasCountry) {
                        country = jo.getString("long_name");
                    } else {
                        if (type.equals(TYPE_POLITICAL)) {
                            hasPolicical = true;
                        }
                        if (type.equals(TYPE_LOCALITY)) {
                            hasLocality = true;
                        }
                        if (hasPolicical && hasLocality) {
                            city = jo.getString("long_name");
                        }
                    }
                }
            }
            return city + "," + country;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jsonObject.has("formatted_address")) {
            try {
                return jsonObject.getString("formatted_address");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
