package com.taiwan.oomatcher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.utils.DialogUtils;
import com.taiwan.oomatcher.utils.ImageLoaderUtils;
import com.taiwan.oomatcher.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2018/1/2 17:50
 */

public class AllImageListAdapter extends BaseAdapter {

    /**
     * 上下文对象
     */
    private Context mContext = null;

    /**
     * 图片列表
     */
    private ArrayList<String> mDataList = new ArrayList<String>();

    /**
     * 选中的图片列表
     */
    private ArrayList<String> mSelectedList = new ArrayList<String>();

    private OnSelectChangeListener mlistener;

    private int width;

    private int mMax;

    public AllImageListAdapter(Context context, ArrayList<String> list, int max, OnSelectChangeListener listener) {
        mDataList = list;
        mContext = context;
        mlistener = listener;
        mMax = max;
        width = ScreenUtils.getScreenWidth(mContext);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public String getItem(int position) {
        if (position < 0 || position > mDataList.size()) {
            return null;
        }
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_all_image_list, null);
            holder.mImageIv = (ImageView) view.findViewById(R.id.list_item_iv);
            holder.mSelectedCb = (ImageView) view.findViewById(R.id.list_item_cb);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width / 3 - 10, width / 3 - 10);
//        holder.mImageIv.setLayoutParams(lp);

        final String path = getItem(position);

        // 加载图片
        ImageLoaderUtils.load(mContext,path, holder.mImageIv, R.drawable.pic_thumb, R.drawable.pic_thumb);

        // 该图片是否选中
        if (mSelectedList.contains(path)) {
            holder.mSelectedCb.setSelected(true);
            holder.mSelectedCb.setBackgroundResource(R.drawable.friend_select_ok);
        }else{
            holder.mSelectedCb.setSelected(false);
            holder.mSelectedCb.setBackgroundResource(R.drawable.friend_select);
        }
        holder.mSelectedCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.mSelectedCb.isSelected()){
                    holder.mSelectedCb.setSelected(false);
                    holder.mSelectedCb.setBackgroundResource(R.drawable.friend_select);
                    deleteImage(path);
                }else{
                    if (mSelectedList.size() >= mMax) {
                        DialogUtils.showRadioDialog(mContext, mContext.getResources().getString(R.string.max_photo_tips, mMax), new DialogUtils.DialogClickListener() {
                            @Override
                            public void leftClick() {
                            }

                            @Override
                            public void rightClick() {
                            }
                        });
                    }else{
                        holder.mSelectedCb.setSelected(true);
                        holder.mSelectedCb.setBackgroundResource(R.drawable.friend_select_ok);
                        addImage(path);
                    }
                }
                mlistener.change();
            }
        });

        holder.mImageIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.imgClick(position);
            }
        });

        return view;
    }

    /**
     * 将图片地址添加到已选择列表中
     *
     * @param path
     */
    private void addImage(String path) {
        if (mSelectedList.contains(path)) {
            return;
        }
        mSelectedList.add(path);
    }

    /**
     * 将图片地址从已选择列表中删除
     *
     * @param path
     */
    private void deleteImage(String path) {
        mSelectedList.remove(path);
    }

    /**
     * 获取已选中的图片列表
     *
     * @return
     */
    public ArrayList<String> getSelectedImgs() {
        return mSelectedList;
    }

    /**
     * 获取已选中的图片列表
     *
     * @return
     */
    public void setSelectedImgs(List<String> list) {
        if(list != null){
            mSelectedList.clear();
            mSelectedList.addAll(list);
            notifyDataSetChanged();
        }
    }

    static class ViewHolder {
        public ImageView mImageIv;
        public ImageView mSelectedCb;
    }

    public interface OnSelectChangeListener{
        void change();
        void imgClick(int posttion);
    }

}
