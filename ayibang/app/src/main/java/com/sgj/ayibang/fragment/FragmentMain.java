package com.sgj.ayibang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sgj.ayibang.R;
import com.sgj.ayibang.adapter.GuideAdapter;
import com.sgj.ayibang.utils.PlayViewHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by John on 2016/3/23.
 */
public class FragmentMain extends Fragment {

    @Bind(R.id.viewpager_main)
    ViewPager mViewPager;
    @Bind(R.id.indicator_default)
    CircleIndicator mCircleIndicator;

    private static final int[] pics = {R.drawable.a, R.drawable.b, R.drawable.c};
    List<ImageView> mDataSet;
    private PlayViewHandler mViewHandler = new PlayViewHandler();

    public static FragmentMain newInstance(){
        FragmentMain fragmentMain = new FragmentMain();
        return fragmentMain;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        mDataSet = new ArrayList<>();
        for(int i=0; i<pics.length; i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(params);
            imageView.setImageResource(pics[i]);
            mDataSet.add(imageView);
        }

        final GuideAdapter adapter = new GuideAdapter(getContext(), mDataSet);
        mViewPager.setAdapter(adapter);
        mCircleIndicator.setViewPager(mViewPager);
        mViewHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mViewHandler.slidingPlayView(mViewPager, adapter.getCount());
            }
        }, 6000);
    }
}
