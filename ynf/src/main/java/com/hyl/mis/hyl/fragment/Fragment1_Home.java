package com.hyl.mis.hyl.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hyl.mis.hyl.Bean.AD1;
import com.hyl.mis.hyl.Bean.AD3;
import com.hyl.mis.hyl.Bean.AD4;
import com.hyl.mis.hyl.Bean.AD5;
import com.hyl.mis.hyl.Bean.BeanHome;
import com.hyl.mis.hyl.Bean.Brief;
import com.hyl.mis.hyl.Bean.GroupBriefs;
import com.hyl.mis.hyl.DetailsPageActivity;
import com.hyl.mis.hyl.R;
import com.hyl.mis.hyl.Utils.ImageLoaderUtils;
import com.hyl.mis.hyl.Utils.MyHttp;
import com.hyl.mis.hyl.Utils.Utils;
import com.hyl.mis.hyl.WebviewActivity;
import com.hyl.mis.hyl.adapter.MyBaseAdapter;
import com.hyl.mis.hyl.adapter.MyF1HomePagerAdapter;
import com.hyl.mis.hyl.view.CustomGridView;
import com.hyl.mis.hyl.view.CustomListView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Drawable top = getResources().getDrawable(R.drawable.image);
 * button.setCompoundDrawablesWithIntrinsicBounds(null, top , null, null);
 */
public class Fragment1_Home extends Fragment {
    private String keyUrl = "URL";
    private String key = "jsonData";
    private String vp_url = "http://m.yunifang.com/yunifang/mobile/home?random=59676&encode=62d458fefce9c740359873cc19b05188";
    //--------------------------------------------
    private View view_home;
    private ViewPager f1_home_vp;
    private LinearLayout f1_home_ll;
    private List<ImageView> listDot;
    private LinearLayout f1_home_ad5_ll;
    private View v_hot;
//    private CustomListView clistView;
    private List<GroupBriefs> groupBriefs;
    private CustomGridView customGridView;
    private CustomListView cuslistView;
    //--------------------------------------------
    LinearLayout.LayoutParams params;
    private DisplayImageOptions options;
    private List<AD1> ad1;
    private List<AD5> ad5;
    private List<Brief> briefList;
    public String tag="Fragment1_Home";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case 0:
                    ImageView img = null;
                    BeanHome beanHome = (BeanHome) msg.obj;
                    ad1 = beanHome.data.ad1;
                    List<AD3> ad3 = beanHome.data.ad3;
                    List<AD4> ad4 = beanHome.data.ad4;
                    ad5 = beanHome.data.ad5;
                    groupBriefs = beanHome.data.groupBriefs;
                    for (int i = 0; i < groupBriefs.size(); i++) {
                        briefList = groupBriefs.get(i).briefList;
                    }
                    //设置监听事件
                    f1_home_vp.setAdapter(new MyF1HomePagerAdapter(getActivity(), ad1, handler));
                    f1_home_vp.setCurrentItem(ad1.size() * 10000);

                    intDots();
                    initEventF1();
                    initTextView();
//                    initViewHot();

                    break;
                case 1://无限轮播
                    int count = f1_home_vp.getCurrentItem();
                    f1_home_vp.setCurrentItem(++count);
                    handler.sendEmptyMessageDelayed(1, 2000);
                    break;

                default:
                    break;
            }
        }
    };



