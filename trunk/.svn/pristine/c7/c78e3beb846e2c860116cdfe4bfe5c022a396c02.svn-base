package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.adapter.CompanionListAdapter;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.entity.MyTask;

import java.util.ArrayList;

/**
 * 作 者: huanghuojun
 * 描 述: 任务陪伴人员列表
 * 版 本:
 * 创建日期: 2017/12/28 23:09
 */

public class CompanionListActivity extends BaseActivity {

    private Context mContext;
    private TextView mRemoveTxt;
    private SmartRefreshLayout mRefreshLayout;
    private ListView mListView;
    private TextView mTitleCenterTxt;
    private CompanionListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        mTitleCenterTxt.setText(R.string.companion_list);
    }

    public void backClick(View view) {
        finish();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_companion_list);
        assignViews();
    }

    @Override
    protected void init() {
        ArrayList<MyTask> mList = new ArrayList<MyTask>();
        mList.add(new MyTask());
        mList.add(new MyTask());
        mList.add(new MyTask());
        mList.add(new MyTask());
        mList.add(new MyTask());
        mList.add(new MyTask());
        mAdapter = new CompanionListAdapter(mContext);
        mListView.setAdapter(mAdapter);
        mAdapter.refreshAndNotifyData(mList);
    }

    @Override
    protected void setListeners() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getListData(1);
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
//                if(mMyTaskData.getPage() == mMyTaskData.getTotalPage()){
//                    showToast(R.string.tip_not_more_data);
//                    refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示加载失败
//                }else{
//                    getListData(mMyTaskData.getPage() + 1);
//                }
            }
        });
    }

    private void getListData(int i) {
    }

    @Override
    protected void widgetClick(View v) {

    }

    private void assignViews() {
        mTitleCenterTxt = (TextView) findViewById(R.id.title_center_txt);
        mRemoveTxt = (TextView) findViewById(R.id.remove_txt);
        mRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        mListView = (ListView) findViewById(R.id.listView);
    }
}
