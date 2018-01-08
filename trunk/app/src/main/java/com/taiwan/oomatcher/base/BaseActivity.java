package com.taiwan.oomatcher.base;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import com.taiwan.oomatcher.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.taiwan.oomatcher.utils.ToastUtils;
import com.taiwan.oomatcher.utils.Tools;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/17 11:20
 */

public abstract class BaseActivity extends FragmentActivity implements BaseView, View.OnClickListener {

    private String mActivityName;
    private Dialog mDialog;
    private WindowManager windowManager;
    private WindowManager.LayoutParams windowManagerParams;
    private TextView mDialogContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityName = getActivityName();
        Tools.println("---------------【" + mActivityName + "--onCreate】---------------");
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        mDialog = new Dialog(this, R.style.dialog);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setContentView(R.layout.dialog_loading);
        mDialogContent = (TextView) mDialog.findViewById(R.id.loadTV);
        initView();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setStatusBarColor(R.color.ixuehao_title);
//        }
        init();
        setListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Tools.println("---------------【" + mActivityName + "--onStart】---------------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Tools.println("---------------【" + mActivityName + "--onResume】---------------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Tools.println("---------------【" + mActivityName + "--onStop】---------------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Tools.println("---------------【" + mActivityName + "--onPause】---------------");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Tools.println("---------------【" + mActivityName + "--onRestart】---------------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.cancel();
        }
        Tools.println("---------------【" + mActivityName + "--onDestroy】---------------");
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }

    protected abstract void initView();

    protected abstract void init();

    protected abstract void setListeners();

    protected abstract void widgetClick(View v);

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    @Override
    public void showProgress() {
        showDialog();
    }

    @Override
    public void hideProgress() {
        closeDialog();
    }

    @Override
    public void showToast(String msg) {
        hideProgress();
        ToastUtils.showShort(msg);
    }

    public void showToast(int resId) {
        hideProgress();
        ToastUtils.showShort(Tools.getString(resId));
    }

    @Override
    public void showError(String msg) {
        //ToastUtils.showCustomToast(R.drawable.icon_error, msg);
    }

    public void showDialog() {
        if (!isFinishing()) {
            if (mDialog != null && !mDialog.isShowing()) {
                mDialog.show();
            }
        }
    }

    public void closeDialog() {
        if (!isFinishing()) {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.cancel();
            }
        }
    }

    public String getActivityName() {
        return super.getClass().getSimpleName();
    }
}
