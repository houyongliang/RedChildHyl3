package com.hyl.mis.hyl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.hyl.mis.hyl.Utils.Utils;
import com.hyl.mis.hyl.adapter.MyPagerAdapter;

public class FirstLeadActivity extends Activity {
    private String key = "IsFirst";
    private int[] imgs = new int[]{R.drawable.huawei_guidance_1, R.drawable.huawei_guidance_2, R.drawable.huawei_guidance_3, R.drawable.huawei_guidance_4};
    private ViewPager first_vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_first_lead);

        Boolean firstSP = Utils.getFirstSP(this, key);
        if (firstSP) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            this.finish();

        } else {
            //初始化控件
            initView();
            //初始化数据
            initData();
        }


    }

    private void initData() {
        int[] first_pics = new int[]{R.drawable.huawei_guidance_1, R.drawable.huawei_guidance_2, R.drawable.huawei_guidance_3, R.drawable.huawei_guidance_4};
        first_vp.setAdapter(new MyPagerAdapter(FirstLeadActivity.this, first_pics));
    }

    private void initView() {

        first_vp = (ViewPager) findViewById(R.id.first_viewPager);
    }


}
