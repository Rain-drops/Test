package com.sgj.ayibang;

import android.os.Bundle;

/**
 * Created by John on 2016/4/8.
 */
public class ImageViewActivity extends BaseActivity {

    private static final String TAG = "ImageViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
