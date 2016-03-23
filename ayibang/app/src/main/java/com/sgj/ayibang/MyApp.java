package com.sgj.ayibang;

import android.app.Application;
import android.content.Context;

import com.bmob.BmobConfiguration;
import com.bmob.BmobPro;


/**
 * Created by John on 2016/3/22.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initConfig(getApplicationContext());
    }

    private void initConfig(Context context) {
        BmobConfiguration config = new BmobConfiguration.Builder(context).customExternalCacheDir("Smile").build();
        BmobPro.getInstance(context).initConfig(config);
    }
}
