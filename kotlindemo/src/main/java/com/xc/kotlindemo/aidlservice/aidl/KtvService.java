package com.xc.kotlindemo.aidlservice.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.xc.kotlindemo.IConrtollerStatusListener;
import com.xc.kotlindemo.IKtvController;

public class KtvService extends Service {
    private IConrtollerStatusListener listener;

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
            if (listener != null) {
                new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                        if (System.currentTimeMillis() % 2 == 0) {
                            listener.onPauseSuccess();
                        } else {
                            listener.onPauseFailed(1002);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (RemoteException remoteException) {
                        remoteException.printStackTrace();
                    }
                }).start();
            }
        }

        @Override
        public void setPlay(String play) throws RemoteException {
            Log.e("Service-play", play);
            //模拟播放耗时 1000 毫秒
            if (listener != null) {
                new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                        if (System.currentTimeMillis() % 2 == 0) {
                            listener.onPlaySuccess();
                        } else {
                            listener.onPlayFailed(1001);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (RemoteException remoteException) {
                        remoteException.printStackTrace();
                    }
                }).start();
            }
        }

        @Override
        public void setOnControllerStatusListener(IConrtollerStatusListener i) throws RemoteException {
            listener = i;
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
