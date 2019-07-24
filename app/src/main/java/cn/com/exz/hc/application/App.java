package cn.com.exz.hc.application;


import android.annotation.SuppressLint;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cn.com.exz.hc.entity.UserBean;
import cn.com.szw.lib.myframework.app.MyApplication;
import cn.jpush.android.api.JPushInterface;


/**
 * Created by pc on 2017/6/22.
 */

public class App extends MyApplication {

    @SuppressLint("RestrictedApi")
    @Override
    public void onCreate() {
        super.onCreate();
        salt = "9E127A4F0BAB43B3";

        MultiDex.install(this);
        com.blankj.utilcode.util.Utils.init(this);
        Fresco.initialize(this);
        CustomActivityOnCrash.install(this);
        ZXingLibrary.initDisplayOpinion(this);
//        initImagePicker();
        ImageLoaderConfiguration config = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(config);

        JPushInterface.init(this);
        JPushInterface.setDebugMode(true);
    }

    @Override
    public String getSalt() {
        return "9E127A4F0BAB43B3";
    }


    public static UserBean userBean;

    public static void setUser(UserBean user) {
        App.userBean = user;
    }

    public static UserBean getUser() {
        return userBean;
    }


    //获取用户的登录userid
    public static String getLoginUserId() {
        if (userBean == null) {
            return "";
        } else {
            return userBean.getSessionId();
        }
    }


}
