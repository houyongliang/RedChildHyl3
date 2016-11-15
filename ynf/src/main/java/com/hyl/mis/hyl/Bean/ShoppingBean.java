package com.hyl.mis.hyl.Bean;

/**
 * Created by mis on 2016/10/20.
 */
public class ShoppingBean {
    @Override
    public String toString() {
        return "ShoppingBean{" +
                "id=" + id +
                ", shop_price=" + shop_price +
                ", numShow=" + numShow +
                ", goodsimg='" + goodsimg + '\'' +
                ", is_activity_goods=" + is_activity_goods +
                ", is_allow_credit=" + is_allow_credit +
                ", url='" + url + '\'' +
                ", goods_name='" + goods_name + '\'' +
                '}'+"state"+state;
    }

    public  int id;
    public  double shop_price;
    public  int numShow;
    public  String goodsimg;
    public String is_activity_goods;
    public  String is_allow_credit;
    public  String url;
    public String goods_name;
    public int  state;
}
