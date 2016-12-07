package com.hhzmy.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhzmy.activity.BaiduMapActivity;
import com.hhzmy.base.BaseFragment;
import com.hhzmy.bean.LoginThread;
import com.hhzmy.httputil.Utils;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.tool.Tool;


import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hhzmy.httputil.Utils.getSPString;

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
//        String pic=Utils.getSPString(getActivity(),"profile_image_url");
//        String name=Utils.getSPString(getActivity(),"screen_name");
//        String gender=Utils.getSPString(getActivity(),"gender");
//        if(!TextUtils.isEmpty(pic)){
//            Tool.displayImage(getActivity(),ivFragMyHeadPic,pic);
//        }
//        if(!TextUtils.isEmpty(name)){
//            tvFragMyName.setText(name);
//        }
//        if(!TextUtils.isEmpty(gender)){
//            Tool.displayImage(getActivity(),ivFragMyHeadPic,pic);
//            tvFragMyName.append("   "+gender);
//        }



        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_baidumap:
                Tool.myStartActivity(getActivity(), BaiduMapActivity.class, null, null);
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginThread = EventBus.getDefault().getStickyEvent(LoginThread.class);
        if(loginThread !=null){
            Tool.displayImage(getActivity(),ivFragMyHeadPic, loginThread.url);
            tvFragMyName.setText(loginThread.name);
            tvFragMyName.append("   "+ loginThread.gender);

        }
//        EventBus.getDefault().cancelEventDelivery(LoginThread.class) ;
//        EventBus.getDefault().register(this);
    }

    //    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)//注意此处需要增加sticky = true，表明处理置顶事件
//    public void handleEvent(LoginThread loginThread) {
//        // UI updates must run on MainThread
//        Tool.displayImage(getActivity(),ivFragMyHeadPic,loginThread.url);
//        tvFragMyName.setText(loginThread.name);
//        tvFragMyName.append("   "+loginThread.gender);
//    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
