package com.sgj.ayibang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sgj.ayibang.R;
import com.sgj.ayibang.ScrollGridActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/3/28.
 */
public class MenuFragment extends Fragment implements View.OnClickListener{

    @Bind(R.id.tv_menu1)
    TextView menu1;
    @Bind(R.id.tv_menu2)
    TextView menu2;
    @Bind(R.id.tv_menu3)
    TextView menu3;
    @Bind(R.id.tv_menu4)
    TextView menu4;

    private int hideTag = 1;


    public static MenuFragment newInstance(){
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slidingpane_menu_layout, container, false);
        ButterKnife.bind(this, view);

        menu1.setOnClickListener(this);
        changeTextColor(menu1, 1);
        menu1.setBackgroundColor(getResources().getColor(R.color.qianhui2));
        menu2.setOnClickListener(this);
        menu3.setOnClickListener(this);
        menu4.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.tv_menu1:
                changeTextColor(menu1, 1);
                break;
            case R.id.tv_menu2:
                changeTextColor(menu2, 2);
                break;
            case R.id.tv_menu3:
                changeTextColor(menu3, 3);
                break;
            case R.id.tv_menu4:
                changeTextColor(menu4, 4);
                intent.setClass(getActivity(), ScrollGridActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

    private void changeTextColor(TextView id, int tag) {

        if(hideTag == tag){
            return;
        }
        if(hideTag ==1 ){
            menu1.setBackgroundColor(getResources().getColor(R.color.qianlv));
        }
        if(hideTag ==2 ){
            menu2.setBackgroundColor(getResources().getColor(R.color.qianlv));
        }
        if(hideTag ==3 ){
            menu3.setBackgroundColor(getResources().getColor(R.color.qianlv));
        }
        if(hideTag ==4 ){
            menu4.setBackgroundColor(getResources().getColor(R.color.qianlv));
        }

        id.setBackgroundColor(getResources().getColor(R.color.qianhui2));
        hideTag = tag;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
