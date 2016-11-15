package com.hhzmy.adpter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by mis on 2016/11/7.
 */

public class MyMainPagerAdapter extends PagerAdapter {
    private List<ImageView> listPage;

    public MyMainPagerAdapter(List<ImageView> listPage) {
        this.listPage = listPage;
    }

    @Override
    public int getCount() {
        return listPage.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = listPage.get(position);
        container.addView(iv);
        return iv;
    }
}
