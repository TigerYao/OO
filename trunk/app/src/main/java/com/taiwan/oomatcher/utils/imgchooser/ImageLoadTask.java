package com.taiwan.oomatcher.utils.imgchooser;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 作 者: huanghuojun
 * 描 述: 使用contentProvider扫描图片异步任务
 * 版 本:
 * 创建日期: 2016/4/11 14:08
 */
public class ImageLoadTask extends BaseTask{

    private Context mContext = null;

    public ImageLoadTask(Context context, OnTaskResultListener listener) {
        super();
        mContext = context;
        setOnResultListener(listener);
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        Uri mImgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver mContentResolver = mContext.getContentResolver();
        //构建查询条件，且只查询jpeg、png的图片
        StringBuilder selection = new StringBuilder();
        selection.append(MediaStore.MediaColumns.MIME_TYPE).append("=?");
        selection.append(" or ");
        selection.append(MediaStore.MediaColumns.MIME_TYPE).append("=?");

        Cursor mCursor = null;
        mCursor = mContentResolver.query(mImgUri,null, selection.toString(), new String[]{"image/jpeg", "image/png"}, MediaStore.Images.ImageColumns.DATE_TAKEN);
        //变量查询结果
        while (mCursor.moveToNext()){
            //获取图片路径
            String imgPath = mCursor.getString(mCursor.getColumnIndex(MediaStore.MediaColumns.DATA));
            mAllImgPathList.add(imgPath);

            File file = new File(imgPath);
            String dirName = null;
            if(file.getParentFile() != null){
                dirName = file.getParentFile().getName();
            }else{
                dirName = file.getName();
            }

            //整理同一个文件夹中的图片
            List<String> list = mDirMap.get(dirName);
            if(list == null){
                list = new ArrayList<String>();
            }
            list.add(imgPath);
            mDirMap.put(dirName, list);
        }
        if (mCursor != null && !mCursor.isClosed()) {
            mCursor.close();
        }
        return true;
    }
}
