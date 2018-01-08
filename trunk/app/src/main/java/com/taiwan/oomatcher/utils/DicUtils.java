package com.taiwan.oomatcher.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taiwan.oomatcher.config.Config;
import com.taiwan.oomatcher.entity.Implementation;
import com.taiwan.oomatcher.http.HttpUtils;

import java.util.List;

import static com.umeng.analytics.pro.x.j;

/**
 * 作 者: huanghuojun
 * 描 述: 字典编码列表
 * 版 本:
 * 创建日期: 2018/1/2 22:01
 */

public class DicUtils {
    private static DicUtils mDicUtils;
    /**
     * 单例模式获取上传工具类
     * @return
     */
    public static DicUtils getInstance() {
        if (null == mDicUtils) {
            mDicUtils = new DicUtils();
        }
        return mDicUtils;
    }

    public void getAll() {
        //occupation.职业;hairColor.头发;eyeColor.眼睛;nationality.国家/国籍:city.城市
        JSONObject json = new JSONObject();
        json.put("dicCode", "");
        HttpUtils.sendGetSign("common/dictionary/getDic", json, new HttpUtils.CallBackText() {
            @Override
            public void onSuccess(String responseData) {
                List<Implementation> dataList = JSONArray.parseArray(responseData,Implementation.class);
                if(dataList != null){
                    for (Implementation item : dataList){

                    }
                }
            }

            @Override
            public void onFailure(Exception e, String msg) {

            }
        });
    }
}
