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

import cn.jpush.android.api.JPushInterface;
import cn.smssdk.SMSSDK;

import static com.baidu.location.b.g.I;

/**
 * Created by mis on 2016/11/17.
 */

public class BaseApplication extends Application {
    {
        //微信 wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //新浪微博
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        PlatformConfig.setQQZone("1105874430", "J7gWrWNYGKncRhay");



    }

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderUtil.initConfiguration(getApplicationContext());
        SDKInitializer.initialize(getApplicationContext());
        /*初始化ImageLoader*/
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式

        UMShareAPI.get(this);
//        Config.REDIRECT_URL = "您新浪后台的回调地址";
        Config.isUmengSina=false;
//        Config.DEBUG = true;
        SMSSDK.initSDK(this, "19bbf6db20b0a", "9bea131ae19da79177c24091dbbb11c7");
        /*jpUSH*/
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
