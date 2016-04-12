package com.sgj.ayibang.viewselect;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sgj.ayibang.R;
import com.sgj.ayibang.utils.NetUtils;

/**
 * Created by John on 2016/3/24.
 */
public class ViewSelectorLayout extends FrameLayout implements View.OnClickListener {

    private View contentView;//目标界面
    private View failView; //失败界面
    private View emptyView;//数据为空界面
    private View loadingView;//加载界面
    private TextView tv_reload;

    Context mContext;

    private ReLoadCallbackListener callbackListener;

    public ViewSelectorLayout(Context context, View view) {
        super(context);
        this.contentView = view;
        this.mContext = context;
        initView();
        handleView();
    }

    public ViewSelectorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewSelectorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void handleView() {
        add_AllView();
        hide_AllView();
    }

    private void add_AllView() {
        this.addView(failView);
        this.addView(loadingView);
        this.addView(emptyView);
        this.addView(contentView);
    }

    private void initView() {
        if(failView == null){
            failView = inflaterView(R.layout.view_fail);
            tv_reload = (TextView) failView.findViewById(R.id.tv_reload);
            tv_reload.setOnClickListener(this);
        }
        if(emptyView == null){
            emptyView = inflaterView(R.layout.view_empty);
        }
        if(loadingView == null){
            loadingView = inflaterView(R.layout.view_loading);
        }
    }

    private View inflaterView(int layoutId) {
        return LayoutInflater.from(getContext()).inflate(layoutId, null);
    }

    private void hide_AllView() {
        if(failView != null){
            failView.setVisibility(INVISIBLE);
        }
        if(loadingView != null){
            loadingView.setVisibility(INVISIBLE);
        }
        if(emptyView != null){
            emptyView.setVisibility(INVISIBLE);
        }
        if(contentView != null){
            contentView.setVisibility(INVISIBLE);
        }
    }

    public void show_ContentView()
    {
        if(contentView != null)
        {
            contentView.setVisibility(VISIBLE);
        }
        if(failView != null)
        {
            failView.setVisibility(INVISIBLE);
        }
        if(loadingView != null)
        {
            loadingView.setVisibility(INVISIBLE);
        }
        if(emptyView != null)
        {
            emptyView.setVisibility(INVISIBLE);
        }

    }

    public void show_FailView()
    {
        if(failView != null)
        {
            failView.setVisibility(VISIBLE);
        }
        if(loadingView != null)
        {
            loadingView.setVisibility(INVISIBLE);
        }
        if(emptyView != null)
        {
            emptyView.setVisibility(INVISIBLE);
        }
        if(contentView != null)
        {
            contentView.setVisibility(INVISIBLE);
        }
    }

    public void show_EmptyView()
    {
        if(emptyView != null)
        {
            emptyView.setVisibility(VISIBLE);
        }

        if(failView != null)
        {
            failView.setVisibility(INVISIBLE);
        }
        if(loadingView != null)
        {
            loadingView.setVisibility(INVISIBLE);
        }

        if(contentView != null)
        {
            contentView.setVisibility(INVISIBLE);
        }
    }

    public void show_LoadingView()
    {
        if(loadingView != null)
        {
            loadingView.setVisibility(VISIBLE);
        }
        if(failView != null)
        {
            failView.setVisibility(INVISIBLE);
        }
        if(emptyView != null)
        {
            emptyView.setVisibility(INVISIBLE);
        }
        if(contentView != null)
        {
            contentView.setVisibility(INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        solveReLoad();
    }

    private void solveReLoad() {
        if(NetUtils.isNetworkConnected(getContext())){
            if(callbackListener != null){
                callbackListener.onReLoadCallback();
            }else {
                throw new RuntimeException("You must be set setReLoadCallbackListener");
            }
        }
//
    }

    public void setReLoadCallbackListener(ReLoadCallbackListener callbackListener) {
        this.callbackListener = callbackListener;
    }
}
