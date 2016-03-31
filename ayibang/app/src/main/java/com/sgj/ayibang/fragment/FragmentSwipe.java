package com.sgj.ayibang.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sgj.ayibang.R;
import com.sgj.ayibang.adapter.SwipeListAdapter;
import com.sgj.ayibang.model.Card;
import com.sgj.ayibang.model.Order;
import com.sgj.ayibang.view.SwipeListView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindCallback;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by John on 2016/3/31.
 */
public class FragmentSwipe extends Fragment implements AdapterView.OnItemClickListener{

    SwipeListAdapter mAdapter;
    ArrayList<Card> mDatas;

    @Bind(R.id.lv_swipe)
    SwipeListView mListView;

    public static FragmentSwipe newInstance(){
        FragmentSwipe mFragment = new FragmentSwipe();
        return mFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mAdapter != null){
            mAdapter.updateDatas(mDatas);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_swipelist, container, false);
        ButterKnife.bind(this, view);

        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData();
    }

    @Override
    public void setRetainInstance(boolean retain) {
        super.setRetainInstance(retain);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void getData() {
        BmobQuery<Card> query = new BmobQuery<>();
        query.setLimit(50);
        query.findObjects(getContext(), new FindListener<Card>() {
            @Override
            public void onSuccess(List<Card> list) {
                mDatas = (ArrayList)list;
                if(mAdapter == null){
                    mAdapter = new SwipeListAdapter(getContext(), mDatas, mListView);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), "----", Toast.LENGTH_SHORT).show();
        View v = parent.getChildAt(position);
    }
}
