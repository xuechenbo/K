package com.xc.common_base.u.base

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.permissionx.guolindev.PermissionX
import com.xc.common_base.u.network.BaseViewModel

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    protected open lateinit var mViewModel: VM
    fun <T : BaseViewModel> getViewModel(clazz: Class<T>): T = ViewModelProvider(this)[clazz]

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this)[viewModelClass()]
    }

    protected abstract fun viewModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    inline fun getPro(str: String, crossinline logE: () -> Unit) {
        if (str.length == 3) {
            PermissionX.init(this)
                .permissions(Manifest.permission.CALL_PHONE)
                .request { a, b, c ->
                    if (a) {
                        logE()
                    } else {
                        Toast.makeText(this, "您拒绝了拨打电话权限", Toast.LENGTH_SHORT).show()
                    }
                }

        } else {
            Log.e("tag", "!=3base")
        }
    }


}