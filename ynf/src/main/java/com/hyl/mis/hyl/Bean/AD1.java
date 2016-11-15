package com.hyl.mis.hyl.Bean;

/**
 * Created by mis on 2016/10/10.
 */
public class AD1 {
    /**
     * "id": "669",
     * "image": "http://image.hmeili.com/yunifang/images/goods/ad0/160929175433720346683913899.jpg",
     * "ad_type": 0,
     * "sort": 688,
     * "position": 0,
     * "enabled": 1,
     * "createtime": "2016.09.21 10:00:29",
     * "createuser": "ruge",
     * "ad_type_dynamic": "35",
     * "show_channel": "1,2",
     * "channelType": "1"
     */
    public String id;
    public String image;
    public int ad_type;
    public int sort;
    public int position;
    public int enabled;
    public String createtime;
    public String createuser;
    public String ad_type_dynamic;
    public String ad_type_dynamic_data;
    public String ad_type_dynamic_detail;
    public String show_channel;
    public String channelType;

    @Override
    public String toString() {
        return "AD1{" +
                "id='" + id + '\'' +
                ", image='" + image + '\'' +
                ", ad_type=" + ad_type +
                ", sort=" + sort +
                ", position=" + position +
                ", enabled=" + enabled +
                ", createtime='" + createtime + '\'' +
                ", createuser='" + createuser + '\'' +
                ", ad_type_dynamic='" + ad_type_dynamic + '\'' +
                ", ad_type_dynamic_data='" + ad_type_dynamic_data + '\'' +
                ", ad_type_dynamic_detail='" + ad_type_dynamic_detail + '\'' +
                ", show_channel='" + show_channel + '\'' +
                ", channelType='" + channelType + '\'' +
                '}';
    }
}
