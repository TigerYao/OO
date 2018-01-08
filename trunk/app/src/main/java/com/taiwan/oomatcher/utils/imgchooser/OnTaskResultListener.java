package com.taiwan.oomatcher.utils.imgchooser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作 者: huanghuojun
 * 描 述: 异步任务执行完后回调接口
 * 版 本:
 * 创建日期: 2016/4/11 14:07
 */
public interface OnTaskResultListener {
    /**
     * 回调函数
     * @param success 是否成功
     * @param error 错误信息，[成功的时候错误信息为空]
     * @param dirMap 所有图片按文件夹分类map
     * @param allImage 获取到的手机所有图片
     */
    void onResult(boolean success, String error, ArrayList<String> allImage, Map<String, List<String>> dirMap);
}
