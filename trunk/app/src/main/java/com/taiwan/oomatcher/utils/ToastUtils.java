package com.taiwan.oomatcher.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.taiwan.oomatcher.base.BaseApplication;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/17 11:53
 */

public class ToastUtils {

    /**
     * 短时间显示Toast By String
     *
     * @param message
     */
    public static void showShort(CharSequence message) {
        Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast By String
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 短时间显示Toast By Res
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 短时间显示Toast By Res
     *
     * @param message
     */
    public static void showShort(int message) {
        Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast By String
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast By Res
     *
     * @param message
     */
    public static void showLong(int message) {
        Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义提示Toast
     *
     * @param resId   图片资源ID
     * @param content 提示内容
     */
    public static void showCustomToast(int resId, String content) {
//        View layout = LayoutInflater.from(BaseApplication.getInstance()).inflate(R.layout.toast_tips, null);
//        TextView text = (TextView) layout.findViewById(R.id.text);
//        ImageView mImageView = (ImageView) layout.findViewById(R.id.iv);
//        mImageView.setImageResource(resId);
//        if (InputValidator.isEmpty(content)) {
//            text.setVisibility(View.GONE);
//        } else {
//            text.setText(content);
//        }
//        Toast toast = new Toast(BaseApplication.getInstance());
//        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//        toast.setDuration(Toast.LENGTH_LONG);
//        toast.setView(layout);
//        toast.setDuration(2000);
//        toast.show();
    }
}
