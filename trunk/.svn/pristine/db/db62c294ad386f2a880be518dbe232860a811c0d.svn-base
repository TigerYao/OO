package com.taiwan.oomatcher.popup;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.entity.Implementation;

import java.util.List;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/18 18:10
 */

public class SelectImplementationPopup extends PopupWindow {

    private Context mContext;
    private ListView mListView;
    private OnSelectImplListener mListener;
    private List<Implementation> mDataList;
    private String mDicCode;

    public SelectImplementationPopup(String dicCode, OnSelectImplListener listener, List<Implementation> dataList, Context mContext){
        this.mContext = mContext;
        this.mListener = listener;
        this.mDataList = dataList;
        this.mDicCode = dicCode;
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
        setContentView(LayoutInflater.from(mContext).inflate(R.layout.popup_select_implementation, null));
        mListView = (ListView) getContentView().findViewById(R.id.listview);
        final SelectImplementAdapter mAdapter = new SelectImplementAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Implementation item = mAdapter.getItem(position);
                mListener.selected(mDicCode, item);
                dismiss();
            }
        });
    }

    /**
     * 显示弹窗列表界面
     */
    public void show(View view){
        showAsDropDown(view, 0, 0);
    }

    public interface OnSelectImplListener{
        public void selected(String dicCode, Implementation item);
    }

    class SelectImplementAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        public SelectImplementAdapter(){
            this.inflater = LayoutInflater.from(mContext);
        }
        @Override
        public int getCount() {
            return mDataList.size();
        }

        @Override
        public Implementation getItem(int position) {
            return mDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_select_im, null);
                holder.content = (TextView) convertView.findViewById(R.id.content);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.content.setText(getItem(position).getDicName());
            return convertView;
        }
        private class ViewHolder{
            TextView content;
        }

    }
}
