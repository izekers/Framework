package com.zoker.common.network.interceptor;

import com.zoker.common.BaseApplication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 缓存Get的数据，OKHTTP调用addNetworkInterceptor添加拦截器
 * Created by Administrator on 2017/5/3.
 */

public class SingleCacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        if (BaseApplication.Instance.isConnnected()) {
            //获取头部信息
            String cacheControl =request.cacheControl().toString();
            return response.newBuilder()
                    .removeHeader("Pragma")//清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .header("Cache-Control", cacheControl)
                    .build();
        }
        return response;
    }

    //单个接口缓存实现
//    @Headers("Cache-Control:public ,max-age=60")
//    @GET("getBusiness.action")//商店信息
//    Call<RestaurantInfoModel> getRestaurantInfo(@Query("userId") String userId,@Query("businessId") String businessId);
}
