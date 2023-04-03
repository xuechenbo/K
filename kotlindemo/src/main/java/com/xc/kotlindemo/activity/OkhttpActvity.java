package com.xc.kotlindemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xc.kotlindemo.R;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpActvity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun);
        initOkhttp();
    }

    private void initOkhttp() {
        //设置超时时间
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newBuilder().connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS);
        String url = "https://www.baidu.com/";

        //post string传参
        MediaType contentType = MediaType.parse("text/x-markdown; charset=utf-8");
        String content = "hello!";
        RequestBody body = RequestBody.create(contentType, content);

        //表单键值对
        RequestBody formBody = new FormBody.Builder()
                .add("name", "name")
                .add("password", "password")
                .build();

        //多类型 字段+图片
        File file = new File("1");
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", "name")
                .addFormDataPart("age", "age")
                .addFormDataPart("image", "", fileBody)
                .build();

        Request getRequest = new Request.Builder()
                .url(url)
//                .post(body)
//                .post(formBody)
//                .post(multipartBody)
                .get()
                .build();

        Call call = okHttpClient.newCall(getRequest);

        new Thread(() -> {
            try {
                //同步请求，要放到子线程执行
                Response response = call.execute();
                Log.i("TAG", "okHttpGet run: response:" + response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
