package com.taiwan.oomatcher.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.utils.ImageLoaderUtils;
import com.taiwan.oomatcher.widget.photoview.PhotoView;
import com.taiwan.oomatcher.widget.photoview.PhotoViewAttacher;

import java.util.List;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2018/1/2 17:44
 */

public class ImagePagerBrowseAdapter extends PagerAdapter {

    private List<String> mDatas;
    private Context mContext;
    private PhotoViewAttacher.OnPhotoTapListener listener;

    public ImagePagerBrowseAdapter(Context mContext, List<String> mDatas, PhotoViewAttacher.OnPhotoTapListener listener) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_image_browse, container, false);
        PhotoView photoView = (PhotoView) view.findViewById(R.id.activity_image_browse_photoview);
        String imgPath = (String) getItem(position);
        ImageLoaderUtils.loadBrowse(mContext, imgPath, photoView, R.drawable.pic_thumb, R.drawable.pic_thumb);

        photoView.setOnPhotoTapListener(listener);

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object getItem(int position) {
        if (position < mDatas.size()) {
            return mDatas.get(position);
        } else {
            return null;
        }
    }
}
