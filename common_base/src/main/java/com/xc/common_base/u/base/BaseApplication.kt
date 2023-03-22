package com.xc.common_base.u.base

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import com.xc.common_base.BuildConfig

open class BaseApplication : Application() {
    companion object {
        lateinit var application: BaseApplication
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        application = this
        //MultiDex分包方法 初始化
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        //初始化ARouter
        initARouter()
        initUM()
    }

    private fun initUM() {
//        UMConfigure.init(this, "5ff566ddadb42d5826a069bf"
//                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "")

    }


    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            // 打印日志
//            ARouter.openLog()
//            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//            ARouter.openDebug()
            Log.e("初始化", "初始化Arouter")
        }
//        ARouter.init(application)
    }


}