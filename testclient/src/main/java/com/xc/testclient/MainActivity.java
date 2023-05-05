package com.xc.testclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.xc.kotlindemo.IKtvController;

public class MainActivity extends AppCompatActivity {
    private IKtvController ktvController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.connect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindKtvService();
            }
        });
        findViewById(R.id.Pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ktvController == null) {
                        Log.e("Pause", "ktvController==null");
                    } else {
                        ktvController.setPause("sorry ~ pause");
                    }

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.Play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ktvController == null) {
                        Log.e("Play", "ktvController==null");
                    } else {
                        ktvController.setPause("hi ~ play");
                    }

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void bindKtvService() {
        Intent intent = new Intent();
        intent.setAction("com.xc.kotlindemo.aidlservice.KtvService");
        intent.setComponent(new ComponentName("com.xc.kotlindemo", "com.xc.kotlindemo.aidlservice.KtvService"));
        boolean b = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        Log.e("绑定----", b + "");
        Log.e("Play", "--------------------");
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            Log.e("client", "onServiceConnected-------------");
            try {
                ktvController = IKtvController.Stub.asInterface(service);
            } catch (Exception e) {

            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("client", "onServiceDisconnected---------");
            ktvController = null;
        }
    };

}