package com.hhzmy.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hhzmy.adpter.MyRecyclerViewAdapterSecond;
import com.hhzmy.adpter.RecyViewFourthItemAdapter;
import com.hhzmy.base.BaseViewHolder;
import com.hhzmy.bean.BeanHome;
import com.hhzmy.httputil.Url;
import com.hhzmy.httputil.Utils;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.tool.Tool;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;
import static com.hhzmy.httputil.Url.urlImageHttpHead;
import static com.hhzmy.mis.redchildhyl.R.id.iv_second;
import static com.hhzmy.mis.redchildhyl.R.id.ll_recyclerview_three;
import static com.hhzmy.mis.redchildhyl.R.id.recyview_second;
import static com.hhzmy.mis.redchildhyl.R.id.ryview_fourth;
import static com.hhzmy.tool.Tool.getScreenWidth;

/**
 * Created by mis on 2016/11/15.
 */

public class ViewHolderThird extends BaseViewHolder {
    private   List<BeanHome.DataBean.TagBean> listAll;
    ImageView iv;
    RecyclerView ryview_three;
    private BeanHome.DataBean dataThree;


    public ViewHolderThird(View itemView, Context context) {
        super(itemView, context);
        listAll=new ArrayList<BeanHome.DataBean.TagBean>();
        ryview_three = (RecyclerView) itemView.findViewById(R.id.ryview_three);

    }

    @Override
    public void initData(Object data, int index) {
        if(listAll!=null)
            listAll.clear();
             listAll.addAll((List<BeanHome.DataBean.TagBean>)data);

        ryview_three.setLayoutManager(new GridLayoutManager(context,listAll.size(),GridLayoutManager.VERTICAL,false));
//        ryview_three.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            RecyViewFourthItemAdapter adapter = new RecyViewFourthItemAdapter(context, listAll);
        ryview_three.setAdapter(adapter);

//        dataThree = (BeanHome.DataBean) data;
//        List<BeanHome.DataBean.TagBean> list = dataThree.getTag();
//        int width =Tool.getScreenWidth(context)/((list==null)?1:list.size());
//
//        for (int i = 0; i < list.size(); i++) {
//            if(!TextUtils.isEmpty(list.get(i).getPicUrl())){
//                iv=new ImageView(context);
//                String url=Url.urlImageHttpHead+ list.get(i).getPicUrl();
//                iv.setLayoutParams(new ViewGroup.LayoutParams(width,ViewGroup.LayoutParams.MATCH_PARENT));
//                Tool.displayImage(context,iv,url);
//                ll_three.addView(iv);
//                listIv.add(iv);
//            }
//
//        }
        
    }
}
