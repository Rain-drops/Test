package com.sgj.ayibang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sgj.ayibang.adapter.GuideAdapter;
import com.sgj.ayibang.utils.PlayViewHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by John on 2016/3/22.
 */
public class GuideActivity extends BaseActivity implements View.OnClickListener{

    private Context mContext;

    ViewPager mViewPager;
    CircleIndicator mCircleIndicator;
    TextView mTextView;

    List<ImageView> mDatas;

    private PlayViewHandler mViewHandler = new PlayViewHandler();


    //引导图片资源
    private static final int[] pics = { R.drawable.guide_a,
            R.drawable.guide_b, R.drawable.guide_c,
            R.drawable.guide_d, R.drawable.guide_e };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        mContext = this;

        SharedPreferences preferences = getSharedPreferences(FirstActivity.FIRST, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(FirstActivity.FIRST, false);
        editor.commit();

        init();
    }

    private void init() {

        mViewPager = (ViewPager) findViewById(R.id.vp_view_pager);
        mCircleIndicator = (CircleIndicator) findViewById(R.id.indicator_default);
        mTextView = (TextView) findViewById(R.id.tv_login);
        mTextView.setOnClickListener(this);

        ViewGroup.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        mDatas = new ArrayList<>();
        for(int i = 0; i<pics.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(mParams);
            imageView.setImageResource(pics[i]);

            mDatas.add(imageView);
        }

        final GuideAdapter mAdapter = new GuideAdapter(mContext, mDatas);
        mViewPager.setAdapter(mAdapter);
        mCircleIndicator.setViewPager(mViewPager);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == (mDatas.size()-1)){
                    mTextView.setVisibility(View.VISIBLE);
                }else {
                    mTextView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login:

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();

                break;
        }
    }
}
