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
import com.taiwan.oomatcher.utils.DensityUtils;

import static android.R.attr.manageSpaceActivity;
import static android.R.attr.type;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/18 18:10
 */

public class SelectLanguagePopup extends PopupWindow {

    private Context mContext;
    private ListView mListView;
    private OnSelectLanguageListener mListener;
    private SelectLanguageAdapter mAdapter;

    public SelectLanguagePopup(OnSelectLanguageListener listener, Context mContext){
        this.mContext = mContext;
        this.mListener = listener;
        mAdapter = new SelectLanguageAdapter(mContext);
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
        setContentView(LayoutInflater.from(mContext).inflate(R.layout.popup_select_language, null));
        mListView = (ListView) getContentView().findViewById(R.id.listView);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.selected(mAdapter.getItem(position).getLanguageName(), mAdapter.getItem(position).getLanguageCode());
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

    public interface OnSelectLanguageListener{
        public void selected(String languageName, String languageCode);
    }
}
