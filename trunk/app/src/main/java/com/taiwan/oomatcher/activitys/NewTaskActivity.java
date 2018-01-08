package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

import com.alibaba.fastjson.JSONObject;
import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.adapter.NewTaskDateAdapter;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.config.Config;
import com.taiwan.oomatcher.entity.Request.CompanionRequirmentRequest;
import com.taiwan.oomatcher.entity.task.TaskDetails;
import com.taiwan.oomatcher.http.HttpUtils;
import com.taiwan.oomatcher.popup.NewTaskPopup;
import com.taiwan.oomatcher.popup.TimePopupWindow;
import com.taiwan.oomatcher.utils.DateUtils;
import com.taiwan.oomatcher.utils.ImageLoaderUtils;
import com.taiwan.oomatcher.utils.Tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.taiwan.oomatcher.utils.DateUtils.getDayHour;
import static com.taiwan.oomatcher.utils.DateUtils.getNextDayList;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/21 15:32
 */
public class NewTaskActivity extends BaseActivity implements OnGestureListener {

    private static final int SHOW_DATE_COUNT = 20;
    private static final int Result_Add_Com = 1;
    private Context mContext;
    private TextView mTitleCenterTxt;
    private EditText mTask_nameEdit;
    private EditText mTask_provideEdit;
    private EditText mTask_meetEdit;
    private ViewFlipper mFlipper1ViewFlipper;
    private ToggleButton mPayToggleButton;
    private TextView mStarttimeTV;
    private RadioGroup mNew_task_typeRGroup;
    private EditText mTask_briefEdit;
    private TextView mTask_time_yearTV;
    private ImageView mLanguage_addImgV;
    private TextView mFinishtimeTV;
    private EditText mTask_notesEdit;
    private EditText mMoneyEdit;
    private TextView mPostTV;
    private EditText mTask_doEdit;
    private ToggleButton mPlaceToggleButton;
    private RadioButton mNew_task1RBtn;
    private RadioButton mNew_task2RBtn;
    private TextView mPeopleCountTV;
    private ImageView mPeopleJianImgV;
    private ImageView mPeopleJiaImgV;
    private RelativeLayout mDependsRl;
    private EditText mWallShowDayEdit;
    private EditText mWallShowHourEdit;

    private TextView mDependsTV;
    private NewTaskPopup mPopup;
    private GestureDetector mGestureDetector = null;
    private GridView mGridView = null;
    private NewTaskDateAdapter mNewTaskDateAdapter;
    private Date mMondayDate;
    private Date mStartDate;
    private Date mFinishDate;
    private TimePopupWindow pwTime;
    private boolean isSelectStart = true;
    private SimpleDateFormat mTimeFormat = null;
    private CompanionRequirmentRequest mCompanionRequirmentRequest = null;
    private LinearLayout mFinishTimeLl;
    private LinearLayout mStartTimeLl;

