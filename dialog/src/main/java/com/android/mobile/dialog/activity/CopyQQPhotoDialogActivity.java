package com.android.mobile.dialog.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.android.mobile.dialog.R;

/**
 * Created by xinming.xxm on 2016/4/3.
 */
public class CopyQQPhotoDialogActivity extends Activity implements View.OnClickListener{
    //触发弹出对话框的按钮
    private Button mBtnPopup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_qq_photo);
        mBtnPopup = (Button)findViewById(R.id.btn);
        mBtnPopup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showDialog();
    }

    private void showDialog() {
        View view = getLayoutInflater().inflate(R.layout.photo_choose_dialog, null);
        Dialog dialog = new Dialog(this, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        Window window = dialog.getWindow();
        //设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();
        //以下两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        //设置显示位置
        dialog.onWindowAttributesChanged(wl);
        //设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
}
