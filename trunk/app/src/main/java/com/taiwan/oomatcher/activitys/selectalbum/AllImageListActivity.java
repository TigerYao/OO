package com.taiwan.oomatcher.activitys.selectalbum;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.adapter.AllImageListAdapter;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.entity.AlbumListBean;
import com.taiwan.oomatcher.utils.ImageLoaderUtils;
import com.taiwan.oomatcher.utils.InputValidator;
import com.taiwan.oomatcher.utils.SDCardUtils;
import com.taiwan.oomatcher.utils.TaskUtil;
import com.taiwan.oomatcher.utils.imgchooser.ImageLoadTask;
import com.taiwan.oomatcher.utils.imgchooser.OnTaskResultListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * 作 者: huanghuojun
 * 描 述: 相册选择
 * 版 本:
 * 创建日期: 2018/1/2 17:36
 */

public class AllImageListActivity  extends BaseActivity {

    public static final int REQUEST_SELECTED_WHAT = 1;
    public static final int REQUEST_IMAGE_BROWSE = 2;

    private AllImageListAdapter mImageAdapter = null;

    /** 图片扫描一般任务 */
    private ImageLoadTask mLoadTask = null;

    /** 图片地址源信息 */
    private ArrayList<String> mData = new ArrayList<String>();
    private Map<String, List<String>> mDirMap;

    private int max = 0;

    private TextView mTitleRightTxt;
    private TextView mTitleCenterTxt;

