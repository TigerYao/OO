package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.adapter.ViewPagerAdapter;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.utils.ImageLoaderUtils;
import com.taiwan.oomatcher.utils.SPUtils;
import com.taiwan.oomatcher.utils.Tools;

import java.io.File;
import java.util.ArrayList;

/**
 * 作 者: huanghuojun
 * 描 述: 引导图
 * 版 本:
 * 创建日期: 2017/12/18 15:57
 */

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private Context mContext;
    private ViewPager mGuideViewpager;
    private LinearLayout mGuideDotViewgroup;
    private ImageView mGuide0;
    private ImageView mGuide1;
    private ImageView mGuide2;
    private ImageView mGuide3;
    private ImageView mGuide4;

    private static final int GUIDECOUNT = 3;
    /**
     * 存放图片集合
     */
    private ArrayList<ImageView> mImageList = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_guide);
        mGuideViewpager = (ViewPager) findViewById(R.id.guide_viewpager);
        mGuideDotViewgroup = (LinearLayout) findViewById(R.id.guide_dot_viewgroup);
        mGuide0 = (ImageView) findViewById(R.id.guide_0);
        mGuide1 = (ImageView) findViewById(R.id.guide_1);
        mGuide2 = (ImageView) findViewById(R.id.guide_2);
        mGuide3 = (ImageView) findViewById(R.id.guide_3);
        mGuide4 = (ImageView) findViewById(R.id.guide_4);
    }

    @Override
    protected void init() {
        ImageView iv = null;
        iv = new ImageView(GuideActivity.this);
        ImageLoaderUtils.load(GuideActivity.this, R.drawable.guide_0, iv);
        mImageList.add(iv);

        iv = new ImageView(GuideActivity.this);
        ImageLoaderUtils.load(GuideActivity.this, R.drawable.guide_1, iv);
        mImageList.add(iv);

        iv = new ImageView(GuideActivity.this);
        ImageLoaderUtils.load(GuideActivity.this, R.drawable.guide_2, iv);
        iv.setOnClickListener(this);
        mImageList.add(iv);

        ViewPagerAdapter adapter = new ViewPagerAdapter(mImageList);
        mGuideViewpager.setAdapter(adapter);
        mGuideViewpager.setOnPageChangeListener(this);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void widgetClick(View v) {
        SPUtils.put(SPUtils.VERSION, Tools.getAppVersionName(mContext));
        Intent intent = new Intent();
        String languageCode = (String) SPUtils.get(SPUtils.LANGUAGE_CODE, "");
        if(Tools.isEmpty(languageCode)){
            intent.setClass(mContext, SelectLanguageActivity.class);
        }else{
            Tools.setlanguage(mContext, languageCode);
            String token = (String) SPUtils.get(SPUtils.TOKEN, "");
            if(Tools.isEmpty(token)){
                intent.setClass(mContext, LoginActivity.class);
            }else{
                intent.setClass(mContext, MainActivity.class);
            }
        }
        startActivity(intent);
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}