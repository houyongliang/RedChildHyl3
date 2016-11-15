package com.hyl.mis.hyl.Utils;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by mis on 2016/10/10.
 */
public class MyHttp {
    public  static void getData(HttpRequest.HttpMethod httpMethod,String url,RequestParams params,RequestCallBack<String> callBack){
        HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(httpMethod,url,params,callBack);
    }
}
