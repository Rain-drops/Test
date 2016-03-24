package com.sgj.ayibang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sgj.ayibang.R;
import com.sgj.ayibang.model.Order;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/3/24.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{

    private Context mContext;
    ArrayList<Order> mList;

    public OrderAdapter(Context mContext, ArrayList<Order> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void updateData(ArrayList<Order> list){
        this.mList = list;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Order order = mList.get(position);
        if(order == null){
            return;
        }
        holder.tv_phone.setText(order.getPhone());
        holder.tv_ordertype.setText(order.getOrderType());
        Glide.with(mContext).load(order.getPhoto().getFileUrl(mContext)).centerCrop().into(holder.iv_photo);
    }

    @Override
    public int getItemCount() {
        return (mList == null ? 0 : mList.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.iv_photo)
        ImageView iv_photo;
        @Bind(R.id.tv_phone)
        TextView tv_phone;
        @Bind(R.id.tv_ordertype)
        TextView tv_ordertype;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
