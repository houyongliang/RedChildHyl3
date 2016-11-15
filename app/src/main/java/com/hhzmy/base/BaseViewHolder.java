package com.hhzmy.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mis on 2016/11/15.
 */

public class BaseViewHolder  extends RecyclerView.ViewHolder{
    protected Context context;
    public BaseViewHolder(View itemView,Context context) {
        super(itemView);
        this.context=context;
    }
    public void initData(Object data){

    }
}
