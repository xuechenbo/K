package com.interview.com.serview;

import android.os.RemoteException;
import android.util.Log;

import com.interview.com.IMyAidlInterface;

//TODO 服务端  提供服务
public class AidlImp extends IMyAidlInterface.Stub {
    private String name;
    private int age;

    @Override
    public void start(int num) {
        Log.e("TAG", num + "");
    }

    @Override
    public void getNum(int a, int b) throws RemoteException {

    }

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    public int j(int a, int b) throws RemoteException {
        return a - b - 1;
    }


}
