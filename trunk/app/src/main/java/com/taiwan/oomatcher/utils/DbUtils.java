package com.taiwan.oomatcher.utils;

import com.lidroid.xutils.exception.DbException;
import com.taiwan.oomatcher.base.BaseApplication;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/25 22:23
 */

public class DbUtils {
    public static void save(Object obj){
        try {
            BaseApplication.getInstance(BaseApplication.getInstance()).save(obj);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
