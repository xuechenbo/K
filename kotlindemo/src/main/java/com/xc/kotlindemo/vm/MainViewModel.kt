package com.xc.kotlindemo.vm

import androidx.lifecycle.MutableLiveData
import com.xc.kotlindemo.network.BaseViewModel
import com.xc.kotlindemo.network.NetworkService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class MainViewModel : BaseViewModel() {
    val logtType: MutableLiveData<String> = MutableLiveData()

    //使用官方库的 MainScope()获取一个协程作用域用于创建协程
    fun getModel() {
        launch(
            block = {
                val result = NetworkService.apiService.searchRepos("Android", 0, 20)
                if (result != null && !result.items.isNullOrEmpty()) {
                    logtType.postValue(result.toString())
                }
            },
            error = {
                errorLiveData.postValue(it)
            },
            complete = {
                loadingLiveData.postValue(false)
            }
        )
    }

    suspend fun testFlow2() {
        //生产者
        flow {
            //发射数据
            emit(5)
        }.collect {
            println("value=$it")
        }
    }
}