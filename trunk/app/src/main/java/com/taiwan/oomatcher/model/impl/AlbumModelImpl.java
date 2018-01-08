package com.taiwan.oomatcher.model.impl;

import com.alibaba.fastjson.JSONObject;
import com.taiwan.oomatcher.config.Config;
import com.taiwan.oomatcher.entity.Response.BaseResponse;
import com.taiwan.oomatcher.http.HttpUtils;
import com.taiwan.oomatcher.listener.AlbumListener;
import com.taiwan.oomatcher.model.AlbumModel;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/18 22:47
 */

public class AlbumModelImpl implements AlbumModel {
    private AlbumListener listener;

    public AlbumModelImpl(AlbumListener listener){
        this.listener = listener;
    }

    @Override
    public void addComment(int albumId, String content) {

    }

    @Override
    public void createAlbum(String albumName) {

    }

    @Override
    public void doFollower(int albumId) {

    }

    @Override
    public void getMyFavoriteList(int pageNo) {

    }

    @Override
    public void getMyPostList(int pageNo) {

    }

    @Override
    public void list(int pageNo, int pageSize) {

    }

    @Override
    public void newPost() {

    }

    @Override
    public void removeAttachment(int attachmentId) {

    }

    @Override
    public void removeComment(int commentId) {

    }

    @Override
    public void removeFollower(int albumId) {

    }
}
