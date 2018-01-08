package com.taiwan.oomatcher.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/21 15:53
 */

public class FilterDateAdapter extends BaseAdapter {

    private ArrayList<Date> dataList = new ArrayList<Date>();
    private int mStartPosition = -1;
    private int mFinishPosition = -1;
    private LayoutInflater inflater;
    private Context mContext;
    private SimpleDateFormat format = new SimpleDateFormat("dd");

    public int getStartPosition() {
        return mStartPosition;
    }

    public void setFinishPosition(int mFinishPosition) {
        if(mFinishPosition > 0 && mStartPosition > mFinishPosition){
            int temp = mStartPosition;
            mStartPosition = mFinishPosition;
            mFinishPosition = temp;
        }
        this.mFinishPosition = mFinishPosition;
    }

    public int getFinishPosition() {
        return mFinishPosition;
    }

    public void setStartPosition(int mStartPosition) {
        this.mStartPosition = mStartPosition;
    }

    public FilterDateAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    public ArrayList<Date> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<Date> dataList) {
        this.dataList = dataList;
    }

    public Date getLastDate() {
        if(dataList.size() > 0)
            return dataList.get(dataList.size() - 1);
        return null;
    }
    public Date getFristDate() {
        if(dataList.size() > 0)
            return dataList.get(0);
        return null;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Date getItem(int position) {
        return dataList.get(position);
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
            convertView = inflater.inflate(R.layout.item_new_task, null);
            holder.mDateText = (TextView) convertView.findViewById(R.id.date);
            holder.mBgImg = (ImageView) convertView.findViewById(R.id.bg);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Date item = dataList.get(position);
        int temp = DateUtils.cpmpareDate(item, new Date());
        if(temp < 0){
            holder.mDateText.setTextColor(mContext.getResources().getColor(R.color.login_tip_color));
            holder.mBgImg.setBackgroundColor(Color.TRANSPARENT);
        }else{
            if(mFinishPosition < 0 && position == mStartPosition){
                holder.mDateText.setTextColor(mContext.getResources().getColor(R.color.white));
                holder.mBgImg.setBackgroundResource(R.drawable.date_simgle);
            }else{
                if (position == mStartPosition) {
                    holder.mDateText.setTextColor(mContext.getResources().getColor(R.color.white));
                    holder.mBgImg.setBackgroundResource(R.drawable.date_left);
                } else if(position > mStartPosition && position < mFinishPosition){
                    holder.mDateText.setTextColor(mContext.getResources().getColor(R.color.white));
                    holder.mBgImg.setBackgroundResource(R.drawable.date_center);
                } else if(position == mFinishPosition){
                    holder.mDateText.setTextColor(mContext.getResources().getColor(R.color.white));
                    holder.mBgImg.setBackgroundResource(R.drawable.date_right);
                }else {
                    holder.mDateText.setTextColor(mContext.getResources().getColor(R.color.black));
                    holder.mBgImg.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        }
        holder.mDateText.setText(format.format(item));
        return convertView;
    }
    private class ViewHolder{
        TextView mDateText;
        ImageView mBgImg;
    }

}
