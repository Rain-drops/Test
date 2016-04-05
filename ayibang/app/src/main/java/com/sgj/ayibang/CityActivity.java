package com.sgj.ayibang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.sgj.ayibang.adapter.CityAdapter;
import com.sgj.ayibang.model.City;
import com.sgj.ayibang.utils.LocationUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by John on 2016/3/28.
 */
public class CityActivity extends BaseActivity implements
        View.OnClickListener, AMapLocationListener{

    private static final String TAG = "CityActivity";
    public static final int TAG_CITY = 0x10;
    public static final String TAG_CITY_NAME = "TAG_CITY_NAME";
    private Context mContext;

    @Bind(R.id.lv_city)
    ListView mListView;
    @Bind(R.id.tv_city)
    TextView mCity;
    @Bind(R.id.iv_close)
    ImageView mClose;

    CityAdapter mAdapter;
    ArrayList<City> mDatas;
    String cityName;

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        ButterKnife.bind(this);
        mContext = this;

        init();
        initLacation();

    }

    private void init() {

        mClose.setOnClickListener(this);

        SharedPreferences preferences = getSharedPreferences("city", MODE_PRIVATE);
        cityName = preferences.getString("City", "");
        mCity.setText(cityName);
        getData();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mAdapter != null) {

                    mAdapter.setSelectedPosition(position);
                    mAdapter.notifyDataSetInvalidated();

//                    String city = mAdapter.getCityName();
                    String city = mDatas.get(position).getCity();
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString(TAG_CITY_NAME, city);
                    intent.putExtras(bundle);
                    CityActivity.this.setResult(TAG_CITY, intent);
                    finish();

                }
            }
        });

    }

    private void initLacation() {
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = new AMapLocationClientOption();
        // 设置定位模式为低功耗模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        // 设置定位监听
        locationClient.setLocationListener(this);
        //单次定位
        locationOption.setOnceLocation(true);

        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);

        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close:
                if(mAdapter != null){
                    String city = mDatas.get(mAdapter.getPosition()).getCity();
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString(TAG_CITY_NAME, city);
                    intent.putExtras(bundle);
                    CityActivity.this.setResult(TAG_CITY, intent);
                    finish();
                }

                break;
        }
    }

    @Override
    public void onLocationChanged(AMapLocation loc) {
        if (null != loc) {
            Message msg = mHandler.obtainMessage();
            msg.obj = loc;
            msg.what = LocationUtils.MSG_LOCATION_FINISH;
            mHandler.sendMessage(msg);
        }
    }

    Handler mHandler = new Handler() {
        public void dispatchMessage(android.os.Message msg) {
            switch (msg.what) {
                //开始定位
                case LocationUtils.MSG_LOCATION_START:
                    break;
                // 定位完成
                case LocationUtils.MSG_LOCATION_FINISH:
                    AMapLocation loc = (AMapLocation) msg.obj;
                    String result = LocationUtils.getLocationStr(mContext, loc);
                    Log.d(TAG, result);
                    break;
                //停止定位
                case LocationUtils.MSG_LOCATION_STOP:
                    break;
                default:
                    break;
            }
        };
    };

    private void getData(){
        BmobQuery<City> query = new BmobQuery<>();
        query.setLimit(50);
        query.findObjects(mContext, new FindListener<City>() {
            @Override
            public void onSuccess(List<City> list) {
                mDatas = (ArrayList)list;
                if(mAdapter == null){
                    mAdapter = new CityAdapter(mContext, mDatas);
                    mListView.setAdapter(mAdapter);
                }else {
                    mAdapter.updateDatas(mDatas);
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
}
