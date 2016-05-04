package com.sgj.wangyi.mytest.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by John on 2016/4/29.
 */
public class TaskFragment extends Fragment {

    interface TaskCallbacks{
        void onPreExecute();
        void onProgressUpdate(int percent);
        void onCancelled();
        void onPostExecute();
    }

    private TaskCallbacks mCallbacks;
    private DummyTask mDummyTask;

    public static TaskFragment newInstance(){
        TaskFragment fragment = new TaskFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (TaskCallbacks) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 跳过销毁重构的过程，告诉系统在Acitivity重构时保持当前Fragment实例的状态
        setRetainInstance(true);

        mDummyTask = new DummyTask();
        mDummyTask.execute();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
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
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    private class DummyTask extends AsyncTask<Void, Integer, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPreExecute() {
            if(mCallbacks != null){
                mCallbacks.onPreExecute();
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(mCallbacks != null){
                mCallbacks.onPostExecute();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if(mCallbacks != null){
                mCallbacks.onProgressUpdate(values[0]);
            }
        }

        @Override
        protected void onCancelled() {
            if(mCallbacks != null){
                mCallbacks.onCancelled();
            }
        }
    }
}
