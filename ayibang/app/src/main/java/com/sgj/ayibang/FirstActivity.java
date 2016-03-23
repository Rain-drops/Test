package com.sgj.ayibang;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by John on 2016/3/22.
 */
public class FirstActivity extends AppCompatActivity {

    public static final String FIRST = "First";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        SharedPreferences preferences = getSharedPreferences(FIRST, MODE_PRIVATE);
        if(preferences.getBoolean(FIRST, true)){
            mHandler.sendEmptyMessageDelayed(SWITCH_GUIDACTIVITY, 1000);
        }else {

            mHandler.sendEmptyMessageDelayed(SWITCH_MAINACTIVITY,1000);
        }

    }

    private final static int SWITCH_MAINACTIVITY = 1000;
    private final static int SWITCH_GUIDACTIVITY = 1001;
    public Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            switch(msg.what){
                case SWITCH_MAINACTIVITY:
                    Intent mIntent = new Intent();
                    mIntent.setClass(FirstActivity.this, MainActivity.class);
                    FirstActivity.this.startActivity(mIntent);
                    FirstActivity.this.finish();
                    break;
                case SWITCH_GUIDACTIVITY:
                    mIntent = new Intent();
                    mIntent.setClass(FirstActivity.this, GuideActivity.class);
                    FirstActivity.this.startActivity(mIntent);
                    FirstActivity.this.finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
