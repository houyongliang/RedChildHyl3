package com.hhzmy.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by mis on 2016/11/16.
 */

public class RecyclerViewClickListener implements RecyclerView.OnItemTouchListener {
    /**
     * 使用方法
     * //调用RecyclerView#addOnItemTouchListener方法能添加一个RecyclerView.OnItemTouchListener对象
     * mRecyclerView.addOnItemTouchListener(new RecyclerViewClickListener(this,new RecyclerViewClickListener.OnItemClickListener() {
     *
     * @Override public void onItemClick(View view, int position) {
     * Toast.makeText(MainActivity.this,"Click "+mData.get(position),Toast.LENGTH_SHORT).show();
     * }
     * @Override public void onItemLongClick(View view, int position) {
     * Toast.makeText(MainActivity.this,"Long Click "+mData.get(position),Toast.LENGTH_SHORT).show();
     * }
     * }));
     */

    private GestureDetector mGestureDetector;
    private OnItemClickListener mListener;

    public RecyclerViewClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context,
                new GestureDetector.SimpleOnGestureListener() { //这里选择SimpleOnGestureListener实现类，可以根据需要选择重写的方法
                    //单击事件
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        if (childView != null && mListener != null) {
                            mListener.onItemClick(childView, recyclerView.getChildLayoutPosition(childView));
                            return true;
                        }
                        return false;
                    }

                    //长按事件
                    @Override
                    public void onLongPress(MotionEvent e) {
                        View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        if (childView != null && mListener != null) {
                            mListener.onItemLongClick(childView, recyclerView.getChildLayoutPosition(childView));
                        }
                    }
                });
    }


    //内部接口，定义点击方法以及长按方法
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        //把事件交给GestureDetector处理
        if (mGestureDetector.onTouchEvent(e)) {
            return true;
        } else
            return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
