package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.adapter.TaskWallListAdapter;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.entity.MyTask;
import com.taiwan.oomatcher.entity.MyTaskList;
import com.taiwan.oomatcher.http.HttpUtils;
import com.taiwan.oomatcher.widget.SelectableRoundedImageView;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/17 15:44
 */

public class TaskWallActivity extends BaseActivity {

    private final static int RESULT_SORT = 1;
    private final static int RESULT_DETAILS_TASK = 2;
    private Context mContext;
    private SelectableRoundedImageView mHeadImageSri;
    private TextView mCityTxt;
    private ImageView mSearchIcon;
    private EditText mEtSearch;
    private RadioGroup mUserWallTypeGroup;
    private RadioButton mUserWallTypeRBtn1;
    private RadioButton mUserWallTypeRBtn2;
    private RadioButton mUserWallTypeRBtn3;
    private LinearLayout mSortLl;
    private SmartRefreshLayout mRefreshLayout;
    private ListView mListView;

    private int mOrderType = 1;
    private TaskWallListAdapter mAdapter;
    private MyTaskList mMyTaskData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_task_wall);
        assignViews();
    }

    @Override
    protected void init() {
        mAdapter = new TaskWallListAdapter(mContext);
        mListView.setAdapter(mAdapter);
        showProgress();
        getTaskDetails(0);
    }

    private void getTaskDetails(int page){
        String url = "";
        if(mUserWallTypeRBtn1.isChecked()){
            url = "taskWall/list";
        }else if(mUserWallTypeRBtn2.isChecked()){
            url = "taskWall/nearestList";
        }else if(mUserWallTypeRBtn3.isChecked()){
            url = "taskWall/lastestList";
        }
        JSONObject json = new JSONObject();
        json.put("countryCode", 1);
        json.put("cityCode", 1);
        json.put("pageNo", page);
        json.put("pageSize", 10);
        json.put("orderType", mOrderType);
        HttpUtils.sendGetSign(url, json, new HttpUtils.CallBackText() {
            @Override
            public void onSuccess(String str) {
                hideProgress();
                mRefreshLayout.finishRefresh();
                mMyTaskData = JSONObject.parseObject(str, MyTaskList.class);
                if(mMyTaskData.getList() != null && mMyTaskData.getList().size() > 0){
                    mAdapter.refreshAndNotifyData(mMyTaskData.getList());
                    handler_timeCurrent.sendEmptyMessageDelayed(0,1000);
                }
            }

            @Override
            public void onFailure(Exception e, String msg) {
                hideProgress();
                showToast(msg);
            }
        });
    }

    @Override
    protected void setListeners() {
        mSortLl.setOnClickListener(this);
        mSearchIcon.setOnClickListener(this);
        mHeadImageSri.setOnClickListener(this);
        mUserWallTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                showProgress();
                getTaskDetails(1);
            }
        });
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getTaskDetails(1);
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if(mMyTaskData.getPage() == mMyTaskData.getTotalPage()){
                    showToast(R.string.tip_not_more_data);
                    refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示加载失败
                }else{
                    getTaskDetails(mMyTaskData.getPage() + 1);
                }
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyTask item = mAdapter.getItem(position);
                Intent intent = new Intent(mContext, MyTaskDetailsActivity.class);
                intent.putExtra("id", item.getTaskId());
                intent.putExtra("type", 1);
                startActivityForResult(intent, RESULT_DETAILS_TASK);
            }
        });
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
        if(v.getId() == mSortLl.getId()){
            Intent intent = new Intent(mContext, SortTaskWallActivity.class);
            intent.putExtra("orderType", mOrderType);
            startActivityForResult(intent, RESULT_SORT);
        }else if(v.getId() == mHeadImageSri.getId()){
            Intent intent = new Intent(mContext, MyProfileActivity.class);
            startActivity(intent);
        }else if(v.getId() == mSearchIcon.getId()){
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_SORT && resultCode == RESULT_OK){
            mOrderType = data.getIntExtra("orderType", 0);
            showProgress();
            getTaskDetails(1);
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
        mSortLl = (LinearLayout) findViewById(R.id.sort_ll);
        mRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        mListView = (ListView) findViewById(R.id.listView);
    }

}
