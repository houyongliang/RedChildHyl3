package com.hyl.mis.hyl.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hyl.mis.hyl.R;
import com.hyl.mis.hyl.Utils.Utils;
import com.hyl.mis.hyl.MainActivity;

/**
 * Created by mis on 2016/9/29.
 */
public class MyPagerAdapter extends PagerAdapter {
    private String key="IsFirst";
    private Activity activity;
    private int[] pics;
    private View view;

    public MyPagerAdapter( Activity activity, int[] pics) {
        this.pics = pics;
        this.activity=activity;
    }

    ;

    @Override
    public int getCount() {
        return pics.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        view = View.inflate(activity, R.layout.first_lead_vp_item,null);
       ImageView iv= (ImageView) view.findViewById(R.id.first_lead_vp_iv);
        if(position==pics.length-1){
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                    Utils.putFirstSP(activity,key,true);
                    activity.finish();

                }
            });
        }
        iv.setImageResource(pics[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);

    }
}
