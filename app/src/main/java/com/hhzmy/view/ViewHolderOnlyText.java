package com.hhzmy.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhzmy.base.BaseViewHolder;
import com.hhzmy.bean.BeanHome;
import com.hhzmy.bean.ClassBean2;
import com.hhzmy.httputil.Url;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.tool.Tool;

import java.util.ArrayList;
import java.util.List;

import static com.hhzmy.mis.redchildhyl.R.id.ll_recyclerview_three;

/**
 * Created by mis on 2016/11/15.
 */

public class ViewHolderOnlyText extends BaseViewHolder {


    private TextView tv_item;

    public ViewHolderOnlyText(View itemView, Context context) {
        super(itemView, context);
        tv_item = (TextView) itemView.findViewById(R.id.tv_item_only_text);
    }

    @Override
    public void initData(Object data, int index) {
        ClassBean2.RsBean.ChildrenBean childrenBean = (ClassBean2.RsBean.ChildrenBean) data;
        tv_item.setText(childrenBean.getDirName());
    }
}
