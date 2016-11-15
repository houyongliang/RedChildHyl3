package com.hyl.mis.hyl.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyl.mis.hyl.R;
import com.hyl.mis.hyl.Utils.ImageLoaderUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by mis on 2016/10/12.
 */
public class MainAdapter extends MyBaseAdapter {

    List list;
    private final DisplayImageOptions options;

    public MainAdapter(Context context, List list) {
        super(context, list);
        this.list = list;
        options = ImageLoaderUtils.initOptions();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.item_main, null);
        ImageView iv = (ImageView) v.findViewById(R.id.item_main_iv);
        TextView tv_name = (TextView) v.findViewById(R.id.item_main_tv_name);
        TextView tv_price = (TextView) v.findViewById(R.id.item_main_tv_price);
        TextView tv_markerprice = (TextView) v.findViewById(R.id.item_main_tv_marketprice);
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                iv.setImageResource(R.drawable.home_rank_list_more);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            } else {
//                ImageLoader.getInstance().displayImage(list.get());
            }
        }
        return convertView;
    }
}
