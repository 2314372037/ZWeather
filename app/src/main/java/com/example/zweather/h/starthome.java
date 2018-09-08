package com.example.zweather.h;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class starthome extends AppCompatActivity {

    private final int startTime=500;//启动延时(毫秒)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starthome);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar bar= getSupportActionBar();
        if (bar!=null)bar.hide();
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(startTime);
                go();
            }
        }).start();
    }
    private void go()//跳转
    {
        Intent intent=new Intent(starthome.this,HomeActivity.class);
        intent.putExtra("state",false);
        starthome.this.startActivity(intent);
        starthome.this.finish();
    }
}
