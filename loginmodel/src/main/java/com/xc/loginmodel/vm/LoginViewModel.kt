package com.xc.loginmodel.vm

import android.util.Log
import com.xc.common_base.u.network.BaseViewModel
import com.xc.common_base.u.network.NetworkService

class LoginViewModel : BaseViewModel() {
    fun login(phone: String, password: String) {
        launch(
            block = {
                val result = NetworkService.apiService.Login(phone, password)
                if (result != null && result.errorCode == 0) {
                    Log.e("TAG", "登陆成功")
                } else {
                    result.errorMsg?.let { it1 -> Log.e("TAG", it1) }
                }
            },
            error = {
                errorLiveData.postValue(it)
            },
            complete = {
                loadingLiveData.postValue(false)

            })
    }
}
