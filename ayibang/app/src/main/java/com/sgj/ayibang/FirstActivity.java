package com.sgj.ayibang;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.sgj.ayibang.utils.LocationUtils;

import java.lang.ref.WeakReference;

/**
 * Created by John on 2016/3/22.
 */
public class FirstActivity extends AppCompatActivity implements AMapLocationListener{

    public static final String FIRST = "First";
    private static final String TAG = "FirstActivity";
    private Context mContext;

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;

    private final MyHandler myHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mContext = this;
        initLacation();

        SharedPreferences preferences = getSharedPreferences(FIRST, MODE_PRIVATE);
        if(preferences.getBoolean(FIRST, true)){
            mHandler.sendEmptyMessageDelayed(SWITCH_GUIDACTIVITY, 1000);
        }else {

            mHandler.sendEmptyMessageDelayed(SWITCH_MAINACTIVITY,1000);
        }

    }

    private void initLacation() {
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = new AMapLocationClientOption();
        // 设置定位模式为低功耗模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        // 设置定位监听
        locationClient.setLocationListener(this);
        //单次定位
        locationOption.setOnceLocation(true);

        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
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

    @Override
    public void onLocationChanged(AMapLocation loc) {
        if (null != loc) {
            Message msg = handler.obtainMessage();
            msg.obj = loc;
            msg.what = LocationUtils.MSG_LOCATION_FINISH;
            handler.sendMessage(msg);
        }
    }

    Handler handler = new Handler() {
        public void dispatchMessage(android.os.Message msg) {
            switch (msg.what) {
                //开始定位
                case LocationUtils.MSG_LOCATION_START:
//                    tvReult.setText("正在定位...");
                    break;
                // 定位完成
                case LocationUtils.MSG_LOCATION_FINISH:
                    AMapLocation loc = (AMapLocation) msg.obj;
                    String result = LocationUtils.getLocationStr(mContext, loc);
                    Log.d(TAG, result);
//                    tvReult.setText(result);
                    break;
                //停止定位
                case LocationUtils.MSG_LOCATION_STOP:
//                    tvReult.setText("定位停止");
                    break;
                default:
                    break;
            }
        };
    };

    private static class MyHandler extends Handler{

        private final WeakReference<FirstActivity> mWeakReference;

        public MyHandler(FirstActivity activity) {
            mWeakReference = new WeakReference<FirstActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            FirstActivity activity = mWeakReference.get();
            if(activity != null){

            }
        }
    }

    private static final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {

        }
    };
}
