package com.sgj.ayibang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sgj.ayibang.R;
import com.sgj.ayibang.fragment.MenuFragment;
import com.sgj.ayibang.model.Card;
import com.sgj.ayibang.view.SwipeItemLayout;
import com.sgj.ayibang.view.SwipeListView;

import java.util.ArrayList;

/**
 * Created by John on 2016/3/31.
 */
public class SwipeListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Card> mDatas;

    ViewHolder mViewHolder;
    SwipeListView mListView;

    public SwipeListAdapter(Context context, ArrayList<Card> mDatas, SwipeListView listView) {
        this.mContext = context;
        this.mDatas = mDatas;
        this.mListView = listView;
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
    public View getView(final int position, View convertView, ViewGroup parent) {


        if(convertView == null){
            View view1 = LayoutInflater.from(mContext).inflate(R.layout.item_swipe, null);
            View view2 = LayoutInflater.from(mContext).inflate(R.layout.button_swipe, null);

            convertView = new SwipeItemLayout(view1, view2, null, null);

            mViewHolder = new ViewHolder(convertView);

            mViewHolder.title.setText(mDatas.get(position).getTitle());
            mViewHolder.author.setText(mDatas.get(position).getAuthor());
            mViewHolder.tv_comic_intro.setText(mDatas.get(position).getDigest());

            String bravos = mDatas.get(position).getBravos();

            SpannableStringBuilder builder = new SpannableStringBuilder(bravos);
            ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);
            builder.setSpan(redSpan, 0, bravos.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            mViewHolder.comicStatus.setText("第 ");
            mViewHolder.comicStatus.append(builder);
            mViewHolder.comicStatus.append(" 话");

            convertView.setTag(mViewHolder);
        }else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, " 订阅 ", Toast.LENGTH_SHORT).show();
                mListView.smoothCloseMenu(position);
            }
        });

        return convertView;
    }

    public void updateDatas(ArrayList<Card> list){
        this.mDatas = list;
        this.notifyDataSetChanged();
    }

    private class ViewHolder{
        LinearLayout button;
        ImageView ivSwipe;
        TextView title;
        TextView author;
        TextView comicStatus;
        TextView tv_comic_intro;

        public ViewHolder(View view) {
            this.button = (LinearLayout) view.findViewById(R.id.ll_button);
            this.title = (TextView) view.findViewById(R.id.tv_title);
            this.author = (TextView) view.findViewById(R.id.tv_author);
            this.button = (LinearLayout) view.findViewById(R.id.ll_button);
            this.comicStatus = (TextView) view.findViewById(R.id.tv_comic_status);
            this.tv_comic_intro = (TextView) view.findViewById(R.id.tv_comic_intro);
        }
    }
}
