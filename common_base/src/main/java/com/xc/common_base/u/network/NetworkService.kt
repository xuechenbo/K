package com.xc.common_base.u.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object NetworkService {
    private const val DEFAULT_TIME_OUT = 5  //超时时间5秒
    private const val DEFAULT_READ_TIME_OUT = 10

    val mBuilder = OkHttpClient.Builder()
        .connectTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)//连接超时时间
        .readTimeout(DEFAULT_READ_TIME_OUT.toLong(), TimeUnit.SECONDS)//读操作超时时间


    // 日志显示级别
    val level = HttpLoggingInterceptor.Level.BODY

    //新建log拦截器
    val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
        Log.e(
            "HTTP",
            "OkHttp====Message:$message"
        )
    }).also { it ->
        it.level = level
        mBuilder.addInterceptor(it)
        mBuilder.addInterceptor(TokenInterceptor())
        //随便加的请求头
//        mBuilder.addInterceptor(HeadInterceptor())
    }


    private val retrofit = Retrofit.Builder()
        .client(mBuilder.build())
        .baseUrl(Constant.BASE_IP)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create<ApiService>()


}