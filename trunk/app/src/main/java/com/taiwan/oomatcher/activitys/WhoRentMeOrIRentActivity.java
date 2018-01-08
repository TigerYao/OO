package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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
import com.taiwan.oomatcher.adapter.MyTaskAdapter;
import com.taiwan.oomatcher.adapter.WhoRentMeOrIRentAdapter;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.entity.MyTask;
import com.taiwan.oomatcher.entity.MyTaskList;
import com.taiwan.oomatcher.http.HttpUtils;
import com.taiwan.oomatcher.popup.RentFilterPopup;

import static android.R.attr.type;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2018/1/1 22:24
 */

public class WhoRentMeOrIRentActivity extends BaseActivity {

    private static final int RESULT_SORT = 1;
    private Context mContext;
    private RadioGroup mTypeGroup;
    private RadioButton mTypeRBtn1;
    private RadioButton mTypeRBtn2;
    private LinearLayout mFilterLl;
    private LinearLayout mSortLl;
    private SmartRefreshLayout mRefreshLayout;
    private ListView mListView;
    private TextView mTitleCenterTxt;
    private WhoRentMeOrIRentAdapter mAdapter;
    //即将开始类型:1.我约了谁;2.谁约了我
    private int mTypeId = 1;
    private MyTaskList mMyTaskData;
    private RentFilterPopup mPopup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        mTypeId = getIntent().getIntExtra("type", 1);
        if(mTypeId == 1){
            mTitleCenterTxt.setText(R.string.who_i_rent);
        }else{
            mTitleCenterTxt.setText(R.string.who_rent_me);
        }
        showProgress();
        getListData(1);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_whorent_or_irent);
        assignViews();
    }

    @Override
    protected void init() {
        mAdapter = new WhoRentMeOrIRentAdapter(mContext);
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
                intent.putExtra("type", mTypeId);
                startActivity(intent);
            }
        });
        mSortLl.setOnClickListener(this);
        mFilterLl.setOnClickListener(this);
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

    private RentFilterPopup.OnSelectListener pupopListener = new RentFilterPopup.OnSelectListener() {
        @Override
        public void selected(String value) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_SORT && resultCode == RESULT_OK){

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
        if(v.getId() == mFilterLl.getId()){
            if(mPopup == null)
                mPopup = new RentFilterPopup(pupopListener, mContext);
            mPopup.show(mFilterLl);
        }else  if(v.getId() == mSortLl.getId()){
            Intent intent = new Intent(mContext, SortTaskWallActivity.class);
            startActivityForResult(intent, RESULT_SORT);
        }
    }

    private void getListData(int pageNum){
        JSONObject json = new JSONObject();
        json.put("pageNum", pageNum);
        json.put("typeId", mTypeId);
        String url = "user/myAppointment/upcomming";
        if(mTypeGroup.getCheckedRadioButtonId() == mTypeRBtn2.getId()){
            url = "user/myAppointment/history";
        }
        HttpUtils.sendGetSign(url, json, new HttpUtils.CallBackText() {
            @Override
            public void onSuccess(String str) {
                hideProgress();
                mRefreshLayout.finishRefresh();
                mMyTaskData = JSONObject.parseObject(str, MyTaskList.class);
                if(mMyTaskData.getList() != null && mMyTaskData.getList().size() > 0){
                    mAdapter.refreshAndNotifyData(mMyTaskData.getList(), mTypeId);
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
        mTitleCenterTxt = (TextView) findViewById(R.id.title_center_txt);
        mTypeGroup = (RadioGroup) findViewById(R.id.type_Group);
        mTypeRBtn1 = (RadioButton) findViewById(R.id.type_rBtn1);
        mTypeRBtn2 = (RadioButton) findViewById(R.id.type_rBtn2);
        mFilterLl = (LinearLayout) findViewById(R.id.filter_ll);
        mSortLl = (LinearLayout) findViewById(R.id.sort_ll);
        mRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        mListView = (ListView) findViewById(R.id.listView);
    }
}
