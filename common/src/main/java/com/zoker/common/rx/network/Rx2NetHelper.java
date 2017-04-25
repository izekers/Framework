package com.zoker.common.rx.network;

import android.content.Context;

import com.google.gson.Gson;
import com.zoker.common.cache.CacheManager;
import com.zoker.common.exception.ApiException;
import com.zoker.common.log.Logger;
import com.zoker.common.model.data.DataWrapper;
import com.zoker.common.rx.RxHelper;
import com.zoker.utils.NetworkUtil;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Rx相关,添加了网络请求相关封装
 * Created by zekers on 2017/1/24.
 */
public class Rx2NetHelper extends RxHelper {
    /**
     * 处理请求
     */
    public static ObservableTransformer<DataWrapper, DataWrapper> handleDataWrapper() {
        return new ObservableTransformer<DataWrapper, DataWrapper>() {
            @Override
            public ObservableSource<DataWrapper> apply(@NonNull Observable<DataWrapper> tObservable) {
                return tObservable.flatMap(new Function<DataWrapper, Observable<DataWrapper>>() {
                    @Override
                    public Observable<DataWrapper> apply(DataWrapper tDataWrapper) {
                        if (tDataWrapper == null)
                            return Observable.error(new ApiException("未知错误"));
                        if (tDataWrapper.success()) {
                            return createData(tDataWrapper);
                        } else {
                            if (tDataWrapper.getMessage() == null)
                                return Observable.error(new ApiException("未知错误"));
                            else
                                return Observable.error(new ApiException(tDataWrapper.getMessage()));
                        }
                    }
                });
            }
        };
    }

    /**
     * 处理请求
     */
    public static <T> ObservableTransformer<DataWrapper<T>, T> handleResult() {
        return new ObservableTransformer<DataWrapper<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<DataWrapper<T>> tObservable) {
                return tObservable.flatMap(new Function<DataWrapper<T>, Observable<T>>() {
                    @Override
                    public Observable<T> apply(DataWrapper<T> tDataWrapper) {
                        if (tDataWrapper == null)
                            return Observable.error(new ApiException("未知错误"));

                        if (tDataWrapper.success()) {
                            return createData(tDataWrapper.getResult());
                        } else {
                            if (tDataWrapper.getMessage() == null)
                                return Observable.error(new ApiException("未知错误"));
                            else
                                return Observable.error(new ApiException(tDataWrapper.getMessage()));
                        }
                    }
                });
            }
        };
    }

    /**
     * 创建处理完数据的请求
     */
    public static <T> Observable<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(data);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }

    /**
     * 缓存加载者
     * 可以通过设置重写该加载者修改加载机制
     */
    public interface ICacheLoad<T> {

        T load(Context context, String cacheKey);

        void save(String cacheKey, T t);
    }

    /**
     * 缓存加载者
     * 可以通过设置重写该加载者修改加载机制
     */
    public static abstract class CacheLoad<T> implements ICacheLoad<T> {
        private CacheManager.CacheModel cacheModel = CacheManager.CacheModel.CONFIG_CACHE_MODEL_LONG;
        private CacheManager.CacheStrategy cacheStrategy;

        public abstract Type getType();

        public CacheLoad<T> setCacheModel(CacheManager.CacheModel cacheModel) {
            this.cacheModel = cacheModel;
            return this;
        }

        public CacheLoad<T> setCacheStrategy(CacheManager.CacheStrategy cacheStrategy) {
            this.cacheStrategy = cacheStrategy;
            return this;
        }

        public T load(Context context, String cacheKey) {
            String cacheBody = null;
            if (cacheStrategy != null) {
                cacheBody = CacheManager.load(context, cacheKey, cacheStrategy);
                Logger.d("loadfromcache:cacheStrategy:" + cacheBody);
            } else if (cacheModel != null) {
                cacheBody = CacheManager.load(context, cacheKey, cacheModel);
                Logger.d("loadfromcache:cacheModel:" + cacheBody);
            }

            if (cacheBody != null && getType() != null) {
                return new Gson().fromJson(cacheBody, getType());
            }
            return null;
        }

        public void save(String cacheKey, T t) {
            Logger.d("savefromsave");
            CacheManager.save(cacheKey, t);
        }
    }

    /**
     * 缓存优先加载
     *
     * @param context      上下文
     * @param cacheKey     保存路径
     * @param forceRefresh 是否缓存
     * @param fromNetWork  网络请求
     * @param cacheLoad    缓存设置
     */
    public static <T> Observable<T> preCache(final Context context, final String cacheKey, boolean forceRefresh, Observable<T> fromNetWork, final ICacheLoad<T> cacheLoad) {
        Observable<T> fromCache = Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> emitter) throws Exception {
                T cache = cacheLoad.load(context, cacheKey);
                if (cache != null) {
                    emitter.onNext(cache);
                } else {
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        /**
         * 这里的fromNetWork 不需要指定schedule,在handleRequest中已经变换了
         */
        fromNetWork = fromNetWork.map(new Function<T, T>() {
            @Override
            public T apply(@NonNull T result) throws Exception {
                cacheLoad.save(cacheKey, result);
                return result;
            }
        });

        if (forceRefresh) {
            return fromNetWork;
        } else
            return Observable.concat(fromCache, fromNetWork).firstElement().toObservable();
    }

    /**
     * 网络优先加载
     */
    public static <T> Observable<T> netCache(final Context context, final String cacheKey, final boolean isFresh, final Observable<T> fromNetWork, final ICacheLoad<T> cacheLoad) {
        return fromNetWork.map(new Function<T, T>() {
            @Override
            public T apply(@NonNull T result) throws Exception {
                cacheLoad.save(cacheKey, result);
                return result;
            }
        }).onErrorResumeNext(new Function<Throwable, Observable<? extends T>>() {
            @Override
            public Observable<? extends T> apply(@NonNull Throwable throwable) throws Exception {
                Logger.e(throwable.getMessage(), throwable);
                if (!NetworkUtil.checkNetwork(context)) {
                    if (cacheLoad != null && isFresh)
                        return createData(cacheLoad.load(context, cacheKey));
                }
                return Observable.error(throwable);
            }
        });
    }

    /**
     * 隐藏式下载
     * 最后显示的都是在缓存中的数据
     */
    public static <T> Observable<T> hidCache(final Context context, final String cacheKey, boolean isFresh, Observable<T> fromNetWork, final ICacheLoad<T> cacheLoad) {
        Observable<T> fromCache = Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> emitter) throws Exception {
                T cache = cacheLoad.load(context, cacheKey);
                if (cache != null) {
                    emitter.onNext(cache);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        /**
         * 这里的fromNetWork 不需要指定schedule,在handleRequest中已经变换了
         */
        fromNetWork = fromNetWork.map(new Function<T, T>() {
            @Override
            public T apply(@NonNull T result) throws Exception {
                Logger.d("loadfromNet");
                cacheLoad.save(cacheKey, result);
                T cache = cacheLoad.load(context, cacheKey);
                return cache;
            }
        });

        if (!isFresh) {
            return fromNetWork;
        } else
            return Observable.merge(fromCache, fromNetWork);
    }

    /**
     * 打log
     *
     * @param <T>
     */
    public static class LogFunc<T> implements Function<T, T> {
        @Override
        public T apply(@NonNull T t) throws Exception {
            Logger.d(new Gson().toJson(t));
            return t;
        }
    }
}
