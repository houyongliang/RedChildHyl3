package com.hhzmy.adpter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhzmy.base.BaseViewHolder;
import com.hhzmy.bean.BeanHome;
import com.hhzmy.httputil.TypeUtil;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.view.ViewHolderFirst;
import com.hhzmy.view.ViewHolderFourth;
import com.hhzmy.view.ViewHolderSecond;
import com.hhzmy.view.ViewHolderThird;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.type;
import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.media.CamcorderProfile.get;

/**
 * Created by mis on 2016/11/15.
 */

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> implements TypeUtil {
    /*获取属性*/
    private BeanHome beanHome;
    private Context context;
    /**
     * 类型集合，adapter对应的数据集合
     */
    List<Pair<Integer, Object>> superData;
    private List<BeanHome.DataBean> data;
    private Map<Integer, Integer> map;
    private List<BeanHome.DataBean.TagBean> listFirst;
    private List<BeanHome.DataBean.TagBean> listSecond;

    private String TAG = "HomeRecyclerViewAdapter";
    private LayoutInflater layoutInflater;

    /**
     * 构造方法
     */
    public HomeRecyclerViewAdapter(Context context, BeanHome beanHome) {
        this.context = context;
        this.beanHome = beanHome;
        superData = new ArrayList<Pair<Integer, Object>>();//初始化数据
        map = new HashMap<>();
        /**数据处理*/
//        superData.add(new Pair<Integer, Object>(ITEM_TOP, beanHome));
        initOtherData();
        layoutInflater = LayoutInflater.from(context);

    }

    private void initOtherData() {
        /**获取说有数据*/
        data = beanHome.getData();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == null || data.get(i).getTag() == null)
                continue;
            if (data.get(i).getSequence() == 2) {
//                map.put(ITEM_GRIDVIEW_ONE_TEXT_IMAGE, data.get(i).getTag().size());
//                    superData.add(new Pair<Integer, Object>(ITEM_GRIDVIEW_ONE_TEXT_IMAGE, data.get(i).getTag().get(j)));
                superData.add(new Pair<Integer, Object>(ITEM_GRIDVIEW_ONE_TEXT_IMAGE, data.get(i)));
            }
            if (data.get(i).getSequence() == 5) {
//                map.put(ITEM_GRIDVIEW_TWO_TIELE_IMAGE, data.get(i).getTag().size());
//                    superData.add(new Pair<Integer, Object>(ITEM_GRIDVIEW_TWO_TIELE_IMAGE, data.get(i).getTag().get(j)));
                superData.add(new Pair<Integer, Object>(ITEM_GRIDVIEW_TWO_TIELE_IMAGE, data.get(i)));
            }
            if(data.get(i).getSequence()>=22&&data.get(i).getSequence()<=33 ){
                if(data.get(i) != null){

                    superData.add(new Pair<Integer, Object>(ITEM_LAYOUT_THREE_TIELE_IMAGE, data.get(i)));
                }
            }
            if(data.get(i).getSequence()>33){
                if(data.get(i) != null){
                   superData.add(new Pair<Integer, Object>(ITEM_HorizontalScrollView_TEXT, data.get(i)));
                }
            }


        }

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_GRIDVIEW_ONE_TEXT_IMAGE:
                View view1 = layoutInflater.inflate(R.layout.recyclerview_item_gridview_one, parent, false);
                return new ViewHolderFirst(view1, context);
            case ITEM_GRIDVIEW_TWO_TIELE_IMAGE:
                return new ViewHolderSecond(layoutInflater.inflate(R.layout.recyclerview_item_hlistview_two, parent, false), context);
            case ITEM_LAYOUT_THREE_TIELE_IMAGE:
                return new ViewHolderThird(layoutInflater.inflate(R.layout.recyclerview_item_linelayout_three, parent, false), context);
            case ITEM_HorizontalScrollView_TEXT:
                return  new ViewHolderFourth(layoutInflater.inflate(R.layout.recyclerview_item_linelayout_fourth, parent, false), context);
        }

        return null;

    }

    @Override
    public int getItemViewType(int position) {
        return superData.get(position).first;

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        switch (superData.get(position).first) {
            case ITEM_GRIDVIEW_ONE_TEXT_IMAGE:
               BeanHome.DataBean dataOne= (BeanHome.DataBean) superData.get(position).second;
                ((ViewHolderFirst) holder).initData(dataOne.getTag(), 0);

                break;
            case ITEM_GRIDVIEW_TWO_TIELE_IMAGE:
                BeanHome.DataBean dataTwo= (BeanHome.DataBean) superData.get(position).second;
                ((ViewHolderSecond) holder).initData(dataTwo.getTag(), 0);
//                listSecond=showData(listSecond,(ViewHolderSecond) holder, position,ITEM_GRIDVIEW_TWO_TIELE_IMAGE);
//                ((ViewHolderSecond) holder).initData(superData.get(position).second, map.get(ITEM_GRIDVIEW_TWO_TIELE_IMAGE));
                break;
            case ITEM_LAYOUT_THREE_TIELE_IMAGE:
                BeanHome.DataBean dataThree= (BeanHome.DataBean) superData.get(position).second;
                ((ViewHolderThird) holder).initData(dataThree.getTag(), 0);
                break;
            case ITEM_HorizontalScrollView_TEXT:
                BeanHome.DataBean dataFourth= (BeanHome.DataBean) superData.get(position).second;
                 ((ViewHolderFourth) holder).initData(dataFourth.getTag(), 0);
                break;

        }
    }


    private List<BeanHome.DataBean.TagBean> showData(List<BeanHome.DataBean.TagBean> list, BaseViewHolder holder, int position, int mapx) {
        if (list == null) {
            list = new ArrayList<BeanHome.DataBean.TagBean>();
            list.add((BeanHome.DataBean.TagBean) superData.get(position).second);
        } else {
            if (list.size() < map.get(mapx)) {
                list.add((BeanHome.DataBean.TagBean) superData.get(position).second);

            }
            if (list.size() == map.get(mapx)) {
                holder.initData(list, 0);
            }
        }
        return list;
    }



    @Override
    public int getItemCount() {
        return superData.size();
    }


    /**
     * 给数据打包，两个一块
     * * @return
     */
    public Pair<Object, Object> wrapData(Object f, Object s) {
        return new Pair<Object, Object>(f, s);
    }


}
