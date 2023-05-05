// IKtvController.aidl
package com.xc.kotlindemo;

// Declare any non-default types here with import statements

interface IKtvController {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void setPause(String pause);
     void setPlay(String play);
}