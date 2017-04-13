package com.zoker.framework.action;

import android.util.Log;

import com.zoker.common.exception.MethodException;
import com.zoker.common.network.HttpMethod;
import com.zoker.framework.api.DemoApi;

import rx.Observable;

/**
 *
 * Created by Zoker on 2017/4/13.
 */

public class DemoAction {
    private DemoApi service;
    {
        service= HttpMethod.getService(HttpMethod.API_HTTP, DemoApi.class);
//        HttpMethod.addRetrofit("demo","http://demo/");
//        service= HttpMethod.getService("demo", DemoApi.class);
        if (service==null)
            throw new MethodException(MethodException.API_NULL);
    }

    public Observable<String> getItem(String token, String key){
        return service.demoFunction();
    }
}
