package com.hyl.mis.hyl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hyl.mis.hyl.Bean.BeanGoods;
import com.hyl.mis.hyl.R;
import com.lidroid.xutils.BitmapUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**

 */
public class PicFragment extends Fragment {


    private View view;
    private LinearLayout ll_add_iv;
    private List<BeanGoods> list_bgoods;
    private String tag="PicFragment";


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pic,container,false);
        ll_add_iv = (LinearLayout)view.findViewById(R.id.ll_add_iv_picfrag);
        initData();
        initEvent();

        return view;
    }

    private void initEvent() {
        ll_add_iv.removeAllViews();
        for (int i = 0; i < list_bgoods.size(); i++) {
            BeanGoods goods = list_bgoods.get(i);
            String url = goods.getUrl();
            Log.e(tag,"url:"+url);
            BitmapUtils bit=new BitmapUtils(getActivity());
            ImageView imgv=new ImageView(getActivity());

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Integer.valueOf(goods.getWidth()),Integer.valueOf(goods.getHeight()));

            imgv.setLayoutParams(params);
            bit.display(imgv,url);
            ll_add_iv.addView(imgv);
        }
    }

    private void initData() {
        Bundle bundle = getArguments();
        String goods_desc = bundle.getString("str");
        Log.e(tag,"goods_desc:"+goods_desc);
        list_bgoods = new ArrayList<BeanGoods>();
        try {
            JSONArray jsonArray = new JSONArray(goods_desc);
            Log.e(tag,"jsonArray.length():"+jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String url = jsonObject.getString("url");
                String width = jsonObject.getString("width");
                String height = jsonObject.getString("height");
                BeanGoods bgoods = new BeanGoods();
                bgoods.setHeight(height);
                bgoods.setWidth(width);
                bgoods.setUrl(url);
                list_bgoods.add(bgoods);
            }
            Log.e(tag,"list_bgoods.size()"+list_bgoods.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
