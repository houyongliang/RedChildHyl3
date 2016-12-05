package com.hhzmy.fragment;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhzmy.adpter.RecyViewLeftAdapter;
import com.hhzmy.adpter.RecyViewRightAdapter;
import com.hhzmy.base.BaseFragment;
import com.hhzmy.base.RecyclerViewClickListener;
import com.hhzmy.bean.ClassBean;
import com.hhzmy.bean.ClassBean2;
import com.hhzmy.httputil.OkHttp;
import com.hhzmy.httputil.RecycleViewDivider;
import com.hhzmy.httputil.Utils;
import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.tool.Tool;
import com.hhzmy.util.DividerGridItemDecoration;
import com.hhzmy.util.DividerItemDecoration;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.handle;
import static android.R.string.ok;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.baidu.location.b.g.i;
import static java.lang.System.clearProperty;
import static java.lang.System.in;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends BaseFragment {

    //左边recyclerview
    @BindView(R.id.rv_class_page_left)
    RecyclerView rvClassPageLeft;
    @BindView(R.id.rv_class_page_right)
    //右边的recyclerview
            RecyclerView rvClassPageRight;
    private Activity mActvity;
    private Handler mHandler=new Handler(Looper.getMainLooper());
    private List<ClassBean2.RsBean> list_left;
    private  List<ClassBean2.RsBean.ChildrenBean> list_right;
    private String TAG="ClassFragment";
    private  Boolean isHeader=false;
    private RecyViewLeftAdapter adapter_left;
    private RecyViewRightAdapter adapter_right;
    private GridLayoutManager glm;
    private int index=1;
    public ClassFragment() {
        list_left=new ArrayList<ClassBean2.RsBean>();
        list_right=new ArrayList<ClassBean2.RsBean.ChildrenBean>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActvity = getActivity();
        View view = inflater.inflate(R.layout.fragment_class, container, false);
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initView() {//初始化数据


    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    InputStream stream = mActvity.getResources().getAssets().open("category.txt");
                    final String json= Utils.stream2String(stream);
                    Log.e(TAG, "run: "+json );
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            parseData(json);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
    private void parseData(String json){
        Log.e(TAG, "parseData: "+json );
        ClassBean2 classBean = Tool.parseJsonWithGson(json, ClassBean2.class);
        List<ClassBean2.RsBean> rs = classBean.getRs();//获取数据集合
        Log.e(TAG, "parseData: "+ rs.size());



        setRecyclerViewLeft(rs);
        setReyclerViewRight(rs, index);
    }

    private void setReyclerViewRight(List<ClassBean2.RsBean> rs, int index) {
        list_right.clear();
        List<ClassBean2.RsBean.ChildrenBean> childrenList = list_left.get(index).getChildren();
        for (int i = 0; i < childrenList.size(); i++) {
            childrenList.get(i).isHeader = true;
            list_right.add(childrenList.get(i));
            list_right.addAll(childrenList.get(i).getChildren());
        }
        //右边 recyclerview
        adapter_right = new RecyViewRightAdapter(mActvity,list_right);
        glm = new GridLayoutManager(mActvity,3,GridLayoutManager.VERTICAL,false);
        //设置列宽
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return  list_right.get(position).isHeader==true?3:1;
            }
        });
        rvClassPageRight.addItemDecoration(new DividerGridItemDecoration(mActvity));
        rvClassPageRight.setLayoutManager(glm);
        //        rvClassPageRight.addItemDecoration(new RecycleViewDivider(mActvity,LinearLayoutManager.HORIZONTAL,1, Color.parseColor("#FFF4C1")));
        rvClassPageRight.setAdapter(adapter_right);


    }


    public void setRecyclerViewLeft(List<ClassBean2.RsBean> list) {
        Log.e(TAG, "setRecyclerViewLeft: "+list.size() );
        list_left.clear();
        //左边视图
        list_left.addAll(list);//获取数据集合

        adapter_left = new RecyViewLeftAdapter(mActvity,list_left);
        rvClassPageLeft.setLayoutManager(new LinearLayoutManager(mActvity));
        rvClassPageLeft.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0,0,0,10);

            }
        });
        /*设置条目点击事件*/
        rvClassPageLeft.setAdapter(adapter_left);
        adapter_left.notifyDataSetChanged();

        //右边






    /*设置点击事件*/
        rvClassPageLeft.addOnItemTouchListener(new RecyclerViewClickListener(mActvity,rvClassPageLeft, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

//                adapter_left.notifyDataSetChanged();
                /*点击更新rv Right 的数据*/
                list_right.clear();
                index=position;
                List<ClassBean2.RsBean.ChildrenBean> childrenList = list_left.get(index).getChildren();
                for (int i = 0; i < childrenList.size(); i++) {
                    childrenList.get(i).isHeader = true;
                    list_right.add(childrenList.get(i));
                    list_right.addAll(childrenList.get(i).getChildren());
                }

               adapter_right.notifyDataSetChanged();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

    }
}
