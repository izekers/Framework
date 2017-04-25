package com.zoker.common.rx;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
/**
 *
 * Created by Zoker on 2017/1/24.
 */
public class Rx2Helper {
    /**
     * 数据加载调度
     * @return 返回服务
     */
    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 当前线程调度
     * @return 返回服务
     */
    public static <T> ObservableTransformer<T, T> applyImmediate() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> observable) {
                return observable.subscribeOn(Schedulers.single())
                        .observeOn(Schedulers.single());
            }
        };
    }
}
