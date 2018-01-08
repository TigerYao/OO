package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.adapter.UserWatkAdapter;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.widget.SelectableRoundedImageView;

import static com.taiwan.oomatcher.R.id.listView;
import static com.taiwan.oomatcher.R.id.refreshLayout;

/**
 * 作 者: huanghuojun
 * 描 述: 用户墙
 * 版 本:
 * 创建日期: 2017/12/17 15:44
 */

public class UserWallActivity extends BaseActivity {

    private Context mContext;
    private SelectableRoundedImageView mHeadImageSri;
    private TextView mCityTxt;
    private ImageView mSearchIcon;
    private EditText mEtSearch;
    private RadioGroup mUserWallTypeGroup;
    private RadioButton mUserWallTypeRBtn1;
    private RadioButton mUserWallTypeRBtn2;
    private RadioButton mUserWallTypeRBtn3;
    private RadioButton mUserWallTypeRBtn4;
    private RadioButton mUserWallTypeRBtn5;
    private LinearLayout mFilterLl;
    private LinearLayout mSortLl;
    private SmartRefreshLayout mRefreshLayout;
    private ListView mListView;


    private UserWatkAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);

        mAdapter = new UserWatkAdapter(mContext);
        mListView.setAdapter(mAdapter);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示加载失败
            }
        });

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_user_wall);
        assignViews();
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setListeners() {
        mFilterLl.setOnClickListener(this);
        mHeadImageSri.setOnClickListener(this);
    }

    @Override
    protected void widgetClick(View v) {
        if(v.getId() == mFilterLl.getId()){
            Intent intent = new Intent(mContext, UserTaskFilterActivity.class);
            startActivity(intent);
        }else if(v.getId() == mHeadImageSri.getId()){
            Intent intent = new Intent(mContext, MyProfileActivity.class);
            startActivity(intent);
        }
    }

    private void assignViews() {
        mHeadImageSri = (SelectableRoundedImageView) findViewById(R.id.head_image_sri);
        mCityTxt = (TextView) findViewById(R.id.city_txt);
        mSearchIcon = (ImageView) findViewById(R.id.search_icon);
        mEtSearch = (EditText) findViewById(R.id.et_search);
        mUserWallTypeGroup = (RadioGroup) findViewById(R.id.user_wall_type_Group);
        mUserWallTypeRBtn1 = (RadioButton) findViewById(R.id.user_wall_type_rBtn1);
        mUserWallTypeRBtn2 = (RadioButton) findViewById(R.id.user_wall_type_rBtn2);
        mUserWallTypeRBtn3 = (RadioButton) findViewById(R.id.user_wall_type_rBtn3);
        mUserWallTypeRBtn4 = (RadioButton) findViewById(R.id.user_wall_type_rBtn4);
        mUserWallTypeRBtn5 = (RadioButton) findViewById(R.id.user_wall_type_rBtn5);
        mFilterLl = (LinearLayout) findViewById(R.id.filter_ll);
        mSortLl = (LinearLayout) findViewById(R.id.sort_ll);
        mRefreshLayout = (SmartRefreshLayout) findViewById(refreshLayout);
        mListView = (ListView) findViewById(R.id.listView);
    }

}
