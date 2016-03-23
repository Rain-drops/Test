package com.sgj.ayibang;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.sgj.ayibang.model.Person;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private Context mContext;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Bind(R.id.ll_main)
    LinearLayout main;
    @Bind(R.id.ll_order)
    LinearLayout order;
    @Bind(R.id.ll_mine)
    LinearLayout mine;
    @Bind(R.id.iv_main)
    ImageView iv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        FragmentMain fragmentMain = FragmentMain.getFragmentMain();
        mFragmentTransaction.add(R.id.main_content, fragmentMain);
        mFragmentTransaction.commit();

        init();
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
                testLogin();
                iv_main.setImageResource(R.drawable.tab_home_check);
                break;
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
