package com.taiwan.oomatcher.utils.imgchooser;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作 者: huanghuojun
 * 描 述: 异步任务基类
 * 版 本:
 * 创建日期: 2016/4/11 14:06
 */
public abstract class BaseTask extends AsyncTask<Void, Void, Boolean> {

    /**
     * 失败的时候的错误提示
     */
    protected String error = "";

    /**
     * 是否被终止
     */
    protected boolean interrupt = false;

    /**
     * 异步任务执行完后的回调接口
     */
    protected OnTaskResultListener resultListener = null;

    /**
     * 存放图片<文件夹,该文件夹下的图片列表>键值对
     */
    protected Map<String, List<String>> mDirMap = new HashMap<String, List<String>>();

    protected ArrayList<String> mAllImgPathList= new ArrayList<String>();

    @Override
    protected void onPostExecute(Boolean success) {
        if (!interrupt && resultListener != null) {
            resultListener.onResult(success, error, mAllImgPathList, mDirMap);
        }
    }

    /**
     * 中断异步任务
     */
    public void cancel() {
        super.cancel(true);
        interrupt = true;
    }

    public void setOnResultListener(OnTaskResultListener listener) {
        resultListener = listener;
    }

}
