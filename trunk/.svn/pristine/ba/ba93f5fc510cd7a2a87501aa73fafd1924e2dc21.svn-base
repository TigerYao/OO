package com.taiwan.oomatcher.activitys.selectalbum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.adapter.ImagePagerBrowseAdapter;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.utils.DialogUtils;
import com.taiwan.oomatcher.widget.HackyViewPager;
import com.taiwan.oomatcher.widget.photoview.PhotoViewAttacher;

import java.util.ArrayList;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2018/1/2 17:36
 */

public class ImageBrowseActivity extends BaseActivity {

    private HackyViewPager mImgViewpager;
    private FrameLayout mTopMainFl;
    private ImageButton mReturnImgBtn;
    private ImageView mSelecterImgView;
    private FrameLayout mBottomMainFl;
    private TextView mCompleteTxt;
    private TextView mImgCountTxt;

    private ImagePagerBrowseAdapter mAdapter;
    private Animation mBottomOutAnimation = null;
    private Animation mBottomInAnimation = null;
    private Animation mTopOutAnimation = null;
    private Animation mTopInAnimation = null;

    private ArrayList<String> mData = new ArrayList<String>();
    private ArrayList<String> mSelectedList = new ArrayList<String>();
    private int mMax = 0;
    private int mCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_image_browse);

        mImgViewpager = (HackyViewPager) findViewById(R.id.img_viewpager);
        mTopMainFl = (FrameLayout) findViewById(R.id.top_main_fl);
        mReturnImgBtn = (ImageButton) findViewById(R.id.return_imgBtn);
        mSelecterImgView = (ImageView) findViewById(R.id.selecter_imgView);
        mBottomMainFl = (FrameLayout) findViewById(R.id.bottom_main_fl);
        mCompleteTxt = (TextView) findViewById(R.id.complete_txt);
        mImgCountTxt = (TextView) findViewById(R.id.img_count_txt);
    }

    @Override
    protected void init() {
        mMax = getIntent().getIntExtra("MAX_SIZE", 0);
        mCurrent = getIntent().getIntExtra("CURRENT", 0);
        mData = getIntent().getStringArrayListExtra("DATA");
        mSelectedList = getIntent().getStringArrayListExtra("SELECT");
        mBottomOutAnimation = AnimationUtils.loadAnimation(this, R.anim.push_down_out_window);
        mBottomInAnimation = AnimationUtils.loadAnimation(this, R.anim.push_up_in_window);
        mTopOutAnimation = AnimationUtils.loadAnimation(this, R.anim.top_push_down_out_window);
        mTopInAnimation = AnimationUtils.loadAnimation(this, R.anim.top_push_down_in_window);
        mAdapter = new ImagePagerBrowseAdapter(ImageBrowseActivity.this, mData, listener);
        mImgViewpager.setAdapter(mAdapter);
        mImgViewpager.setCurrentItem(mCurrent);

        checkSelect();
        checkChange();
    }

    @Override
    protected void setListeners() {
        mReturnImgBtn.setOnClickListener(this);
        mCompleteTxt.setOnClickListener(this);
        mSelecterImgView.setOnClickListener(this);

        mImgViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                checkSelect();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private void checkSelect(){
        if (mSelectedList.contains(mData.get(mImgViewpager.getCurrentItem()))) {
            mSelecterImgView.setSelected(true);
            mSelecterImgView.setBackgroundResource(R.drawable.friend_select_ok);
        }else{
            mSelecterImgView.setSelected(false);
            mSelecterImgView.setBackgroundResource(R.drawable.friend_select);
        }
    }

    private void checkChange(){
        if (mSelectedList.size() == 0) {
            mImgCountTxt.setVisibility(View.GONE);
            mCompleteTxt.setEnabled(false);
        } else {
            mImgCountTxt.setVisibility(View.VISIBLE);
            mImgCountTxt.setText(String.valueOf(mSelectedList.size()));
            mCompleteTxt.setEnabled(true);
        }
    }

    @Override
    protected void widgetClick(View v) {
        int id = v.getId();
        if(id == mReturnImgBtn.getId()){
            closeActivity(RESULT_CANCELED);
        }else if(id == mCompleteTxt.getId()){
            closeActivity(RESULT_OK);
        }else if(id == mSelecterImgView.getId()){
            String path = mData.get(mImgViewpager.getCurrentItem());
            if(mSelecterImgView.isSelected()){
                mSelectedList.remove(path);
            }else{
                if (mSelectedList.size() >= mMax) {
                    DialogUtils.showRadioDialog(ImageBrowseActivity.this, getResources().getString(R.string.max_photo_tips, mMax), new DialogUtils.DialogClickListener() {
                        @Override
                        public void leftClick() {

                        }

                        @Override
                        public void rightClick() {
                        }
                    });
                }else{
                    if (!mSelectedList.contains(path)) {
                        mSelectedList.add(path);
                    }
                }
            }
            checkSelect();
            checkChange();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            closeActivity(RESULT_CANCELED);
        }
        return super.onKeyDown(keyCode, event);
    }

    private void closeActivity(int resultCode){
        Intent data = new Intent();
        data.putStringArrayListExtra("SELECT", mSelectedList);
        setResult(resultCode, data);
        finish();
    }
    private PhotoViewAttacher.OnPhotoTapListener listener = new PhotoViewAttacher.OnPhotoTapListener() {
        @Override
        public void onPhotoTap(View view, float x, float y) {
            if (mTopMainFl.getVisibility() == View.GONE) {
                mBottomMainFl.startAnimation(mBottomInAnimation);
                mTopMainFl.startAnimation(mTopInAnimation);
                mTopMainFl.setVisibility(View.VISIBLE);
                mBottomMainFl.setVisibility(View.VISIBLE);
            } else {
                mBottomMainFl.startAnimation(mBottomOutAnimation);
                mTopMainFl.startAnimation(mTopOutAnimation);
                mTopMainFl.setVisibility(View.GONE);
                mBottomMainFl.setVisibility(View.GONE);
            }
        }
    };
}

