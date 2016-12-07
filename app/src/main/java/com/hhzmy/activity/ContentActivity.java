package com.hhzmy.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.pay.PayResult;
import com.hhzmy.util.OrderInfoUtil2_0;

import java.util.Map;


import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hhzmy.pay.PayDemoActivity.APPID;
import static com.hhzmy.pay.PayDemoActivity.RSA_PRIVATE;

public class ContentActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int SDK_PAY_FLAG = 1;
    @BindView(R.id.fl_main)
    ViewPager flMain;
    @SuppressLint("HandlerLeak")
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
                        Toast.makeText(ContentActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(ContentActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }

                default:
                    break;
            }
        }

        ;
    };


    @BindView(R.id.mb_back_content)
    ImageButton mbBackContent;
    @BindView(R.id.rb_goods_content)
    RadioButton rbGoodsContent;
    @BindView(R.id.rb_content_content)
    RadioButton rbContentContent;
    @BindView(R.id.rb_comment_content)
    RadioButton rbCommentContent;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    @BindView(R.id.mb_shopping_content)
    ImageButton mbShoppingContent;
    @BindView(R.id.mb_ebuy_content)
    ImageButton mbEbuyContent;
    @BindView(R.id.tv_kefu)
    TextView tvKefu;
    @BindView(R.id.tv_dianpu)
    TextView tvDianpu;
    @BindView(R.id.rb_shoucang)
    TextView rbShoucang;
    @BindView(R.id.bt_justbuy)
    Button btJustbuy;
    @BindView(R.id.bt_toshop)
    Button btToshop;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ButterKnife.bind(this);//初始化控件
//
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED
//                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            callPhone();
//            readExternalStorage();
//            writeExternalStorage();
//        }
        btJustbuy.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_justbuy:
                if (TextUtils.isEmpty(APPID) || TextUtils.isEmpty(RSA_PRIVATE)) {
                    new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    //
                                    finish();
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

                        PayTask alipay = new PayTask(ContentActivity.this);
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
        }
    }




}
