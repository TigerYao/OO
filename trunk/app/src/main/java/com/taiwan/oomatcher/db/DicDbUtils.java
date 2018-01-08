package com.taiwan.oomatcher.db;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.taiwan.oomatcher.base.BaseApplication;
import com.taiwan.oomatcher.entity.Implementation;
import com.taiwan.oomatcher.entity.helper.ImplementationHelper;
import com.taiwan.oomatcher.utils.DicUtils;
import com.taiwan.oomatcher.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2018/1/2 22:01
 */

public class DicDbUtils {
    private static DicDbUtils mDicDbUtils;
    /**
     * 单例模式获取上传工具类
     * @return
     */
    public static DicDbUtils getInstance() {
        if (null == mDicDbUtils) {
            mDicDbUtils = new DicDbUtils();
        }
        return mDicDbUtils;
    }

    public void save(List<ImplementationHelper> list){
        try {
            BaseApplication.getInstance(BaseApplication.getInstance()).save(list);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void cleanAll(){
        try {
            BaseApplication.getInstance(BaseApplication.getInstance()).deleteAll(ImplementationHelper.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public List<Implementation> getImplementationList(String type){
        List<Implementation> result = new ArrayList<Implementation>();
        try {
            String language = (String) SPUtils.get(SPUtils.LANGUAGE_CODE,"");
            Selector mSelector = Selector.from(ImplementationHelper.class).where("dicType","=", type).and("langeage", "=", language);
            List<ImplementationHelper> list = BaseApplication.getInstance(BaseApplication.getInstance()).findAll(mSelector);
            if(list != null){
                for(ImplementationHelper item : list){
                    Implementation data = new Implementation();
                    data.setDicItemCode(item.getDicItemCode());
                    data.setDicName(item.getDicName());
                    result.add(data);
                }
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        return result;
    }


}
