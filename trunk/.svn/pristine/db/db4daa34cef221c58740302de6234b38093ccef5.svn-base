package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.activitys.selectalbum.AllImageListActivity;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.entity.MyProfile;
import com.taiwan.oomatcher.entity.MyTaskList;
import com.taiwan.oomatcher.http.HttpUtils;
import com.taiwan.oomatcher.utils.CameraUtil;
import com.taiwan.oomatcher.utils.DialogUtils;
import com.taiwan.oomatcher.utils.ImageBase64;
import com.taiwan.oomatcher.utils.ImageLoaderUtils;
import com.taiwan.oomatcher.utils.SDCardUtils;
import com.taiwan.oomatcher.utils.Tools;
import com.taiwan.oomatcher.widget.SelectableRoundedImageView;

import java.io.File;
import java.util.ArrayList;

/**
 * 作 者: huanghuojun
 * 描 述: 编辑个人资料
 * 版 本:
 * 创建日期: 2017/12/24 16:26
 */

public class EditProfileActivity extends BaseActivity {

    private Context mContext;
    private static final int REQUESTCODE_CAMERA = 207;
    private static final int REQUESTCODE_ALBUM = 208;
    private static final int REQUESTCODE_CROP = 209;
    private TextView mTitleCenterTxt;
    private ImageView mTitleLeftTxt;
    private SelectableRoundedImageView mHeadImage;
    private LinearLayout mLanguageLl;
    private SelectableRoundedImageView mLanguageImage;
    private LinearLayout mChangeLl;
    private SelectableRoundedImageView mChangeImage;
    private RadioGroup mTypeGroup;
    private RadioButton mRBtn1;
    private RadioButton mRBtn2;
    private LinearLayout mBirthdayLl;
    private TextView mBirthdayTxt;
    private RelativeLayout mLookingforRl;
    private TextView mLookingforTxt;
    private RelativeLayout mNationalityRl;
    private TextView mNationalityTxt;
    private RelativeLayout mCountryRl;
    private TextView mCountryTxt;
    private RelativeLayout mCityRl;
    private TextView mCityTxt;
    private RelativeLayout mLanguageRl;
    private TextView mLanguageTxt;
    private RelativeLayout mSecondLanguageRl;
    private TextView mSecondLanguageTxt;
    private RelativeLayout mThirdLanguageRl;
    private TextView mThirdLanguageTxt;
    private RelativeLayout mWeightRl;
    private TextView mWeightTxt;
    private RelativeLayout mHeightRl;
    private TextView mHeightTxt;
    private RelativeLayout mHairColorRl;
    private TextView mHairColorTxt;
    private RelativeLayout mEyeColorRl;
    private TextView mEyeColorTxt;
    private RelativeLayout mOccupationRl;
    private TextView mOccupationTxt;
    private TextView mFinishTxt;
    private String[] photo_Item;
    private String mHeadPath;
    private Uri imageUri;
    private MyProfile mMyProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        getMyProfile();
    }

    private void getMyProfile() {
        JSONObject json = new JSONObject();
        HttpUtils.sendGetSign("user/myHome/getUserInfo", json, new HttpUtils.CallBackText() {
            @Override
            public void onSuccess(String str) {
                hideProgress();
                mMyProfile = JSONObject.parseObject(str, MyProfile.class);
                if(mMyProfile != null){
                    fillView();
                }
            }

            @Override
            public void onFailure(Exception e, String msg) {
                hideProgress();
                showToast(msg);
            }
        });
    }

    private void fillView() {

    }

    public void backClick(View v){
        finish();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_edit_profile);
        assignViews();
    }

    @Override
    protected void init() {
        photo_Item = new String[]{getResources().getString(R.string.album), getResources().getString(R.string.camera)};
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE_CAMERA && resultCode == RESULT_OK) {
            CameraUtil.cropImageUri(this, imageUri, 400, 400, REQUESTCODE_CROP);
        } else if (requestCode == REQUESTCODE_ALBUM && resultCode == RESULT_OK && data != null) {
            ArrayList<String> temp = data.getStringArrayListExtra("SELECT");
            if (temp != null) {
                mHeadPath = temp.get(0);
                imageUri = Uri.fromFile(new File(mHeadPath));
                CameraUtil.cropImageUri(this, imageUri, 400, 400, REQUESTCODE_CROP);
            }
        } else if (requestCode == REQUESTCODE_CROP && resultCode == RESULT_OK) {
            mHeadPath = CameraUtil.path;
            if (mHeadPath != null) {
                uploadHead(ImageBase64.encode(mHeadPath));
            }
        }
    }

    @Override
    protected void setListeners() {
        mHeadImage.setOnClickListener(this);
    }

    @Override
    protected void widgetClick(View v) {
        if(v.getId() == mHeadImage.getId()){
            DialogUtils.showListDialog(mContext, "", photo_Item, new DialogUtils.DialogItemClickListener() {
                @Override
                public void confirm(String result) {
                    if (result.equals(getResources().getString(R.string.camera))) {
                        mHeadPath = SDCardUtils.getCameraFilePath();
                        if (!Tools.isEmpty(mHeadPath)) {
                            File file = new File(mHeadPath);
                            imageUri = Uri.fromFile(file);
                            CameraUtil.openCamera(EditProfileActivity.this, REQUESTCODE_CAMERA, imageUri);
                        }
                    } else {
                        Intent intent = new Intent(mContext, AllImageListActivity.class);
                        intent.putExtra("MAX_SIZE", 1);
                        startActivityForResult(intent, REQUESTCODE_ALBUM);
                    }
                }
            });
        }
    }

    private void updateMyProfile(){
        JSONObject json = new JSONObject();
        json.put("nickname", mMyProfile.getNickname());
        json.put("currencyCode", mMyProfile.getCurrencyCode());
        json.put("genderCode", mMyProfile.getGenderCode());
        json.put("birthday", mMyProfile.getBirthday());
        json.put("companionTypeCode", mMyProfile.getCompanionTypeCode());
        json.put("nationalityCode", mMyProfile.getNationalityCode());
        json.put("countryCode", mMyProfile.getCountryCode());
        json.put("cityCode", mMyProfile.getCityCode());
        json.put("languageCode", mMyProfile.getLanguageCode());
        json.put("secondLanguageCode", mMyProfile.getSecondLanguageCode());
        json.put("thirdLanguageCode", mMyProfile.getThirdLanguageCode());
        json.put("height", mMyProfile.getHeight());
        json.put("weight", mMyProfile.getWeight());
        json.put("hairColorCode", mMyProfile.getHairColorCode());
        json.put("eyeColorCode", mMyProfile.getEyeColorCode());
        json.put("occupationCode", mMyProfile.getOccupationCode());
        HttpUtils.sendPostSign("user/myHome/updateMyProfile", json, new HttpUtils.CallBackText() {
            @Override
            public void onSuccess(String str) {

            }

            @Override
            public void onFailure(Exception e, String msg) {

            }
        });
    }

    private void uploadHead(String base64){
        JSONObject json = new JSONObject();
        json.put("userFigure", base64);
        HttpUtils.sendPostSign("user/myHome/updateUserFigure", json, new HttpUtils.CallBackText() {
            @Override
            public void onSuccess(String str) {
                ImageLoaderUtils.loadHead(mContext, str, mHeadImage);
            }

            @Override
            public void onFailure(Exception e, String msg) {
                showToast(msg);
            }
        });
    }

    private void assignViews() {
        mTitleCenterTxt = (TextView) findViewById(R.id.title_center_txt);
        mTitleLeftTxt = (ImageView) findViewById(R.id.title_left_txt);
        mHeadImage = (SelectableRoundedImageView) findViewById(R.id.head_image);
        mLanguageLl = (LinearLayout) findViewById(R.id.language_ll);
        mLanguageImage = (SelectableRoundedImageView) findViewById(R.id.language_image);
        mChangeLl = (LinearLayout) findViewById(R.id.change_ll);
        mChangeImage = (SelectableRoundedImageView) findViewById(R.id.change_image);
        mTypeGroup = (RadioGroup) findViewById(R.id.type_Group);
        mRBtn1 = (RadioButton) findViewById(R.id.rBtn1);
        mRBtn2 = (RadioButton) findViewById(R.id.rBtn2);
        mBirthdayLl = (LinearLayout) findViewById(R.id.birthday_ll);
        mBirthdayTxt = (TextView) findViewById(R.id.birthday_txt);
        mLookingforRl = (RelativeLayout) findViewById(R.id.lookingfor_rl);
        mLookingforTxt = (TextView) findViewById(R.id.lookingfor_txt);
        mNationalityRl = (RelativeLayout) findViewById(R.id.nationality_rl);
        mNationalityTxt = (TextView) findViewById(R.id.nationality_txt);
        mCountryRl = (RelativeLayout) findViewById(R.id.country_rl);
        mCountryTxt = (TextView) findViewById(R.id.country_txt);
        mCityRl = (RelativeLayout) findViewById(R.id.city_rl);
        mCityTxt = (TextView) findViewById(R.id.city_txt);
        mLanguageRl = (RelativeLayout) findViewById(R.id.language_rl);
        mLanguageTxt = (TextView) findViewById(R.id.language_txt);
        mSecondLanguageRl = (RelativeLayout) findViewById(R.id.second_language_rl);
        mSecondLanguageTxt = (TextView) findViewById(R.id.second_language_txt);
        mThirdLanguageRl = (RelativeLayout) findViewById(R.id.third_language_rl);
        mThirdLanguageTxt = (TextView) findViewById(R.id.third_language_txt);
        mWeightRl = (RelativeLayout) findViewById(R.id.weight_rl);
        mWeightTxt = (TextView) findViewById(R.id.weight_txt);
        mHeightRl = (RelativeLayout) findViewById(R.id.height_rl);
        mHeightTxt = (TextView) findViewById(R.id.height_txt);
        mHairColorRl = (RelativeLayout) findViewById(R.id.hair_color_rl);
        mHairColorTxt = (TextView) findViewById(R.id.hair_color_txt);
        mEyeColorRl = (RelativeLayout) findViewById(R.id.eye_color_rl);
        mEyeColorTxt = (TextView) findViewById(R.id.eye_color_txt);
        mOccupationRl = (RelativeLayout) findViewById(R.id.occupation_rl);
        mOccupationTxt = (TextView) findViewById(R.id.occupation_txt);
        mFinishTxt = (TextView) findViewById(R.id.finish_txt);
    }
}
