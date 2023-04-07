package com.xc.kotlindemo.model;


import android.util.Log;

import javax.inject.Inject;

public class Driver {

    @Inject
    public Driver() {

    }

    public void info() {
        Log.e("TAG", "Driver");
    }
}
