package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.alibaba.fastjson.JSONObject;
import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.base.BaseTabActivity;
import com.taiwan.oomatcher.config.Config;
import com.taiwan.oomatcher.entity.NewVersion;
import com.taiwan.oomatcher.http.HttpUtils;
import com.taiwan.oomatcher.utils.Tools;

import java.util.Map;

/**
 * 作 者: huanghuojun
 * 描 述: 主界面
 * 版 本:
 * 创建日期: 2017/12/17 15:32
 */

public class MainActivity extends BaseTabActivity {

    private Context mContext;
    private TabHost mTabhost;
    private RelativeLayout mMapRl;
    private ImageView mMapImage;
    private RelativeLayout mUserWallRl;
    private ImageView mUserWallImage;
    private RelativeLayout mTaskWallRl;
    private ImageView mTaskWallImage;
    private RelativeLayout mRandomRl;
    private ImageView mRandomImage;
    private RelativeLayout mMessageRl;
    private ImageView mMessageImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        mTabhost = getTabHost();
        mMapRl = (RelativeLayout) findViewById(R.id.map_rl);
        mMapImage = (ImageView) findViewById(R.id.map_image);
        mUserWallRl = (RelativeLayout) findViewById(R.id.user_wall_rl);
        mUserWallImage = (ImageView) findViewById(R.id.user_wall_image);
        mTaskWallRl = (RelativeLayout) findViewById(R.id.task_wall_rl);
        mTaskWallImage = (ImageView) findViewById(R.id.task_wall_image);
        mRandomRl = (RelativeLayout) findViewById(R.id.random_rl);
        mRandomImage = (ImageView) findViewById(R.id.random_image);
        mMessageRl = (RelativeLayout) findViewById(R.id.message_rl);
        mMessageImage = (ImageView) findViewById(R.id.message_image);
    }

    @Override
    protected void init() {
        setCurrentTab(0);
        mTabhost.setup(getLocalActivityManager());
        mTabhost.addTab(mTabhost.newTabSpec("map").setIndicator("map").setContent(new Intent(mContext, MapActivity.class)));
        mTabhost.addTab(mTabhost.newTabSpec("userwall").setIndicator("userwall").setContent(new Intent(mContext, UserWallActivity.class)));
        mTabhost.addTab(mTabhost.newTabSpec("taskwall").setIndicator("taskwall").setContent(new Intent(mContext, TaskWallActivity.class)));
        mTabhost.addTab(mTabhost.newTabSpec("random").setIndicator("random").setContent(new Intent(mContext, RandomActivity.class)));
        mTabhost.addTab(mTabhost.newTabSpec("message").setIndicator("message").setContent(new Intent(mContext, MessageActivity.class)));
        getNewVersion();
    }
    @Override
    protected void setListeners() {
        mMapRl.setOnClickListener(this);
        mUserWallRl.setOnClickListener(this);
        mTaskWallRl.setOnClickListener(this);
        mRandomRl.setOnClickListener(this);
        mMessageRl.setOnClickListener(this);
    }

    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.map_rl:
                setCurrentTab(0);
                break;
            case R.id.user_wall_rl:
                setCurrentTab(1);
                break;
            case R.id.task_wall_rl:
                setCurrentTab(2);
                break;
            case R.id.random_rl:
                setCurrentTab(3);
                break;
            case R.id.message_rl:
                setCurrentTab(4);
                break;
        }
    }

    private void setCurrentTab(int index) {
        clearAllRdoBtnChecked();
        switch (index) {
            case 0:
                mMapImage.setSelected(true);
                break;
            case 1:
                mUserWallRl.setSelected(true);
                break;
            case 2:
                mTaskWallImage.setSelected(true);
                break;
            case 3:
                mRandomImage.setSelected(true);
                break;
            case 4:
                mMessageImage.setSelected(true);
                break;
        }
        mTabhost.setCurrentTab(index);
    }

    private void clearAllRdoBtnChecked() {
        mMapImage.setSelected(false);
        mTaskWallImage.setSelected(false);
        mUserWallImage.setSelected(false);
        mRandomImage.setSelected(false);
        mMessageImage.setSelected(false);
    }

    private void getNewVersion(){
        JSONObject json = new JSONObject();
        json.put("appTypeId",1);
        json.put("versionNum", Tools.getAppVersionCode(mContext));
        HttpUtils.sendGetSign("getNewVersion", json, new HttpUtils.CallBackText() {
            @Override
            public void onSuccess(String responseData) {
                NewVersion newVersion = JSONObject.parseObject(responseData, NewVersion.class);
            }

            @Override
            public void onFailure(Exception e, String msg) {
            }
        });
    }

    private void getMyInfo(){
        JSONObject json = new JSONObject();
        json.put("appTypeId",1);
        json.put("versionNum", Tools.getAppVersionCode(mContext));
        HttpUtils.sendGetSign("user/myHome/getUserInfo", json, new HttpUtils.CallBackText() {
            @Override
            public void onSuccess(String responseData) {

            }

            @Override
            public void onFailure(Exception e, String msg) {
            }
        });
    }
}
