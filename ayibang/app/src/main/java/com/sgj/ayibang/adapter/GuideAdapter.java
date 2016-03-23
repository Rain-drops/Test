package com.sgj.ayibang.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sgj.ayibang.model.AdvModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2016/3/22.
 */
public class GuideAdapter extends PagerAdapter {
    private Context mContext;
    private List<AdvModel.ListEntity> mDatas;

    private ImageView mImageView;
    private List<ImageView> mViews;

//    public GuideAdapter(Context context, List<AdvModel.ListEntity> datas){
//        this.mDatas = datas;
//        this.mContext = context;
//        mViews = new ArrayList<ImageView>();
//        int length = mDatas == null ? 0 : mDatas.size();
//        for(int i = 0; i < length; i++){
//            ImageView imageView = new ImageView(mContext);
//            mViews.add(imageView);
//        }
//    }

    public GuideAdapter(Context context, List<ImageView> datas){
        this.mViews = datas;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mViews != null ? mViews.size() : 0 ;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(View container, int position) {
        mImageView = mViews.get(position);
        //是否保持宽高比
//        mImageView.setAdjustViewBounds(true);
        //不按比例缩放图片，目标是把图片塞满整个View。
//        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        mImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, WebActivity.class);
//                intent.putExtra(WebActivity.EXTRA_URL, mDatas.get(position).Link);
//                mContext.startActivity(intent);
//            }
//        });
//        Glide.with(mContext).load(mDatas.get(position).Img).centerCrop().into(mImageView);

        ((ViewPager)container).addView(mImageView);
        return mImageView;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        mImageView = mViews.get(position);
        ((ViewPager)container).removeView(mImageView);
    }
}
