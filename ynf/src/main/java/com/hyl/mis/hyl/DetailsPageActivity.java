package com.hyl.mis.hyl;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hyl.mis.hyl.Bean.BeanDetailspage;
import com.hyl.mis.hyl.Utils.MyHttp;
import com.hyl.mis.hyl.Utils.Utils;
import com.hyl.mis.hyl.database.MyDatabase;
import com.hyl.mis.hyl.fragment.DetailsFragment;
import com.hyl.mis.hyl.fragment.PicFragment;
import com.hyl.mis.hyl.view.CustomListView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import static com.hyl.mis.hyl.R.id.tv_btnsure_shoping_pop;

/**
 * 内容详情页
 */

public class DetailsPageActivity extends FragmentActivity implements View.OnClickListener {
    private ImageView iv_2shoping_content, iv_back_content, iv_share_content;
    private TextView tv_addshop_content, tv_service_content, tv_shoping_content;
    private CustomListView lv_content;
    private FrameLayout fl_detailgoods_content;
    //------------------------先固定 url
    private String url ;
    private String key = "jsonDetalsData";
    private String tag = "DetailsPageActivity";
    private List<BeanDetailspage.DataBean.ActivityBean> activityBeen;
    private List<ImageView> listDot;
    private MyAdapter myAdapter;
    private View v_footer;
    private View v_header;
    private ViewPager vp_imgs_header;
    private TextView tv_collection_header;
    private TextView tv_price_header;
    private TextView tv_marketprice_header;
    private LinearLayout ll_pics_header;
    private TextView tv_freight_header;
    private TextView tv_goodsnum_header;
    private TextView tv_collecnum_header;

    private List<BeanDetailspage.DataBean.GoodsBean.GalleryBean> gallery;
    private LinearLayout ll_imgs_vp_header;
    private LinearLayout.LayoutParams params;
    private BeanDetailspage.DataBean.GoodsBean goods;
    private BeanDetailspage.DataBean dataBean;
    private LinearLayout.LayoutParams params1;
    private RadioButton rb_detail;
    private RadioButton rb_pics;
    private RadioButton rb_evaluate;
    private RadioGroup rg_frag;
    private FrameLayout fl_fg;
    private String goods_desc;

    private FragmentManager manager;
    private List<BeanDetailspage.DataBean.GoodsBean.AttributesBean> attributes;
    private PopupWindow pop;
    private int[] location;
    private Boolean isPopshow = true;
    private String goods_img;
    private String goods_name;
    private int collect_count;
    private int sales_volume;
    private double shop_price;
    private double market_price;
    private int shipping_fee;
    private int commentNumber;
    private int stock_number;
    private int restrict_purchase_num;
    private TextView tv_show_pop;
    private TextView tv_price_pop;
    private TextView tv_stocknum_pop;
    private TextView tv__rpn_pop;
    private ImageView iv_pop;
    private int numShow;
    private TextView tv_add_pop;
    private TextView tv_reduce_pop;
    private TextView tv_btnsure_pop;
    private MyDatabase mydb;
    private String is_activity_goods;
    private String is_allow_credit;
    private Cursor cursor;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        mydb = new MyDatabase(this);
        Intent intent=getIntent();
        String id = intent.getStringExtra("id");
        Toast.makeText(DetailsPageActivity.this,""+id,Toast.LENGTH_SHORT).show();
    if(TextUtils.isEmpty(id)){
        url ="http://m.yunifang.com/yunifang/mobile/goods/detail?random=6716&encode=b02382bd9e457e06e09b68a6a4f26eb4&id="+10;
    }else{
        url ="http://m.yunifang.com/yunifang/mobile/goods/detail?random=6716&encode=b02382bd9e457e06e09b68a6a4f26eb4&id="+id;
    }

