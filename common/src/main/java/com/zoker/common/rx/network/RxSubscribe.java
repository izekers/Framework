package com.zoker.common.rx.network;


import com.zoker.common.BaseApplication;
import com.zoker.common.R;
import com.zoker.common.exception.ApiException;
import com.zoker.common.log.Logger;
import com.zoker.utils.NetworkUtil;

import rx.Subscriber;

/**
 * 包装过的访问
 * Created by Zeker on 2017/1/24.
 */
public abstract class RxSubscribe<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Logger.e(e.getMessage(), e);
        if (!NetworkUtil.checkNetwork(BaseApplication.Instance)) {
            onError("网络不可用");
        } else if (e instanceof ApiException) {
            onError(e.getMessage());
        } else {
            onError("");
        }
    }

    public abstract void onError(String message);

}
