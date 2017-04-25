package com.zoker.common.event;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * 基础的RxBus
 * Created by Zekers on 2016/9/22.
 */
public class Rx2Bus {
    private static Rx2Bus rxBus;
//    private final Subject<Events<?>> _bus = new  SerializedSubject<>(PublishSubject.<Events<?>>create());
    private final Subject<Events<?>> _bus =PublishSubject.create();
    private HashMap<String, CompositeDisposable> mSubscriptionMap;
    private Rx2Bus() {}

    public static Rx2Bus getInstance() {
        if (rxBus == null) {
            synchronized (Rx2Bus.class) {
                if (rxBus == null) {
                    rxBus = new Rx2Bus();
                }
            }
        }
        return rxBus;
    }

    /**
     * 旧版本的
     */
//    public void send(Object o) {
//        _bus.onNext(o);
//    }

    /**
     * 发送事件
     * @param o
     */
    private void send(Events<?> o) {
        _bus.onNext(o);
    }
    public void send(@Events.EventCode int code, Object content) {
        Events<Object> event = new Events<>();
        event.code = code;
        event.content = content;
        send(event);
    }

    /**
     * 返回Observable实例
     * @return
     */
    public Observable<Events<?>> toObservable() {
        return _bus;
    }

    //code的第二种方案，目的是提高自由度
    public Observable<Events<?>> toObservable(final @Events.EventCode int event){
        return _bus .filter(new Predicate<Events<?>>() {
            @Override
            public boolean test(@NonNull Events<?> events) throws Exception {
                return events.code == event;
            }
        });
    }

    /**
     * 是否已有观察者订阅
     * @return
     */
    public boolean hasObservers() {
        return _bus.hasObservers();
    }

    /**
     * 保存订阅后的subscription
     * 用于之后的取消
     * @param key
     * @param subscription
     */
    public void addSubscription(String key, Disposable subscription) {
        if (mSubscriptionMap == null) {
            mSubscriptionMap = new HashMap<>();
        }

        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).add(subscription);
        } else {
            CompositeDisposable compositeSubscription = new CompositeDisposable();
            compositeSubscription.add(subscription);
            mSubscriptionMap.put(key, compositeSubscription);
        }
    }
    /**
     * 保存订阅后的subscription
     * 用于之后的取消
     * @param o
     * @param subscription
     */
    public void addSubscription(Object o, Disposable subscription) {
        if (mSubscriptionMap == null) {
            mSubscriptionMap = new HashMap<>();
        }
        String key = o.getClass().getName();
        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).add(subscription);
        } else {
            CompositeDisposable compositeSubscription = new CompositeDisposable();
            compositeSubscription.add(subscription);
            mSubscriptionMap.put(key, compositeSubscription);
        }
    }

    /**
     * 取消订阅
     * @param o
     */
    public void unSubscribe(Object o) {
        if (mSubscriptionMap == null) {
            return;
        }

        String key = o.getClass().getName();
        if (!mSubscriptionMap.containsKey(key)){
            return;
        }
        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).dispose();
        }

        mSubscriptionMap.remove(key);
    }




    public static SubscriberBuilder getSubscriber(){
        return new SubscriberBuilder();
    }

    public static class SubscriberBuilder {
        private int event;
        private Consumer<? super Events<?>> onNext;
        private Consumer<Throwable> onError;
        public Disposable create() {
            return _created();
        }


        public SubscriberBuilder setEvent(@Events.EventCode int event) {
            this.event = event;
            return this;
        }

        private Disposable _created() {
            return Rx2Bus.getInstance().toObservable()
                    .filter(new Predicate<Events<?>>() {
                        @Override
                        public boolean test(@NonNull Events<?> events) throws Exception {
                            return events.code == event;
                        }
                    }).subscribe(onNext,onError==null?new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            throwable.printStackTrace();
                        }
                    }:onError);
        }

        public SubscriberBuilder onNext(Consumer<? super Events<?>> action) {
            this.onNext = action;
            return this;
        }

        public SubscriberBuilder onError(Consumer<Throwable> action) {
            this.onError = action;
            return this;
        }
    }
}
