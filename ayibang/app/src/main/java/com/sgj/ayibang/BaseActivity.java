package com.sgj.ayibang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.sgj.ayibang.utils.CityUtils;
import com.sgj.ayibang.utils.Constant;
import com.sgj.ayibang.utils.LocationUtils;

import java.security.Key;

import cn.bmob.v3.Bmob;


/**
 * Created by John on 2016/3/22.
 */
public class BaseActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(getApplicationContext(), Constant.APPID);

//        CityUtils utils = new CityUtils(this);
//        utils.getCity();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
