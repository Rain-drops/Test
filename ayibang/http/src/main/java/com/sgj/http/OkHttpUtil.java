package com.sgj.http;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by John on 2016/3/22.
 */
public class OkHttpUtil {
    private static final OkHttpClient mOkHttpClient = new OkHttpClient();
    static {
        mOkHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
    }
}
