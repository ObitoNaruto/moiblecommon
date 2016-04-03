package com.android.mobile.toast.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.android.mobile.toast.R;
import com.android.mobile.toast.model.AppMsg;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH;

/**
 * Created by xinming.xxm on 2016/4/3.
 */
public class CustomizeToastActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_color_toast_main);
    }

    public void showAppMsg(View v) {
        final CharSequence msg = ((Button) v).getText();
        final AppMsg.Style style;
        // 选择显示的样式
        switch (v.getId()) {
            // 警告
            case R.id.alert:
                style = AppMsg.STYLE_ALERT;
                break;
            // 确认
            case R.id.confirm:
                style = AppMsg.STYLE_CONFIRM;
                break;
            // 信息
            case R.id.info:
                style = AppMsg.STYLE_INFO;
                break;
            // 自定义
            case R.id.custom:
                style = new AppMsg.Style(AppMsg.LENGTH_SHORT, R.color.custom);
                break;

            default:
                return;
        }
        // 创建AppMsg对象，并定义显示的信息和样式
        AppMsg appMsg = AppMsg.makeText(this, msg, style);
        // 设置Toast显示的位置,默认显示在屏幕的最上方
        if (((CheckBox) (findViewById(R.id.bottom))).isChecked()) {
            appMsg.setLayoutGravity(Gravity.CENTER);
        }
        appMsg.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 对于14+的版本这是可选的，
        // 你也可能想在以后方便的调用它，例如在onDestroy()中
        if (SDK_INT < ICE_CREAM_SANDWICH) {
            AppMsg.cancelAll(this);
        }
    }
}
