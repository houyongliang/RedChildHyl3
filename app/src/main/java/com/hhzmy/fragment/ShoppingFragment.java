package com.hhzmy.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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

import com.alipay.sdk.app.PayTask;
import com.hhzmy.activity.BaiduMapActivity;
import com.hhzmy.activity.ContentActivity;
import com.hhzmy.adpter.HomeRecyclerViewAdapter;
import com.hhzmy.adpter.ShopRyViewAdapter;
import com.hhzmy.base.BaseFragment;
import com.hhzmy.bean.BeanHome;
import com.hhzmy.bean.ShopBean;
import com.hhzmy.httputil.OkHttp;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.pay.PayResult;
import com.hhzmy.tool.Tool;
import com.hhzmy.util.OrderInfoUtil2_0;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;

import static android.R.attr.action;
import static android.R.attr.contextClickable;
import static android.R.attr.data;
import static com.baidu.location.b.g.S;
import static com.baidu.location.b.g.t;
import static com.hhzmy.activity.ContentActivity.SDK_PAY_FLAG;
import static com.hhzmy.mis.redchildhyl.R.id.bt_shop_payall;
import static com.hhzmy.mis.redchildhyl.R.id.cb_shop_checkall;
import static com.hhzmy.mis.redchildhyl.R.id.rv_home;
import static com.hhzmy.pay.PayDemoActivity.APPID;
import static com.hhzmy.pay.PayDemoActivity.RSA_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends BaseFragment implements View.OnClickListener {
    /**
     * urlHome 主页网址
     */
    public static final int SDK_PAY_FLAG = 1;

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(getActivity(), "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }

                default:
                    break;
            }
        }

        ;
    };



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
    @BindView(bt_shop_payall)
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
        btShopPayall.setOnClickListener(this);
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
            case R.id.bt_shop_payall:
//                Toast.makeText(mActivity, "支付", Toast.LENGTH_SHORT).show();

                if (TextUtils.isEmpty(APPID) || TextUtils.isEmpty(RSA_PRIVATE)) {
                    new AlertDialog.Builder(getContext()).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    //
//                                    finish();
                                }
                            }).show();
                    return;
                }

                /**
                 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
                 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
                 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
                 *
                 * orderInfo的获取必须来自服务端；
                 */
                Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID);
                String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
                String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE);
                final String orderInfo = orderParam + "&" + sign;

                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {

                        PayTask alipay = new PayTask(getActivity());
                        Map<String, String> result = alipay.payV2(orderInfo, true);
                        Log.i("msp", result.toString());

                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                Thread payThread = new Thread(payRunnable);
                payThread.start();
//                Tool.myStartActivity(ContentActivity.this, PayDemoActivity.class,null,null);
                break;
            default:
                break;
        }
   }
}
