package com.sgj.ayibang;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.Touch;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.sgj.ayibang.adapter.CityAdapter;
import com.sgj.ayibang.db.PersonDB;
import com.sgj.ayibang.fragment.FragmentMain;
import com.sgj.ayibang.fragment.FragmentMine;
import com.sgj.ayibang.fragment.FragmentOrder;
import com.sgj.ayibang.model.City;
import com.sgj.ayibang.model.Person;
import com.sgj.ayibang.utils.CityUtils;
import com.sgj.ayibang.utils.LocationUtils;

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
    @Bind(R.id.ll_city)
    LinearLayout citys;
    @Bind(R.id.lv_news)
    ImageView news;
    @Bind(R.id.tv_city)
    TextView mCity;

    @Bind(R.id.iv_main)
    ImageView iv_main;
    @Bind(R.id.iv_order)
    ImageView iv_order;
    @Bind(R.id.iv_mine)
    ImageView iv_mine;


    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;

        init();
        mMainFragment = FragmentMain.newInstance();
        switchFragment(TAG_MAIN, mMainFragment);
        // 系统内存
        long totalMemory = Runtime.getRuntime().totalMemory();
        // 空闲内存
        long freeMemory = Runtime.getRuntime().freeMemory();




    }

    private void init() {
        main.setOnClickListener(this);
        order.setOnClickListener(this);
        mine.setOnClickListener(this);
        news.setOnClickListener(this);
        citys.setOnClickListener(this);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
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
            case R.id.lv_news:
                intent.setClass(this, AdvertiseActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_city:
                intent.setClass(this, CityActivity.class);
                startActivityForResult(intent, CityActivity.TAG_CITY);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case CityActivity.TAG_CITY:
                String cityName = data.getExtras().getString(CityActivity.TAG_CITY_NAME);
                mCity.setText(cityName);
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

                PersonDB personDB = PersonDB.getInstance(mContext);
                personDB.savePerson(person);

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

    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis() - exitTime) > 2000){
                exitTime = System.currentTimeMillis();
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                return true;
            }else {
                Toast.makeText(getApplicationContext(), "退出程序", Toast.LENGTH_SHORT).show();
            }
//            return true;
        }else {

        }

        return super.onKeyDown(keyCode, event);
    }

}
