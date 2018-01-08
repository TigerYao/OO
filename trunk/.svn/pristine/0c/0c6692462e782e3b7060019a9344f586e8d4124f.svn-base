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
 * 创建日期: 2017/12/28 23:22
 */

public class CompanionListAdapter extends BaseAdapter {
    private ArrayList<MyTask> mList;
    private Context mContext;
    private SimpleDateFormat year;
    private SimpleDateFormat hour;
    private SimpleDateFormat mouth;

    public CompanionListAdapter(Context context) {
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
            convertView = View.inflate(mContext, R.layout.item_companion, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MyTask item = getItem(position);

        return convertView;
    }

    public void refreshAndNotifyData(List<MyTask> beanList) {
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
        public final SelectableRoundedImageView headimage;
        public final TextView paystatustxt;
        public final TextView nicknametxt;
        public final TextView removetxt;
        public final ImageView iconfollower;
        public final TextView tvfollowers;
        public final ImageView iconthumbs;
        public final TextView tvthumbs;
        public final TextView agetxt;
        public final TextView heighttxt;
        public final TextView weighttxt;
        public final LinearLayout inforll01;
        public final TextView occupationtxt;
        public final TextView nationalitytxt;
        public final TextView hairtxt;
        public final LinearLayout inforll02;
        public final TextView eyetxt;
        public final LinearLayout inforll03;
        public final TextView languagetxt;
        public final TextView accepttxt;
        public final ImageView acceptimage;
        public final TextView rentnowtxt;
        public final View root;

        public ViewHolder(View root) {
            headimage = (SelectableRoundedImageView) root.findViewById(R.id.head_image);
            paystatustxt = (TextView) root.findViewById(R.id.pay_status_txt);
            nicknametxt = (TextView) root.findViewById(R.id.nickname_txt);
            removetxt = (TextView) root.findViewById(R.id.remove_txt);
            iconfollower = (ImageView) root.findViewById(R.id.icon_follower);
            tvfollowers = (TextView) root.findViewById(R.id.tv_followers);
            iconthumbs = (ImageView) root.findViewById(R.id.icon_thumbs);
            tvthumbs = (TextView) root.findViewById(R.id.tv_thumbs);
            agetxt = (TextView) root.findViewById(R.id.age_txt);
            heighttxt = (TextView) root.findViewById(R.id.height_txt);
            weighttxt = (TextView) root.findViewById(R.id.weight_txt);
            inforll01 = (LinearLayout) root.findViewById(R.id.infor_ll01);
            occupationtxt = (TextView) root.findViewById(R.id.occupation_txt);
            nationalitytxt = (TextView) root.findViewById(R.id.nationality_txt);
            hairtxt = (TextView) root.findViewById(R.id.hair_txt);
            inforll02 = (LinearLayout) root.findViewById(R.id.infor_ll02);
            eyetxt = (TextView) root.findViewById(R.id.eye_txt);
            inforll03 = (LinearLayout) root.findViewById(R.id.infor_ll03);
            languagetxt = (TextView) root.findViewById(R.id.language_txt);
            accepttxt = (TextView) root.findViewById(R.id.accept_txt);
            acceptimage = (ImageView) root.findViewById(R.id.accept_image);
            rentnowtxt = (TextView) root.findViewById(R.id.rent_now_txt);
            this.root = root;
        }
    }
}
