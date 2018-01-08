package com.taiwan.oomatcher.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.adapter.TopicAdapter;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.entity.TopicInfo;
import com.taiwan.oomatcher.http.HttpUtils;
import com.taiwan.oomatcher.widget.FlowLayout;
import com.taiwan.oomatcher.widget.GridDirectView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddLocalServiceActivity extends BaseActivity {
    private GridDirectView mTopicsView;
    private List<TopicInfo> mTopics;
    private RadioGroup mRadioGroup;
    private TopicAdapter mAdapter;
    private final static String TOPIC_URL = "/goods/hireSetting/getServiceTopicList";
    public static final int NORMAL_TYPE = 1;
    public static final int PROFESSIONAL_TYPE = 2;
    public static final int SERVICE_LOCAL_TYPE = 1;
    public static final int SERVICE_ONLINE_TYPE = 2;
    public static final int SERVICE_FOREIGN_TYPE = 3;
    public static final int SERVICE_NOW_TYPE = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mTopics = Arrays.asList(getResources().getStringArray(R.array.normal_offer_service_topic));
        getTopics(NORMAL_TYPE, SERVICE_LOCAL_TYPE);
    }

//    public void addTopicView(String name) {
//
//        TextView mTextView = new TextView(this);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.setMargins(0, 0, 10, 5);
//        mTextView.setLayoutParams(params);
//        mTextView.setBackgroundResource(R.drawable.btn_circle_shape);
//        mTextView.setText(name + "  ×");
//        mTextView.setTextColor(Color.parseColor("#ffffff"));
//        mTextView.setTextSize(12f);
//
//        mFlowLayout.addView(mTextView);
//    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_add_local_service);
        mTopicsView = (GridDirectView) findViewById(R.id.topics_grid);
        mRadioGroup = (RadioGroup) findViewById(R.id.topic_type_view);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id){
                    case R.id.topic_normal:
                        break;
                    case R.id.topic_professional:
                        break;
                }
            }
        });
    }

    /**
     *
     * @param topicType 1.普通;2.专业服务
     * @param serviceType 1.本地服务;2.在线服务;3.境外服务:4.当天即刻约服务
     */
    private void getTopics(int topicType, int serviceType) {
        JSONObject json = new JSONObject();
        json.put("professionTypeId", topicType);
        json.put("goodsTypeId", serviceType);
        HttpUtils.sendGetSign(TOPIC_URL, json, new HttpUtils.CallBackText() {
            @Override
            public void onSuccess(String responseData) {
                if(TextUtils.isEmpty(responseData)) return;
                mTopics = JSONObject.parseArray(responseData, TopicInfo.class);
                if (mAdapter == null) {
                    mAdapter = new TopicAdapter(AddLocalServiceActivity.this, mTopics);
                    mTopicsView.setAdapter(mAdapter);
                } else
                    mAdapter.updateList(mTopics);
            }

            @Override
            public void onFailure(Exception e, String msg) {

            }
        });
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void widgetClick(View v) {

    }
}
