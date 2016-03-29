package com.sgj.ayibang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.sgj.ayibang.R;
import com.sgj.ayibang.adapter.GridViewAdapter;
import com.sgj.ayibang.model.ScrollGridModel;
import com.sgj.ayibang.model.Type;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/3/28.
 */
public class ProTypeFragment extends Fragment {

    @Bind(R.id.toptype)
    TextView textView;
    @Bind(R.id.listView)
    GridView gridView;

    private String typename;
    private int icon;

    private GridViewAdapter mAdapter;

    private ArrayList<Type> list;
    private Type type;

    public static ProTypeFragment newInstance(){
        ProTypeFragment fragment = new ProTypeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pro_type, container, false);
        ButterKnife.bind(this, view);
        int index = getArguments().getInt("index");
        typename = ScrollGridModel.toolsList[index];
        icon = ScrollGridModel.iconList[index];

        textView.setText(typename);

        GetTypeList();
        mAdapter = new GridViewAdapter(getActivity(), list);
        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return view;
    }

    private void GetTypeList() {
        list = new ArrayList<Type>();
        for (int i = 1; i < 23; i++) {
            type = new Type(i, typename + i, icon);
            list.add(type);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
