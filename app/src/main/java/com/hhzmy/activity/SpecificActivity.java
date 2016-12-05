package com.hhzmy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hhzmy.mis.redchildhyl.R;

public class SpecificActivity extends AppCompatActivity {
    private String key="URL";
    private WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific);
        Intent intent=getIntent();
        String url=intent.getStringExtra(key);
        wv = (WebView) findViewById(R.id.wv);
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
