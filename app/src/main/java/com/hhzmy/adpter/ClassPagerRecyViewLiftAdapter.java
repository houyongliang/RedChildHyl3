package com.hhzmy.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mis on 2016/11/16.
 */

public class ClassPagerRecyViewLiftAdapter extends RecyclerView.Adapter<ClassPagerRecyViewLiftAdapter.MyAdapter> {
    private  Context context;
    private  Object object;

    public  ClassPagerRecyViewLiftAdapter(Context context, Object object){
        this.context=context;
        this.object=object;
    }
    @Override
    public ClassPagerRecyViewLiftAdapter.MyAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ClassPagerRecyViewLiftAdapter.MyAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyAdapter extends RecyclerView.ViewHolder {
        public MyAdapter(View itemView) {
            super(itemView);
        }
    }
}
