package com.hhzmy.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhzmy.base.BaseViewHolder;
import com.hhzmy.bean.ClassBean;
import com.hhzmy.bean.ClassBean2;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.view.ViewHolderLeft;
import com.hhzmy.view.ViewHolderOnlyText;

import java.util.List;

/**
 * Created by mis on 2016/11/16.
 */

public class RecyViewLeftAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<ClassBean2.RsBean> list;
    private Context context;

    public RecyViewLeftAdapter(Context context, List<ClassBean2.RsBean> list) {
        this.context = context;
        this.list = list;
    }
    public void notifyData(){
        notifyDataSetChanged();
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyview_classpage_left_item, parent, false);
        return new ViewHolderLeft(view, context);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        ((ViewHolderLeft) holder).initData(list.get(position), 0);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
