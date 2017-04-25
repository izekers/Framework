package com.zoker.framework.model;

/**
 * Created by Administrator on 2017/4/21.
 */

public class ItemModel1 {
    private String title;

    public ItemModel1(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
