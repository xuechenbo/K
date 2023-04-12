package com.xc.kotlindemo.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.xc.common_base.u.base.BaseActivity
import com.xc.kotlindemo.LeetCodeJava
import com.xc.kotlindemo.databinding.ActivityMainBinding
import com.xc.kotlindemo.vm.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

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


    fun test() {
        lifecycleScope.launch {
            flow {
                for (i in 1..3) {
                    delay(100)
                    //发射数据
                    emit(i)
                }
            }.collect { value -> Log.d("TAG", "value :${value}") }
        }
    }

    fun testFlow2() {
        val list = listOf(1, 2, 3, 4)
        val asFlow = list.asFlow()
        val testFlow = flowOf(65, 66, 67)
        lifecycleScope.launch {
            testFlow.collect {
                println("输出：$it")
                println()
            }

            asFlow.collect {

            }
        }
    }

    private fun obser() {
        binding.run {
            btn.setOnClickListener {
//                mViewModel.getList()
                val lengthOfLongestSubstring22 = LeetCodeJava.lengthOfLongestSubstring22("pwwkew")
                Log.e("tag", "$lengthOfLongestSubstring22")
            }
        }
        mViewModel?.run {
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
        lifecycleScope.launchWhenCreated {
            mViewModel.stateFlow.collect {
                Log.e("ttttt", it.toString())
            }
        }
        mViewModel.SendFlow()

    }

    //Repository层
    private fun log(msg: Any?) = println("[${Thread.currentThread().name}] $msg")


}