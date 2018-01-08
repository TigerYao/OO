package com.taiwan.oomatcher.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.taiwan.oomatcher.utils.LocaleUtils;

import java.util.Locale;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/17 11:17
 */

public class BaseApplication extends Application {

    public static Context applicationContext;
    private static DbUtils db;

    public static Context getInstance() {
        return applicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
//        Locale _UserLocale = LocaleUtils.getUserLocale(this);
//        LocaleUtils.updateLocale(this, _UserLocale);
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        Locale _UserLocale=LocaleUtils.getUserLocale(this);
//        //系统语言改变了应用保持之前设置的语言
//        if (_UserLocale != null) {
//            Locale.setDefault(_UserLocale);
//            Configuration _Configuration = new Configuration(newConfig);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                _Configuration.setLocale(_UserLocale);
//            } else {
//                _Configuration.locale =_UserLocale;
//            }
//            getResources().updateConfiguration(_Configuration, getResources().getDisplayMetrics());
//        }
//    }

    public static DbUtils getInstance(Context context) {
        if (db == null) {
            DbUtils.DaoConfig config = new DbUtils.DaoConfig(context);
            config.setDbName("matcher.db");
//            config.setDbDir(SDCardUtils.getCachePath());
            config.setDbVersion(1);
            config.setDbUpgradeListener(new DbUtils.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbUtils db, int oldVersion, int newVersion) {
                    if(oldVersion == 1 && newVersion == 2){

                    }
                }
            });
            db = DbUtils.create(config);
        }
        // 开启事务
        // db.configAllowTransaction(false);
        return db;
    }

}
