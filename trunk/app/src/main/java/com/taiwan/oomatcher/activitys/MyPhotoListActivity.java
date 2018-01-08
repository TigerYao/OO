package com.taiwan.oomatcher.activitys;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.base.BaseActivity;

/**
 * Created by TigerYao on 2018/1/7.
 */

public class MyPhotoListActivity extends BaseActivity {

    private RecyclerView mPhotoList;
    private static final int GRIDE_TYPE = 1;
    private static final int LIST_TYPE = 2;
    private int mCurrentType = GRIDE_TYPE;
    private LinearLayoutManager mListManager;
    private GridLayoutManager mGridManager;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_photo_list);
        mPhotoList = (RecyclerView) findViewById(R.id.photo_list);
    }

    private void changeLayout(int type){
        if(type == mCurrentType) return;
        switch (type){
            case GRIDE_TYPE:
                break;
            case LIST_TYPE:
                break;
        }
        mCurrentType = type;
    }
    @Override
    protected void init() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void widgetClick(View v) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.grid_btn:
                break;
            case R.id.list_btn:
                break;
            case R.id.back:
                finish();
                break;
            default:
                super.onClick(v);
        }

    }
}
