package com.sgj.ayibang.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sgj.ayibang.R;
import com.sgj.ayibang.url.Urls;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/4/21.
 */
public class TouTiaoActivity extends Activity {

    String mUrl = Urls.TOUTIAO_URL;
    @Bind(R.id.wv_webview)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_web);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setSupportZoom(true);

        mWebView.setWebViewClient(new MyWebClient());
        mWebView.setWebChromeClient(new ChromeClient());
        mWebView.loadUrl(mUrl);

    }

    class ChromeClient extends WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setTitle(title);
        }
    }

    class MyWebClient extends WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url != null){
                view.loadUrl(url);
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
