package com.zoker.common.network;

/**
 * 分页数据加载
 * Created by Zoker on 2017/4/25.
 */

public abstract class PageDataProvider<T> {
    private static final String TAG=PageDataProvider.class.getSimpleName();

    private int pageCount=10;
    private int page=0;

    //还有更多数据
    private boolean hasMore = true;

    //当前页码
    public void setCurrentPage(int page){
        this.page=page;
    }

    //每次搜索个数
    public void setPageCount(int pageCount){
        this.pageCount=pageCount;
    }

    public void next(){
        page++;
        load();
    }

    public void pre(){
        page--;
        load();
    }

    public void reset(){
        page=0;
        next();
    }

    public abstract T setData(int page,int pageCount);

    public abstract void getData(T data);

    private void load(){

    }
}
