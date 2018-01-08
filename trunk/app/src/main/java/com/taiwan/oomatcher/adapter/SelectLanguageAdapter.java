package com.taiwan.oomatcher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.entity.LanguageType;
import com.taiwan.oomatcher.utils.SPUtils;
import com.taiwan.oomatcher.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/24 01:01
 */

public class SelectLanguageAdapter  extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<LanguageType> dataList = new ArrayList<LanguageType>();

    public SelectLanguageAdapter(Context context) {
        String languages = (String) SPUtils.get(SPUtils.LANGUAGES, "");
        if(!Tools.isEmpty(languages)){
            List<LanguageType> temp = JSONArray.parseArray(languages, LanguageType.class);
            if(temp != null){
                dataList.addAll(temp);
            }
        }
        mContext = context;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public LanguageType getItem(int position) {
        return dataList.get(position);
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
            convertView = inflater.inflate(R.layout.item_select_language, null);
            holder.mTextView = (TextView) convertView.findViewById(R.id.language);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTextView.setText(getItem(position).getLanguageName());
        return convertView;
    }

    private class ViewHolder {
        TextView mTextView;
    }
}
