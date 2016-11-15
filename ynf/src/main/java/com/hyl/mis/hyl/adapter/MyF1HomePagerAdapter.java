package com.hyl.mis.hyl.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hyl.mis.hyl.Bean.AD1;
import com.hyl.mis.hyl.WebviewActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import static com.hyl.mis.hyl.Utils.ImageLoaderUtils.initOptions;

/**
 * Created by mis on 2016/9/29.
 */
public class MyF1HomePagerAdapter extends PagerAdapter {
    private final Activity activity;
    private String key = "URL";
    private final Handler handler;
    private DisplayImageOptions options = initOptions();
    //    private Context context;
    private List<AD1> ad1;


    public MyF1HomePagerAdapter(Activity activity, List<AD1> ad1, Handler handler) {
        this.ad1 = ad1;
//        this.context = context;
        this.handler = handler;
        this.activity = activity;
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
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView img = new ImageView(activity);
        final int index = position % ad1.size();
        ImageLoader.getInstance().displayImage(ad1.get(index).image, img, options);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
//        img.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        handler.removeCallbacksAndMessages(null);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        handler.sendEmptyMessageDelayed(1, 2000);
//                        break;
//                    case MotionEvent.ACTION_CANCEL:
//                        handler.sendEmptyMessageDelayed(1, 2000);
//                        break;
//
//                    default:
//                        break;
//                }
//                return true;
//            }
//        });
        //设置条目点击事件
        img.setClickable(true);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, WebviewActivity.class);
                String url="";
                for (int i = 0; i < ad1.size(); i++) {
                    if(i==index){
                        url=ad1.get(i).ad_type_dynamic_data;
                    }
                }

                intent.putExtra(key, url);
                activity.startActivity(intent);
                Log.e("MyF1HomePagerAdapter","oooooooooooo+");
            }
        });
        container.addView(img);
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);

    }
}
