package com.hhzmy.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.hhzmy.activity.ContentActivity;
import com.hhzmy.adpter.MyRecyclerViewAdapter;
import com.hhzmy.adpter.MyRecyclerViewAdapterSecond;
import com.hhzmy.base.BaseViewHolder;
import com.hhzmy.base.RecyclerViewClickListener;
import com.hhzmy.bean.BeanHome;
import com.hhzmy.httputil.Url;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.tool.Tool;
import com.hhzmy.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;
import static android.media.CamcorderProfile.get;
import static com.hhzmy.httputil.Url.url;
import static com.hhzmy.mis.redchildhyl.R.id.iv_second;
import static com.hhzmy.mis.redchildhyl.R.id.recyview_first;

/**
 * Created by mis on 2016/11/15.
 */

public class ViewHolderSecond extends BaseViewHolder {
    List<BeanHome.DataBean.TagBean> listAll;
    List<BeanHome.DataBean.TagBean> listOne;
    List<BeanHome.DataBean.TagBean> listTwo;
    RecyclerView recyview_second;
    ImageView iv_second;
    private MyRecyclerViewAdapterSecond adapter;
    private String url;

    public ViewHolderSecond(View itemView, Context context) {
        super(itemView, context);
        recyview_second = (RecyclerView) itemView.findViewById(R.id.recyview_second);
        iv_second = (ImageView) itemView.findViewById(R.id.iv_second);
        listAll = new ArrayList<BeanHome.DataBean.TagBean>();
    }

    @Override
    public void initData(Object data, int index) {
        if (listOne != null) {
            listOne.clear();
        }
        if (listTwo != null) {
            listTwo.clear();
        }
        listAll = (List<BeanHome.DataBean.TagBean>) data;
        for (int i = 0; i < listAll.size(); i++) {

            if (listAll.get(i).getLinkType() == 4) {
                listOne = listAddData(listOne, listAll.get(i));

            } else {
                listTwo = listAddData(listTwo, listAll.get(i));
            }
        }

        if (adapter == null&&listOne!=null) {
            adapter = new MyRecyclerViewAdapterSecond(context, listOne);
            LinearLayoutManager llm = new LinearLayoutManager(context, OrientationHelper.HORIZONTAL, true);
            recyview_second.setLayoutManager(llm);

            recyview_second.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        if (listTwo != null && listTwo.size() > 0) {
            url = Url.urlImageHttpHead + listTwo.get(0).getPicUrl();
            DisplayImageOptions options = ImageLoaderUtil.myInitOptions();
            ImageLoader.getInstance().displayImage(url,iv_second,options);

//            Tool.displayImage(context, iv_second, url);
        }
        recyview_second.addOnItemTouchListener(new RecyclerViewClickListener(context, recyview_second, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               Tool.myStartActivity(context, ContentActivity.class,null,null);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    public List<BeanHome.DataBean.TagBean> listAddData(List<BeanHome.DataBean.TagBean> list, BeanHome.DataBean.TagBean bean) {
        if (list == null) {
            list = new ArrayList<BeanHome.DataBean.TagBean>();
        }
        list.add(bean);
        return list;
    }
}
