package com.hhzmy.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhzmy.base.BaseViewHolder;
import com.hhzmy.bean.ClassBean;
import com.hhzmy.bean.ClassBean2;
import com.hhzmy.httputil.Url;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.tool.Tool;
import com.hhzmy.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by mis on 2016/11/15.
 */

public class ViewHolderRight extends BaseViewHolder {


    private TextView tv_item;
    private ImageView iv_item;

    public ViewHolderRight(View itemView, Context context) {
        super(itemView, context);
        iv_item = (ImageView) itemView.findViewById(R.id.iv_classpage_right);
        tv_item = (TextView) itemView.findViewById(R.id.tv_classpage_right);
    }

    @Override
    public void initData(Object data, int index) {
        ClassBean2.RsBean.ChildrenBean childrenBean = (ClassBean2.RsBean.ChildrenBean) data;
        tv_item.setText(childrenBean.getDirName());
//        LinearLayout.LayoutParams linearParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,height);//取控件rg_mian当前的布局参数
        ViewGroup.LayoutParams params = iv_item.getLayoutParams();

        params.width=Tool.getScreenWidth(context)*3/16;
        params.height= params.width;
        iv_item.setLayoutParams(params);
        iv_item.setScaleType(ImageView.ScaleType.CENTER);
        DisplayImageOptions options = ImageLoaderUtil.myInitOptions();
        ImageLoader.getInstance().displayImage(childrenBean.getImgApp(),iv_item,options);

    }
}
