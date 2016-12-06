package com.hhzmy.activity;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.hhzmy.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.io.File;

import static com.baidu.location.b.g.I;

/**
 * Created by mis on 2016/11/17.
 */

public class BaseApplication extends Application {
    {
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");


    }

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderUtil.initConfiguration(getApplicationContext());
        SDKInitializer.initialize(getApplicationContext());
        /*初始化ImageLoader*/
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = true;
        UMShareAPI.get(this);
//        Config.REDIRECT_URL = "您新浪后台的回调地址";
        Config.isUmengSina=false;


    }
}
