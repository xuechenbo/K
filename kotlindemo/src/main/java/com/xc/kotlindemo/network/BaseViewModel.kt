package com.xc.kotlindemo.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException

//别名
typealias Block<T> = suspend (CoroutineScope) -> T
typealias Error = suspend (Exception) -> Unit
typealias Cancel = suspend (Exception) -> Unit
typealias Complete = suspend () -> Unit

open class BaseViewModel : ViewModel() {
    val errorLiveData = MutableLiveData<Throwable>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun abc(block: Block<Unit>, error: Error?) {

    }

    /**
     * 创建并执行协程
     * @param block 协程中执行
     * @param error 错误时执行
     * @param cancel 取消时只需
     * @param Complete 完成
     *
     * @return Job
     */
    fun launch(
        block: Block<Unit>,
        error: Error? = null,
        cancel: Cancel? = null,
        complete: Complete? = null
    ) {
        //显示loading
        loadingLiveData.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                block.invoke(this)
            } catch (e: Exception) {
                when (e) {
                    is CancellationException -> {
                        cancel?.invoke(e)
                    }
                    else -> {
                        onError(e)
                        error?.invoke(e)
                    }
                }
            } finally {
                complete?.invoke()
            }
        }
    }

    private fun onError(e: Exception) {
        when (e) {
            is ApiException -> {
                when (e.code) {
                    // 其他api错误
                    -1 -> Log.e("onError", e.message)
                    // 其他错误
                    else -> Log.e("onError", e.message)
                }
            }
            // 网络请求失败
            is ConnectException, is SocketTimeoutException, is UnknownHostException, is HttpException ->
                Log.e("onError", "网络请求失败")
            // 数据解析错误
            is JSONException ->
                Log.e("onError", "数据解析错误")
            // 其他错误
            else ->
                e.message?.let { Log.e("onError", it) }
        }
    }
}