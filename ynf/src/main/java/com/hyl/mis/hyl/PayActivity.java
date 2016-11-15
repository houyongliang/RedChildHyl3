package com.hyl.mis.hyl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.hyl.mis.hyl.Bean.ShoppingBean;
import com.hyl.mis.hyl.Utils.SignUtils;
import com.hyl.mis.hyl.database.MyDatabase;
import com.hyl.mis.hyl.domain.PayResult;
import com.lidroid.xutils.BitmapUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * 支付界面
 */
public class PayActivity extends Activity implements View.OnClickListener{
//---------------------------

    //Pid 商户在支付宝的id
    public static final String PARTNER = "2088901305856832";

    // 商户收款账号
    public static final String SELLER = "8@qdbaiu.com";

    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM/KCxg/OIj6er2GEig0DOkHqBSzEPHGigMbTXP1k2nrxEHeE59xOOuyovQH/A1LgbuVKyOac3uAN4GXIBEoozRVzDhu5dobeNm48BPcpYSAfvN3K/5GLacvJeENqsiBx8KufM/9inlHaDRQV7WhX1Oe2ckat1EkdHwxxQgc36NhAgMBAAECgYEAwn3sWpq6cUR65LD8h9MIjopTImTlpFjgz72bhsHDZK6A+eJDXcddrwh7DI34t/0IBqu+QEoOc/f0fIEXS9hMwTvFY59XG7M8M6SdeaAbemrGmZ1IdD6YDmpbQFHn2ishaYF0YDZIkBS3WLDFrtk/efaarBCpGAVXeEiVQE4LewECQQD5W1rpkq+dHDRzzdtdi1bJ479wun5CfmVDVb2CJH7Iz0t8zyp/iEVV2QELnxZMphmoSzKaLXutTTj2OImpzCtRAkEA1VMxG6nQq9NkU51H1+I3mlUXRZ0XeFA1GFJ7xWpNRAVhEWlDz2zy9v/gX+RFpNC3f5uznycas70Xp78ew43TEQJAZFFqi9mlqTF1sLk67bFnIyXrGPEOZrXvC13tNfR0xVkQZ4/46wHp0xXQo9pG4GNaoyhNnVV7EkelCPnJ+HPZYQJAQh6T9QgQZoGR8hyovPAf3dUL7oa/VIo/urcuJ8VIB5JHQNdIrk0NjaNHj1E4iNosVgATj3vWWel9IIArb99QkQJAKvfm78lwnImtg5IM604hdn/Wu1XF8tpxsKLWcnfchMr0bM9rCmKmhAY+wdmqSyPZRiNb1QaaaDTqJxLy6AnQ+Q==";

    private static final int SDK_PAY_FLAG = 1;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };
    //-----------------------
    private TextView tv_totalprice_pay;
    private Button btn_pay;
    private MyDatabase mydb;
    private String tag = "PayActivity";
    private ListView lv_pay;
    private Activity mactivity = PayActivity.this;
    private ArrayList<ShoppingBean> list;
    private ImageView iv_shoping;
    private TextView tv_listitem_name;
    private TextView tv_listitem_price;
    private TextView tv_listitem_num;
    private LinearLayout ll_listitem_pics;
    private LinearLayout.LayoutParams params;
//-----------------------------------------

    //-----------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pay);
        mydb = new MyDatabase(this);
        initView();//初始化控件
        initData();//初始化数据
    }
