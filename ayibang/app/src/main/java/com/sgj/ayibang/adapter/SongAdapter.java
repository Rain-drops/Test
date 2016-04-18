package com.sgj.ayibang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sgj.ayibang.R;
import com.sgj.ayibang.model.Song;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import org.w3c.dom.Text;

import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * Created by John on 2016/4/1.
 */
public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ItemHolder> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {

    private Context mContext;
    ArrayList<Song> mDatas;

    public SongAdapter(Context mContext, ArrayList<Song> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_song, null);
        ItemHolder holder = new ItemHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Song song = mDatas.get(position);
        holder.title.setText(song.title);
        holder.artist.setText(song.artistName);
    }

    @Override
    public long getHeaderId(int position) {
        return mDatas.get(position).title.charAt(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_header, parent, false);
        return new RecyclerView.ViewHolder(view){

        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        textView.setText(mDatas.get(position).title);
        holder.itemView.setBackgroundColor(getRandomColor());
    }

    private int getRandomColor() {

        SecureRandom random = new SecureRandom();
        return Color.HSVToColor(150, new float[]{random.nextInt(359), 1, 1});
    }

    @Override
    public int getItemCount() {
        return (null == mDatas ? 0 : mDatas.size());
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title, artist;

        public ItemHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.tv_song_title);
            this.artist = (TextView) view.findViewById(R.id.tv_song_artist);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
