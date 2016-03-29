package com.sgj.ayibang;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;

import com.sgj.ayibang.fragment.ContentFragment;
import com.sgj.ayibang.fragment.MenuFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/3/28.
 */
public class ServiceActivity extends BaseActivity {

    MenuFragment mMenuFragment;
    ContentFragment mContentFragment;

    @Bind(R.id.slidingpanellayout)
    SlidingPaneLayout mSlidingPaneLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slidingpane_layout);
        ButterKnife.bind(this);

        init();

    }

    private void init() {



        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(mMenuFragment == null){
            mMenuFragment = MenuFragment.newInstance();
        }
        if(mContentFragment == null){
            mContentFragment = ContentFragment.newInstance();
        }

        transaction.replace(R.id.slidingpane_menu, mMenuFragment);
        transaction.replace(R.id.slidingpane_content, mContentFragment);
        transaction.commit();
        mSlidingPaneLayout.openPane();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
