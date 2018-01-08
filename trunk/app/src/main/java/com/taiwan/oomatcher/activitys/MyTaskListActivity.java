package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.adapter.MyTaskAdapter;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.config.Config;
import com.taiwan.oomatcher.entity.MyTask;
import com.taiwan.oomatcher.entity.MyTaskList;
import com.taiwan.oomatcher.entity.task.TaskDetails;
import com.taiwan.oomatcher.http.HttpUtils;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/22 01:26
 */

public class MyTaskListActivity extends BaseActivity {
    private final static int RESULT_NEW_TASK = 1;
    private final static int RESULT_DETAILS_TASK = 2;
    private Context mContext;
    private RadioGroup mTypeGroup;
    private RadioButton mRBtn1;
    private RadioButton mRBtn2;
    private RelativeLayout mNewTaskRl;
    private SmartRefreshLayout mRefreshLayout;
    private ListView mListView;
    private TextView mTitleCenterTxt;
    private MyTaskList mMyTaskData;
    private MyTaskAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        mTitleCenterTxt.setText(R.string.my_task_title);
        showProgress();
        getListData(1);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_my_task_list);
        assignViews();
    }

    @Override
    protected void init() {
        mAdapter = new MyTaskAdapter(mContext);
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

    public void backClick(View v){
        finish();
    }

    @Override
    protected void setListeners() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyTask item = mAdapter.getItem(position);
                Intent intent = new Intent(mContext, MyTaskDetailsActivity.class);
                intent.putExtra("id", item.getTaskId());
                int type = 2;
                if(mTypeGroup.getCheckedRadioButtonId() == mRBtn2.getId()){
                    type = 3;
                }
                intent.putExtra("type", type);
                startActivityForResult(intent, RESULT_DETAILS_TASK);
            }
        });
        mNewTaskRl.setOnClickListener(this);
        mTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                showProgress();
                getListData(1);
            }
        });
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getListData(1);
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if(mMyTaskData.getPage() == mMyTaskData.getTotalPage()){
                    showToast(R.string.tip_not_more_data);
                    refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示加载失败
                }else{
                    getListData(mMyTaskData.getPage() + 1);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_NEW_TASK && resultCode == RESULT_OK){
            getListData(1);
        }
    }

    private Handler handler_timeCurrent = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mAdapter.notifyDataSetChanged();
            handler_timeCurrent.sendEmptyMessageDelayed(0,1000);
        }
    };

    @Override
    protected void onDestroy() {
        handler_timeCurrent.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override
    protected void widgetClick(View v) {
        if(v.getId() == mNewTaskRl.getId()){
            Intent intent = new Intent(mContext, NewTaskActivity.class);
            startActivityForResult(intent, RESULT_NEW_TASK);
        }
    }

    private void getListData(int pageNum){
        JSONObject json = new JSONObject();
        json.put("pageNum", pageNum);
        String url = "postTask/getMyPostTaskList";
        if(mTypeGroup.getCheckedRadioButtonId() == mRBtn2.getId()){
            url = "postTask/getIWantTaskList";
        }
        HttpUtils.sendGetSign(url, json, new HttpUtils.CallBackText() {
            @Override
            public void onSuccess(String str) {
                hideProgress();
                mRefreshLayout.finishRefresh();
                mMyTaskData = JSONObject.parseObject(str, MyTaskList.class);
                if(mMyTaskData.getList() != null && mMyTaskData.getList().size() > 0){
                    boolean flag = true;
                    if(mTypeGroup.getCheckedRadioButtonId() == mRBtn2.getId()){
                        flag = false;
                    }
                    mAdapter.refreshAndNotifyData(mMyTaskData.getList(), flag);
                    handler_timeCurrent.sendEmptyMessageDelayed(0,1000);
                }
            }

            @Override
            public void onFailure(Exception e, String msg) {
                hideProgress();
                mRefreshLayout.finishRefresh();
                showToast(msg);
            }
        });
    }

    private void assignViews() {
        mTypeGroup = (RadioGroup) findViewById(R.id.type_Group);
        mRBtn1 = (RadioButton) findViewById(R.id.rBtn1);
        mRBtn2 = (RadioButton) findViewById(R.id.rBtn2);
        mNewTaskRl = (RelativeLayout) findViewById(R.id.new_task_rl);
        mRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        mListView = (ListView) findViewById(R.id.listView);
        mTitleCenterTxt = (TextView) findViewById(R.id.title_center_txt);
    }
}
