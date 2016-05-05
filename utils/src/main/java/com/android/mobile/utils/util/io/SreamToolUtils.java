package com.android.mobile.utils.util.io;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by xinming.xxm on 2016/5/4.
 */
public class SreamToolUtils {

    public static String readInputStream(InputStream is){

        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while(-1 != (len = is.read(buffer)))
            {
                baos.write(buffer, 0, len);
            }
            is.close();
            baos.close();
            byte[] result = baos.toByteArray();
            String temp = new String(result);
            return temp;
        }
        catch (Exception e){
            e.printStackTrace();
            return "获取失败";
        }
    }
}
