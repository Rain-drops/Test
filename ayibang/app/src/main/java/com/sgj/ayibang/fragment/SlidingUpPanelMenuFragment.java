package com.sgj.ayibang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.sgj.ayibang.R;


/**
 * Created by John on 2016/5/16.
 */
public class SlidingUpPanelMenuFragment extends Fragment implements View.OnClickListener{

    ImageView iv_a, iv_b, iv_c, iv_d, iv_e, iv_f;

    public static SlidingUpPanelMenuFragment getInstance(){
        SlidingUpPanelMenuFragment slidingUpPanelMenuFragment = new SlidingUpPanelMenuFragment();
        return slidingUpPanelMenuFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sliding_up_panel_menu, container, false);
        initView(view);
        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initView(View view) {
        iv_a = (ImageView) view.findViewById(R.id.iv_a);
        iv_b = (ImageView) view.findViewById(R.id.iv_b);
        iv_c = (ImageView) view.findViewById(R.id.iv_c);
        iv_d = (ImageView) view.findViewById(R.id.iv_d);
        iv_e = (ImageView) view.findViewById(R.id.iv_e);
        iv_f = (ImageView) view.findViewById(R.id.iv_f);

        iv_a.setOnClickListener(this);
        iv_b.setOnClickListener(this);
        iv_c.setOnClickListener(this);
        iv_d.setOnClickListener(this);
        iv_e.setOnClickListener(this);
        iv_f.setOnClickListener(this);
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
            case R.id.iv_a:
                toast(" iv_a ");
                break;
            case R.id.iv_b:
                toast(" iv_b ");
                break;
            case R.id.iv_c:
                toast(" iv_c ");
                break;
            case R.id.iv_d:
                toast(" iv_d ");
                break;
            case R.id.iv_e:
                toast(" iv_e ");
                break;
            case R.id.iv_f:
                toast(" iv_f ");
                break;
        }
    }

    private void toast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
