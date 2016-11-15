package com.hyl.mis.hyl.Bean;

/**
 * Created by mis on 2016/10/10.
 */
public class Goods {
    /**
     * "collect_count": 0,
     "reservable": false,
     "restriction": 0,
     "restrict_purchase_num": 0,
     "is_coupon_allowed": false,
     "allocated_stock": 0,
     "is_gift": 0
     */
    public int collect_count;
    public boolean reservable;
    public int restriction;
    public int restrict_purchase_num;
    public boolean is_coupon_allowed;
    public int allocated_stock;
    public int is_gift;

    @Override
    public String toString() {
        return "Goods{" +
                "collect_count=" + collect_count +
                ", reservable=" + reservable +
                ", restriction=" + restriction +
                ", restrict_purchase_num=" + restrict_purchase_num +
                ", is_coupon_allowed=" + is_coupon_allowed +
                ", allocated_stock=" + allocated_stock +
                ", is_gift=" + is_gift +
                '}';
    }
}
