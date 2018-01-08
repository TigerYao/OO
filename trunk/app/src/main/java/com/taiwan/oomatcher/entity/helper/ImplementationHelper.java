package com.taiwan.oomatcher.entity.helper;

import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2018/1/2 22:15
 */
@Table(name="oomatcher_implementation")
public class ImplementationHelper {
    @Id
    public long id;
    private String dicItemCode;
    private String dicName;
    private String dicType;
    private String langeage;

    public String getDicItemCode() {
        return dicItemCode;
    }

    public void setDicItemCode(String dicItemCode) {
        this.dicItemCode = dicItemCode;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLangeage() {
        return langeage;
    }

    public void setLangeage(String langeage) {
        this.langeage = langeage;
    }

    public String getDicType() {
        return dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType;
    }
}
