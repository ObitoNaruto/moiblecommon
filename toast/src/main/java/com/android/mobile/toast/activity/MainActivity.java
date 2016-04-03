package com.android.mobile.toast.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.mobile.toast.R;


public class MainActivity extends Activity {

    private ListView mLvMain;
    private static String[] names = {
            "自定义彩色Toast"
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
                        //自定义彩色Toast
                        Intent intent = new Intent(MainActivity.this, CustomizeToastActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });

    }
}
