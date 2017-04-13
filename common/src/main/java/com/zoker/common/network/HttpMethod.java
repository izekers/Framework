package com.zoker.common.network;

import com.zoker.common.network.converter.StringConverterFactory;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit网络设置
 * Created by zekers on 2016/12/23.
 */
public class HttpMethod {
    private static HashMap<String, Retrofit> https;
    public static final String API_HTTP = "http";

    static {
        https = new HashMap<>();
        addRetrofit(API_HTTP, "http://edu.depts.bingosoft.net:8084/home/");
    }

    //配置基础的Retrofit
    private static Retrofit createRetrofit(String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder();
        setConfig(builder);
        return builder.baseUrl(baseUrl)
                .build();
    }

    protected static void setConfig(Retrofit.Builder builder) {
        builder.addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(OkHttpClientManager.getClient());
    }

    public static void addRetrofit(String key, String baseUrl) {
        https.put(key, createRetrofit(baseUrl));
    }

    public static Retrofit getRetrofit(String key) {
        return https.get(key);
    }

    /**
     * 初始化服务
     * @param cls 服务类型
     * @param <T> 服务类型
     * @return 返回服务
     */
    public static <T> T getService(String key, Class<T> cls) {
        if (getRetrofit(key) != null)
            return getRetrofit(key).create(cls);
        else
            return null;
    }
}
