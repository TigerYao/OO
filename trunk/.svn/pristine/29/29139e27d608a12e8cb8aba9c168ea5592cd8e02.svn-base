package com.taiwan.oomatcher.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.taiwan.oomatcher.base.BaseApplication;
import com.taiwan.oomatcher.config.Config;

import org.w3c.dom.Text;

import java.util.Locale;

import static android.R.attr.type;
import static android.R.attr.versionName;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/17 11:39
 */

public class Tools {

    public static void setlanguage(Context mContext, String languageCode){
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        // 应用用户选择语言
        if(languageCode.equals("zh-CN")){
            config.locale = Locale.SIMPLIFIED_CHINESE;
        }else if(languageCode.equals("en")){
            config.locale = Locale.ENGLISH;
        }else if(languageCode.equals("zh-TW")){
            config.locale = Locale.TAIWAN;
        }else if(languageCode.equals("fr")){//法语
            config.locale = Locale.FRANCE;
        }else if(languageCode.equals("ja")){//日本語
            config.locale = Locale.JAPAN;
        }else if(languageCode.equals("ko")){//韩语
            config.locale = Locale.KOREA;
        }else if(languageCode.equals("de")){//德国
            config.locale = Locale.GERMANY;
        }else{
            config.locale = Locale.SIMPLIFIED_CHINESE;
        }
        resources.updateConfiguration(config, dm);
    }

    public static boolean isEmpty(String str){
        if (TextUtils.isEmpty(str) || str.equals("null")) {//后台可能会返回“null”
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(EditText stredit){
        if(stredit == null)
            return true;
        String str = stredit.getText().toString();
        if (TextUtils.isEmpty(str) || str.equals("null")) {//后台可能会返回“null”
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(TextView stredit){
        if(stredit == null)
            return true;
        String str = stredit.getText().toString();
        if (TextUtils.isEmpty(str) || str.equals("null")) {//后台可能会返回“null”
            return true;
        } else {
            return false;
        }
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    /**
     * 返回当前程序版本名
     */
    public static int getAppVersionCode(Context context) {
        int versionCode = 0;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = pi.versionCode;
        } catch (Exception e) {
        }
        return versionCode;
    }

    /**
     * 检测网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable() && mNetworkInfo.isConnected();
            }
        }
        return false;
    }

    /**
     * make true current connect service is wifi
     *
     * @param mContext
     * @return
     */
    public static boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    public static void println(String str) {
        if (Config.DEBUG && !TextUtils.isEmpty(str)) {
            System.out.println(str);
        }
    }

    public static String getString(int resId) {
        return BaseApplication.getInstance().getString(resId);
    }
}
