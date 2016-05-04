package com.sgj.wangyi.mytest;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;

/**
 * Created by John on 2016/4/29.
 */
public class MainActivity extends Activity {

    private MyThread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        exampleThree();

    }

    private void exampleThree() {
        mThread = new MyThread();
        mThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mThread.close();
    }

    private static class MyThread extends Thread{
        private boolean mRunning = false;
        @Override
        public void run() {
            mRunning = true;
            while (mRunning) {
                SystemClock.sleep(1000);
            }
        }
        public void close() {
            mRunning = false;
        }
    }
}
