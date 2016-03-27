package com.sgj.ayibang.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by John on 2016/3/25.
 */
public abstract class AbsBaseFragment extends Fragment{

    protected Handler mHandler = new Handler();

    protected abstract void initActions(View paramView);
    protected abstract void initData();
    protected abstract View initView(LayoutInflater parpmLayoutInflater);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = initView(inflater);
        initActions(view);
        return view;
    }
}
