package com.sgj.ayibang;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.bmob.BmobConfiguration;
import com.bmob.BmobPro;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.sgj.ayibang.utils.Constant;

import java.util.Locale;

import cn.bmob.im.bean.BmobMsg;
import cn.bmob.sms.BmobSMS;


/**
 * Created by John on 2016/3/22.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initConfig(getApplicationContext());

//        Resources resources = getResources();
//        Configuration configuration = resources.getConfiguration();
//        configuration.locale = Locale.ENGLISH;
//        DisplayMetrics metrics = resources.getDisplayMetrics();
//        resources.updateConfiguration(configuration, metrics);
    }

    private void initConfig(Context context) {
        BmobConfiguration config = new BmobConfiguration.Builder(context).customExternalCacheDir("Smile").build();
        BmobPro.getInstance(context).initConfig(config);
        // 短信
        BmobSMS.initialize(context, Constant.APPID);
        // 图片( Fresco )
        Fresco.initialize(this);
    }
}
