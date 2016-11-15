package com.hyl.mis.hyl.Bean;

/**
 * Created by mis on 2016/10/10.
 */
public class AD4 {
    /**
     *
     "id": "183",
     "image": "http://image.hmeili.com/yunifang/images/goods/ad1//160823092593113307234135360.jpg",
     "ad_type": 1,
     "url": "http://mobile.hmeili.com/yunifang/web/exchangeCode/code",
     "sort": 25,
     "position": 4,
     "enabled": 1,
     "createtime": "2015.07.06 15:20:05",
     "createuser": "shanxian",
     "ad_type_dynamic": "1",
     "ad_type_dynamic_data": "http://m.yunifang.com/yunifang/web/help/cash",
     "ad_type_dynamic_detail": "http%3A%2F%2Fm.yunifang.com%2Fyunifang%2Fweb%2Fhelp%2Fcash",
     "show_channel": "1,2,3,4",
     "goods": {
     "collect_count": 0,
     "reservable": false,
     "restriction": 0,
     "restrict_purchase_num": 0,
     "is_coupon_allowed": false,
     "allocated_stock": 0,
     "is_gift": 0
     },
     "channelType": "0"
     },

     */
    public String id;
    public String image;
    public int ad_type;
    public String url;
    public int sort;
    public int position;
    public int enabled;
    public String createtime;
    public String createuser;
    public String ad_type_dynamic;
    public String ad_type_dynamic_data;
    public String show_channel;
    public String channelType;
    public String ad_type_dynamic_detail;
   public Goods goods;

    @Override
    public String toString() {
        return "AD4{" +
                "id='" + id + '\'' +
                ", image='" + image + '\'' +
                ", ad_type=" + ad_type +
                ", url='" + url + '\'' +
                ", sort=" + sort +
                ", position=" + position +
                ", enabled=" + enabled +
                ", createtime='" + createtime + '\'' +
                ", createuser='" + createuser + '\'' +
                ", ad_type_dynamic='" + ad_type_dynamic + '\'' +
                ", ad_type_dynamic_data='" + ad_type_dynamic_data + '\'' +
                ", show_channel='" + show_channel + '\'' +
                ", channelType='" + channelType + '\'' +
                ", ad_type_dynamic_detail='" + ad_type_dynamic_detail + '\'' +
                ", goods=" + goods +
                '}';
    }
}
