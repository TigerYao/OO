package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.config.Config;
import com.taiwan.oomatcher.http.HttpUtils;
import com.taiwan.oomatcher.utils.SPUtils;
import com.taiwan.oomatcher.utils.Tools;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/24 12:46
 */

public class WelcomeActivity extends BaseActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void init() {
//        {"success":true,"code":1,"msg":"成功","url":null,"data":{"serviceFeeRate":5,"depositeRate":10,"languages":[{"languageCode":"de","languageName":"德语"},{"languageCode":"en","languageName":"English(US)"},{"languageCode":"fr","languageName":"法语"},{"languageCode":"ja","languageName":"日本語"},{"languageCode":"ko","languageName":"한국어"},{"languageCode":"zh-CN","languageName":"中文(简体)"},{"languageCode":"zh-TW","languageName":"中文繁体"}],"paymentTypes":[{"paymentTypeId":1,"defaultPayTypeFlag":null,"paymentTypeName":"AliPay"},{"paymentTypeId":2,"defaultPayTypeFlag":null,"paymentTypeName":"WechartPay"},{"paymentTypeId":3,"defaultPayTypeFlag":null,"paymentTypeName":"Paypal"},{"paymentTypeId":4,"defaultPayTypeFlag":null,"paymentTypeName":"UnionPay"},{"paymentTypeId":5,"defaultPayTypeFlag":null,"paymentTypeName":"Wallet"}],"newVersionNum":1}}
        getServiceInit();

    }

    private void gotoActivity(){
        String version = (String) SPUtils.get(SPUtils.VERSION, "");
        String currentVersion = Tools.getAppVersionName(mContext);
        Intent intent = new Intent();
        if(version.equals(currentVersion)){
            String languageCode = (String) SPUtils.get(SPUtils.LANGUAGE_CODE, "");
            if(Tools.isEmpty(languageCode)){
                intent.setClass(mContext, SelectLanguageActivity.class);
            }else{
                Tools.setlanguage(mContext, languageCode);
                String token = (String) SPUtils.get(SPUtils.TOKEN, "");
                if(Tools.isEmpty(token)){
                    intent.setClass(mContext, LoginActivity.class);
                }else{
                    intent.setClass(mContext, MainActivity.class);
                }
            }
        }else{
            intent.setClass(mContext, GuideActivity.class);
        }
        startActivity(intent);
        finish();
    }

    private void getServiceInit(){
        HttpUtils.sendGetSign("init", null, new HttpUtils.CallBackText() {
            @Override
            public void onSuccess(String responseData) {
                JSONObject jsonData = JSONObject.parseObject(responseData);
                SPUtils.put(SPUtils.SERVICEFEERATE, jsonData.getInteger("serviceFeeRate"));
                SPUtils.put(SPUtils.DEPOSITERATE, jsonData.getInteger("depositeRate"));
                SPUtils.put(SPUtils.NEWVERSIONNUM, jsonData.getInteger("newVersionNum"));
                SPUtils.put(SPUtils.LANGUAGES, jsonData.getString("languages"));
                SPUtils.put(SPUtils.PAYMENTTYPES, jsonData.getString("paymentTypes"));
                gotoActivity();
            }

            @Override
            public void onFailure(Exception e, String msg) {
            }
        });
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void widgetClick(View v) {

    }
}
