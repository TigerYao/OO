package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.base.BaseActivity;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/28 22:49
 */

public class PayDepositeActivity extends BaseActivity{

    private Context mContext;
    private TextView mNoTxt;
    private TextView mYesTxt;
    private TextView mMoneyQianTxt;
    private TextView mMoneyFuhaoTxt;
    private TextView mMoneyFeilvTxt;
    private TextView mMoneyHouTxt;
    private TextView mMoneyGbpQianTxt;
    private TextView mMoneyGbpHouTxt;
    private TextView mTotalGbpMoneyTxt;
    private TextView mTitleCenterTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        mTitleCenterTxt.setVisibility(View.GONE);
    }

    public void backClick(View view) {

        finish();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_pay_deposite);
        assignViews();
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

    private void assignViews() {
        mTitleCenterTxt = (TextView) findViewById(R.id.title_center_txt);
        mNoTxt = (TextView) findViewById(R.id.no_txt);
        mYesTxt = (TextView) findViewById(R.id.yes_txt);
        mMoneyQianTxt = (TextView) findViewById(R.id.money_qian_txt);
        mMoneyFuhaoTxt = (TextView) findViewById(R.id.money_fuhao_txt);
        mMoneyFeilvTxt = (TextView) findViewById(R.id.money_feilv_txt);
        mMoneyHouTxt = (TextView) findViewById(R.id.money_hou_txt);
        mMoneyGbpQianTxt = (TextView) findViewById(R.id.money_gbp_qian_txt);
        mMoneyGbpHouTxt = (TextView) findViewById(R.id.money_gbp_hou_txt);
        mTotalGbpMoneyTxt = (TextView) findViewById(R.id.total_gbp_money_txt);
    }
}
