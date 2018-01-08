package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.adapter.FilterDateAdapter;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.utils.DateUtils;
import com.taiwan.oomatcher.widget.NestRadioGroup;
import com.taiwan.oomatcher.widget.RangeSeekBar;

import java.util.ArrayList;
import java.util.Date;

import static com.taiwan.oomatcher.utils.DateUtils.getNextDayList;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/23 10:29
 */

public class UserTaskFilterActivity extends BaseActivity implements GestureDetector.OnGestureListener {

    private static final int SHOW_DATE_COUNT = 27;
    private Context mContext;
    private RelativeLayout mTitleMainRl;
    private ImageView mTitleLeftTxt;
    private TextView mTitleRightTxt;
    private TextView mTitleCenterTxt;
    private TextView mOk;
    private RadioGroup mSelect;
    private RadioButton mCrTitleSex1;
    private RadioButton mCrTitleSex2;
    private RadioGroup mSexSelect;
    private RadioButton mFilterTitleSex1;
    private RadioButton mFilterTitleSex2;
    private RadioButton mFilterTitleSex3;
    private TextView mAgeMin;
    private TextView mAgeMax;
    private RangeSeekBar mAge;
    private NestRadioGroup mTypeSelect;
    private RadioButton mTypeRBtn1;
    private RadioButton mTypeRBtn2;
    private RadioButton mTypeRBtn3;
    private RadioButton mTypeRBtn4;
    private TextView mMoreMainTxt;
    private LinearLayout mMoreMainLl;
    private TextView mHeightMin;
    private TextView mHeightMax;
    private RangeSeekBar mHeight;
    private TextView mWeightMin;
    private TextView mWeightMax;
    private RangeSeekBar mWeight;
    private ImageView mLanguageAdd;
    private RelativeLayout mOccupation;
    private TextView mOccupationContent;
    private RelativeLayout mNationality;
    private TextView mNationalityContent;
    private RelativeLayout mHairColor;
    private TextView mHairColorContent;
    private RelativeLayout mEyeColor;
    private TextView mEyeColorContent;
    private ToggleButton mPlace;
    private ToggleButton mAbroadServiceTBtn;
    private RadioGroup mAuthenticationSelect;
    private RadioButton mAuthentication1;
    private RadioButton mAuthentication12;
    private ToggleButton mFilterDateSettingTBtn;
    private LinearLayout mDateSettingMain;
    private TextView mDateYearTxt;
    private TextView mDateMouthTxt;
    private ViewFlipper mViewFlipper;

