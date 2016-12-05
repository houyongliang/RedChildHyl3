package com.hhzmy.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.hhzmy.adpter.RecyViewFourthItemAdapter;
import com.hhzmy.base.BaseViewHolder;
import com.hhzmy.bean.BeanHome;
import com.hhzmy.httputil.Url;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.tool.Tool;
import com.hhzmy.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mis on 2016/11/15.
 * 思路：
 * 通过获取BeanHome.DataBean 对象，通过其 内部 tag 个数来判断，如果对象 有一个 tag ,则展示图片
 * 如果为 多个图片 在讲数据继续传递 ，到 rv
 */

public class ViewHolderFourthItem extends BaseViewHolder {
    RecyclerView ryview_fourth;
    ImageView iv_rv_fourth;

    public ViewHolderFourthItem(View itemView, Context context) {
        super(itemView, context);
        ryview_fourth = (RecyclerView) itemView.findViewById(R.id.ryview_fourth);

        iv_rv_fourth = (ImageView) itemView.findViewById(R.id.iv_fourth_item);
        ryview_fourth.setVisibility(View.GONE);
        iv_rv_fourth.setVisibility(View.VISIBLE);

    }

    @Override
    public void initData(Object data, int index) {
        BeanHome.DataBean.TagBean bean = (BeanHome.DataBean.TagBean) data;

        /*展示图片*/

        if (!TextUtils.isEmpty(bean.getPicUrl())) {//判断图片不为空

            DisplayImageOptions options = ImageLoaderUtil.myInitOptions();
            ImageLoader.getInstance().displayImage(Url.urlImageHttpHead + bean.getPicUrl(),iv_rv_fourth,options);
        }
            /*展示*/
    }

}



