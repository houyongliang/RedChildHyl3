package com.hhzmy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.hhzmy.adpter.MyMainPagerAdapter;
import com.hhzmy.base.BaseActivity;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.httputil.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirstloadActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    @BindView(R.id.ll_firstload_dot)
    LinearLayout llFirstloadDot;
    private String key = "HHZ";
    @BindView(R.id.vp_firstload)
    ViewPager vpFirstload;
    @BindView(R.id.ib_firstload_pass)
    ImageButton ibFirstloadPass;
    @BindView(R.id.ib_firstload_start)
    ImageButton ibFirstloadStart;
    private List<ImageView> listPage;
    private int[] imgs = new int[]{R.mipmap.guide_page1, R.mipmap.guide_page2, R.mipmap.guide_page3};
    private List<ImageView> listDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstload);
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        listPage = new ArrayList<ImageView>();
        listDot = new ArrayList<ImageView>();
        ImageView iv = null;
        ImageView ivDot=null;
        //初始化间距
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(40,20);
        params.leftMargin=10;
        for (int i = 0; i < imgs.length; i++) {
            iv = new ImageView(this);
            iv.setImageResource(imgs[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            listPage.add(iv);

            ivDot=new ImageView(this);
            ivDot.setImageResource(R.drawable.sl_dot_selector);
            ivDot.setLayoutParams(params);
            if(i==0){
                llFirstloadDot.addView(ivDot);
                ivDot.setSelected(true);
            }else{
                llFirstloadDot.addView(ivDot);
                ivDot.setSelected(false);
            }
            listDot.add(ivDot);


        }

    }

    private void initEvent() {
        vpFirstload.setAdapter(new MyMainPagerAdapter(listPage));
        vpFirstload.setCurrentItem(0);
        //VIEWPager设置滑动监听
        vpFirstload.setOnPageChangeListener(this);
    }

    private void initView() {
        ButterKnife.bind(this);
        ibFirstloadPass.setOnClickListener(this);
        ibFirstloadStart.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ib_firstload_pass:
                jumpActivity();
                break;
            case R.id.ib_firstload_start:
                jumpActivity();
                break;
        }
    }

    public void jumpActivity() {
        Intent intent = new Intent(FirstloadActivity.this, MainActivity.class);
        startActivity(intent);
        Utils.putSP(this, key, true);
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        vpFirstload.setCurrentItem(position);
        if (position == listPage.size() - 1) {
            ibFirstloadPass.setVisibility(View.INVISIBLE);
            ibFirstloadStart.setVisibility(View.VISIBLE);
        } else {
            ibFirstloadPass.setVisibility(View.VISIBLE);
            ibFirstloadStart.setVisibility(View.INVISIBLE);
        }
        //小圆点设置
        for (int i = 0; i < listDot.size(); i++) {
            if(i==position){
                listDot.get(i).setSelected(true);
            }else{
                listDot.get(i).setSelected(false);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
