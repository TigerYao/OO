package com.taiwan.oomatcher.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.taiwan.oomatcher.base.BaseApplication;
import com.taiwan.oomatcher.config.Config;
import com.taiwan.oomatcher.entity.Response.LoginResponse;
import com.taiwan.oomatcher.utils.DbUtils;
import com.taiwan.oomatcher.utils.EncodeUtils;
import com.taiwan.oomatcher.utils.SPUtils;
import com.taiwan.oomatcher.utils.SignTools;
import com.taiwan.oomatcher.utils.Tools;

import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/17 14:55
 */

public class HttpUtils {
    private volatile static com.lidroid.xutils.HttpUtils mHttp = null;
    private static com.lidroid.xutils.HttpUtils getInstance() {
        if (mHttp == null) {
            synchronized (HttpUtils.class) {
                if (mHttp == null) {
                    mHttp = new com.lidroid.xutils.HttpUtils();
                }
            }
        }
        return mHttp;
    }
    static {
        getInstance().configSoTimeout(Config.HTTP_SOTIMEOUT);
        getInstance().configTimeout(Config.HTTP_TIMEOUT);
    }

    private static RequestParams getRequestParams() {
        RequestParams params = new RequestParams();
        params.addHeader("Content-Type", "application/x-www-form-urlencoded");
//        params.addHeader("token", (String) SPUtils.get(SPUtils.KEY_TOKEN, ""));
//        params.addHeader("hardware", (String) SPUtils.get(SPUtils.KEY_DEVICE, ""));
//        params.addHeader("app_key", (String) SPUtils.get(SPUtils.KEY_USERNAME, ""));
        return params;
    }

    public static void sendPostSign(String url, JSONObject paramsJSON, final CallBackText callBack) {
        if(paramsJSON == null)
            paramsJSON = new JSONObject();
        paramsJSON.put("token", (String)SPUtils.get(SPUtils.TOKEN, ""));
        paramsJSON.put("appTypeId", 2);
        paramsJSON.put("appId", Tools.getAppVersionCode(BaseApplication.getInstance()));
        paramsJSON.put("language", (String)SPUtils.get(SPUtils.LANGUAGE_CODE, ""));
        paramsJSON.put("timeStamp", System.currentTimeMillis()+"");
        paramsJSON.put("sign",  SignTools.getSign(paramsJSON, Config.KEY));
        PostSign(Config.COMMON + url, paramsJSON, new CallBackText() {
            @Override
            public void onSuccess(String str) {
                JSONObject json = JSONObject.parseObject(str);
                if(json.getBoolean("success")){
                    callBack.onSuccess(json.getString("data"));
                }else{
                    callBack.onFailure(null, json.getString("msg"));
                }
            }

            @Override
            public void onFailure(Exception e, String msg) {
                System.out.println(e.toString());
                callBack.onFailure(e, "网络开个小差~~~");
            }
        });
    }

    public static void sendGetSign(String url, JSONObject paramsJSON, final CallBackText callBack) {
        if(paramsJSON == null)
            paramsJSON = new JSONObject();
        paramsJSON.put("token", (String)SPUtils.get(SPUtils.TOKEN, ""));
        paramsJSON.put("appTypeId", 2);
        paramsJSON.put("appId", Tools.getAppVersionCode(BaseApplication.getInstance()));
        paramsJSON.put("language", (String)SPUtils.get(SPUtils.LANGUAGE_CODE, ""));
        paramsJSON.put("timeStamp", System.currentTimeMillis()+"");
        paramsJSON.put("sign",  SignTools.getSign(paramsJSON, Config.KEY));
        String newUrl = url + joinParams(getMapForJson(paramsJSON));
        GetSign(Config.COMMON + newUrl, new CallBackText() {
            @Override
            public void onSuccess(String str) {
                JSONObject json = JSONObject.parseObject(str);
                if(json.getBoolean("success")){
                    callBack.onSuccess(json.getString("data"));
                }else{
                    callBack.onFailure(null, json.getString("msg"));
                }
            }

            @Override
            public void onFailure(Exception e, String msg) {
                callBack.onFailure(e, "网络开个小差~~~");
            }
        });
    }

