package com.hhzmy.adpter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hhzmy.activity.MainActivity;
import com.hhzmy.bean.ShopBean;
import com.hhzmy.mis.redchildhyl.R;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mis on 2016/11/20.
 */

public class ShopRyViewAdapter extends RecyclerView.Adapter<ShopRyViewAdapter.ItemViewHolder> {


    private  Context context;
    private CheckBox cb;
    private List<ShopBean> list;
    private LayoutInflater inflater;
    private LinkedList<Boolean> listChecked;

    public ShopRyViewAdapter(Context context, List<ShopBean> list, CheckBox  cb) {
        this.context = context;
        this.list = list;
        this.cb=cb;
        listChecked = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            listChecked.add(false);
        }
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.ryview_item_shop, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        holder.shopGoodname.setText(list.get(position).goodName);
        holder.cboxChecked.setChecked(listChecked.get(position));
        holder.cboxChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listChecked.set(position,isChecked);
                    }
        });
        if(!listChecked.contains(false)){
            Toast.makeText(context, "已经全选了", Toast.LENGTH_SHORT).show();
            cb.setChecked(true);
//            notifyDataSetChanged();
        }
    }


    public void checked(Boolean isChecked) {
        for (int i = 0; i < listChecked.size(); i++) {
           listChecked.set(i,isChecked);

        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cbox_checked)
        CheckBox cboxChecked;
        @BindView(R.id.shop_goodimg)
        ImageView shopGoodimg;
        @BindView(R.id.shop_goodname)
        TextView shopGoodname;
        @BindView(R.id.shop_price)
        TextView shopPrice;
        @BindView(R.id.shop_count)
        TextView shopCount;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
