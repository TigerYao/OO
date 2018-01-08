package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.activitys.selectalbum.AllImageListActivity;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.utils.CameraUtil;
import com.taiwan.oomatcher.utils.DialogUtils;
import com.taiwan.oomatcher.utils.ImageBase64;
import com.taiwan.oomatcher.utils.SDCardUtils;
import com.taiwan.oomatcher.utils.Tools;

import java.io.File;
import java.util.ArrayList;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2018/1/2 22:58
 */

public class AuthenticationActivity extends BaseActivity{

    private Context mContext;
    private static final int REQUESTCODE_CAMERA = 207;
    private static final int REQUESTCODE_ALBUM = 208;
    private TextView mTitleCenterTxt;
    private RelativeLayout mPassportRl;
    private TextView mTitleTypeTxt;
    private ImageView mLeftImg;
    private ImageView mRightImg;
    private EditText mNoEdit;
    private TextView mPhotoTxt;
    private ImageView mFrontImg;
    private ImageView mBackImg;
    private TextView mApplyTxt;
    private int mType = 2;
    private int mPhotoType = 1;
    private String[] photo_Item;
    private String mHeadPath;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        mTitleCenterTxt.setText(R.string.authentication);
    }

    public void backClick(View v){
        finish();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_authentication);
        assignViews();
    }

    @Override
    protected void init() {
        photo_Item = new String[]{getResources().getString(R.string.album), getResources().getString(R.string.camera)};
    }

    @Override
    protected void setListeners() {
        mLeftImg.setOnClickListener(this);
        mRightImg.setOnClickListener(this);
        mFrontImg.setOnClickListener(this);
        mBackImg.setOnClickListener(this);
        mApplyTxt.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE_CAMERA && resultCode == RESULT_OK) {
            if(mPhotoType == 1){
                mFrontImg.setImageURI(imageUri);
            }else{
                mBackImg.setImageURI(imageUri);
            }
        } else if (requestCode == REQUESTCODE_ALBUM && resultCode == RESULT_OK && data != null) {
            ArrayList<String> temp = data.getStringArrayListExtra("SELECT");
            if (temp != null) {
                mHeadPath = temp.get(0);
                imageUri = Uri.fromFile(new File(mHeadPath));
                if(mPhotoType == 1){
                    mFrontImg.setImageURI(imageUri);
                }else{
                    mBackImg.setImageURI(imageUri);
                }
            }
        }
    }

    @Override
    protected void widgetClick(View v) {
        if(v.getId() == mLeftImg.getId()){
            mType = 2;
            mLeftImg.setVisibility(View.GONE);
            mRightImg.setVisibility(View.VISIBLE);
            mTitleTypeTxt.setText(R.string.passport);
            mNoEdit.setHint(R.string.passport_no);
            mPhotoTxt.setText(R.string.passport_photo);
        }else if(v.getId() == mRightImg.getId()){
            mType = 1;
            mLeftImg.setVisibility(View.VISIBLE);
            mRightImg.setVisibility(View.GONE);
            mTitleTypeTxt.setText(R.string.identity_card);
            mNoEdit.setHint(R.string.identity_card_no);
            mPhotoTxt.setText(R.string.identity_card_photo);
        }else if(v.getId() == mFrontImg.getId()){
            mPhotoType = 1;
            DialogUtils.showListDialog(mContext, "", photo_Item, new DialogUtils.DialogItemClickListener() {
                @Override
                public void confirm(String result) {
                    if (result.equals(getResources().getString(R.string.camera))) {
                        mHeadPath = SDCardUtils.getCameraFilePath();
                        if (!Tools.isEmpty(mHeadPath)) {
                            File file = new File(mHeadPath);
                            imageUri = Uri.fromFile(file);
                            CameraUtil.openCamera(AuthenticationActivity.this, REQUESTCODE_CAMERA, imageUri);
                        }
                    } else {
                        Intent intent = new Intent(mContext, AllImageListActivity.class);
                        intent.putExtra("MAX_SIZE", 1);
                        startActivityForResult(intent, REQUESTCODE_ALBUM);
                    }
                }
            });
        }else if(v.getId() == mBackImg.getId()){
            mPhotoType = 2;
            DialogUtils.showListDialog(mContext, "", photo_Item, new DialogUtils.DialogItemClickListener() {
                @Override
                public void confirm(String result) {
                    if (result.equals(getResources().getString(R.string.camera))) {
                        mHeadPath = SDCardUtils.getCameraFilePath();
                        if (!Tools.isEmpty(mHeadPath)) {
                            File file = new File(mHeadPath);
                            imageUri = Uri.fromFile(file);
                            CameraUtil.openCamera(AuthenticationActivity.this, REQUESTCODE_CAMERA, imageUri);
                        }
                    } else {
                        Intent intent = new Intent(mContext, AllImageListActivity.class);
                        intent.putExtra("MAX_SIZE", 1);
                        startActivityForResult(intent, REQUESTCODE_ALBUM);
                    }
                }
            });
        }else if(v.getId() == mApplyTxt.getId()){

        }
    }

    private void assignViews() {
        mTitleCenterTxt = (TextView) findViewById(R.id.title_center_txt);
        mPassportRl = (RelativeLayout) findViewById(R.id.passport_rl);
        mTitleTypeTxt = (TextView) findViewById(R.id.title_type_txt);
        mLeftImg = (ImageView) findViewById(R.id.left_img);
        mRightImg = (ImageView) findViewById(R.id.right_img);
        mNoEdit = (EditText) findViewById(R.id.no_edit);
        mPhotoTxt = (TextView) findViewById(R.id.photo_txt);
        mFrontImg = (ImageView) findViewById(R.id.front_img);
        mBackImg = (ImageView) findViewById(R.id.back_img);
        mApplyTxt = (TextView) findViewById(R.id.apply_txt);
    }
}