    private GestureDetector mGestureDetector = null;
    private Date mMondayDate;
    private Date mStartDate;
    private Date mFinishDate;
    private GridView mGridView = null;
    private FilterDateAdapter mFilterDateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_user_task_filter);
        assignViews();
    }

    @Override
    protected void init() {
        mGestureDetector = new GestureDetector(this);
        mFilterDateAdapter = new FilterDateAdapter(mContext);
        mMondayDate = DateUtils.getCurrentMonday(new Date());
        ArrayList<Date> list = getNextDayList(mMondayDate, SHOW_DATE_COUNT);
        addGridView();
        mGridView.setAdapter(mFilterDateAdapter);
        mViewFlipper.addView(mGridView, 0);
        mFilterDateAdapter.setDataList(list);
        mFilterDateAdapter.notifyDataSetChanged();
    }

    public void backClick(View view) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

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

    private AdapterView.OnItemClickListener DateClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Date date = mFilterDateAdapter.getItem(position);
            int temp = DateUtils.cpmpareDate(date, new Date());
            System.out.println("onItemClick    temp = "+temp);
            if(temp >= 0){
                if(mStartDate == null){
                    mStartDate = date;
                    mFinishDate = null;
                    mFilterDateAdapter.setStartPosition(position);
                    mFilterDateAdapter.setFinishPosition(-1);
                    mFilterDateAdapter.notifyDataSetChanged();
                }else{
                    int taskDateTemp = DateUtils.cpmpareDate(mStartDate, date);
                    if(taskDateTemp != 0){
                        if(mFinishDate == null){
                            if(taskDateTemp < 0){
                                mFinishDate = mStartDate;
                                mStartDate = date;
                            }else{
                                mFinishDate = date;
                            }
                            mFilterDateAdapter.setFinishPosition(position);
                            mFilterDateAdapter.notifyDataSetChanged();
                        }else{
                            mStartDate = date;
                            mFinishDate = null;
                            mFilterDateAdapter.setStartPosition(position);
                            mFilterDateAdapter.setFinishPosition(-1);
                            mFilterDateAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        }
    };

    @Override
    protected void setListeners() {
        mMoreMainTxt.setOnClickListener(this);
    }

    @Override
    protected void widgetClick(View v) {
        if(v.getId() == mMoreMainTxt.getId()){
            mMoreMainLl.setVisibility(View.VISIBLE);
            mMoreMainTxt.setVisibility(View.GONE);
        }else if(v.getId() == mOk.getId()){
        }
    }

    private void assignViews() {
        mTitleMainRl = (RelativeLayout) findViewById(R.id.title_main_rl);
        mTitleLeftTxt = (ImageView) findViewById(R.id.title_left_txt);
        mTitleRightTxt = (TextView) findViewById(R.id.title_right_txt);
        mTitleCenterTxt = (TextView) findViewById(R.id.title_center_txt);
        mOk = (TextView) findViewById(R.id.ok);
        mSelect = (RadioGroup) findViewById(R.id.select);
        mCrTitleSex1 = (RadioButton) findViewById(R.id.cr_title_sex1);
        mCrTitleSex2 = (RadioButton) findViewById(R.id.cr_title_sex2);
        mSexSelect = (RadioGroup) findViewById(R.id.sex_select);
        mFilterTitleSex1 = (RadioButton) findViewById(R.id.filter_title_sex1);
        mFilterTitleSex2 = (RadioButton) findViewById(R.id.filter_title_sex2);
        mFilterTitleSex3 = (RadioButton) findViewById(R.id.filter_title_sex3);
        mAgeMin = (TextView) findViewById(R.id.age_min);
        mAgeMax = (TextView) findViewById(R.id.age_max);
        mAge = (RangeSeekBar) findViewById(R.id.age);
        mTypeSelect = (NestRadioGroup) findViewById(R.id.type_select);
        mTypeRBtn1 = (RadioButton) findViewById(R.id.type_rBtn1);
        mTypeRBtn2 = (RadioButton) findViewById(R.id.type_rBtn2);
        mTypeRBtn3 = (RadioButton) findViewById(R.id.type_rBtn3);
        mTypeRBtn4 = (RadioButton) findViewById(R.id.type_rBtn4);
        mMoreMainTxt = (TextView) findViewById(R.id.more_main_txt);
        mMoreMainLl = (LinearLayout) findViewById(R.id.more_main_ll);
        mHeightMin = (TextView) findViewById(R.id.height_min);
        mHeightMax = (TextView) findViewById(R.id.height_max);
        mHeight = (RangeSeekBar) findViewById(R.id.height);
        mWeightMin = (TextView) findViewById(R.id.weight_min);
        mWeightMax = (TextView) findViewById(R.id.weight_max);
        mWeight = (RangeSeekBar) findViewById(R.id.weight);
        mLanguageAdd = (ImageView) findViewById(R.id.language_add);
        mOccupation = (RelativeLayout) findViewById(R.id.occupation);
        mOccupationContent = (TextView) findViewById(R.id.occupation_content);
        mNationality = (RelativeLayout) findViewById(R.id.nationality);
        mNationalityContent = (TextView) findViewById(R.id.nationality_content);
        mHairColor = (RelativeLayout) findViewById(R.id.hair_color);
        mHairColorContent = (TextView) findViewById(R.id.hair_color_content);
        mEyeColor = (RelativeLayout) findViewById(R.id.eye_color);
        mEyeColorContent = (TextView) findViewById(R.id.eye_color_content);
        mPlace = (ToggleButton) findViewById(R.id.place);
        mAbroadServiceTBtn = (ToggleButton) findViewById(R.id.abroad_service_tBtn);
        mAuthenticationSelect = (RadioGroup) findViewById(R.id.authentication_select);
        mAuthentication1 = (RadioButton) findViewById(R.id.authentication1);
        mAuthentication12 = (RadioButton) findViewById(R.id.authentication12);
        mFilterDateSettingTBtn = (ToggleButton) findViewById(R.id.filter_date_setting_tBtn);
        mDateSettingMain = (LinearLayout) findViewById(R.id.date_setting_main);
        mDateYearTxt = (TextView) findViewById(R.id.date_year_txt);
        mDateMouthTxt = (TextView) findViewById(R.id.date_mouth_txt);
        mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
    }

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
            ArrayList<Date> tempList = DateUtils.getNextDayList(DateUtils.getNextDay(mFilterDateAdapter.getLastDate()), SHOW_DATE_COUNT);
            System.out.println("onFling 下一个月    temp = " + mFilterDateAdapter.getLastDate());
            addGridView();
            mFilterDateAdapter = new FilterDateAdapter(mContext);
            mGridView.setAdapter(mFilterDateAdapter);
            mGridView.setOnItemClickListener(DateClick);
            mFilterDateAdapter.setDataList(tempList);
            mFilterDateAdapter.notifyDataSetChanged();
            mViewFlipper.addView(mGridView, 1);

            this.mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
            this.mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
            this.mViewFlipper.showNext();
            mViewFlipper.removeViewAt(0);
            return true;

        } else if (e1.getX() - e2.getX() < -150 && Math.abs(e1.getY() - e2.getY()) < 30) {
            ArrayList<Date> tempList = DateUtils.getPreviouDayList(DateUtils.getPreviouDay(mFilterDateAdapter.getFristDate()), SHOW_DATE_COUNT);
            //上一个月
            addGridView();
            mFilterDateAdapter = new FilterDateAdapter(mContext);
            mGridView.setAdapter(mFilterDateAdapter);
            mGridView.setOnItemClickListener(DateClick);
            mFilterDateAdapter.setDataList(tempList);
            mFilterDateAdapter.notifyDataSetChanged();
            mViewFlipper.addView(mGridView, 1);

            this.mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
            this.mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
            this.mViewFlipper.showNext();
            mViewFlipper.removeViewAt(0);
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
