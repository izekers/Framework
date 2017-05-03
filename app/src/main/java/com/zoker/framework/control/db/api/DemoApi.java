package com.zoker.framework.control.db.api;

import retrofit2.http.POST;
import rx.Observable;

/**
 *
 * Created by Zoker on 2017/4/13.
 */

public interface DemoApi {
    @POST("demofunction/")
    Observable<String> demoFunction();
}
