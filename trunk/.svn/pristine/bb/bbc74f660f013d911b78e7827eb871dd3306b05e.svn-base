package com.taiwan.oomatcher.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作 者: huanghuojun
 * 描 述: 图库文件夹列表展示对象
 * 版 本:
 * 创建日期: 2018/1/2 17:52
 */

public class AlbumListBean implements Parcelable {

    private String imgPath;
    private String dirName;
    private int imgCount;

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public int getImgCount() {
        return imgCount;
    }

    public void setImgCount(int imgCount) {
        this.imgCount = imgCount;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imgPath);
        dest.writeString(this.dirName);
        dest.writeInt(this.imgCount);
    }

    public AlbumListBean() {
    }

    protected AlbumListBean(Parcel in) {
        this.imgPath = in.readString();
        this.dirName = in.readString();
        this.imgCount = in.readInt();
    }

    public static final Parcelable.Creator<AlbumListBean> CREATOR = new Parcelable.Creator<AlbumListBean>() {
        @Override
        public AlbumListBean createFromParcel(Parcel source) {
            return new AlbumListBean(source);
        }

        @Override
        public AlbumListBean[] newArray(int size) {
            return new AlbumListBean[size];
        }
    };
}
