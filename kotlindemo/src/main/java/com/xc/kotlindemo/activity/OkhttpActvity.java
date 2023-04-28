package com.xc.kotlindemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xc.common_base.u.network.BaseResp;
import com.xc.common_base.u.network.NetworkService;
import com.xc.kotlindemo.R;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Callback;

public class OkhttpActvity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun);
//        initOkhttp();
        get();

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get();
            }
        });
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        rotate1(arr, 3);
    }

    public void rotate1(int[] nums, int k) {
        int k1 = k;
        int[] newNums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (k1 != 0) {
                newNums[i] = nums[nums.length - k + i];
                --k1;
            } else {
                newNums[i] = nums[i - k];
            }
        }
        for (int i = 0; i < newNums.length; i++) {
            Log.e("TAG", newNums[i] + "");
        }
    }

    private void get() {
        NetworkService.INSTANCE.getApiService().queryProgressByStaffId("15694804272", "123456").enqueue(new Callback<BaseResp<String>>() {
            @Override
            public void onResponse(retrofit2.Call<BaseResp<String>> call, retrofit2.Response<BaseResp<String>> response) {
                Log.e("TTTTTT", response.body().getErrorMsg());
            }

            @Override
            public void onFailure(retrofit2.Call<BaseResp<String>> call, Throwable t) {
                Log.e("TTTTTT", "response.body().toString()");
            }
        });
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
