package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.config.Config;
import com.taiwan.oomatcher.entity.task.TaskDetails;
import com.taiwan.oomatcher.http.HttpUtils;
import com.taiwan.oomatcher.utils.DateUtils;
import com.taiwan.oomatcher.utils.DialogUtils;
import com.taiwan.oomatcher.utils.ImageLoaderUtils;
import com.taiwan.oomatcher.widget.SelectableRoundedImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.lidroid.xutils.http.client.HttpRequest.HttpMethod.GET;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/28 13:50
 */

public class MyTaskDetailsActivity extends BaseActivity{

    private final static int RESULT_EDIT_TASK = 1;
    private Context mContext;
    private RelativeLayout mBottomMainLl;
    private ImageView mIvDateIcon;
    private TextView mTvHaveTime;
    private TextView mOptionTxt;
    private TextView mTaskNameTv;
    private SelectableRoundedImageView mHeadImage;
    private TextView mNicknameTv;
    private TextView mXinTxt;
    private TextView mZanTxt;
    private TextView mLanguageTv;
    private TextView mAddressTv;
    private TextView mProvideTv;
    private TextView mStartYearTxt;
    private TextView mStartHourTxt;
    private TextView mEndYearTxt;
    private TextView mEndHourTxt;
    private TextView mMoneyTxt;
    private TextView mMoneyJisuanTxt;
    private TextView mMeetTxt;
    private TextView mDoTxt;
    private TextView mProvideTxt;
    private TextView mNoteTxt;
    private LinearLayout mPeopleMainLl;
    private LinearLayout mPeopleLl01;
    private SelectableRoundedImageView mPeopleImage01;
    private TextView mPeopleTxt01;
    private LinearLayout mPeopleLl02;
    private SelectableRoundedImageView mPeopleImage02;
    private TextView mPeopleTxt02;
    private TextView mPeopleCountTxt;
    private TextView mPeopleTxt;
    private TextView mDepositTxt;
    private TextView mTaskBirTv;
    private TextView mTitleCenterTxt;

    private int mTaskId;
    /** 1 任务墙， 2  我发布的任务， 3 我加入的任务*/
    private int mType   ;
    private SimpleDateFormat time;
    private SimpleDateFormat year;
    