        Log.e(tag,url);
        Toast.makeText(DetailsPageActivity.this,"URL"+url,Toast.LENGTH_SHORT).show();
        initView();//初始化控件
        initData();//缓存数据，加载网络数据
        initEvent();//初始化事件
    }

    private void initEvent() {
        lv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String description = activityBeen.get(position).getDescription();
                Intent intent = new Intent(DetailsPageActivity.this, WebviewActivity.class);
                intent.putExtra("URL", description);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        /**
         * 在页面创建的时候，先去拿到字符串，判断不为空的话去解析
         */
        String result = Utils.getBufferJsonSP(DetailsPageActivity.this, key);
        if (!TextUtils.isEmpty(result)) {
            progressData(result);
        }
        getData();//解析数据


    }

    private void getData() {
        //加载数据并设置缓存 ，将json 保存至sp中
        MyHttp.getData(HttpRequest.HttpMethod.GET, url, null, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Utils.putBufferJsonSP(DetailsPageActivity.this, key, responseInfo.result);
                progressData(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(DetailsPageActivity.this, "网络以断开", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void progressData(String result) {//获取listview 里面的数据后设置适配器
        Gson gson = new Gson();
        Log.e(tag, "result:" + result);
        BeanDetailspage beanDetailspage = gson.fromJson(result, BeanDetailspage.class);
        if (beanDetailspage.getCode() == 200) {
            dataBean = beanDetailspage.getData();
            activityBeen = dataBean.getActivity();//listview 展示数据
            Log.e(tag, activityBeen.toString());
            myAdapter = new MyAdapter();
            //lv 在设置适配器前，加载头视图
            //头部信息加载及处理
            goods = dataBean.getGoods();//获取商品相关信息
            gallery = goods.getGallery();//轮播图片处理
//-------------------------
            //商品限购数量
            restrict_purchase_num = goods.getRestrict_purchase_num();

//库存数量
            stock_number = goods.getStock_number();

            goods.getEfficacy();//功效
            //pop图片
            goods_img = goods.getGoods_img();
            goods.getRestriction();//是否预定
            goods.getIs_on_sale();//是否在售
            goods_desc = goods.getGoods_desc();
            attributes = goods.getAttributes();


            //------------------
            //获取商品名称
            goods_name = goods.getGoods_name();

            //获取收藏数量
            collect_count = goods.getCollect_count();
            //销量
            sales_volume = goods.getSales_volume();
            //售价
            shop_price = goods.getShop_price();
            //市场价格
            market_price = goods.getMarket_price();
            //运费
            shipping_fee = goods.getShipping_fee();
            //获取评论数量
            commentNumber = dataBean.getCommentNumber();
//---------------------------
            dataHeader();

            initDataFooter();

            intDots();
            lv_content.setAdapter(myAdapter);

        }


    }

    private void initDataFooter() {//处理listview下面数据

        manager = getSupportFragmentManager();//获取管理对象

        rg_frag.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.rb_pro_pic_frag_footer://向 picfragment 传递参数
                        PicFragment picfg = new PicFragment();
                        Bundle bundle = new Bundle();
                        Log.e(tag, "goods_desc:" + goods_desc);
                        bundle.putString("str", goods_desc);
                        picfg.setArguments(bundle);
                        //如果transaction  commit（）过  那么我们要重新得到transaction
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.fl_fg_footer, picfg);
                        transaction.commit();
                        break;
                    case R.id.rb_pro_detail_frag_footer:
                        DetailsFragment detailsfg = new DetailsFragment();
                        Bundle bundle1 = new Bundle();
//                        bundle1.putParcelableArrayList("str", (ArrayList<? extends Parcelable>) attributes);

//                        detailsfg.setArguments(bundle1);
                        //如果transaction  commit（）过  那么我们要重新得到transaction
                        FragmentTransaction transaction1 = manager.beginTransaction();
                        transaction1.replace(R.id.fl_fg_footer, detailsfg);
                        transaction1.commit();

                        break;
                    case R.id.rb_pro_evaluate_frag_footer:
//                        CommentFragment commfg = new CommentFragment();
                        break;

                }
            }
        });
        rg_frag.check(R.id.rb_pro_pic_frag_footer);//默认选中图片页面


    }

    private void dataHeader() {

        //是否是活动商品
        is_activity_goods = goods.getIs_activity_goods();
        //是否有优惠
        is_allow_credit = goods.getIs_allow_credit();
        //---------------------控件赋值
        tv_price_header.setText("￥" + shop_price);
        tv_marketprice_header.setText("￥" + market_price);
        tv_marketprice_header.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        if (shipping_fee == 0) {
            tv_freight_header.setText("包邮");
        } else {
            tv_freight_header.setText("￥" + shipping_fee);
        }

        tv_goodsnum_header.setText(numConversion(sales_volume));
        tv_collecnum_header.setText(numConversion(collect_count));
        //------添加图片
        ll_pics_header.removeAllViews();
        if (is_activity_goods.equals("0")) {
            ImageView img1 = new ImageView(this);
            params1 = new LinearLayout.LayoutParams(20, 20);

            params1.leftMargin = 10;
            img1.setLayoutParams(params1);
            img1.setBackgroundResource(R.drawable.coupons);
            ll_pics_header.addView(img1);
        }
        if (is_allow_credit.equals("True")) {
            ImageView img1 = new ImageView(this);
            params1 = new LinearLayout.LayoutParams(20, 20);
            params1.leftMargin = 10;
            img1.setLayoutParams(params1);
            img1.setBackgroundResource(R.drawable.pledge);
            ll_pics_header.addView(img1);
        }

//-------------------------------
        vp_imgs_header.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return gallery.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView img = new ImageView(DetailsPageActivity.this);
                img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                img.setScaleType(ImageView.ScaleType.FIT_XY);
                BitmapUtils bitmaps = new BitmapUtils(DetailsPageActivity.this);
                bitmaps.display(img, gallery.get(position).getNormal_url());
                container.addView(img);
                return img;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == (View) object;
            }
        });
        vp_imgs_header.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < listDot.size(); i++) {
                    ImageView iv = listDot.get(i);
                    params = new LinearLayout.LayoutParams(10, 10);
                    params.leftMargin = 10;

                    if (position == i) {
                        iv.setBackgroundResource(R.drawable.shape_dot_fouse_ring_red);
                    } else {
                        iv.setBackgroundResource(R.drawable.shape_dot_nomal);
                    }
                    iv.setLayoutParams(params);
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private String numConversion(int sales_volume) {
        DecimalFormat df = new DecimalFormat("0.00");
        double i = (double) sales_volume / 10000;
        if (i >= 1) {
            return df.format(i) + "万";
        }
        return sales_volume + "";
    }

    private void intDots() {
        ll_imgs_vp_header.removeAllViews();
        ;
        listDot = new ArrayList<ImageView>();
        for (int i = 0; i < gallery.size(); i++) {
            ImageView iv = new ImageView(this);

            LinearLayout.LayoutParams params;
            params = new LinearLayout.LayoutParams(10, 10);
            params.leftMargin = 10;
            iv.setLayoutParams(params);
            if (i == 0) {
                iv.setBackgroundResource(R.drawable.shape_dot_fouse_ring_red);
            } else {

                iv.setBackgroundResource(R.drawable.shape_dot_nomal);
            }
            listDot.add(iv);
            ll_imgs_vp_header.addView(iv);
        }

    }


    private void initView() {//初始化数据
        iv_2shoping_content = (ImageView) findViewById(R.id.iv_2shoping_content);
        iv_back_content = (ImageView) findViewById(R.id.iv_back_content);
        iv_share_content = (ImageView) findViewById(R.id.iv_share_content);
        tv_addshop_content = (TextView) findViewById(R.id.tv_addshop_content);
        tv_service_content = (TextView) findViewById(R.id.tv_service_content);
        tv_shoping_content = (TextView) findViewById(R.id.tv_shoping_content);
        lv_content = (CustomListView) findViewById(R.id.lv_content);
        initHeader();
        initFooter();
        tv_addshop_content.setOnClickListener(this);
//------------------------------------pop
        initPop();


    }

    private void initPop() {
        numShow = 1;
        View view = View.inflate(DetailsPageActivity.this, R.layout.pop_shopping, null);
        pop = new PopupWindow(view, (ViewGroup.LayoutParams.MATCH_PARENT), 350);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(true);
        location = new int[2];
        tv_addshop_content.getLocationOnScreen(location);
        iv_pop = (ImageView) view.findViewById(R.id.iv_shoping_pop);
        tv_add_pop = (TextView) view.findViewById(R.id.tv_add_shoping_pop);
        tv_reduce_pop = (TextView) view.findViewById(R.id.tv_reduce_shoping_pop);
        tv_btnsure_pop = (TextView) view.findViewById(tv_btnsure_shoping_pop);
        tv_show_pop = (TextView) view.findViewById(R.id.tv_show_shoping_pop);
        tv_price_pop = (TextView) view.findViewById(R.id.tv_price_shoping_pop);

        tv_stocknum_pop = (TextView) view.findViewById(R.id.tv_stocknum_shoping_pop);
        tv__rpn_pop = (TextView) view.findViewById(R.id.tv_restrict_purchase_num_shoping_pop);


        //-------------------点击事件
        tv_add_pop.setOnClickListener(this);
        tv_reduce_pop.setOnClickListener(this);
        tv_btnsure_pop.setOnClickListener(this);
        iv_share_content.setOnClickListener(this);

    }

    private void initFooter() {
        v_footer = findViewById(R.id.lv_footer);
        rb_detail = (RadioButton) v_footer.findViewById(R.id.rb_pro_detail_frag_footer);
        rb_pics = (RadioButton) v_footer.findViewById(R.id.rb_pro_pic_frag_footer);
        rb_evaluate = (RadioButton) v_footer.findViewById(R.id.rb_pro_evaluate_frag_footer);
        rg_frag = (RadioGroup) v_footer.findViewById(R.id.rg_frag_footer);
        fl_fg = (FrameLayout) v_footer.findViewById(R.id.fl_fg_footer);


    }

    private void initHeader() {//初始化头部数据
        v_header = findViewById(R.id.lv_header);
        vp_imgs_header = (ViewPager) v_header.findViewById(R.id.vp_imgs_picplay_detailspager);
        tv_collection_header = (TextView) v_header.findViewById(R.id.tv_collection_picplay_detailspager);
        tv_price_header = (TextView) v_header.findViewById(R.id.tv_price_picplay_detailspager);
        tv_marketprice_header = (TextView) v_header.findViewById(R.id.tv_marketprice_picplay_detailspager);
        ll_pics_header = (LinearLayout) v_header.findViewById(R.id.ll_pics_picplay_detailspager);
        ll_imgs_vp_header = (LinearLayout) v_header.findViewById(R.id.ll_imgs_vp);
        tv_freight_header = (TextView) v_header.findViewById(R.id.tv_freight_picplay_detailspager);
        tv_goodsnum_header = (TextView) v_header.findViewById(R.id.tv_goodsnum_picplay_detailspager);
        tv_collecnum_header = (TextView) v_header.findViewById(R.id.tv_collecnum_picplay_detailspager);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_addshop_content://加入购物车
//  Toast.makeText(DetailsPageActivity.this,"弹出POP", Toast.LENGTH_SHORT).show();

//                pop.showAtLocation( tv_addshop_content,Gravity.NO_GRAVITY, location[0], location[1]-pop.getHeight());
                if (pop != null) {
                    if (isPopshow) {
                        pop.showAsDropDown(tv_addshop_content);
                        BitmapUtils bits = new BitmapUtils(this);
                        bits.display(iv_pop, goods_img);//pop图片
                        tv_price_pop.setText("￥" + shop_price);//价格
                        tv_stocknum_pop.setText("库存" + stock_number + "件");//库存
                        tv__rpn_pop.setText("限购" + restrict_purchase_num + "件");//限购
                        tv_btnsure_pop.setText("确定");
                        isPopshow = !isPopshow;
                    } else {
                        pop.dismiss();
                        isPopshow = !isPopshow;
                    }
                }
                break;


            case R.id.tv_shoping_content://立即购买

                break;
            case R.id.tv_add_shoping_pop://pop增
                int num = Integer.valueOf(tv_show_pop.getText().toString().trim());
                if (num < restrict_purchase_num && num >= 0) {
                    tv_add_pop.setClickable(true);
                    numShow++;
                    tv_show_pop.setText(numShow + "");
                } else {
                    tv_add_pop.setSelected(false);
                }

                break;
            case R.id.tv_reduce_shoping_pop://pop减少
                int num1 = Integer.valueOf(tv_show_pop.getText().toString().trim());
                if (num1 <= restrict_purchase_num && num1 >= 1) {
                    tv_reduce_pop.setClickable(true);
                    numShow--;
                    if (num1 == 1) {
                        numShow = 1;
                    }

                    tv_show_pop.setText(numShow + "");
                } else {
                    tv_reduce_pop.setSelected(false);
                }


                break;
            case tv_btnsure_shoping_pop://pop确定

                SQLiteDatabase db = mydb.getWritableDatabase();
                Integer numShow2=0;
               //查出数据
                Cursor cursor = db.query("shopping", null, "goods_name=?", new String[]{goods_name}, null, null, null);

                if(cursor.getCount()>0){
                    while(cursor.moveToNext()){
                        numShow2= cursor.getInt(cursor.getColumnIndex("numShow"));

                    }
                    ContentValues values=new ContentValues();
                    if((numShow+numShow2)>restrict_purchase_num){
                        numShow=restrict_purchase_num;
                    }else{
                        numShow+=numShow2;
                    }
                    values.put("numShow",numShow);
                    values.put("state", "1");
                    Toast.makeText(DetailsPageActivity.this, "numShow"+numShow, Toast.LENGTH_SHORT).show();
                   db.update("shopping",values,"goods_name=?",new String[]{goods_name});
                    db.close();
                }else{
                    ContentValues values=new ContentValues();
                    values.put("goods_name",goods_name);
                    values.put("shop_price",shop_price);
                    values.put("numShow",numShow);
                    values.put("goods_img",goods_img);
                    values.put("is_activity_goods",is_activity_goods);
                    values.put("is_allow_credit",is_allow_credit);
                    values.put("url",url);
                    values.put("state",1);
                    db.insert("shopping",null,values);
                    Toast.makeText(DetailsPageActivity.this, "numShow"+numShow, Toast.LENGTH_SHORT).show();
                    db.close();
                }


                break;

            case R.id.iv_share_content://分享
                Log.e(tag,"nihaoa fenxiang");
                Toast.makeText(this,"分享来了",Toast.LENGTH_SHORT).show();
                showShare();
                break;
            default:
                break;

        }
    }
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("我来了");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是侯永亮");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.baidu.com");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return activityBeen.size();
        }

        @Override
        public BeanDetailspage.DataBean.ActivityBean getItem(int position) {
            return activityBeen.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(DetailsPageActivity.this, R.layout.listitem_activity_detailspage, null);

            }
            TextView tv_showactivity = (TextView) convertView.findViewById(R.id.tv_showactivity);
            tv_showactivity.setText(getItem(position).getTitle());

            return convertView;
        }
    }
}
