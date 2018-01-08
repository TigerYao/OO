package com.taiwan.oomatcher.popup;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.adapter.SelectLanguageAdapter;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/24 01:39
 */

public class NewTaskPopup  extends PopupWindow {

    private Context mContext;
    private TextView mTextView1, mTextView2;
    private OnSelectListener mListener;

    public NewTaskPopup(final OnSelectListener listener, Context mContext){
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
        setContentView(LayoutInflater.from(mContext).inflate(R.layout.popup_select_newtask, null));
        mTextView1 = (TextView) getContentView().findViewById(R.id.language01);
        mTextView2 = (TextView) getContentView().findViewById(R.id.language02);
        mTextView1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  listener.selected(mTextView1.getText().toString());
                  dismiss();
              }
        });
        mTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.selected(mTextView2.getText().toString());
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
}