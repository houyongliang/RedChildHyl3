package com.hhzmy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.hhzmy.base.BaseActivity;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.httputil.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    private String key="HHZ";
    @BindView(R.id.iv_splash)
    ImageView ivSplash;
    private Boolean isFirst;//已经进入第一次

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        isFirst = Utils.getSP(this, key);
        initView();
        initEvent();
        
    }

    private void initEvent() {
        ivSplash.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isFirst){//已经进入第一次
                    Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{//第二次进入
                    Intent intent=new Intent(SplashActivity.this,FirstloadActivity.class);
                    startActivity(intent);
                }

                finish();
            }
        },3000);
    }

    private void initView() {
        ButterKnife.bind(this);
    }
}
