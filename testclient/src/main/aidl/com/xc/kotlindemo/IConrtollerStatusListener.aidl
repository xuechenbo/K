// IConrtollerStatusListener.aidl
package com.xc.kotlindemo;

// Declare any non-default types here with import statements

interface IConrtollerStatusListener {
    void onPauseSuccess();
    void onPauseFailed(int errorCode);
    void onPlaySuccess();
    void onPlayFailed(int errorCode);
}