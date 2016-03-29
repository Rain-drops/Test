package com.sgj.ayibang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sgj.ayibang.R;
import com.sgj.ayibang.model.Type;

import java.util.ArrayList;

/**
 * Created by John on 2016/3/28.
 */
public class GridViewAdapter extends BaseAdapter {

    private ArrayList<Type> list;
    private Type type;
    private Context context;
    Holder view;

    public GridViewAdapter(Context context, ArrayList<Type> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return (null == list ? 0 : list.size());
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(context, R.layout.item_gridview, null);
            view = new Holder(convertView);
            convertView.setTag(view);
        }else {
            view = (Holder) convertView.getTag();
        }
        if(list != null && list.size() > 0){
            type = list.get(position);
            view.icon.setBackground(context.getResources().getDrawable(
                    type.getIcon()));
            view.name.setText(type.getTypename());
        }
        return convertView;
    }

    private class Holder {
        private ImageView icon;
        private TextView name;

        public Holder(View view) {
            icon = (ImageView) view.findViewById(R.id.typeicon);
            name = (TextView) view.findViewById(R.id.typename);
        }
    }
}
