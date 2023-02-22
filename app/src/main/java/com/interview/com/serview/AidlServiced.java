package com.interview.com.serview;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;


public class AidlServiced extends Service {

    public AidlServiced() {

    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        boolean b = Looper.getMainLooper().getThread() == Thread.currentThread();
        Log.i(
                "TAG", "handleActionPlayMusic： Current Thread is " +
                        Thread.currentThread().getName() +
                        " , is MainThread: " + b + ", main Thread:" + Looper.getMainLooper().getThread().getName()
        );
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new AidlImp();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
