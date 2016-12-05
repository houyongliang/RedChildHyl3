package com.hhzmy.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hhzmy.activity.BaiduMapActivity;
import com.hhzmy.base.BaseFragment;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.tool.Tool;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.alipay.sdk.app.statistic.c.s;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyebuyFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.bt_baidumap)
    Button btBaidumap;

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
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_baidumap:
                Tool.myStartActivity(getActivity(), BaiduMapActivity.class,null,null);
            break;
        }
    }
}
