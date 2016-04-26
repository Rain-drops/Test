package com.sgj.ayibang;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

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

    final Handler myHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        mContext = this;

        JavaScriptInterface anInterface = new JavaScriptInterface(mContext);

        WebSettings mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setAppCacheEnabled(true);
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        mWebView.setWebChromeClient(new ChromeClient());
        mWebView.setWebViewClient(new MyWebClient());

        mWebView.addJavascriptInterface(anInterface, "AndroidFunction");


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

    private class ChromeClient extends WebChromeClient {

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

    private class MyWebClient extends WebViewClient {

        public MyWebClient() {
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!TextUtils.isEmpty(url)) {
                view.loadUrl(url);
            }

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

    @JavascriptInterface
    public void showToast(String activityName) {
        //此处应该定义常量对应，同时提供给web页面编写者
        if (TextUtils.equals(activityName, "10086")) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    public class JavaScriptInterface {
        Context mContext;

        JavaScriptInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void showToast(String webMessage) {
            final String msgeToast = webMessage;
            Log.e(TAG, msgeToast);
            myHandler.post(new Runnable() {
                @Override
                public void run() {
                    // This gets executed on the UI thread so it can safely modify Views

                }
            });
            if ("10086".equals(msgeToast)) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:"+msgeToast);
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                intent.setData(uri);
                startActivity(intent);
            }else {
                startActivity(new Intent(mContext, MainActivity.class));
                DetailsActivity.this.finish();
            }
        }
    }

}
