package com.sgj.ayibang;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;

import com.sgj.ayibang.fragment.ContentFragment;
import com.sgj.ayibang.fragment.FragmentMine;
import com.sgj.ayibang.fragment.FragmentSwipe;
import com.sgj.ayibang.fragment.MenuFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/3/28.
 */
public class ServiceActivity extends BaseActivity implements MenuFragment.BookmarkListener{

    MenuFragment mMenuFragment;
    ContentFragment mContentFragment;
    FragmentSwipe mFragmentSwipe;
    FragmentMine mFragmentMine;

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Bind(R.id.slidingpanellayout)
    SlidingPaneLayout mSlidingPaneLayout;

    String hideTag;
    boolean isInit = true;

    public static final String TAG_MENU = "Menu";
    public static final String TAG_CONTENT = "Content";
    public static final String TAG_SWIPE = "Swipe";
    public static final String TAG_MINE = "Mine";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slidingpane_layout);
        ButterKnife.bind(this);

        init();

    }

    private void init() {
        switchFragment(TAG_CONTENT, ContentFragment.newInstance());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onBookmarkChanged(String tag, String bookmark) {

        switch (tag){
            case "menu1":
                switchFragment(TAG_CONTENT, ContentFragment.newInstance());
                break;
            case "menu2":
                switchFragment(TAG_SWIPE, FragmentSwipe.newInstance());
                break;
            case "menu3":
                switchFragment(TAG_MINE, FragmentMine.newInstance());
                break;
            case "menu4":
                break;
            default:
                break;
        }

    }

    private void switchFragment(String tag, Fragment mFragment) {
        if(tag == hideTag){
            return;
        }
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        if(isInit){
            mFragmentTransaction.add(R.id.slidingpane_menu, MenuFragment.newInstance());
            isInit = false;
        }
        Fragment tagFragment = mFragmentManager.findFragmentByTag(tag);
        if(tagFragment == null){
            mFragmentTransaction.replace(R.id.slidingpane_content, mFragment);
        }else {
            mFragmentTransaction.show(tagFragment);
        }

        tagFragment = mFragmentManager.findFragmentByTag(hideTag);
        if(tagFragment != null){
            mFragmentTransaction.hide(tagFragment);
        }
        hideTag = tag;
        mFragmentTransaction.commit();
        mSlidingPaneLayout.closePane();
    }


    private class loadContact extends AsyncTask<String, Void, Integer> {


        @Override
        protected Integer doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }
    }
}
