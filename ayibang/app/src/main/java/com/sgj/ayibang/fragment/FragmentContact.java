package com.sgj.ayibang.fragment;

import android.app.FragmentTransaction;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.sgj.ayibang.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/3/30.
 */
public class FragmentContact extends Fragment {

    @Bind(R.id.sv_surfaceview)
    SurfaceView mSurfaceView;
    SurfaceHolder mSurfaceHolder;

    private Timer mTimer;
    private MyTimerTask mTimerTask;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_surface, container, false);
        ButterKnife.bind(this, view);

        mSurfaceHolder = mSurfaceView.getHolder();




        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private class MyTimerTask extends TimerTask{

        @Override
        public void run() {

        }
    }

    private void SimpleDraw(){


    }

    private void ClearDraw(){

    }
}
