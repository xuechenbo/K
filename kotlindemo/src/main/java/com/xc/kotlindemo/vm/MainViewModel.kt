package com.xc.kotlindemo.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xc.kotlindemo.model.MainModel
import com.xc.common_base.u.network.BaseViewModel
import com.xc.common_base.u.network.NetworkService
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {
    private lateinit var model: MainModel
    val logtType: MutableLiveData<String> = MutableLiveData()

    //使用官方库的 MainScope()获取一个协程作用域用于创建协程
    fun getList() {
        launch(
            block = {
                val result = NetworkService.apiService.searchRepos("Android", 0, 20)

                if (result != null && !result.items.isNullOrEmpty()) {
//                    logtType.postValue(result.toString())
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

    fun testFlow2() {
        //code 3
        val list = listOf(1, 2, 3, 4)
        val asFlow = list.asFlow()
        val testFlow = flowOf(65, 66, 67)
        viewModelScope.launch {
            testFlow.collect {
                println("输出：$it")
                println()
            }

            asFlow.collect {

            }
        }
    }
}