package com.sgj.ayibang.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by John on 2016/3/25.
 */
public class AppUtils {

    /**
     * 屏幕显示尺寸
     * @param context
     * @return
     */
    public static DisplayMetrics getScreenDisplayMetrics(Context context){
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }
}
