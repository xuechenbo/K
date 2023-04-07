package com.xc.kotlindemo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xc.kotlindemo.databinding.ActivityHiltBinding
import com.xc.kotlindemo.model.Truck
import com.xc.kotlindemo.model.Driver
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class HiltActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHiltBinding

    @Inject
    lateinit var driver: Driver

    @Inject
    lateinit var truck: Truck

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Inject
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHiltBinding.inflate(layoutInflater)
        setContentView(binding.root)

        truck.deliver()

//        okHttpClient.newCall(Request.Builder().url("").get().build()).execute()

    }

}