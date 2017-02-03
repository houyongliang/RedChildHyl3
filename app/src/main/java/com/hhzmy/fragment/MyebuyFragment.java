package com.hhzmy.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhzmy.activity.BaiduMapActivity;
import com.hhzmy.activity.XunFeiActivity;
import com.hhzmy.base.BaseFragment;
import com.hhzmy.bean.LoginThread;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.tool.Tool;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyebuyFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.bt_baidumap)
    Button btBaidumap;
    @BindView(R.id.iv_fragMy_headPic)
    ImageView ivFragMyHeadPic;
    @BindView(R.id.tv_fragMy_name)
    TextView tvFragMyName;
    @BindView(R.id.bt_xunfei)
    Button btXunfei;
    @BindView(R.id.touxiang)
    ImageView touxiang;
    @BindView(R.id.shoujihao)
    TextView shoujihao;
    @BindView(R.id.shezhi)
    TextView shezhi;
    @BindView(R.id.xiaoxi)
    ImageView xiaoxi;
    @BindView(R.id.zhanghuguanli)
    TextView zhanghuguanli;
    @BindView(R.id.daizhifu)
    LinearLayout daizhifu;
    @BindView(R.id.daishouhuo)
    LinearLayout daishouhuo;
    @BindView(R.id.daipingjia)
    LinearLayout daipingjia;
    @BindView(R.id.tuihuanweixiu)
    LinearLayout tuihuanweixiu;
    @BindView(R.id.shangpinshoucang)
    LinearLayout shangpinshoucang;
    @BindView(R.id.chakanyunzuan)
    LinearLayout chakanyunzuan;
    @BindView(R.id.zuji)
    LinearLayout zuji;
    @BindView(R.id.youhuiquan)
    LinearLayout youhuiquan;
    @BindView(R.id.bangdingshezhi)
    LinearLayout bangdingshezhi;
    @BindView(R.id.zhanghuanquan)
    LinearLayout zhanghuanquan;
    @BindView(R.id.activity_my)
    LinearLayout activityMy;
    private LoginThread loginThread;

    public MyebuyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myebuy, container, false);
        ButterKnife.bind(this, view);
        btBaidumap.setOnClickListener(this);
        btXunfei.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_baidumap:
                Tool.myStartActivity(getActivity(), BaiduMapActivity.class, null, null);
                break;
            case R.id.bt_xunfei:
                startActivity(new Intent(getActivity(), XunFeiActivity.class));
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginThread = EventBus.getDefault().getStickyEvent(LoginThread.class);
        if (loginThread != null) {
            Tool.displayImage(getActivity(), ivFragMyHeadPic, loginThread.url);
            Tool.displayImage(getActivity(), touxiang, loginThread.url);

            tvFragMyName.setText(loginThread.name);
            tvFragMyName.append("   " + loginThread.gender);
            shoujihao.setText(loginThread.name);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
