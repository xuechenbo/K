package com.xc.common_base.u.network;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (request.header("Authentication") == null) {
            Request.Builder builder = request.newBuilder();
            builder.addHeader("Authentication", getNewToken());
            request = builder.build();
        }
        Response response = chain.proceed(request);
        if (isTokenExpired(response)) {
            //token过期 跳转到登录页
        }
        return response;
    }

    /**
     * 根据Response，判断Token是否失效
     *
     * @param response
     * @return
     */
    private boolean isTokenExpired(Response response) {
        if (response.code() == 401) {
            return true;
        }
        return false;
    }

    /**
     * 同步请求方式，获取最新的Token
     *
     * @return
     */
    private String getNewToken() throws IOException {
        return "newToken";
    }
}