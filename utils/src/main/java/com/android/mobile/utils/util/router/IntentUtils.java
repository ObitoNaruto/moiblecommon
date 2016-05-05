package com.android.mobile.utils.util.router;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by xinming.xxm on 2016/5/4.
 */
public class IntentUtils {

    public static void openNetPage(Context context){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.baidu.com"));
        context.startActivity(intent);
    }
}
