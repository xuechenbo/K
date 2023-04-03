package com.interview.com

import com.alibaba.android.arouter.launcher.ARouter
import com.xc.common_base.u.base.BaseApplication


class App : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            // 打印日志
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

}