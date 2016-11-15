package com.hyl.mis.hyl.fragment;


import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hyl.mis.hyl.Bean.BeanClassfiy;
import com.hyl.mis.hyl.Bean.Brief;
import com.hyl.mis.hyl.R;
import com.hyl.mis.hyl.Utils.ImageLoaderUtils;
import com.hyl.mis.hyl.Utils.MyHttp;
import com.hyl.mis.hyl.Utils.Utils;
import com.hyl.mis.hyl.adapter.MeBaseAdapter;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2_Classify extends Fragment {
    String tag = "Fragment2_Classify";

    private View view;
    //--------------------------
    private String key = "jsonClassfiyData";
    private String url = "http://m.yunifang.com/yunifang/mobile/category/list?random=9503&encode=e181334d0cd93bf2ec95f3c9f85d1dde";
    private BeanClassfiy.DataBean data;
    private GridView gv_shop;
    private GridView gv_hot;
    private GridView gv_hotgoods;
    

    int[] pics = new int[]{R.drawable.skin, R.drawable.efficacy, R.drawable.eshuxing};
    private DisplayImageOptions options=ImageLoaderUtils.initOptions();

    public Fragment2_Classify() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment2__classify, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();//初始化控件
        initData();//初始化数据

    }

    private void initData() {
        /**
         * 在页面创建的时候，先去拿到字符串，判断不为空的话去解析
         */
        String result = Utils.getBufferJsonSP(getActivity(), key);
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
                Utils.putBufferJsonSP(getActivity(), key, responseInfo.result);
                progressData(responseInfo.result);

            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getActivity(), "网络以断开", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void progressData(String result) {
        Gson gson = new Gson();
        Log.e(tag, "result:" + result);
        Log.e(tag, "beanClassfiy" + new BeanClassfiy().toString());
        BeanClassfiy beanClassfiy = gson.fromJson(result, BeanClassfiy.class);
        Log.e(tag, "beanClassfiy2" + beanClassfiy.toString());

        if (beanClassfiy.getCode() == 200) {
            data = beanClassfiy.getData();
            //设置图片
            gv_shop.setAdapter(new MeBaseAdapter() {
                @Override
                public int getCount() {
                    return 3;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = View.inflate(getActivity(), R.layout.item_gv_item_justpic_classfiy, null);
                    }
                    ImageView iv = (ImageView) convertView.findViewById(R.id.iv_item_gvitem_justpic);
                    iv.setImageResource(pics[position]);
                    return convertView;
                }
            });
            //设置title
            final List<BeanClassfiy.DataBean.CategoryBean.ChildrenBean> listChild=new ArrayList<BeanClassfiy.DataBean.CategoryBean.ChildrenBean>();
            listChild.clear();
            List<BeanClassfiy.DataBean.CategoryBean> category = data.getCategory();
            for (BeanClassfiy.DataBean.CategoryBean categoryBean : category) {
                List<BeanClassfiy.DataBean.CategoryBean.ChildrenBean> children = categoryBean.getChildren();
                for (BeanClassfiy.DataBean.CategoryBean.ChildrenBean child : children) {
                    listChild.add(child);
                }
            }

            gv_hot.setAdapter(new MeBaseAdapter(){
                @Override
                public int getCount() {
                    return 6;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {

                    convertView = View.inflate(getActivity(), R.layout.item_gv_item_justtext_classfiy, null);

                    TextView tv = (TextView) convertView.findViewById(R.id.tv_item_gvitem_justtext);
                    tv.setText(listChild.get(position).getCat_name());
                    return convertView;
                }
            });

            //设置混合模式
            final List<BeanClassfiy.DataBean.GoodsBriefBean> goodsBrief = data.getGoodsBrief();
            if(goodsBrief!=null&&goodsBrief.size()>0){

                gv_hotgoods.setAdapter(new MeBaseAdapter(){
                    @Override
                    public int getCount() {
                        return goodsBrief.size();
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        if(convertView==null){
                            convertView = View.inflate(getActivity(), R.layout.item_main, null);
                        }


                        ImageView item_iv = (ImageView) convertView.findViewById(R.id.item_main_iv);
                        TextView main_tv_name = (TextView) convertView.findViewById(R.id.item_main_tv_name);
                        TextView main_tv_price = (TextView) convertView.findViewById(R.id.item_main_tv_price);
                        TextView main_tv_marketprice = (TextView) convertView.findViewById(R.id.item_main_tv_marketprice);
                        BeanClassfiy.DataBean.GoodsBriefBean goodsBriefBean = goodsBrief.get(position);
                        ImageLoader.getInstance().displayImage(goodsBriefBean.getGoods_img(), item_iv, options);
                        main_tv_name.setText(goodsBriefBean.getGoods_name());
                        main_tv_price.setText("￥"+goodsBriefBean.getShop_price());
                        main_tv_marketprice.setText("￥"+goodsBriefBean.getMarket_price());
                        main_tv_marketprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );



                        return convertView;
                    }
                });
            }



        }


    }


    private void initViews() {
        gv_shop = (GridView) view.findViewById(R.id.gv_shop_justpic_classfiy);

        gv_hot = (GridView) view.findViewById(R.id.gv_hot_justtext_classfiy);
        gv_hotgoods = (GridView) view.findViewById(R.id.gv_hotgoods_mixture_classfiy);
        gv_shop.setFocusable(false);
        gv_hot.setFocusable(false);
        gv_hotgoods.setFocusable(false);
    }




}
