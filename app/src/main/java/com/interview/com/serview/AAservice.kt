package com.interview.com.serview

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class AAservice : Service() {
    private val mBinder: MyBinder = MyBinder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)

    }

    override fun onBind(p0: Intent?): IBinder? {
        return mBinder
    }


    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }


    class MyBinder : Binder() {
        open fun getCount(): Int {
            return 1
        }
    }
}