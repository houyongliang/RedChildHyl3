package com.hyl.mis.hyl.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyl.mis.hyl.R;

import java.util.List;

/**
 * Created by mis on 2016/10/12.
 */
public abstract  class MyBaseAdapter<T> extends BaseAdapter{
    public Context context;
    public  List<T> list;

    public MyBaseAdapter(Context context, List<T> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


  public abstract View getView(int position, View convertView, ViewGroup parent) ;
}
