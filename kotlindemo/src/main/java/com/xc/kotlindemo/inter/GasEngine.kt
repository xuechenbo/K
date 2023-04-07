package com.xc.kotlindemo.inter

import android.util.Log
import javax.inject.Inject

class GasEngine @Inject constructor() : Engine {
    override fun start() {
        Log.e("GasEngine", "start")
    }

    override fun shutdown() {
        Log.e("GasEngine", "shutdown")
    }
}