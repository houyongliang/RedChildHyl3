package com.hyl.mis.hyl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.hyl.mis.hyl.adapter.MyFragmentAdapter;
import com.hyl.mis.hyl.fragment.Fragment1_Home;
import com.hyl.mis.hyl.fragment.Fragment2_Classify;
import com.hyl.mis.hyl.fragment.Fragment3_Shopping;
import com.hyl.mis.hyl.fragment.Fragment4_User;
import com.hyl.mis.hyl.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private RadioGroup rgGroup_main;
    private NoScrollViewPager main_vp;
    private List<Fragment> list_fg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        rgGroup_main.check(R.id.rb_home);//默认选中 home页面
        rgGroup_main.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.rb_home:
                        main_vp.setCurrentItem(0);
                    break;
                    case R.id.rb_classify:
                        main_vp.setCurrentItem(1);
                        break;
                    case R.id.rb_shopping:
                        main_vp.setCurrentItem(2);
                        break;
                    case R.id.rb_user:
                        main_vp.setCurrentItem(3);
                        break;
                }
            }
        });
    }

    private void initData() {
        list_fg = new ArrayList<Fragment>();
        Fragment1_Home f1_home = new Fragment1_Home();
        Fragment2_Classify f2_classify = new Fragment2_Classify();
        Fragment3_Shopping f3_shopping = new Fragment3_Shopping();
        Fragment4_User f4_usrer = new Fragment4_User();
        list_fg.add(f1_home);
        list_fg.add(f2_classify);
        list_fg.add(f3_shopping);
        list_fg.add(f4_usrer);
        main_vp.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), list_fg));
    }

    private void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        rgGroup_main = (RadioGroup) findViewById(R.id.main_rg);
        main_vp = (NoScrollViewPager) findViewById(R.id.main_vp);
    }
}
