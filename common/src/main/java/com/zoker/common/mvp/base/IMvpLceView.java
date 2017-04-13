package com.zoker.common.mvp.base;

/**
 * LCE(Loading-Content-Error)
 * Created by Zoker on 2017/4/12.
 */
public interface IMvpLceView<M> extends IMvpView {
    public void showLoading(boolean pullToRefresh);
    public void showContent();
    public void showError(Throwable e, boolean pullToRefresh);
    public void setData(M data);
    public void loadData(boolean pullToRefresh);
}
