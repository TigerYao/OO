package com.taiwan.oomatcher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.entity.AlbumListBean;
import com.taiwan.oomatcher.utils.ImageLoaderUtils;

import java.util.List;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2018/1/2 17:54
 */

public class ImageListAdapter extends BaseAdapter {

    private List<AlbumListBean> mData;
    private Context mContext;

    public ImageListAdapter(Context mContext, List<AlbumListBean> mData){
        this.mContext = mContext;
        this.mData = mData;
    }


    @Override
    public int getCount() {
        return (mData.size());
    }

    @Override
    public AlbumListBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_image_list, null);

            holder.mPhotoImgView = (ImageView) convertView.findViewById(R.id.photo_imgView);
            holder.mDirNameTxt = (TextView) convertView.findViewById(R.id.dir_name_txt);
            holder.mDirCountTxt = (TextView) convertView.findViewById(R.id.dir_count_txt);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AlbumListBean item = getItem(position);
        // 加载图片
        ImageLoaderUtils.load(mContext, item.getImgPath(), holder.mPhotoImgView, R.drawable.pic_thumb, R.drawable.pic_thumb);
        holder.mDirNameTxt.setText(item.getDirName());
        holder.mDirCountTxt.setText("("+mContext.getString(R.string.image_count, item.getImgCount()+"")+")");
        return convertView;
    }

    private class ViewHolder {
        private ImageView mPhotoImgView;
        private TextView mDirNameTxt;
        private TextView mDirCountTxt;
    }
}
