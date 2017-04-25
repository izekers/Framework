package com.zoker.common.rx.network;


import com.zoker.common.BaseApplication;
import com.zoker.common.exception.ApiException;
import com.zoker.common.log.Logger;
import com.zoker.utils.NetworkUtil;

import org.reactivestreams.Subscription;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subscribers.ResourceSubscriber;
/**
 * 包装过的访问
 * Created by Zoker on 2017/1/24.
 */
public abstract class Rx2Subscriber<T> extends ResourceSubscriber<T> {
//    @Override
//    public void onSubscribe(Subscription s) {
//        //这一步是必须，我们通常可以在这里做一些初始化操作，调用request()方法表示初始化工作已经完成
//        //调用request()方法，会立即触发onNext()方法
//        //在onComplete()方法完成，才会再执行request()后边的代码
//        s.request(Long.MAX_VALUE);
//    }

    @Override
    public void onError(Throwable e) {
        Logger.e(e.getMessage(), e);
        if (!NetworkUtil.checkNetwork(BaseApplication.Instance)) {
            onError("网络不可用");
        } else if (e instanceof ApiException) {
            onError(e.getMessage());
        } else {
            onError("未知错误，请稍后重试");
        }
    }

    public abstract void onError(String message);

    @Override
    public void onComplete() {

    }
}
