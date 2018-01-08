package com.taiwan.oomatcher.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/26 17:40
 */

public class SignTools {
    public static String getSign(JSONObject paramsJSON, String key) {
        Map<String, String> params = new HashMap<String, String>();
        JSONObject jsonObj = JSON.parseObject(paramsJSON.toJSONString());
        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
            params.put(entry.getKey(), entry.getValue()+"");
        }
        return getSign(params, key);
    }

    /**
     * 生成签名
     *
     * @param paramMap
     * @param key
     * @return
     */
    public static String getSign(Map<String, String> paramMap, String key) {
        String string, sign = "";
        SortedMap<String, String> sortedMap = filterMap(paramMap);
        if (sortedMap != null && sortedMap.size() > 0) {
            StringBuffer stringBuffer = new StringBuffer("");
            Iterator<String> iterator = sortedMap.keySet().iterator();
            while (iterator.hasNext()) {
                String name = iterator.next();
                String value = sortedMap.get(name);
                stringBuffer.append(name).append("=").append(value).append("&");
            }
            string = stringBuffer.substring(0, stringBuffer.length() - 2);
            sign = MD5Util.getmd5(MD5Util.getmd5(key + string) + key);

        }
        return sign;

    }
    public static SortedMap<String, String> filterMap(Map<String, String> sArray) {

    SortedMap<String, String> result = new TreeMap<String, String>();

    if (sArray == null || sArray.size() <= 0) {
        return result;
    }

    for (String key : sArray.keySet()) {
        String value = sArray.get(key);
        if (value == null || value.equals("") || key.equalsIgnoreCase("sign")) {
            continue;
        }
        result.put(key, value);
    }

    return result;
}
}
