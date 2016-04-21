package com.sgj.ayibang.contral.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sgj.ayibang.model.Card;

import java.util.List;


/**
 * Created by John on 2016/4/20.
 */
public class RhythmAdapter extends BaseAdapter {

    private Context mContext;
    private List<Card> mCardList;
    private RhythmLayout mRhythmLayout;

    private LayoutInflater mInflater;

    public RhythmAdapter(Context context, RhythmLayout rhythmLayout, List<Card> cards) {
        this.mCardList = cards;
        this.mContext = context;
        this.mRhythmLayout = rhythmLayout;
        if(context != null){
            mInflater = LayoutInflater.from(context);
        }
    }

    @Override
    public int getCount() {
        return (mCardList == null ? 0 : mCardList.size());
    }

    @Override
    public Object getItem(int position) {
        return mCardList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        return null;
    }
}
