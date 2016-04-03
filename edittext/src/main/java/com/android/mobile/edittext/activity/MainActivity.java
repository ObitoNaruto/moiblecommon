package com.android.mobile.edittext.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.mobile.edittext.R;


public class MainActivity extends Activity {

    private ListView mLvMain;
    private static String[] names = {
            "EditText文字输入时，左边图片改变",
            "自定义带有删除功能的文本框"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLvMain = (ListView) findViewById(R.id.lv_main);
        mLvMain.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item_main, R.id.tv, names));

        mLvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        //D-左边图片的文本框，当文字输入时改变图片，模仿微博登录框
                        Intent intent = new Intent(MainActivity.this, CopyWeiboEditTextActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        //自定义带有删除功能的edittext实例，在登录，搜索等地方经常用到可以直接用
                        Intent intent2 = new Intent(MainActivity.this, CustomizeEditTextWithDelActivity.class);
                        startActivity(intent2);
                        break;
                    default:
                        break;
                }
            }
        });

    }
}
