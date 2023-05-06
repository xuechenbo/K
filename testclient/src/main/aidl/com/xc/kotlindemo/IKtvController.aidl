// IKtvController.aidl
package com.xc.kotlindemo;

//导入AIDL
import com.xc.kotlindemo.IConrtollerStatusListener;

interface IKtvController {
    void setOnControllerStatusListener(in IConrtollerStatusListener i);
    void setPause(String pause);
    void setPlay(String play);
}