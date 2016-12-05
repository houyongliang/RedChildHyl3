package com.hhzmy.adpter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hhzmy.activity.SpecificActivity;
import com.hhzmy.bean.BeanHome;
import com.hhzmy.httputil.Url;
import com.hhzmy.mis.redchildhyl.R;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.media.CamcorderProfile.get;

/**
 * Created by mis on 2016/11/7.
 * 设置无限轮播适配器
 */

public class MyHomePagerAdapter extends PagerAdapter {
    private String key="URL";
    private List<BeanHome.DataBean.TagBean> list;
    private Context context;
    /**拼接字符串*/
    private String url="http://image1.suning.cn";
    /**设置标示*/
    private String TAG="MyHomePagerAdapter";

    public MyHomePagerAdapter(List<BeanHome.DataBean.TagBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if(list.size()>0){
            View view = View.inflate(context, R.layout.vp_home_item, null);//获取布局视图
            ImageView iv_vp_home = (ImageView) view.findViewById(R.id.iv_vp_home);
            int index = position % list.size();
            BitmapUtils bitmapUtils=new BitmapUtils(context);
            BeanHome.DataBean.TagBean tagBean = list.get(index);
            String uUrl= Url.urlImageHttpHead+tagBean.getPicUrl();
            Log.e(TAG, "instantiateItem: "+uUrl );
            bitmapUtils.display(iv_vp_home,uUrl);
            container.addView(view);
            final String linkUrl = tagBean.getLinkUrl();
            iv_vp_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, SpecificActivity.class);
                    intent.putExtra(key,linkUrl);
                    context.startActivity(intent);
                }
            });

            return view;
        }


        return null;
    }
}
