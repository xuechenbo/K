package com.xc.kotlindemo.activity

import android.os.Bundle
import android.util.Log
import com.xc.kotlindemo.base.BaseActivity
import com.xc.kotlindemo.databinding.ActivityMainBinding
import com.xc.kotlindemo.vm.MainViewModel

class MainActivity : BaseActivity<MainViewModel>() {
    //使用官方库的 MainScope()获取一个协程作用域用于创建协程
    private lateinit var binding: ActivityMainBinding
    override fun viewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obser()
    }

    private fun obser() {
        binding.run {
            btn.setOnClickListener {
                mViewModel.getList()
            }
        }
        mViewModel.run {
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