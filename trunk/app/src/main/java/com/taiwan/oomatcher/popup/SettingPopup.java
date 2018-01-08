package com.taiwan.oomatcher.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.utils.DensityUtils;
import com.taiwan.oomatcher.utils.ScreenUtils;

import static android.R.attr.gravity;
import static android.R.attr.x;
import static android.R.attr.y;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/24 11:52
 */

public class SettingPopup extends PopupWindow {

    private Context mContext;
    private TextView mTextView1, mTextView2, mTextView3, mTextView4;
    private ImageView mCloseImg;

    public SettingPopup(Context mContext){
        this.mContext = mContext;
        //设置可以获得焦点
        setFocusable(true);
        //设置弹窗内可点击
        setTouchable(true);
        //设置弹窗外可点击
        setOutsideTouchable(true);

        //设置弹窗的宽度和高度
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        setBackgroundDrawable(dw);
        setAnimationStyle(R.style.AnimationLeftFade);
        //设置弹窗的布局界面
        //设置背景半透明
        setContentView(LayoutInflater.from(mContext).inflate(R.layout.popup_setting, null));
        mCloseImg = (ImageView) getContentView().findViewById(R.id.close_img);
        mTextView1 = (TextView) getContentView().findViewById(R.id.language01);
        mTextView2 = (TextView) getContentView().findViewById(R.id.language02);
        mTextView3 = (TextView) getContentView().findViewById(R.id.language03);
        mTextView4 = (TextView) getContentView().findViewById(R.id.language04);
        mCloseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTextView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTextView4.setOnClickListener(new View.OnClickListener() {
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
        final int screenHeight = ScreenUtils.getScreenHeight(mContext);
        showAsDropDown(view, 0, -DensityUtils.dp2px(mContext,44));
        // 计算contentView的高宽
//        showAtLocation(view, Gravity.LEFT, 0, -(screenHeight / 2 - DensityUtils.dp2px(mContext,100)));
    }

    public interface OnSelectListener{
        public void selected(String value);
    }
}