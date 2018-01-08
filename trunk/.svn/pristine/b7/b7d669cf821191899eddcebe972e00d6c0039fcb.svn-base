package com.taiwan.oomatcher.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.taiwan.oomatcher.R;

import java.util.concurrent.ExecutionException;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/18 16:03
 */

public class ImageLoaderUtils {
    /**
     * .skipMemoryCache(false)                                          //是否将图片放到内存中
     * .diskCacheStrategy(DiskCacheStrategy.ALL)                        //磁盘图片缓存策略
     * .dontAnimate()                                                   //不执行淡入淡出动画
     * .crossFade(100)                                                  // 默认淡入淡出动画300ms
     * .override(300,300)                                               //图片大小
     * .placeholder(R.drawable.shouye_haibao)                           // 占位图片
     * .error(R.drawable.shouye_haibao)                                 //图片加载错误显示
     * .centerCrop()       //  fitCenter()                              //图片的显示方式
     * .animate()                                                       // 执行的动画
     * .bitmapTransform(null)                                           // bitmap操作
     * .priority(Priority.HIGH)                                         // 当前线程的优先级
     * .signature(new StringSignature("ssss"))
     * .transform()                                                     //bitmap转换
     * .bitmapTransform()                                               //bitmap转换。
     * 比如:旋转，方法，缩小，高斯模糊等等（当用了转换后你就不能使用 .centerCrop() 或 .fitCenter() 了。
     * .thumbnail(0.1f) 缩略图，3个重构方法：优先显示原始图片的百分比(10%)
     * .listener() 异常监听
     */

    /**
     * @param context
     * @param resourceId
     * @param imgView
     */
    public static void load(Context context, Integer resourceId, ImageView imgView) {
        //Glide会为每种大小的ImageView缓存一次,让Glide既缓存全尺寸又缓存其他尺寸
        //下次在任何ImageView中加载图片的时候，全尺寸的图片将从缓存中取出，重新调整大小，然后缓存。
        Glide.with(context)
                .load(resourceId)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgView);
    }

    /**
     * @param context
     * @param imgPath
     * @param imgView
     */
    public static void load(Context context, String imgPath, ImageView imgView, int placeResId, int errorResId) {
        //Glide会为每种大小的ImageView缓存一次,让Glide既缓存全尺寸又缓存其他尺寸
        //下次在任何ImageView中加载图片的时候，全尺寸的图片将从缓存中取出，重新调整大小，然后缓存。
        Glide.with(context)
                .load(imgPath)
                .placeholder(placeResId)
                .error(errorResId)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgView);
    }


    /**
     * @param context
     * @param imgPath
     * @param imgView
     */
    public static void loadBrowse(Context context, String imgPath, ImageView imgView, int placeResId, int errorResId) {
        //Glide会为每种大小的ImageView缓存一次,让Glide既缓存全尺寸又缓存其他尺寸
        //下次在任何ImageView中加载图片的时候，全尺寸的图片将从缓存中取出，重新调整大小，然后缓存。
        Glide.with(context)
                .load(imgPath)
                .into(imgView);
    }

    /**
     * 加载网络头像
     *
     * @param context
     * @param url
     * @param imgView
     */
    public static void loadHead(Context context, String url, ImageView imgView) {
        Glide.with(context)
                .load(url)
                .dontAnimate()
                .placeholder(R.drawable.default_head)
                .error(R.drawable.default_head)
                .centerCrop()
                .into(imgView);
    }

    /**
     * 清除所有图片加载请求
     */
    public static void clearAllImageLoader(View view) {
        Glide.clear(view);
    }

    /**
     * 恢复所有图片加载请求
     */
    public static void resumeRequests(final Context context) {
        Glide.with(context).resumeRequests();
    }

    /**
     * 暂停所有图片加载请求
     */
    public static void pauseRequests(final Context context) {
        Glide.with(context).pauseRequests();
    }

    /**
     * 清除缓存
     */
    public static void clearImageCache(final Context context) {
        Glide.get(context).clearMemory();
    }


}
