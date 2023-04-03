package com.interview.com

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.interview.com.databinding.ActivityLaunchBinding
import com.xc.common_base.u.constants.ARouterConfig

class LaunchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaunchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {

            //直接跳转
//            ARouter.getInstance().build(ARouterConfig.LOGIN_MOUDLE).navigation()

            //传递参数
            ARouter.getInstance().build(ARouterConfig.LOGIN_MOUDLE)
                .withString("title", "标题").navigation()
            finish()
        }
    }

}