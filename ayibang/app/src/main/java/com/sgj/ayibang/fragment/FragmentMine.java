package com.sgj.ayibang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sgj.ayibang.LoginActivity;
import com.sgj.ayibang.R;
import com.sgj.ayibang.db.PersonDB;

/**
 * Created by John on 2016/3/24.
 */
public class FragmentMine extends Fragment implements View.OnClickListener{

    TextView tv_login;

    public static FragmentMine newInstance(){
        FragmentMine fragmentMine = new FragmentMine();
        return fragmentMine;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        tv_login = (TextView) view.findViewById(R.id.tv_login);
        tv_login.setOnClickListener(this);

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
            case R.id.tv_login:
                intent.setClass(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }



    private void toast(String str){
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

}
