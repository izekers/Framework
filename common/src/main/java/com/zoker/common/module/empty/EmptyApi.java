package com.zoker.common.module.empty;

import com.zoker.common.BaseApplication;

/**
 * Created by Administrator on 2017/5/3.
 */

public abstract class EmptyApi {
    abstract String getModuleName();
    protected void doNothing(){
        BaseApplication.Instance.showToast("不支持"+getModuleName()+"模块");
    }
}
