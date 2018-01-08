package com.taiwan.oomatcher.view;

import com.taiwan.oomatcher.entity.Response.TaskWallListBean;

import java.util.List;

/**
 * Created by zhangxiangcheng
 * Date 2017/12/22
 * Description
 */

public interface TaskWallView {
  void  updateList(List<TaskWallListBean> beanList,boolean isRefresh);
}
