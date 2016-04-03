package com.android.mobile.dialog.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.mobile.dialog.R;

/**
 * Created by xinming.xxm on 2016/4/3.
 */
public class BaseStyleDialogActivity extends Activity implements View.OnClickListener{
    private Button btn1, btn2, btn3, btn4, btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basestyle_dialog);
        initView();
    }

    private void initView(){
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                //确定取消对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(BaseStyleDialogActivity.this);
                builder.setTitle("对话框标题");
                builder.setMessage("提示是否退出");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(BaseStyleDialogActivity.this, "确定按钮被点击", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(BaseStyleDialogActivity.this, "取消按钮被点击", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
                break;
            case R.id.btn2:
                //pick对话框
                AlertDialog.Builder builder2 = new AlertDialog.Builder(BaseStyleDialogActivity.this);
                builder2.setTitle("选择一个人");
                final String[] arr = new String[]{"张三", "李四", "王五"};
                builder2.setItems(arr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(BaseStyleDialogActivity.this, arr[which], Toast.LENGTH_SHORT).show();
                    }
                });
                builder2.create().show();
                break;
            case R.id.btn3:
                //带选择的单选按钮对话框
                AlertDialog.Builder builder3 = new AlertDialog.Builder(BaseStyleDialogActivity.this);
                builder3.setTitle("选择一个颜色");
                final String[] items = new String[]{"蓝色", "黄色", "红色"};
                builder3.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(BaseStyleDialogActivity.this, items[which], Toast.LENGTH_SHORT).show();
                    }
                });
                builder3.create().show();
                break;
            case R.id.btn4:
                //多选对话框
                AlertDialog.Builder builder4 = new AlertDialog.Builder(BaseStyleDialogActivity.this);
                builder4.setTitle("选择若干颜色");
                final String[] items4 = new String[]{"蓝色", "黄色", "红色"};
                builder4.setMultiChoiceItems(items4, new boolean[]{false, false, true}, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        Toast.makeText(BaseStyleDialogActivity.this, items4[which], Toast.LENGTH_SHORT).show();
                    }
                });
                builder4.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(BaseStyleDialogActivity.this, "确定", Toast.LENGTH_SHORT).show();
                    }
                });
                builder4.create().show();
                break;
            case R.id.btn5:
                //进度条
                ProgressDialog pd = new ProgressDialog(BaseStyleDialogActivity.this);
                pd.setTitle("提示");
                pd.setMessage("正在加载中...");
                //设置带进度条的
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd.setMax(100);

                pd.show();
                pd.setProgress(50);
                break;
            default:
                break;
        }
    }
}
