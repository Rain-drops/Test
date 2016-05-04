package com.sgj.wangyi.mytest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by John on 2016/4/29.
 *
 * 使用Fragment处理配置更改
 * http://www.jianshu.com/p/eb3f05610472
 */
public class TaskActivity extends AppCompatActivity implements TaskFragment.TaskCallbacks{

    private static final String TAG = "TaskActivity";
    FragmentManager mManager;
    FragmentTransaction mTransaction;

    private static final String TAG_TASK_FRAGMENT = "task_fragment";
    private TaskFragment mTaskFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mManager = getSupportFragmentManager();
        mTaskFragment = (TaskFragment) mManager.findFragmentByTag(TAG_TASK_FRAGMENT);
        if(mTaskFragment == null){
            mTaskFragment = TaskFragment.newInstance();
            mTransaction = mManager.beginTransaction();
            mTransaction.add(mTaskFragment, TAG_TASK_FRAGMENT);
        }else {
            mTransaction.show(mTaskFragment);
        }
        mTransaction.commit();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onProgressUpdate(int percent) {

    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onPostExecute() {

    }
}
