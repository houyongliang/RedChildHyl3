package com.hyl.mis.hyl.Bean;

/**
 * Created by mis on 2016/10/10.
 */
public class AD5 {
    /**
     *
     "id": "358",
     "image": "http://image.hmeili.com/yunifang/images/goods/ad0/160623120383916524110935835.png",
     "ad_type": 1,
     "url": "http://mobile.hmeili.com/yunifang/web/member/gift",
     "sort": 96,
     "position": 5,
     "enabled": 0,
     "ad_type_dynamic": "1",
     "ad_type_dynamic_data": "http://m.yunifang.com/yunifang/mobile/creditShop/loginDBShop?dbredirect=http://www.duiba.com.cn/chome/index",
     "ad_type_dynamic_detail": "http%3A%2F%2Fm.yunifang.com%2Fyunifang%2Fmobile%2FcreditShop%2FloginDBShop%3Fdbredirect%3Dhttp%3A%2F%2Fwww.duiba.com.cn%2Fchome%2Findex",
     "show_channel": "1,2,3,4",
     "title": "积分商城"
     */
    public String id;
    public String image;
    public int ad_type;
    public String url;
    public int sort;
    public int position;
    public int enabled;
    public String ad_type_dynamic;
    public String ad_type_dynamic_data;
    public String ad_type_dynamic_detail;
    public String show_channel;
    public String title;

    @Override
    public String toString() {
        return "AD5{" +
                "id='" + id + '\'' +
                ", image='" + image + '\'' +
                ", ad_type=" + ad_type +
                ", url='" + url + '\'' +
                ", sort=" + sort +
                ", position=" + position +
                ", enabled=" + enabled +
                ", ad_type_dynamic='" + ad_type_dynamic + '\'' +
                ", ad_type_dynamic_data='" + ad_type_dynamic_data + '\'' +
                ", ad_type_dynamic_detail='" + ad_type_dynamic_detail + '\'' +
                ", show_channel='" + show_channel + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
