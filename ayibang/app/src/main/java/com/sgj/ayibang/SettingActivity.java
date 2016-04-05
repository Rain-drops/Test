package com.sgj.ayibang;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.sgj.ayibang.fragment.FragmentSetting;

/**
 * Created by John on 2016/4/5.
 */
public class SettingActivity extends BaseActivity {

    FragmentSetting mFragmentSetting;
    public static final String TAG_SETTING = "Setting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mFragmentSetting = FragmentSetting.newInstance();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.fl_setting, mFragmentSetting, TAG_SETTING);

        transaction.commit();


    }
}
