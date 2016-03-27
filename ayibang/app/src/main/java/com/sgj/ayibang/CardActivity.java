package com.sgj.ayibang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.sgj.ayibang.fragment.CardViewPagerFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/3/25.
 */
public class CardActivity extends BaseActivity {

    CardViewPagerFragment cardViewPagerFragment;
    private static final String TAG_CARD = "Card";
    private String hideTag;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        ButterKnife.bind(this);

        Fragment fragment = CardViewPagerFragment.getInstance();
        swichFragment(TAG_CARD, fragment);
        init();

    }

    private void swichFragment(String tag, Fragment fragment) {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        Fragment tagFragment = mFragmentManager.findFragmentByTag(tag);
        if(tagFragment == null){

            mFragmentTransaction.add(R.id.card_content, fragment, tag);

        }else {
            mFragmentTransaction.show(tagFragment);
        }

        tagFragment = mFragmentManager.findFragmentByTag(hideTag);

        if(tagFragment !=null){
            mFragmentTransaction.hide(tagFragment);
        }
        hideTag = tag;
        mFragmentTransaction.commit();
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                CardActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
