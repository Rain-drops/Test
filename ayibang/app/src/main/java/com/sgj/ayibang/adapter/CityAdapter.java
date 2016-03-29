package com.sgj.ayibang.adapter;


import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.sgj.ayibang.R;
import com.sgj.ayibang.model.City;
import com.sgj.ayibang.utils.CityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by John on 2016/3/29.
 */
public class CityAdapter extends BaseAdapter{

    private Context mContext;
    ArrayList<City> mDatas;

    City city;
    String cityName;

    Holder view;

    int isCheckId;

    public CityAdapter(Context context, ArrayList<City> list) {
        this.mContext = context;
        this.mDatas = list;
        SharedPreferences preferences = context.getSharedPreferences("city", Context.MODE_PRIVATE);
        cityName = preferences.getString("City", "");

    }

    @Override
    public int getCount() {
        return (null == mDatas ? 0 : mDatas.size());
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        if (convertView == null){
            convertView = View.inflate(mContext, R.layout.item_city, null);
            view = new Holder(convertView);
            convertView.setTag(view);
        }else {
            view = (Holder) convertView.getTag();
        }

        if(mDatas != null && mDatas.size() > 0){
            city = mDatas.get(position);
            if(city.getCity().equals(cityName)){
                view.check.setVisibility(View.VISIBLE);
                isCheckId = position;
            }else {
                view.check.setVisibility(View.INVISIBLE);
            }
            view.city.setText(city.getCity());
        }
        view.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                view.check.setVisibility(View.VISIBLE);
                
            }
        });
        return convertView;
    }

    private class Holder{

        TextView city;
        ImageView check;
        RelativeLayout relativeLayout;

        public Holder(View view) {
            city = (TextView) view.findViewById(R.id.tv_city);
            check = (ImageView) view.findViewById(R.id.iv_check);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_city_item);
        }
    }

    public void updateDatas(ArrayList<City> cities){
        this.mDatas = cities;
        this.notifyDataSetChanged();
    }

}
