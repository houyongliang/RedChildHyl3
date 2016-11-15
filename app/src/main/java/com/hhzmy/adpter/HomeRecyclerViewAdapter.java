package com.hhzmy.adpter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;

import com.hhzmy.base.BaseViewHolder;
import com.hhzmy.bean.BeanHome;
import com.hhzmy.httputil.TypeUtil;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.view.ViewHolderFirst;
import com.hhzmy.view.ViewHolderSecond;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.type;
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

    /**
     * 构造方法
     */
    public HomeRecyclerViewAdapter(Context context, BeanHome beanHome) {
        this.context = context;
        this.beanHome = beanHome;
        superData = new ArrayList<Pair<Integer, Object>>();//初始化数据
        /**数据处理*/
//        superData.add(new Pair<Integer, Object>(ITEM_TOP, beanHome));
        initOtherData();

    }

    private void initOtherData() {
        /**获取说有数据*/
        List<BeanHome.DataBean> data = beanHome.getData();
        for (int i = 0; i < data.size(); i++) {

                for (int j = 0; j < data.get(i).getTag().size(); j++) {
                    if(data.get(i).getSequence()==2){
                        superData.add(new Pair<Integer, Object>(ITEM_GRIDVIEW_ONE_TEXT_IMAGE, data.get(i).getTag().get(j)));
                    }
//                    Pair<Object, Object> objectObjectPair;//两个数据的集合  超过两个可以用list集合
                    if(data.get(i).getSequence()==5){
                        superData.add(new Pair<Integer, Object>(ITEM_GRIDVIEW_TWO_TIELE_IMAGE, data.get(i).getTag().get(j)));
                    }
                }
            }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_GRIDVIEW_ONE_TEXT_IMAGE:
                return new ViewHolderFirst(View.inflate(context, R.layout.recyclerview_item_gridview_one, null),context);
            case ITEM_GRIDVIEW_TWO_TIELE_IMAGE:
                return new ViewHolderSecond(View.inflate(context, R.layout.recyclerview_item_hlistview_two, null),context);
        }

        return new ViewHolderSecond(View.inflate(context, R.layout.recyclerview_item_hlistview_two, null),context);

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        switch (superData.get(position).first){
            case ITEM_GRIDVIEW_ONE_TEXT_IMAGE:
                ((ViewHolderFirst)holder).initData(superData.get(position));
                break;
            case ITEM_GRIDVIEW_TWO_TIELE_IMAGE:
                ((ViewHolderSecond)holder).initData(superData.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return superData.size();
    }







    /**
     * 给数据打包，两个一块
     *
     * @return
     */
    public Pair<Object, Object> wrapData(Object f, Object s) {
        return new Pair<Object, Object>(f, s);
    }


}
