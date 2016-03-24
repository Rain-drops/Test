package com.sgj.ayibang;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.Touch;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sgj.ayibang.fragment.FragmentMain;
import com.sgj.ayibang.fragment.FragmentMine;
import com.sgj.ayibang.fragment.FragmentOrder;
import com.sgj.ayibang.model.Person;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    public static final String TAG_MAIN = "Main";
    public static final String TAG_ORDER = "Order";
    public static final String TAG_MINE = "Mine";

    private String hideTag;
    private String previousTag;

    private Context mContext;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    private FragmentMain mMainFragment;
    private FragmentMine mMineFragment;
    private FragmentOrder mOrderFragment;

    @Bind(R.id.ll_main)
    LinearLayout main;
    @Bind(R.id.ll_order)
    LinearLayout order;
    @Bind(R.id.ll_mine)
    LinearLayout mine;

    @Bind(R.id.iv_main)
    ImageView iv_main;
    @Bind(R.id.iv_order)
    ImageView iv_order;
    @Bind(R.id.iv_mine)
    ImageView iv_mine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;

        init();
        mMainFragment = FragmentMain.newInstance();
        switchFragment(TAG_MAIN, mMainFragment);

    }

    private void init() {
        main.setOnClickListener(this);
        order.setOnClickListener(this);
        mine.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_main:
                if(mMainFragment == null){
                    mMainFragment = FragmentMain.newInstance();
                }
                switchFragment(TAG_MAIN, mMainFragment);
                break;
            case R.id.ll_order:
                if(mOrderFragment == null){
                    mOrderFragment = FragmentOrder.newInstance();
                }
                switchFragment(TAG_ORDER, mOrderFragment);
                break;
            case R.id.ll_mine:
                testLogin();
                if(mMineFragment == null){
                    mMineFragment = FragmentMine.newInstance();
                }
                switchFragment(TAG_MINE, mMineFragment);
                break;
        }
    }

    /**
     * 转换不同的Fragment
     * @param tag
     * @param mFragment
     */
    private void switchFragment(String tag, Fragment mFragment) {

        if(hideTag == tag){
            return;
        }

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        Fragment tagFragment = mFragmentManager.findFragmentByTag(tag);
        if(tagFragment == null){
            mFragmentTransaction.add(R.id.main_content, mFragment, tag);
        }else {
            mFragmentTransaction.show(tagFragment);
        }

        tagFragment = mFragmentManager.findFragmentByTag(hideTag);

        if(tagFragment !=null){
            mFragmentTransaction.hide(tagFragment);
        }
        previousTag = hideTag;
        hideTag = tag;
        switchNav();
        mFragmentTransaction.commit();
    }

    private void switchNav() {
        if(TAG_MAIN.equals(previousTag)){
            iv_main.setImageResource(R.drawable.tab_home_normal);
        }
        if(TAG_ORDER.equals(previousTag)){
            iv_order.setImageResource(R.drawable.tab_order_normal);
        }
        if(TAG_MINE.equals(previousTag)){
            iv_mine.setImageResource(R.drawable.tab_mine_normal);
        }
        if(TAG_MAIN.equals(hideTag)){
            iv_main.setImageResource(R.drawable.tab_home_check);
        }
        if(TAG_ORDER.equals(hideTag)){
            iv_order.setImageResource(R.drawable.tab_order_check);
        }
        if(TAG_MINE.equals(hideTag)){
            iv_mine.setImageResource(R.drawable.tab_mine_check);
        }
    }

    private void testLogin() {
        BmobQuery<Person> query = new BmobQuery<>();
        query.getObject(this, "RzjUYYYo", new GetListener<Person>() {
            @Override
            public void onSuccess(Person person) {

                toast("查询成功 : phone = " + person.getPhone());
            }

            @Override
            public void onFailure(int i, String s) {
                toast("查询失败 : error = " + s);
            }
        });
    }
    public void toast(String str){
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }
}
