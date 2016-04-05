package com.sgj.ayibang.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sgj.ayibang.R;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/4/5.
 */
public class FragmentSetting extends Fragment implements View.OnClickListener{

    @Bind(R.id.ll_language)
    LinearLayout ll_language;

    public static FragmentSetting newInstance(){
        FragmentSetting setting = new FragmentSetting();
        return setting;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        ll_language.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_language:
//                showDialog();
                break;
        }
    }

    public void showDialog(){
        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("切换语言环境")
                .setMessage("英文？").setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Resources resources = getResources();
                        Configuration configuration = resources.getConfiguration();
                        configuration.locale = Locale.ENGLISH;
                        DisplayMetrics metrics = resources.getDisplayMetrics();
                        resources.updateConfiguration(configuration, metrics);
                    }
                }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Resources resources = getResources();
                        Configuration configuration = resources.getConfiguration();
                        configuration.locale = Locale.CHINESE;
                        DisplayMetrics metrics = resources.getDisplayMetrics();
                        resources.updateConfiguration(configuration, metrics);
                    }
                }).create();
        dialog.show();
    }
}
