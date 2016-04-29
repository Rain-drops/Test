package com.sgj.ayibang;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by John on 2016/4/28.
 */
public class MusicPlayer {

    public static final class ServiceBinder implements ServiceConnection{

        public ServiceBinder() {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
