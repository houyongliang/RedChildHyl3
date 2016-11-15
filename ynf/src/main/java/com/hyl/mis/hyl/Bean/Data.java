package com.hyl.mis.hyl.Bean;

import java.util.List;

/**
 * Created by mis on 2016/10/10.
 */
public class Data {
    public List<AD1> ad1;
    public List<AD3> ad3;
    public List<AD4> ad4;
    public List<AD5> ad5;
    public List<GroupBriefs>  groupBriefs;
    public Boolean creditRecived;

    @Override
    public String toString() {
        return "Data{" +
                "ad1=" + ad1 +
                ", ad3=" + ad3 +
                ", ad4=" + ad4 +
                ", ad5=" + ad5 +
                ", groupBriefs=" + groupBriefs +
                ", creditRecived=" + creditRecived +
                '}';
    }
}
