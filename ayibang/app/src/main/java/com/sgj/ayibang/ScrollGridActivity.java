package com.sgj.ayibang;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sgj.ayibang.BaseActivity;
import com.sgj.ayibang.R;
import com.sgj.ayibang.fragment.ProTypeFragment;
import com.sgj.ayibang.model.ScrollGridModel;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/3/28.
 */
public class ScrollGridActivity extends FragmentActivity {


    private LayoutInflater inflater;
    @Bind(R.id.tools_scrlllview)
    ScrollView scrollView;
    @Bind(R.id.goods_pager)
    ViewPager viewpager;
    private String[] list;

    private ShopAdapter shopAdapter;
    private int currentItem = 0;
    private TextView[] tvList;
    private View[] views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollgrid);
        ButterKnife.bind(this);
        inflater = LayoutInflater.from(this);
        showToolsView();
        initPage();
    }

    private void showToolsView() {
        list = ScrollGridModel.toolsList;
        LinearLayout toolsLayout = (LinearLayout) findViewById(R.id.tools);
        tvList = new TextView[list.length];
        views = new View[list.length];

        for (int i = 0; i < list.length; i++) {
            final View view = inflater.inflate(R.layout.item_list_layout, null);
            view.setId(i);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewpager.setCurrentItem(v.getId());
                }
            });
            TextView textView = (TextView) view.findViewById(R.id.text);
            textView.setText(list[i]);
            toolsLayout.addView(view);
            tvList[i] = textView;
            views[i] = view;
        }
        changeTextColor(0);
    }

    private void changeTextColor(int id) {
        for (int i = 0; i < tvList.length; i++) {
            if (i != id) {
                tvList[i].setBackgroundColor(0x00000000);
                tvList[i].setTextColor(0xFF000000);
            }
        }
        tvList[id].setBackgroundColor(0xFFFFFFFF);
        tvList[id].setTextColor(0xFFFF5D5E);
    }

    private void initPage() {
        shopAdapter = new ShopAdapter(getSupportFragmentManager());
        viewpager.setAdapter(shopAdapter);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (viewpager.getCurrentItem() != position) {
                    viewpager.setCurrentItem(position);
                }
                if (currentItem != position) {
                    changeTextColor(position);
                    changeTextLocation(position);
                }
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void changeTextLocation(int position) {
        int x = views[position].getTop();
        scrollView.smoothScrollTo(0, x);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private class ShopAdapter extends FragmentPagerAdapter{

        public ShopAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = ProTypeFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putInt("index", position);
            fragment.setArguments(bundle);

            return fragment;
        }

        @Override
        public int getCount() {
            return list.length;
        }
    }
}
