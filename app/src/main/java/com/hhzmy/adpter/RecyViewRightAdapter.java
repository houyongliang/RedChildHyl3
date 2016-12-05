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
import com.hhzmy.view.ViewHolderOnlyText;
import com.hhzmy.view.ViewHolderRight;

import java.util.List;

/**
 * Created by mis on 2016/11/16.
 */

public class RecyViewRightAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private  List<ClassBean2.RsBean.ChildrenBean> list;
    private Context context;
    private final LayoutInflater inflater;

    public enum ITEM_TYPE {
        ITEM1,
        ITEM2
    }

    public RecyViewRightAdapter(Context context,  List<ClassBean2.RsBean.ChildrenBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==ITEM_TYPE.ITEM1.ordinal()){
           return new ViewHolderOnlyText( inflater.inflate(R.layout.recyview_item_only_text,parent,false),context);
        }else{
            return new ViewHolderRight(inflater.inflate(R.layout.recyview_classpage_right_item,parent,false),context);
        }
       }
    public void notifyData(){
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {


        if(holder instanceof ViewHolderOnlyText ){

            ((ViewHolderOnlyText) holder).initData(list.get(position), 0);
        }else if(holder instanceof ViewHolderRight){
            ((ViewHolderRight) holder).initData( list.get(position), 0);

        }


    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position).isHeader){
            return ITEM_TYPE.ITEM1.ordinal();
        }else{
            return ITEM_TYPE.ITEM2.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
