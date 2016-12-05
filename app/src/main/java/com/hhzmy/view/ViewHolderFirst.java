package com.hhzmy.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.hhzmy.adpter.MyRecyclerViewAdapter;
import com.hhzmy.base.BaseViewHolder;
import com.hhzmy.bean.BeanHome;
import com.hhzmy.mis.redchildhyl.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mis on 2016/11/15.
 */

public class ViewHolderFirst extends BaseViewHolder {
    List<BeanHome.DataBean.TagBean> list;
    RecyclerView recyview_first;
    private MyRecyclerViewAdapter adapter;

    public ViewHolderFirst(View itemView, Context context) {
        super(itemView, context);
        recyview_first = (RecyclerView) itemView.findViewById(R.id.recyview_first);

    }

    @Override
    public void initData(Object data,int index) {
        super.initData(data,index);
        list= (List<BeanHome.DataBean.TagBean>) data;

        if (adapter == null) {
            adapter = new MyRecyclerViewAdapter(context,  list);
            GridLayoutManager glm = new GridLayoutManager(context, 4);
            glm.setOrientation(OrientationHelper.VERTICAL);
            recyview_first.setLayoutManager(glm);
            recyview_first.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

    }
}
