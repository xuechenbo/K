package com.interview.com.serview;

import android.util.Log;

import com.interview.com.IMyAidlInterface;

public class AidlImp extends IMyAidlInterface.Stub {
    private String name;
    private int age;

    @Override
    public void start(int num) {
        Log.e("TAG", num + "");
    }

    @Override
    public void getNum(int a, int b) {
        Log.e("TAG", "total==" + a + b);
    }
}