    private TaskDetails mTaskDetails;
    private int mTaskId;
    private String url = "postTask/getIWantTaskDetail";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        mTitleCenterTxt.setText(R.string.new_task);
        mTaskId = getIntent().getIntExtra("id", 0);
        if(mTaskId != 0){
            boolean isPost = getIntent().getBooleanExtra("post", true);
            if(isPost){
                url = "postTask/getMyPostTaskDetail";
            }
            getTaskDetails(mTaskId);
        }
    }

    public void backClick(View v){
        Intent data = new Intent();
        setResult(RESULT_CANCELED, data);
        finish();
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

    private void initValue() {
        mCompanionRequirmentRequest = mTaskDetails.getCompanionRequirment();
        mTask_nameEdit.setText(mTaskDetails.getTaskName());
        mTask_briefEdit.setText(mTaskDetails.getTaskBrief());
        if(mTaskDetails.getProfessionTypeId() == 1)
            mNew_task1RBtn.setChecked(true);
        else
            mNew_task2RBtn.setChecked(true);
        mStartDate = new Date(mTaskDetails.getStartTime());
        mFinishDate = new Date(mTaskDetails.getEndTime());
        mStarttimeTV.setText(mTimeFormat.format(new Date(mTaskDetails.getStartTime())));
        mFinishtimeTV.setText(mTimeFormat.format(new Date(mTaskDetails.getEndTime())));

        mNewTaskDateAdapter = new NewTaskDateAdapter(mContext);
        mMondayDate = DateUtils.getCurrentMonday(mStartDate);
        ArrayList<Date> list = getNextDayList(mMondayDate, SHOW_DATE_COUNT);
        addGridView();
        mGridView.setAdapter(mNewTaskDateAdapter);
        mGridView.setOnItemClickListener(DateClick);
        mFlipper1ViewFlipper.addView(mGridView, 0);
        mNewTaskDateAdapter.setDataList(list);
        mNewTaskDateAdapter.notifyDataSetChanged();

        mTask_meetEdit.setText(mTaskDetails.getMeetPoint());
        if(mTaskDetails.getProvideStayPlaceFlag() == 1)
            mPlaceToggleButton.setChecked(true);
        else
            mPlaceToggleButton.setChecked(false);
        if(mTaskDetails.getPayTravelFeeFlag() == 1)
            mPayToggleButton.setChecked(true);
        else
            mPayToggleButton.setChecked(false);
        mMoneyEdit.setText(mTaskDetails.getTaskAmount()+"");
        mTask_doEdit.setText(mTaskDetails.getWeTodoDesc());
        mTask_provideEdit.setText(mTaskDetails.getCompanionProvideDesc());
        mTask_notesEdit.setText(mTaskDetails.getNotes());

        mPeopleCountTV.setText(mTaskDetails.getCompanionQty()+"");
        mWallShowDayEdit.setText(mTaskDetails.getWallShowDay()+"");
        mWallShowHourEdit.setText(mTaskDetails.getWallShowHour()+"");

        if(mTaskDetails.getNeedDepositeFlag() == 1)
            mDependsTV.setText(R.string.new_task_deposit2);
        else
            mDependsTV.setText(R.string.new_task_deposit1);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_new_task);
        mTask_nameEdit = (EditText) findViewById(R.id.task_name);
        mTask_provideEdit = (EditText) findViewById(R.id.task_provide);
        mTask_meetEdit = (EditText) findViewById(R.id.task_meet);
        mFlipper1ViewFlipper = (ViewFlipper) findViewById(R.id.flipper1);
        mPayToggleButton = (ToggleButton) findViewById(R.id.pay);
        mStarttimeTV = (TextView) findViewById(R.id.starttime);
        mNew_task_typeRGroup = (RadioGroup) findViewById(R.id.new_task_type);
        mTask_briefEdit = (EditText) findViewById(R.id.task_brief);
        mTask_time_yearTV = (TextView) findViewById(R.id.task_time_year);
        mLanguage_addImgV = (ImageView) findViewById(R.id.language_add);
        mFinishtimeTV = (TextView) findViewById(R.id.finishtime);
        mTask_notesEdit = (EditText) findViewById(R.id.task_notes);
        mMoneyEdit = (EditText) findViewById(R.id.money);
        mPostTV = (TextView) findViewById(R.id.post);
        mTask_doEdit = (EditText) findViewById(R.id.task_do);
        mPlaceToggleButton = (ToggleButton) findViewById(R.id.place);
        mNew_task1RBtn = (RadioButton) findViewById(R.id.new_task1);
        mNew_task2RBtn = (RadioButton) findViewById(R.id.new_task2);
        mPeopleCountTV  = (TextView) findViewById(R.id.people_count);
        mPeopleJianImgV = (ImageView) findViewById(R.id.people_jian);
        mPeopleJiaImgV = (ImageView) findViewById(R.id.people_jia);
        mDependsRl = (RelativeLayout) findViewById(R.id.depends_rl);
        mDependsTV = (TextView) findViewById(R.id.depends_txt);
        mWallShowDayEdit = (EditText) findViewById(R.id.wallShowDay);
        mWallShowHourEdit = (EditText) findViewById(R.id.wallShowHour);
        mStartTimeLl = (LinearLayout) findViewById(R.id.start_time);
        mFinishTimeLl = (LinearLayout) findViewById(R.id.finish_time);
        mTitleCenterTxt = (TextView) findViewById(R.id.title_center_txt);
    }

    @Override
    protected void init() {
        mTimeFormat = new SimpleDateFormat("HH:mm");
        mGestureDetector = new GestureDetector(this);
        pwTime = new TimePopupWindow(mContext, TimePopupWindow.Type.HOURS_MINS);
        mNewTaskDateAdapter = new NewTaskDateAdapter(mContext);
        mMondayDate = DateUtils.getCurrentMonday(new Date());
        ArrayList<Date> list = getNextDayList(mMondayDate, SHOW_DATE_COUNT);
        addGridView();
        mGridView.setAdapter(mNewTaskDateAdapter);
        mGridView.setOnItemClickListener(DateClick);
        mFlipper1ViewFlipper.addView(mGridView, 0);
        mNewTaskDateAdapter.setDataList(list);
        mNewTaskDateAdapter.notifyDataSetChanged();
    }

    private void sendPost(){
        if(Tools.isEmpty(mTask_nameEdit)){
            showToast(R.string.error_new_task_name);
            return;
        }
        if(Tools.isEmpty(mTask_briefEdit)){
            showToast(R.string.error_new_task_brief);
            return;
        }
        if(mStartDate == null || Tools.isEmpty(mStarttimeTV) || Tools.isEmpty(mFinishtimeTV) ){
            showToast(R.string.error_new_task_time);
            return;
        }
        if(Tools.isEmpty(mTask_meetEdit)){
            showToast(R.string.error_new_task_meet);
            return;
        }
        if(Tools.isEmpty(mMoneyEdit)){
            showToast(R.string.error_new_task_money);
            return;
        }if(Tools.isEmpty(mTask_doEdit)){
            showToast(R.string.error_new_task_do);
            return;
        }if(Tools.isEmpty(mTask_provideEdit)){
            showToast(R.string.error_new_task_companion_p);
            return;
        }
        if(Tools.isEmpty(mTask_notesEdit)){
            showToast(R.string.error_new_task_notes);
            return;
        }
        if(mCompanionRequirmentRequest == null){
            showToast(R.string.error_new_task_companion);
            return;
        }
        showProgress();
        JSONObject json = new JSONObject();
        String url = "postTask/pushTask";
        if(mTaskId != 0){
            json.put("id", mTaskId);
            url = "postTask/editTask";
        }
        int id = mNew_task_typeRGroup.getCheckedRadioButtonId();
        if(id == R.id.new_task1)
            json.put("professionTypeId", 1);
        else
            json.put("professionTypeId", 2);
        json.put("taskTypeId", 1);
        json.put("taskName", mTask_nameEdit.getText().toString());
        json.put("taskBrief", mTask_briefEdit.getText().toString());

        Date tempStartDate = DateUtils.getTaskDate(mStartDate, mStarttimeTV.getText().toString());
        json.put("startTime",  tempStartDate.getTime()+"");
        Date tempFinish = mFinishDate;
        if(mFinishDate == null){
            tempFinish = mStartDate;
        }
        json.put("endTime",  DateUtils.getTaskDateStr(tempFinish, mFinishtimeTV.getText().toString()));

        json.put("wallShowTime", DateUtils.getTasShowTime(tempStartDate,  mWallShowDayEdit.getText().toString(), mWallShowHourEdit.getText().toString()));
        json.put("wallShowDay", Integer.parseInt(mWallShowDayEdit.getText().toString()));
        json.put("wallShowHour", Integer.parseInt(mWallShowHourEdit.getText().toString()));
        json.put("taskAmount", Integer.parseInt(mMoneyEdit.getText().toString()));

//        json.put("countryCode", "2");
        json.put("cityCode", "11");
        json.put("lat", "12.2342");
        json.put("lng", "29.1234");

        json.put("meetPoint", mTask_meetEdit.getText().toString());
        if(mPlaceToggleButton.isChecked())
            json.put("provideStayPlaceFlag", 1);
        else
            json.put("provideStayPlaceFlag", 0);
        if(mPayToggleButton.isChecked())
            json.put("payTravelFeeFlag", 1);
        else
            json.put("payTravelFeeFlag", 0);
        if(mDependsTV.getText().toString().equals(getResources().getString(R.string.new_task_deposit2)))
            json.put("needDepositeFlag", 1);
        else
            json.put("needDepositeFlag", 0);

        json.put("weTodoDesc", mTask_doEdit.getText().toString());
        json.put("companionProvideDesc", mTask_provideEdit.getText().toString());
        json.put("notes", mTask_notesEdit.getText().toString());
        json.put("companionQty", Integer.parseInt(mPeopleCountTV.getText().toString()));
        json.put("companionRequirment.genderCode", mCompanionRequirmentRequest.getGenderCode());
        json.put("companionRequirment.languageCode", mCompanionRequirmentRequest.getLanguageCode());
        json.put("companionRequirment.minAge", mCompanionRequirmentRequest.getMinAge());
        json.put("companionRequirment.maxAge", mCompanionRequirmentRequest.getMaxAge());
        json.put("companionRequirment.minHeight", mCompanionRequirmentRequest.getMinHeight());
        json.put("companionRequirment.maxHeight", mCompanionRequirmentRequest.getMaxHeight());
        json.put("companionRequirment.minWeight", mCompanionRequirmentRequest.getMinWeight());
        json.put("companionRequirment.maxWeight", mCompanionRequirmentRequest.getMaxWeight());
        json.put("companionRequirment.occupationCode", mCompanionRequirmentRequest.getOccupationCode());
        json.put("companionRequirment.nationalityCode", mCompanionRequirmentRequest.getNationalityCode());
        json.put("companionRequirment.hairColorCode", mCompanionRequirmentRequest.getHairColorCode());
        json.put("companionRequirment.eyeColorCode", mCompanionRequirmentRequest.getEyeColorCode());
        json.put("companionRequirment.authFlag", mCompanionRequirmentRequest.getAuthFlag());
        HttpUtils.sendPostSign(url, json, new HttpUtils.CallBackText(){
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

//    postObj.setProfessionTypeId(1);
//    postObj.setTaskTypeId(1);
//    postObj.setTaskName(mTask_nameEdit.getText().toString());
//    postObj.setTaskBrief(mTask_briefEdit.getText().toString());
//
//    postObj.setStartTime(mStartDate.toString());
//    postObj.setEndTime(mFinishDate.toString());
//
//    postObj.setWallShowTime("");
//    postObj.setWallShowHour(1);
//    postObj.setWallShowDay(2);
//    postObj.setTaskAmount(Integer.parseInt(mMoneyEdit.getText().toString()));
//    postObj.setCountryCode("");
//    postObj.setCityCode("");
//    postObj.setLat("");
//    postObj.setLng("");
//    postObj.setMeetPoint(mTask_meetEdit.getText().toString());
//    if(mPlaceToggleButton.isChecked())
//            postObj.setProvideStayPlaceFlag(1);
//    else
//            postObj.setProvideStayPlaceFlag(0);
//    if(mPayToggleButton.isChecked())
//            postObj.setPayTravelFeeFlag(1);
//    else
//            postObj.setPayTravelFeeFlag(0);
//    if(mPayToggleButton.isChecked())
//            postObj.setNeedDepositeFlag(1);
//    else
//            postObj.setNeedDepositeFlag(0);
//    postObj.setWeTodoDesc(mTask_doEdit.getText().toString());
//    postObj.setCompanionQty(Integer.parseInt(mPeopleCountTV.getText().toString()));
//    postObj.setCompanionRequirment(mCompanionRequirmentRequest);

    private TimePopupWindow.OnTimeSelectListener onTimeSelect = new TimePopupWindow.OnTimeSelectListener(){
        @Override
        public void onTimeSelect(Date date) {
            if(isSelectStart){
                mStarttimeTV.setText(mTimeFormat.format(date));
            }else{
                mFinishtimeTV.setText(mTimeFormat.format(date));
            }
            changeDay();
        }
    };

    private void changeDay(){
        if(!Tools.isEmpty(mStarttimeTV.getText().toString()) &&  !Tools.isEmpty(mFinishtimeTV.getText().toString())){
            if(mStartDate != null){
                Date endDate = mFinishDate;
                if(mFinishDate == null){
                    endDate =  mStartDate;
                }
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                int[] dayHourArr = DateUtils.getDayHour(df.format(mStartDate)+" "+mStarttimeTV.getText().toString()+":00",df.format(endDate)+" "+mFinishtimeTV.getText().toString()+":00");
                if(dayHourArr != null){
                    mWallShowDayEdit.setText(dayHourArr[0]+"");
                    mWallShowHourEdit.setText(dayHourArr[1]+"");
                }
            }
        }
    }

    private AdapterView.OnItemClickListener DateClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Date date = mNewTaskDateAdapter.getItem(position);
            int temp = DateUtils.cpmpareDate(date, new Date());
            System.out.println("onItemClick    temp = "+temp);
            if(temp >= 0){
                if(mStartDate == null){
                    mStartDate = date;
                    mFinishDate = null;
                    mNewTaskDateAdapter.setStartPosition(position);
                    mNewTaskDateAdapter.setFinishPosition(-1);
                    mNewTaskDateAdapter.notifyDataSetChanged();
                }else{
                    int taskDateTemp = DateUtils.cpmpareDate(date, mStartDate);
                    if(taskDateTemp != 0){
                        if(mFinishDate == null){
                            if(taskDateTemp < 0){
                                mFinishDate = mStartDate;
                                mStartDate = date;
                            }else{
                                mFinishDate = date;
                            }
                            mNewTaskDateAdapter.setFinishPosition(position);
                            mNewTaskDateAdapter.notifyDataSetChanged();
                        }else{
                            mStartDate = date;
                            mFinishDate = null;
                            mNewTaskDateAdapter.setStartPosition(position);
                            mNewTaskDateAdapter.setFinishPosition(-1);
                            mNewTaskDateAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
//            changeDay();
        }
    };

    private void addGridView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mGridView = new GridView(this);
        mGridView.setNumColumns(7);
        mGridView.setVerticalScrollBarEnabled(false);
        mGridView.setGravity(Gravity.CENTER);
        mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mGridView.setVerticalSpacing(7);
        mGridView.setLayoutParams(params);
    }

    @Override
    protected void setListeners() {
        pwTime.setOnTimeSelectListener(onTimeSelect);
        mLanguage_addImgV.setOnClickListener(this);
        mPeopleJianImgV.setOnClickListener(this);
        mPeopleJiaImgV.setOnClickListener(this);
        mPostTV.setOnClickListener(this);
        mDependsRl.setOnClickListener(this);
        mStartTimeLl.setOnClickListener(this);
        mFinishTimeLl.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Result_Add_Com && resultCode == RESULT_OK){
            mCompanionRequirmentRequest = (CompanionRequirmentRequest) data.getSerializableExtra("item");
        }
    }

    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.pay:
                break;
            case R.id.new_task_type:
                break;
            case R.id.task_brief:
                break;
            case R.id.task_time_year:
                break;
            case R.id.language_add:
                Intent intent = new Intent(mContext, CompanionRequirementActivity.class);
                intent.putExtra("item", mCompanionRequirmentRequest);
                startActivityForResult(intent, Result_Add_Com);
                break;
            case R.id.post:
                sendPost();
                break;
            case R.id.depends_rl:
                if (mPopup == null) {
                    mPopup = new NewTaskPopup(mListener, mContext);
                }
                mPopup.show(mDependsRl);
                break;
            case R.id.people_jia:
                mPeopleCountTV.setText((Integer.parseInt(mPeopleCountTV.getText().toString())+1)+"");
                break;
            case R.id.people_jian:
                int count = Integer.parseInt(mPeopleCountTV.getText().toString());
                if(count == 1){
                    return;
                }
                mPeopleCountTV.setText((count - 1) + "");
                break;
            case R.id.start_time:
                isSelectStart = true;
                pwTime.showAtLocation(v, Gravity.BOTTOM, 0, 0,new Date());
                break;
            case R.id.finish_time:
                isSelectStart = false;
                pwTime.showAtLocation(v, Gravity.BOTTOM, 0, 0,new Date());
                break;
        }
    }

    private NewTaskPopup.OnSelectListener mListener = new NewTaskPopup.OnSelectListener() {
        @Override
        public void selected(String value) {
            mDependsTV.setText(value);
        }
    };

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX() - e2.getX() > 150 && Math.abs(e1.getY() - e2.getY()) < 30) {
            //下一个月
            ArrayList<Date> tempList = DateUtils.getNextDayList(DateUtils.getNextDay(mNewTaskDateAdapter.getLastDate()), SHOW_DATE_COUNT);
            System.out.println("onFling 下一个月    temp = " + mNewTaskDateAdapter.getLastDate());
            addGridView();
            mNewTaskDateAdapter = new NewTaskDateAdapter(mContext);
            mGridView.setAdapter(mNewTaskDateAdapter);
            mGridView.setOnItemClickListener(DateClick);
            mNewTaskDateAdapter.setDataList(tempList);
            mNewTaskDateAdapter.notifyDataSetChanged();
            mFlipper1ViewFlipper.addView(mGridView, 1);

            this.mFlipper1ViewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
            this.mFlipper1ViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
            this.mFlipper1ViewFlipper.showNext();
            mFlipper1ViewFlipper.removeViewAt(0);
            return true;

        } else if (e1.getX() - e2.getX() < -150 && Math.abs(e1.getY() - e2.getY()) < 30) {
            ArrayList<Date> tempList = DateUtils.getPreviouDayList(DateUtils.getPreviouDay(mNewTaskDateAdapter.getFristDate()), SHOW_DATE_COUNT);
            //上一个月
            addGridView();
            mNewTaskDateAdapter = new NewTaskDateAdapter(mContext);
            mGridView.setAdapter(mNewTaskDateAdapter);
            mGridView.setOnItemClickListener(DateClick);
            mNewTaskDateAdapter.setDataList(tempList);
            mNewTaskDateAdapter.notifyDataSetChanged();
            mFlipper1ViewFlipper.addView(mGridView, 1);

            this.mFlipper1ViewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
            this.mFlipper1ViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
            this.mFlipper1ViewFlipper.showNext();
            mFlipper1ViewFlipper.removeViewAt(0);
            return true;
        }
        return false;
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        mGestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.mGestureDetector.onTouchEvent(event);
    }


}
