package com.sgj.ayibang.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sgj.ayibang.R;
import com.sgj.ayibang.adapter.SongAdapter;
import com.sgj.ayibang.dataloaders.SongLoader;
import com.sgj.ayibang.model.Song;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/4/1.
 */
public class SongsFragment extends Fragment {

    private static final String TAG = "SongsFragment";

    @Bind(R.id.recyclerview)
    RecyclerView mListView;

    SongAdapter mAdapter;
    ArrayList<Song> mDatas;

    public static SongsFragment newInstance(){
        SongsFragment fragment = new SongsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_song, container, false);
        ButterKnife.bind(this, view);

        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));

        new loadSong().execute("");
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }



    private class loadSong extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {

            if(getActivity() != null){
                mDatas = SongLoader.getAllSongs(getActivity());

                mAdapter = new SongAdapter(getActivity(), mDatas);
            }
            return "Executed";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mListView.setAdapter(mAdapter);
        }
    }
}
