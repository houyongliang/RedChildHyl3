package com.hhzmy.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.baidu.platform.comapi.map.I;
import com.hhzmy.fragment.ClassFragment;
import com.hhzmy.fragment.HomeFragment;
import com.hhzmy.fragment.MyebuyFragment;
import com.hhzmy.fragment.ShoppingFragment;
import com.hhzmy.mis.redchildhyl.R;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {


    private RadioGroup rg_mian;
    private FrameLayout fl_mian;
    private List<Fragment> list_fg;
    private FragmentManager fragmentManager;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String ipg = getIntent().getStringExtra(GET_USER_FOR_THREAD_IPG);
//        String name = getIntent().getStringExtra(GET_USER_FOR_THREAD_NAME);
//        String gender = getIntent().getStringExtra(GET_USER_FOR_THREAD_GENDER);
        MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this, "584a8e3a2ae85b37e800083a", "hyl"));


        initView();
        initData();
        initEvent();
    }

    private void initData() {
        list_fg = new ArrayList<Fragment>();
        list_fg.add(new HomeFragment());
        list_fg.add(new ClassFragment());
        list_fg.add(new ShoppingFragment());
        list_fg.add(new MyebuyFragment());
        commitFragment(0, "home");
        rg_mian.check(R.id.rb_home);//默认选中


    }

    private void commitFragment(int posstion, String tag) {
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        if (posstion < list_fg.size()) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(fl_mian.getId(), list_fg.get(posstion), tag);
            fragmentTransaction.commit();
        }

    }

    private void initEvent() {
        rg_mian.setOnCheckedChangeListener(this);
    }

    private void initView() {
        fl_mian = (FrameLayout) findViewById(R.id.fl_main);
        rg_mian = (RadioGroup) findViewById(R.id.rg_main);
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        Log.e(TAG, "onCheckedChanged: " + i);
        switch (i) {
            case R.id.rb_home:
                commitFragment(0, "home");
                break;
            case R.id.rb_class:
                commitFragment(1, "class");
                break;
            case R.id.rb_shopping:
                commitFragment(2, "shopping");
                break;
            case R.id.rb_myebuy:
                commitFragment(3, "myebuy");
                break;

        }
    }

//    public static Intent toMainActivity(Context context,String str){
//        Intent intent=new Intent(context,MainActivity.class);
//        intent.putExtra(GET_USER_FOR_THREAD_IPG,str);
//        intent.putExtra(GET_USER_FOR_THREAD_NAME,str);
//        intent.putExtra(GET_USER_FOR_THREAD_GENDER,str);
//        return  intent;
//    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
