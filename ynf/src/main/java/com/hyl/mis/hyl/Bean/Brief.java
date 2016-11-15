package com.hyl.mis.hyl.Bean;

/**
 * Created by mis on 2016/10/10.
 */
public class Brief {
    /**
     "id": "772",
     "goods_name": "热销爆款丨清爽亮颜黑面膜套装21片",
     "shop_price": 99.9,
     "market_price": 297,
     "goods_img": "http://image.hmeili.com/yunifang/images/goods/772/goods_img/1608191429278479187604212.jpg",
     "reservable": false

     */
    public String id;
    public String goods_name;
    public double shop_price;
    public double market_price;
    public String goods_img;
    public Boolean reservable;

    @Override
    public String toString() {
        return "Brief{" +
                "id='" + id + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", shop_price=" + shop_price +
                ", market_price=" + market_price +
                ", goods_img='" + goods_img + '\'' +
                ", reservable=" + reservable +
                '}';
    }
}
