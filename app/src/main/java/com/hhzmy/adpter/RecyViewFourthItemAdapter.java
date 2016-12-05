package com.hhzmy.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hhzmy.base.BaseViewHolder;
import com.hhzmy.bean.BeanHome;
import com.hhzmy.bean.ClassBean2;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.view.ViewHolderFourth;
import com.hhzmy.view.ViewHolderFourthItem;
import com.hhzmy.view.ViewHolderOnlyText;
import com.hhzmy.view.ViewHolderRight;

import java.util.List;

/**
 * Created by mis on 2016/11/16.
 */

public class RecyViewFourthItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<BeanHome.DataBean.TagBean> list;
    private Context context;
    private final LayoutInflater inflater;


    public RecyViewFourthItemAdapter(Context context, List<BeanHome.DataBean.TagBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new ViewHolderFourthItem (inflater.inflate(R.layout.recyclerview_item_linelayout_fourth, parent, false), context);

    }



    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {


        if (holder instanceof ViewHolderFourthItem) {
            ((ViewHolderFourthItem ) holder).initData(list.get(position), 0);
        }


    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}
