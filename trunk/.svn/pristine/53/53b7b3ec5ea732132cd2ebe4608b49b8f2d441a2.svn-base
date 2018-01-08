package com.taiwan.oomatcher.utils;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2018/1/2 17:57
 */

public class TaskUtil {
    /**
     * 执行异步任务
     * <p>
     * android 2.3 及一下使用execute()方法
     * <p>
     * android 3.0 及以上使用executeOnExecutor方法
     *
     * @param task
     * @param params
     */
    @SuppressLint("NewApi")
    public static <Params, Progress, Result> void execute(AsyncTask<Params, Progress, Result> task,
                                                          Params... params) {
        if (Build.VERSION.SDK_INT >= 11) {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
        } else {
            task.execute(params);
        }
    }
}

