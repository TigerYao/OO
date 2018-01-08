package com.taiwan.oomatcher.base;

import android.app.Dialog;
import android.app.TabActivity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.utils.ToastUtils;
import com.taiwan.oomatcher.utils.Tools;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/17 15:32
 */

public abstract class BaseTabActivity extends TabActivity implements BaseView,View.OnClickListener{

    private String mActivityName;
    private Dialog mDialog;
    private WindowManager windowManager;
    private WindowManager.LayoutParams windowManagerParams;

    private ViewGroup mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityName = getActivityName();
        Tools.println("---------------【"+mActivityName+"--onCreate】---------------");
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);

        mDialog = new Dialog(this, R.style.dialog);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setContentView(R.layout.dialog_loading);
        initView();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
//            setStatusBarColor(R.color.ixuehao_title);
//        }
        init();
        setListeners();
    }

    /**
     * 状态栏着色。
     * @param resColor
     */
    protected void setStatusBarColor(int resColor) {
//        Window window = getWindow();
//        ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
//        //First translucent status bar.
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        int statusBarHeight = getStatusBarHeight(this);
//        View mChildView = mContentView.getChildAt(0);
//        if (mChildView != null) {
//            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
//            //如果已经为 ChildView 设置过了 marginTop, 再次调用时直接跳过
//            if (lp != null && lp.topMargin < statusBarHeight && lp.height != statusBarHeight) {
//                //不预留系统空间
//                ViewCompat.setFitsSystemWindows(mChildView, false);
//                lp.topMargin += statusBarHeight;
//                mChildView.setLayoutParams(lp);
//            }
//        }
//        View statusBarView = mContentView.getChildAt(0);
//        if (statusBarView != null && statusBarView.getLayoutParams() != null &&
//                statusBarView.getLayoutParams().height == statusBarHeight) {
//            //避免重复调用时多次添加 View
//            statusBarView.setBackgroundColor(getResources().getColor(resColor));
//            return;
//        }
//        statusBarView = new View(this);
//        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
//        statusBarView.setBackgroundColor(getResources().getColor(resColor));
//        //向 ContentView 中添加假 View
//        mContentView.addView(statusBarView, 0, lp);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Tools.println("---------------【"+mActivityName+"--onStart】---------------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Tools.println("---------------【"+mActivityName+"--onResume】---------------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Tools.println("---------------【"+mActivityName+"--onStop】---------------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Tools.println("---------------【"+mActivityName+"--onPause】---------------");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Tools.println("---------------【"+mActivityName+"--onRestart】---------------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDialog != null) {
            mDialog.dismiss();
        }
        Tools.println("---------------【"+mActivityName+"--onDestroy】---------------");
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
        ToastUtils.showShort(msg);
    }

    public void showToast(int resId) {
        ToastUtils.showShort(Tools.getString(resId));
    }

    @Override
    public void showError(String msg) {
//        ToastUtils.showCustomToast(R.drawable.icon_error, msg);
    }

    public String getActivityName(){
        return super.getClass().getSimpleName();
    }

    public void showDialog(){
        if(mDialog != null && !mDialog.isShowing()){
            mDialog.show();
        }
    }

    public void closeDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.cancel();
        }
    }
}