//--------------------------------------------



    //------------------------------------------
    private void initData() {
        list = new ArrayList<ShoppingBean>();
        SQLiteDatabase db = mydb.getWritableDatabase();

        Cursor cursor = db.query("shopping", null, "state=?", new String[]{"2"}, null, null, null);
        ShoppingBean shoppingBean = null;
        while (cursor.moveToNext()) {
            shoppingBean = new ShoppingBean();
            shoppingBean.id = cursor.getInt(cursor.getColumnIndex("_id"));
            shoppingBean.goods_name = cursor.getString(cursor.getColumnIndex("goods_name"));
            shoppingBean.shop_price = cursor.getInt(cursor.getColumnIndex("shop_price"));
            shoppingBean.numShow = cursor.getInt(cursor.getColumnIndex("numShow"));
            shoppingBean.goodsimg = cursor.getString(cursor.getColumnIndex("goods_img"));
            shoppingBean.is_activity_goods = cursor.getString(cursor.getColumnIndex("is_activity_goods"));
            shoppingBean.is_allow_credit = cursor.getString(cursor.getColumnIndex("is_allow_credit"));
            shoppingBean.url = cursor.getString(cursor.getColumnIndex("url"));
            list.add(shoppingBean);
        }
        Log.e(tag, list.toString());
        PayAdapter adapter = new PayAdapter();
        lv_pay.setAdapter(adapter);
        tv_totalprice_pay.setText("实付："+adapter.getTotalprice()+"");

    }

    private void initView() {
        tv_totalprice_pay = (TextView) findViewById(R.id.tv_totalprice_pay);
        lv_pay = (ListView) findViewById(R.id.lv_pay);
        btn_pay = (Button) findViewById(R.id.btn_pay);
        btn_pay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_pay:
                //采用支付宝方式进行支付
                alipayMoney(0.01);

                break;
            default:
                break;
        }
    }

    private void alipayMoney(double price) {
        //提交订单点击事件
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }

        //这里需要自己根据实际去传值
        String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", price+"");

/**
 * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
 */
        String sign = sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/**
 * 完整的符合支付宝参数规范的订单信息
 */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(PayActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

// 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

    /**
     * create the order info. 创建订单信息
     */
    private String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号--------------------------拿到提交订单返回的数据
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";//-----------传

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径-------商户服务器
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    class PayAdapter extends BaseAdapter {//设置适配器


        private double getTotalprice() {//获取总价方法
            double totalprice = 0;
            for (int i = 0; i < list.size(); i++) {
                ShoppingBean shoppingBean = list.get(i);
                totalprice += shoppingBean.shop_price * shoppingBean.numShow;
            }
            return totalprice;
        }

        private int getTotalnum() {//获取总数量的方法
            int num = 0;
            for (int i = 0; i < list.size(); i++) {
                ShoppingBean shoppingBean = list.get(i);
                num += shoppingBean.numShow;
            }
            return num;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
//            ll_listitem_pics.removeAllViews();
            if (convertView == null) {
                convertView = View.inflate(mactivity, R.layout.listitem_pay, null);

                iv_shoping = (ImageView) convertView.findViewById(R.id.iv_pay);
                tv_listitem_name = (TextView) convertView.findViewById(R.id.tv_pay_name);
                tv_listitem_price = (TextView) convertView.findViewById(R.id.tv_pay_price);
                tv_listitem_num = (TextView) convertView.findViewById(R.id.tv_pay_num);
                ll_listitem_pics = (LinearLayout) convertView.findViewById(R.id.ll_pay_pics);
            }
            //处理点击事件  判断如果外部选择，则


            ShoppingBean shoppingBean = list.get(position);
            BitmapUtils bit = new BitmapUtils(mactivity);
            bit.display(iv_shoping, shoppingBean.goodsimg);//商品图片
            tv_listitem_name.setText(shoppingBean.goods_name);//商品名称
            tv_listitem_price.setText("￥" + shoppingBean.shop_price);
            tv_listitem_num.setText(""+shoppingBean.numShow);
            String is_allow_credit = shoppingBean.is_allow_credit;
            String is_activity_goods = shoppingBean.is_activity_goods;
            //---------------------


            if (ll_listitem_pics.getChildCount() > 0) {
                ll_listitem_pics.removeAllViews();
            }
            if (is_activity_goods.equals("0")) {
                ImageView img1 = new ImageView(mactivity);
                params = new LinearLayout.LayoutParams(20, 20);

                params.leftMargin = 10;
                img1.setLayoutParams(params);
                img1.setBackgroundResource(R.drawable.coupons);
                ll_listitem_pics.addView(img1);
            }
            if (is_allow_credit.equals("True")) {
                ImageView img1 = new ImageView(mactivity);
                params = new LinearLayout.LayoutParams(20, 20);
                params.leftMargin = 10;
                img1.setLayoutParams(params);
                img1.setBackgroundResource(R.drawable.pledge);
                ll_listitem_pics.addView(img1);
            }

            return convertView;
        }
    }
}
