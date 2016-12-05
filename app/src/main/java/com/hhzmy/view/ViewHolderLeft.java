package com.hhzmy.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhzmy.base.BaseViewHolder;
import com.hhzmy.bean.ClassBean2;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.tool.Tool;

/**
 * Created by mis on 2016/11/15.
 */

public class ViewHolderLeft extends BaseViewHolder {


    private TextView tv_item;


    public ViewHolderLeft(View itemView, Context context) {
        super(itemView, context);
        tv_item = (TextView) itemView.findViewById(R.id.tv_classpage_left);
    }

    @Override
    public void initData(Object data, int index) {
        ClassBean2.RsBean childrenBean = (ClassBean2.RsBean) data;
        tv_item.setText(childrenBean.getDirName());

    }
}
