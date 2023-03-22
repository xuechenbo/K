package com.xc.common_base.u.network

import okhttp3.Interceptor
import okhttp3.Response

class HeadInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder().addHeader("name", "xcb")
        return chain.proceed(builder.build())
    }
}