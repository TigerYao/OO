package com.taiwan.oomatcher.entity;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/24 01:25
 */

public class NewVersion {
    /**
     * id : 1
     * versionNum : 1
     * version : 1.0
     * appType : 1
     * mustUpdate : 1
     * appUrl : http://www.baidu.com
     * status : 1
     * creatorId : null
     * creator : null
     * createTime : 1506783932000
     * updateTime : 1506783932000
     * content : 第一次上线
     */

    private int id;
    private int versionNum;
    private String version;
    private int appType;
    private int mustUpdate;
    private String appUrl;
    private int status;
    private long creatorId;
    private long creator;
    private long createTime;
    private long updateTime;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public int getMustUpdate() {
        return mustUpdate;
    }

    public void setMustUpdate(int mustUpdate) {
        this.mustUpdate = mustUpdate;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public long getCreator() {
        return creator;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
