package com.xc.kotlindemo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.xc.kotlindemo.databinding.ActivityMainBinding
import com.xc.kotlindemo.vm.MainViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    //使用官方库的 MainScope()获取一个协程作用域用于创建协程
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        obser()
    }

    private fun obser() {
        binding.run {
            btn.setOnClickListener {
                mainViewModel.getModel()
            }
        }

        mainViewModel.run {
            logtType.observeForever {
                Log.e("logtType", "$it")
            }
            loadingLiveData.observeForever {
                if (it) {
                    Log.e("loadingLiveData", "请求开始")
                } else {
                    Log.e("loadingLiveData", "请求结束")
                }

            }
            errorLiveData.observeForever {
                Log.e("errorLiveData", "$it")
            }
        }
    }

    //Repository层
    private fun log(msg: Any?) = println("[${Thread.currentThread().name}] $msg")
}