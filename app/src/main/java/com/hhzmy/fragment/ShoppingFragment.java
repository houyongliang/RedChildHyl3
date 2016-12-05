package com.hhzmy.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hhzmy.activity.BaiduMapActivity;
import com.hhzmy.adpter.HomeRecyclerViewAdapter;
import com.hhzmy.adpter.ShopRyViewAdapter;
import com.hhzmy.base.BaseFragment;
import com.hhzmy.bean.BeanHome;
import com.hhzmy.bean.ShopBean;
import com.hhzmy.httputil.OkHttp;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.tool.Tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;

import static android.R.attr.action;
import static android.R.attr.contextClickable;
import static android.R.attr.data;
import static com.baidu.location.b.g.S;
import static com.hhzmy.mis.redchildhyl.R.id.cb_shop_checkall;
import static com.hhzmy.mis.redchildhyl.R.id.rv_home;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends BaseFragment implements View.OnClickListener {
    /**
     * urlHome 主页网址
     */
    private String urlHome = "http://mock.eoapi.cn/success/jSWAxchCQfuhI6SDlIgBKYbawjM3QIga";

    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.bt_address)
    Button btAddress;
    @BindView(R.id.tv_shopcar_title)
    TextView tvShopcarTitle;
    @BindView(R.id.tv_shop_editbutton)
    TextView tvShopEditbutton;
    @BindView(R.id.rv_shop)
    RecyclerView rvShop;
    @BindView(cb_shop_checkall)
    CheckBox cbShopCheckall;
    @BindView(R.id.tv_shop_allprice)
    TextView tvShopAllprice;
    @BindView(R.id.rela_allprice)
    RelativeLayout relaAllprice;
    @BindView(R.id.bt_shop_payall)
    Button btShopPayall;
    @BindView(R.id.shop_lin2)
    LinearLayout shopLin2;
    private boolean isChecked;
    /*数据*/
    List<ShopBean> list;
   

    private Activity mActivity;
    private ShopRyViewAdapter adapter;

    public ShoppingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        ButterKnife.bind(this, view);

        btAddress.setOnClickListener(this);
        initData();
        return view;
    }

    private void initData() {
        list=new ArrayList<ShopBean>();
        ShopBean sb=null;
        for(int i=0;i<3;i++){
            sb=new ShopBean();
            sb.goodName="这是好商品"+i;
            list.add(sb);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity=getActivity();

        rvShop.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new ShopRyViewAdapter(mActivity,list,cbShopCheckall);
        rvShop.setAdapter(adapter);
        cbShopCheckall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(mActivity, "cbShopCheckall.isChecked()", Toast.LENGTH_SHORT).show();
                adapter.checked(cbShopCheckall.isChecked());
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //拿到 定位后的地址
        if(resultCode==1){
            String action = data.getAction();
            tvAddress.setText(action);
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_address:
                startActivityForResult(new Intent(getActivity(), BaiduMapActivity.class), 1);
                break;

            default:
                break;
        }
   }
}
