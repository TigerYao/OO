package com.taiwan.oomatcher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.entity.TopicInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TigerYao on 2018/1/7.
 */

public class TopicAdapter extends BaseAdapter {
    private List<TopicInfo> mTopics;
    private Context mCtx;
    private List<TopicInfo> mSelectedTopics;

    public TopicAdapter(Context ctx, List<TopicInfo> mTopics) {
        this.mTopics = mTopics;
        mCtx = ctx;
        mSelectedTopics = new ArrayList<>();
    }

    public void updateList(List<TopicInfo> topics) {
        mTopics = topics;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mTopics.size();
    }

    @Override
    public TopicInfo getItem(int i) {
        return mTopics.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).getGoodsId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        CheckBox checkBox = (CheckBox) LayoutInflater.from(mCtx).inflate(R.layout.topic_view, null);
        final TopicInfo info = getItem(position);
        checkBox.setText(info.getGoodsName());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked)
                    mSelectedTopics.add(info);
                else
                    mSelectedTopics.remove(info);
            }
        });
        return checkBox;
    }
}
