package com.taiwan.oomatcher.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.entity.MyTask;
import com.taiwan.oomatcher.utils.DateUtils;
import com.taiwan.oomatcher.utils.ImageLoaderUtils;
import com.taiwan.oomatcher.widget.SelectableRoundedImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/27 23:24
 */

public class MyTaskAdapter extends BaseAdapter {
    private ArrayList<MyTask> mList;
    private Context mContext;
    private SimpleDateFormat year;
    private SimpleDateFormat hour;
    private SimpleDateFormat mouth;
    private boolean isPost = true;

    public MyTaskAdapter(Context context) {
        this.mContext = context;
        year = new SimpleDateFormat("yyyy");
        mouth = new SimpleDateFormat("MMM-dd", Locale.ENGLISH);
        hour = new SimpleDateFormat("HH : mm");
        this.mList = new ArrayList<MyTask>();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public MyTask getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_my_task, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MyTask item = getItem(position);
        if(isPost){
            holder.optiontxt.setVisibility(View.VISIBLE);
            holder.llmoredetails.setVisibility(View.VISIBLE);
            holder.llwantdetails.setVisibility(View.GONE);
            holder.counttxt.setText(item.getCompanionCount() + "");
        }else{
            holder.optiontxt.setVisibility(View.GONE);
            holder.llmoredetails.setVisibility(View.GONE);
            holder.llwantdetails.setVisibility(View.VISIBLE);
            holder.moneytxt.setText(item.getTaskAmount() + "K");
            if(item.getTaskStatus() == 0){
                holder.moneystatustxt.setText(R.string.my_task_list_accepted);
            }else{
                holder.moneystatustxt.setText(R.string.my_task_list_waitting_accepted);
            }
        }

        ImageLoaderUtils.loadHead(mContext, item.getFigureUrl(), holder.ivphoto);
        holder.tvfollowers.setText(mContext.getString(R.string.my_followers, item.getFollowerCount()));
        holder.tvthumbs.setText(mContext.getString(R.string.my_thumbs, item.getThumbCount()));
        holder.tvactivitytitle.setText(item.getTaskName());
        holder.tvcontent.setText(item.getTaskBrief());
        holder.tvstartyear.setText(year.format(new Date(item.getStartTime())));
        holder.tvendyear.setText(year.format(new Date(item.getEndTime())));
        holder.tvstartday.setText(mouth.format(new Date(item.getStartTime())));
        holder.tvendday.setText(mouth.format(new Date(item.getEndTime())));
        holder.tvstartmin.setText(hour.format(new Date(item.getStartTime())));
        holder.tvendmin.setText(hour.format(new Date(item.getEndTime())));



        String[] date = DateUtils.getTaskDate(item.getStartTime());
        holder.tvhavetime.setText(mContext.getString(R.string.my_task_list_daojishi, date[0], date[1], date[2], date[3]));

        if(item.getStartTime() > System.currentTimeMillis()){
            holder.optiontxt.setText(R.string.my_task_list_delete);
            holder.optiontxt.setTextColor(mContext.getResources().getColor(R.color.text_fense_normal));
        }else{
            holder.optiontxt.setText(R.string.my_task_list_edit);
            if(item.getCompanionCount() == 0){
                holder.optiontxt.setTextColor(mContext.getResources().getColor(R.color.text_fense_normal));
            }else{
                holder.optiontxt.setTextColor(mContext.getResources().getColor(R.color.my_main_bg));
            }
        }
        return convertView;
    }

    public void refreshAndNotifyData(List<MyTask> beanList, boolean flag) {
        isPost = flag;
        mList.clear();
        addAndNotifyData(beanList);
    }

    public void addAndNotifyData(List<MyTask> beanList) {
        if (beanList != null) {
            mList.addAll(beanList);
        }
        this.notifyDataSetChanged();
    }


    public class ViewHolder {
        public final SelectableRoundedImageView ivphoto;
        public final TextView tvactivitytitle;
        public final TextView tvcontent;
        public final ImageView ivdateicon;
        public final TextView tvhavetime;
        public final ImageView iconfollower;
        public final TextView tvfollowers;
        public final ImageView iconthumbs;
        public final TextView tvthumbs;
        public final TextView tvstartday;
        public final TextView tvstartyear;
        public final TextView tvstartmin;
        public final LinearLayout llstartdatecontent;
        public final TextView tvendday;
        public final TextView tvendyear;
        public final TextView tvendmin;
        public final LinearLayout llendcontent;
        public final TextView tvmeetpointtitle;
        public final TextView tvmeetpoint;
        public final TextView tvaddnow;
        public final TextView tvdistance;
        public final TextView counttxt;
        public final RelativeLayout llmoredetails;
        public final TextView optiontxt;
        public final TextView moneytxt;
        public final TextView moneyfeilvtxt;
        public final TextView moneystatustxt;
        public final RelativeLayout llwantdetails;
        public final View root;

        public ViewHolder(View root) {
            ivphoto = (SelectableRoundedImageView) root.findViewById(R.id.iv_photo);
            tvactivitytitle = (TextView) root.findViewById(R.id.tv_activity_title);
            tvcontent = (TextView) root.findViewById(R.id.tv_content);
            ivdateicon = (ImageView) root.findViewById(R.id.iv_date_icon);
            tvhavetime = (TextView) root.findViewById(R.id.tv_have_time);
            iconfollower = (ImageView) root.findViewById(R.id.icon_follower);
            tvfollowers = (TextView) root.findViewById(R.id.tv_followers);
            iconthumbs = (ImageView) root.findViewById(R.id.icon_thumbs);
            tvthumbs = (TextView) root.findViewById(R.id.tv_thumbs);
            tvstartday = (TextView) root.findViewById(R.id.tv_start_day);
            tvstartyear = (TextView) root.findViewById(R.id.tv_start_year);
            tvstartmin = (TextView) root.findViewById(R.id.tv_start_min);
            llstartdatecontent = (LinearLayout) root.findViewById(R.id.ll_start_date_content);
            tvendday = (TextView) root.findViewById(R.id.tv_end_day);
            tvendyear = (TextView) root.findViewById(R.id.tv_end_year);
            tvendmin = (TextView) root.findViewById(R.id.tv_end_min);
            llendcontent = (LinearLayout) root.findViewById(R.id.ll_end_content);
            tvmeetpointtitle = (TextView) root.findViewById(R.id.tv_meet_point_title);
            tvmeetpoint = (TextView) root.findViewById(R.id.tv_meet_point);
            tvaddnow = (TextView) root.findViewById(R.id.tv_add_now);
            tvdistance = (TextView) root.findViewById(R.id.tv_distance);
            counttxt = (TextView) root.findViewById(R.id.count_txt);
            llmoredetails = (RelativeLayout) root.findViewById(R.id.ll_more_details);
            optiontxt = (TextView) root.findViewById(R.id.option_txt);
            moneytxt = (TextView) root.findViewById(R.id.money_txt);
            moneyfeilvtxt = (TextView) root.findViewById(R.id.money_feilv_txt);
            moneystatustxt = (TextView) root.findViewById(R.id.money_status_txt);
            llwantdetails = (RelativeLayout) root.findViewById(R.id.ll_want_details);
            this.root = root;
        }
    }
}
