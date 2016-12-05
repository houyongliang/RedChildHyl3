package com.hhzmy.fragment;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hhzmy.activity.TestScanActivity;
import com.hhzmy.adpter.HomeRecyclerViewAdapter;
import com.hhzmy.adpter.MyHomePagerAdapter;
import com.hhzmy.adpter.MyRecyclerViewAdapter;
import com.hhzmy.base.BaseFragment;
import com.hhzmy.bean.BeanHome;
import com.hhzmy.httputil.OkHttp;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.tool.Tool;
import com.lidroid.xutils.HttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Request;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static android.R.attr.data;
import static android.R.id.list;
import static com.hhzmy.tool.Tool.getScreenHeight;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements ViewPager.OnPageChangeListener,View.OnClickListener {

/** 获取视图*/
    /**
     * 获取布局视图
     */
    private View homeView;
    /**
     * 获取搜索框
     */
    private EditText et_search;
    /**
     * 无限轮播
     */
    private ViewPager vp_home_loop;
    /**
     * RecyclerView 视图
     */
    private RecyclerView rv_home;
    /**
     * 二维码按钮和消息按钮
     */
    private TextView tv_mes, tv_scan;
    /**
     * 小圆点布置
     */
    private LinearLayout ll_home;
    /**
     * VP_HOME 适配器
     */
    private MyHomePagerAdapter adapter;
    /**
     * urlHome 主页网址
     */
    private String urlHome = "http://mock.eoapi.cn/success/jSWAxchCQfuhI6SDlIgBKYbawjM3QIga";
    /**
     * 获取无限轮播数据
     */
    private List<BeanHome.DataBean.TagBean> tag;
    /**
     * 获取数据
     */
    private List<BeanHome.DataBean> data;
    private ArrayList<ImageView> listDot;

    /**
     * 获取
     */

    private String TAG = "HomeFragment";
    private BeanHome.DataBean dataBean;

    public HomeFragment() {

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case 0:
                    int poss = vp_home_loop.getCurrentItem();
                    poss++;
                    vp_home_loop.setCurrentItem(poss);
                    mHandler.sendEmptyMessageDelayed(0, 2000);
                    break;

            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeView = inflater.inflate(R.layout.fragment_home, container, false);
        initView();//初始化

        return homeView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData();//初始化数据
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 网络请求
     */

    private void initData() {
        OkHttp.getAsync(urlHome, new OkHttp.DataCallBack() {


            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(getActivity(), "亲，请求失败了。。", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                BeanHome beanHome = Tool.parseJsonWithGson(result, BeanHome.class);
                data = beanHome.getData();
                if (data != null && data.size() > 0) {
                    wuXianLunBo(data, 0);//无限轮播
                }
                if (data != null && data.size() > 2) {


          /*默认设置为 垂直样式
           GridLayoutManager glm = new GridLayoutManager(getActivity(), 4);
                        glm.setOrientation(OrientationHelper.VERTICAL);
                        rv_home.setLayoutManager(glm);*/
                    rv_home.setLayoutManager(new LinearLayoutManager(getActivity()));
                    HomeRecyclerViewAdapter mAdapter = new HomeRecyclerViewAdapter(getActivity(),beanHome);
                    rv_home.setAdapter(mAdapter);
                    //                    List<BeanHome.DataBean.TagBean> list_tag_two = data.get(1).getTag();
                    //                    List<BeanHome.DataBean.TagBean> list_tag_three = data.get(2).getTag();
                    //                    Log.e(TAG, "list_tag_two.size: "+list_tag_two.size() );
                    //                    map.put(0,list_tag_two);
                    //                    map.put(1,list_tag_three);
                    //                    MyRecyclerViewAdapter adapterOne = new MyRecyclerViewAdapter(getActivity(), map);
                    //                    rv_home.setAdapter(adapterOne);

                }

            }
        });
    }

    /**
     * 无限轮播
     */
    private void wuXianLunBo(List<BeanHome.DataBean> datas, int possion) {
        /*防止无限轮播 越来越快*/
        mHandler.removeMessages(0);
        dataBean = datas.get(possion);
        tag = dataBean.getTag();
        Log.e(TAG, "requestSuccess:" + tag.size());
        reflashData(tag, getActivity());
        initDot(tag);
        vpListener();
        mHandler.sendEmptyMessage(0);
    }

    /**
     * 刷新数据
     */
    private void reflashData(List<BeanHome.DataBean.TagBean> tag, Activity activity) {
        if (tag != null && tag.size() > 0) {
            if (adapter == null) {
                adapter = new MyHomePagerAdapter(tag, activity);
                vp_home_loop.setAdapter(adapter);
                vp_home_loop.setCurrentItem(10000 * tag.size());
            } else {
                adapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 初始化视图  动态设置高度
     */
    private void initView() {
        et_search = (EditText) homeView.findViewById(R.id.et_search);
        vp_home_loop = (ViewPager) homeView.findViewById(R.id.vp_home_loop);//无限循环
        rv_home = (RecyclerView) homeView.findViewById(R.id.rv_home);
        tv_mes = (TextView) homeView.findViewById(R.id.tv_mes);
        tv_scan = (TextView) homeView.findViewById(R.id.tv_scan);
        ll_home = (LinearLayout) homeView.findViewById(R.id.ll_home);
        //动态设置vp高度
        int height = Tool.getScreenHeight(getActivity());
        RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) vp_home_loop.getLayoutParams(); // 取控件mGrid当前的布局参数
        linearParams.height = height / 4;// 当控件的高强制设成50象素
        vp_home_loop.setLayoutParams(linearParams);

        tv_scan.setOnClickListener(this);

    }

    /**
     * 初始化点
     */
    private void initDot(List<BeanHome.DataBean.TagBean> list) {
        listDot = new ArrayList<ImageView>();
        ImageView ivDot = null;
        //初始化间距
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(40, 20);
        params.leftMargin = 10;
        for (int i = 0; i < list.size(); i++) {

            ivDot = new ImageView(getActivity());
            ivDot.setImageResource(R.drawable.sl_dot_selector);
            ivDot.setLayoutParams(params);
            if (i == 0) {
                ll_home.addView(ivDot);
                ivDot.setSelected(true);
            } else {
                ll_home.addView(ivDot);
                ivDot.setSelected(false);
            }
            listDot.add(ivDot);
        }

    }

    ;

    /**
     * vp 设置监听
     */
    private void vpListener() {
        vp_home_loop.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int index = position % listDot.size();
        //小圆点设置
        for (int i = 0; i < listDot.size(); i++) {
            if (i == index) {
                listDot.get(i).setSelected(true);
            } else {
                listDot.get(i).setSelected(false);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_scan:
                Intent intent=new Intent(getActivity(), TestScanActivity.class);
                startActivity(intent);
                break;
        }
    }
//权限处理
    //扫描跳转Activity RequestCode
    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;
    @Override
    public void onStart() {
        super.onStart();
//        requestCodeQrcodePermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }


    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQrcodePermissions() {
//        String[] perms = {Manifest.permission.CAMERA};
//        if (!EasyPermissions.hasPermissions(getActivity(), perms)) {
//            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
//        }
    }

    @Override
    public void onDestroy() {

        adapter=null;
        super.onDestroy();
    }
}
