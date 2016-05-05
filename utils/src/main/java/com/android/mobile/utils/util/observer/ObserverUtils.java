package com.android.mobile.utils.util.observer;

/**
 * Created by xinming.xxm on 2016/4/27.
 */
public class ObserverUtils {
    /**
     * need permission:<uses-permission android:name="android.permission.READ_SMS"/>
     */
    public static void userContentResolver(){
//        //1.第一步
//        ContentResolver resolver = getContentResolver();
//        Uri uri = Uri.parse("content://sms/");
//        resolver.registerContentObserver(uri, true, new MyObserver(new Handler()));
//
//        //2.第二步
//        class MyObserver extends ContentObserver
//        {
//
//            public MyObserver(Handler handler)
//            {
//                super(handler);
//
//            }
//
//            //当内容观察者观察到了数据库的内容变化时调用这个方法
//            //观察到消息邮箱里有一条数据库内容发生变化的通知
//            @Override
//            public void onChange(boolean selfChange)
//            {
//                super.onChange(selfChange);
////                Toast.makeText(MainActivity.this, "数据库的内容变化了", Toast.LENGTH_SHORT).show();
//
//                ContentResolver resolver = getContentResolver();
//                Uri uri = Uri.parse("content://sms/");
//                Cursor cursor = resolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, null);
//                cursor.moveToFirst();
//                String address = cursor.getString(0);
//                long date = cursor.getLong(1);
//                int type = cursor.getInt(2);
//                String body = cursor.getString(3);
//                System.out.println("address = " + address + " : " + "body = " + body);
//                cursor.close();
//
//            }
//        }
    }
}
