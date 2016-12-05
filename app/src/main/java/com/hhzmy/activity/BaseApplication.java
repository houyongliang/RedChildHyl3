package com.hhzmy.activity;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.hhzmy.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;

import static com.baidu.location.b.g.I;

/**
 * Created by mis on 2016/11/17.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderUtil.initConfiguration(getApplicationContext());
        SDKInitializer.initialize(getApplicationContext());
        /*初始化ImageLoader*/



    }
}
