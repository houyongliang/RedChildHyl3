package com.hyl.mis.hyl.Bean;

import java.util.List;

/**
 * Created by mis on 2016/10/10.
 */
public class GroupBriefs {
    /**
     * "id": "88",
     "name": "热销爆款",
     "image": "http://image.hmeili.com/yunifang/images/goodsGroup/160311192853416668405402254.jpg",
     "more_flag": true,

     */
    public String id;
    public String name;
    public String image;
    public Boolean more_flag;
    public List<Brief> briefList;

    @Override
    public String toString() {
        return "GroupBriefs{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", more_flag=" + more_flag +
                ", briefList=" + briefList +
                '}';
    }
}