    private TaskDetails mTaskDetails;
    private String url = "postTask/getIWantTaskDetail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        year = new SimpleDateFormat("MMM-dd yyyy", Locale.ENGLISH);
        time = new SimpleDateFormat("HH : mm");
        mTitleCenterTxt.setText(R.string.task);
        mTitleCenterTxt.setVisibility(View.GONE);
        mTaskId = getIntent().getIntExtra("id", 0);
        mType = getIntent().getIntExtra("type", 0);
        if(mType == 1){
            url = "taskWall/detail";
        }else if(mType == 2){
            url = "postTask/getMyPostTaskDetail";
        }else if(mType == 3){
            url = "postTask/getIWantTaskDetail";
        }
        getTaskDetails(mTaskId);
    }

    private void initValue() {
        mTaskNameTv.setText(mTaskDetails.getTaskName());
        mTaskBirTv.setText(mTaskDetails.getTaskBrief());
        mNicknameTv.setText(mTaskDetails.getNickname());
        mZanTxt.setText(mContext.getString(R.string.my_followers, mTaskDetails.getFollowerCount()));
        mXinTxt.setText(mContext.getString(R.string.my_thumbs, mTaskDetails.getThumbCount()));
        ImageLoaderUtils.loadHead(mContext, mTaskDetails.getFigureUrl(), mHeadImage);
        mStartYearTxt.setText(year.format(new Date(mTaskDetails.getStartTime())));
        mStartHourTxt.setText(time.format(new Date(mTaskDetails.getStartTime())));
        mEndYearTxt.setText(year.format(new Date(mTaskDetails.getEndTime())));
        mEndHourTxt.setText(time.format(new Date(mTaskDetails.getEndTime())));
        mMoneyTxt.setText(mTaskDetails.getTaskAmount()+"");
        mMeetTxt.setText(mTaskDetails.getMeetPoint());
        mDoTxt.setText(mTaskDetails.getWeTodoDesc());
        mProvideTxt.setText(mTaskDetails.getCompanionProvideDesc());
        mNoteTxt.setText(mTaskDetails.getNotes());
        if(mTaskDetails.getNeedDepositeFlag() == 1)
            mDepositTxt.setText(R.string.new_task_deposit2);
        else
            mDepositTxt.setText(R.string.new_task_deposit1);

        if(mTaskDetails.getInterestedCompanionList() != null && mTaskDetails.getInterestedCompanionList().size() > 0){
            mPeopleMainLl.setVisibility(View.VISIBLE);
            mPeopleCountTxt.setText(getResources().getString(R.string.view_all_count, mTaskDetails.getInterestedCompanionList().size()));
            mPeopleTxt01.setText(mTaskDetails.getInterestedCompanionList().get(0).getNickname());
            ImageLoaderUtils.loadHead(mContext, mTaskDetails.getInterestedCompanionList().get(0).getFigureUrl(), mPeopleImage01);
            mPeopleLl01.setVisibility(View.VISIBLE);
            if(mTaskDetails.getInterestedCompanionList().size() > 1){
                mPeopleTxt02.setText(mTaskDetails.getInterestedCompanionList().get(1).getNickname());
                ImageLoaderUtils.loadHead(mContext, mTaskDetails.getInterestedCompanionList().get(1).getFigureUrl(), mPeopleImage02);
                mPeopleLl02.setVisibility(View.VISIBLE);
            }else{
                mPeopleLl02.setVisibility(View.GONE);
            }
        }else{
            mPeopleMainLl.setVisibility(View.GONE);
        }
        mPeopleTxt.setText(getResources().getString(R.string.task_details_people, mTaskDetails.getCompanionQty()));

        if(mTaskDetails.getStartTime() > System.currentTimeMillis()){
            mOptionTxt.setText(R.string.my_task_list_delete);
            mOptionTxt.setTextColor(mContext.getResources().getColor(R.color.text_fense_normal));
        }else{
            mOptionTxt.setText(R.string.my_task_list_edit);
            if(mTaskDetails.getCompanionCount() == 0){
                mOptionTxt.setTextColor(mContext.getResources().getColor(R.color.text_fense_normal));
            }else{
                mOptionTxt.setTextColor(mContext.getResources().getColor(R.color.my_main_bg));
            }
        }
        handler_timeCurrent.sendEmptyMessageDelayed(0,1000);
    }

    private Handler handler_timeCurrent = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            daoJiShi();
            handler_timeCurrent.sendEmptyMessageDelayed(0,1000);
        }
    };

    private void daoJiShi(){
        String[] date = DateUtils.getTaskDate(mTaskDetails.getStartTime());
        mTvHaveTime.setText(mContext.getString(R.string.my_task_list_daojishi, date[0], date[1], date[2], date[3]));
    }

    @Override
    protected void onDestroy() {
        handler_timeCurrent.removeCallbacksAndMessages(null);
        super.onDestroy();
    }


    @Override
    protected void initView() {
        setContentView(R.layout.activity_task_details);
        assignViews();
    }

    public void backClick(View view) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setListeners() {
        mOptionTxt.setOnClickListener(this);
    }

    @Override
    protected void widgetClick(View v) {
        if(v.getId() == mOptionTxt.getId()){
            if(mTaskDetails.getStartTime() > System.currentTimeMillis()){
                deleteTask(mTaskDetails.getId());
                //DialogUtils.showBoxDialog(mContext,getResources().getString((R.string.new_task_require)))
            }else{
                if(mTaskDetails.getCompanionCount() == 0){
                    Intent intent = new Intent(mContext, NewTaskActivity.class);
                    intent.putExtra("id", mTaskDetails.getId());
                    startActivityForResult(intent, RESULT_EDIT_TASK);
                }else{
                    DialogUtils.showRadioDialog(mContext,getResources().getString((R.string.error_edit_task)), new DialogUtils.DialogClickListener(){
                        @Override
                        public void leftClick() {

                        }

                        @Override
                        public void rightClick() {

                        }
                    });
                }
            }
        }
    }

    private void deleteTask(int id) {
        showProgress();
        JSONObject json = new JSONObject();
        json.put("id", id);
        HttpUtils.sendPostSign("postTask/removeMyTask", json, new HttpUtils.CallBackText() {
            @Override
            public void onSuccess(String str) {
                hideProgress();
                Intent data = new Intent();
                setResult(RESULT_OK, data);
                finish();
            }

            @Override
            public void onFailure(Exception e, String msg) {
                hideProgress();
                showToast(msg);
            }
        });
    }

    private void getTaskDetails(int id){
        showProgress();
        JSONObject json = new JSONObject();
        json.put("id", id);
        HttpUtils.sendGetSign(url, json, new HttpUtils.CallBackText() {
            @Override
            public void onSuccess(String str) {
                hideProgress();
                mTaskDetails = JSONObject.parseObject(str, TaskDetails.class);
                if(mTaskDetails != null){
                    initValue();
                }
            }

            @Override
            public void onFailure(Exception e, String msg) {
                hideProgress();
                showToast(msg);
            }
        });
    }

    private void assignViews() {
        mTitleCenterTxt = (TextView) findViewById(R.id.title_center_txt);
        mBottomMainLl = (RelativeLayout) findViewById(R.id.bottom_main_ll);
        mIvDateIcon = (ImageView) findViewById(R.id.iv_date_icon);
        mTvHaveTime = (TextView) findViewById(R.id.tv_have_time);
        mTaskBirTv = (TextView) findViewById(R.id.task_bir_tv);
        mOptionTxt = (TextView) findViewById(R.id.option_txt);
        mTaskNameTv = (TextView) findViewById(R.id.task_name_tv);
        mHeadImage = (SelectableRoundedImageView) findViewById(R.id.head_image);
        mNicknameTv = (TextView) findViewById(R.id.nickname_tv);
        mXinTxt = (TextView) findViewById(R.id.xin_txt);
        mZanTxt = (TextView) findViewById(R.id.zan_txt);
        mLanguageTv = (TextView) findViewById(R.id.language_tv);
        mAddressTv = (TextView) findViewById(R.id.address_tv);
        mProvideTv = (TextView) findViewById(R.id.provide_tv);
        mStartYearTxt = (TextView) findViewById(R.id.start_year_txt);
        mStartHourTxt = (TextView) findViewById(R.id.start_hour_txt);
        mEndYearTxt = (TextView) findViewById(R.id.end_year_txt);
        mEndHourTxt = (TextView) findViewById(R.id.end_hour_txt);
        mMoneyTxt = (TextView) findViewById(R.id.money_txt);
        mMoneyJisuanTxt = (TextView) findViewById(R.id.money_jisuan_txt);
        mMeetTxt = (TextView) findViewById(R.id.meet_txt);
        mDoTxt = (TextView) findViewById(R.id.do_txt);
        mProvideTxt = (TextView) findViewById(R.id.provide_txt);
        mNoteTxt = (TextView) findViewById(R.id.note_txt);
        mPeopleMainLl = (LinearLayout) findViewById(R.id.people_main_ll);
        mPeopleLl01 = (LinearLayout) findViewById(R.id.people_ll_01);
        mPeopleImage01 = (SelectableRoundedImageView) findViewById(R.id.people_image01);
        mPeopleTxt01 = (TextView) findViewById(R.id.people_txt01);
        mPeopleLl02 = (LinearLayout) findViewById(R.id.people_ll_02);
        mPeopleImage02 = (SelectableRoundedImageView) findViewById(R.id.people_image02);
        mPeopleTxt02 = (TextView) findViewById(R.id.people_txt02);
        mPeopleCountTxt = (TextView) findViewById(R.id.people_count_txt);
        mPeopleTxt = (TextView) findViewById(R.id.people_txt);
        mDepositTxt = (TextView) findViewById(R.id.deposit_txt);
    }
}
