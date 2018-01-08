package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.popup.SelectLanguagePopup;
import com.taiwan.oomatcher.utils.SPUtils;
import com.taiwan.oomatcher.utils.Tools;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/18 17:57
 */
public class SelectLanguageActivity extends BaseActivity {

    private Context mContext;
    private TextView mNextTV;
    private TextView mSelect_languageTV;
    private SelectLanguagePopup mPopup;
    private String mLanguageCode = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_select_language);
        mNextTV = (TextView) findViewById(R.id.next);
        mSelect_languageTV = (TextView) findViewById(R.id.select_language);
    }

    @Override
    protected void init() {
    }

    private void setLanguage() {
        if(Tools.isEmpty(mLanguageCode)){
            showToast(R.string.error_new_task_language);
            return;
        }
        SPUtils.put(SPUtils.LANGUAGE_CODE, mLanguageCode);
        Intent intent = new Intent(mContext, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void setListeners() {
        mNextTV.setOnClickListener(this);
        mSelect_languageTV.setOnClickListener(this);
    }

    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                setLanguage();
                break;
            case R.id.select_language:
                if (mPopup == null) {
                    mPopup = new SelectLanguagePopup(languageListener, mContext);
                }
                mPopup.show(mSelect_languageTV);
                break;
        }
    }

    private SelectLanguagePopup.OnSelectLanguageListener languageListener = new SelectLanguagePopup.OnSelectLanguageListener() {

        @Override
        public void selected(String languageName, String languageCode) {
            mLanguageCode = languageCode;
            mSelect_languageTV.setText(languageName);
        }
    };

    /**
     * 重启当前Activity
     */
    private void restartAct() {
        finish();
        Intent _Intent = new Intent(this, MainActivity.class);
        startActivity(_Intent);
        //清除Activity退出和进入的动画
        overridePendingTransition(0, 0);
    }

}
