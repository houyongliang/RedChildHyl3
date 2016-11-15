package com.hyl.mis.hyl;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hyl.mis.hyl.R;

public class WebviewActivity extends Activity {
private String key="URL";
    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_webview);
      Intent intent=getIntent();
        String url=intent.getStringExtra(key);
        wv = (WebView) findViewById(R.id.wv_wv);

        initWebView();
        wv.loadUrl(url);
    }

    private void initWebView() {
//设置支持js
        WebSettings setting = wv.getSettings();
        setting.setJavaScriptCanOpenWindowsAutomatically(true);
        setting.setJavaScriptEnabled(true);
        //设置当前显示
        wv.setWebChromeClient(new WebChromeClient());

    }
}
