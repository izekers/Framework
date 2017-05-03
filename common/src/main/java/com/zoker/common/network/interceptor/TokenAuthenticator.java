package com.zoker.common.network.interceptor;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by Administrator on 2017/5/3.
 */

/**
 * 只有返回HTTP的状态码为401时，才会使用Authenticator接口
 * client.setAuthenticator(new TokenAuthenticator());
 */
public class TokenAuthenticator implements Authenticator{
    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        String token=null;

//        //取出本地的refreshToken
//        String refreshToken = "sssgr122222222";
//
//        // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
//        ApiService service = ServiceManager.getService(ApiService.class);
//        Call<String> call = service.refreshToken(refreshToken);
//
//        //要用retrofit的同步方式
//        String newToken = call.execute().body();

        return response.request().newBuilder()
                .header("token", token)
                .build();
    }
}
