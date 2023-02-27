package com.interview.com.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val tag = MutableLiveData<String>()

    fun getTag(){
        tag.postValue("ccccc")
    }
}