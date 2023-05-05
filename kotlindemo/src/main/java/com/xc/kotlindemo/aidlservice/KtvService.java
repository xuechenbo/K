package com.xc.kotlindemo.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.xc.kotlindemo.IKtvController;

public class KtvService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    //构造内部类
    private IKtvController.Stub stub = new IKtvController.Stub() {

        @Override
        public void setPause(String pause) throws RemoteException {
            Log.e("Service-pause", pause);
        }

        @Override
        public void setPlay(String play) throws RemoteException {
            Log.e("Service-play", play);
        }
    };

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("Service", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e("Service", "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        Log.e("Service", "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Service", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e("Service", "onRebind");
        super.onRebind(intent);
    }

}
