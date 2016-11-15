package com.hyl.mis.hyl.Bean;

/**
 * Created by mis on 2016/10/10.
 */
public class BeanHome{
    public int code;
    public String msg;
    public Data  data;

    @Override
    public String toString() {
        return "BeanHome{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
