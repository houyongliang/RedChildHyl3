package com.hhzmy.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhzmy.bean.BeanHome;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.tool.Tool;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.R.id.list;

/**
 * Created by mis on 2016/11/14.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * 网格布局 头为图片，下面为标题
     */
    public static final int ITEM_GRIDVIEW_ONE_TEXT_IMAGE = 0;
    /**
     * 网格布局  抬头，图片网格布局，格式two
     */
    public static final int ITEM_GRIDVIEW_TWO_TIELE_IMAGE = 1;
    /**
     * 网格布局  抬头，图片网格布局，格式three
     */
    public static final int ITEM_GRIDVIEW_THREE_TIELE_IMAGE = 2;
    /**
     * 网格布局  抬头为文字，下面为水平滚动条
     */
    public static final int ITEM_HorizontalScrollView_TEXT = 3;
    /**
     * 网格布局  抬头为图片，下面为水平滚动条
     */
    public static final int ITEM_HorizontalScrollView_IMAGE = 4;
    /**
     * 网格布局  抬头为文字，下面为listview 垂直滚动样式
     */
    public static final int ITEM_ScrollView_IMAGE = 5;


    private Context context;
    private List<BeanHome.DataBean.TagBean> list = new ArrayList<>();


    public MyRecyclerViewAdapter(Context context, List<BeanHome.DataBean.TagBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_item_gridview_one, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BeanHome.DataBean.TagBean tagBean = list.get(position);
        String url = "http://image1.suning.cn" + tagBean.getPicUrl();
        Log.e("sdgsagdsdg", "onBindViewHolder: "+url );

        Tool.displayImage(context,( (MyViewHolder)holder).iv, url);//展示图片数据
        ( (MyViewHolder)holder).tv.setText(tagBean.getElementName() + "");
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_recyview_gridview_one);
            iv = (ImageView) itemView.findViewById(R.id.iv_recyview_gridview_one);

        }
    }
}
