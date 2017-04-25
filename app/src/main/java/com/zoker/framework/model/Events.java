package com.zoker.framework.model;

import android.app.Activity;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;

import com.zoker.common.event.RxBus;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import rx.functions.Action1;

/**
 * Created by Administrator on 2017/4/25.
 */

public class Events<T> extends com.zoker.common.event.Events<T>{

    public static final int sss = 555; //点击事件

    public void in( int i){

    }
    public void set(){
        in(sss);
        in(23);
    }

    public void init(){
        RxBus.getInstance().send(Events.First,null);
        RxBus.getSubscriber().setEvent(Events.First)
                .onNext(new Action1<com.zoker.common.event.Events<?>>() {
                    @Override
                    public void call(com.zoker.common.event.Events<?> events) {
                        String s=events.getContent();
                    }
                });
    }
}
