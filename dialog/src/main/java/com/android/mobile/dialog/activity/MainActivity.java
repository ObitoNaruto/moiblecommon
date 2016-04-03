package com.android.mobile.dialog.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.mobile.dialog.R;


public class MainActivity extends Activity {
    private ListView mLvMain;
    private static String[] names = {
            "几种基本的对话框",
            "仿QQ头像选择弹出对话框"
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
                        //基本的对话框
                        Intent intent = new Intent(MainActivity.this, BaseStyleDialogActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        //仿QQ头像选择弹出对话框
                        Intent intent1 = new Intent(MainActivity.this, CopyQQPhotoDialogActivity.class);
                        startActivity(intent1);
                        break;
                    default:
                        break;
                }
            }
        });

    }

}
