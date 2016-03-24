package com.sgj.ayibang.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by John on 2016/3/24.
 */
public class NetUtils {
    public static boolean isNetworkConnected(Context context){

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = manager.getActiveNetworkInfo();

        if(info == null || info.isAvailable()){
            Toast.makeText(context, "没有可用网络", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public static boolean isWIFIConnected(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        return wifi;
    }
}
