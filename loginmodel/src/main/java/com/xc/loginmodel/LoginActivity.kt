package com.xc.loginmodel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.xc.common_base.u.constants.ARouterConfig

@Route(path = ARouterConfig.LOGIN_MOUDLE, name = "登录")
class LoginActivity : AppCompatActivity() {
    @Autowired
    open lateinit var title: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ARouter.getInstance().inject(this)
        Log.e("TAG", title)
    }
}