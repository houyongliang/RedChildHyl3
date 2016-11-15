package com.hyl.mis.hyl.application;

import android.app.Application;

import com.hyl.mis.hyl.Utils.ImageLoaderUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by mis on 2016/10/10.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化ImageLoader
        ImageLoaderUtils.initImageLoaderUtils(getApplicationContext());
    }
}