    private TextView mPreviewTxt;
    private TextView mCompleteTxt;
    private TextView mImgCountTxt;
    /** 图片列表GridView */
    private GridView mImgGridView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_all_image_list);

        mTitleCenterTxt = (TextView) findViewById(R.id.title_center_txt);
        mTitleRightTxt = (TextView) findViewById(R.id.title_right_txt);
        mTitleRightTxt.setVisibility(View.VISIBLE);

        mPreviewTxt = (TextView) findViewById(R.id.preview_txt);
        mCompleteTxt = (TextView) findViewById(R.id.complete_txt);
        mImgCountTxt = (TextView) findViewById(R.id.img_count_txt);
        mImgGridView = (GridView) findViewById(R.id.img_gridView);
    }

    @Override
    protected void init() {
        mTitleRightTxt.setText(R.string.cancel);
        String title = getIntent().getStringExtra("TITLE");
        max = getIntent().getIntExtra("MAX_SIZE", 0);

        if(InputValidator.isEmpty(title)){
            mTitleCenterTxt.setText(R.string.select_photo);
        }else{
            mTitleCenterTxt.setText(title);
        }

        if(mData.size() == 0){
            loadImages();
        }
        mImageAdapter = new AllImageListAdapter(mContext, mData, max, selectChangeListener);
        mImgGridView.setAdapter(mImageAdapter);
        mImgGridView.setSelection(mData.size());

        List<String> selectList = getIntent().getStringArrayListExtra("SELECT");
        if(selectList != null && selectList.size() != 0){
            for (int i = 0; i < selectList.size(); i++) {
                File file = new File(selectList.get(i));
                //判断文件夹是否存在,如果不存在则为服务器地址直接移除集合。
                if (!file.exists()) {
                    selectList.remove(selectList.get(i));
                    i-=1;
                }
            }
            mImageAdapter.setSelectedImgs(selectList);
        }
        selectImg(mImageAdapter.getSelectedImgs().size());
    }

    @Override
    protected void setListeners() {
        mTitleRightTxt.setOnClickListener(this);
        mPreviewTxt.setOnClickListener(this);
        mCompleteTxt.setOnClickListener(this);
        mImgGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private AllImageListAdapter.OnSelectChangeListener selectChangeListener = new AllImageListAdapter.OnSelectChangeListener(){
        @Override
        public void change() {
            selectImg(mImageAdapter.getSelectedImgs().size());
        }
        @Override
        public void imgClick(int position) {
            gotoBrowse(mData, position);
        }
    };


    public void backClick(View view){
        if(mDirMap != null){
            ArrayList<AlbumListBean> list = new ArrayList<AlbumListBean>();
            for(Map.Entry<String, List<String>> entry : mDirMap.entrySet()){
                AlbumListBean item = new AlbumListBean();
                item.setDirName(entry.getKey());
                List<String> temp = entry.getValue();
                item.setImgPath(temp.get(0));
                item.setImgCount(temp.size());
                list.add(item);
            }
            Intent intent = new Intent(mContext, ImageListActivity.class);
            intent.putParcelableArrayListExtra("LIST", list);
            startActivityForResult(intent, REQUEST_SELECTED_WHAT);
        }
    }

    @Override
    protected void widgetClick(View v) {
        int id = v.getId();
        if (id ==  mTitleRightTxt.getId()) {
            finish();
        } else if (id == mPreviewTxt.getId()) {
            gotoBrowse(mImageAdapter.getSelectedImgs(), 0);
        } else if (id == mCompleteTxt.getId()) {
            if(mImageAdapter.getSelectedImgs().size() != 0){
                closeActivity(RESULT_OK);
            }
        }
    }

    @Override
    protected void onDestroy() {
        ImageLoaderUtils.clearImageCache(mContext);
        mContext = null;
        super.onDestroy();
    }

    /**
     * 加载图片
     */
    private void loadImages() {
        if (!SDCardUtils.isSDCardEnable()) {
            showToast(getString(R.string.donot_has_sdcard));
            return;
        }
        // 线程正在执行
        if (mLoadTask != null && mLoadTask.getStatus() == AsyncTask.Status.RUNNING) {
            return;
        }

        mLoadTask = new ImageLoadTask(mContext, new OnTaskResultListener() {
            @Override
            public void onResult(boolean success, String error, ArrayList<String> allImage, Map<String, List<String>> dirMap) {
                // 图片加载成功
                mData.addAll(allImage);
                mDirMap = dirMap;
                mImageAdapter.notifyDataSetChanged();
                mImgGridView.setSelection(mData.size());
            }
        });
        TaskUtil.execute(mLoadTask);
    }

    private void selectImg(int size){
        if (size != 0) {
            mCompleteTxt.setEnabled(true);
            mPreviewTxt.setEnabled(true);
            mImgCountTxt.setVisibility(View.VISIBLE);
            mImgCountTxt.setText(String.valueOf(size));
        } else {
            mCompleteTxt.setEnabled(false);
            mPreviewTxt.setEnabled(false);
            mImgCountTxt.setVisibility(View.GONE);
            mImgCountTxt.setText("0");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_SELECTED_WHAT && resultCode == RESULT_CANCELED){
            finish();
        }else if(requestCode == REQUEST_SELECTED_WHAT && resultCode == RESULT_OK){
            String title = data.getStringExtra("TITLE");
            if(!InputValidator.isEmpty(title)){
                mTitleCenterTxt.setText(title);
                mData.clear();
                mData.addAll(mDirMap.get(title));
                mImageAdapter.notifyDataSetChanged();
                mImgGridView.setSelection(mData.size());
            }
        }else if(requestCode == REQUEST_IMAGE_BROWSE ){
            ArrayList<String> temp = data.getStringArrayListExtra("SELECT");
            mImageAdapter.setSelectedImgs(temp);
            selectImg(mImageAdapter.getSelectedImgs().size());
            if(resultCode == RESULT_OK){
                closeActivity(resultCode);
            }
        }
    }

    private void gotoBrowse(ArrayList<String> list, int position){
        Intent intent = new Intent(mContext, ImageBrowseActivity.class);
        intent.putExtra("MAX_SIZE", max);
        intent.putStringArrayListExtra("SELECT", mImageAdapter.getSelectedImgs());
        intent.putStringArrayListExtra("DATA", list);
        intent.putExtra("CURRENT", position);
        startActivityForResult(intent, REQUEST_IMAGE_BROWSE);
    }

    private void closeActivity(int resultCode){
        Intent data = new Intent();
        data.putStringArrayListExtra("SELECT", mImageAdapter.getSelectedImgs());
        setResult(resultCode, data);
        finish();
    }
}
