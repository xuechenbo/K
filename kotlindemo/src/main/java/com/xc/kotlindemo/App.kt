package com.xc.kotlindemo

import com.alibaba.android.arouter.launcher.ARouter
import com.xc.common_base.u.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp
import skin.support.SkinCompatManager
import skin.support.app.SkinAppCompatViewInflater
import skin.support.app.SkinCardViewInflater
import skin.support.constraint.app.SkinConstraintViewInflater
import skin.support.design.app.SkinMaterialViewInflater

@HiltAndroidApp
class App : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            // 打印日志
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);

        SkinCompatManager.withoutActivity(this)
            .addInflater(SkinAppCompatViewInflater())           // 基础控件换肤初始化
            .addInflater(SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
            .addInflater(SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
            .addInflater(SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
//            .setSkinStatusBarColorEnable(false)                     // 关闭状态栏换肤，默认打开[可选]
//            .setSkinWindowBackgroundEnable(false)                   // 关闭windowBackground换肤，默认打开[可选]
            .loadSkin();
    }

}