//    private void initViewHot() {
////        clistView = (CustomListView) v_hot.findViewById(R.id.weekHot_cusListView);
//
//    }


    private void initTextView() {

        View v = null;
        f1_home_ad5_ll.removeAllViews();
        for (int i = 0; i < ad5.size(); i++) {
            final int poss = i;
            v = View.inflate(getActivity(), R.layout.mytextview, null);
            ImageView mt_iv = (ImageView) v.findViewById(R.id.mt_iv);
            TextView mt_tv = (TextView) v.findViewById(R.id.mt_tv);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            options = ImageLoaderUtils.initOptions();
            mt_tv.setText(ad5.get(i).title);
            Log.e("Fragment1_Home", ad5.get(i).image);
            ImageLoader.getInstance().displayImage(ad5.get(i).image, mt_iv, options);
            v.setLayoutParams(params);
            v.setClickable(true);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), WebviewActivity.class);
                    String url = ad5.get(poss).ad_type_dynamic_data;
                    intent.putExtra(keyUrl, url);
                    getActivity().startActivity(intent);
                }
            });
            f1_home_ad5_ll.addView(v);

        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view_home = inflater.inflate(R.layout.fragment1__home, container, false);
        return view_home;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewF1();
        initDataF1();

    }

    private void initEventF1() {
        //vp的页面改变监听事件
        f1_home_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int index = position % ad1.size();
                for (int i = 0; i < ad1.size(); i++)
                    if (index == i) {
                        ImageView iv = listDot.get(i);
                        params = new LinearLayout.LayoutParams(20, 5);
                        params.setMargins(0, 2, 0, 0);
                        params.leftMargin = 10;
                        iv.setLayoutParams(params);
                        iv.setBackgroundResource(R.drawable.shape_dot_fouse);
                    } else {
                        ImageView iv = listDot.get(i);
                        params = new LinearLayout.LayoutParams(10, 10);
                        params.leftMargin = 10;
                        iv.setLayoutParams(params);
                        iv.setBackgroundResource(R.drawable.shape_dot_nomal);
                    }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    //设置条目点击事件
//        cuslistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
        cuslistView.setAdapter(new HomeAdapter(getActivity(),groupBriefs));
    }


    private void initDataF1() {

        //加载数据并设置缓存 ，将json 保存至sp中
        MyHttp.getData(HttpRequest.HttpMethod.GET, vp_url, null, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String json = responseInfo.result;
                Gson gson = new Gson();
                BeanHome beanHome = gson.fromJson(json, BeanHome.class);
                Log.e("Fragment1_Home", beanHome.toString());

                Message msg_beanHome = Message.obtain();
                msg_beanHome.obj = beanHome;
                msg_beanHome.what = 0;
                handler.sendMessage(msg_beanHome);
                Utils.putBufferJsonSP(getActivity(), key, json);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getActivity(), "网络以断开", Toast.LENGTH_SHORT).show();
                String json = Utils.getBufferJsonSP(getActivity(), key);
                Gson gson = new Gson();
                BeanHome beanHome = gson.fromJson(json, BeanHome.class);
                Log.e("Fragment1_Home", beanHome.toString());
                Message msg_beanHome = Message.obtain();
                msg_beanHome.obj = beanHome;
                msg_beanHome.what = 0;
                handler.sendMessage(msg_beanHome);
            }
        });

    }

    private void intDots() {
        listDot = new ArrayList<ImageView>();
        for (int i = 0; i < ad1.size(); i++) {
            ImageView iv = new ImageView(getActivity());

            if (i == 0) {
                params = new LinearLayout.LayoutParams(20, 5);
                params.setMargins(0, 2, 0, 0);
                params.leftMargin = 10;
                iv.setLayoutParams(params);

//                iv.setImageResource(R.drawable.dot_fouse);
                iv.setBackgroundResource(R.drawable.shape_dot_fouse);
            } else {
                params = new LinearLayout.LayoutParams(10, 10);
                params.leftMargin = 10;
                iv.setLayoutParams(params);
                iv.setBackgroundResource(R.drawable.shape_dot_nomal);
            }
            listDot.add(iv);
            f1_home_ll.addView(iv);
        }

    }

    private void initViewF1() {
        f1_home_vp = (ViewPager) view_home.findViewById(R.id.f1_home_sc_vp);
        f1_home_ll = (LinearLayout) view_home.findViewById(R.id.f1_home_ad4_ll);
        f1_home_ad5_ll = (LinearLayout) view_home.findViewById(R.id.f1_home_ad5_ll);
        //初始化自定义控件
        cuslistView = (CustomListView) view_home.findViewById(R.id.f1_home_cuslistView);
        customGridView = (CustomGridView) view_home.findViewById(R.id.f1_home2_cusGridView);
    }

    class HomeAdapter extends MyBaseAdapter<GroupBriefs> {
        private List<GroupBriefs> list;

        public HomeAdapter(Context context, List<GroupBriefs> list) {
            super(context, list);
            this.list = list;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.cus_listview_item, null);
            }
            ImageView cusView = (ImageView) convertView.findViewById(R.id.item_cus_view);
            LinearLayout ll = (LinearLayout) convertView.findViewById(R.id.item_ll);


            ImageLoader.getInstance().displayImage(list.get(position).image, cusView, options);
            List<Brief> briefList = list.get(position).briefList;
            ll.removeAllViews();

            for (int i = 0; i < briefList.size(); i++) {

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams
                        (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);


                View view = View.inflate(context, R.layout.item_main, null);
                final Brief brief = briefList.get(i);
                view.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(), DetailsPageActivity.class);

                        String id1 = brief.id;
                        intent.putExtra("id",id1);
                        startActivity(intent);
                        Log.e(tag,id1);
                        Toast.makeText(getActivity(),"条目点击事件"+id1,Toast.LENGTH_LONG).show();
                    }
                });
                if(i==briefList.size()-1){
                    ImageView iv_more = (ImageView) view.findViewById(R.id.item_main_iv);
                    View line2 = (View) view.findViewById(R.id.line2);
                    View line1 = (View) view.findViewById(R.id.line1);
                    line1.setVisibility(View.INVISIBLE);
                    line2.setVisibility(View.INVISIBLE);
                    iv_more.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    iv_more.setImageResource(R.drawable.home_rank_list_more);
                }else{
                    ImageView item_iv = (ImageView) view.findViewById(R.id.item_main_iv);
                    TextView main_tv_name = (TextView) view.findViewById(R.id.item_main_tv_name);
                    TextView main_tv_price = (TextView) view.findViewById(R.id.item_main_tv_price);
                    TextView main_tv_marketprice = (TextView) view.findViewById(R.id.item_main_tv_marketprice);
                    Brief brieg = briefList.get(i);
                    ImageLoader.getInstance().displayImage(brieg.goods_img, item_iv, options);
                    main_tv_name.setText(brieg.goods_name);
                    main_tv_price.setText("￥"+brieg.shop_price);
                    main_tv_marketprice.setText("￥"+brieg.market_price);
                    main_tv_marketprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );

                }

                lp.setMargins(10,0,0,0);
                view.setLayoutParams(lp);
                ll.addView(view);
            }

            return convertView;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.sendEmptyMessageDelayed(1, 2000);
    }
}
