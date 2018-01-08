package com.taiwan.oomatcher.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

/**
 * 作 者: huanghuojun
 * 描 述: 相机的工具类
 * 版 本:
 * 创建日期: 2018/1/2 17:33
 */

public class CameraUtil {
    public static String path = SDCardUtils.getTempPath() + File.separator + "temp.jpg";

    /**
     * 打开照相机
     * @param activity 当前的activity
     * @param requestCode 拍照成功时activity forResult 的时候的requestCode
     * @param photoFile 拍照完毕时,图片保存的位置
     */
    public static void openCamera(Activity activity, int requestCode, File photoFile){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 打开照相机
     * @param activity 当前的activity
     * @param requestCode 拍照成功时activity forResult 的时候的requestCode
     * @param uri 拍照完毕时,图片的URI
     */
    public static void openCamera(Activity activity, int requestCode, Uri uri){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 调用系统剪裁图片
     * @param activity
     * @param uri
     * @param outputX
     * @param outputY
     * @param requestCode
     */
    public static void cropImageUri(Activity activity, Uri uri, int outputX, int outputY, int requestCode){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //发送裁剪信号
        intent.putExtra("crop", "true");
        //X方向上的比例
        intent.putExtra("aspectX", 1);
        //Y方向上的比例
        intent.putExtra("aspectY", 1);
        //裁剪区的宽
        intent.putExtra("outputX", outputX);
        //裁剪区的高
        intent.putExtra("outputY", outputY);
        //是否保留比例
        intent.putExtra("scale", true);
        /**
         * 将URI指向相应的file://
         * 由于我们不让它保存在Intent的data域中，
         * 但我们总要有地方来保存我们的图片啊，
         * 这个参数就是转移保存地址的，对应Value中保存的URI就是指定的保存地址。
         * 至于这两个参数能不能同时设为输出,不清楚
         */
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
        /**
         * 是否将数据保留在Bitmap中返回.
         * 是将结果保存在data中返回，在onActivityResult中，
         * 直接调用intent.getdata()就可以获取值了，
         * 这里设为fase，就是不让它保存在data中
         */
        intent.putExtra("return-data", false);
        //输出格式，一般设为Bitmap格式：Bitmap.CompressFormat.JPEG.toString()
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        //是否取消人脸识别功能
        intent.putExtra("noFaceDetection", true);
        activity.startActivityForResult(intent, requestCode);
    }

}
