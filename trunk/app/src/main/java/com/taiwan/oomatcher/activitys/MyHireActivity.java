package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.utils.DialogUtils;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/24 16:45
 */

public class MyHireActivity extends BaseActivity {

    private Context mContext;
    private TextView mTitleCenterTxt;
    private RadioGroup mTypeGroup;
    private RadioButton mTypeRBtn1;
    private RadioButton mTypeRBtn2;
    private RadioButton mTypeRBtn3;
    private RadioButton mTypeRBtn4;
    private RelativeLayout mAddNewServiceRl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        mTitleCenterTxt.setText(R.string.my_hire_setting);
    }

    public void backClick(View v){
        finish();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_my_hire_setting);
        assignViews();
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setListeners() {
        mAddNewServiceRl.setOnClickListener(this);
    }

    @Override
    protected void widgetClick(View v) {
        if(v.getId() == mAddNewServiceRl.getId()){
            switch (mTypeGroup.getCheckedRadioButtonId()){
                case R.id.type_rBtn1:
                    checkedBtn1();
                    break;
                case R.id.type_rBtn2:
                    checkedBtn2();
                    break;
                case R.id.type_rBtn3:
                    break;
                case R.id.type_rBtn4:
                    break;
            }
        }
    }

    private void checkedBtn1(){
        DialogUtils.showVipDialog(mContext, new DialogUtils.DialogItemClickListener() {
            @Override
            public void confirm(String result) {

            }
        });
    }

    private void checkedBtn2(){
        Intent intent = new Intent(this, AddLocalServiceActivity.class);
        startActivity(intent);
    }

    private void assignViews() {
        mTitleCenterTxt = (TextView) findViewById(R.id.title_center_txt);
        mTypeGroup = (RadioGroup) findViewById(R.id.type_Group);
        mTypeRBtn1 = (RadioButton) findViewById(R.id.type_rBtn1);
        mTypeRBtn2 = (RadioButton) findViewById(R.id.type_rBtn2);
        mTypeRBtn3 = (RadioButton) findViewById(R.id.type_rBtn3);
        mTypeRBtn4 = (RadioButton) findViewById(R.id.type_rBtn4);
        mAddNewServiceRl = (RelativeLayout) findViewById(R.id.add_new_service_rl);
    }
}
