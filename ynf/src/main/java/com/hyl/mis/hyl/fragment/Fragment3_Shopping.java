package com.hyl.mis.hyl.fragment;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyl.mis.hyl.Bean.ShoppingBean;
import com.hyl.mis.hyl.PayActivity;
import com.hyl.mis.hyl.R;
import com.hyl.mis.hyl.database.MyDatabase;
import com.lidroid.xutils.BitmapUtils;
import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * shopping 购物车界面
 * lv_shopping
 */
public class Fragment3_Shopping extends Fragment implements View.OnClickListener {

    private Activity mactivity;
    private View view;
    private ListView lv_shopping;
    private MyDatabase mydb;
    private List<ShoppingBean> list;
    private CheckBox cbox_shopping;
    private ImageView iv_shoping;
    private TextView tv_listitem_name;
    private TextView tv_listitem_price;
    private TextView tv_listitem_num;
    private LinearLayout ll_listitem_pics;
    private LinearLayout.LayoutParams params;
    private String tag = "Fragment3_Shopping";
    private ShoppingAdapter adapter;
    private CheckBox cbox_all;
    private TextView tv_totalprice;
    private TextView btn_account;
    private ShoppingBean shoppingBean;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment3__shopping, container, false);
        mactivity = getActivity();//获取数据库对象
        mydb = new MyDatabase(mactivity);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();//初始化控件
        initData();//初始化数据
    }

    private void initData() {
        list = new ArrayList<ShoppingBean>();
        SQLiteDatabase db = mydb.getWritableDatabase();

        Cursor cursor = db.query("shopping", null, "state=?", new String[]{"1"}, null, null, null);
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
        adapter = new ShoppingAdapter(list);

        lv_shopping.setAdapter(adapter);
    }

    private void initView() {
        lv_shopping = (ListView) view.findViewById(R.id.lv_shopping);
        cbox_all = (CheckBox) view.findViewById(R.id.cbox_all_shopping);
        tv_totalprice = (TextView) view.findViewById(R.id.tv_totalprice_shopping);
        btn_account = (TextView) view.findViewById(R.id.btn_account_shopping);
        cbox_all.setOnClickListener(this);
        btn_account.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cbox_all_shopping://全选，要么全true，要么全false
                Log.i("--", "cbox_all---" + cbox_all.isChecked());
                boolean flag = cbox_all.isChecked();

                for (int i = 0; i < adapter.getList().size(); i++) {
                    adapter.getList().set(i, flag);
                }

                lv_shopping.setAdapter(adapter);
                //-----------------每次点击为0
                tv_totalprice.setText("￥" + adapter.getTotalprice());

                break;
            case R.id.btn_account_shopping:
                Intent intent = new Intent(getActivity(), PayActivity.class);
                //将数据库中状态数据该为2
                //判断listview状态如果为选中，则加入String 【】中
                getStrings();
                startActivity(intent);
                break;
            default:
                break;

        }

    }

    private void getStrings() {
        List<String> itemIds = adapter.getItemIds();
        mydb.updataTo2(itemIds);//跟新数据库
        adapter.notifyDataSetChanged();
    }


    class ShoppingAdapter extends BaseAdapter {//设置适配器

        List<ShoppingBean> list;
        private LinkedList<Boolean> linkedList = new LinkedList<Boolean>();
        private ShoppingBean shoppingBean;

        public ShoppingAdapter(List<ShoppingBean> list) {
            this.list = list;

            for (int i = 0; i < list.size(); i++) {
                linkedList.add(false);
            }
        }

        private List<Boolean> getList() {
            return linkedList;
        }

        private List<String> getItemIds() {
            List<String> list2 = new ArrayList<String>();
            for (int i = 0; i < linkedList.size(); i++) {
                if (linkedList.get(i)) {
                    list2.add(shoppingBean.id + "");
                }
            }

            return list2;
        }

        private double getTotalprice() {
            double totalprice = 0;

            for (int i = 0; i < linkedList.size(); i++) {
                ShoppingBean shoppingBean = list.get(i);
                if (linkedList.get(i)) {
                    totalprice += shoppingBean.shop_price * shoppingBean.numShow;
                }
            }
            return totalprice;
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
                convertView = View.inflate(mactivity, R.layout.listitem_shopping, null);
                cbox_shopping = (CheckBox) convertView.findViewById(R.id.cbox_shopping);
                iv_shoping = (ImageView) convertView.findViewById(R.id.iv_shoping);
                tv_listitem_name = (TextView) convertView.findViewById(R.id.tv_listitem_name);
                tv_listitem_price = (TextView) convertView.findViewById(R.id.tv_listitem_price);
                tv_listitem_num = (TextView) convertView.findViewById(R.id.tv_listitem_num);
                ll_listitem_pics = (LinearLayout) convertView.findViewById(R.id.ll_listitem_pics);
            }
            //处理点击事件  判断如果外部选择，则

            cbox_shopping.setChecked(linkedList.get(position));
            shoppingBean = list.get(position);

            BitmapUtils bit = new BitmapUtils(mactivity);
            bit.display(iv_shoping, shoppingBean.goodsimg);//商品图片
            tv_listitem_name.setText(shoppingBean.goods_name);//商品名称
            tv_listitem_price.setText("￥" + shoppingBean.shop_price);
            tv_listitem_num.setText("数量：" + shoppingBean.numShow);
            String is_allow_credit = shoppingBean.is_allow_credit;
            String is_activity_goods = shoppingBean.is_activity_goods;
            //---------------------
            cbox_shopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linkedList.set(position, !linkedList.get(position));//点击取反
                    //如果全选，则全选按钮选择
                    if (linkedList.contains(false)) {
                        cbox_all.setChecked(false);
                    } else {
                        cbox_all.setChecked(true);
                    }
                    notifyDataSetChanged();
                    tv_totalprice.setText("￥" + getTotalprice());

                }
            });


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
