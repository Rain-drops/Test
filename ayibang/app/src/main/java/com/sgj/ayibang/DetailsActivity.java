package com.sgj.ayibang;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/4/6.
 */

public class DetailsActivity extends Activity {

    private static final String TAG = "DetailsActivity";
    private Context mContext;

    @Bind(R.id.webView)
    WebView mWebView;
    @Bind(R.id.btn_button)
    Button mButton;

    String mUrl = "file:///android_asset/MyHtml.html";




    @SuppressLint("JavascriptInterface")
    @JavascriptInterface
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        mContext = this;

        WebSettings mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setAppCacheEnabled(true);
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        mWebView.addJavascriptInterface(new Object() {
            // 这里我定义了一个拨打的方法
            public void startPhone(String num) {
                Log.e(TAG, num);
                Intent intent = new Intent();
                intent.setClass(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, "demo");

        mWebView.setWebChromeClient(new ChromeClient());
        mWebView.setWebViewClient(new MyWebClient());

        mWebView.loadUrl(mUrl);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("javascript:show('activity传过来的数据')");
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private class ChromeClient extends WebChromeClient{

        public ChromeClient() {
            super();
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Log.e(TAG, " -- " + title + " -- ");
            setTitle(title);
        }
    }

    private class MyWebClient extends WebViewClient{

        public MyWebClient() {}

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(!TextUtils.isEmpty(url)){
                view.loadUrl(url);
            }

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

}
