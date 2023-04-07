package com.xc.kotlindemo.inter

import android.util.Log
import javax.inject.Inject

class ElectricEngine @Inject constructor() : Engine {
    override fun start() {
        Log.e("ElectricEngine", "start")
    }

    override fun shutdown() {
        Log.e("ElectricEngine", "shutdown")
    }
}