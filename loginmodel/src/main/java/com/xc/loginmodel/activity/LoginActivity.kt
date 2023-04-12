package com.xc.loginmodel.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.xc.common_base.u.base.BaseActivity
import com.xc.common_base.u.constants.ARouterConfig
import com.xc.loginmodel.databinding.ActivityLoginBinding
import com.xc.loginmodel.vm.LoginViewModel

@Route(path = ARouterConfig.LOGIN_MOUDLE, name = "登录")
class LoginActivity : BaseActivity<LoginViewModel>() {
    @Autowired
    open lateinit var title: String
    private lateinit var binding: ActivityLoginBinding
    override fun viewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ARouter.getInstance().inject(this)
        listener()
    }

    fun listener() {
        binding.login.setOnClickListener {
            mViewModel.login(binding.username.text.toString(), binding.password.text.toString())
        }

        mViewModel.run {
            errorLiveData.observeForever {

            }
        }
    }
}