package com.taiwan.oomatcher.base;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/17 11:20
 */

public interface BaseView {
    void showProgress();
    void hideProgress();
    void showToast(String msg);
    void showError(String msg);
}
