package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.popup.SettingPopup;
import com.taiwan.oomatcher.widget.SelectableRoundedImageView;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/24 16:18
 */

public class MyProfileActivity extends BaseActivity {

    private Context mContext;
    private ImageView mSettingImg;
    private ImageView mEditImg;
    private TextView mNicknameTxt;
    private TextView mIdTxt;
    private LinearLayout mTop;
    private TextView mMytaskTxt;
    private TextView mMyfavoritesTxt;
    private TextView mPhotoTxt;
    private TextView mVideoTxt;
    private SelectableRoundedImageView mHeadImage;
    private TextView mXinTxt;
    private TextView mZanTxt;
    private ToggleButton mPrivateTBtn;
    private TextView mWhoIRentTxt;
    private TextView mWhoRentMeTxt;
    private RelativeLayout mAuthenticationRl;
    private ImageView mCell0Icon;
    private RelativeLayout mWalletRl;
    private ImageView mCell1Icon;
    private RelativeLayout mHotelsRl;
    private View mCell2Icon;
    private RelativeLayout mTicketsRl;
    private View mCell3Icon;
    private RelativeLayout mActivityRl;
    private View mCell4Icon;
    private RelativeLayout mScheduleRl;
    private ImageView mCell5Icon;
    private RelativeLayout mHireSettingRl;
    private ImageView mCell6Icon;

    private SettingPopup mPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_my_profile);
        assignViews();
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setListeners() {
        mWhoIRentTxt.setOnClickListener(this);
        mWhoRentMeTxt.setOnClickListener(this);
        mSettingImg.setOnClickListener(this);
        mEditImg.setOnClickListener(this);
        mMytaskTxt.setOnClickListener(this);
        mMyfavoritesTxt.setOnClickListener(this);
        mPhotoTxt.setOnClickListener(this);
        mVideoTxt.setOnClickListener(this);
        mAuthenticationRl.setOnClickListener(this);
        mWalletRl.setOnClickListener(this);
        mHotelsRl.setOnClickListener(this);
        mTicketsRl.setOnClickListener(this);
        mActivityRl.setOnClickListener(this);
        mHireSettingRl.setOnClickListener(this);
        mPrivateTBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    @Override
    protected void widgetClick(View v) {
        if(v.getId() == mSettingImg.getId()){
            if(mPopup == null)
                mPopup = new SettingPopup(mContext);
            mPopup.show(mSettingImg);
        }else if(v.getId() == mEditImg.getId()){
            Intent intent = new Intent(mContext, EditProfileActivity.class);
            startActivity(intent);
        }else if(v.getId() == mMytaskTxt.getId()){
            Intent intent = new Intent(mContext, MyTaskListActivity.class);
            startActivity(intent);
        }else if(v.getId() == mMyfavoritesTxt.getId()){

        }else if(v.getId() == mPhotoTxt.getId()){
            Intent intent = new Intent(mContext, MyPhotoListActivity.class);
            startActivity(intent);
        }else if(v.getId() == mVideoTxt.getId()){

        }else if(v.getId() == mAuthenticationRl.getId()){
            Intent intent = new Intent(mContext, AuthenticationActivity.class);
            startActivity(intent);
        }else if(v.getId() == mWalletRl.getId()){

        }else if(v.getId() == mHotelsRl.getId()){

        }else if(v.getId() == mTicketsRl.getId()){

        }else if(v.getId() == mActivityRl.getId()){

        }else if(v.getId() == mHireSettingRl.getId()){
            Intent intent = new Intent(mContext, MyHireActivity.class);
            startActivity(intent);
        }else if(v.getId() == mWhoRentMeTxt.getId()){
            Intent intent = new Intent(mContext, WhoRentMeOrIRentActivity.class);
            intent.putExtra("type", 2);
            startActivity(intent);
        }else if(v.getId() == mWhoIRentTxt.getId()){
            Intent intent = new Intent(mContext, WhoRentMeOrIRentActivity.class);
            intent.putExtra("type", 1);
            startActivity(intent);
        }
    }

    private void assignViews() {
        mSettingImg = (ImageView) findViewById(R.id.setting_img);
        mEditImg = (ImageView) findViewById(R.id.edit_img);
        mNicknameTxt = (TextView) findViewById(R.id.nickname_txt);
        mIdTxt = (TextView) findViewById(R.id.id_txt);
        mTop = (LinearLayout) findViewById(R.id.top);
        mMytaskTxt = (TextView) findViewById(R.id.mytask_txt);
        mMyfavoritesTxt = (TextView) findViewById(R.id.myfavorites_txt);
        mPhotoTxt = (TextView) findViewById(R.id.photo_txt);
        mVideoTxt = (TextView) findViewById(R.id.video_txt);
        mHeadImage = (SelectableRoundedImageView) findViewById(R.id.head_image);
        mXinTxt = (TextView) findViewById(R.id.xin_txt);
        mZanTxt = (TextView) findViewById(R.id.zan_txt);
        mPrivateTBtn = (ToggleButton) findViewById(R.id.private_tBtn);
        mWhoIRentTxt = (TextView) findViewById(R.id.who_i_rent_txt);
        mWhoRentMeTxt = (TextView) findViewById(R.id.who_rent_me_txt);
        mAuthenticationRl = (RelativeLayout) findViewById(R.id.authentication_rl);
        mCell0Icon = (ImageView) findViewById(R.id.cell0_icon);
        mWalletRl = (RelativeLayout) findViewById(R.id.wallet_rl);
        mCell1Icon = (ImageView) findViewById(R.id.cell1_icon);
        mHotelsRl = (RelativeLayout) findViewById(R.id.hotels_rl);
        mCell2Icon = findViewById(R.id.cell2_icon);
        mTicketsRl = (RelativeLayout) findViewById(R.id.tickets_rl);
        mCell3Icon = findViewById(R.id.cell3_icon);
        mActivityRl = (RelativeLayout) findViewById(R.id.activity_rl);
        mCell4Icon = findViewById(R.id.cell4_icon);
        mScheduleRl = (RelativeLayout) findViewById(R.id.schedule_rl);
        mCell5Icon = (ImageView) findViewById(R.id.cell5_icon);
        mHireSettingRl = (RelativeLayout) findViewById(R.id.hire_setting_rl);
        mCell6Icon = (ImageView) findViewById(R.id.cell6_icon);
    }
}
