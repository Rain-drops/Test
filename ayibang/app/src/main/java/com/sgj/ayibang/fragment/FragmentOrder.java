package com.sgj.ayibang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sgj.ayibang.R;
import com.sgj.ayibang.adapter.OrderAdapter;
import com.sgj.ayibang.model.Order;
import com.sgj.ayibang.viewselect.ReLoadCallbackListener;
import com.sgj.ayibang.viewselect.ViewSelectorLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by John on 2016/3/24.
 */
public class FragmentOrder extends Fragment implements ReLoadCallbackListener{

    private static final String TAG = "FragmentOrder";

    private ViewSelectorLayout viewSelectorLayout;
    private OrderAdapter mAdapter;
    private ArrayList<Order> mDataSet;

    @Bind(R.id.recycler_view)
    RecyclerView recycler_view;

    public static FragmentOrder newInstance(){
        FragmentOrder fragmentOrder = new FragmentOrder();
        return fragmentOrder;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mAdapter != null){
            mAdapter.updateData(mDataSet);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, view);

        viewSelectorLayout = new ViewSelectorLayout(getActivity(), view);
        viewSelectorLayout.setReLoadCallbackListener(this);

        return viewSelectorLayout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(layoutManager);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewSelectorLayout.show_LoadingView();
        getData();
    }

    private void getData() {
        BmobQuery<Order> query = new BmobQuery<>();
        //查询 phone = "15011386125" 的数据
        query.addWhereEqualTo("phone", "15011386125");
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
        query.findObjects(getContext(), new FindListener<Order>() {
            @Override
            public void onSuccess(List<Order> list) {
                mDataSet = (ArrayList)list;
                if(mAdapter == null){
                    mAdapter = new OrderAdapter(getContext(), mDataSet);
                    recycler_view.setAdapter(mAdapter);
                }else {

                }
                viewSelectorLayout.show_ContentView();
            }

            @Override
            public void onError(int i, String s) {
                Log.i("sgj", " i = " + i + "resultError ----> " + s.toString());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onReLoadCallback() {
        viewSelectorLayout.show_LoadingView();
        getData();
    }
}
