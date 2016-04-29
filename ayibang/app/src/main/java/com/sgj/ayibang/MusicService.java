package com.sgj.ayibang;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * Created by John on 2016/4/27.
 */
public class MusicService extends Service {

    private final IBinder mBinder = new ServiceStub(this);

    public static final String CMDNAME = "command"; // 命令
    public static final String CMDTOGGLEPAUSE = "togglepause"; // 切换暂停
    public static final String CMDSTOP = "stop"; // 停止
    public static final String CMDPAUSE = "pause"; // 暂停
    public static final String CMDPLAY = "play"; // 播放
    public static final String CMDPREVIOUS = "previous" ;// 上一首
    public static final String CMDNEXT = "next";// 下一首
    public static final String CMDNOTIF = "buttonId";//

    public static final String TOGGLEPAUSE_ACTION = "com.sgj.timber.togglepause";
    public static final String PAUSE_ACTION = "com.sgj.timber.pause";
    public static final String STOP_ACTION = "com.sgj.timber.stop";
    public static final String PREVIOUS_ACTION = "com.sgj.timber.previous";
    public static final String PREVIOUS_FORCE_ACTION = "com.sgj.timber.previous.force";
    public static final String NEXT_ACTION = "fcom.sgj.timber.next";
    public static final String REPEAT_ACTION = "com.sgj.timber.repeat"; // 单曲循环
    public static final String SHUFFLE_ACTION = "com.sgj.timber.shuffle"; // 随机播放

    public static final String SERVICECMD = "com.sgj.timber.musicservicecommand";

    private final BroadcastReceiver mIntentRecever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String command = intent.getStringExtra(CMDNAME);

            handleCommandIntent(intent);
        }
    };

    private void handleCommandIntent(Intent intent) {
        final String action = intent.getAction();
        final String command = SERVICECMD.equals(action) ? intent.getStringExtra(CMDNAME) : null;

        if(CMDNEXT.equals(command) || NEXT_ACTION.equals(action)){

        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter filter = new IntentFilter();
        filter.addAction(SERVICECMD);
        filter.addAction(TOGGLEPAUSE_ACTION);
        filter.addAction(PAUSE_ACTION);
        filter.addAction(STOP_ACTION);
        filter.addAction(NEXT_ACTION);
        filter.addAction(PREVIOUS_ACTION);
        filter.addAction(PREVIOUS_FORCE_ACTION);
        filter.addAction(REPEAT_ACTION);
        filter.addAction(SHUFFLE_ACTION);

        registerReceiver(mIntentRecever, filter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mIntentRecever);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public static final class ServiceStub extends ITimberService.Stub{

        private static WeakReference<MusicService> mService;

        public ServiceStub(MusicService service) {
            mService = new WeakReference<MusicService>(service);
        }

        @Override
        public void openFile(String path) throws RemoteException {

        }

        @Override
        public void open(long[] list, int position, long sourceId, int sourctType) throws RemoteException {

        }

        @Override
        public void stop() throws RemoteException {

        }

        @Override
        public void play() throws RemoteException {

        }

        @Override
        public void next() throws RemoteException {

        }
    }
}
