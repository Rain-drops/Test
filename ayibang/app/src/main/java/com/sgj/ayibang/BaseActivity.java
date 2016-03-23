package com.sgj.ayibang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.sgj.ayibang.utils.Constant;

import cn.bmob.v3.Bmob;


/**
 * Created by John on 2016/3/22.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(getApplicationContext(), Constant.APPID);
    }
}
