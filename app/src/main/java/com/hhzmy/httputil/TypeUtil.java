package com.hhzmy.httputil;

/**
 * Created by mis on 2016/11/15.
 */

public interface TypeUtil {
    /** 头部布局，下面为标题*/
    public static final int ITEM_TOP=0;
    /** 网格布局 头为图片，下面为标题*/
    public static final int ITEM_GRIDVIEW_ONE_TEXT_IMAGE=1;
    /** 网格布局  抬头，图片网格布局，格式two*/
    public static final int ITEM_GRIDVIEW_TWO_TIELE_IMAGE=2;
    /** 网格布局  抬头，图片网格布局，格式three*/
    public static final int ITEM_GRIDVIEW_THREE_TIELE_IMAGE=3;
    /** 网格布局  抬头为文字，下面为水平滚动条 */
    public static final int ITEM_HorizontalScrollView_TEXT=4;
    /** 网格布局  抬头为图片，下面为水平滚动条 */
    public static final int ITEM_HorizontalScrollView_IMAGE=5;
    /** 网格布局  抬头为文字，下面为listview 垂直滚动样式 */
    public static final int ITEM_ScrollView_IMAGE=6;
}
