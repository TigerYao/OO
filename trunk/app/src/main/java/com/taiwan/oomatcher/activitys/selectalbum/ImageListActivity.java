package com.taiwan.oomatcher.activitys.selectalbum;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.adapter.ImageListAdapter;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.entity.AlbumListBean;
import com.taiwan.oomatcher.utils.ImageLoaderUtils;
import com.taiwan.oomatcher.utils.InputValidator;

import java.util.List;

import static android.app.Activity.RESULT_FIRST_USER;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2018/1/2 17:53
 */

public class ImageListActivity extends BaseActivity {
    private ImageView mTitleLeftImg;
    private TextView mTitleRightTxt;
    private TextView mTitleCenterTxt;
    private ListView mListview;
    private Context mContext;
    private ImageListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_image_list);
        mTitleCenterTxt = (TextView) findViewById(R.id.title_center_txt);
        mTitleLeftImg = (ImageView) findViewById(R.id.title_left_txt);
        mTitleRightTxt = (TextView) findViewById(R.id.title_right_txt);
        mListview = (ListView) findViewById(R.id.listview);
    }

    @Override
    protected void init() {
        mTitleLeftImg.setVisibility(View.INVISIBLE);
        mTitleRightTxt.setText(R.string.cancel);
        mTitleCenterTxt.setText(R.string.photo);
        List<AlbumListBean> data = getIntent().getParcelableArrayListExtra("LIST");
        if (data != null) {
            mAdapter = new ImageListAdapter(mContext, data);
            mListview.setAdapter(mAdapter);
        }
    }

    @Override
    protected void setListeners() {
        mTitleLeftImg.setOnClickListener(this);
        mTitleRightTxt.setOnClickListener(this);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                colseActivity(RESULT_OK, mAdapter.getItem(position).getDirName());
            }
        });
    }

    @Override
    protected void widgetClick(View v) {
        int id = v.getId();
        if (id == mTitleRightTxt.getId()) {
            colseActivity(RESULT_CANCELED, null);
        }
    }

    @Override
    protected void onDestroy() {
        ImageLoaderUtils.clearImageCache(mContext);
        mContext = null;
        super.onDestroy();
    }

    private void colseActivity(int resultCode, String title){
        Intent data = new Intent();
        if(!InputValidator.isEmpty(title)){
            data.putExtra("TITLE",title);
        }
        setResult(resultCode,data);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            colseActivity(RESULT_FIRST_USER, null);
        }
        return super.onKeyDown(keyCode, event);
    }
}
