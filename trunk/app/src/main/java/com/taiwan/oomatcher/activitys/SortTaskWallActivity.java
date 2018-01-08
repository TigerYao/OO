package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.base.BaseActivity;

import static com.taiwan.oomatcher.R.id.select;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/27 13:05
 */

public class SortTaskWallActivity extends BaseActivity{

    private Context mContext;
    private TextView mTitleRightTxt;
    private TextView mTitleCenterTxt;
    private RelativeLayout mOrderTypeLl1;
    private ImageView mOrderTypeImage1;
    private RelativeLayout mOrderTypeLl2;
    private ImageView mOrderTypeImage2;
    private RelativeLayout mOrderTypeLl3;
    private ImageView mOrderTypeImage3;
    private RelativeLayout mOrderTypeLl4;
    private ImageView mOrderTypeImage4;
    private int mOrderType = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        mOrderType = getIntent().getIntExtra("orderType", 1);
        mTitleCenterTxt.setText(R.string.sort);
        mTitleRightTxt.setText(R.string.ok);

        select(mOrderType);
    }

    private void select(int i){
        mOrderType = i;
        hideAll();
        if(i == 1){
            mOrderTypeImage1.setVisibility(View.VISIBLE);
        }else  if(i == 2){
            mOrderTypeImage2.setVisibility(View.VISIBLE);
        }else  if(i == 3){
            mOrderTypeImage3.setVisibility(View.VISIBLE);
        }else  if(i == 4){
            mOrderTypeImage4.setVisibility(View.VISIBLE);
        }
    }

    private void hideAll(){
        mOrderTypeImage1.setVisibility(View.GONE);
        mOrderTypeImage2.setVisibility(View.GONE);
        mOrderTypeImage3.setVisibility(View.GONE);
        mOrderTypeImage4.setVisibility(View.GONE);
    }

    public void backClick(View view) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_sort_task_wall);
        assignViews();
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setListeners() {
        mTitleRightTxt.setOnClickListener(this);
        mOrderTypeLl1.setOnClickListener(this);
        mOrderTypeLl2.setOnClickListener(this);
        mOrderTypeLl3.setOnClickListener(this);
        mOrderTypeLl4.setOnClickListener(this);
    }

    @Override
    protected void widgetClick(View v) {
        if(v.getId() == mTitleRightTxt.getId()){
            Intent intent = new Intent();
            intent.putExtra("orderType", mOrderType);
            setResult(RESULT_OK, intent);
            finish();
        }else if(v.getId() == mOrderTypeLl1.getId()){
            select(1);
        }else if(v.getId() == mOrderTypeLl2.getId()){
            select(2);
        }else if(v.getId() == mOrderTypeLl3.getId()){
            select(3);
        }else if(v.getId() == mOrderTypeLl4.getId()){
            select(4);
        }
    }

    private void assignViews() {
        mTitleRightTxt = (TextView) findViewById(R.id.title_right_txt);
        mTitleRightTxt.setVisibility(View.VISIBLE);
        mTitleCenterTxt = (TextView) findViewById(R.id.title_center_txt);
        mOrderTypeLl1 = (RelativeLayout) findViewById(R.id.order_type_ll1);
        mOrderTypeImage1 = (ImageView) findViewById(R.id.order_type_image1);
        mOrderTypeLl2 = (RelativeLayout) findViewById(R.id.order_type_ll2);
        mOrderTypeImage2 = (ImageView) findViewById(R.id.order_type_image2);
        mOrderTypeLl3 = (RelativeLayout) findViewById(R.id.order_type_ll3);
        mOrderTypeImage3 = (ImageView) findViewById(R.id.order_type_image3);
        mOrderTypeLl4 = (RelativeLayout) findViewById(R.id.order_type_ll4);
        mOrderTypeImage4 = (ImageView) findViewById(R.id.order_type_image4);
    }

}
