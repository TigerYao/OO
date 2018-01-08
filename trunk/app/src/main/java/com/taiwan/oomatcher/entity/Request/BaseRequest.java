package com.taiwan.oomatcher.entity.Request;

import com.taiwan.oomatcher.base.BaseApplication;
import com.taiwan.oomatcher.utils.Tools;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/25 20:43
 */

public class BaseRequest {
    private String token;
    private int appTypeId = 2;
    private int appId = Tools.getAppVersionCode(BaseApplication.getInstance());
    private String language;
    private String timeStamp;
    private String sign;
}
