package com.taiwan.oomatcher.popup;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.widget.NestRadioGroup;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/24 20:28
 */

public class RentFilterPopup extends PopupWindow {

    private Context mContext;
    private TextView mResetTxt;
    private RadioGroup mSexSelect;
    private RadioButton mFilterTitleSex1;
    private RadioButton mFilterTitleSex2;
    private RadioButton mFilterTitleSex3;
    private NestRadioGroup mTypeSelect;
    private RadioButton mTypeRBtn1;
    private RadioButton mTypeRBtn2;
    private RadioButton mTypeRBtn3;
    private RadioButton mTypeRBtn4;
    private RadioGroup mFromSelect;
    private RadioButton mFromRBtn1;
    private RadioButton mFromRBtn2;
    private RadioButton mFromRBtn3;
    private RadioGroup mAuthSelect;
    private RadioButton mAuthRBtn1;
    private RadioButton mAuthRBtn2;
    private RadioGroup mDepositeSelect;
    private RadioButton mDepositeRBtn1;
    private RadioButton mDepositeRBtn2;
    private RadioButton mDepositeRBtn3;
    private TextView mOkTxt;
    private OnSelectListener mListener;

    public RentFilterPopup(OnSelectListener listener, Context mContext){
        this.mContext = mContext;
        this.mListener = listener;
        //设置可以获得焦点
        setFocusable(true);
        //设置弹窗内可点击
        setTouchable(true);
        //设置弹窗外可点击
        setOutsideTouchable(true);

        //设置弹窗的宽度和高度
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        setBackgroundDrawable(new BitmapDrawable());
        //设置弹窗的布局界面
        setContentView(LayoutInflater.from(mContext).inflate(R.layout.popup_who_i_rent_filter, null));
        assignViews();
        mOkTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mResetTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * 显示弹窗列表界面
     */
    public void show(View view){
//        showAsDropDown(view, 0, -DensityUtils.dp2px(mContext,50));
        showAsDropDown(view, 0, 0);
    }

    public interface OnSelectListener{
        public void selected(String value);
    }

    private void assignViews() {
        mResetTxt = (TextView) getContentView().findViewById(R.id.reset_txt);
        mSexSelect = (RadioGroup) getContentView().findViewById(R.id.sex_select);
        mFilterTitleSex1 = (RadioButton) getContentView().findViewById(R.id.filter_title_sex1);
        mFilterTitleSex2 = (RadioButton) getContentView().findViewById(R.id.filter_title_sex2);
        mFilterTitleSex3 = (RadioButton) getContentView().findViewById(R.id.filter_title_sex3);
        mTypeSelect = (NestRadioGroup) getContentView().findViewById(R.id.type_select);
        mTypeRBtn1 = (RadioButton) getContentView().findViewById(R.id.type_rBtn1);
        mTypeRBtn2 = (RadioButton) getContentView().findViewById(R.id.type_rBtn2);
        mTypeRBtn3 = (RadioButton) getContentView().findViewById(R.id.type_rBtn3);
        mTypeRBtn4 = (RadioButton) getContentView().findViewById(R.id.type_rBtn4);
        mFromSelect = (RadioGroup) getContentView().findViewById(R.id.from_select);
        mFromRBtn1 = (RadioButton) getContentView().findViewById(R.id.from_rBtn1);
        mFromRBtn2 = (RadioButton) getContentView().findViewById(R.id.from_rBtn2);
        mFromRBtn3 = (RadioButton) getContentView().findViewById(R.id.from_rBtn3);
        mAuthSelect = (RadioGroup) getContentView().findViewById(R.id.auth_select);
        mAuthRBtn1 = (RadioButton) getContentView().findViewById(R.id.auth_rBtn1);
        mAuthRBtn2 = (RadioButton) getContentView().findViewById(R.id.auth_rBtn2);
        mDepositeSelect = (RadioGroup) getContentView().findViewById(R.id.deposite_select);
        mDepositeRBtn1 = (RadioButton) getContentView().findViewById(R.id.deposite_rBtn1);
        mDepositeRBtn2 = (RadioButton) getContentView().findViewById(R.id.deposite_rBtn2);
        mDepositeRBtn3 = (RadioButton) getContentView().findViewById(R.id.deposite_rBtn3);
        mOkTxt = (TextView) getContentView().findViewById(R.id.ok_txt);
    }
}