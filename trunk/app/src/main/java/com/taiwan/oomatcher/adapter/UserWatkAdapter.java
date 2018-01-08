package com.taiwan.oomatcher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.taiwan.oomatcher.R;


/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/23 21:39
 */

public class UserWatkAdapter extends BaseAdapter{
    private String[] mArray;
    private Context mContext;
    private LayoutInflater inflater;

    public UserWatkAdapter(Context context) {
        mContext = context;
        this.inflater = LayoutInflater.from(mContext);
        String[] array1 = new String[10];
        for (int i = 0; i < array1.length; i++) {
            array1[i] = "string " + i;
        }
        mArray = array1;
    }

    @Override
    public int getCount() {
        return mArray.length;
    }

    @Override
    public String getItem(int position) {
        return mArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_user_task, null);
            holder.mTextView = (TextView) convertView.findViewById(R.id.nickname_txt);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTextView.setText(getItem(position));
        return convertView;
    }

    private class ViewHolder {
        TextView mTextView;
    }
}
