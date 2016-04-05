package com.sgj.ayibang;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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
    @Bind(R.id.nav_view)
    NavigationView mNavigationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

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
        toolbar.setNavigationIcon(R.drawable.ic_menu);

        if(mNavigationView != null){
            setupDrawerContent(mNavigationView);
        }

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                intent.setClass(this, SettingActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
