package com.xc.kotlindemo.model

import android.util.Log
import com.xc.kotlindemo.inter.BindElectricEngine
import com.xc.kotlindemo.inter.BindGasEngine
import com.xc.kotlindemo.inter.Engine
import javax.inject.Inject

class Truck @Inject constructor(val driver: Driver) {
    @BindGasEngine
    @Inject
    lateinit var gasEngine: Engine

    @BindElectricEngine
    @Inject
    lateinit var electricEngine: Engine
    //30

    fun deliver() {
        gasEngine.start()
        electricEngine.start()
        Log.e("TAG", "Truck")
        gasEngine.shutdown()
        electricEngine.shutdown()

    }
}