    private static Map<String, Object> getMapForJson(JSONObject json) {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        Iterator<String> keys = json.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            valueMap.put(key, json.get(key));
        }
        return valueMap;
    }

    private static String joinParams(Map<String, Object> params) {
        if (params != null) {
            Iterator<String> it = params.keySet().iterator();
            StringBuffer sb = null;
            while (it.hasNext()) {
                String key = it.next();
                if (sb == null) {
                    sb = new StringBuffer();
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(key);
                sb.append("=");
                sb.append(params.get(key));
            }
            if (sb != null) {
                return sb.toString();
            }
        }
        return "";
    }

    private interface CallBackJSONObject {
        public void onSuccess(JSONObject json);

        public abstract void onFailure(Exception e, String msg);
    }

    public interface CallBackText {
        public void onSuccess(String responseData);

        public abstract void onFailure(Exception e, String msg);
    }

    /**
     * 整个应用传输的基类POST
     *
     * @param url
     * @param paramsJSON
     * @param callBack
     */
    private static void PostSign(String url, JSONObject paramsJSON, final CallBackText callBack) {
        RequestParams params = getRequestParams();
        Tools.println("-----------------------HttpTools 【Post发送请求】-------------------------");
        Tools.println(url);
        Tools.println("-----------------------HttpTools 【Post发送请求】-------------------------");

        JSONObject jsonObj = JSON.parseObject(paramsJSON.toJSONString());
        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
            params.addBodyParameter(entry.getKey(), entry.getValue()+"");
        }
        Tools.println("-----------------------HttpTools 【Post发送参数】-------------------------");
        Tools.println(paramsJSON.toJSONString());
        Tools.println("-----------------------HttpTools 【Post发送参数】-------------------------");
        getInstance().send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onFailure(HttpException e, String msg) {
                Tools.println("-----------------------HttpTools onFailure 【返回】-------------------------");
                Tools.println(msg);
                Tools.println("-----------------------HttpTools onFailure 【返回】-------------------------");

                callBack.onFailure(e, "网络开个小差~~~");
            }

            @Override
            public void onSuccess(ResponseInfo<String> msg) {
                Tools.println("-----------------------HttpTools onSuccess 【Post 返回】-------------------------");
                Tools.println(msg.result);
                Tools.println("-----------------------HttpTools onSuccess 【Post 返回】-------------------------");

                callBack.onSuccess(msg.result);
            }
        });
    }
    /**
     * 整个应用传输的基类GET
     *
     * @param url
     * @param callBack
     */
    private static void GetSign(String url, final CallBackText callBack) {
        getInstance().configCurrentHttpCacheExpiry(0);
        getInstance().configDefaultHttpCacheExpiry(0);
        Tools.println("-----------------------HttpTools 【Get 发送请求】-------------------------");
        Tools.println(url);
        Tools.println("-----------------------HttpTools 【Get 发送请求】-------------------------");
        getInstance().send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onFailure(HttpException e, String msg) {
                Tools.println("-----------------------HttpTools onFailure 【Get 返回】-------------------------");
                Tools.println(msg);
                Tools.println("-----------------------HttpTools onFailure 【Get 返回】-------------------------");
                callBack.onFailure(e, "网络开个小差~~~");
            }

            @Override
            public void onSuccess(ResponseInfo<String> msg) {
                Tools.println("-----------------------HttpTools onSuccess 【Get 返回】-------------------------");
                Tools.println(msg.result);
                Tools.println("-----------------------HttpTools onSuccess 【Get 返回】-------------------------");
                callBack.onSuccess(msg.result);
            }
        });
    }
    /**
     * 整个应用传输的基类DELETE
     *
     * @param url
     * @param callBack
     */
    private static void sendDelete(String url, final CallBackText callBack) {
        RequestParams params = getRequestParams();
        Tools.println("-----------------------HttpTools 【发送请求】-------------------------");
        Tools.println(url);
        Tools.println("-----------------------HttpTools 【发送请求】-------------------------");
        getInstance().send(HttpRequest.HttpMethod.DELETE, url, params, new RequestCallBack<String>() {
            @Override
            public void onFailure(HttpException e, String msg) {
                Tools.println("-----------------------HttpTools onFailure 【返回】-------------------------");
                Tools.println(msg);
                Tools.println("-----------------------HttpTools onFailure 【返回】-------------------------");
                callBack.onFailure(e, "网络开个小差~~~");
            }

            @Override
            public void onSuccess(ResponseInfo<String> msg) {
                Tools.println("-----------------------HttpTools onSuccess 【返回】-------------------------");
                Tools.println(msg.result);
                Tools.println("-----------------------HttpTools onSuccess 【返回】-------------------------");
                callBack.onSuccess(msg.result);
            }
        });
    }
    /**
     * 整个应用传输的基类PUT
     *
     * @param url
     * @param entity
     * @param callBack
     */
    private static void sendPut(String url, StringEntity entity, final CallBackText callBack) {
        RequestParams params = getRequestParams();
        Tools.println("-----------------------HttpTools 【发送请求】---------- ----");
        Tools.println(url);
        Tools.println("-----------------------HttpTools 【发送请求】-------------------------");
        try {
            params.setBodyEntity(entity);

            getInstance().send(HttpRequest.HttpMethod.PUT, url, params, new RequestCallBack<String>() {
                @Override
                public void onFailure(HttpException e, String msg) {
                    Tools.println("-----------------------HttpTools onFailure 【返回】-------------------------");
                    Tools.println(msg);
                    Tools.println("-----------------------HttpTools onFailure 【返回】-------------------------");
                    callBack.onFailure(e, "网络开个小差~~~");
                }

                @Override
                public void onSuccess(ResponseInfo<String> msg) {
                    Tools.println("-----------------------HttpTools onSuccess 【返回】-------------------------");
                    Tools.println(msg.result);
                    Tools.println("-----------------------HttpTools onSuccess 【返回】-------------------------");
                    callBack.onSuccess(msg.result);
                }
            });
        } catch (Exception e1) {
            callBack.onFailure(e1, e1.toString());
            e1.printStackTrace();
        }
    }
}
