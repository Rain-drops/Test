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
import android.widget.Toast;

import com.sgj.ayibang.R;
import com.sgj.ayibang.adapter.SongAdapter;
import com.sgj.ayibang.dataloaders.SongLoader;
import com.sgj.ayibang.model.Song;
import com.sgj.quicksidebar.QuickSideBarView;
import com.sgj.quicksidebar.listener.OnQuickSideBarTouchListener;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/4/1.
 */
public class SongsFragment extends Fragment implements OnQuickSideBarTouchListener {

    private static final String TAG = "SongsFragment";

    @Bind(R.id.recyclerview)
    RecyclerView mListView;

    @Bind(R.id.quickSideBarView)
    QuickSideBarView mQuickSideBarView;

    HashMap<String,Integer> letters = new HashMap<>();

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

        mQuickSideBarView.setOnQuickSideBarTouchListener(this);



        // Add decoration for dividers between list items
//        mListView.addItemDecoration(new DividerDecoration(this));

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

    @Override
    public void onLetterChanged(String letter, int position, int itemHeight) {
        Toast.makeText(getContext(), letter, Toast.LENGTH_SHORT).show();
        if(letters.containsKey(letter)){
            mListView.scrollToPosition(letters.get(letter));
        }
    }

    @Override
    public void onLetterTouching(boolean touching) {

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

            final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(mAdapter);
            mListView.addItemDecoration(headersDecor);
        }
    }
}
