package com.hyl.mis.hyl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class LeadActivity extends Activity {
    private String key="IsFirst";
    private int i = 3;
    private boolean flag = true;
    

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int num = msg.what;
            if (num == 0) {
                pic_one.setVisibility(View.GONE);
                pic_two.setVisibility(View.VISIBLE);
                tv_jump.setVisibility(View.VISIBLE);
                tv_title.setVisibility(View.GONE);
                tv_jump.setText("跳过" + i + "S");
                mHandler.sendEmptyMessageDelayed(1, 1000);
            }
            if (num == 1) {
                pic_one.setVisibility(View.GONE);
                pic_two.setVisibility(View.VISIBLE);
                tv_jump.setVisibility(View.VISIBLE);
                tv_title.setVisibility(View.GONE);
                if (i > 0) {
                    i--;
                    tv_jump.setText("跳过" + i + "S");
                    mHandler.sendEmptyMessageDelayed(1, 1000);
                } else {
                    i = 3;
                    if (flag) {
                        Intent intent = new Intent(LeadActivity.this, FirstLeadActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        }
    };
    private ImageView pic_one;
    private ImageView pic_two;
    private TextView tv_jump;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化控件
        initView();
        //初始化事件
        initEvent();
    }

    private void initEvent() {
        pic_one.setVisibility(View.VISIBLE);
        pic_two.setVisibility(View.GONE);
        tv_jump.setVisibility(View.GONE);
        tv_title.setVisibility(View.VISIBLE);
        tv_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
                Intent intent = new Intent(LeadActivity.this, FirstLeadActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }


    private void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_lead);
        pic_one = (ImageView) findViewById(R.id.lead_iv_pic_one);
        pic_two = (ImageView) findViewById(R.id.lead_iv_pic_two);
        tv_jump = (TextView) findViewById(R.id.lead_tv_jump);
        tv_title = (TextView) findViewById(R.id.lead_tv_title);
    }


}
