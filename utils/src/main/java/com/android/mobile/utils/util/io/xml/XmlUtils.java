package com.android.mobile.utils.util.io.xml;

import android.os.Environment;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinming.xxm on 2016/4/27.
 */
public class XmlUtils {
    private static List<SmsInfo> smsInfos;

    /**
     * need permission:<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
     */
    public static void backSms(){
        // 假设已经获得了所有的短信
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        sb.append("<smss>");
        for (SmsInfo info : smsInfos)
        {
            sb.append("<sms>");
            sb.append("<date>");
            sb.append(info.getDate());
            sb.append("</date>");
            sb.append("<type>");
            sb.append(info.getType());
            sb.append("</type>");
            sb.append("<body>");
            sb.append(info.getBody());
            sb.append("</body>");
            sb.append("<address>");
            sb.append(info.getAddress());
            sb.append("</address>");
            sb.append("</sms>");
        }
        sb.append("</smss>");
        try
        {
            File file = new File(Environment.getExternalStorageDirectory(),
                    "backup.xml");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(sb.toString().getBytes());
            fos.close();
            //备份成功
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //备份失败
        }
    }

    /**
     * 第二种方式生成xml文件
     *need permission:<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
     */
    public static void backSms2()
    {
        try
        {
            XmlSerializer serializer = Xml.newSerializer();
            File file = new File(Environment.getExternalStorageDirectory(),
                    "backup2.xml");
            FileOutputStream fos = new FileOutputStream(file);
            // 初始化序列化器，指定xml数据写入哪个文件，并指定文件的编码格式
            serializer.setOutput(fos, "utf-8");
            serializer.startDocument("utf-8", true);
            serializer.startTag(null, "smss");
            for (SmsInfo info : smsInfos)
            {
                serializer.startTag(null, "sms");
                serializer.attribute(null, "id", info.getId() + "");

                serializer.startTag(null, "date");
                serializer.text(info.getDate() + "");
                serializer.endTag(null, "date");

                serializer.startTag(null, "type");
                serializer.text(info.getType() + "");
                serializer.endTag(null, "type");

                serializer.startTag(null, "body");
                serializer.text(info.getBody());
                serializer.endTag(null, "body");

                serializer.startTag(null, "address");
                serializer.text(info.getAddress() + "");
                serializer.endTag(null, "address");

                serializer.endTag(null, "sms");
            }

            serializer.endTag(null, "smss");
            serializer.endDocument();

            fos.close();
            //备份成功

        }
        catch (Exception e)
        {
            e.printStackTrace();
            //备份失败
        }
    }

    public static List<SmsInfo> getWeatherInfos(InputStream is) throws Exception
    {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        List<SmsInfo> smsInfos = null;
        SmsInfo smsInfo = null;
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT)
        {
            switch (type)
            {
                case XmlPullParser.START_TAG:
                    if ("smss".equals(parser.getName()))// 解析到全局开始标签
                    {
                        smsInfos = new ArrayList<SmsInfo>();
                    }
                    else if ("sms".equals(parser.getName()))
                    {
                        smsInfo = new SmsInfo();
                        String idStr = parser.getAttributeValue(0);
                        smsInfo.setId(Integer.parseInt(idStr));
                    }
                    else if ("date".equals(parser.getName()))
                    {
                        String date = parser.nextText();
                        smsInfo.setDate(Long.parseLong(date));
                    }
                    else if ("type".equals(parser.getName()))
                    {
                        String type2 = parser.nextText();
                        smsInfo.setType(Integer.parseInt(type2));
                    }
                    else if ("body".equals(parser.getName()))
                    {
                        String body = parser.nextText();
                        smsInfo.setBody(body);
                    }
                    else if ("address".equals(parser.getName()))
                    {
                        String address = parser.nextText();
                        smsInfo.setAddress(address);
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if("sms".equals(parser.getName()))//一个城市的信息已经处理完了
                    {
                        smsInfos.add(smsInfo);
                        smsInfo = null;
                    }
                    break;
            }

            type = parser.next();
        }
        return smsInfos;
    }

}
