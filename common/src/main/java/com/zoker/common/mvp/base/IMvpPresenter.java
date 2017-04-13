package com.zoker.common.mvp.base;

/**
 * Created by Administrator on 2017/3/9.
 */

public interface IMvpPresenter<T extends IMvpView> {
    void attachView(T view);
    void detachView(boolean retainInstance);
}
