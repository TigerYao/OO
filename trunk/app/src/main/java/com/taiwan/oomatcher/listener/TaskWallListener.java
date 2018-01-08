package com.taiwan.oomatcher.listener;

import com.taiwan.oomatcher.entity.Response.TaskWallListBean;

import java.util.List;

/**
 * Created by zhangxiangcheng
 * Date 2017/12/22
 * Description
 */

public interface TaskWallListener extends OnCallBackListener  {
  void onSuccess(String dataJsonStr);
}
