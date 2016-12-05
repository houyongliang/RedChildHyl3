package com.hhzmy.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.hhzmy.activity.ContentActivity;
import com.hhzmy.adpter.MyRecyclerViewAdapterSecond;
import com.hhzmy.adpter.RecyViewFourthItemAdapter;
import com.hhzmy.base.BaseViewHolder;
import com.hhzmy.base.RecyclerViewClickListener;
import com.hhzmy.bean.BeanHome;
import com.hhzmy.httputil.Url;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.tool.Tool;
import com.hhzmy.util.ImageLoaderUtil;
import com.lidroid.xutils.BitmapUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;
import static com.hhzmy.httputil.Url.url;
import static com.hhzmy.mis.redchildhyl.R.id.recyview_second;

/**
 * Created by mis on 2016/11/15.
 * 思路：
 * 通过获取BeanHome.DataBean 对象，通过其 内部 tag 个数来判断，如果对象 有一个 tag ,则展示图片
 * 如果为 多个图片 在讲数据继续传递 ，到 rv
 */

public class ViewHolderFourth extends BaseViewHolder {
  private   List<BeanHome.DataBean.TagBean> listAll;

    RecyclerView ryview_fourth;
    ImageView iv_rv_fourth;



    public ViewHolderFourth(View itemView, Context context) {
        super(itemView, context);
        ryview_fourth = (RecyclerView) itemView.findViewById(R.id.ryview_fourth);
        iv_rv_fourth = (ImageView) itemView.findViewById(R.id.iv_fourth_item);
        listAll = new ArrayList<BeanHome.DataBean.TagBean>();
    }

    @Override
    public void initData(Object data, int index) {
        if(listAll!=null)
        listAll.clear();
        listAll.addAll((List<BeanHome.DataBean.TagBean>)data);
        /*展示图片*/
        if(listAll.size()==1){
            if(!TextUtils.isEmpty(listAll.get(0).getPicUrl())){//判断图片不为空
                ryview_fourth.setVisibility(View.GONE);
                iv_rv_fourth.setVisibility(View.VISIBLE);
                DisplayImageOptions options = ImageLoaderUtil.myInitOptions();
                ImageLoader.getInstance().displayImage(Url.urlImageHttpHead+listAll.get(0).getPicUrl(),iv_rv_fourth,options);
            }
            /*展示*/
        }else if(listAll.size()>1){//复用 item
            iv_rv_fourth.setVisibility(View.GONE);
            ryview_fourth.setVisibility(View.VISIBLE);
            ryview_fourth.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            RecyViewFourthItemAdapter adapter = new RecyViewFourthItemAdapter(context, listAll);
            ryview_fourth.setAdapter(adapter);
            }

    }

//
}
