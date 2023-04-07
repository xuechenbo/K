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
    var count = 1
    private val mutableStateFlow = MutableStateFlow(count)
    val stateFlow: StateFlow<Int> = mutableStateFlow


    fun SendFlow() {
        mutableStateFlow.value = count
        count++
    }

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
}