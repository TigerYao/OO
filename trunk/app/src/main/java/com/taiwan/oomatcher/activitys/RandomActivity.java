package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.base.BaseActivity;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/24 02:10
 */

public class RandomActivity extends BaseActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_random);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void widgetClick(View v) {

    }
}
