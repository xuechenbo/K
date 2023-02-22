package com.interview.com.serview

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.Looper
import android.util.Log

class MusicPlayerService : IntentService("MusicPlayerService") {
    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            val action = intent.action
            Log.i(TAG, "onHandleIntent: action = $action")
            if (ACTION_PLAY_MUSIC == action) {
                handleActionPlayMusic()
            }
        }
    }

    private fun handleActionPlayMusic() {
        val isMainThread = Looper.getMainLooper().thread === Thread.currentThread()
        //打印handle此任务的出当前线程名
        Log.i(
            TAG, "handleActionPlayMusic： Current Thread is " +
                    Thread.currentThread().name +
                    " , is MainThread: " + isMainThread
        )
    }

    override fun onCreate() {
        Log.i(TAG, "onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onStart(intent: Intent?, startId: Int) {
        Log.i(TAG, "onStart")
        super.onStart(intent, startId)
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy")
        super.onDestroy()
    }

    companion object {
        private const val ACTION_PLAY_MUSIC = "com.wanda.servicedemo.action.PLAY_MUSIC"
        private val TAG = MusicPlayerService::class.java.simpleName
        fun startPlayer(context: Context) {
            Log.i(TAG, "startPlayer")
            val intent = Intent(context, MusicPlayerService::class.java)
            intent.action = ACTION_PLAY_MUSIC
            context.startService(intent)
        }
    }
}